package com.ibnu.project.services;


import java.sql.Date;
import java.util.List;

import com.ibnu.project.model.StokBarang;

public interface StockBarangService {
	StokBarang findById(Long id);
	StokBarang findByTanggal(Date tanggal);
	void saveStockBarang(StokBarang stokBarang);
	void updateStockBarang(StokBarang stokBarang);
	void deleteStockBarangById(Long id);
	void deleteAllStockBarang();
	List<StokBarang> findAllStockBarang();
	boolean isStockBarangExist(StokBarang stokBarang);
}
