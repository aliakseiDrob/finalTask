package com.epam.hotel.entity;

import com.epam.hotel.entity.enums.RoomStatus;
import com.epam.hotel.entity.enums.RoomType;

import java.math.BigDecimal;

public class Room implements Identifier {
    private Long id;
    private RoomType type;
    private BigDecimal price;
    private RoomStatus status;
    private byte capacity;
    private int number;

    public Room(Long id, RoomType type, BigDecimal price, RoomStatus status, byte capacity, int number) {
        this.id = id;
        this.type = type;
        this.price = price;
        this.status = status;
        this.capacity = capacity;
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoomType getType() {
        return type;
    }

    public void setType(RoomType type) {
        this.type = type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public RoomStatus getStatus() {
        return status;
    }

    public void setStatus(RoomStatus status) {
        this.status = status;
    }

    public byte getCapacity() {
        return capacity;
    }

    public void setCapacity(byte capacity) {
        this.capacity = capacity;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
