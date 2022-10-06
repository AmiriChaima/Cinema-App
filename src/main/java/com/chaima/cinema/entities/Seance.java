package com.chaima.cinema.entities;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Entity
@Data // lombok gérer getters and setters
@AllArgsConstructor //constructeur avec paramètres
@NoArgsConstructor  // constructeur sans paramètres
@ToString
public class Seance {
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Temporal(TemporalType.TIME)
	private Date heureDebut;

}
