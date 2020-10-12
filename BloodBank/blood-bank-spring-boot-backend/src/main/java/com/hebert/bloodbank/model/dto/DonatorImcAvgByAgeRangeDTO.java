package com.hebert.bloodbank.model.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class DonatorImcAvgByAgeRangeDTO {
	private String ageRange;
	private BigDecimal imcAvg;
}
