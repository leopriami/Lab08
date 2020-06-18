package it.polito.tdp.newufosightings.model;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

import it.polito.tdp.newufosightings.model.Event.EventType;

public class Simulator {
	
	private Model model;
	
	private int t1 = 4;
	private int t2 = 10;
	
	private Map<String, Integer> idMap;
	private Map<String, Integer> emergencyMap;
	
	private PriorityQueue<Event> queue;
	
	public void init(Model model, int t1, int t2, int anno, int giorni) {
		this.model = model;
		this.t1 = t1;
		this.t2 = t2;
		this.idMap = new HashMap<>();
		for(String s : this.model.getGrafo().vertexSet()) {
			idMap.put(s, 5);
		}
		this.emergencyMap = new HashMap<>();
		for(String s : this.model.getGrafo().vertexSet()) {
			emergencyMap.put(s, 0);
		}
		queue = new PriorityQueue<>(this.model.getDao().loadAllAvvistamenti(anno, giorni));
	}
	
	public void run() {
		while (!this.queue.isEmpty()) {
			Event e = this.queue.poll();
			processEvent(e);
		}
	}
	
	private void processEvent(Event e) {

		switch (e.getType()) {
		case AVVISTAMENTO:
			if(idMap.get(e.getState()) > 1) {
				idMap.put(e.getState(), idMap.get(e.getState())-1);
				if(idMap.get(e.getState()) == 1) {
					Event em = new Event(e.getDate().plusDays(t2), EventType.EMERGENZA, e.getState());
					queue.add(em);
					emergencyMap.put(e.getState(), emergencyMap.get(e.getState())+1);
				}
				else {
					Event def = new Event(e.getDate().plusDays(t1), EventType.DEFCON, e.getState());
					queue.add(def);
				}
			}
			break;

		case DEFCON:
			idMap.put(e.getState(), idMap.get(e.getState())+1);
			break;
			
		case EMERGENZA:
			idMap.put(e.getState(), 5);
			break;
		}
	}

	public Map<String, Integer> getEmergencyMap() {
		return emergencyMap;
	}
	
}
