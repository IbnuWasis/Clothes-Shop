package com.ibnu.project.services.impl;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibnu.project.model.StokBarang;
import com.ibnu.project.repository.StokBarangRepository;
import com.ibnu.project.services.StockBarangService;

@Service("stockBarangService")
@Transactional
public class StockBarangServiceImpl implements StockBarangService{
	
	@Autowired
	private StokBarangRepository stokBarangRepository;
	
	public StokBarang findById(Long id) {
		return stokBarangRepository.findOne(id);
	}
	public StokBarang findByTanggal(Date tanggal) {
		return stokBarangRepository.findByTanggal(tanggal);
	}
	public void saveStockBarang(StokBarang stokBarang) {
		stokBarangRepository.save(stokBarang);
	}
	public void updateStockBarang(StokBarang stokBarang) {
		saveStockBarang(stokBarang);
	}
	public void deleteStockBarangById(Long id) {
		stokBarangRepository.delete(id);
	}
	public void deleteAllStockBarang() {
		stokBarangRepository.deleteAll();
	}
	public List<StokBarang> findAllStockBarang(){
		return stokBarangRepository.findAll();
	}
	public boolean isStockBarangExist(StokBarang stokBarang) {
		return stokBarangRepository.findOne(stokBarang.getId()) != null;
	}

}
