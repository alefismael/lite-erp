package com.ketsoft.liteerp.model;

import org.springframework.data.geo.Point;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity(name="cidade")
@Data
public class Cidade {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long id;
	@ManyToOne
    @JoinColumn(name = "uf", referencedColumnName = "id")
	Estado estado;
	@Column(length = 120, nullable = false)
	String nome;
	Integer ibge;
	@Column(columnDefinition = "geometry(Point,4326)")
	Point lat_lon;
	Short cod_tom ;
}
