public class RemoveNthNodeLinkedList {
    public RemoveNthNodeLinkedList() {

    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode curr = head;
        int count = 0;
        while (curr != null) {
            count++;
            curr = curr.next;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode prev = dummy;
        for (int i = 0; i < count - n; i++) {
            prev = prev.next;
        }

        prev.next = prev.next.next;

        return dummy.next;
    }

    public static void main(String[] args) {

    }
}
