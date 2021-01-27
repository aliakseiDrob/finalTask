package com.epam.hotel.connection;

import com.epam.hotel.exception.ConnectionPoolException;
import com.epam.hotel.exception.DaoException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class ConnectionPool {
    private ConnectionFactory connectionFactory;
    private static ConnectionPool instance = null;
    private static final AtomicBoolean initStarted = new AtomicBoolean(false);
    private final Queue<ProxyConnection> availableConnections;
    private final Queue<ProxyConnection> connectionsInUse;
    private static final ReentrantLock CONNECTIONS_LOCKER = new ReentrantLock();
    private static final ReentrantLock INSTANCE_LOCKER = new ReentrantLock();
    private final Semaphore semaphore = new Semaphore(POOL_SIZE, true);
    private static final int POOL_SIZE = 10;

    private ConnectionPool() {
        if (instance != null) {
            throw new ConnectionPoolException("Only one instance is allowed for ConnectionPool class");
        }
        availableConnections = new ArrayDeque<>();
        connectionsInUse = new ArrayDeque<>();
        for (int i = 0; i < POOL_SIZE; i++) {
            connectionFactory = new ConnectionFactory();
            Connection connection = connectionFactory.create();
            ProxyConnection proxyConnection = new ProxyConnection(connection, this);
            availableConnections.add(proxyConnection);
        }
    }

    public static ConnectionPool getInstance() {        // TODO improve
        ConnectionPool localInstance = instance;
        if (!initStarted.get()) {
            INSTANCE_LOCKER.lock();
            try {
                if (!initStarted.getAndSet(true)) {
                    instance = new ConnectionPool();
                }
            } finally {
                INSTANCE_LOCKER.unlock();
            }
        }
        return instance;
    }

    public void returnConnection(ProxyConnection proxyConnection) {

        CONNECTIONS_LOCKER.lock();
        try {
            if (connectionsInUse.contains(proxyConnection)) {
                connectionsInUse.remove(proxyConnection);
                availableConnections.offer(proxyConnection);
                semaphore.release();
            }
        } finally {
            CONNECTIONS_LOCKER.unlock();
        }
    }

    public ProxyConnection getConnection() throws DaoException {
        try {
            semaphore.acquire();
            CONNECTIONS_LOCKER.lock();
            ProxyConnection proxyConnection = availableConnections.poll();
            connectionsInUse.offer(proxyConnection);
            return proxyConnection;
        } catch (InterruptedException e) {
            throw new DaoException(e);
        } finally {
            CONNECTIONS_LOCKER.unlock();
        }
    }

    public void closeAllConnections() throws SQLException {
        for (Connection connection : connectionsInUse) {
            connection.close();
        }
        for (ProxyConnection connection : availableConnections) {
            connection.closeConnection();
        }
    }

}
