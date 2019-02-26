import java.util.*;

public class palindromePartitioning
{	
	// Given a string s, partition s such that every substring of the partition is a palindrome.
	// Return all possible palindrome partitioning of s.
	// For example, given s = "aab",
	// [
	//    ["aa","b"],
 	//    ["a","a","b"]
 	// ]
	public List<List<String>> partition(String s) 
	{
        List<List<String>> resultList = new ArrayList<List<String>>();
        List<String> tempList = new ArrayList<String>();

        if(s == null || s.length() == 0)
        {
        	return resultList;
        }

        partitionDFS(resultList, tempList, s);

        return resultList;
    }

    public void partitionDFS(List<List<String>> resultList, List<String> tempList, String s)
    {
    	if(s.length() == 0)
    	{
    		resultList.add(new ArrayList<String>(tempList));
    		return;
    	}

    	for(int i = 0;i < s.length();i++)
    	{	
    		String tempStr = s.substring(0, i + 1);//"aab" -> "a", i = 0; "ab", i = 1
    		//only add to the list if the tempStr is a valid palindrome
    		if(isPalindrome(tempStr))
    		{
	    		tempList.add(tempStr);
	    		partitionDFS(resultList, tempList, s.substring(i + 1));//start at next index which is i + 1
	    		tempList.remove(tempList.size() - 1);
	    	}
    	}
    }

    public boolean isPalindrome(String s)
    {
    	int start = 0;
    	int end = s.length() - 1;
    	while(start <= end)
    	{	
    		if(s.charAt(start) != s.charAt(end))
    		{
    			return false;
    		}
    		start++;
    		end--;
    	}

    	return true;
    }

    public List<List<String>> palindromePartitioningDP(String s) 
    {
 
		List<List<String>> resultList = new ArrayList<>();
		List<String> tempList = new ArrayList<String>();

		int n = s.length();
        boolean[][] dp = new boolean[n][n];

        //every single char is a palindrome
        for(int i = 0;i < n;i++)
        {
        	dp[i][i] = true;
        }
        
        for(int i = 1; i < n; i++) 
        {	
            for(int j = 0; j <= i; j++) 
            {
                if(s.charAt(i) == s.charAt(j) && (i - j <= 2 || dp[j + 1][i - 1])) 
                {
                    dp[j][i] = true;
                }
            }
        }

        palindromePartitioningDPHelper(resultList, tempList, s, 0, dp);
        return resultList;
	}

	public void palindromePartitioningDPHelper(List<List<String>> resultList, List<String> tempList, String s, int start, boolean[][] dp)
	{
		if(start == s.length())
		{
			resultList.add(new ArrayList<>(tempList));
			return;
		}

		for(int i = start;i < s.length();i++)
		{
			if(dp[start][i])
			{
				tempList.add(s.substring(start, i + 1));
				palindromePartitioningDPHelper(resultList, tempList, s, i + 1, dp);
				tempList.remove(tempList.size() - 1);
			}
		}
	}

    public int minCut(String s) 
    {	
    	int n = s.length();
    	boolean[][] dp = new boolean[n][n];
    	int [] cut = new int[n];

    	for(int i = 0;i < n;i++)
    	{	
    		int min = i;//Initialize min cut with max count, "a" has max cut 1, "aab" has max cut 3
    		for(int j = 0;j <= i;j++)
    		{
    			if(s.charAt(j) == s.charAt(i) && (i - j <= 1 || dp[j + 1][i - 1]))
    			{	
    				dp[j][i] = true;
    				//if [0...i] is palindrome
    				//no need to cut
    				if(j == 0)
    				{
    					min = 0;
    				}
    				else
    				{
    					min = Math.min(min, cut[j - 1] + 1);
    				}
    			}
    		}

    		cut[i] = min;
    	}

        return cut[n - 1];
    }
}