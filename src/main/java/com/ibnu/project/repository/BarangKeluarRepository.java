package com.ibnu.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibnu.project.model.BarangKeluar;
@Repository
public interface BarangKeluarRepository extends JpaRepository<BarangKeluar, Long>{
	List<BarangKeluar> findByIdBarang(Long idBarang);
}
