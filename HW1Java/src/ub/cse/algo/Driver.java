package ub.cse.algo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

/**
 * The driver for homework one. CSE 331
 */
public class Driver {

    // The following variables might need to be changed.
    private static String inputFilename;

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Please provide the input filepath as the first argument");
            return;
        }

        inputFilename = args[0];

        PreferenceLists prefLists = new HW1Utility().readFile(inputFilename);

        Solution student = new Solution(prefLists.getMen().size(), prefLists.getMen(), prefLists.getWomen(), prefLists.getRequiredPairMen(), prefLists.getRequiredPairWomen());
        ArrayList<Matching> studentSolution = student.outputStableMatchings();

        Collections.sort(studentSolution);
        //System.out.println("Your solution:");
        //System.out.println("=======================================================================================================");
        System.out.println(studentSolution.size());
        for (ArrayList<Marriage> match : studentSolution) {
            System.out.println(match.toString());

        }
    }

}
