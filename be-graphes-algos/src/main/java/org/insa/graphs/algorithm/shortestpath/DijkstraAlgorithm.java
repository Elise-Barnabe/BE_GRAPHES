package org.insa.graphs.algorithm.shortestpath;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.insa.graphs.algorithm.AbstractSolution.Status;
import org.insa.graphs.algorithm.utils.BinaryHeap;
import org.insa.graphs.algorithm.utils.ElementNotFoundException;
import org.insa.graphs.model.*;

public class DijkstraAlgorithm extends ShortestPathAlgorithm {

    public DijkstraAlgorithm(ShortestPathData data) {
        super(data);
    }
    
    public double min(double x, double y) {
    	if(x<=y) {
    		return x;
    	}else {
    		return y;
    	}
    }

    @Override
    protected ShortestPathSolution doRun() {
        final ShortestPathData data = getInputData();
        ShortestPathSolution solution = null;
        Graph graph = data.getGraph();
        int nbNodes = graph.size() ;
        Label[] labels = new Label[nbNodes];
        BinaryHeap<Label> tas = new BinaryHeap<Label>();
       
        
        for(Node noeud : graph.getNodes()) {
        	labels[noeud.getId()]=new Label(noeud);
        }
        
        Label destination = labels[data.getDestination().getId()];
        Label origine =labels[data.getOrigin().getId()];
        origine.setCout(0);
        tas.insert(origine);
        Label x=null;

        while(!tas.isEmpty()&&destination.getMarque() == false) {
        	x = tas.deleteMin();
        	x.setMarque(true);
        	List<Arc> succ = x.getSommet_Courant().getSuccessors();
        	
        	for(Arc suc : succ) {
        		
        		Label y = labels[suc.getDestination().getId()];
        		if((y.getMarque()==false) && data.isAllowed(suc)) {
        			if(y.getCout()>x.getCout()+data.getCost(suc)) {
        				//y.setCout(x.getCoup()+suc.getLength());
        				update(tas, y,x, suc, data);
        			}	
        		}
        	}
        }
        if(destination.getMarque()==false) {
        	solution = new ShortestPathSolution(data, Status.INFEASIBLE);
        }else {
        	ArrayList<Arc> arcs = new ArrayList<Arc>();
        	while(!x.equals(origine)) {
        		arcs.add(x.getPere());
        		x=labels[x.getPere().getOrigin().getId()];
        	}
            Collections.reverse(arcs);
            solution = new ShortestPathSolution(data, Status.OPTIMAL, new Path(graph, arcs));
            
        }
        return solution;
    }
    
    public void update(BinaryHeap<Label> tas, Label y, Label x, Arc pere, ShortestPathData data) {
    	double ancien_cout;
    	double nouveau_cout;
    	ancien_cout = y.getCout();
    	nouveau_cout = x.getCout()+data.getCost(pere);
    	if(nouveau_cout<ancien_cout) {
    		try {
    			tas.remove(y);
    		}catch(ElementNotFoundException e) {
    			
    		}
    		y.setCout(x.getCout()+pere.getLength());
    		y.setPere(pere);
    		tas.insert(y);
    	}
    	
    }

}
