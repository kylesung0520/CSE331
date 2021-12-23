package ub.cse.algo;

import java.util.ArrayList;
import java.util.HashMap;

public class Solution {

    private int _startNode;
    private int _endNode;
    private HashMap<Integer, ArrayList<Integer>> graph;
    public Solution(int startNode, int endNode, HashMap<Integer, ArrayList<Integer>> g){
        _startNode = startNode;
        _endNode = endNode;
        graph = g;
    }

    public ArrayList<Integer> outputPath() {
        /*
         * Find the smallest weighted path between _startNode and _endNode
         * The first number of graph's adjacency list is the weight of it's node
         */
        ArrayList<Integer> retVal = new ArrayList<Integer>();
            HashMap<Integer, Boolean> cc = new HashMap<>();
            for(int i = 0; i < graph.size(); i++){
                cc.put(i,false);
            }
            ArrayList<Integer> start = new ArrayList<>();
            HashMap<Integer, ArrayList<Integer>> lvl = new HashMap<>();
            int level = 0;
            cc.put(_startNode, true);
            start.add(_startNode);
            lvl.put(0, start);

            while(!lvl.get(level).isEmpty()){
                ArrayList<Integer> nextL = new ArrayList<>();
                for(Integer nextLV : lvl.get(level)){
                    for(int j = 1; j < graph.get(nextLV).size(); j++){
                        int v = graph.get(nextLV).get(j);
                        if(cc.get(v)==false){
                            cc.put(v, true);
                            nextL.add(v);
                        }
                    }
                }
                level++;
                lvl.put(level, nextL);
            }

            ArrayList<Integer> costs = new ArrayList<>();
            ArrayList<Integer> parents = new ArrayList<>();
            for(int i = 0; i < graph.size(); i++){
                costs.add(0);
                parents.add(-1);
            }
            costs.set(_startNode, graph.get(_startNode).get(0));
            parents.set(_startNode, _startNode);
            System.out.println(costs);
            System.out.println(parents);
            int tmplvl = 0;
            System.out.println(lvl.get(tmplvl).size());
            while(tmplvl <= lvl.size()-1) {
                for (int i = 0; i < lvl.get(tmplvl).size(); i++) {
                    int n = lvl.get(tmplvl).get(i);
                    for (int j = 1; j < graph.get(n).size(); j++) {
                        int n2 = graph.get(n).get(j);
                        if (lvl.get(tmplvl).contains(n2) || lvl.get(tmplvl + 1).contains(n2)) {
                            int costOfN = costs.get(n);
                            int costOfN2 = costs.get(n2);

                            if (costOfN2 == 0){
                                costs.set(n2, costOfN + graph.get(n2).get(0));
                                parents.set(n2, n);
                            }
                            else if(costOfN2 > costOfN + graph.get(n2).get(0)){
                                costs.set(n2, costOfN + graph.get(n2).get(0));
                                parents.set(n2, n);
                            }
                        }
                    }
                }
                tmplvl++;
            }
            return retVal;
    }
}

