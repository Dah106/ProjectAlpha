import java.util.*;
/*
	Given a singly linked list, determine if it is a palindrome.

    Follow up:
    Could you do it in O(n) time and O(1) space?
*/
public class palindromeLinkedList
{
	public static void main(String[] args) 
	{
		palindromeLinkedList test = new palindromeLinkedList();
		
        ListNode root = new ListNode(1);
        root.next = new ListNode(1);
        root.next.next = new ListNode(2);
        root.next.next.next = new ListNode(1);

        // ListNode newHead = test.reverseLinkedListIterative(root);
        

        System.out.println(test.isPalindrome(root));
        test.printLinkedList(root);
     
	}

	public boolean isPalindrome(ListNode head) 
    {
        if (head==null) return true;

        //Find middle node: use slow, fast pointers.
        ListNode fast = head.next;
        ListNode mid = head;

        while(fast !=null && fast.next != null) {
            fast = fast.next.next;
            mid = mid.next;
        }

        //Reverse: mid..end
        ListNode tail = reverseLinkedListIterative(mid);

        //Compare tail to mid and start to mid.
        while (tail !=null && head != null) 
        {
            if (tail.val != head.val) 
            {   
                return false;
            }
            tail = tail.next;
            head = head.next;
        }

        return true;
    }

    public ListNode reverseLinkedListIterative(ListNode head)
    {
        /*
            Save next node in a temp node
            Reverse current node and his parent node
            Newhead always points to the previous node
            Keep going until current node passes over the last node in the original list
        */

        ListNode currentNode = head;
        ListNode tempNode = null;
        ListNode newHead = null;

        while(currentNode != null)
        {   
            tempNode = currentNode.next;

            currentNode.next = newHead;
            newHead = currentNode;

            currentNode = tempNode;
        }

        return newHead;
    }

    public static class ListNode 
    {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }


    public void printLinkedList(ListNode head)
    {
        System.out.println();
        if(head == null)
        {
            return;
        }

        while(head != null)
        {
            System.out.print(head.val + " ");
            head = head.next;
        }
    }
}