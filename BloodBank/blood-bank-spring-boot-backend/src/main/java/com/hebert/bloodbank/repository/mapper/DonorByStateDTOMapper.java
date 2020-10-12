package com.hebert.bloodbank.repository.mapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.hebert.bloodbank.model.dto.DonorQuantityByStateDTO;

public class DonorByStateDTOMapper implements RowMapper<DonorQuantityByStateDTO> {

	@Override
	public DonorQuantityByStateDTO mapRow(ResultSet row, int rowNum) throws SQLException {
		return new DonorQuantityByStateDTO(row.getString("state"), new BigDecimal(row.getString("quant")));
	}

}