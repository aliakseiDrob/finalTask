package com.epam.hotel.entity;

import com.epam.hotel.entity.enums.RoomStatus;
import com.epam.hotel.entity.enums.RoomType;

import java.math.BigDecimal;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Room room = (Room) o;
        return capacity == room.capacity && number == room.number && Objects.equals(id, room.id) && type == room.type && Objects.equals(price, room.price) && status == room.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, price, status, capacity, number);
    }
}
