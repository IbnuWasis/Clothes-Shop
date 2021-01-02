package com.ibnu.project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import com.ibnu.project.config.BaseController;
import com.ibnu.project.model.Barang;
import com.ibnu.project.services.BarangService;

import lombok.extern.slf4j.Slf4j;

@BaseController
@Slf4j
public class BarangController {
	@Autowired
	BarangService barangService;
	
	@RequestMapping(value = "/barang/getList/",method = RequestMethod.GET )
	public ResponseEntity<List<Barang>> listAllBarang(){
		List<Barang> allBarang = barangService.findAllBarang();
		if(allBarang.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Barang>>(allBarang, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/barang/getBarangById/", method = RequestMethod.GET)
	public ResponseEntity<Barang> getBarangById(@PathVariable("id") Long id){
		log.info("Fetching Barang with id {}", id);
		Barang barang = barangService.findById(id);
		if (barang == null) {
			log.error("Barang with id {} not found", id);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Barang>(barang, HttpStatus.OK);
	}
	
	@RequestMapping(value="/barang/createBarang/", method = RequestMethod.POST)
	public ResponseEntity<Barang> createBarang(@RequestBody Barang barang, UriComponentsBuilder builder){
		log.info("Creating Barang : {}", barang);
		if (barangService.isBarangExist(barang)) {
			log.error("Unable to Create. Barang with name {} already exist", barang.getNamaBarang());
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		barangService.saveBarang(barang);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/barang/getBarangById/{id}").buildAndExpand(barang.getId()).toUri());
		return new ResponseEntity<Barang>(headers,HttpStatus.CREATED);
	}
	
	@RequestMapping(value="/barang/updateBarang/", method = RequestMethod.PUT)
	public ResponseEntity<Barang> updateBarang(@PathVariable("id") Long id, @RequestBody Barang barang){
		log.info("Updating Barang with id {}", id);
		Barang currentBarang = barangService.findById(id);
		if(currentBarang == null) {
			log.error("Unable to update. Barang with id {} not found", id);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		currentBarang.setNamaBarang(barang.getNamaBarang());
		currentBarang.setKodeBarang(barang.getKodeBarang());
		currentBarang.setIdType(barang.getIdType());
		barangService.updateBarang(currentBarang);
		
		return new ResponseEntity<Barang>(currentBarang,HttpStatus.OK);
	}
	
	@RequestMapping(value ="/barang/deleteBarang", method = RequestMethod.DELETE)
	public ResponseEntity<Barang> deleteBarang(@PathVariable("id")Long id){
		log.info("Fetching & deleting Barang with id {}", id);
		Barang barang = barangService.findById(id);
		if(barang == null) {
			log.error("Unble to delete. Barang with id {} not found", id);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		barangService.deleteById(barang.getId());
		return new ResponseEntity<Barang>(HttpStatus.NO_CONTENT);
	}
}
