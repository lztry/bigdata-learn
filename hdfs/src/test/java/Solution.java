

class ListNode {
      int val;
      ListNode next;
      ListNode() {}
     ListNode(int val) { this.val = val; }
     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 }

class Solution {
    public static void main(String[] args) {
        ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(1);
        l1.next=new ListNode(2);
        l1.next.next=new ListNode(4);
        l2.next = new ListNode(3);
        l2.next.next=new ListNode(4);
        new Solution().mergeTwoLists(l1,l2);
    }
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        //以一个链表为基准，然后另一个链表插入其中，插入时多个节点一起插入
        if(l1==null)
            return l2;
        if(l2==null)
            return l1;
        //记录位置
        ListNode l3 =l2;
        ListNode l4 =l2;
        ListNode l6 =l1;
        while(l2!=null){
            if(l1.next==null){
                l1.next =l3;
                return l6;
            }
            if(l2.val>l1.next.val){
                ListNode l5=l1;
                l1=l1.next;
                l4.next=l5.next;
                l5.next = l3;
                l3=l2;
                l4=l2;
            }
            else{
                l4=l2;
                l2=l2.next;

            }
            if(l2==null){
                ListNode l5=l1;
                l1 = l1.next;
                l5.next=l3;
                l4.next=l1;
                return l6;
            }
        }
        return l6;
    }
}