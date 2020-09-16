package array.h.handson.sep152020;

public class KSortedList {

   public static void main(String[] args) {
      KSortedList kSortedList = new KSortedList();
      ListNode l1 = new ListNode(1);
      ListNode l12 = new ListNode(5);
      ListNode l13 = new ListNode(9);
      l1.next = l12;
      l12.next = l13;
      printList(l1);

      ListNode l2 = new ListNode(2);
      ListNode l22 = new ListNode(6);
      ListNode l23 = new ListNode(10);
      l2.next = l22;
      l22.next = l23;
      printList(l2);
      //ListNode l = kSortedList.mergeTwo(l1,l2);
      //kSortedList.printList(l);

      ListNode l3 = new ListNode(3);
      ListNode l32 = new ListNode(7);
      ListNode l33 = new ListNode(11);
      l3.next = l32;
      l32.next = l33;
      printList(l3);

      ListNode[] lists = new ListNode[3];
      lists[0] = l1;
      lists[1] = l2;
      lists[2] = l3;

      ListNode mk = kSortedList.mergeKLists(lists);
      printList(mk);
   }

   public static void printList(ListNode l) {
      System.out.println();
      while(l !=null) {
         System.out.print(l.val + " ");
         l = l.next;
      }
   }
   public ListNode mergeKLists(ListNode[] lists) {
      if(lists.length == 0) return null;
      if(lists.length == 1) return lists[0];
      ListNode mergedList = lists[0];
      ListNode nextList;

      for(int i=1; i<lists.length;i++) {
         nextList = lists[i];
         mergedList = mergeTwo(mergedList, nextList);
      }

      return mergedList;
   }

   private ListNode mergeTwo(ListNode l1, ListNode l2) {
      System.out.println("Called..");
      if(l1 == null) return l2;
      if(l2 == null) return l1;

      ListNode n = new ListNode();
      ListNode root = n;
      while(l1 !=null && l2 !=null) {
         if(l1.val < l2.val) {
            n.val = l1.val;
            l1 = l1.next;
         } else {
            n.val = l2.val;
            l2 = l2.next;
         }
         n.next = new ListNode();
         n = n.next;
      }
      while(l1 !=null) {
         n.val = l1.val;
         l1 = l1.next;
         if(l1!=null) {
            n.next = new ListNode();
            n = n.next;
         }
      }
      while(l2 !=null) {
         n.val = l2.val;
         l2 = l2.next;
         if(l2!=null) {
            n.next = new ListNode();
            n = n.next;
         }
      }
      n.next = null;
      return root;
   }

   static public class ListNode {
     int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }
}
