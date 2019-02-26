import java.util.*;

public class linkedList
{	

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

    //Reverse a singly linked list.
    public ListNode reverseList(ListNode head) 
    {   

        return reverseListIterative(head);
    }

    public ListNode reverseListIterative(ListNode head)
    {

        /*
            Save next node in a temp node
            Reverse current node and his parent node
            Newhead always points to the previous node
            Keep going until current node passes over the last node in the original list
        */

        if(head == null || head.next == null)
        {
            return head;   
        }
        
        ListNode currentNode = head;
        ListNode tempNode = null;
        ListNode newHead = null;

        while(currentNode != null)
        {   
            tempNode  = currentNode.next;

            currentNode.next = newHead;
            newHead = currentNode;

            currentNode = tempNode;
        }
        return newHead;
    }

    public ListNode reverseListRecursive(ListNode head)
    {
        if(head == null || head.next == null)
        {
            return head;
        }

        ListNode nextNode = head.next;

        ListNode newHead = reverseListRecursive(nextNode);

        nextNode.next = head;

        head.next = null;

        return newHead;
    }

    /*
        Reverse a linked list from position m to n. Do it in-place and in one-pass.

        For example:
        Given 1->2->3->4->5->NULL, m = 2 and n = 4,

        return 1->4->3->2->5->NULL.

        Note:
        Given m, n satisfy the following condition:
        1 ≤ m ≤ n ≤ length of list.
    */
    public ListNode reverseBetween(ListNode head, int m, int n) 
    {	
    	if (m >= n || head == null || head.next == null) 
    	{
            return head;
        }

    	ListNode dummy = new ListNode(-1);
    	dummy.next = head;
    	ListNode current = dummy;

    	for(int i = 1;i < m;i++)
    	{
    		if(current == null)
    		{
    			return null;
    		}

    		current = current.next;
    	}

    	ListNode preMNode = current;
    	ListNode mNode = current.next;
    	ListNode nNode = mNode;
    	ListNode postNNode = mNode.next;

    	for(int i = m;i < n;i++)
    	{
    		if(postNNode == null)
    		{
    			return null;
    		}

    		ListNode temp = postNNode.next;
    		postNNode.next = nNode;
    		nNode = postNNode;
    		postNNode = temp;
    	}

    	preMNode.next = nNode;
    	mNode.next = postNNode;

        return dummy.next;
    }

    //Given a linked list, remove the nth node from the end of list and return its head.
    //Given linked list: 1->2->3->4->5, and n = 2.
    //After removing the second node from the end, the linked list becomes 1->2->3->5.
    public ListNode removeNthFromEnd(ListNode head, int n) 
    {
        if(head == null)
            return null;
            
        ListNode faster = head;
        ListNode slower = head;
        
        for(int i = 0; i < n; i++)
        {
            faster = faster.next;
        }
        
        //if faster comes to the end of the list
        //it means n equals the total length of the list
        //then remove the head
        if(faster == null)
        {
            head = head.next;
            return head;
        }
        

        while(faster.next != null)
        {
            slower = slower.next;
            faster = faster.next;
        }
        
        //slow stops at the node that is previous to the node that is going to be deleted
        slower.next = slower.next.next;
        return head;
    }

    //Merge two sorted linked lists and return it as a new list. 
    //The new list should be made by splicing together the nodes of the first two lists.
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) 
    {
        ListNode dummy = new ListNode(-1);
    	ListNode lastNode = dummy;

    	while(l1 != null && l2 != null)
    	{
    		if(l1.val < l2.val)
    		{
    			lastNode.next = l1;
    			l1 = l1.next;
    		}
    		else
    		{
    			lastNode.next = l2;
    			l2 = l2.next;
    		}

    		lastNode = lastNode.next;
    	}

    	if (l1 != null) 
    	{
            lastNode.next = l1;
        } else 
        {
            lastNode.next = l2;
        }
        
        return dummy.next;
    }

    public ListNode findMiddle(ListNode head)
    {
    	ListNode slow = head;
    	ListNode fast = head;

    	while(fast.next != null && fast.next.next != null)
    	{
    		slow = slow.next;
    		fast = fast.next.next;
    	}

    	return slow;
    }

    //Sort a linked list in O(n log n) time using constant space complexity.
    public ListNode sortList(ListNode head) 
    {
        return mergeSort(head);
    }

	public ListNode mergeSort(ListNode head)
	{
		if(head == null || head.next == null)
        {
        	return head;
        }

        ListNode mid = findMiddle(head);
        ListNode right = mergeSort(mid.next);//soring the right portion

        mid.next = null;//get ready for sorting the left portion
        ListNode left = mergeSort(head);

        return mergeTwoLists(left, right);
	}

    // Merge k sorted linked lists and return it as one sorted list. 
    // Analyze and describe its complexity.
    // Time: O(n*klog(k))
    // Space: O(log(k))
    public ListNode mergeKLists(ListNode[] lists) 
    {
        if(lists == null || lists.length == 0)
        {
            return null;
        }

        return mergeKListsHelper(lists, 0, lists.length - 1);
    }

    private ListNode mergeKListsHelper(ListNode[] lists, int start, int end) 
    {
        if (start == end) 
        {
            return lists[start];
        }
        
        int mid = start + (end - start) / 2;
        ListNode left = mergeKListsHelper(lists, start, mid);
        ListNode right = mergeKListsHelper(lists, mid + 1, end);
        return mergeTwoLists(left, right);
    }

    // Time: O(n*klog(k))
    // Space: O(k)
    public ListNode mergeKListsUsingHeap(ListNode[] lists) 
    {
        if (lists.length == 0)
        {
            return null;
        }
 
        //PriorityQueue is a sorted queue
        PriorityQueue<ListNode> minHeap = new PriorityQueue<ListNode>(lists.length,
                new Comparator<ListNode>() {
                    public int compare(ListNode a, ListNode b) {
                        if (a.val > b.val)
                            return 1;
                        else if(a.val == b.val)
                            return 0;
                        else 
                            return -1;
                    }
                });
 
        //add first node of each list to the queue
        for (ListNode list : lists) 
        {
            if (list != null)
            {
                minHeap.add(list);
            }
        }
 
        ListNode dummy = new ListNode(-1);
        ListNode currentNode = dummy; // start from dummy node
 
        while (minHeap.size() > 0) 
        {
            //pop the min value from minHeap
            ListNode minValue = minHeap.poll();

            //keep adding next element of each list
            if (minValue.next != null)
            {
                minHeap.add(minValue.next);
            }

            currentNode.next = minValue;
            currentNode = currentNode.next;
        }
 
        return dummy.next;
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

 	// Given a linked list and a value x, partition it such that all nodes less than x come before nodes greater than or equal to x.
	// You should preserve the original relative order of the nodes in each of the two partitions.
	// Given 1->4->3->2->5->2 and x = 3,
	// return 1->2->2->4->3->5.
    public ListNode partition(ListNode head, int x) 
    {
        if (head == null) 
        {
            return null;
        }

        ListNode leftDummy = new ListNode(0);
        ListNode rightDummy = new ListNode(0);
        leftDummy.next = head;
        rightDummy.next = head;
        ListNode left = leftDummy;
        ListNode right = rightDummy;

        ListNode current = head;
        while(current != null)
        {
        	if(current.val < x)
        	{
        		left.next = current;//connect current node to left head if it is less than x
        		left = current;
        	}
        	else
        	{
        		right.next = current;
        		right = current;
        	}

        	current = current.next;
        }

        right.next = null;//the very end of the list connects to null
        left.next = rightDummy.next;

        return leftDummy.next;
    }

    //Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
	//Output: 7 -> 0 -> 8
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) 
    {
    	ListNode dummy = new ListNode(-1);
    	ListNode current = dummy;

    	int carry = 0;
    	int digit = 0;
        while(l1 != null && l2 != null)
        {
        	digit = (l1.val+l2.val+carry)%10;  
        	carry = (l1.val+l2.val+carry)/10; 

        	ListNode newNode = new ListNode(digit);
        	current.next = newNode;
        	current = current.next;
        	l1 = l1.next;
        	l2 = l2.next;
        }

        while(l1!=null)  
	    {  
	        digit = (l1.val+carry)%10;  
	        carry = (l1.val+carry)/10;  
  
	        ListNode newNode = new ListNode(digit);
        	current.next = newNode;
        	current = current.next;
        	l1 = l1.next;             
	    }  

	    while(l2!=null)  
	    {  
	        digit = (l2.val+carry)%10;  
	        carry = (l2.val+carry)/10;  

	        ListNode newNode = new ListNode(digit);
        	current.next = newNode;
        	current = current.next;
	        l2 = l2.next;              
	    }

	    if(carry > 0)
	    {
	    	ListNode newNode = new ListNode(carry);
	    	current.next = newNode;
	    }

        return dummy.next;
    }

	// Given a sorted linked list, 
	// delete all duplicates such that each element appear only once.
	// Given 1->1->2, return 1->2.
	// Given 1->1->2->3->3, return 1->2->3.
	public ListNode deleteDuplicates(ListNode head) 
	{	
		ListNode current = head;
		while(current != null)
		{
			while(current.next != null && current.next.val == current.val)
			{
				current.next = current.next.next;
			}
			current = current.next;
		}

        return head;
    }

	// Given a sorted linked list, delete all nodes that have duplicate numbers, 
	// leaving only distinct numbers from the original list.
	// Given 1->2->3->3->4->4->5, return 1->2->5.
	// Given 1->1->1->2->3, return 2->3.
	public ListNode deleteDuplicatesII(ListNode head) 
	{	
		if(head == null)
        {
            return head;
        }

		ListNode dummy = new ListNode(-1);
		dummy.next = head;
		ListNode pre = dummy;
		ListNode current = head;

		while(current != null)
		{
			while(current.next != null && current.next.val == current.val)
			{
				current = current.next;
			}

			//detect dups
			if(pre.next != current)
			{
				pre.next = current.next;
			}
			//no dups
			else
			{
				pre = current;
			}

			current = current.next;
		}

        return dummy.next;
    }

    //Given a linked list, determine if it has a cycle in it.
    public boolean hasCycle(ListNode head) 
    {
    	if(head == null)  
        {
        	return false;
        }  
	    
	    ListNode walker = head;  
	    ListNode runner = head;  
	    
	    while(runner != null && runner.next!=null)  
	    {  
	        walker = walker.next;  
	        runner = runner.next.next;  
	        if(walker == runner)  
	        {
	            return true;  
	        }
	    }  
	    return false;
    }

    //Given a linked list, return the node where the cycle begins. 
    //If there is no cycle, return null.
	//Note: Do not modify the linked list.
    public ListNode detectCycle(ListNode head) 
    {
        if(head == null || head.next == null)
    	{
    		return head;
    	} 

    	ListNode walker = head;  
	    ListNode runner = head; 
 
        while(true)
        {	
        	if(runner == null || runner.next == null)
        	{
                return null;
        	}

            walker = walker.next;
            runner = runner.next.next;
            if(runner == walker)
            {
            	break;
            }
        }

        //when walker and runner meet
        //walker has walk a + x steps, a -> steps from origin to entrance, x -> steps travelled since entrance
        //only walk another a steps and the entrance will be found
        runner = head;
        while(runner != walker)
        {
        	walker = walker.next;
        	runner = runner.next;
        }

        return walker;
    }

    // Given a linked list and two values v1 and v2. 
    // Swap the two nodes in the linked list with values v1 and v2. 
    // It's guaranteed there is no duplicate values in the linked list. 
    // If v1 or v2 does not exist in the given linked list, do nothing.
    // Example
    // Given 1->2->3->4->null and v1 = 2, v2 = 4.
    // Return 1->4->3->2->null.
    public ListNode swapNodes(ListNode head, int v1, int v2) 
    {   
        //do nothing if head is null or there is only 1 node in the list
        if(head == null || head.next == null)
        {
            return head;
        }

        ListNode dummyHead = new ListNode(-1);
        dummyHead.next = head;

        ListNode prevV1 = dummyHead;
        ListNode prevV2 = dummyHead;
        boolean isFoundV1 = false;
        boolean isFoundV2 = false;

        ListNode current = head;
        while(current != null)
        {   
            if(isFoundV1 && isFoundV2)
            {
                break;
            }

            if(v1 == current.val)
            {
                isFoundV1 = true;
            }
            else if(v2 == current.val)
            {
                isFoundV2 = true;
            }

            if(!isFoundV1)
            {
                prevV1 = current;
            }

            if(!isFoundV2)
            {
                prevV2 = current;
            }

            current = current.next;
        }

        if(!isFoundV1 || !isFoundV2)
        {
            return head;
        }   

        //System.out.println("prevV1: " + prevV1.val + " prevV2: " + prevV2.val);

        //if two nodes are neighbors
        //prevV1 -> v1 (prevV2) -> v2 -> v3
        if(prevV1.next == prevV2)
        {
            ListNode afterV2 = prevV2.next.next;

            prevV1.next = prevV2.next;
            prevV1.next.next = prevV2;
            prevV2.next = afterV2;

            return dummyHead.next;
        }
        //prevV2 -> v2(prevV1) -> v1 -> v3
        else if(prevV2.next == prevV1)
        {
            ListNode afterV1 = prevV1.next.next;

            prevV2.next = prevV1.next;
            prevV2.next.next = prevV1;
            prevV1.next = afterV1;

            return dummyHead.next;
        }

        ListNode afterV1 = prevV1.next.next;
        ListNode afterV2 = prevV2.next.next;
        ListNode tempV1 = prevV1.next;

        prevV1.next = prevV2.next;
        prevV1.next.next = afterV1;

        prevV2.next = tempV1;
        prevV2.next.next = afterV2;

        return dummyHead.next;
    }

 	// Given a linked list, swap every two adjacent nodes and return its head.
	// Given 1->2->3->4, you should return the list as 2->1->4->3.
    public ListNode swapPairs(ListNode head) 
    {
        if(head == null || head.next == null)
        {
        	return head;
        }

        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode n1 = dummy;
        ListNode n2 = head;

        //a1 -> a2 -> a3 -> a4
        while(n2 != null && n2.next != null)
        {
        	ListNode nextStart = n2.next.next;//save a3
        	n2.next.next = n2;//set lastNode -> a1 -> a2 -> a1
        	n1.next = n2.next;//set lastNode -> a2 -> a1
        	
        	n2.next = nextStart;//set lastNode -> a2 -> a1 -> a3
        	n1 = n2;//n1 always point to the node before the pair
        	n2 = n2.next;//n2 always point to the first node in the pair

        }

        return dummy.next;
    }
    
    // Given a singly linked list where elements are sorted in ascending order, 
    // convert it to a height balanced BST.
    public TreeNode sortedListToBST(ListNode head) 
    {
        if(head == null)
        {
            return null;
        }

        ListNode current = head;
        int size = 0;
        while(current != null)
        {
            current = current.next;
            size++;
        }

        ListNode[] node = new ListNode[1];
        node[0] = head;
        return sortedListToBSTHelper(node, 0, size - 1);
    }

    public TreeNode sortedListToBSTHelper(ListNode[] node, int start, int end)
    {
        if(start > end)
        {
            return null;
        }

        int mid = start + (end - start) / 2;
        TreeNode left = sortedListToBSTHelper(node, start, mid - 1);
        TreeNode root = new TreeNode(node[0].val);
        root.left = left;//construct left subtree
        node[0] = node[0].next;
        root.right = sortedListToBSTHelper(node, mid + 1, end);
        
        return root;
    }

    // A linked list is given such that each node contains an additional random pointer which could point to any node in the list or null.
    // Return a deep copy of the list.
    // Time: O(n)
    // Space: O(n)
    public RandomListNode copyRandomList(RandomListNode head) 
    {
        if(head == null)
        {
            return head;
        }

        HashMap<RandomListNode,RandomListNode> map = new HashMap<RandomListNode,RandomListNode>();  
        RandomListNode currentNode = head;
        while(currentNode != null)
        {   
            //use a normal node as key, the value is the exact copy node
            RandomListNode copyNode = new RandomListNode(currentNode.label);
            map.put(currentNode, copyNode);

            currentNode = currentNode.next;
        } 

        currentNode = head;
        while(currentNode != null)
        {
            map.get(currentNode).next = map.get(currentNode.next);
            map.get(currentNode).random = map.get(currentNode.random);
            currentNode = currentNode.next;
        }

        RandomListNode newHead = map.get(head);
        return newHead;
    }

    // Time: O(n)
    // Space: O(1)
    public RandomListNode copyRandomListOptimized(RandomListNode head) 
    {
        if(head == null)
        {
            return head;  
        }
        
        RandomListNode currentNode = head;  
        //put copynode right after its original node
        while(currentNode != null)  
        {  
            RandomListNode copyNode = new RandomListNode(currentNode.label);  
            copyNode.next = currentNode.next;  
            currentNode.next = copyNode;  
            currentNode = copyNode.next;  
        } 

        currentNode = head;
        //set random pointer
        while(currentNode != null)  
        {  
            if(currentNode.random != null)
            {
                currentNode.next.random = currentNode.random.next;  
            }
            currentNode = currentNode.next.next;  
        }  

        RandomListNode newHead = head.next;
        currentNode = head;
        //reshape original list
        while(currentNode != null)  
        {  
            RandomListNode copyNode = currentNode.next;  
            currentNode.next = copyNode.next;  
            if(copyNode.next != null)
            {
                copyNode.next = copyNode.next.next;  
            }

            currentNode = currentNode.next;  
        }  

        return newHead;
    }

    // Given a singly linked list L: L0→L1→…→Ln-1→Ln,
    // reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…
    // Given {1,2,3,4}, reorder it to {1,4,2,3}.
    public void reorderList(ListNode head) 
    {
        if(head == null || head.next == null)
        {
            return;
        }   

        ListNode head1 = head;

        //get the second half of the list
        ListNode midPointer = findMiddle(head);
        ListNode head2 = midPointer.next;
        midPointer.next = null;
        //reverse the second half
        ListNode newHead2 = reverseList(head2);

        while(newHead2 != null)
        {
            ListNode temp1 = head1.next;
            ListNode temp2 = newHead2.next;
            head1.next = newHead2;
            newHead2.next = temp1;
            head1 = temp1;
            newHead2 = temp2;
        }
    }

    // Given a list, rotate the list to the right by k places, where k is non-negative.
    // Given 1->2->3->4->5->NULL and k = 2,
    // return 4->5->1->2->3->NULL.
    public ListNode rotateRight(ListNode head, int k) 
    {   
        if(head == null || head.next == null || k == 0)
        {
            return head;
        }

        ListNode currentNode = head;
        int length = 0;
        while(currentNode != null)
        {
            length++;
            currentNode = currentNode.next;
        }

        k = k % length;
        if(k == 0)
        {
            return head;
        }

        ListNode fast = head;
        ListNode slow = head;
        for(int i = 0;i < k;i++)
        {
            fast = fast.next;
        }

        while(fast.next != null)
        {
            slow = slow.next;
            fast = fast.next;
        }

        ListNode newHead = slow.next;
        fast.next = head;
        slow.next = null;

        return newHead;
    }

    // Given a linked list, reverse the nodes of a linked list k at a time and return its modified list.
    // Given this linked list: 1->2->3->4->5
    // For k = 2, you should return: 2->1->4->3->5
    // For k = 3, you should return: 3->2->1->4->5
    public ListNode reverseKGroup(ListNode head, int k) 
    {
        if (head == null || k <= 1) 
        {
            return head;
        }
        
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        
        ListNode currentNode = dummy;
        while (currentNode.next != null) 
        {
            currentNode = reverseNextK(currentNode, k);
        }
        
        return dummy.next;
    }

    // reverse head->n1->..->nk->next..
    // to head->nk->..->n1->next..
    // return n1
    public ListNode reverseNextK(ListNode head, int k)
    {

        // check there is enought nodes to reverse
        ListNode currentNode = head; // next is not null
        for (int i = 0; i < k; i++) 
        {
            if (currentNode.next == null) 
            {
                return currentNode;
            }

            currentNode = currentNode.next;
        }
        
        // reverse
        ListNode newHead = head.next;
        ListNode prev = head; 
        currentNode = newHead;
        for (int i = 0; i < k; i++) 
        {
            ListNode temp = currentNode.next;
            currentNode.next = prev;
            prev = currentNode;
            currentNode = temp;
        }
        
        newHead.next = currentNode;
        head.next = prev;
        return newHead;
    }

    public ListNode plusOne(ListNode head) {
        
        if(head == null) {
            return head;
        }


        boolean isNewHeadNeeded = false;

        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;

        ListNode stopAddingPosition = dummyHead;
        ListNode runner = head;

        while(runner != null) {
            
            // stop adding position is updated with current position
            // only if the number is not 9, which means there is no carry
            // we should set the stop position for carry at current 
            // otherwise, we pause before a 9
            if(runner.val != 9) {
                stopAddingPosition = runner;
            }
            runner = runner.next;
        }

        // if stop position has not been updated
        // new head is needed
        if(stopAddingPosition == dummyHead) {
            isNewHeadNeeded = true;
        }

        while(stopAddingPosition != null) {
            if(stopAddingPosition.val != 9) {
                stopAddingPosition.val += 1;
            } else {
                stopAddingPosition.val = 0;
            }

            stopAddingPosition = stopAddingPosition.next;
        }

        return isNewHeadNeeded ? dummyHead : head;  
    }

    public void deleteNode(ListNode head) {
        ListNode slowRunner = head;
        ListNode fastRunner = head;
        ListNode nodeBeforeMiddle = head;
        int counter = 0;

        if(head == null) {
            return;
        }

        if(head.next == null) {
            head = head.next;
            return;
        }

        //System.out.println("fastRunner is " + fastRunner.val);
        while(fastRunner != null && fastRunner.next != null) {
            fastRunner = fastRunner.next.next;
            nodeBeforeMiddle = slowRunner;
            slowRunner = slowRunner.next;
        }


        nodeBeforeMiddle.next = nodeBeforeMiddle.next.next;
    }

    public static void main(String[] args) 
    {
        linkedList test = new linkedList();

        ListNode one = new ListNode(1);
        one.next = new ListNode(2);
        one.next.next = new ListNode(3);
        one.next.next.next = new ListNode(4);
        one.next.next.next.next = new ListNode(5);

        // ListNode newHead = test.reverseList(one);
        // test.printLinkedList(newHead);

        // ListNode newHead2 = test.reverseBetween(one, 2, 4);
        // test.printLinkedList(newHead2);

        ListNode ten = new ListNode(10);
        ListNode twenty = new ListNode(20);
        ListNode thirty = new ListNode(30);
        ListNode fourty = new ListNode(40);
        ListNode fifty = new ListNode(50);
        ten.next = twenty;
        twenty.next = thirty;
        thirty.next = fourty;
        fourty.next = fifty;

        // ListNode newHead3 = test.deleteDuplicates(ten);
        // test.printLinkedList(newHead3);

        // ListNode newHead4 = test.deleteDuplicatesII(ten);
        // test.printLinkedList(newHead4);

        // ListNode nine = new ListNode(1);
        // nine.next = new ListNode(-1);

        // System.out.println("does the list has cycle: " + test.hasCycle(nine));
        // System.out.println("the cycle of the list begins at: " + test.detectCycle(nine));

        // ListNode newHead5 = test.swapPairs(ten);
        // test.printLinkedList(newHead5);

        // ListNode newHead6 = test.sortList(ten);
        // test.printLinkedList(newHead6);

        // ListNode list1 = new ListNode(2);
        // list1.next = new ListNode(4);
        // list1.next.next = new ListNode(3);

        // ListNode list2 = new ListNode(5);
        // list2.next = new ListNode(6);
        // list2.next.next = new ListNode(4);

        // ListNode newHead7 = test.addTwoNumbers(list1, list2);
        // test.printLinkedList(newHead7);

        // ListNode newHead8 = test.reverseKGroup(ten, 3);
        // test.printLinkedList(newHead8);

        ListNode newHead9 = test.swapNodes(ten, 20, 30);
        test.printLinkedList(newHead9);

        ListNode node1 = new ListNode(9);
        ListNode node2 = new ListNode(9);
        ListNode node3 = new ListNode(9);
        ListNode node4 = new ListNode(9);
        ListNode node5 = new ListNode(9);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        test.plusOne(node1);
    }
}   

class ListNode 
{
	int val;
	ListNode next;
 	ListNode(int x) { val = x; }
}

class RandomListNode 
{
    int label;
    RandomListNode next, random;
    RandomListNode(int x) { this.label = x; }
}

class TreeNode
{
        
    public TreeNode left;
    public TreeNode right;
    public int val;

    public TreeNode(int x){ val = x; }
        
}