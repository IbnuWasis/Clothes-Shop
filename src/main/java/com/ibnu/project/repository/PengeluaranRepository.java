package com.ibnu.project.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibnu.project.model.Pengeluaran;
@Repository
public interface PengeluaranRepository extends JpaRepository<Pengeluaran, Long>{
	List<Pengeluaran> findByTanggal(Date tanggal);
}
