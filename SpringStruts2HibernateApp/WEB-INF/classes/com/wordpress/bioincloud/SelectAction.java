package com.wordpress.bioincloud;
import java.util.ArrayList;
import java.util.List;

import com.opensymphony.xwork2.ActionSupport;

public class SelectAction extends ActionSupport{

	private List<String> searchEngine;

	private String yourSearchEngine;
	private String yourMonth;
	
	public String getYourMonth() {
		return yourMonth;
	}

	public void setYourMonth(String yourMonth) {
		this.yourMonth = yourMonth;
	}

	public List<String> getSearchEngine() {
		return searchEngine;
	}

	public void setSearchEngine(List<String> searchEngine) {
		this.searchEngine = searchEngine;
	}

	public String getYourSearchEngine() {
		return yourSearchEngine;
	}

	public void setYourSearchEngine(String yourSearchEngine) {
		this.yourSearchEngine = yourSearchEngine;
	}

	public String getDefaultSearchEngine() {
		return "yahoo.com";
	}
	
	public SelectAction(){
		
		searchEngine = new ArrayList<String>();
		searchEngine.add("google.com");
		searchEngine.add("bing.com");
		searchEngine.add("yahoo.com");
		searchEngine.add("baidu.com");
	}

	public String execute() {
		return SUCCESS;
	}
	
	public String display() {
		return NONE;
	}
	
}