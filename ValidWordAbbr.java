import java.util.*;

public class ValidWordAbbr
{	
	HashMap<String, String> map = new HashMap<String, String>();

	public ValidWordAbbr(String[] dictionary) 
	{
    	for(int i = 0;i < dictionary.length;i++)
    	{	
    		String key = getAbbr(dictionary[i]);
    		if(map.containsKey(key) && !map.get(key).equals(dictionary[i]))
    		{
    			map.put(key, "");	
    		}
    		else
    		{
    			map.put(key, dictionary[i]);
    		}

    	}    
    }

    public boolean isUnique(String word) 
    {
        String key = getAbbr(word);

        if(!map.containsKey(key))
        {
        	return true;
        }

        if(map.get(key).equals(word))
        {
        	return true;
        }

        return false;
    }

    public String getAbbr(String word)
    {
    	if(word.length() <= 2)
    	{
    		return word;
    	}

    	return word.charAt(0) + Integer.toString(word.length() - 2) + word.charAt(word.length() - 1);
    }
}

// Your ValidWordAbbr object will be instantiated and called as such:
// ValidWordAbbr vwa = new ValidWordAbbr(dictionary);
// vwa.isUnique("Word");
// vwa.isUnique("anotherWord");