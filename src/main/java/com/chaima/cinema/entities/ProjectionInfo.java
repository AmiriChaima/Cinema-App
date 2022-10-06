package com.chaima.cinema.entities;
import java.util.Collection;
import java.util.Date;

import org.springframework.data.rest.core.config.Projection;
@Projection(name="p1",types= {com.chaima.cinema.entities.Projection.class})
public interface ProjectionInfo {
	public Integer getId();
	public double getPrix();
	public Date getDateProjection();
	public Salle getSalle();
	public Film getFilm();
	public Seance getSeance();
	public Collection<Ticket> getTicket();

}
