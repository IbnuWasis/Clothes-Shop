package com.ibnu.project.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ibnu.project.config.BaseController;
import com.ibnu.project.model.TypeBarang;
import com.ibnu.project.services.TypeBarangService;

import lombok.extern.slf4j.Slf4j;

@BaseController
@Slf4j
@ResponseBody
public class TypeBarangController {
	@Autowired
	TypeBarangService tBarangService;
	
	@RequestMapping(value = "/typebarang/getList/", method = RequestMethod.GET)
	public ResponseEntity<List<TypeBarang>> listAllTypeBarang(){
		List<TypeBarang> allTypeBarang = tBarangService.findAllTypeBarang();
		if(allTypeBarang.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<TypeBarang>>(allTypeBarang,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/typebarang/getTypeBarangById/", method = RequestMethod.GET)
	public ResponseEntity<TypeBarang> getTypeBarangById(@PathVariable("id") Long id){
		log.info("Fetching Type Barang with {}",id);
		TypeBarang typeBarang = tBarangService.findTypeBarangById(id);
		if(typeBarang == null) {
			log.error("Type Barang with id {} not found", id);
			return new ResponseEntity<TypeBarang>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<TypeBarang>(typeBarang, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/typebarang/createTypeBarang/", method = RequestMethod.POST)
	public ResponseEntity<TypeBarang> createTypeBarang(@RequestBody TypeBarang typeBarang,UriComponentsBuilder builder){
		log.info("Creating Type Barang : {}", typeBarang);
		if(tBarangService.isTypeBarangExist(typeBarang)) {
			log.error("Unable to create. A type Barang with name {} already exist ", typeBarang.getNamaType());
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		tBarangService.saveTypeBarang(typeBarang);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("typebarang/getTypeBarangById/{id}").buildAndExpand(typeBarang.getId()).toUri());
		return new ResponseEntity<TypeBarang>(headers,HttpStatus.CREATED);
		
	}
	
	@RequestMapping(value ="/typebarang/updateTypeBarang/", method = RequestMethod.PUT)
	public ResponseEntity<TypeBarang> updateTypeBarang(@PathVariable("id") Long id, @RequestBody TypeBarang typeBarang){
		log.info("Updating Type Barang with id {}", id);
		TypeBarang currentTypeBarang = tBarangService.findTypeBarangById(id);
		if(currentTypeBarang == null) {
			log.error("Unable to update Type Barang with id {} not found", id);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		currentTypeBarang.setNamaType(typeBarang.getNamaType());
		tBarangService.updateTypeBarang(currentTypeBarang);
		
		return new ResponseEntity<TypeBarang>(currentTypeBarang,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/typebarang/deleteTypeBarang/", method = RequestMethod.DELETE)
	public ResponseEntity<TypeBarang> deleteTypeBarang(@PathVariable("id") Long id){
		log.info("Fetcing & delete Type Barang with id {}", id);
		TypeBarang typeBarang = tBarangService.findTypeBarangById(id);
		if(typeBarang == null) {
			log.error("Unable to delete. Type Barang with id {} not found", id);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		tBarangService.deleteTypeBarangById(typeBarang.getId());
		return new ResponseEntity<TypeBarang>(HttpStatus.NO_CONTENT);
		
	}

}
