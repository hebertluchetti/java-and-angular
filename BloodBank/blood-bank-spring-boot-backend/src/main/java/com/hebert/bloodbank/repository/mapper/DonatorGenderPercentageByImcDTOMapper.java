package com.hebert.bloodbank.repository.mapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.hebert.bloodbank.enums.GenderTypes;
import com.hebert.bloodbank.model.dto.DonatorImcPercentageByGenderDTO;

public class DonatorGenderPercentageByImcDTOMapper implements RowMapper<DonatorImcPercentageByGenderDTO> {

	@Override
	public DonatorImcPercentageByGenderDTO mapRow(ResultSet row, int rowNum) throws SQLException {
		GenderTypes type = GenderTypes.getByOrdinal(row.getInt("gender"));
		return new DonatorImcPercentageByGenderDTO(type.getType(), new BigDecimal(row.getDouble("percentage")));
	}

}