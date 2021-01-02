package com.ibnu.project.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.ibnu.project.model.GajiKaryawan;
import com.ibnu.project.services.GajiKaryawanService;

import lombok.extern.slf4j.Slf4j;

@BaseController
@ResponseBody
@Slf4j
public class GajiKaryawanController {
	@Autowired
	GajiKaryawanService gajiKaryawanService;
	
	@GetMapping("/gajikaryawan/getList/")
	public ResponseEntity<List<GajiKaryawan>> listAllGajiKaryawan(){
		List<GajiKaryawan> allGaji = gajiKaryawanService.findAllGajiKaryawan();
		if(allGaji.isEmpty()) {
			log.error("Gaji Karywan not found");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<GajiKaryawan>>(allGaji,HttpStatus.OK);
	}
	
	
	@PostMapping("/gajikaryawan/createGajiKaryawan/")
	public ResponseEntity<GajiKaryawan> createGajiKaryawan(@RequestBody GajiKaryawan gajiKaryawan, UriComponentsBuilder builder){
		log.info("Creating Gaji Karyawan :{}", gajiKaryawan);
		if(gajiKaryawanService.isGajiKaryawanExist(gajiKaryawan)) {
			log.error("Gaji Karyawan already exist");
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		gajiKaryawanService.saveGajiKaryawan(gajiKaryawan);
		return new ResponseEntity<GajiKaryawan>(HttpStatus.CREATED);
	}
	
	@PutMapping("/gajikaryawan/updateGajiKaryawan")
	public ResponseEntity<GajiKaryawan> updateGajiKaryawan(@PathVariable("id") Long id, @RequestBody GajiKaryawan gajiKaryawan){
		log.info("Updating Gaji Karyawan with id {}", id);
		GajiKaryawan currentGaji = gajiKaryawanService.findGajiKarywanById(id);
		if(currentGaji == null) {
			log.error("Unable to update. Gaji Karyawan with id {} not found", id);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		currentGaji.setGaji(gajiKaryawan.getGaji());
		currentGaji.setTanggal(gajiKaryawan.getTanggal());
		gajiKaryawanService.updateGajiKaryawan(currentGaji);
		
		return new ResponseEntity<GajiKaryawan>(currentGaji,HttpStatus.OK);
	}
	
	@DeleteMapping("/gajikaryawan/deleteGajiKaryawan/")
	public ResponseEntity<GajiKaryawan> deleteGajiKaryawan(@PathVariable("id") Long id){
		log.info("Deleting Gaji Karyawan with id {}", id);
		GajiKaryawan gajiKaryawan = gajiKaryawanService.findGajiKarywanById(id);
		if(gajiKaryawan == null) {
			log.error("Unable to delete. Gaji Karyawan with id {} not found",id);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		gajiKaryawanService.deleteGajiKaryawanById(gajiKaryawan.getId());
		return new ResponseEntity<GajiKaryawan>(HttpStatus.NO_CONTENT);
	}
}
