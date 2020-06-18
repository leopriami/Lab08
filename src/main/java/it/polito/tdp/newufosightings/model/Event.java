package it.polito.tdp.newufosightings.model;

import java.time.LocalDate;

public class Event implements Comparable<Event>{
	
	public enum EventType{
		AVVISTAMENTO,
		DEFCON,
		EMERGENZA,
	}
	
	private LocalDate date ;
	private EventType type ;
	private String state ;

	public Event(LocalDate date, EventType type, String state) {
		this.date = date;
		this.type = type;
		this.state = state;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public EventType getType() {
		return type;
	}

	public void setType(EventType type) {
		this.type = type;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public int compareTo(Event o) {
		return this.date.compareTo(o.date);
	}
	
}
