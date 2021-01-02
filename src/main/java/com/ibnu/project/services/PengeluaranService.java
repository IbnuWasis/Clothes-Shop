package com.ibnu.project.services;

import java.sql.Date;
import java.util.List;

import com.ibnu.project.model.Pengeluaran;

public interface PengeluaranService {
	Pengeluaran findPengeluaranById(Long id);
	List<Pengeluaran> findPengeluaranByTanggal(Date tanggal);
	void savePengeluaran(Pengeluaran pengeluaran);
	void updatePengeluaran(Pengeluaran pengeluaran);
	void deletePengeluaranById(Long id);
	void deleteAllPengeluaran();
	List<Pengeluaran> findAllPengeluaran();
	boolean isPengeluaranExist(Pengeluaran pengeluaran);
}
