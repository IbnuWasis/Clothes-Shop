package com.ibnu.project.services;

import java.sql.Date;
import java.util.List;

import com.ibnu.project.model.Setoran;

public interface SetoranService {
	
	Setoran findSetoranById(Long id);
	void saveSetoran(Setoran setoran);
	void updateSetoran(Setoran setoran);
	void deleteSetoranById(Long id);
	List<Setoran> findAllSetoran();
	boolean isSetoranExist(Setoran setoran);
	Setoran findSetoranByTanggal(Date tanggal);

}
