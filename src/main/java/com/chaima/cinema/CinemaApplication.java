package com.chaima.cinema;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.chaima.cinema.services.ICinemaInitService;

@SpringBootApplication
public class CinemaApplication implements CommandLineRunner {

	@Autowired
	private ICinemaInitService iCinemaInitService;
	
	public static void main(String[] args) {
		SpringApplication.run(CinemaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		iCinemaInitService.InitVilles();
		iCinemaInitService.InitCinemas();
		iCinemaInitService.InitSalles();
		iCinemaInitService.InitPlaces();
		iCinemaInitService.InitSeances();
		iCinemaInitService.InitCategories();
		iCinemaInitService.InitFilms();
		iCinemaInitService.InitProjections();
		iCinemaInitService.InitTickets();
	}

}
