package com.ibnu.project.model;

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

@Entity
@Table(name="stok_barang")
@Data
public class StokBarang {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;
	@NotEmpty
	@Column(name="id_barang", nullable=false)
	private Long idBarang;
	@NotEmpty
	@Column(name="harga_beli_per_barang", nullable=false)
	private BigInteger HargaBeliPerBarang;
	@NotEmpty
	@Column(name="harga_grosir", nullable=false)
	private BigInteger HargaGrosir;
	@NotEmpty
	@Column(name="total_barang", nullable=false)
	private int TotalBarang;
	@NotEmpty
	@Column(name="tanggal", nullable=false)
	private Date tanggal;
	@NotEmpty
	@Column(name="tanggal_update", nullable=false)
	private Date tanggalUpdate;
	
	
}
