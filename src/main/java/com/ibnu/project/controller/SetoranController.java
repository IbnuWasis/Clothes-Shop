package com.ibnu.project.controller;

import java.sql.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ibnu.project.config.BaseController;
import com.ibnu.project.model.Setoran;
import com.ibnu.project.services.SetoranService;

import lombok.extern.slf4j.Slf4j;

@BaseController
@ResponseBody
@Slf4j
public class SetoranController {
	@Autowired
	SetoranService setoranService;
	
	@GetMapping("/setoran/getList/")
	public ResponseEntity<List<Setoran>> listAllSetoran(){
		List<Setoran> allSetoran = setoranService.findAllSetoran();
		if(allSetoran.isEmpty()) {
			log.error("Setoran not found");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Setoran>>(allSetoran,HttpStatus.OK);
	}
	
	@GetMapping("/setoran/getSetoranByTanggal/")
	public ResponseEntity<Setoran> getSetoranByTanggal(@PathVariable("tanggal") Date tanggal){
		Setoran allSetoran = setoranService.findSetoranByTanggal(tanggal);
		if(allSetoran == null) {
			log.error("Setoran with date {} not found", tanggal);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Setoran>(allSetoran,HttpStatus.OK);
	}
	
	@PostMapping("/setoran/createSetoran/")
	public ResponseEntity<Setoran> createSetoran(@RequestBody Setoran setoran,UriComponentsBuilder builder){
		log.info("Creating Setoran : {}", setoran);
		if(setoranService.isSetoranExist(setoran)) {
			log.error("Unable to create. Setoran with date {} already exist",setoran.getTanggal());
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		setoranService.saveSetoran(setoran);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/setoran/getSetoranByTanggal/{tanggal}").buildAndExpand(setoran.getTanggal()).toUri());
		return new ResponseEntity<Setoran>(headers,HttpStatus.CREATED);
	}
	
	@PutMapping("/setoran/updateSetoran/")
	public ResponseEntity<Setoran> updateSetoran(@PathVariable("id") Long id,@RequestBody Setoran setoran){
		log.info("Updating Setoran with date {}", id);
		Setoran currentSetoran = setoranService.findSetoranById(id);
		if(currentSetoran == null) {
			log.error("Unable to update Setoran with date {} not found", id);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		currentSetoran.setJumlahSetoran(setoran.getJumlahSetoran());
		setoranService.updateSetoran(currentSetoran);
		return new ResponseEntity<Setoran>(currentSetoran,HttpStatus.OK);
	}
	
	@DeleteMapping("/setoran/deleteSetoran")
	public ResponseEntity<Setoran> deleteSetoran(@PathVariable("id") Long id){
		log.info("Fetching & deleting Setoran");
		Setoran setoran = setoranService.findSetoranById(id);
		if(setoran == null) {
			log.error("Unable to delete. Setoran with id {} not found", id);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		setoranService.deleteSetoranById(setoran.getId());
		return new ResponseEntity<Setoran>(HttpStatus.NO_CONTENT);
	}

}
