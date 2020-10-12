package com.hebert.bloodbank.repository.mapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.hebert.bloodbank.enums.BloodTypes;
import com.hebert.bloodbank.model.dto.DonatorQuantityByBloodTypeDTO;
import com.hebert.bloodbank.model.dto.DonatorForBloodTypeDTO;

public class DonatorFromByBloodTypeDTOMapper implements RowMapper<DonatorForBloodTypeDTO> {

	@Override
	public DonatorForBloodTypeDTO mapRow(ResultSet row, int rowNum) throws SQLException {
		BloodTypes btype = BloodTypes.getByOrdinal(row.getInt("blood_type"));
		return new DonatorForBloodTypeDTO(btype.getType(), new BigDecimal(row.getString("quant")));
	}

}