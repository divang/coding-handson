package string.h.handson.sep152020;

public class NumberToWords {

   public static void main(String[] args) {
      NumberToWords numberToWords = new NumberToWords();
      String str=numberToWords.numberToWords(100);
      System.out.println(str);
   }
   String[] words = {"Zero","One","Two","Three","Four","Five","Six","Seven","Eight","Nine","Ten",
         "Eleven", "Twelve","Thirteen","Fourteen","Fifteen","Sixteen","Seventeen","Eighteen",
         "Nineteen","Twenty","Thirty","Forty","Fifty","Sixty","Seventy","Eighty","Ninety",
         "Hundred","Thousand","Million","Billion"};

   int oneThousand = 1000;
   int oneMillion  = 1000000;
   int oneBillion  = 1000000000;
   public String numberToWords(int num) {
      if(num ==0) return words[num];
      StringBuilder builder = new StringBuilder();
      builder.append(billion(num));
      num = num % oneBillion;
      builder.append(million(num));
      num = num % oneMillion;
      builder.append(thousand(num));
      num = num % oneThousand;
      builder.append(hundred(num));
      return builder.toString().trim();
   }

   private String billion(int num) {
      int n = num / oneBillion;
      StringBuilder builder = new StringBuilder();
      System.out.println("b-"+n);
      if(n >0) {
         builder.append(hundred(n, words[28])).append(" ").append(words[31]);
      }
      return builder.toString();
   }

   private String million(int num) {
      int n = num / oneMillion;
      StringBuilder builder = new StringBuilder();
      if(n>0) {
         builder.append(hundred(n, words[28])).append(" ").append(words[30]);
      }
      return builder.toString();
   }

   private String thousand(int num) {
      int n = num / oneThousand;
      StringBuilder builder = new StringBuilder();
      if(n>0) {
         builder.append(hundred(n, words[28])).append(" ").append(words[29]);
      }
      return builder.toString();
   }

   private String hundred(int num) {
      return hundred(num, words[28]);
   }

   private String hundred(int num, String unitPlace) {
      StringBuilder builder = new StringBuilder();
      System.out.println("Passed num-"+num);

      int n = num/100;
      if(n > 0) {
         builder.append(" ");
         builder.append(words[n]).append(" ").append(unitPlace);
      }
      n= num % 100;
      if(n!= 0) {
         builder.append(" ");
         System.out.println("n-"+n);
         if (n > 0 && n < 21) {
            builder.append(words[n]);
         } else if(n%10 ==0){
            builder.append(words[n / 10 + 18]);
         } else {
            builder.append(words[n / 10 + 18]).append(" ").append(words[n % 10]);
         }
      }
      return builder.toString();
   }
}
