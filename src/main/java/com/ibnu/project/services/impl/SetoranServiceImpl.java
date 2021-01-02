package com.ibnu.project.services.impl;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibnu.project.model.Setoran;
import com.ibnu.project.repository.SetoranRepository;
import com.ibnu.project.services.SetoranService;

@Service("setoranService")
@Transactional
public class SetoranServiceImpl implements SetoranService {
	
	@Autowired
	private SetoranRepository sRepository;
	
	public Setoran findSetoranById(Long id) {
		return sRepository.findOne(id);
	}
	public void saveSetoran(Setoran setoran) {
		sRepository.save(setoran);
	}
	public void updateSetoran(Setoran setoran) {
		saveSetoran(setoran);
	}
	public void deleteSetoranById(Long id) {
		sRepository.delete(id);
	}
	public List<Setoran> findAllSetoran(){
		return sRepository.findAll();
	}
	public boolean isSetoranExist(Setoran setoran) {
		return sRepository.findOne(setoran.getId()) != null;
	}
	public Setoran findSetoranByTanggal(Date tanggal){
		return sRepository.findSetoranByTanggal(tanggal);
	}

}
