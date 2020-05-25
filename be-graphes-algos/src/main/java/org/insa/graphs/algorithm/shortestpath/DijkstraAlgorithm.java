package org.insa.graphs.algorithm.shortestpath;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.insa.graphs.algorithm.AbstractSolution.Status;
import org.insa.graphs.algorithm.utils.BinaryHeap;
import org.insa.graphs.algorithm.utils.ElementNotFoundException;
import org.insa.graphs.model.*;

public class DijkstraAlgorithm extends ShortestPathAlgorithm {
	int compteur_sommets_entree_tas = 0;
    int compteur_sommets_marques = 0;

    public DijkstraAlgorithm(ShortestPathData data) {
        super(data);
    }

    @Override
    protected ShortestPathSolution doRun() {
        final ShortestPathData data = getInputData();
        
        Graph graph = data.getGraph();
        int nbNodes = graph.size() ;
        Label[] labels = new Label[nbNodes];
        
       
        
        for(Node noeud : graph.getNodes()) {
        	labels[noeud.getId()]=new Label(noeud);
        }
        
        return do_D(labels, data, graph);
    }
    
    public ShortestPathSolution do_D (Label[] labels, ShortestPathData data, Graph graph ) {
    	long start = new Date().getTime();
    	ShortestPathSolution solution = null;
    	BinaryHeap<Label> tas = new BinaryHeap<Label>();
        Label destination = labels[data.getDestination().getId()];
        Label origine =labels[data.getOrigin().getId()];
        
        
        //on dit aux observateurs que le premier process a commencé
        this.notifyOriginProcessed(origine.getSommet_Courant());
        if(destination.getSommet_Courant().equals(origine.getSommet_Courant())) {
        	solution = new ShortestPathSolution (data, Status.INFEASIBLE);
        }
        origine.setCout(0);
        tas.insert(origine);
        Label x=null;
        int cpt =0;
        while(!tas.isEmpty()&&destination.getMarque() == false&&solution==null) {
        	cpt++;
        	x = tas.deleteMin();
        	x.setMarque(true);
        	compteur_sommets_marques++;
        	//System.out.println("Cout visité :"+x.getCout());
        	this.notifyNodeReached(x.getSommet_Courant());
        	List<Arc> succ = x.getSommet_Courant().getSuccessors();
        	//int cpt2=0;
        	for(Arc suc : succ) {
        		//cpt2+=1;
        		Label y = labels[suc.getDestination().getId()];
        		if((y.getMarque()==false) && data.isAllowed(suc)) {
        			//if(y.getCout()>x.getCout()+data.getCost(suc)) {
        				//y.setCout(x.getCoup()+suc.getLength());
        				update(tas, y,x, suc, data);
        			//}	
        		}
        	}
        	//System.out.println("nbre sucesseurs visités:"+ cpt2);
        }
        
        if(destination.getMarque()==false) {
        	solution = new ShortestPathSolution(data, Status.INFEASIBLE);
        }else {
        	this.notifyDestinationReached(destination.getSommet_Courant());
        	ArrayList<Arc> arcs = new ArrayList<Arc>();
        	while(!x.equals(origine)) {
        		arcs.add(x.getPere());
        		x=labels[x.getPere().getOrigin().getId()];
        	}
            Collections.reverse(arcs);
            //System.out.println("nombre d'arcs solution :"+arcs.size());
            //System.out.println("nbre itérations :"+cpt);
            solution = new ShortestPathSolution(data, Status.OPTIMAL, new Path(graph, arcs));
            
        }
        long end = new Date().getTime();
        long time = end - start;
        System.out.println("Temps exécution en millisecondes :"+time+" taille solution :"+solution.getPath().size());
        System.out.println("Sommets entrée tas :"+compteur_sommets_entree_tas+" sommets marqués :"+compteur_sommets_marques);
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
    		y.setCout(nouveau_cout);
    		y.setPere(pere);
    		//System.out.println(tas);
    		//System.out.println("tas valide :"+tas.isValid(0));
    		tas.insert(y);
    		compteur_sommets_entree_tas++;
    	}
    	
    }

}
