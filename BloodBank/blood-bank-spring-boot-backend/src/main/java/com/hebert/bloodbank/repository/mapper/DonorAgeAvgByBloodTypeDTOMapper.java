package com.hebert.bloodbank.repository.mapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.hebert.bloodbank.enums.BloodTypes;
import com.hebert.bloodbank.model.dto.DonorAgeAvgByBloodTypeDTO;

public class DonorAgeAvgByBloodTypeDTOMapper implements RowMapper<DonorAgeAvgByBloodTypeDTO> {

	@Override
	public DonorAgeAvgByBloodTypeDTO mapRow(ResultSet row, int rowNum) throws SQLException {
		BloodTypes btype = BloodTypes.getByOrdinal(row.getInt("blood_type"));
		return new DonorAgeAvgByBloodTypeDTO(btype.getType(), new BigDecimal(row.getString("age_avg")));
	}

}