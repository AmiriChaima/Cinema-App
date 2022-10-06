package com.chaima.cinema.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Entity
@Data // lombok gérer getters and setters
@AllArgsConstructor //constructeur avec paramètres
@NoArgsConstructor  // constructeur sans paramètres
@ToString
public class Ville implements Serializable {
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private Double Longitude,latitude,altitude;
	@OneToMany(mappedBy="ville")
	private Collection <Cinema> cinemas;

}
