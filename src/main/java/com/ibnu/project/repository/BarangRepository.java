package com.ibnu.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibnu.project.model.Barang;
@Repository
public interface BarangRepository extends JpaRepository<Barang, Long>{
	Barang findByNamaBarang(String namaBarang);
}
