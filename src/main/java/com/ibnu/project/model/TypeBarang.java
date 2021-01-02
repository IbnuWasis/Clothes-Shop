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
@Table(name="type_barang")
@Data
public class TypeBarang {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;
	@NotEmpty
	@Column(name="nama_type", nullable=false)
	private String namaType;
	
}
