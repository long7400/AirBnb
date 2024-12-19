package com.airbnb.dto.request.user;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChangePassRequest {

    @Column(name = "old_password")
    private String oldPassword;

    @Column(name = "new_password")
    private String newPassword;
}