package it.polito.tdp.newufosightings.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Sighting {
	private String state1;
	private String state2;
	private int peso;
	
	public Sighting(String state1, String state2, int peso) {
		this.state1 = state1;
		this.state2 = state2;
		this.peso = peso;
	}

	public String getState1() {
		return state1;
	}

	public void setState1(String state1) {
		this.state1 = state1;
	}

	public String getState2() {
		return state2;
	}

	public void setState2(String state2) {
		this.state2 = state2;
	}

	public int getPeso() {
		return peso;
	}

	public void setPeso(int peso) {
		this.peso = peso;
	}
	
}
