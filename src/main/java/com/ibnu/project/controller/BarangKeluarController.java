package com.ibnu.project.controller;

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
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ibnu.project.config.BaseController;
import com.ibnu.project.model.BarangKeluar;
import com.ibnu.project.services.BarangKeluarService;

import lombok.extern.slf4j.Slf4j;

@BaseController
@Slf4j
public class BarangKeluarController {
	
	@Autowired
	BarangKeluarService barangKeluarService;
	
	@GetMapping("/barangkeluar/getList/")
	public ResponseEntity<List<BarangKeluar>> listAllBarangKeluar(){
		List<BarangKeluar> allBarangKeluar = barangKeluarService.findAllBarangKeluar();
		if (allBarangKeluar.isEmpty()) {
			log.error("Barang Keluar not found");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<BarangKeluar>>(allBarangKeluar,HttpStatus.OK);
	}
	
	@GetMapping("/barangkeluar/getBarangKeluarByBarang/")
	public ResponseEntity<List<BarangKeluar>> getBarangKeluarById(@PathVariable("id") Long id){
		log.info("Fetching Barang Keluar with id {}", id);
		List<BarangKeluar> barangKeluar = barangKeluarService.findBarangKeluarByBarang(id);
		if(barangKeluar.isEmpty()) {
			log.error("Barang Keluar not found with idBarang {}", id);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<BarangKeluar>>(barangKeluar, HttpStatus.OK);
	}
	
	@PostMapping("/barangkeluar/createBarangKeluar/")
	public ResponseEntity<BarangKeluar> createBarangKeluar(@RequestBody BarangKeluar barangKeluar, UriComponentsBuilder builder){
		log.info("Create Barang Keluar: {}", barangKeluar);
		if(barangKeluarService.isBarangKeluarExist(barangKeluar)) {
			log.error("Unable to create. Barang Keluar with id Barang {} is already exist", barangKeluar.getIdBarang());
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		barangKeluarService.saveBarangKeluar(barangKeluar);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/barang/getBarangKeluarByBarang/{id}").buildAndExpand(barangKeluar.getIdBarang()).toUri());
		return new ResponseEntity<BarangKeluar>(headers,HttpStatus.CREATED);
	}
	
	@PutMapping("/barangkeluar/updateBarangKeluar/")
	public ResponseEntity<BarangKeluar> updateBarangKeluar(@PathVariable("id") Long id, @RequestBody BarangKeluar barangKeluar){
		log.info("Updating Barang Keluar with id {}", id);
		BarangKeluar currentBarangKeluar = barangKeluarService.findBarangKeluarById(id);
		if(currentBarangKeluar == null) {
			log.error("Unable to update. Barang Keluar with id {} not found", id);
			return new ResponseEntity<> (HttpStatus.NOT_FOUND);
		}
		currentBarangKeluar.setIdBarang(barangKeluar.getIdBarang());
		currentBarangKeluar.setTanggal(barangKeluar.getTanggal());
		currentBarangKeluar.setHargaBeli(barangKeluar.getHargaBeli());
		currentBarangKeluar.setHargaJual(barangKeluar.getHargaJual());
		currentBarangKeluar.setTotalBeli(barangKeluar.getTotalBeli());
		currentBarangKeluar.setTotalJual(barangKeluar.getTotalJual());
		currentBarangKeluar.setTotalBarang(barangKeluar.getTotalBarang());
		
		barangKeluarService.updateBarangKeluar(currentBarangKeluar);
		return new ResponseEntity<BarangKeluar>(currentBarangKeluar, HttpStatus.OK);
	}
	
	@DeleteMapping("/barangkeluar/deleteBarangKeluar/")
	public ResponseEntity<BarangKeluar> deleteBarangKeluar(@PathVariable("id") Long id){
		log.info("Fatching & deleting Barang Keluar with id {}", id);
		BarangKeluar barangKeluar = barangKeluarService.findBarangKeluarById(id);
		if(barangKeluar == null) {
			log.error("Unable to delete. Barang Keluar with id {} not found", id);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		barangKeluarService.deleteBarangKeluarById(barangKeluar.getId());
		return new ResponseEntity<BarangKeluar>(HttpStatus.NO_CONTENT);
	}
}
