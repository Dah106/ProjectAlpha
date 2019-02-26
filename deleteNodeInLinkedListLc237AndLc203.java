import java.util.*;
/*
	Given a singly linked list, determine if it is a palindrome.

    Follow up:
    Could you do it in O(n) time and O(1) space?
*/
public class deleteNodeInLinkedListLc237AndLc203
{
	public static void main(String[] args) 
	{
		deleteNodeInLinkedListLc237AndLc203 test = new deleteNodeInLinkedListLc237AndLc203();
		
        ListNode root = new ListNode(1);
        root.next = new ListNode(2);
        root.next.next = new ListNode(5);
        root.next.next.next = new ListNode(6);
        root.next.next.next.next = new ListNode(1);

        // test.deleteNode(root);
        ListNode newHead = test.removeElements(root, 1);
        test.printLinkedList(newHead);
	}

    public void deleteNode(ListNode node) {
        //Since we don't have the reference to the previous node
        //We could simply copy the next node of the desired node over
        //And set the next node to the nex next
        node.val = node.next.val;
        node.next = node.next.next;
    }

    /*
        Remove all elements from a linked list of integers that have value val.

        Example
        Given: 1 --> 2 --> 6 --> 3 --> 4 --> 5 --> 6, val = 6
        Return: 1 --> 2 --> 3 --> 4 --> 5
    */
    public ListNode removeElements(ListNode head, int val) 
    {   
        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;
        ListNode currentNode = dummyHead;

        while(currentNode.next != null)
        {   
            if(currentNode.next.val == val)
            {
                currentNode.next = currentNode.next.next;
            }
            else
            {
                currentNode = currentNode.next;
            }
        }

        return dummyHead.next;
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