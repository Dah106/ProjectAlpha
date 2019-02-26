import java.util.*;

public class WordDictionary
{	
	private TrieNode root;

	// Design a data structure that supports the following two operations:

	// void addWord(word)
	// bool search(word)
	// search(word) can search a literal word or a regular expression string containing only letters a-z or .. A . means it can represent any one letter.

	// For example:

	// addWord("bad")
	// addWord("dad")
	// addWord("mad")
	// search("pad") -> false
	// search("bad") -> true
	// search(".ad") -> true
	// search("b..") -> true
	// Note:
	// You may assume that all words are consist of lowercase letters a-z.
	public WordDictionary() 
    {
        root = new TrieNode();
    }

	// Adds a word into the data structure.
    public void addWord(String word) 
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

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word) 
    {	
		return searchDFS(word, root, 0);
    }

	public boolean searchDFS(String word, TrieNode root, int start)
	{
		if(root == null)
		{
			return false;
		}

		if(start == word.length())
		{
			return root.isLeaf;
		}

		Map<Character, TrieNode> children = root.children;
		char c = word.charAt(start);

		if(c == '.')
		{
			for(char key: children.keySet())
			{
				if(searchDFS(word, children.get(key), start + 1))
				{
					return true;
				}
			}

			return false;
		}
		else if(!children.containsKey(c))
		{
			return false;
		}
		else
		{	
			TrieNode newNode = children.get(c);
			return searchDFS(word, newNode, start + 1);
		}
	}    
    // Your WordDictionary object will be instantiated and called as such:
	// WordDictionary wordDictionary = new WordDictionary();
	// wordDictionary.addWord("word");
	// wordDictionary.search("pattern");

	public static void main(String[] args) 
	{
		WordDictionary wordDictionary = new WordDictionary();
		wordDictionary.addWord("word");
		wordDictionary.search("pattern");
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