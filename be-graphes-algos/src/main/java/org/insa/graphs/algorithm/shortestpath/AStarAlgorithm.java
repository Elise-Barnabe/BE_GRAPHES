package org.insa.graphs.algorithm.shortestpath;

import org.insa.graphs.algorithm.AbstractInputData;
import org.insa.graphs.model.Graph;
import org.insa.graphs.model.Node;

public class AStarAlgorithm extends DijkstraAlgorithm {

    public AStarAlgorithm(ShortestPathData data) {
        super(data);
    }

    public ShortestPathSolution doRun() {
    	final ShortestPathData data = getInputData();
        
        Graph graph = data.getGraph();
        int nbNodes = graph.size() ;
        double h; 
        LabelStar[] labels = new LabelStar[nbNodes];
          
        for(Node noeud : graph.getNodes()) {
        	if(this.data.getMode()==AbstractInputData.Mode.TIME) {
        		// faire une comparaison entre maximum speed datat et maximum speed graph
        		//prendre le min et si data est -1 prendre graph
        		//vitesses en km/H attention !!
        		int vitesse_graph = graph.getGraphInformation().getMaximumSpeed();
        		int vitesse_data = this.data.getMaximumSpeed();
        		int vitesse;
        		if(vitesse_data == -1 && vitesse_graph == -1) {
        			vitesse =130;
        		}else if(vitesse_data == -1) {
        			vitesse = vitesse_graph;
        		}else if (vitesse_graph == -1) {
        			vitesse = vitesse_data;
        		}else if(vitesse_data<vitesse_graph) {
        			vitesse = vitesse_data;
        		}else {
        			vitesse = vitesse_graph;
        		}
        		
        		h= 3.6*noeud.getPoint().distanceTo(data.getDestination().getPoint())/(double) vitesse;
        		
        	}else {
        		h= noeud.getPoint().distanceTo(data.getDestination().getPoint());
        	}
        	
        	labels[noeud.getId()]=new LabelStar(noeud, h);
        }
        
        return do_D(labels, data, graph);
    }

}


