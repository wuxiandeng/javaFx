package com.spartajet.fxboot.demo.service;

import java.util.List;
import java.util.Map;

public interface IServiceService {
	public List<Map<String,String>> queryList(Map<String,String> map);
	public void insertPerson(Map<String,String> map);
	public void updatePerson(Map<String,String> map);
	public void deletePerson(Map<String,String> map);
}
