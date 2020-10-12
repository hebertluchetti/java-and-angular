package com.hebert.bloodbank.repository.mapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.hebert.bloodbank.enums.GenderTypes;
import com.hebert.bloodbank.model.dto.DonorImcPercentageByGenderDTO;

public class DonorGenderPercentageByImcDTOMapper implements RowMapper<DonorImcPercentageByGenderDTO> {

	@Override
	public DonorImcPercentageByGenderDTO mapRow(ResultSet row, int rowNum) throws SQLException {
		GenderTypes type = GenderTypes.getByOrdinal(row.getInt("gender"));
		return new DonorImcPercentageByGenderDTO(type.getType(), new BigDecimal(row.getDouble("percentage")));
	}

}