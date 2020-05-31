package org.insa.graphs.algorithm.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import org.insa.graphs.algorithm.AbstractSolution.Status;
import org.insa.graphs.algorithm.ArcInspector;
import org.insa.graphs.algorithm.ArcInspectorFactory;
import org.insa.graphs.algorithm.shortestpath.AStarAlgorithm;
import org.insa.graphs.algorithm.shortestpath.BellmanFordAlgorithm;
import org.insa.graphs.algorithm.shortestpath.DijkstraAlgorithm;
import org.insa.graphs.algorithm.shortestpath.ShortestPathData;
import org.insa.graphs.model.Graph;
import org.insa.graphs.model.io.BinaryGraphReader;
import org.insa.graphs.model.io.GraphReader;
import org.junit.BeforeClass;
import org.junit.Test;



public class ShortestPathTest {
	
	protected static String mapCarre, mapInsa, mapGua;
	protected static DijkstraAlgorithm guaLongueurNulle_L,guaLongueurNulle_T, insaLongueurUne_L, insaLongueurUne_T, carreLongueurUne_L, carreLongueurUne_T, carreLongueurNormale_L, carreLongueurNormale_T, insaLongueurNulle_L, insaLongueurNulle_T, insaLongueurNormale_L, insaLongueurNormale_T;
	protected static AStarAlgorithm guaLongueurNulleL,guaLongueurNulleT,carreLongueurUneL, carreLongueurUneT, insaLongueurUneL, insaLongueurUneT, carreLongueurNormaleL, carreLongueurNormaleT, insaLongueurNulleL, insaLongueurNulleT, insaLongueurNormaleL, insaLongueurNormaleT; 
	protected static BellmanFordAlgorithm bguaLongueurNulle_L, bguaLongueurNulle_T,bcarreLongueurUneL, bcarreLongueurUneT, binsaLongueurUneL, binsaLongueurUneT, bcarreLongueurNormaleL, bcarreLongueurNormaleT, binsaLongueurNulleL, binsaLongueurNulleT, binsaLongueurNormaleL, binsaLongueurNormaleT;  
	
	@BeforeClass
	public static void init() throws IOException {
		//chargement des chemins des cartes
		mapCarre = "C:\\Users\\elise\\OneDrive\\Bureau\\ELISE\\INSA\\3A\\GRAPHES\\Maps\\carre.mapgr";
		mapInsa = "C:\\Users\\elise\\OneDrive\\Bureau\\ELISE\\INSA\\3A\\GRAPHES\\Maps\\insa.mapgr";
		mapGua = "C:\\Users\\elise\\OneDrive\\Bureau\\ELISE\\INSA\\3A\\GRAPHES\\Maps\\guadeloupe.mapgr";
		
		//creation des reader pour lire les cartes
		final GraphReader readCarre = new BinaryGraphReader(new DataInputStream(new BufferedInputStream(new FileInputStream(mapCarre))));
		final GraphReader readInsa = new BinaryGraphReader(new DataInputStream(new BufferedInputStream(new FileInputStream(mapInsa))));
		final GraphReader readGua = new BinaryGraphReader(new DataInputStream(new BufferedInputStream(new FileInputStream(mapGua))));
		
		//on lit les cartes et on recupere les infos dans graph
		final Graph carre = readCarre.read();
		final Graph insa = readInsa.read();
		final Graph gua = readGua.read();
		
		//fermeture des reader
		readCarre.close();
		readInsa.close();
		readGua.close();
		
		//recuperation des arc inspector
		final ArcInspector length = ArcInspectorFactory.getAllFilters().get(0);
		final ArcInspector time = ArcInspectorFactory.getAllFilters().get(2);
		
		//Scenarios
		//Pour la carte carre
		//chemin de longueur normale
		final ShortestPathData longueurNormaleLengthCarre = new ShortestPathData(carre, carre.getNodes().get(1), carre.getNodes().get(9), length);
		final ShortestPathData longueurNormaleTimeCarre = new ShortestPathData(carre, carre.getNodes().get(1), carre.getNodes().get(9), time);
		//chemin de longueur 1
		final ShortestPathData longueurUneLengthCarre = new ShortestPathData(carre, carre.getNodes().get(1), carre.getNodes().get(1), length);
		//chemin de longueur 1 en temps
		final ShortestPathData longueurUneTimeCarre = new ShortestPathData(carre, carre.getNodes().get(1), carre.getNodes().get(1), time);
		
		//Pour la carte insa
		//chemin de longueur normale 
		final ShortestPathData longueurNormaleLengthInsa = new ShortestPathData(insa, insa.getNodes().get(1), insa.getNodes().get(106), length);
		final ShortestPathData longueurNormaleTimeInsa = new ShortestPathData(insa, insa.getNodes().get(1), insa.getNodes().get(106), time);
		//chemin de longueur 1 
		final ShortestPathData longueurUneLengthInsa = new ShortestPathData(insa, insa.getNodes().get(1), insa.getNodes().get(1), length);
		final ShortestPathData longueurUneTimeInsa = new ShortestPathData(insa, insa.getNodes().get(1), insa.getNodes().get(1), time);	
		
		//Pour la carte Guadeloupe
		final ShortestPathData longueurNulleLengthGua = new ShortestPathData(gua, gua.getNodes().get(15991), gua.getNodes().get(29842), length);
		final ShortestPathData longueurNulleTimeGua = new ShortestPathData(gua, gua.getNodes().get(15991), gua.getNodes().get(29842), time);
		
		//Calcul des chemins via Dijkstra
		carreLongueurNormale_L = new DijkstraAlgorithm(longueurNormaleLengthCarre);
		carreLongueurNormale_T = new DijkstraAlgorithm(longueurNormaleTimeCarre);
		carreLongueurUne_L = new DijkstraAlgorithm(longueurUneLengthCarre);
		carreLongueurUne_T = new DijkstraAlgorithm(longueurUneTimeCarre);
		
		insaLongueurNormale_L = new DijkstraAlgorithm(longueurNormaleLengthInsa);
		insaLongueurNormale_T = new DijkstraAlgorithm(longueurNormaleTimeInsa);
		insaLongueurUne_L = new DijkstraAlgorithm(longueurUneLengthInsa);
		insaLongueurUne_T = new DijkstraAlgorithm(longueurUneTimeInsa);
		
		guaLongueurNulle_L = new DijkstraAlgorithm(longueurNulleLengthGua);
		guaLongueurNulle_T = new DijkstraAlgorithm(longueurNulleTimeGua);
		
		//Calcul des chemins via A*
		carreLongueurNormaleL = new AStarAlgorithm(longueurNormaleLengthCarre);
		carreLongueurNormaleT = new AStarAlgorithm(longueurNormaleTimeCarre);
		carreLongueurUneL = new AStarAlgorithm(longueurUneLengthCarre);
		carreLongueurUneT = new AStarAlgorithm(longueurUneTimeCarre);
		
		insaLongueurNormaleL = new AStarAlgorithm(longueurNormaleLengthInsa);
		insaLongueurNormaleT = new AStarAlgorithm(longueurNormaleTimeInsa);
		insaLongueurUneL = new AStarAlgorithm(longueurUneLengthInsa);
		insaLongueurUneT = new AStarAlgorithm(longueurUneTimeInsa);
		
		guaLongueurNulleL = new AStarAlgorithm(longueurNulleLengthGua);
		guaLongueurNulleT = new AStarAlgorithm(longueurNulleTimeGua);
		
		//Calcul des chemins via Bellman Ford
		bcarreLongueurNormaleL = new BellmanFordAlgorithm(longueurNormaleLengthCarre);
		bcarreLongueurNormaleT = new BellmanFordAlgorithm(longueurNormaleTimeCarre);
		bcarreLongueurUneL = new BellmanFordAlgorithm(longueurUneLengthCarre);
		bcarreLongueurUneT = new BellmanFordAlgorithm(longueurUneTimeCarre);
		
		binsaLongueurNormaleL = new BellmanFordAlgorithm(longueurNormaleLengthInsa);
		binsaLongueurNormaleT = new BellmanFordAlgorithm(longueurNormaleTimeInsa);
		binsaLongueurUneL = new BellmanFordAlgorithm(longueurUneLengthInsa);
		binsaLongueurUneT = new BellmanFordAlgorithm(longueurUneTimeInsa);
		
		bguaLongueurNulle_L = new BellmanFordAlgorithm(longueurNulleLengthGua);
		bguaLongueurNulle_T = new BellmanFordAlgorithm(longueurNulleTimeGua);
	}
	
	@Test
	public void testIsValidDijkstra() {
		//verification validité chemins dijkstra
		assertTrue(carreLongueurNormale_L.run().getPath().isValid());
		assertTrue(carreLongueurNormale_T.run().getPath().isValid());
		assertTrue(insaLongueurNormale_L.run().getPath().isValid());
		assertTrue(insaLongueurNormale_T.run().getPath().isValid());
		
		assertTrue(carreLongueurUne_L.run().getPath().isValid());
		assertTrue(carreLongueurUne_T.run().getPath().isValid());
		assertTrue(insaLongueurUne_L.run().getPath().isValid());
		assertTrue(insaLongueurUne_T.run().getPath().isValid());
		
		assertTrue(guaLongueurNulle_L.run().getPath().isValid());
		assertTrue(guaLongueurNulle_T.run().getPath().isValid());
	}
	@Test
	public void testIsValidAStar() {
		//verification validité A*
		assertTrue(carreLongueurNormaleL.run().getPath().isValid());
		assertTrue(carreLongueurNormaleT.run().getPath().isValid());
		assertTrue(insaLongueurNormaleL.run().getPath().isValid());
		assertTrue(insaLongueurNormaleT.run().getPath().isValid());
		
		assertTrue(carreLongueurUneL.run().getPath().isValid());
		assertTrue(carreLongueurUneT.run().getPath().isValid());
		assertTrue(insaLongueurUneL.run().getPath().isValid());
		assertTrue(insaLongueurUneT.run().getPath().isValid());
		
		assertTrue(guaLongueurNulleL.run().getPath().isValid());
		assertTrue(guaLongueurNulleT.run().getPath().isValid());
	}
		
	@Test
	public void testEgaliteSolution() {
		
		//longueur normale en length carre
		//Dijsktra et A*
		assertEquals(carreLongueurNormale_L.run().getPath().getLength(), carreLongueurNormaleL.run().getPath().getLength(),0);
		//Dijkstra et Bellman 
		assertEquals(carreLongueurNormale_L.run().getPath().getLength(), bcarreLongueurNormaleL.run().getPath().getLength(),0);
		
		//longueur normale en temps carre
		//Dijsktra et A*
		assertEquals(carreLongueurNormale_T.run().getPath().getMinimumTravelTime(), carreLongueurNormaleT.run().getPath().getMinimumTravelTime(),0);
		//Dijkstra et Bellman 
		assertEquals(carreLongueurNormale_T.run().getPath().getMinimumTravelTime(), bcarreLongueurNormaleT.run().getPath().getMinimumTravelTime(),0);
		
		//longueur normale en length insa
		//Dijsktra et A*
		assertEquals(insaLongueurNormale_L.run().getPath().getLength(), insaLongueurNormaleL.run().getPath().getLength(),0);
		//Dijkstra et Bellman 
		assertEquals(insaLongueurNormale_L.run().getPath().getLength(), binsaLongueurNormaleL.run().getPath().getLength(),0);
		
		//longueur normale en temps insa
		//Dijsktra et A*
		assertEquals(insaLongueurNormale_T.run().getPath().getMinimumTravelTime(), insaLongueurNormaleT.run().getPath().getMinimumTravelTime(),0);
		//Dijkstra et Bellman 
		assertEquals(insaLongueurNormale_T.run().getPath().getMinimumTravelTime(), binsaLongueurNormaleT.run().getPath().getMinimumTravelTime(),0);
		
		assertEquals(carreLongueurUne_L.run().getPath().getLength(), 0,0);
		assertEquals(carreLongueurUne_T.run().getPath().getLength(), 0,0);
		assertEquals(insaLongueurUne_L.run().getPath().getLength(), 0,0);
		assertEquals(insaLongueurUne_T.run().getPath().getLength(), 0,0);
		
		assertEquals(carreLongueurUneL.run().getPath().getLength(), 0,0);
		assertEquals(carreLongueurUneT.run().getPath().getLength(), 0,0);
		assertEquals(insaLongueurUneL.run().getPath().getLength(), 0,0);
		assertEquals(insaLongueurUneT.run().getPath().getLength(), 0,0);
	}
	
	@Test
	public void testStatus() {
		
		//longueur normale carre 
		//Dijkstra
		assertEquals(carreLongueurNormale_L.run().getStatus(), Status.OPTIMAL);
		assertEquals(carreLongueurNormale_T.run().getStatus(), Status.OPTIMAL);
		//A*
		assertEquals(carreLongueurNormaleL.run().getStatus(), Status.OPTIMAL);
		assertEquals(carreLongueurNormaleT.run().getStatus(), Status.OPTIMAL);
		//longueur 1 carre
		//Dijkstra
		assertEquals(carreLongueurUne_L.run().getStatus(), Status.OPTIMAL);
		assertEquals(carreLongueurUne_T.run().getStatus(), Status.OPTIMAL);
		//A*
		assertEquals(carreLongueurUneL.run().getStatus(), Status.OPTIMAL);
		assertEquals(carreLongueurUneT.run().getStatus(), Status.OPTIMAL);
		//longueur normale insa 
		//Dijkstra
		assertEquals(insaLongueurNormale_L.run().getStatus(), Status.OPTIMAL);
		assertEquals(insaLongueurNormale_T.run().getStatus(), Status.OPTIMAL);
		//A*
		assertEquals(insaLongueurNormaleL.run().getStatus(), Status.OPTIMAL);
		assertEquals(insaLongueurNormaleT.run().getStatus(), Status.OPTIMAL);
		//longueur 1 insa
		//Dijkstra
		assertEquals(insaLongueurUne_L.run().getStatus(), Status.OPTIMAL);
		assertEquals(insaLongueurUne_T.run().getStatus(), Status.OPTIMAL);
		//A*
		assertEquals(insaLongueurUneL.run().getStatus(), Status.OPTIMAL);
		assertEquals(insaLongueurUneT.run().getStatus(), Status.OPTIMAL);
		//Longueur nulle gua
		//Dijkstra
		assertEquals(guaLongueurNulle_L.run().getStatus(), Status.INFEASIBLE);
		assertEquals(guaLongueurNulle_T.run().getStatus(), Status.INFEASIBLE);
		//A*
		assertEquals(guaLongueurNulleL.run().getStatus(), Status.INFEASIBLE);
		assertEquals(guaLongueurNulleT.run().getStatus(), Status.INFEASIBLE);
		
	}
}
