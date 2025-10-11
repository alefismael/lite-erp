package com.ketsoft.liteerp.model;

import java.util.List;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity(name="estado")
@Data
public class Estado {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long id;
	@ManyToOne
    @JoinColumn(name = "pais", referencedColumnName = "id")
	Pais pais;
	@Column(length = 60, nullable = false)
	String nome;
	@Column(length = 2, nullable = false, unique = true)
	String uf;
	@Column(unique = true)
	Integer ibge;
	@JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "json")
	List<Integer> ddd;
}
