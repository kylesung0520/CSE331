package ub.cse.algo;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Wrapper class for objects given to students.
 */
public class PreferenceLists {

    private HashMap<Integer, ArrayList<Integer>> men;
    private HashMap<Integer, ArrayList<Integer>> women;
    int required_pair_man, required_pair_woman;

    public PreferenceLists(HashMap<Integer, ArrayList<Integer>> men,
                           HashMap<Integer, ArrayList<Integer>> women,
                           int required_pair_man,
                           int required_pair_woman) {
        this.men = men;
        this.women = women;
        this.required_pair_man = required_pair_man;
        this.required_pair_woman = required_pair_woman;
    }

    /**
     * Get the map containing the men's preference lists
     * 
     * @return map of men's preference list
     */
    public HashMap<Integer, ArrayList<Integer>> getMen() {
        return men;
    }

    /**
     * Get the map containing the women's preference lists
     * 
     * @return map of women's preference list
     */
    public HashMap<Integer, ArrayList<Integer>> getWomen() {
        return women;
    }

    /**
     * With N men and N woman will give N.
     * 
     * @return number of men (equal to number of women)
     */
    public int getSize() {
        return men.size();
    }

    /**
     *
     * @return the man in the required pair 
     */
    public int getRequiredPairMen() {
        return required_pair_man;
    }

    /**
     *
     * @return the woman in the required pair 
     */
    public int getRequiredPairWomen() {
        return required_pair_woman;
    }
}
