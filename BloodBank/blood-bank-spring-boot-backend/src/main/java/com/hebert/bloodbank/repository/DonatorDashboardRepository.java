package com.hebert.bloodbank.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.hebert.bloodbank.model.dto.DonatorAgeAvgByBloodTypeDTO;
import com.hebert.bloodbank.model.dto.DonatorImcAvgByAgeRangeDTO;
import com.hebert.bloodbank.model.dto.DonatorImcPercentageByGenderDTO;
import com.hebert.bloodbank.model.dto.DonatorQuantityByBloodTypeDTO;
import com.hebert.bloodbank.model.dto.DonatorQuantityByStateDTO;
import com.hebert.bloodbank.model.dto.DonatorForBloodTypeDTO;
import com.hebert.bloodbank.repository.mapper.DonatorAgeAvgByBloodTypeDTOMapper;
import com.hebert.bloodbank.repository.mapper.DonatorByBloodTypeDTOMapper;
import com.hebert.bloodbank.repository.mapper.DonatorByStateDTOMapper;
import com.hebert.bloodbank.repository.mapper.DonatorFromByBloodTypeDTOMapper;
import com.hebert.bloodbank.repository.mapper.DonatorImcAvgByAgeRangeDTOMapper;
import com.hebert.bloodbank.repository.mapper.DonatorGenderPercentageByImcDTOMapper;

@Repository
public class DonatorDashboardRepository {
	private static final Logger LOGGER = LoggerFactory.getLogger(DonatorDashboardRepository.class);
	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	 
	public DonatorDashboardRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate; 
	}
	 
	 private final String SQL_DONATORS_BY_STATE = "/** DonatorDashboardRepository.findDonatorsByState **/"+
	 		" SELECT s.name as state, count(*) as quant " + 
	 		"  FROM bloodbankdb.donator d " + 
	 		"	join bloodbankdb.address a on (d.address_id = a.id) " + 
	 		"	join bloodbankdb.city c on (a.city_id = c.id) " + 
	 		"	join bloodbankdb.state s on (c.state_id = s.id) " + 
	 		" GROUP BY s.id  " + 
	 		" ORDER BY s.name";
		 
	 private final String SQL_AGE_AVG_BY_BLOOD_TYPE_ = "/** DonatorDashboardRepository.findAgeAvgByBloodType **/"+
			 "SELECT d.blood_type, avg((YEAR(CURDATE())-YEAR(d.birth_date)) ) as age_avg " + 
	 		"  FROM bloodbankdb.donator as d " + 
	 		" GROUP BY d.blood_type ";
	 
	 private final String SQL_DONATORS_BY_BLOOD_TYPE = "/** DonatorDashboardRepository.findDonatorsByBloodType **/"+
		 		" SELECT  d.blood_type as blood_type, count(*) as quant " + 
		 		"  FROM bloodbankdb.donator d " + 
		 		" GROUP BY d.blood_type " + 
		 		" ORDER BY quant";
	 
	 private final String SQL_IMC_AVG_BY_AGE_RANGE = "/** DonatorDashboardRepository.findDonatorImcAvgByAgeRange **/"+
	 " SELECT AVG(d.weight/(d.height*d.height)) AS imc_avg, " + 
	 "		CASE  " + 
	 "			WHEN (YEAR(CURDATE())-YEAR(d.birth_date)) BETWEEN 0 and 10 THEN '0-10' " + 
	 "			WHEN (YEAR(CURDATE())-YEAR(d.birth_date)) BETWEEN 11 and 20 THEN '11-20' " + 
	 "			WHEN (YEAR(CURDATE())-YEAR(d.birth_date)) BETWEEN 21 and 30 THEN '21-30' " + 
	 "          WHEN (YEAR(CURDATE())-YEAR(d.birth_date)) BETWEEN 31 and 40 THEN '31-40' " + 
	 "          WHEN (YEAR(CURDATE())-YEAR(d.birth_date)) BETWEEN 41 and 50 THEN '41-50' " + 
	 "          WHEN (YEAR(CURDATE())-YEAR(d.birth_date)) BETWEEN 51 and 60 THEN '51-60' " + 
	 "			WHEN (YEAR(CURDATE())-YEAR(d.birth_date)) BETWEEN 61 and 70 THEN '61-70' " + 
	 "			WHEN (YEAR(CURDATE())-YEAR(d.birth_date)) BETWEEN 71 and 80 THEN '71-80' " + 
	 "			WHEN (YEAR(CURDATE())-YEAR(d.birth_date)) BETWEEN 81 and 90 THEN '81-90' " + 
	 "			WHEN (YEAR(CURDATE())-YEAR(d.birth_date)) BETWEEN 91 and 100 THEN '91-100' " + 
	 "			WHEN (YEAR(CURDATE())-YEAR(d.birth_date)) BETWEEN 101 and 110 THEN '101-110' " + 
	 "			WHEN (YEAR(CURDATE())-YEAR(d.birth_date)) BETWEEN 111 and 120 THEN '111-120' " + 
	 "      END AS age_range " + 
	 "  FROM bloodbankdb.donator d " + 
	 " GROUP BY age_range " + 
	 " ORDER BY age_range";
	 
	 private final String SQL_IMC_PERCENT_BY_GENDER = "/** DonatorDashboardRepository.findDonatorGenderPercentageByImc **/"+
	 		" SELECT count(dg.id) / t.total * 100 AS percentage, " + 
	 		"		 dg.gender AS gender " + 
	 		"  FROM bloodbankdb.donator dg, " + 
	 		"		( SELECT count(*) as total " + 
	 		"		   FROM bloodbankdb.donator d ) t " + 
	 		" WHERE dg.weight/(dg.height*dg.height) > :imcValue " + 
	 		" GROUP BY dg.gender ";
	 
	 private final String SQL_DONATORS_FOR_EACH_BLOOD_TYPE = "/** DonatorDashboardRepository.findDonatorsFromByBloodType **/"+
	 		" WITH bloodtypes AS ( " + 
	 		"	SELECT distinct dg.blood_type " + 
	 		"	 FROM bloodbankdb.donator dg	 " + 
	 		" ) " + 
	 		" SELECT count(*) AS quant,  " + 
	 		"		 btypes.blood_type  " + 
	 		"  FROM bloodbankdb.donator dg, " + 
	 		" 		bloodtypes btypes " + 
	 		" WHERE dg.weight > :minWeight  " + 
	 		"	   AND (YEAR(CURDATE())-YEAR(dg.birth_date)) BETWEEN :minAge and :maxAge " + 
	 		"      AND (  " + 
	 		"        CASE  " + 
	 		"			WHEN btypes.blood_type = 0 AND dg.blood_type IN (:fromAp) THEN 1 " + 
	 		"			WHEN btypes.blood_type = 1 AND dg.blood_type IN (:fromAn) THEN 1 " + 
	 		"			WHEN btypes.blood_type = 2 AND dg.blood_type IN (:fromBp) THEN 1 " + 
	 		"			WHEN btypes.blood_type = 3 AND dg.blood_type IN (:fromBn) THEN 1 " + 
	 		"           WHEN btypes.blood_type = 4 AND dg.blood_type IN (:fromABp) THEN 1 " + 
	 		"           WHEN btypes.blood_type = 5 AND dg.blood_type IN (:fromABn) THEN 1 " + 
	 		"           WHEN btypes.blood_type = 6 AND dg.blood_type IN (:fromOp) THEN 1 " + 
	 		"           WHEN btypes.blood_type = 7 AND dg.blood_type IN (:fromOn) THEN 1 " + 
	 		"           ELSE 0 " + 
	 		"         END ) " + 
	 		"GROUP BY btypes.blood_type " + 
	 		"ORDER BY btypes.blood_type; ";
		 
	 public List<DonatorQuantityByStateDTO> findDonatorQuantityByState()  {
	        RowMapper<DonatorQuantityByStateDTO> rowMapper = new DonatorByStateDTOMapper();
	        List<DonatorQuantityByStateDTO> list = new ArrayList<DonatorQuantityByStateDTO>();
	        Map<String, Object> paramMap = new HashMap<String, Object>();
	        
	        LOGGER.info("[findDonatorQuantityByState] Generating the query");
	        list = namedParameterJdbcTemplate.query(SQL_DONATORS_BY_STATE, paramMap, rowMapper);
	        LOGGER.info("[findDonatorQuantityByState] Finished");

	        return list;
	 }
	  
	 public List<DonatorAgeAvgByBloodTypeDTO> findDonatorAgeAvgByBloodType()  {
	        RowMapper<DonatorAgeAvgByBloodTypeDTO> rowMapper = new DonatorAgeAvgByBloodTypeDTOMapper();
	        List<DonatorAgeAvgByBloodTypeDTO> list = new ArrayList<DonatorAgeAvgByBloodTypeDTO>();
	        Map<String, Object> paramMap = new HashMap<String, Object>();
	        
	        LOGGER.info("[findDonatorAgeAvgByBloodType] Generating the query");
	        list = namedParameterJdbcTemplate.query(SQL_AGE_AVG_BY_BLOOD_TYPE_, paramMap, rowMapper);
	        LOGGER.info("[findDonatorAgeAvgByBloodType] Finished");

	        return list;
	  }
	 
	 public List<DonatorImcAvgByAgeRangeDTO> findDonatorImcAvgByAgeRange()  {
	        RowMapper<DonatorImcAvgByAgeRangeDTO> rowMapper = new DonatorImcAvgByAgeRangeDTOMapper();
	        List<DonatorImcAvgByAgeRangeDTO> list = new ArrayList<DonatorImcAvgByAgeRangeDTO>();
	        Map<String, Object> paramMap = new HashMap<String, Object>();
	        
	        LOGGER.info("[findDonatorImcAvgByAgeRange] Generating the query");
	        list = namedParameterJdbcTemplate.query(SQL_IMC_AVG_BY_AGE_RANGE, paramMap, rowMapper);
	        LOGGER.info("[findDonatorImcAvgByAgeRange] Finished");

	        return list;
	  }
	 
	 public List<DonatorQuantityByBloodTypeDTO> findDonatorQuantityByBloodType()  {
	        RowMapper<DonatorQuantityByBloodTypeDTO> rowMapper = new DonatorByBloodTypeDTOMapper();
	        List<DonatorQuantityByBloodTypeDTO> list = new ArrayList<DonatorQuantityByBloodTypeDTO>();
	        Map<String, Object> paramMap = new HashMap<String, Object>();
	        
	        LOGGER.info("[findDonatorQuantityByBloodType] Generating the query");
	        list = namedParameterJdbcTemplate.query(SQL_DONATORS_BY_BLOOD_TYPE, paramMap, rowMapper);
	        LOGGER.info("[findDonatorQuantityByBloodType] Finished");

	        return list;
	  }
	  
	 public List<DonatorImcPercentageByGenderDTO> findDonatorImcPercentageByGender(BigDecimal imc)  {
	        RowMapper<DonatorImcPercentageByGenderDTO> rowMapper = new DonatorGenderPercentageByImcDTOMapper();
	        List<DonatorImcPercentageByGenderDTO> list = new ArrayList<DonatorImcPercentageByGenderDTO>();
	        Map<String, Object> paramMap = new HashMap<String, Object>();
	        paramMap.put("imcValue", imc);
	        
	        LOGGER.info("[findDonatorImcPercentageByGender] Generating the query");
	        list = namedParameterJdbcTemplate.query(SQL_IMC_PERCENT_BY_GENDER, paramMap, rowMapper);
	        LOGGER.info("[findDonatorImcPercentageByGender] Finished");

	        return list;
	  }

	 public List<DonatorForBloodTypeDTO> findDonatorsForEachBloodType (
			 BigDecimal minWeight, 
			 Integer minAge, 
			 Integer maxAge,
			 List<Integer> fromAp,
			 List<Integer> fromAn,
			 List<Integer> fromBp,
			 List<Integer> fromBn,
			 List<Integer> fromABp,
			 List<Integer> fromABn,
			 List<Integer> fromOp,
			 List<Integer> fromOn		 
			 )  {
	        RowMapper<DonatorForBloodTypeDTO> rowMapper = new DonatorFromByBloodTypeDTOMapper();
	        List<DonatorForBloodTypeDTO> list = new ArrayList<DonatorForBloodTypeDTO>();
	        Map<String, Object> paramMap = new HashMap<String, Object>();
	        paramMap.put("minWeight", minWeight);
	        paramMap.put("minAge", minAge);
	        paramMap.put("maxAge", maxAge);
	        paramMap.put("fromAp", fromAp);
	        paramMap.put("fromAn", fromAn);
	        paramMap.put("fromBp", fromBp);
	        paramMap.put("fromBn", fromBn);
	        paramMap.put("fromABp", fromABp);
	        paramMap.put("fromABn", fromABn);
	        paramMap.put("fromOp", fromOp);
	        paramMap.put("fromOn", fromOn);
	        
	        LOGGER.info("[findDonatorsForEachBloodType] Generating the query");
	        list = namedParameterJdbcTemplate.query(SQL_DONATORS_FOR_EACH_BLOOD_TYPE, paramMap, rowMapper);
	        LOGGER.info("[findDonatorsForEachBloodType] Finished");

	        return list;
	  }

}
