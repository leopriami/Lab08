package it.polito.tdp.extflightdelays.model;

import java.time.LocalDateTime;

public class Flight {

	private int originAirportId;
	private int destinationAirportId;
	private double distance;

	public Flight(int originAirportId,int destinationAirportId,double distance) {
		this.originAirportId = originAirportId;
		this.destinationAirportId = destinationAirportId;
		this.distance = distance;
	}

	public int getOriginAirportId() {
		return originAirportId;
	}

	public void setOriginAirportId(int originAirportId) {
		this.originAirportId = originAirportId;
	}

	public int getDestinationAirportId() {
		return destinationAirportId;
	}

	public void setDestinationAirportId(int destinationAirportId) {
		this.destinationAirportId = destinationAirportId;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

}
