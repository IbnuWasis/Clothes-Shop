package com.ibnu.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Data;

@Entity
@Table(name="barang")
@Data
public class Barang {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;
	@NotEmpty
	@Column(name="nama_barang", nullable = false)
	private String namaBarang;
	@NotEmpty
	@Column(name="kode_barang", nullable = false)
	private String kodeBarang;
	@NotEmpty
	@Column(name="id_type", nullable=false)
	private int idType;
	
}
