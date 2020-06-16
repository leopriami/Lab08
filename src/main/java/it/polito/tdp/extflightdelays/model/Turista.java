package it.polito.tdp.extflightdelays.model;

public class Turista {
	
	private Integer id;
	private String statoAttuale;
	
	public Turista(Integer id, String statoAttuale) {
		this.id = id;
		this.statoAttuale = statoAttuale;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStatoAttuale() {
		return statoAttuale;
	}

	public void setStatoAttuale(String statoAttuale) {
		this.statoAttuale = statoAttuale;
	}

}
