package com.chaima.cinema.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.chaima.cinema.entities.Seance;
@RepositoryRestResource
@CrossOrigin("*")
public interface SeanceRepository extends JpaRepository<Seance,Integer> {

}
