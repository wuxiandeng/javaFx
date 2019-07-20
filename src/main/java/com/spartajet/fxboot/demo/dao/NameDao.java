package com.spartajet.fxboot.demo.dao;

import java.util.List;
import java.util.Map;

public interface NameDao {
	public List<Map<String,String>> queryList(Map<String,String> map);
	public void insertPerson(Map<String,String> map);
	public void updatePerson(Map<String,String> map);
	public void deletePerson(Map<String,String> map);
}
