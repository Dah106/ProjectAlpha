public class BSTIterator 
{
    private Stack<TreeNode> treeStack = new Stack<>();
    private TreeNode currentNode;
    //@param root: The root of binary tree.
    public BSTIterator(TreeNode root) 
    {   
        currentNode = root;
    }

    //@return: True if there has next node, or false
    public boolean hasNext() 
    {
        if(currentNode != null || !treeStack.isEmpty())
        {
            return true;
        }

        return false;
    }
    
    //@return: return next node
    public TreeNode next() 
    {
        while(currentNode != null)
        {
            treeStack.push(currentNode);
            currentNode = currentNode.left;
        }

        currentNode = treeStack.pop();
        TreeNode tempNode = currentNode;
        currentNode = currentNode.right;

        return tempNode;
    }
}

class TreeNode
{
        
    public TreeNode left;
    public TreeNode right;
    public int val;

    public TreeNode(int x){ val = x; }
        
}