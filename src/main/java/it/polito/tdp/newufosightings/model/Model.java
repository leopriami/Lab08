package it.polito.tdp.newufosightings.model;

import java.util.ArrayList;
import java.util.List;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.newufosightings.db.NewUfoSightingsDAO;

public class Model {
	
	private SimpleWeightedGraph<String, DefaultWeightedEdge> grafo;
	private List<String> nodi;
	private List<Edge> archi;
	private List<Sighting> pesi;
	private NewUfoSightingsDAO dao;
	private Simulator sim;
	
	public Model() {
		this.dao = new NewUfoSightingsDAO();
		this.sim = new Simulator();
	}

	public void creaGrafo(int anno, int giorni) {
		nodi = new ArrayList<>(dao.loadAllStates());
		archi = new ArrayList<>(dao.loadAllEdges());
		pesi = new ArrayList<>(dao.loadAllSightings(anno, giorni));
		for(Sighting s : pesi) {
			for(Edge e : archi) {
				if(s.getState1().equals(e.getV1()) && s.getState2().equals(e.getV2()) || s.getState1().equals(e.getV2()) && s.getState2().equals(e.getV1())) {
					e.setWeight(s.getPeso());
					break;
				}
			}
		}
		grafo = new SimpleWeightedGraph<>(DefaultWeightedEdge.class);
		for(String v : nodi) grafo.addVertex(v);
		for(Edge e : archi) {
			Graphs.addEdge(grafo, e.getV1(), e.getV2(), e.getWeight());
		}
	}

	public SimpleWeightedGraph<String, DefaultWeightedEdge> getGrafo() {
		return grafo;
	}

	public NewUfoSightingsDAO getDao() {
		return dao;
	}

	public Simulator getSim() {
		return sim;
	}

}
