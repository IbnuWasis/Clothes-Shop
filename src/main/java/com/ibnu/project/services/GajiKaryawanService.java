package com.ibnu.project.services;

import java.sql.Date;
import java.util.List;

import com.ibnu.project.model.GajiKaryawan;

public interface GajiKaryawanService {
	GajiKaryawan findGajiKarywanById(Long id);
	GajiKaryawan findGajiKaryawanByTanggal(Date tanggal);
	void saveGajiKaryawan(GajiKaryawan gajiKaryawan);
	void updateGajiKaryawan(GajiKaryawan gajiKaryawan);
	void deleteGajiKaryawanById(Long id);
	List<GajiKaryawan> findAllGajiKaryawan();
	boolean isGajiKaryawanExist(GajiKaryawan gajiKaryawan);
}
