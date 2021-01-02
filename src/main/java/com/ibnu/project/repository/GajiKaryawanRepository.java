package com.ibnu.project.repository;

import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibnu.project.model.GajiKaryawan;
@Repository
public interface GajiKaryawanRepository extends JpaRepository<GajiKaryawan, Long> {
	GajiKaryawan findGajiKaryawanByTanggal(Date tanggal);
}
