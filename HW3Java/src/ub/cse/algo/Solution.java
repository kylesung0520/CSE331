package ub.cse.algo;

import java.util.ArrayList;
import java.util.HashMap;

public class Solution {
	  private int startNode;
	  private HashMap<Integer, ArrayList<Integer>> graph;
	  public Solution(int node, HashMap<Integer, ArrayList<Integer>> g){
	    startNode = node;
	    graph = g;
	  }

	  public int[] outputDistances(){
		int[] a = new int[graph.size()];
		HashMap<Integer, Boolean> cc = new HashMap<>();
		for(int i = 0; i < graph.size(); i++){
			cc.put(i, false);
		}

		ArrayList<Integer> start = new ArrayList<Integer>();
		HashMap<Integer, ArrayList<Integer>> level = new HashMap<Integer, ArrayList<Integer>>();
		int i = 0;
		cc.put(startNode, true);
		start.add(startNode);
		level.put(0, start);

		  while(!level.get(i).isEmpty()){
		  	ArrayList<Integer> nextL = new ArrayList<Integer>();
		  	for(Integer nextLV : level.get(i)){
		  		for(Integer V : graph.get(nextLV)){
					if(cc.get(V) == false){
						cc.put(V, true);
						nextL.add(V);
						a[V] = i+1;
					}
				}
			}
				i++;
				level.put(i, nextL);
		  }

	    return a;
	  }
}
