package com.ibnu.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibnu.project.model.BalanceToko;

@Repository
public interface BalanceTokoRepository extends JpaRepository<BalanceToko, Long>{

}
