package com.ibnu.project.services.impl;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibnu.project.model.GajiKaryawan;
import com.ibnu.project.repository.GajiKaryawanRepository;
import com.ibnu.project.services.GajiKaryawanService;

@Service("gajiKaryawanService")
@Transactional
public class GajiKaryawanServiceImpl implements GajiKaryawanService{
	
	@Autowired
	private GajiKaryawanRepository gRepository;
	
	public GajiKaryawan findGajiKarywanById(Long id) {
		return gRepository.findOne(id);
	}
	public GajiKaryawan findGajiKaryawanByTanggal(Date tanggal) {
		return gRepository.findGajiKaryawanByTanggal(tanggal);
	}
	public void saveGajiKaryawan(GajiKaryawan gajiKaryawan) {
		gRepository.save(gajiKaryawan);
	}
	public void updateGajiKaryawan(GajiKaryawan gajiKaryawan) {
		saveGajiKaryawan(gajiKaryawan);
	}
	public void deleteGajiKaryawanById(Long id) {
		gRepository.delete(id);
	}
	public List<GajiKaryawan> findAllGajiKaryawan(){
		return gRepository.findAll();
	}
	public boolean isGajiKaryawanExist(GajiKaryawan gajiKaryawan) {
		return gRepository.findOne(gajiKaryawan.getId()) != null;
	}

}
