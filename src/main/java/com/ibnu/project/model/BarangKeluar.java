package com.ibnu.project.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Data
@Entity
@Table(name="barang_keluar")
public class BarangKeluar {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;
	@NotEmpty
	@Column(name="id_barang", nullable=false)
	private Long idBarang;
	@NotEmpty
	@Column(name="tanggal", nullable=false)
	private Date tanggal;
	@NotEmpty
	@Column(name="harga_beli", nullable=false)
	private BigDecimal hargaBeli;
	@NotEmpty
	@Column(name="harga_jual", nullable=false)
	private BigDecimal hargaJual;
	@NotEmpty
	@Column(name="total_barang", nullable=false)
	private int TotalBarang;
	@NotEmpty
	@Column(name="total_beli", nullable=false)
	private BigDecimal TotalBeli;
	@NotEmpty
	@Column(name="total_jual", nullable=false)
	private BigDecimal TotalJual;
	
	
}
