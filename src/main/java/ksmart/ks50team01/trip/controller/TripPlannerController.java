package ksmart.ks50team01.trip.controller;

import ksmart.ks50team01.trip.service.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TripPlannerController {

    private final RegionService regionService;
}
