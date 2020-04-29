package org.insa.graphs.algorithm.shortestpath;

import org.insa.graphs.model.*;

public class Label implements Comparable<Label>{
	private Node sommet_courant;
	private boolean marque;
	protected double cout;
	private Arc pere;
	
	public Label(Node sommet_courant) {
		this.sommet_courant=sommet_courant;
		this.marque=false;
		this.cout=Double.POSITIVE_INFINITY;
		this.pere=null;
	}
	
	public Node getSommet_Courant() {
		return this.sommet_courant;
	}

	public double getCout() {
		return this.cout;
	}
	
	public double getCoutTotal() {
		return this.getCout();
	}
	
	public void setCout(double cout) {
		this.cout=cout;
	}
	
	public void setMarque(boolean marque) {
		this.marque=marque; 
	}
	
	public boolean getMarque() {
		return this.marque;
	}
	
	public void setPere(Arc pere) {
		this.pere=pere;
	}
	
	public Arc getPere() {
		return this.pere;
	}
	
	public int compareTo(Label label) {
		return Double.compare(this.getCoutTotal(), label.getCoutTotal());
	}
}
