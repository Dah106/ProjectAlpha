/*
	Invert a binary tree.
*/
import java.util.*;
import java.lang.*;

public class invertBstLc226
{
	public static void main(String[] args) 
	{
		invertBstLc226 test = new invertBstLc226();	
		
		TreeNode root = new TreeNode (4);
		root.left = new TreeNode(2);
		root.right = new TreeNode(7);
		root.left.left = new TreeNode(1);
		root.left.right = new TreeNode(3);
		root.right.left = new TreeNode(6);
		root.right.right = new TreeNode(9);

		System.out.println("Before inverting the binary tree:");
		test.levelorder(root);
		
		test.invertTree(root);

		System.out.println("After inverting the binary tree:");
		test.levelorder(root);
	}

	public TreeNode invertTree(TreeNode root) 
	{
		if(root == null)
		{
			return root;
		}

		TreeNode tempNode = root.left;
		root.left = root.right;
		root.right = tempNode;

		invertTree(root.left);
		invertTree(root.right);

 		return root;
    }


    public void levelorder(TreeNode node)
	{
		if(node != null)
		{
			Queue<TreeNode> nodeQ = new LinkedList<TreeNode>();
			nodeQ.add(node);
			while(!nodeQ.isEmpty())
			{
				TreeNode temp = nodeQ.remove();
				System.out.println(temp.val);
				if(temp.left != null)
				{
					nodeQ.add(temp.left);
				}
				if(temp.right != null)
				{
					nodeQ.add(temp.right);
				}
			}
		}
	}

	public static class TreeNode
	{
		
		public TreeNode left;
		public TreeNode right;
		public int val;

		public TreeNode(int val)
		{
			this.val = val;
		}
	}
}