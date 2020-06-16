package it.polito.tdp.extflightdelays.model;

public class Event implements Comparable<Event>{
	
	public enum EventType{
		VOLO,
	}
	
	private Integer giorno;
	private Turista turista;
	private EventType type;
	private String statoPartenza;
	private String statoArrivo;

	public Event(Integer giorno, Turista turista, EventType type, String statoPartenza, String statoArrivo) {
		this.giorno = giorno;
		this.turista = turista;
		this.type = type;
		this.statoPartenza = statoPartenza;
		this.statoArrivo = statoArrivo;
	}

	public Integer getGiorno() {
		return giorno;
	}

	public void setGiorno(Integer giorno) {
		this.giorno = giorno;
	}

	public Turista getTurista() {
		return turista;
	}

	public void setTurista(Turista turista) {
		this.turista = turista;
	}

	public EventType getType() {
		return type;
	}

	public void setType(EventType type) {
		this.type = type;
	}

	public String getStatoPartenza() {
		return statoPartenza;
	}

	public void setStatoPartenza(String statoPartenza) {
		this.statoPartenza = statoPartenza;
	}

	public String getStatoArrivo() {
		return statoArrivo;
	}

	public void setStatoArrivo(String statoArrivo) {
		this.statoArrivo = statoArrivo;
	}

	@Override
	public int compareTo(Event o) {
		return giorno.compareTo(o.getGiorno());
	}
	
}
