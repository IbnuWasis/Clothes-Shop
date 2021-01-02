package com.ibnu.project.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibnu.project.model.Setoran;
@Repository
public interface SetoranRepository extends JpaRepository<Setoran, Long>{
	Setoran findSetoranByTanggal(Date tanggal);
}
