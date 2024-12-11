package com.airbnb.mapper;

import com.airbnb.dto.response.common.CommonResponse;
import com.airbnb.dto.response.common.ResponseCode;

public interface CommonMapper {
    <T> CommonResponse<T> mapToCommonResponse(ResponseCode responseCode, T data);
}