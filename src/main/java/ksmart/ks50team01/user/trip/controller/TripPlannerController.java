package ksmart.ks50team01.user.trip.controller;

import ksmart.ks50team01.user.trip.service.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TripPlannerController {

    private final RegionService regionService;
}
