package com.ibnu.project.model;


import java.math.BigDecimal;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Entity
@Table(name="balance_toko")
@Data
public class BalanceToko {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;
	@NotEmpty
	@Column(name="tanggal", nullable=false)
	private Date tanggal;
	@NotEmpty
	@Column(name="jumlah_pemasukan", nullable=false)
	private BigDecimal JumlahPemasukan;
	@NotEmpty
	@Column(name="jumlah_pengeluaran", nullable=false)
	private BigDecimal JumlahPengeluaran;
	@NotEmpty
	@Column(name="balance_sekarang", nullable=false)
	private BigDecimal BalanceSekarang;
	
	
	
}
