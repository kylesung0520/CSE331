package ub.cse.algo;

import java.util.ArrayList;
public class Solution {

    private int _n_battles;
    private ArrayList<int[]> _battles;
    
    public Solution (int n_battles, ArrayList<int[]> battles){
        _n_battles = n_battles;
        _battles = battles;
    }

    /**
     * You should fill this in on your own. battles is list of tuples, in the form (battle duration, battle deadline).
     * Your output should also be a list of tuples, of the form (battle id, start time of battle). If no possible
     * schedule exists, you should return an empty list.
     * @return
     */

    //divide the list into half in order to sort and conquer
    public ArrayList<int[]> dividing(ArrayList<int[]> orig, int start, int end){
        ArrayList<int[]> divided =  new ArrayList<int[]>();
        for(int i = start; i < end; i++){
            divided.add(orig.get(i));
        }
        return divided;
    }

    //sort and conquer the divided lists.
    public ArrayList<int[]> sorting(ArrayList<int[]> lowA, ArrayList<int[]> highA){
        ArrayList<int[]> sortedArr = new ArrayList<int[]>();
        int mid = 0, low = 0, high = 0;
        while(low < lowA.size() && high < highA.size()){
            if(lowA.get(low)[2] < highA.get(high)[2]){
                sortedArr.add(mid++, lowA.get(low++));
            }
            else{
                sortedArr.add(mid++, highA.get(high++));
            }
        }
        while(low < lowA.size()){
            sortedArr.add(mid++, lowA.get(low++));
        }
        while(high < highA.size()){
            sortedArr.add(mid++, highA.get(high++));
        }
        return sortedArr;
    }

    //Merge sort which takes O(nlogn)time
    public ArrayList<int[]> mergeSort(ArrayList<int[]> presortArr){
        if(presortArr.size() >= 2){
            int mid = presortArr.size()/2;
            ArrayList<int[]> low_arr = mergeSort(dividing(presortArr, 0, mid));
            ArrayList<int[]> high_arr = mergeSort(dividing(presortArr, mid, presortArr.size()));
            ArrayList<int[]> retVal = sorting(low_arr, high_arr);
            return retVal;
        }
        else {
            return presortArr;
        }
    }
    public ArrayList<int[]> getSchedule(){
        ArrayList<int[]> schedule = new ArrayList<>();

        if(_n_battles != _battles.size()){
            return schedule;
        }
        else {
            ArrayList<int[]> presorted = new ArrayList<>();
            int startT = 0;
            for (int i = 0; i < _n_battles; i++) {
                int[] a = new int[]{i, _battles.get(i)[0],_battles.get(i)[1]}; //ID, Duration and DeadLine (0,1,2)
                presorted.add(a);
            }

            ArrayList<int[]> sorted = mergeSort(presorted); //ID, Duration and DeadLine

            for (int i = 0; i < _n_battles; i++) {
                if ((startT + sorted.get(i)[1]) <= sorted.get(i)[2]) {
                    int[] output = new int[]{sorted.get(i)[0], startT}; //ID and Strat time
                    schedule.add(output);
                    startT += sorted.get(i)[1];

                } else {
                    schedule = new ArrayList<>();
                    break;
                }
            }
            return schedule;
        }
    }
}
