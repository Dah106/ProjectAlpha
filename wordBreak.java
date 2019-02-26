import java.util.*;

public class wordBreak
{	
	// s = "leetcode",
	// dict = ["leet", "code"].
	// Return true because "leetcode" can be segmented as "leet code".
	public boolean wordBreak(String s, Set<String> wordDict) 
	{	
		if(s == null || s.length() == 0)  
		{
			return true;  
		}
        
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;//initial state

        for(int i = 0;i <= s.length();i++)
        {
        	//should continue from match position
            if(!dp[i])
            {
                continue;
            }
 
            for(String word: wordDict)
            {
                int len = word.length();
                int end = i + len;
                if(end > s.length())
                {
                    continue;
                }
 
                if(dp[end]) 
                {
                	continue;
                }
 
                if(s.substring(i, end).equals(word))
                {
                    dp[end] = true;
                }
            }
        }

        return dp[s.length()];
    }

 	// s = "catsanddog",
	// dict = ["cat", "cats", "and", "sand", "dog"].
	// A solution is ["cats and dog", "cat sand dog"].
	public List<String> wordBreakII(String s, Set<String> wordDict) 
	{
        if(s == null || s.length() == 0)  
		{
			return null;  
		}

		//create an array of ArrayList<String>
	    List<String> dp[] = new ArrayList[s.length() + 1];
	    dp[0] = new ArrayList<String>();

	    for(int i = 0; i < s.length(); i++)
	    {
	        if(dp[i] == null) 
	        {
	            continue; 
	        }
	 
	        for(String word: wordDict)
	        {
	            int len = word.length();
	            int end = i + len;
	            if(end > s.length()) 
	            {
	                continue;
	            }
	 
	            if(s.substring(i,end).equals(word))
	            {
	                if(dp[end] == null)
	                {
	                    dp[end] = new ArrayList<String>();
	                }	              
	                dp[end].add(word);
	            }
	        }
	    }

	    List<String> resultList = new ArrayList<String>();

	    if(dp[s.length()] == null)
	    {
	    	return resultList;
	    } 

	    List<String> tempList = new ArrayList<String>();
	    wordBreakIIDfs(dp, resultList, tempList, s.length());
	 
	    return resultList;      
    }

    public void wordBreakIIDfs(List<String> dp[], List<String> resultList, List<String> tempList, int end)
    {
    	if(end <= 0)
    	{
    		String path = tempList.get(tempList.size() - 1);
    		for(int i = tempList.size() - 2; i >= 0;i--)
    		{
    			path = path + " " + tempList.get(i);
    		}

    		resultList.add(path);
    		return;
    	}

    	for(String str: dp[end])
    	{
    		tempList.add(str);
    		wordBreakIIDfs(dp, resultList, tempList, end - str.length());
    		tempList.remove(tempList.size() - 1);
    	}
    }

}