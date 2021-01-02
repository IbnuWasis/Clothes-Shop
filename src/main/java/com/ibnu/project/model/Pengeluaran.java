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

@Entity
@Table(name="pengeluaran")
@Data
public class Pengeluaran {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;
	@NotEmpty
	@Column(name="tanggal", nullable=false)
	private Date tanggal;
	@NotEmpty
	@Column(name="nama_pengeluaran", nullable=false)
	private String NamaPengeluaran;
	@NotEmpty
	@Column(name="jumlah_pengeluaran", nullable=false)
	private BigDecimal JumlahPengeluaran;	
	
}
