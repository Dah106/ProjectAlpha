import java.util.*;

public class palindrome
{	
	public static void main(String[] args) 
	{
		palindrome test = new palindrome();

		String s1 = "code";
		String s2 = "aab";
		String s3 = "carerac";

		System.out.println(test.canPermutePalindrome(s1));
		System.out.println(test.canPermutePalindrome(s2));
		System.out.println(test.canPermutePalindrome(s3));


		String s4 = "aabb";
		String s5 = "abc";
		String s6 = "aabbcc";

		System.out.println("palindrome permutation: " + test.canPermutePalindromeUseSet(s4));
		System.out.println("palindrome permutation: " + test.canPermutePalindromeUseSet(s5));

		System.out.println("palindrome permutation II: " + test.generatePalindromes(s4));
		System.out.println("palindrome permutation II: " + test.generatePalindromes(s5));
		System.out.println("palindrome permutation II: " + test.generatePalindromes(s6));

        String s7 = "abcdzdcab";
        System.out.println("longest palindrome substring is: " + test.longestPalindrome(s7));

	}

	public boolean isPalindrome(String s)
    {
    	if(s == null || s.length() == 0)
		{
			return true;
		}
        
        s=s.toLowerCase().replaceAll("[^a-z0-9]", "");
        
        for (int i = 0; i < s.length()/2; i++) 
        {
            if (s.charAt(i) != s.charAt(s.length() - 1 - i)) 
            {
            return false;
            }
        }
     
        return true;
    }

	public static boolean isValidPalindromeTwoPointer(String s)
    {

		if(s==null||s.length()==0) return true;
 
		s = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
		System.out.println(s);
 
		for(int i = 0; i < s.length() ; i++)
		{
			if(s.charAt(i) != s.charAt(s.length() - 1 - i)){
				return false;
			}
		}
 
		return true;
	}

    public boolean isAlphaOrDigits(char c)
    {
    	if(c >= 'a' && c <= 'z')
    	{
    		return true;
    	}

    	if(c >= '0' && c <= '9')
    	{
    		return true;
    	}

    	return false;
    }


	// Given a string, determine if a permutation of the string could form a palindrome.
	// For example,
	// "code" -> False, "aab" -> True, "carerac" -> True.
	public boolean canPermutePalindrome(String s) 
	{
		if(s == null || s.length() == 0)
		{
			return true;
		}

    	HashMap<Character, Integer> map = new HashMap<Character, Integer>();
    	for(int i = 0;i < s.length();i++)
    	{
    		char tempChar = s.charAt(i);

    		if(!map.containsKey(tempChar))
    		{
    			map.put(tempChar, 1);
    		}
    		else
    		{
    			map.put(tempChar, map.get(tempChar) + 1);
    		}
    	}

    	int oddCount = 0;
    	for(int frequency: map.values())
    	{
    		if(frequency%2 == 1)
    		{
    			oddCount++;
    		}

    		if(oddCount > 1)
    		{
    			return false;
    		}

    	}

    	return true;
    }

    public boolean canPermutePalindromeUseSet(String s) 
	{
		Set<Character> set = new HashSet<Character>();
	    for (char c : s.toCharArray()) 
	    { 
	        if (set.contains(c)) 
	        {
	        	set.remove(c);// If char already exists in set, then remove it from set
	        }
	        else 
	        {
	        	set.add(c);// If char doesn't exists in set, then add it to set
	        }
	    }

	    return set.size() <= 1;
    }

    // Given a string s, return all the palindromic permutations (without duplicates) of it. 
    // Return an empty list if no palindromic permutation could be form.
    public List<String> generatePalindromes(String s) 
    {	
        List<String> resultList = new ArrayList<String>();

        if(s == null || s.length() == 0)
		{
			return resultList;
		}

        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        
        int oddCount = 0;
    	for(int i = 0;i < s.length();i++)
    	{
    		char tempChar = s.charAt(i);

    		if(!map.containsKey(tempChar))
    		{
    			map.put(tempChar, 1);
    		}
    		else
    		{
    			map.put(tempChar, map.get(tempChar) + 1);
    		}

    		oddCount += map.get(tempChar) % 2 != 0 ? 1 : -1;
    	}

    	//if there are more than 1 characters that occur oddly
    	//no palindromic permutation
    	if(oddCount > 1)
    	{
    		return resultList;
    	}
    	
    	List<Character> list = new ArrayList<>();
    	String midChar = "";
    	
    	for(char c: map.keySet())
    	{	
    		Integer frequency = map.get(c);

    		if(frequency % 2 == 1)
    		{
    			midChar += c;
    		}	

    		for(int j = 0;j < frequency / 2;j++)
    		{
    			list.add(c);
    		}
    	}

    	boolean[] visited = new boolean[s.length()];
    	StringBuilder currentString = new StringBuilder(); 

    	generatePalindromesDfs(list, midChar, currentString, visited, resultList);

        return resultList;
    }

    

    public void generatePalindromesDfs(List<Character> list, String midChar,
    	StringBuilder currentString, boolean[] visited, List<String> resultList)
    {
    		//System.out.println("currentString: " + currentString + " listsize: " + list.size() + " midChar: " + midChar);
	    	if (currentString.length() == list.size()) 
	    	{
		        // form the palindromic string
		        resultList.add(currentString.toString() + midChar + currentString.reverse().toString());
		        currentString.reverse();
		        return;
		    }

	    	for (int i = 0; i < list.size(); i++) 
	    	{
		        // avoid duplication
		        if (i > 0 && list.get(i) == list.get(i - 1) && !visited[i - 1]) continue;

		        if (!visited[i]) 
		        {
		            visited[i] = true; 
		            currentString.append(list.get(i));
		            // recursion
		            generatePalindromesDfs(list, midChar, currentString, visited, resultList);
		            // backtracking
		            visited[i] = false; 
		            currentString.deleteCharAt(currentString.length() - 1);
		        }
		    }
    }

    // Given a string S, find the longest palindromic substring in S. 
    // You may assume that the maximum length of S is 1000, 
    // and there exists one unique longest palindromic substring.
    // Time: O(n^2)
    // Space: O(1) 
    public String longestPalindrome(String s) 
    {
        if (s.length() == 1) 
        {
            return s;
        }
     
        String longest = s.substring(0, 1);
        for (int i = 0; i < s.length(); i++) 
        {
            // get longest palindrome with center of i
            // odd number palindrome
            String tmp = longestPalindromeHelper(s, i, i);
            if (tmp.length() > longest.length()) {
                longest = tmp;
            }
     
            // get longest palindrome with center of i, i+1
            tmp = longestPalindromeHelper(s, i, i + 1);
            if (tmp.length() > longest.length()) {
                longest = tmp;
            }
        }
     
        return longest;
    }
 
    // Given a center, either one letter or two letter, 
    // Find longest palindrome
    public String longestPalindromeHelper(String s, int begin, int end) 
    {
        while (begin >= 0 && end <= s.length() - 1 && s.charAt(begin) == s.charAt(end)) {
            begin--;
            end++;
        }
        return s.substring(begin + 1, end);
    }

    //Time: O(N^2)
    //Space: O(N^2)
    public String longestPalindromeDP(String s)
    {
     
        if (s.length() == 1) 
        {
            return s;
        }


          int n = s.length();
          int palindromeBeginsAt = 0; //index where the longest palindrome begins
          int max_len = 1;//length of the longest palindrome
          boolean palindrome[][] = new boolean[n][n]; //boolean table to store palindrome truth
          
          //Trivial case: single letter palindromes
          for (int i = 0; i < n; i++) 
          {
              palindrome[i][i] = true;
          }
          
          //Finding palindromes of two characters.
          for (int i = 0; i < n-1; i++) 
          {
            if (s.charAt(i) == s.charAt(i+1)) 
            {
              palindrome[i][i+1] = true;
              palindromeBeginsAt = i;
              max_len = 2;
            }
          }
          
          //Finding palindromes of length 3 to n and saving the longest
          for (int curr_len = 3; curr_len <= n; curr_len++) 
          {
            for (int i = 0; i < n-curr_len+1; i++) 
            {
              int j = i+curr_len-1;
              if (s.charAt(i) == s.charAt(j) //1. The first and last characters should match 
                  && palindrome[i+1][j-1]) //2. Rest of the substring should be a palindrome
              {
                palindrome[i][j] = true; 
                palindromeBeginsAt = i;
                max_len = curr_len;
              }
            }
          }
          return s.substring(palindromeBeginsAt, max_len + 1);
    }

    // Given a list of unique words. Find all pairs of distinct indices (i, j) in the given list,
    // so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.
    // Example 1:
    // Given words = ["bat", "tab", "cat"]
    // Return [[0, 1], [1, 0]]
    // The palindromes are ["battab", "tabbat"]
    // Example 2:
    // Given words = ["abcd", "dcba", "lls", "s", "sssll"]
    // Return [[0, 1], [1, 0], [3, 2], [2, 4]]
    // The palindromes are ["dcbaabcd", "abcddcba", "slls", "llssssll"]
    public List<List<Integer>> palindromePairs(String[] words) 
    {
        List<List<Integer>> resultList = new ArrayList<List<Integer>>();

        if(words == null || words.length == 0)
        {
            return resultList;
        }

        HashMap<String, Integer> map = new HashMap<String, Integer>();
        for(int i = 0;i < words.length;i++)
        {
            map.put(words[i], i);
        }

        for(int i = 0;i < words.length;i++)
        {
            String wordReverse = new StringBuilder(words[i]).reverse().toString();
                
            // check if the reverse of the word is in the map
            if(map.containsKey(wordReverse) && map.get(wordReverse) != i)
            {
                List<Integer> tempList = new ArrayList<Integer>();
                tempList.add(i);
                tempList.add(map.get(wordReverse));
                resultList.add(tempList);
            }

            // check if there is empty string
            // in that case if word is palindrome, empty string can be added
            // before and after the word.
            if (map.containsKey("")
                    && map.get("") != i
                    && isPalindrome(words[i])) 
            {
                List<Integer> list = new ArrayList<>();
                list.add(i);
                list.add(map.get(""));
                resultList.add(list);
                list = new ArrayList<>();
                list.add(map.get(""));
                list.add(i);
                resultList.add(list);
            }

            // check if some other word can be added as prefix
            int curReverse = 0;
            while (curReverse < wordReverse.length() - 1) 
            {
                if (map.containsKey(wordReverse.substring(0, curReverse + 1))) 
                {
                    String rem = words[i].substring(0, words[i].length()
                            - curReverse - 1);
                    if (isPalindrome(rem)) 
                    {
                        List<Integer> list = new ArrayList<>();
                        list.add(map.get(wordReverse.substring(0, curReverse + 1)));
                        list.add(i);
                        resultList.add(list);
                    }
                }
                curReverse++;
            }

            // check if some other word can be added as suffix
            int curForward = 0;
            while (curForward < words[i].length() - 1) 
            {
                String rev = new StringBuilder(words[i].substring(0,
                        curForward + 1)).reverse().toString();
                if (map.containsKey(rev)) 
                {
                    String rem = words[i].substring(curForward + 1);
                    if (isPalindrome(rem)) 
                    {
                        List<Integer> list = new ArrayList<>();
                        list.add(i);
                        list.add(map.get(rev));
                        resultList.add(list);
                    }
                }
                curForward++;
            }

        }

        return resultList;
    }

    // public int longestPalindromeSubsequence(String s)
    // {   
     
    //     if (s.length() == 1) {
    //         return s;
    //     }
        
    //     int T[][] = new int[s.length()][s.length()];
    //     for(int i=0; i < s.length(); i++)
    //     {
    //         T[i][i] = 1;
    //     } 
    //     for(int l = 2; l <= s.length(); l++)
    //     {
    //         for(int i = 0; i < s.length()-l + 1; i++)
    //         {
    //             int j = i + l - 1;
    //             if(l == 2 && s[i] == s[j]){
    //                 T[i][j] = 2;
    //             }else if(s[i] == s[j]){
    //                 T[i][j] = T[i + 1][j-1] + 2;
    //             }else{ 
    //                 T[i][j] = Math.max(T[i + 1][j], T[i][j - 1]);
    //             } 
    //         } 
    //     } 
    //     return T[0][s.length() - 1];
    // } 

}