package com.airbnb.mapper.user.impl;

import com.airbnb.dto.request.user.UserRegistrationRequest;
import com.airbnb.dto.response.booking.BookingResponse;
import com.airbnb.dto.response.user.UserResponse;
import com.airbnb.entity.User;
import com.airbnb.enums.UserStatus;
import com.airbnb.enums.UserType;
import com.airbnb.mapper.booking.BookingMapper;
import com.airbnb.mapper.user.UserMapper;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserMapperImpl implements UserMapper {

    @Resource
    private BookingMapper bookingMapper;

    @Override
    public User toEntity(UserRegistrationRequest request) {
        return User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .fullName(request.getFullName())
                .type(UserType.GUEST)
                .status(UserStatus.ACTIVE)
                .isDelete(false)
                .build();
    }

    @Override
    public UserResponse toResponse(User user) {
        UserResponse userResponse = UserResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .build();

        List<BookingResponse> bookingDTOList = bookingMapper.toListResponse(user.getBookings());
        userResponse.setBookings(bookingDTOList);

        return userResponse;
    }
}