package com.ketsoft.liteerp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name="pais")
@Data
public class Pais {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long id;
	@Column(length = 60, nullable = false)
	String nome;
	@Column(length = 60)
	String nome_pt;
	@Column(length = 2, nullable = false,unique = true)
	String sigla;
	@Column(unique = true)
	Integer bacen;
	Integer ddi;
}
