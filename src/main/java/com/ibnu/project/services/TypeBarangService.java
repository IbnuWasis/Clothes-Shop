package com.ibnu.project.services;

import java.util.List;

import com.ibnu.project.model.TypeBarang;

public interface TypeBarangService {
	TypeBarang findTypeBarangById(Long id);
	TypeBarang findTypeBarangByNama(String name);
	void saveTypeBarang(TypeBarang typeBarang);
	void updateTypeBarang(TypeBarang typeBarang);
	void deleteTypeBarangById(Long id);
	List<TypeBarang> findAllTypeBarang();
	boolean isTypeBarangExist(TypeBarang typeBarang);

}
