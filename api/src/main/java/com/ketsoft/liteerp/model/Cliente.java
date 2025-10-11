package com.ketsoft.liteerp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name="cliente")
public class Cliente {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long id;
	@Column(name="user", nullable = false)
	User user;
}
