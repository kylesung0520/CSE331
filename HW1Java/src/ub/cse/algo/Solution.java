package ub.cse.algo;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * For use in CSE 331 HW1.
 * This is the class you will be editing and turning in. It will be timed against our implementation
 * NOTE that if you declare this file to be in a package, it will not compile in Autolab
 */
public class Solution {

    private int _numberOfMenAndWomen;
    private int _required_pair_man, _required_pair_woman;


    // The following represent the preference list for the men and women.
    // The KEY represents the integer representation of a given man or woman.
    // The VALUE is a list, from most preferred to least, of the member of the opposite gender.
    private HashMap<Integer, ArrayList<Integer>> men;
    private HashMap<Integer, ArrayList<Integer>> women;
    private ArrayList<Matching> _stableMatchings = new ArrayList<>();

    /**
     * The constructor simply sets up the necessary data structures.
     * The grader for the homework will first call this class and pass the necessary variables.
     * There is no need to edit this constructor.
     *
     * @param n The number of men/women.
     * @param men A map linking each man (integer value) with their preference list.
     * @param women A map linking each woman (integer value) with their preference list.
     * @param required_pair_man the man in the requried pair
     * @param required_pair_woman the woman in the required pair
     */
    public Solution(int n, HashMap<Integer, ArrayList<Integer>> men, HashMap<Integer, ArrayList<Integer>> women, int required_pair_man, int required_pair_woman){
        this._numberOfMenAndWomen = n;
        this.men = men;
        this.women = women;
        this._required_pair_man = required_pair_man;
        this._required_pair_woman = required_pair_woman;
    }

     /**
     * This method must be filled in by you. You may add other methods and subclasses as you see fit,
     * but they must remain within the Solution class.
     * @return Your set of stable matches. Order does not matter.
     */
    public ArrayList<Matching> outputStableMatchings() {

            /* The code below just calls the allPermutations function, and then just prints all permutattions*/
            /* To compare your code's output with the sample outpout you need to comment out the part about printing the permutations*/

            ArrayList<ArrayList<Integer>> listOfAllPermutations = new ArrayList<>();
            listOfAllPermutations = allPermutations(_numberOfMenAndWomen);

            Marriage required = new Marriage(_required_pair_man,_required_pair_woman);

            for(ArrayList<Integer> set : listOfAllPermutations){
                ArrayList<Marriage> marriageL = new ArrayList<Marriage>();
                for(int i = 0; i < _numberOfMenAndWomen; i++){
                    Marriage marriage = new Marriage(set.get(i), i+1);
                    marriageL.add(marriage);
                }

                Matching d = new Matching(marriageL);

                if(d.contains(required)){
                        for(int i = 0; i < d.size(); i++){
                            int sw = 0;
                            for(int j = 0; j < d.size(); j++){
                                if(i != j){
                                   int w = men.get(d.get(i).man).indexOf(d.get(i).woman);
                                   int wP = men.get(d.get(i).man).indexOf(d.get(j).woman);
                                   int m = women.get(d.get(j).woman).indexOf(d.get(i).man);
                                   int mP = women.get(d.get(j).woman).indexOf(d.get(j).man);
                                   if(w > wP && mP > m){
                                       sw = 1;
                                       break;
                                   }
                                }
                            }
                            if(sw == 1){
                                _stableMatchings.remove(d);
                                break;
                            }
                            else{
                                if(!_stableMatchings.contains(d)){
                                    _stableMatchings.add(d);
                                }
                            }
                        }
                }
            }
            /*allPermutations call done*/
        return _stableMatchings;
    }
	    /**
     * Generates all permutations.
     * Just a wrapper function to call permutate
     */
     private ArrayList<ArrayList<Integer>> allPermutations(int n){
            ArrayList<Integer> start = new ArrayList<Integer>();
            for(int k = 1; k<=_numberOfMenAndWomen; ++k) {
                start.add(k);
            }
            ArrayList<ArrayList<Integer>> allPermuts= new ArrayList<>();
            permutate(start,allPermuts,n); // Once this call returns the list of all permutations will be in "allPermuts"

            return allPermuts;
     }

    /**
     * This method generates all of the permutations of the input for you.
     * Implements Jeap's algorithm.
     * @param set A complete matching set, not necesarrily stable
     * @param listOfpermut Current of of all opermutations that have been generated so far. This would be updated by ref
     * @param length length of the set
     */
    private void permutate(ArrayList<Integer> set, ArrayList<ArrayList<Integer>> listOfPermut, int length){
        if(length == 1){
            //System.out.println(set);
            //Have to deep copy the current matching so that next call of Heap's does not over-write the current matching
            ArrayList<Integer> cloneSet = new ArrayList<>();
            for(int i = 0; i < set.size(); i++){
                cloneSet.add(set.get(i));
            }
            listOfPermut.add(cloneSet); 
        }
        else{
            for(int i = 0; i < length; i++){
                permutate(set, listOfPermut, length - 1);
                int j = (length % 2 == 0 ) ? i : 0;
                Integer t = set.get(length-1);
                set.set(length-1, set.get(j));
                set.set(j, t);
            }
        }
    }

}
