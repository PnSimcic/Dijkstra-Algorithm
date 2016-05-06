/**
 * Dijkstra.java
 * 
 * This class creates constructors for edges and vertices and uses
 * dijkstras algorithm to solve shortest paths between them.
 */


import java.util.*;

public class Dijkstra {
	private String start;
	private String end;
	private int k;
	
	//Constructor for Edge
	public class Edge{
		public Vertex target;
		public int weight;
		
		public Edge(Vertex argTarget, int argWeight){
			target = argTarget;
			weight = argWeight;
		}
		
	}
	//Constructor for Vertex
	public class Vertex implements Comparable<Vertex>{
		public String name;
		public Edge[] adjacencies;
		public double minDistance = Double.POSITIVE_INFINITY;
		public Vertex previous;
		
		public Vertex(String argName){
			name = argName;
		}
		public String toString(){
			return name;
		}
		public int compareTo(Vertex other){
			return Double.compare(minDistance, other.minDistance);
		}
		
	}
	/*
	 * Does dijkstras algorithm which
	 * just computes the shortest path between two
	 * vertices according to the weight of each
	 */
	public void computePaths(Vertex source){
		source.minDistance = 0;
		PriorityQueue<Vertex> vQueue = new PriorityQueue<Vertex>();
		vQueue.add(source);
		
		while(!vQueue.isEmpty()){
			Vertex u = vQueue.poll();
			
			for(Edge a: u.adjacencies){
				Vertex v = a.target;
				double weight = a.weight;
				double distanceToU = u.minDistance + weight;
				
				if(distanceToU < v.minDistance){
					vQueue.remove(v);
					v.minDistance = distanceToU;
					v.previous = u;
					vQueue.add(v);
				}
			}
		}
	}
	
	public static List <Vertex> getShortestPathTo(Vertex target){
		List <Vertex> path = new ArrayList <Vertex>();
		for (Vertex vertex = target; vertex != null; vertex = vertex.previous){
			path.add(vertex);
			
		}
		Collections.reverse(path);
		return path;
	}
	/*
	 * Runs the program and instantiates all values from
	 * existing data
	 */
	public void run(){
		Scanner in = new Scanner(System.in);
		System.out.println("Please enter a starting vertex: ");
		start = in.nextLine();
		System.out.println("Please enter an ending vertex: ");
		end = in.nextLine();
		
		Vertex A = new Vertex("A");
		Vertex B = new Vertex("B");
		Vertex C = new Vertex("C");
		Vertex D = new Vertex("D");
		Vertex E = new Vertex("E");
		Vertex F = new Vertex("F");
		Vertex G = new Vertex("G");
		
		A.adjacencies = new Edge[]{
				new Edge(B, 2),
				new Edge(F, 6)
		};
		B.adjacencies = new Edge[]{
				new Edge(A, 2),
				new Edge(D, 9)		
		};
		C.adjacencies = new Edge[]{
				new Edge(D, 3),
				new Edge(E, 4)		
		};
		D.adjacencies = new Edge[]{
				new Edge(B, 9),
				new Edge(F, 3),
				new Edge(C, 3),
				new Edge(G, 5),
				new Edge(E, 10)
		};
		E.adjacencies = new Edge[]{
				new Edge(C, 4),
				new Edge(D, 10)		
		};
		F.adjacencies = new Edge[]{
				new Edge(A, 6),
				new Edge(B, 3),
				new Edge(D, 3),
				new Edge(G, 1)
		};
		G.adjacencies = new Edge[]{
				new Edge(D, 5),
				new Edge(F, 1)		
		};
		
		Vertex [] vertices = 
			{A, B, C, D, E, F, G};
		if(start.equals("A")){
			computePaths(A);
		}
		if(start.equals("B")){
			computePaths(B);
		}
		if(start.equals("C")){
			computePaths(C);
		}
		if(start.equals("D")){
			computePaths(D);
		}
		if(start.equals("E")){
			computePaths(E);
		}
		if(start.equals("F")){
			computePaths(F);
		}
		if(start.equals("G")){
			computePaths(G);
		}
		if(end.equals("A")){
			k = 0;
		}
		if(end.equals("B")){
			k = 1;
		}
		if(end.equals("C")){
			k = 2;
		}
		if(end.equals("D")){
			k = 3;
		}
		if(end.equals("E")){
			k = 4;
		}
		if(end.equals("F")){
			k = 5;
		}
		if(end.equals("G")){
			k = 6;
		}
		
		System.out.println("Distance to " + end + ": "
				+ vertices[k].minDistance);
		List<Vertex> path = getShortestPathTo(vertices[k]);
		System.out.println("Path: " + path);
		
	}
}
