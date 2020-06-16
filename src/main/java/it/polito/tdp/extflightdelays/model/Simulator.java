package it.polito.tdp.extflightdelays.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import org.jgrapht.graph.DefaultWeightedEdge;

import it.polito.tdp.extflightdelays.model.Event.EventType;

public class Simulator {
	
	private Model model;
	
	private Integer G = 7;
	private Integer T = 100;
	private String statoIniziale;
	
	private List<Turista> turisti;
	private Map<String, Integer> idMap;
	
	private PriorityQueue<Event> queue;
	
	public void init(Model model, Integer G, Integer T, String statoIniziale) {
		this.model = model;
		this.G = G;
		this.T = T;
		this.statoIniziale = statoIniziale;
		turisti = new ArrayList<>();
		for(int i = 1; i <= T; i++) {
			turisti.add(new Turista(i, statoIniziale));
		}
		idMap = new HashMap<>();
		for(String s : this.model.getGrafo().vertexSet()) {
			idMap.put(s, 0);
		}
		idMap.put(statoIniziale, T);
		queue = new PriorityQueue<>();
		for(Turista t : turisti) {
			int random = (int)(Math.random()*this.model.getGrafo().outgoingEdgesOf(statoIniziale).size());
			List<DefaultWeightedEdge> voli = new ArrayList<>(this.model.getGrafo().outgoingEdgesOf(statoIniziale));
			queue.add(new Event(1, t, EventType.VOLO, statoIniziale, this.model.getGrafo().getEdgeTarget(voli.get(random))));
		}
	}
	
	public void run() {
		while (!this.queue.isEmpty()) {
			Event e = this.queue.poll();
			if(e.getGiorno()>=G) return;
			processEvent(e);
		}
	}
	
	private void processEvent(Event e) {

		switch (e.getType()) {
		case VOLO:
			idMap.put(e.getStatoArrivo(), idMap.get(e.getStatoArrivo())+1);
			idMap.put(e.getStatoPartenza(), idMap.get(e.getStatoPartenza())-1);
			int random = (int)(Math.random()*this.model.getGrafo().outgoingEdgesOf(e.getStatoArrivo()).size());
			List<DefaultWeightedEdge> voli = new ArrayList<>(this.model.getGrafo().outgoingEdgesOf(e.getStatoArrivo()));
			queue.add(new Event(e.getGiorno()+1, e.getTurista(), EventType.VOLO, e.getStatoArrivo(), this.model.getGrafo().getEdgeTarget(voli.get(random))));
			break;
		}
	}
	
	private double sommaPesi(String s) {
		double peso = 0;
		for(DefaultWeightedEdge e : this.model.getGrafo().outgoingEdgesOf(s)) {
			peso = peso + this.model.getGrafo().getEdgeWeight(e);
		}
		return peso;
	}

	public Map<String, Integer> getIdMap() {
		return idMap;
	}

}
