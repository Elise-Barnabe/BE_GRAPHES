package org.insa.graphs.algorithm.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;

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
	
	protected static String mapCarre, mapInsa;
	protected static DijkstraAlgorithm carreLongueurNulle_L, carreLongueurNulle_T, carreLongueurNormale_L, carreLongueurNormale_T, insaLongueurNulle_L, insaLongueurNulle_T, insaLongueurNormale_L, insaLongueurNormale_T;
	protected static AStarAlgorithm carreLongueurNulleL, carreLongueurNulleT, carreLongueurNormaleL, carreLongueurNormaleT, insaLongueurNulleL, insaLongueurNulleT, insaLongueurNormaleL, insaLongueurNormaleT; 
	protected static BellmanFordAlgorithm bcarreLongueurNulleL, bcarreLongueurNulleT, bcarreLongueurNormaleL, bcarreLongueurNormaleT, binsaLongueurNulleL, binsaLongueurNulleT, binsaLongueurNormaleL, binsaLongueurNormaleT;  
	
	@BeforeClass
	public static void init() throws IOException {
		//chargement des chemins des cartes
		mapCarre = "C:\\Users\\elise\\OneDrive\\Bureau\\ELISE\\INSA\\3A\\GRAPHES\\Maps\\carre.mapgr";
		mapInsa = "C:\\Users\\elise\\OneDrive\\Bureau\\ELISE\\INSA\\3A\\GRAPHES\\Maps\\insa.mapgr";
		
		//creation des reader pour lire les cartes
		final GraphReader readCarre = new BinaryGraphReader(new DataInputStream(new BufferedInputStream(new FileInputStream(mapCarre))));
		final GraphReader readInsa = new BinaryGraphReader(new DataInputStream(new BufferedInputStream(new FileInputStream(mapInsa))));
		
		
		//on lit les cartes et on recupere les infos dans graph
		final Graph carre = readCarre.read();
		final Graph insa = readInsa.read();
		
		//fermeture des reader
		readCarre.close();
		readInsa.close();
		
		//recuperation des arc inspector
		final ArcInspector length = ArcInspectorFactory.getAllFilters().get(0);
		final ArcInspector time = ArcInspectorFactory.getAllFilters().get(2);
		
		//Scenarios
		//Pour la carte carre
		//chemin de longueur nulle en longueur
		final ShortestPathData longueurNulleLengthCarre = new ShortestPathData(carre, carre.getNodes().get(1), insa.getNodes().get(2), length);
		//chemin de longueur nulle en temps
		final ShortestPathData longueurNulleTimeCarre = new ShortestPathData(carre, carre.getNodes().get(1), insa.getNodes().get(1), time);
		//chemin de longueur normale
		final ShortestPathData longueurNormaleLengthCarre = new ShortestPathData(carre, carre.getNodes().get(1), carre.getNodes().get(9), length);
		final ShortestPathData longueurNormaleTimeCarre = new ShortestPathData(carre, carre.getNodes().get(1), carre.getNodes().get(9), time);
		
		//Pour la carte insa
		//chemin de longueur nulle
		final ShortestPathData longueurNulleLengthInsa = new ShortestPathData(insa, insa.getNodes().get(1), carre.getNodes().get(1), length);
		final ShortestPathData longueurNulleTimeInsa = new ShortestPathData(insa, insa.getNodes().get(1), carre.getNodes().get(1), time);
		//chemin de longueur normale 
		final ShortestPathData longueurNormaleLengthInsa = new ShortestPathData(insa, insa.getNodes().get(1), insa.getNodes().get(106), length);
		final ShortestPathData longueurNormaleTimeInsa = new ShortestPathData(insa, insa.getNodes().get(1), insa.getNodes().get(106), time);
	
		//Calcul des chemins via Dijkstra
		carreLongueurNulle_L = new DijkstraAlgorithm(longueurNulleLengthCarre);
		carreLongueurNulle_T = new DijkstraAlgorithm(longueurNulleTimeCarre);
		carreLongueurNormale_L = new DijkstraAlgorithm(longueurNormaleLengthCarre);
		carreLongueurNormale_T = new DijkstraAlgorithm(longueurNormaleTimeCarre);
		
		insaLongueurNulle_L = new DijkstraAlgorithm(longueurNulleLengthInsa);
		insaLongueurNulle_T = new DijkstraAlgorithm(longueurNulleTimeInsa);
		insaLongueurNormale_L = new DijkstraAlgorithm(longueurNormaleLengthInsa);
		insaLongueurNormale_T = new DijkstraAlgorithm(longueurNormaleTimeInsa);
		
		//Calcul des chemins via A*
		carreLongueurNulleL = new AStarAlgorithm(longueurNulleLengthCarre);
		carreLongueurNulleT = new AStarAlgorithm(longueurNulleTimeCarre);
		carreLongueurNormaleL = new AStarAlgorithm(longueurNormaleLengthCarre);
		carreLongueurNormaleT = new AStarAlgorithm(longueurNormaleTimeCarre);
		
		insaLongueurNulleL = new AStarAlgorithm(longueurNulleLengthInsa);
		insaLongueurNulleT = new AStarAlgorithm(longueurNulleTimeInsa);
		insaLongueurNormaleL = new AStarAlgorithm(longueurNormaleLengthInsa);
		insaLongueurNormaleT = new AStarAlgorithm(longueurNormaleTimeInsa);
		
		//Calcul des chemins via Bellman Ford
		bcarreLongueurNulleL = new BellmanFordAlgorithm(longueurNulleLengthCarre);
		bcarreLongueurNulleT = new BellmanFordAlgorithm(longueurNulleTimeCarre);
		bcarreLongueurNormaleL = new BellmanFordAlgorithm(longueurNormaleLengthCarre);
		bcarreLongueurNormaleT = new BellmanFordAlgorithm(longueurNormaleTimeCarre);
		
		binsaLongueurNulleL = new BellmanFordAlgorithm(longueurNulleLengthInsa);
		binsaLongueurNulleT = new BellmanFordAlgorithm(longueurNulleTimeInsa);
		binsaLongueurNormaleL = new BellmanFordAlgorithm(longueurNormaleLengthInsa);
		binsaLongueurNormaleT = new BellmanFordAlgorithm(longueurNormaleTimeInsa);
	}
	
	@Test
	public void testIsValid() {
		//verification validité chemins dijkstra
		assertTrue(carreLongueurNulle_L.run().getPath().isValid());
		assertTrue(carreLongueurNulle_T.run().getPath().isValid());
		assertTrue(carreLongueurNormale_L.run().getPath().isValid());
		assertTrue(carreLongueurNormale_T.run().getPath().isValid());
		assertTrue(insaLongueurNulle_L.run().getPath().isValid());
		assertTrue(insaLongueurNulle_T.run().getPath().isValid());
		assertTrue(insaLongueurNormale_L.run().getPath().isValid());
		assertTrue(insaLongueurNormale_T.run().getPath().isValid());
		
		//verification validité A*
		assertTrue(carreLongueurNulleL.run().getPath().isValid());
		assertTrue(carreLongueurNulleT.run().getPath().isValid());
		assertTrue(carreLongueurNormaleL.run().getPath().isValid());
		assertTrue(carreLongueurNormaleT.run().getPath().isValid());
		assertTrue(insaLongueurNulleL.run().getPath().isValid());
		assertTrue(insaLongueurNulleT.run().getPath().isValid());
		assertTrue(insaLongueurNormaleL.run().getPath().isValid());
		assertTrue(insaLongueurNormaleT.run().getPath().isValid());
	}
		
	@Test
	public void testEgaliteSolution() {
		//longueur nulle en length carre
		//Dijkstra
		assertEquals(0,carreLongueurNulle_L.run().getPath().getLength(),0);
		//A*
		assertEquals(0,carreLongueurNulleL.run().getPath().getLength(), 0);
		//longueur nulle en temps carre
		//Dijkstra
		assertEquals(0,carreLongueurNulle_T.run().getPath().getMinimumTravelTime(),0);
		//A*
		assertEquals(0,carreLongueurNulleT.run().getPath().getMinimumTravelTime(),0);
		//longueur nulle en length insa 
		//Dijkstra
		assertEquals(0,insaLongueurNulle_L.run().getPath().getLength(),0);
		//A*
		assertEquals(0,insaLongueurNulleL.run().getPath().getLength(), 0);
		//longueur nulle en temps insa
		//Dijkstra
		assertEquals(0,insaLongueurNulle_T.run().getPath().getMinimumTravelTime(),0);
		//A*
		assertEquals(0,insaLongueurNulleT.run().getPath().getMinimumTravelTime(),0);
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
		
		
		
	}

}
