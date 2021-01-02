package com.ibnu.project.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibnu.project.model.TypeBarang;
import com.ibnu.project.repository.TypeBarangRepository;
import com.ibnu.project.services.TypeBarangService;

@Service("typeBarangService")
@Transactional
public class TypeBarangServiceImpl implements TypeBarangService{
	@Autowired
	private TypeBarangRepository tBarangRepository;
	
	public TypeBarang findTypeBarangById(Long id) {
		return tBarangRepository.findOne(id);
	}
	public TypeBarang findTypeBarangByNama(String name) {
		return tBarangRepository.findBynamaType(name);
	}
	public void saveTypeBarang(TypeBarang typeBarang) {
		tBarangRepository.save(typeBarang);
	}
	public void updateTypeBarang(TypeBarang typeBarang) {
		saveTypeBarang(typeBarang);
	}
	public void deleteTypeBarangById(Long id) {
		tBarangRepository.delete(id);
	}
	public List<TypeBarang> findAllTypeBarang(){
		return tBarangRepository.findAll();
	}
	public boolean isTypeBarangExist(TypeBarang typeBarang) {
		return tBarangRepository.findOne(typeBarang.getId()) != null;
	}
}
