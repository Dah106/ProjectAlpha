import java.util.*;
public class topKElements
{
	public static void main(String[] args) 
	{
		topKElements test = new topKElements();
		String[] words = {
		    "yes", "lint", "code",
		    "yes", "code", "baby",
		    "you", "baby", "chrome",
		    "safari", "lint", "code",
		    "body", "lint", "code"
		};

		String[] top3 = test.topKFrequentWords(words, 4);
		System.out.println("top 4 elements: " + Arrays.toString(top3));
	}

	//Given a list of words and an integer k, return the top k frequent words in the list.
	//Time: O(n*log(k)) 
	//Space: O(n)
	public String[] topKFrequentWords(String[] words, int k) 
	{
        String[] result = new String[k];

        if(words == null || words.length == 0 || k <= 0)
        {
        	return result;
        }

        //map that stores the frequency of each word
        HashMap<String, Pair> map = new HashMap<>();
        for(String word: words)
        {
        	if(!map.containsKey(word))
        	{	
        		Pair newNode = new Pair(word, 1);
        		map.put(word, newNode);
        	}
        	else
        	{
        		Pair updateNode = map.get(word);
        		updateNode.frequency++; 
        		map.put(word, updateNode);
        	}
        }

        Comparator<Pair> pairComparator = new Comparator<Pair>()
        {
        	public int compare(Pair left, Pair right)
        	{
        		if(left.frequency == right.frequency)
        		{
        			return left.key.compareTo(right.key);
        		}

        		return right.frequency - left.frequency;
        	}
        };

        PriorityQueue<Pair> maxHeap = new PriorityQueue<Pair>(k, pairComparator);

        for (Map.Entry<String, Pair> entry : map.entrySet()) 
        {
            maxHeap.offer(entry.getValue());
        }

        for (int i = 0; i < k; i++) 
        {
            result[i] = maxHeap.poll().key;
        }

        return result;
    }
}

class Pair 
{
    String key;
    int frequency;
    
    Pair(String key, int frequency) 
    {
        this.key = key;
        this.frequency = frequency;
    }
}