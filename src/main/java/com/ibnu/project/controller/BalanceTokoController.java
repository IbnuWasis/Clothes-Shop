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
import com.ibnu.project.model.BalanceToko;
import com.ibnu.project.services.BalanceTokoService;

import lombok.extern.slf4j.Slf4j;

@BaseController
@Slf4j
@ResponseBody
public class BalanceTokoController {
	@Autowired
	BalanceTokoService balanceTokoService;
	
	@GetMapping("/balancetoko/getList/")
	public ResponseEntity<List<BalanceToko>> listAllBalanceToko(){
		List<BalanceToko> allBalance = balanceTokoService.findAllBalanceToko();
		if(allBalance.isEmpty()) {
			log.error("Balance Toko not found");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<BalanceToko>>(allBalance,HttpStatus.OK);
	}
	
	@PostMapping("/balancetoko/createBalance/")
	public ResponseEntity<BalanceToko> createBalanceToko(@RequestBody BalanceToko balanceToko){
		log.info("Creating Balance Toko :{}", balanceToko);
		if(balanceTokoService.BalanceTokoIsExist(balanceToko)) {
			log.error("Unable to create. Balance already exist");
			return new ResponseEntity<>(HttpStatus.CONFLICT);
		}
		balanceTokoService.saveBalanceToko(balanceToko);
		return new ResponseEntity<BalanceToko>(HttpStatus.CREATED);
	}
	
	@PutMapping("/balancetoko/updateBalance/")
	public ResponseEntity<BalanceToko> updateBalanceToko(@PathVariable("id") Long id,@RequestBody BalanceToko balanceToko){
		log.info("Updating Balance");
		BalanceToko currentBalance = balanceTokoService.findByIdBalance(id);
		if(currentBalance == null) {
			log.error("Balance not found");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		currentBalance.setTanggal(balanceToko.getTanggal());
		currentBalance.setJumlahPemasukan(balanceToko.getJumlahPemasukan());
		currentBalance.setJumlahPengeluaran(balanceToko.getJumlahPengeluaran());
		currentBalance.setBalanceSekarang(balanceToko.getBalanceSekarang());
		balanceTokoService.updateBalanceToko(currentBalance);
		return new ResponseEntity<BalanceToko>(currentBalance,HttpStatus.OK);
	}
	
	@DeleteMapping("/balancetoko/deleteBalance/")
	public ResponseEntity<BalanceToko> deleteBalanceToko(@PathVariable("id") Long id){
		log.info("Deleting Balance Toko");
		BalanceToko balanceToko = balanceTokoService.findByIdBalance(id);
		if(balanceToko == null) {
			log.error("Unable to delete");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		balanceTokoService.deleteBalanceTokoById(balanceToko.getId());
		return new ResponseEntity<BalanceToko>(HttpStatus.NO_CONTENT);
	}
}
