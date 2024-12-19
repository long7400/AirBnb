package com.airbnb.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
public class HomestayAmenityId implements Serializable {

    private Long homestayId;
    private Long amenityId;

    // Override equals
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HomestayAmenityId that = (HomestayAmenityId) o;
        return Objects.equals(homestayId, that.homestayId) &&
                Objects.equals(amenityId, that.amenityId);
    }

    // Override hashCode
    @Override
    public int hashCode() {
        return Objects.hash(homestayId, amenityId);
    }
}