package com.hebert.bloodbank.repository.mapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.hebert.bloodbank.model.dto.DonatorImcAvgByAgeRangeDTO;

public class DonatorImcAvgByAgeRangeDTOMapper implements RowMapper<DonatorImcAvgByAgeRangeDTO> {

	@Override
	public DonatorImcAvgByAgeRangeDTO mapRow(ResultSet row, int rowNum) throws SQLException {
		return new DonatorImcAvgByAgeRangeDTO(row.getString("age_range"), new BigDecimal(row.getDouble("imc_avg")));
	}

}