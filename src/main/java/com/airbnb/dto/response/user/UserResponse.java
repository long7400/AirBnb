package com.airbnb.dto.response.user;

import com.airbnb.dto.response.booking.BookingResponse;
import com.airbnb.dto.response.profile.ProfileResponse;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {

    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "bookings")
    private List<BookingResponse> bookings;

    @Column(name = "profile")
    private ProfileResponse profile;
}