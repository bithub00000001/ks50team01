package ksmart.ks50team01.chimney.cleansys.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import ksmart.ks50team01.chimney.cleansys.service.CustomDoubleDeserializer;
import lombok.Data;

/**
 * 실시간 측정 결과 데이터 전송 객체
 */
@Data
public class MeasurementResultDTO {

	/** 지역명 */
	@JsonProperty("area_nm")
	private String areaNm;

	/** 사업장명 */
	@JsonProperty("fact_manage_nm")
	private String factManageNm;

	/** 배출구 번호 */
	@JsonProperty("stack_code")
	private String stackCode;

	/** 측정 시간 */
	@JsonProperty("mesure_dt")
	// 날짜 형식을 명시적으로 지정
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm[:ss]") // 초가 있을 수도 있고 없을 수도 있음
	private LocalDateTime mesureDt;

	/** 먼지 허용 기준값 */
	@JsonProperty("tsp_exhst_perm_stdr_value")
	@JsonDeserialize(using = CustomDoubleDeserializer.class)
	private Double tspExhstPermStdrValue;

	/** 먼지 측정값 */
	@JsonProperty("tsp_mesure_value")
	@JsonDeserialize(using = CustomDoubleDeserializer.class)
	private Double tspMesureValue;

	/** 황산화물 허용 기준값 */
	@JsonProperty("sox_exhst_perm_stdr_value")
	@JsonDeserialize(using = CustomDoubleDeserializer.class)
	private Double soxExhstPermStdrValue;

	/** 황산화물 측정값 */
	@JsonProperty("sox_mesure_value")
	@JsonDeserialize(using = CustomDoubleDeserializer.class)
	private Double soxMesureValue;

	/** 질소산화물 허용 기준값 */
	@JsonProperty("nox_exhst_perm_stdr_value")
	@JsonDeserialize(using = CustomDoubleDeserializer.class)
	private Double noxExhstPermStdrValue;

	/** 질소산화물 측정값 */
	@JsonProperty("nox_mesure_value")
	@JsonDeserialize(using = CustomDoubleDeserializer.class)
	private Double noxMesureValue;

	/** 염화수소 허용 기준값 */
	@JsonProperty("hcl_exhst_perm_stdr_value")
	@JsonDeserialize(using = CustomDoubleDeserializer.class)
	private Double hclExhstPermStdrValue;

	/** 염화수소 측정값 */
	@JsonProperty("hcl_mesure_value")
	@JsonDeserialize(using = CustomDoubleDeserializer.class)
	private Double hclMesureValue;

	/** 불화수소 허용 기준값 */
	@JsonProperty("hf_exhst_perm_stdr_value")
	@JsonDeserialize(using = CustomDoubleDeserializer.class)
	private Double hfExhstPermStdrValue;

	/** 불화수소 측정값 */
	@JsonProperty("hf_mesure_value")
	@JsonDeserialize(using = CustomDoubleDeserializer.class)
	private Double hfMesureValue;

	/** 암모니아 허용 기준값 */
	@JsonProperty("nh3_exhst_perm_stdr_value")
	@JsonDeserialize(using = CustomDoubleDeserializer.class)
	private Double nh3ExhstPermStdrValue;

	/** 암모니아 측정값 */
	@JsonProperty("nh3_mesure_value")
	@JsonDeserialize(using = CustomDoubleDeserializer.class)
	private Double nh3MesureValue;

	/** 일산화탄소 허용 기준값 */
	@JsonProperty("co_exhst_perm_stdr_value")
	@JsonDeserialize(using = CustomDoubleDeserializer.class)
	private Double coExhstPermStdrValue;

	/** 일산화탄소 측정값 */
	@JsonProperty("co_mesure_value")
	@JsonDeserialize(using = CustomDoubleDeserializer.class)
	private Double coMesureValue;
}
