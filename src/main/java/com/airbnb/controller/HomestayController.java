package com.airbnb.controller;

import com.airbnb.annotation.HostOnly;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@HostOnly
@Slf4j
@RestController
@RequestMapping("/api/v1/host/homestay")
public class HomestayController {
}