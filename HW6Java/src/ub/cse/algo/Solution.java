package ub.cse.algo;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * For use in CSE 331
 */
public class Solution {

    private ArrayList<ArrayList<Integer>> adj_matrix;

    public Solution(ArrayList<ArrayList<Integer>> adj_matrix) {
        this.adj_matrix = adj_matrix;
    }

    public int[] outputEdges() {
        /*
         * Output the node ids of the smallest weighted path.
         */

        int retVal[] = new int[adj_matrix.size()]; //parent of each vertex
        int minCost[] = new int[adj_matrix.size()]; //minimum cost of each vertex
        int[] Vnodes = new int[adj_matrix.size()]; //check the vertex has been visited or not

        for(int i = 0; i < adj_matrix.size(); i++){
            minCost[i] = 51;
            Vnodes[i] = 0;
        }
        minCost[0] = 0;
        retVal[0] = -1;

        for(int i = 0; i < adj_matrix.size()-1; i++){
            int tmpMin = 51, tmpNode = -1;

            for(int j = 0; j < adj_matrix.size(); j++){
                if(Vnodes[j] == 0 && minCost[j] < tmpMin){
                    tmpMin = minCost[j];
                    tmpNode = j;
                }
            }
            int u = tmpNode;
            Vnodes[u] = 1;

            for(int w = 0; w < adj_matrix.size(); w++){
                if(adj_matrix.get(u).get(w) != -1 && Vnodes[w] == 0 && adj_matrix.get(u).get(w) < minCost[w]){
                    retVal[w] = u;
                    minCost[w] = adj_matrix.get(u).get(w);
                }
            }
        }
        return retVal;
    }
}
