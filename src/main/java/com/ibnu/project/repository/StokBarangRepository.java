package com.ibnu.project.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibnu.project.model.StokBarang;
@Repository
public interface StokBarangRepository extends JpaRepository<StokBarang, Long>{
	StokBarang findByTanggal(Date tanggal);
}
