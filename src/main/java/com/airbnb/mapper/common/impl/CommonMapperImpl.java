package com.airbnb.mapper.common.impl;

import com.airbnb.dto.response.common.CommonResponse;
import com.airbnb.dto.response.common.ResponseCode;
import com.airbnb.mapper.common.CommonMapper;
import org.springframework.stereotype.Component;

@Component
public class CommonMapperImpl implements CommonMapper {

    @Override
    public <T> CommonResponse<T> mapToCommonResponse(ResponseCode responseCode, T data) {
        return new CommonResponse<>(responseCode.getCode(), responseCode.getMessage(), data);
    }
}
