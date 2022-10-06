package com.chaima.cinema.entities;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Entity
@Data // lombok gérer getters and setters
@AllArgsConstructor //constructeur avec paramètres
@NoArgsConstructor  // constructeur sans paramètres
@ToString

public class Film {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String titre;
	private String description;
	private String realisateur;
	private Date dateSortie;
	private Double duree;
	private String photo;
	@JsonProperty(access=Access.WRITE_ONLY)
	@OneToMany(mappedBy="film")
	private Collection<Projection> projections;
	
	@ManyToOne
	private Categorie categorie;

}
