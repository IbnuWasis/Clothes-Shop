package com.ibnu.project.services;

import java.util.List;

import com.ibnu.project.model.Barang;

public interface BarangService {
	Barang findById(Long id);
	Barang findByNamaBarang(String name);
	void saveBarang(Barang barang);
	void updateBarang(Barang barang);
	void deleteById(Long id);
	void deleteAllBarangs();
	List<Barang> findAllBarang();
	boolean isBarangExist(Barang barang);
}
