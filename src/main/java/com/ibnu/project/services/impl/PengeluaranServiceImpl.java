package com.ibnu.project.services.impl;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibnu.project.model.Pengeluaran;
import com.ibnu.project.repository.PengeluaranRepository;
import com.ibnu.project.services.PengeluaranService;

@Service("pengeluaranService")
@Transactional
public class PengeluaranServiceImpl implements PengeluaranService{
	@Autowired
	private PengeluaranRepository pRepository;
	
	public Pengeluaran findPengeluaranById(Long id) {
		return pRepository.findOne(id);
	}
	public List<Pengeluaran> findPengeluaranByTanggal(Date tanggal) {
		return pRepository.findByTanggal(tanggal);
	}
	public void savePengeluaran(Pengeluaran pengeluaran) {
		pRepository.save(pengeluaran);
	}
	public void updatePengeluaran(Pengeluaran pengeluaran) {
		savePengeluaran(pengeluaran);
	}
	public void deletePengeluaranById(Long id) {
		pRepository.delete(id);
	}
	public void deleteAllPengeluaran() {
		pRepository.deleteAll();
	}
	public List<Pengeluaran> findAllPengeluaran(){
		return pRepository.findAll();
	}
	public boolean isPengeluaranExist(Pengeluaran pengeluaran) {
		return pRepository.findOne(pengeluaran.getId()) != null;
	}

}
