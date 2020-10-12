package com.hebert.bloodbank.repository.mapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.hebert.bloodbank.enums.BloodTypes;
import com.hebert.bloodbank.model.dto.DonorQuantityByBloodTypeDTO;
import com.hebert.bloodbank.model.dto.DonorForBloodTypeDTO;

public class DonorFromByBloodTypeDTOMapper implements RowMapper<DonorForBloodTypeDTO> {

	@Override
	public DonorForBloodTypeDTO mapRow(ResultSet row, int rowNum) throws SQLException {
		BloodTypes btype = BloodTypes.getByOrdinal(row.getInt("blood_type"));
		return new DonorForBloodTypeDTO(btype.getType(), new BigDecimal(row.getString("quant")));
	}

}