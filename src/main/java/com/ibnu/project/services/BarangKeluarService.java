package com.ibnu.project.services;

import java.util.List;

import com.ibnu.project.model.BarangKeluar;

public interface BarangKeluarService {
	BarangKeluar findBarangKeluarById(Long id);
	void saveBarangKeluar(BarangKeluar barangKeluar);
	void updateBarangKeluar(BarangKeluar barangKeluar);
	void deleteBarangKeluarById(Long id);
	List<BarangKeluar> findAllBarangKeluar();
	boolean isBarangKeluarExist(BarangKeluar barangKeluar);
	List<BarangKeluar> findBarangKeluarByBarang(Long idBarang);
}
