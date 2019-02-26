import java.util.*;
/*
	Given a binary search tree (BST), 

    find the lowest common ancestor (LCA) of two given nodes in the BST.

    According to the definition of LCA on Wikipedia: 

    “The lowest common ancestor is defined between two nodes v and w as the lowest node in T 

    that has both v and w as descendants (where we allow a node to be a descendant of itself).”
    
           _______6______
       /              \
    ___2__          ___8__
   /      \        /      \
   0      _4       7       9
         /  \
         3   5
    
    For example, the lowest common ancestor (LCA) of nodes 2 and 8 is 6. Another example is LCA of nodes 2 and 4 is 2, 

    since a node can be a descendant of itself according to the LCA definition.
*/
public class sameTreeLc100
{
	public static void main(String[] args) 
	{
		sameTreeLc100 test = new sameTreeLc100();
		
        
	}

    public boolean isSameTree(TreeNode p, TreeNode q) 
    {
        if(p == null && q == null) return true;
        if(p == null || q == null) return false;
        if(p.val == q.val)
            return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
        return false;    
    }

    public static class TreeNode{
        
        public TreeNode left;
        public TreeNode right;
        public int data;

        public TreeNode(int data)
        {
            this.data = data;
        }
    }
}