package com.hebert.bloodbank.repository.mapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.hebert.bloodbank.model.dto.DonatorQuantityByStateDTO;

public class DonatorByStateDTOMapper implements RowMapper<DonatorQuantityByStateDTO> {

	@Override
	public DonatorQuantityByStateDTO mapRow(ResultSet row, int rowNum) throws SQLException {
		return new DonatorQuantityByStateDTO(row.getString("state"), new BigDecimal(row.getString("quant")));
	}

}