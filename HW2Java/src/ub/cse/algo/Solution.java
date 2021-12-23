package ub.cse.algo;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.ArrayList;

/**
 * For use in CSE 331 HW1.
 * This is the class you will be editing and turning in. It will be timed against our implementation
 * NOTE that if you declare this file to be in a package, it will not compile in Autolab
 */

public class Solution {
	private int _nHospital;
	private int _nStudent;

    // The following represent the preference list of hospitals and students.
    // The KEY represents the integer representation of a given hospital or student.
    // The VALUE is a list, from most preferred to least.
    // For hospital, first element of the list is number of available slots
	private HashMap<Integer, ArrayList<Integer>> _hospitalList;
	private HashMap<Integer, ArrayList<Integer>> _studentList;
    
    
    /**
     * The constructor simply sets up the necessary data structures.
     * The grader for the homework will first call this class and pass the necessary variables.
     * There is no need to edit this constructor.
     * @param m Number of hospitals
     * @param n Number of students
     * @param A map linking each hospital with its preference list
     * @param A map linking each student with their preference list
     * @return
     */
	public Solution(int m, int n, HashMap<Integer, ArrayList<Integer>> hospitalList, HashMap<Integer, ArrayList<Integer>> studentList) {
		_nHospital = m;
		_nStudent = n;
		_hospitalList = hospitalList;
		_studentList = studentList;
	}
    
    /**
     * This method must be filled in by you. You may add other methods and subclasses as you see fit,
     * but they must remain within the HW1_Student_Solution class.
     * @return Your stable matches
     */
	/*s is assigned to h, and
	  sp is assigned to no hospital, and
	  h prefers sp to s.*/

	public ArrayList<Match> getMatches() {
		// Returns an empty ArrayList for now
		ArrayList<Match> retVal = new ArrayList<Match>();
		ArrayList<Integer> chosenS = new ArrayList<Integer>();

		int nSlots = 0;

		Match c = new Match(0,0);
		for(ArrayList<Integer> z : _hospitalList.values()){
			nSlots += z.get(0);
		}

		int pref = 0;
		int tempP = 0;
		while(nSlots > 0) {
			tempP += 1;
			pref = tempP;
			for (int i = 1; i <= _nHospital; i++) {
				if (_hospitalList.get(i).get(0) != 0) {
					int s = _hospitalList.get(i).get(pref);
					if (!chosenS.contains(s)) {
						c = new Match(i, s);
						chosenS.add(s);
						retVal.add(c);
						_hospitalList.get(i).add(0, _hospitalList.get(i).remove(0) - 1);
						nSlots -= 1;
					}
					else {
						for (int j = 0; j < retVal.size(); j++) {
							if (retVal.get(j).student == s) {
								if (_studentList.get(s).indexOf(retVal.get(j).hospital) > _studentList.get(s).indexOf(i)) {
									c = new Match(i, s);
									retVal.add(c);
									_hospitalList.get(i).add(0, _hospitalList.get(i).remove(0) - 1);
									_hospitalList.get(retVal.get(j).hospital).add(0, _hospitalList.get(retVal.get(j).hospital).remove(0) + 1);
									retVal.remove(j);
								}
							}
						}
					}
				}
				for(int i = 0; i < retVal.size(); i++){
					for(int j = 0; j < retVal.size(); j++){
						if(j != i){
							int s = _hospitalList.get(retVal.get(i).hospital).indexOf(retVal.get(i).student);
							int sp = _hospitalList.get(retVal.get(i).hospital).indexOf(retVal.get(j).student);
							int h = _studentList.get(retVal.get(j).student).indexOf(retVal.get(i).hospital);
							int hp = _studentList.get(retVal.get(j).student).indexOf(retVal.get(j).hospital);
							if(s > sp && h < hp){

							}
						}
					}
				}
			}
		}
		int sw2 = 0;
		for(int i = 1; i <= _nStudent; i++){
			if(!chosenS.contains(i)){
				for(Match aa : retVal){
					int s = _hospitalList.get(aa.hospital).indexOf(aa.student);
					int sp = _hospitalList.get(aa.hospital).indexOf(i);
					if(s > sp){
						sw2 = 1;
					}
					else{
						sw2 = 0;
					}
				}
			}
			if(sw2 == 0){
				System.out.println("Instability1 : GOOD");
			}
			else{
				System.out.println("Instability1 : Unstable");
			}
		}
		for(int i = 0; i < retVal.size(); i++){
			int sw = 0;
			for(int j = 0; j < retVal.size(); j++){
				if(j != i){
					int s = _hospitalList.get(retVal.get(i).hospital).indexOf(retVal.get(i).student);
					int sp = _hospitalList.get(retVal.get(i).hospital).indexOf(retVal.get(j).student);
					int h = _studentList.get(retVal.get(j).student).indexOf(retVal.get(i).hospital);
					int hp = _studentList.get(retVal.get(j).student).indexOf(retVal.get(j).hospital);
					if(s > sp && h < hp){
						sw = 1;
					}
				}
			}
			if(sw == 0){
				System.out.println("Instability2 : GOOD");
			}
			else{
				System.out.println("Instability2 : Unstable");
			}
		}
		return retVal;
	}
}
