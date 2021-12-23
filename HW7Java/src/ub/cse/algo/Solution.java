package ub.cse.algo;

import java.util.*;

public class Solution {
    
    private Integer _n_points;          // Number of points in the plane
    private ArrayList<Point> _points;  // This ArrayList contains all points in the plane

    public Solution (Integer n_points, ArrayList<Point> points){
        _n_points = n_points;
        _points = points;
    }
    //sort according to x
    ArrayList<Point> sortingX (ArrayList<Point> points) {
        ArrayList<Point> retVal = new ArrayList<>();
        HashMap<Integer, Integer> ttmp = new HashMap<>();
        int[] tmp = new int[points.size()];
        for(int i = 0; i < tmp.length; i++){
            ttmp.put(points.get(i)._x, points.get(i)._y);
            tmp[i] = points.get(i)._x;
        }
        Arrays.sort(tmp); //Take O(nlogn) time
        for(int i = 0; i < tmp.length; i++){
            retVal.add(new Point(tmp[i], ttmp.get(tmp[i])));
        }
        return retVal;
    }

    //sort according to y
    ArrayList<Point> sortingY (ArrayList<Point> points) {
        ArrayList<Point> retVal = new ArrayList<>();
        HashMap<Integer, Integer> ttmp = new HashMap<>();
        int[] tmp = new int[points.size()];
        for(int i = 0; i < tmp.length; i++){
            ttmp.put(points.get(i)._y, points.get(i)._x);
            tmp[i] = points.get(i)._y;
        }
        Arrays.sort(tmp); //Take O(nlogn) time
        for(int i = 0; i < tmp.length; i++){
            retVal.add(new Point(ttmp.get(tmp[i]), tmp[i]));
        }
        return retVal;
    }

    double dist(Point p, Point q){ //calculate the distance between two points.
        return Math.sqrt((Math.pow((p._x - q._x), 2) + Math.pow((p._y - q._y), 2)));
    }

    double bruteForce(ArrayList<Point> points, int start, int end){ //bruteForce algo when num of points is less than 4
        double mindist = Double.MAX_VALUE;
        for(int i = start; i < end; i++){
            for(int j = i + 1; j <= end; j++){
                double d = dist(points.get(i), points.get(j));
                mindist = Math.min(d, mindist);
            }
        }
        return mindist;
    }

    double closest(ArrayList<Point> points, int s, int e){ //find closest pair of points by diving and conquer
        int n = e - s + 1;
        if(n <= 3){
            return bruteForce(points, s, e);
        }
        else {
            int mid = (s + e) / 2;

            double d = Math.min(closest(points, s, mid), closest(points, mid + 1, e)); //divide into 2parts and find minimum distance and conquer.

            ArrayList<Point> pairsInbox = new ArrayList<>(); //compare the points in the range of 1 delta on both sides center-based.
            for (int i = s; i <= e; i++) {
                double tmp = Math.pow((points.get(mid)._x - points.get(i)._x), 2);
                if (tmp < d) {
                    pairsInbox.add(points.get(i));
                }
            }
            pairsInbox = sortingY(pairsInbox); //sort the points in box according to y.
            for (int i = 0; i < pairsInbox.size() - 1; i++) {
                for (int j = i + 1; j < pairsInbox.size(); j++) {
                    double tmp = Math.pow((pairsInbox.get(j)._y - pairsInbox.get(i)._y), 2);
                    if (tmp <= d) {
                        d = Math.min(d, dist(pairsInbox.get(i), pairsInbox.get(j)));
                    }
                }
            }
            return d;
        }
    }

    public double outputClosestDistance(){
        ArrayList<Point> tmp = new ArrayList<>();
        for(int i = 0; i < _n_points; i++){
            tmp.add(_points.get(i));
        }
        tmp = sortingX(tmp);
        return closest(tmp, 0 , _n_points-1);
    }
}
