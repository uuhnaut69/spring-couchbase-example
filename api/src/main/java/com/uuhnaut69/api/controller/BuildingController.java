package com.uuhnaut69.api.controller;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.uuhnaut69.api.model.entity.Building;
import com.uuhnaut69.api.service.BuildingService;

@RestController
@RequestMapping(path = "/buildings", produces = MediaType.APPLICATION_JSON_VALUE)
public class BuildingController {

	private final BuildingService buildingService;

	public BuildingController(BuildingService buildingService) {
		this.buildingService = buildingService;
	}

	@GetMapping
	public ResponseEntity<Page<Building>> findAll(@RequestParam(value = "sort", defaultValue = "id") String sortBy,
			@RequestParam(value = "order", defaultValue = "desc") String order,
			@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize) {
		Sort sort = "asc".equals(order.toLowerCase()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
		Page<Building> buildings = buildingService.findAll(PageRequest.of(page, pageSize, sort));
		return new ResponseEntity<Page<Building>>(buildings, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Building> findById(@PathVariable String id) throws Exception {
		Building building = buildingService.findById(id);
		return new ResponseEntity<Building>(building, HttpStatus.OK);
	}

	@PostMapping(path = "/add", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> add(@RequestBody Building building) {
		buildingService.add(building);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PutMapping(path = "/edit", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> edit(@RequestBody @Valid Building building) {
		buildingService.add(building);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<Object> delete(@PathVariable String id) throws Exception {
		buildingService.delete(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
