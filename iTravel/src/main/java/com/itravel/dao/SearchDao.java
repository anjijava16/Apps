package com.itravel.dao;

import java.util.List;

import com.itravel.dto.Search;

public interface SearchDao {
	
	public List<Search> getBuses(Integer searchId);
}
