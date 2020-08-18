package graph.h.handson.aug182020;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CriticalConnections {
	public static void main(String[] args) {
		int[][] connections = {{0, 1}, {1, 2}, {2, 0}, {1, 3}};
		// int[][] connections = {{0, 1}};
		List<List<Integer>> listConnections = new ArrayList<List<Integer>>();
		for (int[] arr : connections) {
			List<Integer> pairConnection = new ArrayList<Integer>();
			for (int p : arr) {
				pairConnection.add(p);
			}
			listConnections.add(pairConnection);
		}

		CriticalConnections criticalConnections = new CriticalConnections();

		List<List<Integer>> edges = criticalConnections.criticalConnections(4,
				listConnections);
		for (List<Integer> e : edges) {
			System.out.println(e);
		}
	}

	public void printGraph(List<Integer>[] graph) {
		for (int nodeNumber = 0; nodeNumber < graph.length; nodeNumber++) {
			System.out.println("node numeber->" + nodeNumber
					+ " connected Nodes: " + graph[nodeNumber]);
		}
	}

	int time;
	boolean[] visited;
	// It mentioned sequence of nodes in graph
	int[] discoveredTime;
	// It is useful for identify circular path in graph
	int[] lowestVertex;
	// Graph Source as Array Index and List of Array Index as neighbour
	List<Integer>[] graph;
	List<List<Integer>> criticalConns;

	public void init(int n) {
		time = 0;
		visited = new boolean[n];
		discoveredTime = new int[n];
		lowestVertex = new int[n];
		Arrays.fill(discoveredTime, -1);
		Arrays.fill(lowestVertex, -1);
		Arrays.fill(visited, false);
		graph = new ArrayList[n];
		// Fill with empty List as neighbour
		for (int i = 0; i < n; i++) {
			graph[i] = new ArrayList<Integer>();
		}
		criticalConns = new ArrayList<List<Integer>>();
	}

	public void buildGraph(List<List<Integer>> connections) {

		for (List<Integer> srcTarNodes : connections) {
			graph[srcTarNodes.get(0)].add(srcTarNodes.get(1));
			graph[srcTarNodes.get(1)].add(srcTarNodes.get(0));
		}
	}

	public List<List<Integer>> criticalConnections(int n,
			List<List<Integer>> connections) {
		init(n);
		buildGraph(connections);
		findCriticalConnections(0, -1);
		return criticalConns;
	}

	public void findCriticalConnections(int current, int parent) {

		visited[current] = true;
		discoveredTime[current] = lowestVertex[current] = time++;
		for (Integer neighbor : graph[current]) {
			// It is a base condition, if neighbor is a parent then do not
			// perform any action.
			if (neighbor == parent)
				continue;
			if (!visited[neighbor]) {
				findCriticalConnections(neighbor, current);
				// When dfs callbacks, if there is cycle in the graph, then
				// current node should check its lowest with neighbor's lowest
				// vertex count.
				lowestVertex[current] = Math.min(lowestVertex[current],
						lowestVertex[neighbor]);

				// All the neighbor should have on same low vertex count and
				// that is nothing but the lowest discovered time in the cycle
				// of those nodes. So if there is no cycle formation in
				// neighbor, then it means, this neighbor have a higher lowest
				// vertex count then current node. S it is a critical connection
				// in a network.
				if (lowestVertex[neighbor] > discoveredTime[current]) {
					criticalConns.add(Arrays.asList(current, neighbor));
				}
			} else {
				// This is place where current node get the lowest vertex count
				// from neighbor node. That is why, current node should compare
				// its lowest vertex with visited node's distance value. If
				// there is cycle formation then definitely one of the node
				// should have the low distance then the current node's lowest
				// vertex count.
				lowestVertex[current] = Math.min(lowestVertex[current],
						discoveredTime[neighbor]);
			}
		}
	}
}
