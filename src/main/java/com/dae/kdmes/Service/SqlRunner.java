package com.dae.kdmes.Service;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;

import java.util.List;
import java.util.Map;

public interface SqlRunner {

	public List<Map<String, Object>> getRows(String sql, MapSqlParameterSource mapParam);

	public Map<String, Object> getRow(String sql, MapSqlParameterSource mapParam);
	
	public int execute(String sql, MapSqlParameterSource mapParam);
	
	public int queryForCount(String sql,  MapSqlParameterSource mapParam);
	
	public <T> T queryForObject(String sql,  MapSqlParameterSource mapParam, RowMapper<T> mapper);
}
