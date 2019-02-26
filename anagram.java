import java.util.*;

public class anagram
{
	public static void main(String[] args) 
	{
		anagram test = new anagram();
		String s1 = "anagram";
		String t1 = "nagaram";
		String s2 = "rat";
		String t2 = "car";

		System.out.println(s1 + " and " + t1 + " are anagram: " + test.isAnagramUnicode(s1, t1));
		System.out.println(s2 + " and " + t2 + " are anagram: " + test.isAnagramUnicode(s2, t2));
	
		String[] groupStrings = {"abc", "am"};
		System.out.println("group shifted strings: " + test.groupStrings(groupStrings));
	
		String[] anagramGroup = {"eat", "tea", "tan", "ate", "nat", "bat"};
		System.out.println("group anagram is: " + test.groupAnagrams(anagramGroup));
	}

	//if string only contains ASCII char
	public boolean isAnagram(String s, String t) 
	{	
		if(s.length() != t.length())
		{
			return false;
		}

		int [] resultArray = new int [256];

		for(int i = 0;i < s.length();i++)
		{
			resultArray[s.charAt(i)]++;
		}

		for(int i = 0;i < t.length();i++)
		{
			resultArray[t.charAt(i)]--;

			if(resultArray[t.charAt(i)] < 0)
			{
				return false;
			}
		}

		return true;
	}

	//if string extends to unicode
	public boolean isAnagramUnicode(String s, String t) 
	{	
		if(s.length() != t.length())
		{
			return false;
		}

		if(s.length() == 0)
		{
			return true;
		}

		HashMap<Character,Integer> resultMap = new HashMap<Character,Integer>();


		for(int i = 0;i < s.length();i++)
		{	
			if(!resultMap.containsKey(s.charAt(i)))
			{
				resultMap.put(s.charAt(i), 1);
			}
			else
			{
				resultMap.put(s.charAt(i), resultMap.get(s.charAt(i)) + 1);
			}
		}

		for(int i = 0;i < t.length();i++)
		{	

			if(!resultMap.containsKey(t.charAt(i)))
			{
				return false;
			}
			else
			{	
				if(resultMap.get(t.charAt(i)) == 0)
				{
					return false;
				}

				resultMap.put(t.charAt(i), resultMap.get(t.charAt(i)) - 1);
			}

		}

		return true;
	}

	public List<List<String>> groupAnagrams(String[] strings) 
	{
        List<List<String>> resultList = new ArrayList<List<String>>();

        if(strings == null || strings.length == 0)
        {
        	return resultList;
        }

        HashMap<String, List<String>> map = new HashMap<String, List<String>>();

        for(String s: strings)
        {
			char[] tempCharArray = s.toCharArray();
        	Arrays.sort(tempCharArray);
        	String tempString = new String(tempCharArray);

       		if(!map.containsKey(tempString))
        	{	
        		List<String> newList = new ArrayList<String>();
        		newList.add(s);
        		map.put(tempString, newList);
        	}
        	else
        	{
        		map.get(tempString).add(s);
        	}
        }

        for(List<String> list: map.values())
        {
        	Collections.sort(list);
       		resultList.add(list);
       	}

        return resultList;
    }

	public List<List<String>> groupStrings(String[] strings) 
	{
     	List<List<String>> resultList = new ArrayList<List<String>>();
     	
     	if(strings == null || strings.length == 0)
        {
        	return resultList;
        }

        HashMap<String, List<String>> map = new HashMap<String, List<String>>();
        
        for (String s : strings) 
        {
        	//need to add a delimiter to indicate 
        	//for example "abc" = 0#1#2, "am" = 0#12
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                int l = (s.charAt(i) - s.charAt(0) + 26) % 26;
                sb.append(l + "#");
          
            }

            String str = sb.toString();
            if (!map.containsKey(str)) {
                List<String> tem = new ArrayList<String>();
                map.put(str, tem);
            }
            map.get(str).add(s);
        }

        for (String m : map.keySet()) 
        {
            Collections.sort(map.get(m));
            resultList.add(map.get(m));
        }
        return resultList;   
    }
}