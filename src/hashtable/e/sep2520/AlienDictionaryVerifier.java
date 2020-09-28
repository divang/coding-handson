package hashtable.e.sep2520;

import java.util.HashMap;

public class AlienDictionaryVerifier    {
   public static void main(String[] args) {
      AlienDictionaryVerifier alienDictionaryVerifier = new AlienDictionaryVerifier();
      /*
["app","apple"]
"abcdefghijklmnopqrstuvwxyz"
       */
      String[] words = {"app","apple"};
      String order = "abcdefghijklmnopqrstuvwxyz";
      System.out.println(alienDictionaryVerifier.isAlienSorted(words, order));
   }
   HashMap<Character, Integer> charOrderMap = new HashMap<>();

   public boolean isAlienSorted(String[] words, String order) {
      int orderIndex =1;
      for(Character c: order.toCharArray()) {
         charOrderMap.put(c,orderIndex++);
      }

      for(int i=0;i<words.length-1;i++) {
         for(int j=i+1;j< words.length; j++) {
            if(!compareOrder(words[i], words[j])) return false;
         }
      }

      return true;
   }

   public boolean compareOrder(String w1, String w2) {

      boolean isSubString = true;
      for(int i=0 ;i<w1.length() && i<w2.length();i++) {
         int i1 = charOrderMap.get(w1.charAt(i));
         int i2 = charOrderMap.get(w2.charAt(i));
         if(isSubString && i1!=i2)isSubString = false;
         if(i1 < i2) return true;
         else if(i1 > i2)return false;
      }

      if(isSubString) {
         return w1.length() < w2.length();
      }
      return false;
   }
}
