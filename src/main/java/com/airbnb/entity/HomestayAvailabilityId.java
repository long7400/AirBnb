package com.airbnb.entity;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Data
public class HomestayAvailabilityId implements Serializable {
    private Long homestayId;
    private LocalDate date;

    // Override equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HomestayAvailabilityId that = (HomestayAvailabilityId) o;
        return Objects.equals(homestayId, that.homestayId) &&
                Objects.equals(date, that.date);
    }

    // Override hashCode
    @Override
    public int hashCode() {
        return Objects.hash(homestayId, date);
    }
}