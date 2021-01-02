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
import com.ibnu.project.model.Pengeluaran;
import com.ibnu.project.services.PengeluaranService;

import lombok.extern.slf4j.Slf4j;

@BaseController
@ResponseBody
@Slf4j
public class PengeluaranController {
	@Autowired
	PengeluaranService pengeluaranService;
	
	@GetMapping("/pengeluaran/getList")
	public ResponseEntity<List<Pengeluaran>> listAllPengeluaran(){
		List<Pengeluaran> allPengeluaran = pengeluaranService.findAllPengeluaran();
		if(allPengeluaran.isEmpty()) {
			log.error("pengeluaran not found");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Pengeluaran>>(allPengeluaran,HttpStatus.OK);
	}
	
	@GetMapping("/pengeluaran/getPengeluaranByTanggal/")
	public ResponseEntity<List<Pengeluaran>> getPengeluaranByTanggal(@PathVariable("tanggal") Date tanggal){
		List<Pengeluaran> allPengeluran = pengeluaranService.findPengeluaranByTanggal(tanggal);
		if(allPengeluran.isEmpty()) {
			log.error("pengeluaran with date {} not found",tanggal);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<Pengeluaran>>(allPengeluran,HttpStatus.OK);
	}
	
	@PostMapping("/pengeluaran/createPengeluaran/")
	public ResponseEntity<Pengeluaran> createPengeluaran(@RequestBody Pengeluaran pengeluaran, UriComponentsBuilder builder){
		log.info("Creating Pengeluaran : {}", pengeluaran);
		if(pengeluaranService.isPengeluaranExist(pengeluaran)) {
			log.error("Unable to create. Pengeluaran with name {} already exist",pengeluaran.getNamaPengeluaran());
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		pengeluaranService.savePengeluaran(pengeluaran);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/pengeluaran/getPengeluaranByTanggal/{tanggal}").buildAndExpand(pengeluaran.getTanggal()).toUri());
		return new ResponseEntity<Pengeluaran>(headers,HttpStatus.CREATED);
	}
	
	@PutMapping("/pengeluaran/updatePengeluaran/")
	public ResponseEntity<Pengeluaran> updatePengeluaran(@PathVariable("id") Long id,@RequestBody Pengeluaran pengeluaran){
		log.info("Updating pengeluaran with id {}", id);
		Pengeluaran currentPengeluaran = pengeluaranService.findPengeluaranById(id);
		if(currentPengeluaran == null) {
			log.error("Unable to Update. Pengeluaran with id {} not found", id);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		currentPengeluaran.setNamaPengeluaran(pengeluaran.getNamaPengeluaran());
		currentPengeluaran.setTanggal(pengeluaran.getTanggal());
		currentPengeluaran.setJumlahPengeluaran(pengeluaran.getJumlahPengeluaran());
		
		return new ResponseEntity<Pengeluaran>(currentPengeluaran,HttpStatus.OK);
	}
	
	@DeleteMapping("/pengeluaran/deletePengeluaran")
	public ResponseEntity<Pengeluaran> deletePengeluaran(@PathVariable("id") Long id){
		log.info("Fetching & deleting Pengeluaran with id {}", id);
		Pengeluaran pengeluaran = pengeluaranService.findPengeluaranById(id);
		if(pengeluaran == null) {
			log.error("Unable to delete. Pengeluaran with id {} not found", id);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		pengeluaranService.deletePengeluaranById(pengeluaran.getId());
		return new ResponseEntity<Pengeluaran>(HttpStatus.NO_CONTENT);
	}

}
