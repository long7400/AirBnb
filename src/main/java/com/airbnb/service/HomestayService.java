package com.airbnb.service;

import com.airbnb.dto.request.home.HomestayRequest;
import com.airbnb.dto.request.home.HomestaySearchRequest;
import com.airbnb.dto.response.home.HomestayDTO;
import com.airbnb.dto.response.home.HomestayResponse;

import java.util.List;

public interface HomestayService {

    boolean createHomestay(HomestayRequest request) throws Exception;

    List<HomestayDTO> searchHomestays(HomestaySearchRequest request);
}
