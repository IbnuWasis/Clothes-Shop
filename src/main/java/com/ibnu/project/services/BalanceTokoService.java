package com.ibnu.project.services;

import java.util.List;

import com.ibnu.project.model.BalanceToko;

public interface BalanceTokoService {
	void saveBalanceToko(BalanceToko balanceToko);
	void updateBalanceToko(BalanceToko balanceToko);
	List<BalanceToko> findAllBalanceToko();
	void deleteBalanceTokoById(Long id);
	boolean BalanceTokoIsExist(BalanceToko balanceToko);
	BalanceToko findByIdBalance(Long id);
	
}
