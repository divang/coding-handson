package array.m.sep2520;

import java.util.*;

public class MergeIntervals {
   public static void main(String[] args) {
      MergeIntervals mergeIntervals = new MergeIntervals();
      int[][] arr = {{2,3},{5,5},{2,2},{3,4},{3,4}};
      for(int[] a: mergeIntervals.merge(arr)) {
         System.out.println(a[0] +":" + a[1]);
      }
   }

   class Pair implements  Comparable {
      int i0;
      int i1;

      Pair(int x0, int x1) {
         i0 = x0;
         i1 = x1;
      }

      @Override public int compareTo(Object o) {
         Pair oPair = (Pair) o;
         if(this.i0 == oPair.i0) {
            return this.i1 - oPair.i1;
         } else {
            return this.i0 - oPair.i0;
         }
      }
   }

   public int[][] merge(int[][] intervals) {
      Set<Pair> intervalMap = new TreeSet<>();

      for(int i=0;i<intervals.length;i++){
            intervalMap.add(new Pair(intervals[i][0], intervals[i][1]));
      }

      Pair prePair = null;
      for(Pair pair: intervalMap) {
         int curStartInterval = pair.i0;
         int curEndInterval = pair.i1;

         if(prePair == null) {
            prePair = pair;
            continue;
         }

         int preStartInterval = prePair.i0;
         int preEndInterval = prePair.i1;

         if(curStartInterval <= preEndInterval) {
            if(preEndInterval < curEndInterval) {
               prePair.i1 = curEndInterval;
            } else {
               prePair.i1 = preEndInterval;
            }
            pair.i1 = -1;
         } else {
            prePair = pair;
         }
      }

      int[][] newIntervals = new int[getNewSize(intervalMap)][2];
      int i=0;
      for(Pair curInterval: intervalMap) {
         if(curInterval.i1 != -1) {
            newIntervals[i][0] = curInterval.i0;
            newIntervals[i][1] = curInterval.i1;
            i++;
         }
      }
      return newIntervals;
   }

   public int getNewSize(Set<Pair> intervalSet){
      int i=0;
      for(Pair k: intervalSet) {
         if(k.i1 != -1) i++;
      }
      return i;
   }
}
