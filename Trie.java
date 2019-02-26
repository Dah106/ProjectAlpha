import java.util.*;

public class Trie
{	
	// Your Trie object will be instantiated and called as such:
	// Trie trie = new Trie();
	// trie.insert("somestring");
	// trie.search("key");

	private TrieNode root;

    public Trie() 
    {
        root = new TrieNode();
    }

    // Inserts a word into the trie.
    public void insert(String word) 
    {	
    	TrieNode currentNode = root;
     	for(int i = 0;i < word.length();i++)
     	{
     		char c = word.charAt(i);

     		if(!currentNode.children.containsKey(c))
     		{	
     			TrieNode newNode = new TrieNode(c);
     			currentNode.children.put(c, newNode);
     		}

     		currentNode = currentNode.children.get(c);
     	}   

     	currentNode.isLeaf = true;
    }

    // Returns if the word is in the trie.
    public boolean search(String word) 
    {
        TrieNode currentNode = root;
        for(int i = 0;i < word.length();i++)
     	{
     		char c = word.charAt(i);
     		if(!currentNode.children.containsKey(c))
     		{
     			return false;
     		}

     		currentNode = currentNode.children.get(c);
     	}

     	return currentNode.isLeaf;
    }

    // Returns if there is any word in the trie
    // that starts with the given prefix.
    public boolean startsWith(String prefix) 
    {
        TrieNode currentNode = root;
        for(int i = 0;i < prefix.length();i++)
     	{
     		char c = prefix.charAt(i);
     		if(!currentNode.children.containsKey(c))
     		{
     			return false;
     		}

     		currentNode = currentNode.children.get(c);
     	}

     	return true;
    }
}

class TrieNode 
{	
	char c;
	HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
	boolean isLeaf;
    // Initialize your data structure here.
    public TrieNode() 
    {
       	
    }

    public TrieNode(char c) 
    {
       	this.c = c;
    }

}