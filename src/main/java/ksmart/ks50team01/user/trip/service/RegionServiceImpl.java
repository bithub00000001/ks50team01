package ksmart.ks50team01.user.trip.service;

import ksmart.ks50team01.user.trip.mapper.TripOptionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RegionServiceImpl implements RegionService{

    private final TripOptionMapper tripOptionMapper;

    @Override
    public List<String> getRegion() {
        return tripOptionMapper.getRegions();
    }
}
