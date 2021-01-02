package com.ibnu.project.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibnu.project.model.Barang;
import com.ibnu.project.repository.BarangRepository;
import com.ibnu.project.services.BarangService;

@Service("barangService")
@Transactional
public class BarangServiceImpl implements BarangService{
	
	@Autowired
	private BarangRepository barangRepository;
	
	public Barang findById(Long id) {
		return barangRepository.findOne(id);
	}
	public Barang findByNamaBarang(String name) {
		return barangRepository.findByNamaBarang(name);
	}
	public void saveBarang(Barang barang) {
		barangRepository.save(barang);
	}
	public void updateBarang(Barang barang) {
		saveBarang(barang);
	}
	public void deleteById(Long id) {
		barangRepository.delete(id);
	}
	public void deleteAllBarangs() {
		barangRepository.deleteAll();
	}
	public List<Barang> findAllBarang(){
		return barangRepository.findAll();
	}
	public boolean isBarangExist(Barang barang) {
		return barangRepository.findByNamaBarang(barang.getNamaBarang()) != null;
	}
}
