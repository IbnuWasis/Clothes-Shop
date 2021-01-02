package com.ibnu.project.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibnu.project.model.TypeBarang;
@Repository
public interface TypeBarangRepository extends JpaRepository<TypeBarang, Long>{
	TypeBarang findBynamaType(String nama);
}
