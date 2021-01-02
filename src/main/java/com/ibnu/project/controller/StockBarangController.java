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
import com.ibnu.project.model.StokBarang;
import com.ibnu.project.services.StockBarangService;

import lombok.extern.slf4j.Slf4j;

@BaseController
@Slf4j
@ResponseBody
public class StockBarangController {
	@Autowired
	StockBarangService stockBarangService;
	
	@RequestMapping(value = "/stockbarang/getList/", method = RequestMethod.GET)
	public ResponseEntity<List<StokBarang>> listAllStockBarang(){
		List<StokBarang> allStockBarang = stockBarangService.findAllStockBarang();
		if(allStockBarang.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<StokBarang>>(allStockBarang, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/stockbarang/getStockBarangById/", method = RequestMethod.GET)
	public ResponseEntity<StokBarang> getStockBarangById(@PathVariable("id") Long id){
		log.info("Fetching Stock Barang with id {}", id);
		StokBarang stokBarang = stockBarangService.findById(id);
		if(stokBarang == null) {
			log.error("Stock Barang with id {} not found.", id);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<StokBarang>(stokBarang, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/stockbarang/createStockBarang/", method = RequestMethod.POST)
	public ResponseEntity<StokBarang> createStockBarang(@RequestBody StokBarang stokBarang, UriComponentsBuilder builder){
		log.info("Creating Stock Barang : {}", stokBarang);
		if(stockBarangService.isStockBarangExist(stokBarang)) {
			log.error("Unable to create. A Stock Barang with id {} already exist ", stokBarang.getIdBarang());
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		stockBarangService.saveStockBarang(stokBarang);
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(builder.path("/stockbarang/getStockBarangById/{id}").buildAndExpand(stokBarang.getId()).toUri());
		return new ResponseEntity<StokBarang>(headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/stockbarang/updateStockBarang/", method = RequestMethod.PUT)
	public ResponseEntity<StokBarang> updateStockBarang(@PathVariable("id") Long id, @RequestBody StokBarang stokBarang){
		log.info("Updating Stock Barang with id {}", id);
		StokBarang currentStockBarang = stockBarangService.findById(id);
		if (currentStockBarang == null) {
			log.error("Unable to update. Stock Barang with id {} not found.", id);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		currentStockBarang.setIdBarang(stokBarang.getIdBarang());
		currentStockBarang.setTanggal(stokBarang.getTanggal());
		currentStockBarang.setHargaBeliPerBarang(stokBarang.getHargaBeliPerBarang());
		currentStockBarang.setHargaGrosir(stokBarang.getHargaGrosir());
		currentStockBarang.setTanggalUpdate(stokBarang.getTanggalUpdate());
		currentStockBarang.setTotalBarang(stokBarang.getTotalBarang());
		stockBarangService.updateStockBarang(currentStockBarang);
		
		return new ResponseEntity<StokBarang>(currentStockBarang, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/stockbarang/deleteStockBarang/", method = RequestMethod.DELETE)
	public ResponseEntity<StokBarang> deleteStockBarang(@PathVariable("id") Long id){
		log.info("Fetching & Deleting Stock Barang with id {}", id);
		
		StokBarang stokBarang = stockBarangService.findById(id);
		if (stokBarang == null) {
			log.error("Unable to delete. Stock Barang with id {} not found.", id);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		stockBarangService.deleteStockBarangById(stokBarang.getId());
		return new ResponseEntity<StokBarang>(HttpStatus.NO_CONTENT);
	}

}
