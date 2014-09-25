package com.itravel.service;

import java.util.List;

import com.itravel.dto.Search;

public interface SearchService {
	public List<Search> getBuses(Integer searchId);
}
