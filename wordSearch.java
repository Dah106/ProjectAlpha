import java.util.*;

public class wordSearch
{	
	public static void main(String[] args) 
	{
		wordSearch test = new wordSearch();

			
	}

	// Given a 2D board and a word, find if the word exists in the grid.
	// The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. 
	// The same letter cell may not be used more than once.
	// Given board =

	// [
	//   ['A','B','C','E'],
	//   ['S','F','C','S'],
	//   ['A','D','E','E']
	// ]
	public boolean exist(char[][] board, String word) 
	{
        if(board == null || board.length == 0 || board[0].length == 0)
        {
        	return false;
        }

        int row = board.length;
        int col = board[0].length;

        for(int i = 0;i < row;i++)
    	{
    		for(int j = 0;j < col;j++)
    		{
    			if(wordSearchDFS(board, word, i, j, 0))
    			{
    				return true;
    			}
    		}
    	}

        return false;
    }

    public boolean wordSearchDFS(char[][] board, String word, int i, int j, int index)
    {	
    	int row = board.length;
    	int col = board[0].length;

    	if(i < 0 || j < 0 || i >= row || j >= col)
    	{
    		return false;
    	}

    	if(board[i][j] == word.charAt(index) )
    	{	
    		char temp = board[i][j];
    		board[i][j] = '#';
    		//the word has completed
    		if(index == word.length() - 1)
    		{
    			return true;
    		}
    		else if(wordSearchDFS(board, word, i - 1, j, index + 1))
    		{
    			return true;
    		}
    		else if(wordSearchDFS(board, word, i + 1, j, index + 1))
    		{
    			return true;
    		}
    		else if(wordSearchDFS(board, word, i, j - 1, index + 1))
    		{
    			return true;
    		}
    		else if(wordSearchDFS(board, word, i, j + 1, index + 1))
    		{
    			return true;
    		}

    		board[i][j] = temp;
    	}

    	return false;
    }	

 	// Given a 2D board and a list of words from the dictionary, find all words in the board.
	// Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. 
	// The same letter cell may not be used more than once in a word.
	// Given words = ["oath","pea","eat","rain"] and board =

	// [
	//   ['o','a','a','n'],
	//   ['e','t','a','e'],
	//   ['i','h','k','r'],
	//   ['i','f','l','v']
	// ]
	// Return ["eat","oath"].
	// You may assume that all inputs are consist of lowercase letters a-z.
    public List<String> findWords(char[][] board, String[] words) 
    {
        List<String> resultList = new ArrayList<String>();

        if(board == null || board.length == 0 || board[0].length == 0)
        {
        	return resultList;
        }

        int row = board.length;
        int col = board[0].length;

        boolean[][] visited = new boolean[row][col];
        Trie trie = new Trie();
        for(String word: words)
        {
        	trie.insert(word);
        }

        for(int i = 0;i < row;i++)
    	{
    		for(int j = 0;j < col;j++)
    		{
    			findWordsDFS(board, resultList, visited, "", i, j, trie);
    		}
    	}

        return resultList;
    }

    public void findWordsDFS(char[][] board, List<String> resultList, boolean[][] visited, String word, int i, int j, Trie trie)
    {
    	int row = board.length;
    	int col = board[0].length;

    	if(i < 0 || j < 0 || i >= row || j >= col)
    	{
    		return;
    	}

    	//if a letter has been used in previous loop
    	//stop searching
    	if(visited[i][j])
    	{
    		return;
    	}

    	word = word + board[i][j];

    	//if the current result does not fit any prefix of words in the trie
    	//stop searching
    	if(!trie.startsWith(word))
    	{
    		return;
    	}

    	//if the current result is in the trie
    	//include in the result
    	if(trie.search(word))
    	{	
    		if(!resultList.contains(word))
    		{
    			resultList.add(word);
    		}
    	}

    	visited[i][j] = true;
    	findWordsDFS(board, resultList, visited, word, i - 1, j, trie);
    	findWordsDFS(board, resultList, visited, word, i + 1, j, trie);
    	findWordsDFS(board, resultList, visited, word, i, j - 1, trie);
    	findWordsDFS(board, resultList, visited, word, i, j + 1, trie);
    	visited[i][j] = false;
    }
}

class Trie
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