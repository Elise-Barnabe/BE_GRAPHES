package org.insa.graphs.algorithm.shortestpath;
import org.insa.graphs.model.*;

public class LabelStar extends Label implements Comparable<Label>{
	
	private double coutEstime;
	
	public LabelStar (Node sommet_courant, double h) {
		super(sommet_courant);
		this.coutEstime=h;
	}
	
	
	@Override
	public double getCoutTotal() {
		return this.coutEstime+this.cout;
	}

	public double getCoutEstime() {
		return this.coutEstime;
	}
	public int compareTo(LabelStar label) {
		if(label.getCoutTotal()==this.getCoutTotal()) {
			return Double.compare(label.getCoutEstime(), this.getCoutEstime());
		}else {
			return Double.compare(label.getCoutTotal(), this.getCoutTotal());
		}
	}
}
