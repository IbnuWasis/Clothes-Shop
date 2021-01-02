package com.ibnu.project.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibnu.project.model.BarangKeluar;
import com.ibnu.project.repository.BarangKeluarRepository;
import com.ibnu.project.services.BarangKeluarService;

@Service("barangKeluarService")
@Transactional
public class BarangKeluarServiceImpl implements BarangKeluarService{
	@Autowired
	private BarangKeluarRepository bKeluarRepository;
	
	public BarangKeluar findBarangKeluarById(Long id) {
		return bKeluarRepository.findOne(id);
	}
	public void saveBarangKeluar(BarangKeluar barangKeluar) {
		bKeluarRepository.save(barangKeluar);
	}
	public void updateBarangKeluar(BarangKeluar barangKeluar) {
		saveBarangKeluar(barangKeluar);
	}
	public void deleteBarangKeluarById(Long id) {
		bKeluarRepository.delete(id);
	}
	public List<BarangKeluar> findAllBarangKeluar(){
		return bKeluarRepository.findAll();
	}
	public boolean isBarangKeluarExist(BarangKeluar barangKeluar) {
		return bKeluarRepository.findOne(barangKeluar.getId()) != null;
	}
	public List<BarangKeluar> findBarangKeluarByBarang(Long idBarang){
		return bKeluarRepository.findByIdBarang(idBarang);
	}

}
