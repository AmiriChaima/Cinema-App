package com.chaima.cinema.services;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chaima.cinema.dao.CategorieRepository;
import com.chaima.cinema.dao.CinemaRepository;
import com.chaima.cinema.dao.FilmRepository;
import com.chaima.cinema.dao.PlaceRepository;
import com.chaima.cinema.dao.ProjectionRepository;
import com.chaima.cinema.dao.SalleRepository;
import com.chaima.cinema.dao.SeanceRepository;
import com.chaima.cinema.dao.TicketRepository;
import com.chaima.cinema.dao.VilleRepository;
import com.chaima.cinema.entities.Categorie;
import com.chaima.cinema.entities.Cinema;
import com.chaima.cinema.entities.Film;
import com.chaima.cinema.entities.Place;
import com.chaima.cinema.entities.Projection;
import com.chaima.cinema.entities.Salle;
import com.chaima.cinema.entities.Seance;
import com.chaima.cinema.entities.Ticket;
import com.chaima.cinema.entities.Ville;



@Service
@Transactional
public class CinemaInitServiceImpl implements ICinemaInitService{
 @Autowired	
 private VilleRepository villeRepository;
 @Autowired	
 private CinemaRepository cinemaRepository;
 @Autowired	
 private SalleRepository salleRepository;
 @Autowired	
 private PlaceRepository placeRepository;
 @Autowired	
 private SeanceRepository seanceRepository;
 @Autowired	
 private FilmRepository filmRepository;
 @Autowired	
 private ProjectionRepository projectionRepository;
 @Autowired	
 private CategorieRepository categorieRepository;
 @Autowired	
 private TicketRepository ticketRepository;
	
	
	
	@Override
	public void InitVilles() {
		Stream.of("Tunis","Sousse","Kef","Sfax").forEach(v->{
			Ville ville= new Ville();
			ville.setName(v);
			villeRepository.save(ville);
		});
		
	}

	@Override
	public void InitCinemas() {
		villeRepository.findAll().forEach(v-> {
			Stream.of("Le Colisée","Pathe Sousse","Pathé Azur City","L'agora","CinéMadart").forEach(nameCinema->{
				Cinema cinema =new Cinema();
				cinema.setName(nameCinema);
				cinema.setNombreSalles(3+(int)(Math.random()*7));
				cinema.setVille(v);
				cinemaRepository.save(cinema);
			})	;
		});
		
	}

	@Override
	public void InitSalles() {
		cinemaRepository.findAll().forEach(cinema-> {
		for(int i=0;i<cinema.getNombreSalles();i++)
		{
			Salle salle=new Salle();
			salle.setName("salle"+(i+1));
			salle.setCinema(cinema);
			salle.setNombrePlaces(20+(int)(Math.random()*20));
			salleRepository.save(salle);
		}
		});
		
	}

	@Override
	public void InitPlaces() {
		salleRepository.findAll().forEach(salle->{
			for(int i=0;i<salle.getNombrePlaces();i++)
			{
				Place place=new Place();
				place.setNumero(i+1);
				place.setSalle(salle);
				placeRepository.save(place);
			}
		});
	}

	@Override
	public void InitSeances() {
		DateFormat dateFormat =new SimpleDateFormat("HH:mm");
		Stream.of("10:00","12:00","15:00","17:00","22:00").forEach(s->{
			
			Seance seance=new Seance();
			try {
				seance.setHeureDebut(dateFormat.parse(s));
				seanceRepository.save(seance);
			} catch (ParseException e) {
				
				e.printStackTrace();
			}
			
		}
			);
			
	}
	
	@Override
	public void InitCategories() {
		
		Stream.of("Histoire","Fiction","Actions","Drame","Policier").forEach(c->
		{
			Categorie categorie=new Categorie();
			categorie.setName(c);
			categorieRepository.save(categorie);
					
		}
				);
				
	}
	
	
	@Override
	public void InitFilms() {
	double[] duree=new double[]{1,2,1.5,2.5,3};
	List <Categorie> categories = categorieRepository.findAll();
    Stream.of("Game of Thrones","Harry Potter","Spiderman","Anne with an E","Vikings").forEach(f->
    {
    	Film film =new Film();
    	film.setTitre(f);
    	film.setDuree(duree[new Random().nextInt(duree.length)]);
    	film.setPhoto(f.replaceAll(" ", "")+".jpg");
    	film.setCategorie(categories.get(new Random().nextInt(categories.size())));
    	filmRepository.save(film);
    }
    		);		
	}
	

	@Override
	public void InitProjections() {
		double[] prix =new double[] {20,30,40,50,60,70};
   villeRepository.findAll().forEach(v->{
	 
	 v.getCinemas().forEach(c->{
		 c.getSalles().forEach(s->{
			 filmRepository.findAll().forEach(f->{
				 seanceRepository.findAll().forEach(seance->{
					 
					 Projection projection=new Projection();
					 projection.setDateProjection(new Date());
					 projection.setFilm(f);
					 projection.setPrix(prix[new Random().nextInt(prix.length)]);
					 projection.setSalle(s);
					 projection.setSeance(seance);
					 projectionRepository.save(projection); 
					 
				 });
				 
			 });
			 
		 });
	 });
 });
		
	}

	

	@Override
	public void InitTickets() {

		projectionRepository.findAll().forEach(p->{
			p.getSalle().getPlaces().forEach(place->{
				Ticket ticket =new Ticket();
				ticket.setPlace(place);
				ticket.setPrix(p.getPrix());
				ticket.setProjection(p);
				ticket.setReservee(false);
				ticketRepository.save(ticket);
				
			});
			
		});
	}

}
