package com.chaima.cinema.web;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.chaima.cinema.dao.FilmRepository;
import com.chaima.cinema.dao.TicketRepository;
import com.chaima.cinema.entities.Film;
import com.chaima.cinema.entities.Ticket;

import lombok.Data;


@RestController
@CrossOrigin("*")
public class CinemaRestController {

	@Autowired 
	private FilmRepository filmRepository;
	@Autowired 
	private TicketRepository ticketRepository;

	@GetMapping(path="/imageFilms/{id}",produces=MediaType.IMAGE_JPEG_VALUE)
	public byte[] image (@PathVariable(name="id") Integer id) throws Exception  {
		Film f = filmRepository.findById(id).get();
		String photoname= f.getPhoto();
		File file =new File(System.getProperty("user.home")+"/cinema/"+photoname);
		Path path = Paths.get(file.toURI());
		return Files.readAllBytes(path);
	
	}

	@PostMapping("/payerTicket")
	@Transactional
	public List<Ticket> payerTicket(@RequestBody TicketForm ticketForm)
	{
		List<Ticket> listTickets =new ArrayList<>();
		ticketForm.getTickets().forEach(idTicket->{
			Ticket ticket=ticketRepository.findById(idTicket).get();
			ticket.setNomClient(ticketForm.getNomClient());
			ticket.setCodePayement(ticketForm.getCodePayement());
			ticket.setReservee(true);
			ticketRepository.save(ticket);
			listTickets.add(ticket);
		});
		
		return listTickets;
	}
	
}
	@Data
	class TicketForm
	{
		private String nomClient;
		private int codePayement;
		private List<Integer>tickets=new ArrayList<>();
	}
	

