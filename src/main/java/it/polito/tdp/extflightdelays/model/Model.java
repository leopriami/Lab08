package it.polito.tdp.extflightdelays.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.DirectedWeightedPseudograph;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import org.jgrapht.graph.SimpleWeightedGraph;

import it.polito.tdp.extflightdelays.db.ExtFlightDelaysDAO;

public class Model {
	
	private DirectedWeightedPseudograph<String, DefaultWeightedEdge> grafo;
	private List<String> nodi;
	private List<Edge> archi;
	private ExtFlightDelaysDAO dao;
	private Simulator sim;
	
	public Model() {
		this.dao = new ExtFlightDelaysDAO();
		this.sim = new Simulator();
	}

	public void creaGrafo() {
		nodi = new ArrayList<>(dao.loadAllStates());
		archi = new ArrayList<>(dao.loadAllFlights());
		grafo = new DirectedWeightedPseudograph<>(DefaultWeightedEdge.class);
		for(String v : nodi) grafo.addVertex(v);
		for(Edge e : archi) {
			Graphs.addEdge(grafo, e.getS1(), e.getS2(), e.getWeight());
		}
	}

	public DirectedWeightedPseudograph<String, DefaultWeightedEdge> getGrafo() {
		return grafo;
	}
	
	public List<DefaultWeightedEdge> velivoli(String s){
		List<DefaultWeightedEdge> vicini = new ArrayList<>(grafo.outgoingEdgesOf(s));
		Collections.sort(vicini, new Comparator<DefaultWeightedEdge>() {
			public int compare(DefaultWeightedEdge c1, DefaultWeightedEdge c2) {
				if(grafo.getEdgeWeight(c1) - grafo.getEdgeWeight(c2) > 0) {
					return -1;
				}
				else return 1;
			}
		});
		return vicini;
	}

	public Simulator getSim() {
		return sim;
	}

}
