package com.epam.hotel.entity;

import com.epam.hotel.entity.enums.ApplicationStatus;
import com.epam.hotel.entity.enums.RoomType;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

public class Application implements Identifier {
    private Long id;
    private LocalDate dateCheckIn;
    private LocalDate dateCheckOut;
    private RoomType type;
    private byte capacity;
    private ApplicationStatus status;
    private Long userId;
    private Long roomId;
    private BigDecimal invoice;

    public Application(Long id, LocalDate dateCheckIn, LocalDate dateCheckOut, RoomType type, byte capacity, ApplicationStatus status, Long userId, Long roomId, BigDecimal invoice) {
        this.id = id;
        this.dateCheckIn = dateCheckIn;
        this.dateCheckOut = dateCheckOut;
        this.type = type;
        this.capacity = capacity;
        this.status = status;
        this.userId = userId;
        this.roomId = roomId;
        this.invoice = invoice;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getDateCheckIn() {
        return dateCheckIn;
    }

    public void setDateCheckIn(LocalDate dateCheckIn) {
        this.dateCheckIn = dateCheckIn;
    }

    public LocalDate getDateCheckOut() {
        return dateCheckOut;
    }

    public void setDateCheckOut(LocalDate dateCheckOut) {
        this.dateCheckOut = dateCheckOut;
    }

    public RoomType getType() {
        return type;
    }

    public void setType(RoomType type) {
        this.type = type;
    }

    public byte getCapacity() {
        return capacity;
    }

    public void setCapacity(byte capacity) {
        this.capacity = capacity;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

    public BigDecimal getInvoice() {
        return invoice;
    }

    public void setInvoice(BigDecimal invoice) {
        this.invoice = invoice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Application that = (Application) o;
        return capacity == that.capacity && Objects.equals(id, that.id) && Objects.equals(dateCheckIn, that.dateCheckIn) && Objects.equals(dateCheckOut, that.dateCheckOut) && type == that.type && status == that.status && Objects.equals(userId, that.userId) && Objects.equals(roomId, that.roomId) && Objects.equals(invoice, that.invoice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateCheckIn, dateCheckOut, type, capacity, status, userId, roomId, invoice);
    }

    @Override
    public String toString() {
        return "Application{" +
                "id=" + id +
                ", dateCheckIn=" + dateCheckIn +
                ", dateCheckOut=" + dateCheckOut +
                ", type=" + type +
                ", capacity=" + capacity +
                ", status=" + status +
                ", userId=" + userId +
                ", roomId=" + roomId +
                ", invoice=" + invoice +
                '}';
    }
}
