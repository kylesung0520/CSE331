package ub.cse.algo;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

/**
 * Class for running the grader. Will take in a command line argument specifying
 * the number of testcases to run.
 */
public class Driver {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Please provide the number of runs as the argument");
        }

	    String inputFilename = args[0];
        ArrayList<int[]> listOfBattles = readFile(inputFilename);
        
        Solution student = new Solution(listOfBattles.size(), listOfBattles);
        ArrayList<int[]> studentSolution = student.getSchedule();
	
        //System.out.println("Your solution:");
        //System.out.println("=======================================================================================================");
        System.out.print("[");        
        for (int i = 0; i < studentSolution.size(); i++) {            
            System.out.print("(" + studentSolution.get(i)[0] + ", " + studentSolution.get(i)[1]+")");
            if (i < studentSolution.size() - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
        //System.out.println("=======================================================================================================");
  
    }

    public static ArrayList<int[]> readFile(String inputFilePath) {
            String numberOfBattles;
            String temp;
            ArrayList<int[]> listOfBattles = new ArrayList<int[]>();
            BufferedReader bufferedReader = null;
            
            try {
                bufferedReader = new BufferedReader(new FileReader(inputFilePath));
            }
            catch (FileNotFoundException e) {
                System.err.println("Unable to open the file " + inputFilePath);
                e.printStackTrace();
            }
            
            try{
                numberOfBattles = bufferedReader.readLine();
                for(int i = 1; i <= Integer.parseInt(numberOfBattles); i++){
                    temp = bufferedReader.readLine();
                    String[] battleInString = temp.split("\\s+");
                    int[] battle = new int[2];
                    battle[0] = Integer.parseInt(battleInString[0]);
                    battle[1] = Integer.parseInt(battleInString[1]);
                    listOfBattles.add(battle);
                }
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            
            return listOfBattles;
     }
}
