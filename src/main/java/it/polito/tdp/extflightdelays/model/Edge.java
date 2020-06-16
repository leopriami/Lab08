package it.polito.tdp.extflightdelays.model;

public class Edge {
	
	private String s1;
	private String s2;
	private int weight;
	
	public Edge(String s1, String s2, int weight) {
		this.s1 = s1;
		this.s2 = s2;
		this.weight = weight;
	}

	public String getS1() {
		return s1;
	}

	public void setS1(String s1) {
		this.s1 = s1;
	}

	public String getS2() {
		return s2;
	}

	public void setS2(String s2) {
		this.s2 = s2;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

}
