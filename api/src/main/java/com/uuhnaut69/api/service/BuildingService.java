package com.uuhnaut69.api.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.uuhnaut69.api.model.entity.Building;

public interface BuildingService {

	List<Building> findByCompanyId(String companyId);

	Page<Building> findByCompanyIdAndNameLikeOrderByName(String companyId, String name, Pageable pageable);

	List<Building> findByPhoneNumber(String telephoneNumber);

	Long countBuildings(String companyId);

	Building findById(String id) throws Exception;

	Building findByCompanyAndAreaId(String companyId, String areaId) throws Exception;

	void add(Building building);

	void delete(String id) throws Exception;

	Page<Building> findAll(Pageable pageable);
}
