import java.util.*;

public class wordLadder
{	
	// Given two words (beginWord and endWord), and a dictionary's word list, 
	// find the length of shortest transformation sequence from beginWord to endWord, such that:

	// Only one letter can be changed at a time
	// Each intermediate word must exist in the word list
	// For example,

	// Given:
	// beginWord = "hit"
	// endWord = "cog"
	// wordList = ["hot","dot","dog","lot","log"]
	// As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
	// return its length 5.

	// Note:
	// Return 0 if there is no such transformation sequence.
	// All words have the same length.
	// All words contain only lowercase alphabetic characters.
	public int ladderLength(String beginWord, String endWord, Set<String> wordList) 
	{
       if(beginWord ==null || endWord == null || beginWord.length() == 0 || endWord.length() == 0 
       	|| beginWord.length() != endWord.length())
       	{
  			return 0;  
       	}  
      	
      	HashSet<String> visited = new HashSet<String>();
      	Queue<String> queue = new LinkedList<String>();

      	int length = 1;
      	int lastLevelNumLeft = 1;//denote the number of nodes left in previous level in bfs
      	int currentNum = 0;//denote the number of current level nodes in bfs 

      	visited.add(beginWord);
      	queue.add(beginWord);

      	while(!queue.isEmpty())
      	{
      		String currentWords = queue.poll();
      		lastLevelNumLeft--;

      		for(int i = 0;i < currentWords.length();i++)
      		{	
      			//for every english alphabet of the word in the dictionary 
      			//check if a single change can find the end word
      			//if it can, return the current length
      			//if no, check if the dictionary contains such word and it has not been visited before
      			for(char c = 'a';c <= 'z';c++)
      			{
      				String transformed = replaceString(currentWords, i, c);

      				if(transformed.equals(endWord))
      				{
      					return length + 1;
      				}

      				if(wordList.contains(transformed) && !visited.contains(transformed))
      				{
      					currentNum++;
      					queue.add(transformed);
      					visited.add(transformed);
      				}
      			}
      		}

      		//when the previous level has no nodes left
      		//update the lastLevelNumLeft to currentNum
      		//increase the distance of the graph
      		if(lastLevelNumLeft == 0)
      		{
      			lastLevelNumLeft = currentNum;
      			currentNum = 0;
      			length++;
      		}
      	} 

      	//if no such transformation sequence has been found
      	//return 0
      	return 0;
    }

    public String replaceString(String s, int index, char c)
    {
    	char[] charArray = s.toCharArray();
    	charArray[index] = c;
    	return new String(charArray);
    }
}