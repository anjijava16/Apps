package com.itravel.service;

import java.util.List;

import com.itravel.dao.SearchDao;
import com.itravel.dao.SearchDaoImpl;
import com.itravel.dto.Search;

public class SearchServiceImpl implements SearchService {
	
	
	private SearchDao searchDao = new SearchDaoImpl();
	
	@Override
	public List<Search> getBuses(Integer searchId) {
		return searchDao.getBuses(searchId);
	}

	public SearchDao getSearchDao() {
		return searchDao;
	}

	public void setSearchDao(SearchDao searchDao) {
		this.searchDao = searchDao;
	}
	

}
