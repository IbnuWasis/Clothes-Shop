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
@Table(name="_user")
@Data
public class User {
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private Long id;
	@NotEmpty
	@Column(name="username", nullable=false)
	private String username;
	@NotEmpty
	@Column(name="password", nullable=false)
	private String password;
	@NotEmpty
	@Column(name="fullname", nullable=false)
	private String fullname;
	@Column(name="phone", nullable=false)
	private String phone;
	@Column(name="status", nullable=false)
	private String status;
	
}
