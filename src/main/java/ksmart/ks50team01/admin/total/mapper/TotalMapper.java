package ksmart.ks50team01.admin.total.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TotalMapper {
	
	int getDailyLogin();
	
	int getNewMemeberTotal();
	int getWithdrawnMemeberTotal();
	
	
}
