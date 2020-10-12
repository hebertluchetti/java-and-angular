package com.hebert.bloodbank.repository.mapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.hebert.bloodbank.enums.BloodTypes;
import com.hebert.bloodbank.model.dto.DonorQuantityByBloodTypeDTO;

public class DonorByBloodTypeDTOMapper implements RowMapper<DonorQuantityByBloodTypeDTO> {

	@Override
	public DonorQuantityByBloodTypeDTO mapRow(ResultSet row, int rowNum) throws SQLException {
		BloodTypes btype = BloodTypes.getByOrdinal(row.getInt("blood_type"));
		return new DonorQuantityByBloodTypeDTO(btype.getType(), new BigDecimal(row.getString("quant")));
	}

}