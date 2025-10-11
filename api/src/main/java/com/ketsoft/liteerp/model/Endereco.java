package com.ketsoft.liteerp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity(name="endereco")
@Data
public class Endereco {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long id;
	@Column(length = 20)
	Integer numero;
	Short sn;
	@Column(length = 50)
	String bairro;
	@Column(length = 8)
	String cep;
	@ManyToOne
    @JoinColumn(name = "cidade_id", referencedColumnName = "id")
	Cidade cidade;
}
