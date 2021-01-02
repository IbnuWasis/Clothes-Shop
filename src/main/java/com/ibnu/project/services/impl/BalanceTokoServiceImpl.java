package com.ibnu.project.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibnu.project.model.BalanceToko;
import com.ibnu.project.repository.BalanceTokoRepository;
import com.ibnu.project.services.BalanceTokoService;

@Service("balanceTokoService")
@Transactional
public class BalanceTokoServiceImpl implements BalanceTokoService{
	
	@Autowired
	private BalanceTokoRepository bRepository;
	
	public void saveBalanceToko(BalanceToko balanceToko) {
		bRepository.save(balanceToko);
	}
	public void updateBalanceToko(BalanceToko balanceToko) {
		saveBalanceToko(balanceToko);
	}
	public List<BalanceToko> findAllBalanceToko(){
		return bRepository.findAll();
	}
	public void deleteBalanceTokoById(Long id) {
		bRepository.delete(id);
	}
	public boolean BalanceTokoIsExist(BalanceToko balanceToko) {
		return bRepository.findOne(balanceToko.getId()) != null;
	}
	public BalanceToko findByIdBalance(Long id) {
		return bRepository.findOne(id);
	}

}
