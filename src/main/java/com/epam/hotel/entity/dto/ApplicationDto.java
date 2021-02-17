package com.epam.hotel.entity.dto;

import com.epam.hotel.entity.Identifier;
import com.epam.hotel.entity.enums.RoomType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class ApplicationDto implements Identifier {
    private Long id;
    private LocalDate dateCheckIn;
    private LocalDate dateCheckOut;
    private RoomType type;
    private byte capacity;
    private BigDecimal invoice;
    private int roomNumber;

    public ApplicationDto(Long id, LocalDate dateCheckIn, LocalDate dateCheckOut, RoomType type, byte capacity, BigDecimal invoice, int roomNumber) {
        this.id = id;
        this.dateCheckIn = dateCheckIn;
        this.dateCheckOut = dateCheckOut;
        this.type = type;
        this.capacity = capacity;
        this.invoice = invoice;
        this.roomNumber = roomNumber;
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

    public BigDecimal getInvoice() {
        return invoice;
    }

    public void setInvoice(BigDecimal invoice) {
        this.invoice = invoice;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ApplicationDto that = (ApplicationDto) o;
        return capacity == that.capacity && roomNumber == that.roomNumber && Objects.equals(id, that.id) && Objects.equals(dateCheckIn, that.dateCheckIn) && Objects.equals(dateCheckOut, that.dateCheckOut) && type == that.type && Objects.equals(invoice, that.invoice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, dateCheckIn, dateCheckOut, type, capacity, invoice, roomNumber);
    }
}
