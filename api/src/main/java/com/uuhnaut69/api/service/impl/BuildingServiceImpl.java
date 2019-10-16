package com.uuhnaut69.api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.uuhnaut69.api.model.entity.Building;
import com.uuhnaut69.api.repository.BuildingRepository;
import com.uuhnaut69.api.service.BuildingService;

@Service
public class BuildingServiceImpl implements BuildingService {

	private final BuildingRepository buildingRepository;

	public BuildingServiceImpl(BuildingRepository buildingRepository) {
		this.buildingRepository = buildingRepository;
	}

	@Override
	public List<Building> findByCompanyId(String companyId) {
		return buildingRepository.findByCompanyId(companyId);
	}

	@Override
	public Page<Building> findByCompanyIdAndNameLikeOrderByName(String companyId, String name, Pageable pageable) {
		return buildingRepository.findByCompanyIdAndNameLikeOrderByName(companyId, name, pageable);
	}

	@Override
	public List<Building> findByPhoneNumber(String telephoneNumber) {
		return buildingRepository.findByPhoneNumber(telephoneNumber);
	}

	@Override
	public Long countBuildings(String companyId) {
		return buildingRepository.countBuildings(companyId);
	}

	@Override
	public Building findById(String id) throws Exception {
		Optional<Building> building = buildingRepository.findById(id);
		return building.orElseThrow(() -> new NotFoundException());
	}

	@Override
	public Building findByCompanyAndAreaId(String companyId, String areaId) throws Exception {
		Building building = findByCompanyAndAreaId(companyId, areaId);
		return building;
	}

	@Override
	public void add(Building building) {
		buildingRepository.getCouchbaseOperations().save(building);
	}

	@Override
	public void delete(String id) throws Exception {
		Building building = findById(id);
		buildingRepository.getCouchbaseOperations().remove(building);
	}

	@Override
	public Page<Building> findAll(Pageable pageable) {
		return buildingRepository.findAll(pageable);
	}

}
