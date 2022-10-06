package com.chaima.cinema.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.chaima.cinema.entities.Salle;
@RepositoryRestResource
@CrossOrigin("*")
public interface SalleRepository extends JpaRepository<Salle,Integer> {

}
