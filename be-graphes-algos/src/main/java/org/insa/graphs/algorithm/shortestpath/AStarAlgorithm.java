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
        		h= noeud.getPoint().distanceTo(data.getDestination().getPoint())/(double)this.data.getMaximumSpeed();
        	}else {
        		h= noeud.getPoint().distanceTo(data.getDestination().getPoint());
        	}
        	
        	labels[noeud.getId()]=new LabelStar(noeud, h);
        }
        
        return do_D(labels, data, graph);
    }

}
