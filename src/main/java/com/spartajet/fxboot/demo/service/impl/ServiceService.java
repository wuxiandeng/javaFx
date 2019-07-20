package com.spartajet.fxboot.demo.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spartajet.fxboot.demo.dao.NameDao;
import com.spartajet.fxboot.demo.service.IServiceService;

@Service
public class ServiceService implements IServiceService {
	@Autowired
	private NameDao nameDao;

	@Override
	public List<Map<String, String>> queryList(Map<String, String> map) {
		return nameDao.queryList(map);
	}

	@Override
	public void insertPerson(Map<String, String> map) {
		nameDao.insertPerson(map);
	}

	@Override
	public void updatePerson(Map<String, String> map) {
		nameDao.updatePerson(map);
	}

	@Override
	public void deletePerson(Map<String, String> map) {
		nameDao.deletePerson(map);
	}
}
