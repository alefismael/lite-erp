package com.ketsoft.liteerp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity(name="pessoa")
@Data
public class Pessoa {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long id;
	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id",nullable = false)
	User user;
	@Column(nullable = false,length = 50)
	String nome;
	@Column(length = 50)
	String nomeFantasia;
	@Column(nullable = false, unique = true, length = 11)
	String cpfCnpj;
	@ManyToOne
	@JoinColumn(name = "endereco_id", referencedColumnName = "id")
	Endereco endereco;
	
}
