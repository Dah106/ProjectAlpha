import java.util.*;

public class shortestWordDistance
{

	public static void main(String[] args) 
	{
		shortestWordDistance test = new shortestWordDistance();
		String [] words = {"practice", "makes", "perfect", "coding", "makes"};
		System.out.println("the min distance between two words are: " + test.shortestWordDistance(words, "makes", "coding")); 
	

	}

	public int shortestWordDistance(String[] words, String word1, String word2)
	{
		if(words == null || words.length == 0)
		{
			return 0;
		}

		if(word1 == null || word2 == null || word1.length() == 0 || word2.length() == 0)
		{
			return 0;
		}

		int index1 = -1;
		int index2 = -1;
		int minDistance = Integer.MAX_VALUE;
		for(int i = 0;i < words.length;i++)
		{
			if(words[i].equals(word1))
			{
				index1 = i;
			}

			if(words[i].equals(word2))
			{
				index2 = i;
			}

			if(index1 != -1 && index2 != -1)
			{
				minDistance = Math.min(minDistance, Math.abs(index1 - index2));
			}
		}

		return minDistance;
	}

	public int shortestWordDistanceIII(String[] words, String word1, String word2) 
	{
        
        if(words == null || words.length == 0)
		{
			return 0;
		}

		if(word1 == null || word2 == null || word1.length() == 0 || word2.length() == 0)
		{
			return 0;
		}

		int index1 = -1;
		int index2 = -1;
		int minDistance = Integer.MAX_VALUE;
		for(int i = 0;i < words.length;i++)
		{
			if(words[i].equals(word1))
			{
				index1 = i;
				if(index1 != -1 && index2 != -1)
				{	
					if(index1 != index2)
					{	
						minDistance = Math.min(minDistance, Math.abs(index1 - index2));
					}
				}
			}

			if(words[i].equals(word2))
			{
				index2 = i;
				if(index1 != -1 && index2 != -1)
				{	
					if(index1 != index2)
					{	
						minDistance = Math.min(minDistance, Math.abs(index1 - index2));
					}
				}
			}

			
		}

		return minDistance;
    }
}

class WordDistance
{	
	HashMap<String, List<Integer>> map = new HashMap<String, List<Integer>>();

	public WordDistance(String[] words) 
	{
        for(int i = 0;i < words.length;i++)
		{
			if(!map.containsKey(words[i]))
			{
				List<Integer> indexList = new ArrayList<Integer>();
				indexList.add(i);
				map.put(words[i], indexList);
			}
			else
			{
				map.get(words[i]).add(i);
			}
		}
    }

    public int shortest(String word1, String word2) 
    {
    	if(word1 == null || word2 == null || word1.length() == 0 || word2.length() == 0)
		{
			return 0;
		}

        List<Integer> indexList1 = map.get(word1);
        List<Integer> indexList2 = map.get(word2);

        int minDistance = Integer.MAX_VALUE;
        int size1 = indexList1.size();
        int size2 = indexList2.size();
        int i = 0;
        int j = 0;
        while(i < size1 && j < size2)
        {	
        	int index1 = indexList1.get(i);
        	int index2 = indexList2.get(j);
        	minDistance = Math.min(minDistance, Math.abs(index1 - index2));

        	if(index1 < index2)
        	{
        		i++;
        	}
        	else
        	{
        		j++;
        	}
        }

        return minDistance;
    }
}