import java.util.*;

public class binaryTree
{
    public TreeNode insertNode(TreeNode root, TreeNode node)
    {
      		if(root == null)
            {
                return node;
            }
        
      		TreeNode prevNode = null;
      		TreeNode currentNode = root;
      		while(currentNode != null)
            {
                if(node.val > currentNode.val)
                {
                    prevNode = currentNode;
                    currentNode = currentNode.right;
                }
                else if(node.val < currentNode.val)
                {
                    prevNode = currentNode;
                    currentNode = currentNode.left;
                }
                else
                {
                    //overwriting same node
                    currentNode.val = node.val;
                    return root;
                }
            }
        
      		if(node.val > prevNode.val)
            {
                prevNode.right = node;
            }
            else
            {
                prevNode.left = node;
            }
        
      		return root;
    }
    
    /**
     *Given preorder and inorder traversal of a tree, construct the binary tree.
     *@param preorder : A list of integers that preorder traversal of a tree
     *@param inorder : A list of integers that inorder traversal of a tree
     *@return : Root of a tree
     */
    
    
    // Inorder sequence: D B E A F C
    // Preorder sequence: A B D E C F
    // 		    A
    //        /   \
    //      /       \
    //     B         C
    //    / \        /
    //  /     \    /
    // D       E  F
    // In a Preorder sequence, leftmost element is the root of the tree.
    // So we know ‘A’ is root for given sequences.
    // By searching ‘A’ in Inorder sequence,
    // we can find out all elements on left side of ‘A’ are in left subtree and elements on right are in right subtree
    public TreeNode buildTreeUsingInAndPreOrder(int[] preorder, int[] inorder)
    {
        if(preorder==null || inorder==null)
        {
          		return null;
        }
        
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        
        //Store the key as inorder node value
        //Store the value as its position in the inorder list
        for(int i=0;i<inorder.length;i++)
        {
          		map.put(inorder[i],i);
        }
        
        return buildTreeUsingInAndPreOrderHelper(preorder,0,preorder.length-1,
                                                 inorder,0,inorder.length-1, map);
    }
    
    public TreeNode buildTreeUsingInAndPreOrderHelper(int[] preorder, int preStart, int preEnd,
                                                      int[] inorder, int inStart, int inEnd, HashMap<Integer, Integer> map)
    {
        if(preStart > preEnd || inStart > inEnd)
        {
          		return null;
        }
        
        TreeNode root = new TreeNode(preorder[preStart]);
        int k = map.get(root.val); //the position of root value in inorder list
        
        //left tree recursively checks the next left node starting at preStart + 1
        //And ends at preStart + (k - start) in preorder list
        //it also close in range of inorder list by having the range as [inStart:k - 1]
        root.left = buildTreeUsingInAndPreOrderHelper(preorder, preStart + 1, preStart + (k - inStart),
                                                      inorder, inStart, k - 1, map);
        //right tree recursively checks the next right node starting at preStart + (k - inStart) + 1 in preorder list
        //And ends at preEnd in preorder list
        ///it also close in range of inorder list by having the range as [k + 1:inEnd]
        root.right = buildTreeUsingInAndPreOrderHelper(preorder, preStart + (k - inStart) + 1, preEnd, inorder, k + 1, inEnd, map);
        return root;
    }
    
    // Given inorder and postorder traversal of a tree, construct the binary tree.
    public TreeNode buildTreeUsingInAndPostOrder(int[] inorder, int[] postorder)
    {
        if(postorder==null || inorder==null)
        {
          		return null;
        }
        
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        
        //Store the key as inorder node value
        //Store the value as its position in the inorder list
        for(int i=0;i<inorder.length;i++)
        {
          		map.put(inorder[i],i);
        }
        
        return buildTreeUsingInAndPostOrderHelper(postorder, 0, postorder.length - 1,
                                                  inorder, 0, inorder.length - 1, map);
    }
    
    public TreeNode buildTreeUsingInAndPostOrderHelper(int[] postorder, int postStart, int postEnd,
                                                       int[] inorder, int inStart, int inEnd, HashMap<Integer, Integer> map)
    {
        if(postStart > postEnd || inStart > inEnd)
        {
          		return null;
        }
        
        TreeNode root = new TreeNode(postorder[postEnd]);
        int k = map.get(root.val); //the position of root value in inorder list
        
        //(k - inStart) = total number of the left subtree nodes
        //left tree recursively checks the next left node starting at postStart
        //And ends at preStart + (k - start) - 1 in preorder list
        //it also close in range of inorder list by having the range as [inStart:k - 1]
        root.left = buildTreeUsingInAndPostOrderHelper(postorder, postStart, postStart + (k - inStart) - 1,
                                                       inorder, inStart, k - 1, map);
        
        //right tree recursively checks the next right node starting at postStart + (k - inStart) in postorder list
        //And ends at postEnd - 1
        ///it also close in range of inorder list by having the range as [k + 1:inEnd]
        root.right = buildTreeUsingInAndPostOrderHelper(postorder, postStart + (k - inStart), postEnd - 1, inorder, k + 1, inEnd, map);
        
        return root;
    }
    
    public boolean isIdentical(TreeNode a, TreeNode b)
    {
        if(a == null && b == null)
        {
          		return true;
        }
        
        if((a == null && b != null) || (a != null && b == null))
        {
          		return false;
        }
        
        if(a.val != b.val)
        {
          		return false;
        }
        
        return isIdentical(a.left, b.left) && isIdentical(a.right, b.right);
    }
    
    // Given a binary tree, check whether it is a mirror of itself (ie, symmetric around its center).
    
    // For example, this binary tree is symmetric:
    
    //     1
    //    / \
    //   2   2
    //  / \ / \
    // 3  4 4  3
    public boolean isSymmetricTree(TreeNode root)
    {
        if(root == null) return true;
        return isSymmetricTree(root.left, root.right);
    }
    
    public boolean isSymmetricTree(TreeNode a, TreeNode b)
    {
        if(a == null && b == null)
        {
          		return true;
        }
        
        if ((a != null && b == null) || (b != null && a == null))
        {
          		return false;
        }
        // For two trees to be mirror images, the following three
        // conditions must be true
        // 1 - Their root node's key must be same
        // 2 - left subtree of left tree and right subtree
        //      of right tree have to be mirror images
        // 3 - right subtree of left tree and left subtree
        //      of right tree have to be mirror images
        if(a.val != b.val)
        {
          		return false;
        }
        
        // if neither of the above conditions is true then
        // root1 and root2 are not mirror images
        return isSymmetricTree(a.left, b.right) && isSymmetricTree(a.right, b.left);
    }
    
    // Check two given binary trees are identical or not.
    // Assuming any number of tweaks are allowed.
    // A tweak is defined as a swap of the children of one node in the tree.
    public boolean isTweakedIdentical(TreeNode a, TreeNode b)
    {
        if (a == null && b == null) {
          		return true;
        }
        
        if (a == null || b == null) {
          		return false;
        }
        
        if (a.val != b.val) {
          		return false;
        }
        
        if (isTweakedIdentical(a.left, b.left) && isTweakedIdentical(a.right, b.right)) {
          		return true;
        }
        
        if (isTweakedIdentical(a.left, b.right) && isTweakedIdentical(a.right, b.left)) {
          		return true;
        }
        
        return false;
    }
    
    public ArrayList<Integer> preorderTraversal(TreeNode root)
    {
        ArrayList<Integer> resultList = new ArrayList<Integer>();
        Stack<TreeNode> treeStack = new Stack<TreeNode>();
        
        if(root == null)
        {
          		return resultList;
        }
        
        treeStack.push(root);
        TreeNode currentNode = root;
        while(!treeStack.isEmpty())
        {
          		currentNode = treeStack.pop();
          		resultList.add(currentNode.val);
            
            //push right node first so that when the stack pops node out
            //the order is popping left node first then right node
          		if(currentNode.right != null)
                {
                    treeStack.push(currentNode.right);
                }
            
          		if(currentNode.left != null)
                {
                    treeStack.push(currentNode.left);
                }
        }
        
        return resultList;
    }
    
    //preorder morris traversal
    //Time: O(N)
    //Space: O(logN)
    //这个方法不需要为每个节点额外分配指针指向其前驱和后继结点，而是利用叶子节点中的右空指针指向中序遍历下的后继节点就可以了。
    public ArrayList<Integer> preorderTraversalMorris(TreeNode root)
    {
        ArrayList<Integer> res = new ArrayList<Integer>();
        TreeNode cur = root;
        TreeNode pre = null;
        while(cur != null)
        {
          		if(cur.left == null)
                {
                    res.add(cur.val);
                    cur = cur.right;
                }
                else
                {
                    pre = cur.left;
                    while(pre.right!=null && pre.right!=cur)
                    {
                        pre = pre.right;
                    }
                    
                    if(pre.right==null)
                    {
                        res.add(cur.val);
                        pre.right = cur;
                        cur = cur.left;
                    }
                    else
                    {
                        pre.right = null;
                        cur = cur.right;
                    }
                }
        }
        return res;
    }
    
    //iterative version
    //Time: O(N)
    //Space: O(logN)
    public ArrayList<Integer> inorderTraversal(TreeNode root)
    {
        ArrayList<Integer> resultList = new ArrayList<Integer>();
        Stack<TreeNode> treeStack = new Stack<TreeNode>();
        
        if(root == null)
        {
          		return resultList;
        }
        
        TreeNode currentNode = root;
        while(currentNode != null || !treeStack.isEmpty())
        {
            //saving potential root node to the stack
            //keep searching for left node if there are any
          		while(currentNode != null)
                {
                    treeStack.push(currentNode);
                    currentNode = currentNode.left;
                }
            
            //we have reach to a null node
            //pop the last node which has no left child
            //add it to the list and continue searching for possible right child
          		currentNode = treeStack.pop();
          		resultList.add(currentNode.val);
          		currentNode = currentNode.right;
        }
        
        return resultList;
    }
    
    //morris inorder traversal
    //Time: O(N)
    //Space: O(1)
    //     1. 如果当前结点的左孩子为空，则输出当前结点并将其当前节点赋值为右孩子。
    // 2. 如果当前节点的左孩子不为空，则寻找当前节点在中序遍历下的前驱节点（也就是当前结点左子树的最右孩子）。接下来分两种情况：
    //  a) 如果前驱节点的右孩子为空，将它的右孩子设置为当前节点（做线索使得稍后可以重新返回父结点）。然后将当前节点更新为当前节点的左孩子。
    //  b) 如果前驱节点的右孩子为当前节点，表明左子树已经访问完，可以访问当前节点。将它的右孩子重新设为空（恢复树的结构）。输出当前节点。当前节点更新为当前节点的右孩子。
    public ArrayList<Integer> inorderTraversalMorris(TreeNode root)
    {
        ArrayList<Integer> res = new ArrayList<Integer>();
        TreeNode cur = root;
        TreeNode pre = null;
        while(cur != null)
        {
          		if(cur.left == null)
                {
                    res.add(cur.val);
                    cur = cur.right;
                }
                else
                {
                    pre = cur.left;
                    while(pre.right!=null && pre.right!=cur)
                    {
                        pre = pre.right;
                    }
                    
                    if(pre.right==null)
                    {
                        pre.right = cur;
                        cur = cur.left;
                    }
                    else
                    {
                        pre.right = null;
                        res.add(cur.val);
                        cur = cur.right;
                    }
                }
        }
        return res;
    }
    
    // 1) If right subtree of node is not NULL,
    // 	then succ lies in right subtree.
    // 	Go to right subtree
    // 	and return the node with minimum key value in right subtree
    // 2) If right sbtree of node is NULL, then start from root
    // 	Travel down the tree,
    // 	if a node’s data is greater than root’s data then go right side, otherwise go to left side.
    // Time Complexity: O(h) where h is height of tree.
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p)
    {
        TreeNode successor = null;
        
        if(p == null || root == null)
        {
          		return null;
        }
        
        //if node p has right tree
        //the successor must lie in right subtree
        if(p.right != null)
        {
          		return findMinNodeBST(p.right);
        }
        
        while(root != null)
        {
            //if curret node value is less than p
            //go to its right subtree
          		if(root.val < p.val)
                {
                    root = root.right;
                }
            //if current node value is larger than p
            //mark sucessor and check if it has left child
                else if(root.val > p.val)
                {
                    successor = root;
                    root = root.left;
                }
            //if current node value is equal to its node value
            //stop searching and return
                else
                {
                    break;
                }
        }
        
        return successor;
    }
    
    public TreeNode findMinNodeBST(TreeNode root)
    {
        TreeNode temp = root;
        
        
        while(temp.left != null)
        {
          		temp = temp.left;
        }
        
        return temp;
    }
    
    //with a parent pointer
    // 1) If right subtree of node is not NULL,
    // 	then succ lies in right subtree.
    // 	Go to right subtree
    // 	and return the node with minimum key value in right subtree
    // 2) If right sbtree of node is NULL, then start from root
    // 	Travel up using the parent pointer
    // 	until you see a node which is left child of it’s parent. The parent of such a node is the succ.
    public ParentTreeNode inorderSuccessorWithParentPointer(ParentTreeNode root, ParentTreeNode p)
    {
        ParentTreeNode successor = null;
        
        if(p == null || root == null)
        {
          		return null;
        }
        
        //if node p has right tree
        //the successor must lie in right subtree
        if(p.right != null)
        {
          		return findMinNodeBSTWithParentNode(p.right);
        }
        
        ParentTreeNode parentNode = p.parent;
        while(parentNode != null && parentNode.val < p.val)
        {
          		parentNode = parentNode.parent;
        }
        
        return parentNode;
    }
    
    public ParentTreeNode findMinNodeBSTWithParentNode(ParentTreeNode root)
    {
        ParentTreeNode temp = root;
        
        
        while(temp.left != null)
        {
          		temp = temp.left;
        }
        
        return temp;
    }
    
    public ArrayList<Integer> postorderTraversal(TreeNode root)
    {
        
        ArrayList<Integer> resultList = new ArrayList<Integer>();
        Stack<TreeNode> treeStack = new Stack<TreeNode>();
        
        if(root == null)
        {
          		return resultList;
        }
        
        treeStack.push(root);
        TreeNode currentNode = root;
        TreeNode prevNode = null; // previously traversed node
        while(!treeStack.isEmpty())
        {
            currentNode = treeStack.peek();
            
            //if current node does not have any children
            //or current node left children or right children has been traversed
            if((currentNode.left == null && currentNode.right == null)
               || (prevNode != null && (prevNode == currentNode.left || prevNode == currentNode.right)))
            {
                resultList.add(currentNode.val);
                treeStack.pop();
                prevNode = currentNode;
            }
            else
            {
                //push right node first so that when the stack pops node out
                //the order is popping left node first then right node
                if(currentNode.right != null)
                {
                    treeStack.push(currentNode.right);
                }
                
                if(currentNode.left != null)
                {
                    treeStack.push(currentNode.left);
                }
            }
        }
        
        return resultList;
    }
    
    //morris postorder traversal
    //Time: O(N)
    //Space: O(logN)
    // 后序遍历的情况要复杂得多，因为后序遍历中一直要等到孩子结点访问完才能访问自己，需要一些技巧来维护。
    
    // 在这里，我们需要创建一个临时的根节点dummy，把它的左孩子设为树的根root。同时还需要一个subroutine来倒序输出一条右孩子路径上的结点。跟迭代法一样我们需要维护cur指针和pre指针来追溯访问的结点。具体步骤是重复以下两步直到结点为空：
    // 1. 如果cur指针的左孩子为空，那么cur设为其右孩子。
    // 2. 否则，在cur的左子树中找到中序遍历下的前驱结点pre（其实就是左子树的最右结点）。接下来分两种子情况：
    // （1）如果pre没有右孩子，那么将他的右孩子接到cur。将cur更新为它的左孩子。
    // （2）如果pre的右孩子已经接到cur上了，说明这已经是回溯访问了，可以处理访问右孩子了，倒序输出cur左孩子到pre这条路径上的所有结点，并把pre的右孩子重新设为空（结点已经访问过了，还原现场）。最后将cur更新为cur的右孩子。
    // 空间复杂度同样是O(1)，而时间复杂度也还是O(n)，倒序输出的过程只是加大了常数系数，并没有影响到时间的量级
    public ArrayList<Integer> postorderTraversalMorris(TreeNode root)
    {
        ArrayList<Integer> res = new ArrayList<Integer>();
        TreeNode dummy = new TreeNode(0);
        dummy.left = root;
        TreeNode cur = dummy;
        TreeNode pre = null;
        while(cur!=null)
        {
            if (cur.left==null)
            {
                cur = cur.right;
            }
            else
            {
                pre = cur.left;
                while (pre.right!=null && pre.right!=cur)
                    pre = pre.right;
                if (pre.right==null)
                {
                    pre.right = cur;
                    cur = cur.left;
                }
                else
                {
                    reverse(cur.left, pre);
                    TreeNode temp = pre;
                    while (temp != cur.left)
                    {
                        res.add(temp.val);
                        temp = temp.right;
                    }
                    res.add(temp.val);
                    reverse(pre, cur.left);
                    pre.right = null;
                    cur = cur.right;
                }
            }
        }
        return res;
    }
    
    void reverse(TreeNode start, TreeNode end)
    {
        if (start == end)
            return;
        TreeNode pre = start;
        TreeNode cur = start.right;
        TreeNode next;
        while (pre != end)
        {
            next = cur.right;
            cur.right = pre;
            pre = cur;
            cur = next;
        }
    }
    
    public ArrayList<ArrayList<Integer>> levelorderTraversal(TreeNode root)
    {
        ArrayList<ArrayList<Integer>> resultList = new ArrayList<ArrayList<Integer>>();
        Queue<TreeNode> treeQueue = new LinkedList<TreeNode>();
        
        if(root == null)
        {
            return resultList;
        }
        
        treeQueue.offer(root);
        while(!treeQueue.isEmpty())
        {
            ArrayList<Integer> eachLevel = new ArrayList<Integer>();
            
            //get level breadth by calculating the queue size
            int levelBreadth = treeQueue.size();
            for(int i = 0;i < levelBreadth;i++)
            {
                //remove the head of the queue
                //check if it has any children
                TreeNode tempHead = treeQueue.poll();
                eachLevel.add(tempHead.val);
                if(tempHead.left != null)
                {
                    treeQueue.offer(tempHead.left);
                }
                
                if(tempHead.right != null)
                {
                    treeQueue.offer(tempHead.right);
                }
            }
            
            resultList.add(eachLevel);
            
        }
        
        return resultList;
    }
    
    //return the bottom-up level order traversal
    public ArrayList<ArrayList<Integer>> levelOrderBottom(TreeNode root)
    {
        ArrayList<ArrayList<Integer>> resultList = new ArrayList<ArrayList<Integer>>();
        Queue<TreeNode> treeQueue = new LinkedList<TreeNode>();
        
        if(root == null)
        {
            return resultList;
        }
        
        treeQueue.offer(root);
        while(!treeQueue.isEmpty())
        {
            ArrayList<Integer> eachLevel = new ArrayList<Integer>();
            
            //get level breadth by calculating the queue size
            int levelBreadth = treeQueue.size();
            for(int i = 0;i < levelBreadth;i++)
            {
                //remove the head of the queue
                //check if it has any children
                TreeNode tempHead = treeQueue.poll();
                eachLevel.add(tempHead.val);
                if(tempHead.left != null)
                {
                    treeQueue.offer(tempHead.left);
                }
                
                if(tempHead.right != null)
                {
                    treeQueue.offer(tempHead.right);
                }
            }
            
            resultList.add(0, eachLevel);
            
        }
        
        return resultList;
    }
    //find minimum depth of a binary tree
    //The difference between min depth and max depth is that
    //max depth we don't care about the case
    //where the node has only a left child or right child
    //it gets the larger one in terms of the depth
    //but for the min depth, you have to keep searching until you reach to a leaf
    public int minDepthRec(TreeNode root)
    {
        //base case if root is null
        //return depth as zero
        if(root == null)
        {
            return 0;
        }
        
        //base case if a leaf is reached
        //add depth 1 to the parent node
        if(root.left == null && root.right == null)
        {
            return 1;
        }
        
        //check if it has a left child
        //if no, searching right child
        if(root.left == null)
        {
            return minDepthRec(root.right) + 1;
        }
        
        if(root.right == null)
        {
            return minDepthRec(root.left) + 1;
        }
        
        //once left subtree and right subtree are done, get the larger value, add 1
        //then return to one level up
        return Math.min(minDepthRec(root.left),minDepthRec(root.right)) + 1;
    }
    
    //another way of getting min depth is bfs
    //the benefit of doing so is that the algorithm exits earlier
    //therefore does not need to traverse the entire tree like the minDepthRec does
    public int minDepthIterative(TreeNode root)
    {
        if(root == null)
        {
            return 0;
        }
        
        Queue<TreeNode> treeQueue = new LinkedList<TreeNode>();
        
        int depth = 1;
        treeQueue.offer(root);
        
        int currentLevelNodeLeft = 1; //num of nodes left in current level
        int nextLevelNodeNum = 0; //num of nodes in next level
        while(!treeQueue.isEmpty())
        {
          		TreeNode tempHead = treeQueue.poll();
          		currentLevelNodeLeft--;
            
          		//check if the node is a leaf
          		//if it is, simply return current depth
          		if(tempHead.left == null && tempHead.right == null)
                {
                    return depth;
                }
            
          		//if the node has left child
          		//add it to the queue
          		//increment counter to mark the number of level node
          		if(tempHead.left != null)
                {
                    treeQueue.offer(tempHead.left);
                    nextLevelNodeNum++;
                }
            
          		if(tempHead.right != null)
                {
                    treeQueue.offer(tempHead.right);
                    nextLevelNodeNum++;
                }
            
          		//if all nodes at current level have been traversed
          		//reset counter and go to next level
          		if(currentLevelNodeLeft == 0)
                {
                    currentLevelNodeLeft = nextLevelNodeNum;
                    nextLevelNodeNum = 0;
                    depth++;
                }
        }
        
        return depth;
    }
    
    //find maximum depth of a binary tree
    //recursive solution using traversal is pretty straightforward
    //return either left or right subtree that is deeper and add one to it
    public int maxDepthRec(TreeNode root)
    {
        if(root == null)
        {
          		return 0;
        }
        
        return Math.max(maxDepthRec(root.left), maxDepthRec(root.right)) + 1;
    }
    
    //recursive solution using divide and conquer
    public int maxDepthDivideConquer(TreeNode root)
    {
        if(root == null)
        {
          		return 0;
        }
        
        //get left depth and right depth
        int leftDepth = maxDepthDivideConquer(root.left);
        int rightDepth = maxDepthDivideConquer(root.right);
        
        int maxDepth = 0;
        //select the larger one and return
        if(leftDepth > rightDepth)
        {
          		maxDepth = leftDepth;
        }
        else
        {
          		maxDepth = rightDepth;
        }
        
        return maxDepth + 1;
    }
    //use BFS (level order) to get the level
    public int maxDepthIterative(TreeNode root)
    {
        if(root == null)
        {
          		return 0;
        }
        
        Queue<TreeNode> treeQueue = new LinkedList<TreeNode>();
        
        int depth = 0;
        treeQueue.offer(root);
        
        int currentLevelNodeLeft = 1; //num of nodes left in current level
        int nextLevelNodeNum = 0; //num of nodes in next level
        while(!treeQueue.isEmpty())
        {
            
          		TreeNode tempHead = treeQueue.poll();
          		currentLevelNodeLeft--;
            
          		if(tempHead.left != null)
                {
                    treeQueue.offer(tempHead.left);
                    nextLevelNodeNum++;
                }
            
          		if(tempHead.right != null)
                {
                    treeQueue.offer(tempHead.right);
                    nextLevelNodeNum++;
                }
            
          		if(currentLevelNodeLeft == 0)
                {
                    currentLevelNodeLeft = nextLevelNodeNum;
                    nextLevelNodeNum = 0;
                    depth++;
                }
        }
        return depth;
    }
    
    //if the tree is a binary search tree
    //time complexity is O(height)
    //space complexity is O(height) stack space
    public TreeNode lowestCommonAncestorBinarySearchTree(TreeNode root, TreeNode A, TreeNode B)
    {
        //if current node is larger than both nodes
        //we need to check its left node
        if(root.val > A.val && root.val > B.val)
        {
          		return lowestCommonAncestorBinarySearchTree(root.left, A, B);
        }
        
        if(root.val < A.val && root.val < B.val)
        {
          		return lowestCommonAncestorBinarySearchTree(root.right, A, B);
        }
        
        return root;
    }
    
    //if the tree is a general binary tree
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode A, TreeNode B)
    {
        //发现目标节点则通过返回值标记该子树发现了某个目标结点
        if(root == null || root == A || root == B)
        {
          		return root;
        }
        
        //查看左子树中是否有目标结点，没有为null
        TreeNode left = lowestCommonAncestor(root.left, A, B);
        //查看右子树是否有目标节点，没有为null
        TreeNode right = lowestCommonAncestor(root.right, A, B);
        
        //都不为空，说明左右子树都有目标结点，则公共祖先就是本身
        if(left!=null&&right!=null)
        {
          		return root;
        }
        
        if (left != null)
        {
          		return left;
        }
        
        if (right != null)
        {
          		return right;
        }
        return null;
    }
    
    //if every node has a parent pointer
    public ParentTreeNode lowestCommonAncestorII(ParentTreeNode root, ParentTreeNode A, ParentTreeNode B)
    {
        int d1 = getDepthByParentingUp(A);
        int d2 = getDepthByParentingUp(B);
        
        // System.out.println("d1: " + d1 + " d2: " + d2);
        int depthDiff = 0;
        
        //if node A is deeper than B
        //Level up node A
        if(d1 > d2)
        {
          		depthDiff = d1 - d2;
          		for(int i = 0;i < depthDiff;i++)
                {
                    A = A.parent;
                }
        }
        else if(d1 < d2)
        {
          		depthDiff = d2 - d1;
          		for(int i = 0;i < depthDiff;i++)
                {
                    B = B.parent;
                }
        }
        
        while(A != null && B != null)
        {
          		if(A == B)
                {
                    return A;
                }
            
          		A = A.parent;
          		B = B.parent;
        }
        
        //if either A or B is not in the tree
        return null;
    }
    
    
    //if there is a parent pointer
    public int getDepthByParentingUp(ParentTreeNode root)
    {
        if(root == null)
        {
          		return 0;
        }
        
        int height = 0;
        while(root != null)
        {
          		root = root.parent;
          		height++;
        }
        
        return height;
    }
    
    
    //if any subtree is not balanced, the entire tree is not balanced
    //the idea is to take a bottom-up approach
    public boolean isBalanced(TreeNode root)
    {
        int result = findDepth(root);
        
        if(result == -1)
        {
          		return false;
        }
        
        return true;
    }
    
    public int findDepth(TreeNode root)
    {
        if(root == null)
        {
          		return 0;
        }
        
        
        //get left depth and right depth
        int leftDepth = findDepth(root.left);
        
        //if the left subtree is not balanced
        //doing pruning, simply return -1
        if(leftDepth == -1)
        {
          		return -1;
        }
        
        int rightDepth = findDepth(root.right);
        
        //if the left subtree is not balanced
        //doing pruning, simply return -1
        if(rightDepth == -1)
        {
          		return -1;
        }
        
        int difference = Math.abs(leftDepth - rightDepth);
        if(difference > 1)
        {
          		return -1;
        }
        
        //if left and right subtrees are balanced
        //return the max depth
        return Math.max(leftDepth, rightDepth) + 1;
    }
    
    
    //check whether the tree is a valid binary search tree
    //setting a low and high boundary for each node
    public boolean isValidBST(TreeNode root)
    {
        if(root == null)
        {
          		return true;
        }
        
        
        Stack<TreeNode> treeStack = new Stack<TreeNode>();
        TreeNode currentNode = root;
        int index = 0;
        
        int lastValue = 0;
        int currentValue = 0;
        while(currentNode != null || !treeStack.isEmpty())
        {
            //saving potential root node to the stack
            //keep searching for left node if there are any
          		while(currentNode != null)
                {
                    treeStack.push(currentNode);
                    currentNode = currentNode.left;
                }
            
            //we have reach to a null node
            //pop the last node which has no left child
            //add it to the list and continue searching for possible right child
          		currentNode = treeStack.pop();
            
          		if(index == 0)
                {
                    lastValue = Integer.MIN_VALUE;
                    currentValue = currentNode.val;
                }
            
          		if(index > 0)
                {
                    lastValue = currentValue;
                    currentValue = currentNode.val;
                    if(currentValue <= lastValue)
                    {
                        return false;
                    }
                }
            
          		index++;
          		currentNode = currentNode.right;
        }
        return true;
    }
    
    public boolean isValidBSTRecursive(TreeNode root)
    {
        
        
        return isValidBSTRecursiveHelper(root, null, null);
    }
    
    public boolean isValidBSTRecursiveHelper(TreeNode root, Integer min, Integer max)
    {
        if(root == null)
        {
          		return true;
        }
        
        if ((min != null && root.val <= min) || (max != null && root.val >= max))
        {
          		return false;
        }
        
        return isValidBSTRecursiveHelper(root.left, min, root.val) && isValidBSTRecursiveHelper(root.right, root.val, max);
    }
    
    // Check a binary tree is completed or not.
    // A complete binary tree is a binary tree that every level is completed filled
    // except the deepest level. In the deepest level, all nodes must be as left as possible
    public boolean isComplete(TreeNode root)
    {
        return false;
    }
    
    //use BFS
    //Mark a flag true once we first encounter a null children
    //this means we have reached the lowest level
    //the queue should not have any nodes that have children
    //if it has, then the tree is not complete
    //Time: O(N)
    //Space: O(N)
    public boolean isCompleteIterative(TreeNode root)
    {
        if(root == null)
        {
          		return true;
        }
        
        //full node has both left and right children
        boolean isFoundNoFullNode = false;
        Queue<TreeNode> treeQueue = new LinkedList<TreeNode>();
        treeQueue.offer(root);
        while(!treeQueue.isEmpty())
        {
            
            //remove the head of the queue
            //check if it has any children
          		TreeNode tempHead = treeQueue.poll();
            
            //check if a a node is found which is not a full node
            //the following nodes must be leaf nodes
            //otherwise the tree is not complete
          		if(isFoundNoFullNode && (tempHead.left != null || tempHead.right != null))
                {
                    return false;
                }
            
            //once the node is not a full node,
            //all the following nodes must be leaf nodes
          		if(tempHead.left == null || tempHead.right == null)
                {
                    isFoundNoFullNode = true;
                }
            
            //if the node has an empty left child,
            //then the right child must be empty
          		if(tempHead.left == null && tempHead.right != null)
                {
                    return false;
                }
            
          		if(tempHead.left != null)
                {
                    treeQueue.offer(tempHead.left);
                }
            
          		if(tempHead.right != null)
                {
                    treeQueue.offer(tempHead.right);
                }
            
        }
        
        //if bfs completes
        //the tree is complete
        return true;
    }
    
    // Given a complete binary tree, count the number of nodes.
    //if the height of left-most part equals to the height of right-most part
    //then # nodes is defined by the equation = 2^h - 1
    //when they are not equal
    //Time: O(h^2)
    public int countNodes(TreeNode root)
    {
        if(root == null)
        {
          		return 0;
        }
        
        int leftHeight = getLeftMostHeight(root);
        int rightHeight = getRightMostHeight(root);
        
        // System.out.println("root val: " + root.val + " leftHeight: " + leftHeight
        // 	+ " rightHeight: " + rightHeight);
        if(leftHeight == rightHeight)
        {
          		return (1 << leftHeight) - 1;
        }
        else
        {
          		return countNodes(root.left) + countNodes(root.right) + 1;
        }
    }
    
    public int getLeftMostHeight(TreeNode root)
    {
        if(root == null)
        {
          		return 0;
        }
        
        int height = 0;
        while(root != null)
        {
          		height++;
          		root = root.left;
        }
        
        return height;
    }
    
    public int getRightMostHeight(TreeNode root)
    {
        if(root == null)
        {
          		return 0;
        }
        
        int height = 0;
        while(root != null)
        {
          		height++;
          		root = root.right;
        }
        
        return height;
    }
    
    //Give a binary tree and a sum,
    //determine if the tree has a root-to-leaf path
    //such that adding up all the values along the path equals the given sum.
    public boolean hasPathSum(TreeNode root, int sum)
    {
        //null tree does not have a sum
        if(root == null)
        {
          		return false;
        }
        
        if(root.left == null && root.right == null && root.val == sum)
        {
          		return true;
        }
        
        //either left path or right path
        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }
    
    // Given a binary tree containing digits from 0-9 only, each root-to-leaf path could represent a number.
    // An example is the root-to-leaf path 1->2->3 which represents the number 123.
    // Find the total sum of all root-to-leaf numbers.
    // For example,
    
    //     1
    //    / \
    //   2   3
    // The root-to-leaf path 1->2 represents the number 12.
    // The root-to-leaf path 1->3 represents the number 13.
    
    // Return the sum = 12 + 13 = 25.
    public int sumNumbers(TreeNode root)
    {
        if(root == null)
        {
          		return 0;
        }
        
        return sumNumbersDfs(root, 0, 0);
    }
    
    public int sumNumbersDfs(TreeNode root, int number, int sum)
    {
        if(root == null)
        {
          		return sum;
        }
        
        number = number * 10 + root.val;
        
        if(root.left == null && root.right == null)
        {
          		sum = sum + number;
          		return sum;
        }
        
        return sumNumbersDfs(root.left, number, sum) + sumNumbersDfs(root.right, number, sum);
    }
    
    //find every path that add up to a given sum
    public List<List<Integer>> pathSum(TreeNode root, int sum)
    {
        List<List<Integer>> resultList = new ArrayList<List<Integer>>();
        
        if(root == null)
        {
          		return resultList;
        }
        
        
        List<Integer> singlePath = new ArrayList<Integer>();
        singlePath.add(root.val);
        
        pathSumDfs(root, sum - root.val,  resultList, singlePath);
        
        return resultList;
    }
    
    public void pathSumDfs(TreeNode root, int sum, List<List<Integer>> resultList, List<Integer> singlePath)
    {
        //System.out.println("root val -> " + root.val);
        //if we find a leaf and the path sum equals to the target sum
        //add it to the list
        if(root.left == null && root.right == null && sum == 0)
        {
          		//make an exact copy
          		List<Integer> copyPath = new ArrayList<Integer>(singlePath);
          		resultList.add(copyPath);
        }
        
        //if we find path to left child
        //add the node to the path and continue searching
        //once we are done, must remove the last element
        //because it has either been bookkeeped in the final result
        //or the path is not valid
        if(root.left != null)
        {
          		singlePath.add(root.left.val);
          		pathSumDfs(root.left, sum - root.left.val, resultList, singlePath);
          		singlePath.remove(singlePath.size() - 1);
        }
        
        if(root.right != null)
        {
          		singlePath.add(root.right.val);
          		pathSumDfs(root.right, sum - root.right.val, resultList, singlePath);
          		singlePath.remove(singlePath.size() - 1);
        }
    }
    
    // For each node there can be four ways that the max path goes through the node:
    // 1. Node only
    // 2. Max path through Left Child + Node
    // 3. Max path through Right Child + Node
    // 4. Max path through Left Child + Node + Max path through Right Child
    public int maxPathSum(TreeNode root)
    {
        MaxPathSumResult res = new MaxPathSumResult(Integer.MIN_VALUE);
        findMaxPathSum(root, res);
        return res.maxPathSum;
    }
    
    // This function returns overall maximum path sum in 'res'
    // And returns max path sum going through root.
    public int findMaxPathSum(TreeNode root, MaxPathSumResult res)
    {
        if(root == null)
        {
          		return 0;
        }
        
      		// l and r store maximum path sum going through left and
        // right child of root respectively
        int leftMaxPathSum = findMaxPathSum(root.left, res);
        int rightMaxPathSum = findMaxPathSum(root.right, res);
        
        // Max path for parent call of root. This path must
        // include at-most one child of root
        int maxSinglePath = Math.max(Math.max(leftMaxPathSum, rightMaxPathSum) + root.val, root.val);
        
        // Max Path represents the sum when the Node under
        // consideration is the root of the maxsum path and no
        // ancestors of root are there in max sum path
        int maxPath = Math.max(maxSinglePath, leftMaxPathSum + rightMaxPathSum + root.val);
        
        //store the maximum result
        res.maxPathSum = Math.max(maxPath, res.maxPathSum);
        
        return maxSinglePath;
    }
    
    //Given a binary tree, find the maximum path sum from root
    public int maxPathSum2(TreeNode root)
    {
        if(root == null)
        {
          		return 0;
        }
        
        int left = maxPathSum2(root.left);
        int right = maxPathSum2(root.right);
        
        int maxSubPathSum = Math.max(left, right);
        
        return root.val + Math.max(0, maxSubPathSum);
    }
    
    //Given a non-empty binary search tree and a target value,
    //find the value in the BST that is closest to the target.
    public int closestValueIterative(TreeNode root, double target)
    {
        int closestValue = root.val;
        
        while(root != null)
        {
          		if(closestValue == target)
                {
                    return closestValue;
                }
            
          		//only update the closestValue whenever the current node is closer
          		if(Math.abs(target - root.val) < Math.abs(target - closestValue))
                {
                    closestValue = root.val;
                }
            
          		if(target > root.val)
                {
                    root = root.right;
                }
                else
                {
                    root = root.left;
                }
        }
        
        return closestValue;
    }
    
    //recursive solution
    public int closestValueRecursive(TreeNode root, double target)
    {
        
        return closestValueRecursiveHelper(root, target, root.val);
    }
    
    public int closestValueRecursiveHelper(TreeNode root, double target, int closestValue)
    {
        if(root == null)
        {
          		return closestValue;
        }
        if(Math.abs(target - root.val) < Math.abs(target - closestValue))
        {
          		closestValue = root.val;
        }
        
        if(root.val < target)
        {
          		closestValueRecursiveHelper(root.right, target, closestValue);
        }
        else if(root.val > target)
        {
          		closestValueRecursiveHelper(root.left, target, closestValue);
        }
        
        return closestValue;
    }
    
    //Given a non-empty binary search tree and a target value,
    //find k values in the BST that are closest to the target.
    public List<Integer> closestKValues(TreeNode root, double target, int k)
    {
        List<Integer> resultList = new ArrayList<Integer>();
        Stack<Integer> stackPredecessor = new Stack<Integer>();
        Stack<Integer> stackSuccessor = new Stack<Integer>();
        
        closestKValuesInorder(root, target, stackPredecessor);
        closestKValuesInorderReverse(root, target, stackSuccessor);
        
        while (k > 0)
        {
          		if (stackPredecessor.isEmpty())
                {
                    resultList.add(stackSuccessor.pop());
                }
                else if (stackSuccessor.isEmpty())
                {
                    resultList.add(stackPredecessor.pop());
                }
                else if (Math.abs(stackPredecessor.peek() - target) < Math.abs(stackSuccessor.peek() - target))
                {
                    resultList.add(stackPredecessor.pop());
                }
                else
                {
                    resultList.add(stackSuccessor.pop());
                }
            
          		k--;
        }
        
        return resultList;
    }
    
    public void closestKValuesInorder(TreeNode root, double target, Stack<Integer> treeStack)
    {
        if(root == null)
        {
          		return;
        }
        
        closestKValuesInorder(root.left, target, treeStack);
        
        //whenever the value is larger than target value
        //we return
        if(root.val > target)
        {
          		return;
        }
        
        treeStack.push(root.val);
        
        closestKValuesInorder(root.right, target, treeStack);
    }
    
    public void closestKValuesInorderReverse(TreeNode root, double target, Stack<Integer> treeStack)
    {
        if(root == null)
        {
          		return;
        }
        
        closestKValuesInorderReverse(root.right, target, treeStack);
        
        //whenever the value is less or equal than target value
        //we return
        if(root.val <= target)
        {
          		return;
        }
        
        treeStack.push(root.val);
        
        closestKValuesInorderReverse(root.left, target, treeStack);
    }
    
    // Given two values k1 and k2 (where k1 < k2) and a root pointer to a Binary Search Tree.
    // Find all the keys of tree in range k1 to k2.
    // i.e. print all x such that k1<=x<=k2 and x is a key of given BST.
    // Return all the keys in ascending order.
    // Time: O(N)
    public ArrayList<Integer> searchRange(TreeNode root, int k1, int k2)
    {
        ArrayList<Integer> resultList = new ArrayList<Integer>();
        
        searchRangeHelper(root, k1, k2, resultList);
        return resultList;
    }
    
    //if root.val > k1, continue searching its left child
    //otherwise add to the list
    //if root.val < k2, continue searching its right child
    public void searchRangeHelper(TreeNode root, int k1, int k2, ArrayList<Integer> resultList)
    {
        if(root == null)
        {
          		return;
        }
        
        if(root.val > k1)
        {
          		searchRangeHelper(root.left, k1, k2, resultList);
        }
        
        if(k1 <= root.val && root.val <= k2)
        {
          		resultList.add(root.val);
        }
        
        if(root.val < k2)
        {
          		searchRangeHelper(root.right, k1, k2, resultList);
        }
        
    }
    
    // Given n, how many structurally unique BST's (binary search trees) that store values 1...n?
    // For example,
    // Given n = 3, there are a total of 5 unique BST's.
    //    1         3     3      2      1
    //     \       /     /      / \      \
    //      3     2     1      1   3      2
    //     /     /       \                 \
    //    2     1         2                 3
    public int numTrees(int n)
    {
        //if f(n) means the number of unique BST with [1..n] nodes
        //f(0) = 1
        //f(1) = 1
        //f(2) = f(0) * f(1) + f(1) * f(0)
        //f(3) = f(0) * f(2) + f(1) * f(1) + f(2) * f(0)
        //f(n) = f(0) * f(n - 1) + f(1) * f(n - 2) + ... + f(n - 1) * f(0)
        int[] f = new int[n + 1];
        f[0] = 1;
        f[1] = 1;
        for(int i = 2;i <= n;i++)
        {
          		for(int j = 0;j < i;j++)
                {
                    f[i] += f[j] * f[i - j - 1];
                }
        }
        
        return f[n];
    }
    
    // Given n, generate all structurally unique BST's (binary search trees) that store values 1...n.
    // For example,
    // Given n = 3, your program should return all 5 unique BST's shown below.
    
    //    1         3     3      2      1
    //     \       /     /      / \      \
    //      3     2     1      1   3      2
    //     /     /       \                 \
    //    2     1         2                 3
    public List<TreeNode> generateTrees(int n)
    {
        if(n == 0)
        {
          		return new ArrayList<TreeNode>();
        }
        return generateTreesHelper(1, n);
    }
    
    public List<TreeNode> generateTreesHelper(int left, int right)
    {
        List<TreeNode> resultList = new ArrayList<TreeNode>();
        if(left > right)
        {
          		resultList.add(null);
          		return resultList;
        }
        
        for(int i = left;i <= right;i++)
        {
          		List<TreeNode> leftList = generateTreesHelper(left, i - 1);
          		List<TreeNode> rightList = generateTreesHelper(i + 1, right);
          		for(int j = 0;j < leftList.size();j++)
                {
                    for(int k = 0;k < rightList.size();k++)
                    {
                        TreeNode root = new TreeNode(i);
                        root.left = leftList.get(j);
                        root.right = rightList.get(k);
                        resultList.add(root);
                    }
                }
        }
        
        return resultList;
    }
    
    // Given a binary tree, find the largest subtree which is a Binary Search Tree (BST), where largest means subtree with largest number of nodes in it.
    
    // Note:
    // A subtree must include all of its descendants.
    // Here's an example:
    //     10
    //     / \
    //    5  15
    //   / \   \
    //  1   8   7
    // The Largest BST Subtree in this case is the highlighted one.
    // The return value is the subtree's size, which is 3.
    public int largestBSTSubtree(TreeNode root)
    {
        return largestBSTSubtreeDFS(root).ans;
    }
    
    public SuperNode largestBSTSubtreeDFS(TreeNode node)
    {
        if (node == null)
        {
          		return new SuperNode();
        }
        
        SuperNode now = new SuperNode();
        SuperNode left = largestBSTSubtreeDFS(node.left);
        SuperNode right = largestBSTSubtreeDFS(node.right);

        if (left.small < node.val) 
        {
          	now.small = left.small;
        } 
        else 
        {
          	now.small = node.val;
        }
        now.large = Math.max(right.large,node.val);
        if (left.isBST && right.isBST && left.large <= node.val && right.small >= node.val) {
          		now.ans = left.ans + right.ans +1;
          		now.isBST = true;
        } else
        {
          		now.ans=Math.max(left.ans,right.ans);
          		now.isBST = false;
        }
        return now;
    }
    
    class SuperNode
    {
        int ans;
        int small, large;
        boolean isBST;
        public SuperNode() 
        {
          		ans = 0;
          		isBST = true;
          		small = Integer.MAX_VALUE;
          		large = -Integer.MAX_VALUE;
        }
    }
        
    // Given a binary tree, flatten it to a linked list in-place.

    // For example,
    // Given

    //          1
    //         / \
    //        2   5
    //       / \   \
    //      3   4   6
    // The flattened tree should look like:
    //    1
    //     \
    //      2
    //       \
    //        3
    //         \
    //          4
    //           \
    //            5
    //             \
    //              6
    public void flatten(TreeNode root) 
    {
        Stack<TreeNode> stack = new Stack<TreeNode>();
        TreeNode p = root;
 
        //keep searching for right child
        while(p != null || !stack.empty())
        {
            //if there exists one, add to the stack
            if(p.right != null)
            {
                stack.push(p.right);
            }
    
            //if there exists a left child
            //set next child to left and dereference the old left child
            if(p.left != null)
            {
                p.right = p.left;
                p.left = null;
            }
            //otherwise if the stack is not empty, meaning there is still a right child
            //set next child to the right child popped from the stack
            else if(!stack.empty())
            {
                TreeNode temp = stack.pop();
                p.right = temp;
            }
 
            p = p.right;
        }
    }

    public static void main(String[] args)
    {
        binaryTree test = new binaryTree();
        
        TreeNode root1 = new TreeNode(1);
        TreeNode root2 = new TreeNode(2);
        TreeNode root3 = new TreeNode(3);
        TreeNode root4 = new TreeNode(4);
        TreeNode root5 = new TreeNode(5);
        TreeNode root6 = new TreeNode(6);
        TreeNode root7 = new TreeNode(7);
        root1.left = root2;
        root1.right = root3;
        root2.left = root4;
        root2.right = root5;
        root3.left = root6;
        root3.right = root7;
        
        
        ArrayList<Integer> preorderList = test.preorderTraversal(root1);
        ArrayList<Integer> inorderList = test.inorderTraversal(root1);
        ArrayList<Integer> inorderListMorris = test.inorderTraversalMorris(root1);
        ArrayList<Integer> postorderList = test.postorderTraversal(root1);
        ArrayList<ArrayList<Integer>> levelorderList = test.levelorderTraversal(root1);
        ArrayList<ArrayList<Integer>> levelorderListBottomUpList = test.levelOrderBottom(root1);
        
        System.out.println("preorder (iterative): " + preorderList);
        System.out.println("inorder (iterative): " + inorderList);
        System.out.println("inorder (morris): " + inorderListMorris);
        System.out.println("postorder (iterative): " + postorderList);
        System.out.println("levelorder (iterative): " + levelorderList);
        System.out.println("levelorder (bottom up): " + levelorderListBottomUpList);
        
        boolean isCompleteTree = test.isCompleteIterative(root1);
        System.out.println("the binary tree is complete (iterative bfs): " + isCompleteTree);
        
        int numberOfNodes = test.countNodes(root1);
        System.out.println("the number of nodes in the binary tree: " + numberOfNodes);
        
        int minDepthRec = test.minDepthRec(root1);
        int minDepthIterative = test.minDepthIterative(root1);
        
        System.out.println("min depth recursive (traversal): " + minDepthRec);
        System.out.println("min depth iterative: " + minDepthIterative);
        
        int maxDepthRec = test.maxDepthRec(root1);
        int maxDepthDivideConquer = test.maxDepthDivideConquer(root1);
        int maxDepthIterative = test.maxDepthIterative(root1);
        
        System.out.println("max depth recursive (traversal): " + maxDepthRec);
        System.out.println("max depth recursive (divide&conquer): " + maxDepthDivideConquer);
        System.out.println("max depth iterative: " + maxDepthIterative);
        
        int sum = 10;
        boolean hasPathSum = test.hasPathSum(root1, sum);
        System.out.println("Does the tree have path sum of " + sum + ": " + hasPathSum);
        
        List<List<Integer>> validPathSum = test.pathSum(root1, sum);
        System.out.println("valid paths sum are: " + validPathSum);
        
        int maxPathSum = test.maxPathSum(root1);
        System.out.println("the max path sum is: " + maxPathSum);
        
        int maxPathSum2 = test.maxPathSum2(root1);
        System.out.println("the max path sum (from root) is: " + maxPathSum2);
        
        System.out.println("#############");
        System.out.println("#############");
        
        ParentTreeNode parentRoot1 = new ParentTreeNode(1);
        ParentTreeNode parentRoot2 = new ParentTreeNode(2);
        ParentTreeNode parentRoot3 = new ParentTreeNode(3);
        ParentTreeNode parentRoot4 = new ParentTreeNode(4);
        ParentTreeNode parentRoot5 = new ParentTreeNode(5);
        ParentTreeNode parentRoot6 = new ParentTreeNode(6);
        parentRoot1.left = parentRoot2;
        parentRoot2.parent = parentRoot1;
        
        parentRoot1.right = parentRoot3;
        parentRoot3.parent = parentRoot1;
        
        parentRoot2.left = parentRoot4;
        parentRoot4.parent = parentRoot2;
        
        parentRoot2.right = parentRoot5;
        parentRoot5.parent = parentRoot2;
        
        parentRoot3.left = parentRoot6;
        parentRoot6.parent = parentRoot3;
        
        ParentTreeNode ancestorII = test.lowestCommonAncestorII(parentRoot1, parentRoot1, parentRoot2);
        System.out.println("lowest common ancestor with parent node: " + ancestorII.val);
        
        System.out.println("#############");
        System.out.println("#############");
        
        TreeNode bstRoot1 = new TreeNode(1);
        TreeNode bstRoot2 = new TreeNode(2);
        TreeNode bstRoot3 = new TreeNode(3);
        TreeNode bstRoot4 = new TreeNode(4);
        TreeNode bstRoot5 = new TreeNode(5);
        TreeNode bstRoot6 = new TreeNode(6);
        TreeNode bstRoot7 = new TreeNode(7);
        TreeNode bstRoot8 = new TreeNode(8);
        TreeNode bstRoot9 = new TreeNode(9);
        TreeNode bstRoot10 = new TreeNode(10);
        
        
        bstRoot6.left = bstRoot4;
        bstRoot6.right = bstRoot8;
        
        bstRoot4.left = bstRoot2;
        bstRoot4.right = bstRoot5;
        
        bstRoot2.left = bstRoot1;
        bstRoot2.right = bstRoot3;
        
        bstRoot8.left = bstRoot7;
        bstRoot8.right = bstRoot9;
        
        bstRoot9.right = bstRoot10;
        
        ArrayList<Integer> preorderListBST = test.preorderTraversal(root1);
        ArrayList<Integer> inorderListBST = test.inorderTraversal(root1);
        ArrayList<Integer> inorderListMorrisBST = test.inorderTraversalMorris(root1);
        ArrayList<Integer> postorderListBST = test.postorderTraversal(root1);
        ArrayList<ArrayList<Integer>> levelorderListBST = test.levelorderTraversal(root1);
        ArrayList<ArrayList<Integer>> levelorderListBottomUpListBST = test.levelOrderBottom(root1);
        
        System.out.println("preorder (iterative) BST: " + preorderListBST);
        System.out.println("inorder (iterative) BST: " + inorderListBST);
        System.out.println("inorder (morris) BST: " + inorderListMorrisBST);
        System.out.println("postorder (iterative) BST: " + postorderListBST);
        System.out.println("levelorder (iterative) BST: " + levelorderListBST);
        System.out.println("levelorder (bottom up) BST: " + levelorderListBottomUpListBST);
        
        TreeNode minNodeBST = test.findMinNodeBST(bstRoot6);
        System.out.println("the min node val is: " + minNodeBST.val);
        
        TreeNode bstInorderSuccessor = test.inorderSuccessor(bstRoot6, bstRoot2);
        System.out.println("Inorder successor of node " + bstRoot2.val + " is node " + bstInorderSuccessor.val);
        
        boolean isValidBST = test.isValidBST(bstRoot6);
        System.out.println("the binary search tree is valid: " + isValidBST);
        
        int closestValueBST = test.closestValueIterative(bstRoot6, 5.5);
        System.out.println("the closest value in the BST is (iterative): " + closestValueBST);
        
        int closestValueBSTRec = test.closestValueRecursive(bstRoot6, 5.2);
        System.out.println("the closest value in the BST is (recursive): " + closestValueBSTRec);
        
        List<Integer> closetKValuesList = test.closestKValues(bstRoot6, 3.4, 5);
        System.out.println("the k closest value in the BST is: " + closetKValuesList);
        
        ArrayList<Integer> rangeList = test.searchRange(bstRoot6, 2, 9);
        System.out.println("the range list is: " + rangeList);
        
        System.out.println("#############");
        System.out.println("#############");
        
        ParentTreeNode bstParentRoot1 = new ParentTreeNode(1);
        ParentTreeNode bstParentRoot2 = new ParentTreeNode(2);
        ParentTreeNode bstParentRoot3 = new ParentTreeNode(3);
        ParentTreeNode bstParentRoot4 = new ParentTreeNode(4);
        ParentTreeNode bstParentRoot5 = new ParentTreeNode(5);
        ParentTreeNode bstParentRoot6 = new ParentTreeNode(6);
        ParentTreeNode bstParentRoot7 = new ParentTreeNode(7);
        ParentTreeNode bstParentRoot8 = new ParentTreeNode(8);
        ParentTreeNode bstParentRoot9 = new ParentTreeNode(9);
        ParentTreeNode bstParentRoot10 = new ParentTreeNode(10);
        
        
        bstParentRoot6.left = bstParentRoot4;
        bstParentRoot6.right = bstParentRoot8;
        bstParentRoot4.parent = bstParentRoot6;
        bstParentRoot8.parent = bstParentRoot6;
        
        
        bstParentRoot4.left = bstParentRoot2;
        bstParentRoot4.right = bstParentRoot5;
        bstParentRoot2.parent = bstParentRoot4;
        bstParentRoot5.parent = bstParentRoot4;
        
        bstParentRoot2.left = bstParentRoot1;
        bstParentRoot2.right = bstParentRoot3;
        bstParentRoot1.parent = bstParentRoot2;
        bstParentRoot3.parent = bstParentRoot2;
        
        bstParentRoot8.left = bstParentRoot7;
        bstParentRoot8.right = bstParentRoot9;
        bstParentRoot7.parent = bstParentRoot8;
        bstParentRoot9.parent = bstParentRoot8;
        
        bstParentRoot9.right = bstParentRoot10;
        bstParentRoot10.parent = bstParentRoot9;
        
        ParentTreeNode bstParentInorderSuccessor = test.inorderSuccessorWithParentPointer(bstParentRoot6, bstParentRoot5);
        
        System.out.println("Inorder Successor (with parent pointer) of node " + bstParentRoot5.val + " is node " + bstParentInorderSuccessor.val);
    }
    
}

class TreeNode
{
    
    public TreeNode left;
    public TreeNode right;
    public int val;
    
    public TreeNode(int x){ val = x; }
    
}

class ParentTreeNode
{
    public ParentTreeNode parent;
    public ParentTreeNode left;
    public ParentTreeNode right;
    public int val;
    
    public ParentTreeNode(int x){ val = x; }
}

class MaxPathSumResult
{
    public int maxPathSum;
    
    public MaxPathSumResult(int x)
    {
      		maxPathSum = x;
    }
}