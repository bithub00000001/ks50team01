package ksmart.ks50team01.trip.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TripOptionMapper {
    List<String> getRegions();

    List<String> getOptions(@Param("region") String region, @Param("category") String category);
}
