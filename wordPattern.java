import java.util.*;

public class wordPattern
{
	public static void main(String[] args)
	{
		
	}
	// Given a pattern and a string str, find if str follows the same pattern.
	// Here follow means a full match, 
	// such that there is a bijection between a letter in pattern and a non-empty word in str.
	// Examples:
	// pattern = "abba", str = "dog cat cat dog" should return true.
	// pattern = "abba", str = "dog cat cat fish" should return false.
	// pattern = "aaaa", str = "dog cat cat dog" should return false.
	// pattern = "abba", str = "dog dog dog dog" should return false.
	public boolean wordPattern(String pattern, String str) 
	{	
        String[] words = str.split(" ");
        
        if(words.length != pattern.length())
        {
        	return false;
        }

        HashMap<String, Character> map = new HashMap<String, Character>();
        
        for(int i = 0;i < words.length;i++)
        {
        	String s = words[i];
        	char c = pattern.charAt(i);

        	if(map.containsKey(s))
        	{
        		if(!map.get(s).equals(c))
        		{
        			return false;
        		}
        	}
        	else
        	{	
        		if(map.containsValue(c))
        		{
        			return false;
        		}

        		map.put(s, c);
        	}
        }

        return true;
    }

 	// Given a pattern and a string str, find if str follows the same pattern.
	// Here follow means a full match, 
	// such that there is a bijection between a letter in pattern and a non-empty substring in str.
	// Examples:
	// pattern = "abab", str = "redblueredblue" should return true.
	// pattern = "aaaa", str = "asdasdasdasd" should return true.
	// pattern = "aabb", str = "xyzabcxzyabc" should return false.
	// Notes:
	// You may assume both pattern and str contains only lowercase letters.
    public boolean wordPatternMatch(String pattern, String str) 
    {	
    	//check the relationship between pattern char to substr
        HashMap<Character, String> map = new HashMap<>();
        //check which substring has been recored
        HashSet<String> set = new HashSet<String>();

        boolean[] result = new boolean[1];


		wordPatternMatchHelper(map, set, pattern, str, 0, 0, result);
        return result[0];
    }

    public void wordPatternMatchHelper(HashMap<Character, String> map, HashSet<String> set, 
    	String pattern, String str, int i, int j, boolean[] result)
    {

    	if(i == pattern.length() && j == str.length())
    	{	
    		result[0] = true;
    		return;
    	}

    	if(i >= pattern.length() || j >= str.length())
    	{
    		return;
    	}

    	char c = pattern.charAt(i);

    	//try every substring
    	for(int cut = j + 1;cut <= str.length();cut++)
    	{
    		String substr = str.substring(j, cut);
    		//if current substr has not been included in both 
    		if(!set.contains(substr) && !map.containsKey(c))
    		{
    			map.put(c, substr);
    			set.add(substr);
    			wordPatternMatchHelper(map, set, pattern, str, i + 1, cut, result);
    			set.remove(substr);
    			map.remove(c);
    		}
    		else if(map.containsKey(c) && map.get(c).equals(substr))
    		{
    			wordPatternMatchHelper(map, set, pattern, str, i + 1, cut, result);
    		}
    	}
    }

}