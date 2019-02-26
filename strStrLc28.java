import java.util.*;
/*
	Implement strStr().

    Returns the index of the first occurrence of needle in haystack, 

    or -1 if needle is not part of haystack.
*/
public class strStrLc28
{
	public static void main(String[] args) 
	{
		strStrLc28 test = new strStrLc28();
		String source = "abcdefghijk";
        String target = "jk";
		System.out.println(test.strStrKMP(source, target));
	}

    //Naive implementation
	public int strStr(String source, String target) 
    {
        int result = -1;
        if(source == null || target == null)
        {
            return -1;
        }

        //if source string is shorter than target string
        if(source.length() < target.length())
        {
            return -1;
        }

        for(int i = 0;i <= source.length() - target.length();i++)
        {
            if(target.equals(source.substring(i, i + target.length())))
            {
                return i;
            }
        }

        return result;    
    }

    //KMP Algorithm
    public int strStrKMP(String haystack, String needle) 
    {
        if(haystack==null || needle==null)    
            return 0;
 
        int h = haystack.length();
        int n = needle.length();
     
        if (n > h)
            return -1;
        if (n == 0)
            return 0;
     
        int[] next = getNext(needle);
        int i = 0;
     
        while (i <= h - n) {
            int success = 1;
            for (int j = 0; j < n; j++) {
                if (needle.charAt(0) != haystack.charAt(i)) {
                    success = 0;
                    i++;
                    break;
                } else if (needle.charAt(j) != haystack.charAt(i + j)) {
                    success = 0;
                    i = i + j - next[j - 1];
                    break;
                }
            }
            if (success == 1)
                return i;
        }
 
        return -1;
    }
 
    //calculate KMP array
    public int[] getNext(String needle) 
    {
        int[] next = new int[needle.length()];
        next[0] = 0;
     
        for (int i = 1; i < needle.length(); i++) {
            int index = next[i - 1];
            while (index > 0 && needle.charAt(index) != needle.charAt(i)) 
            {
                index = next[index - 1];
            }
     
            if (needle.charAt(index) == needle.charAt(i)) {
                next[i] = next[i - 1] + 1;
            } else {
                next[i] = 0;
            }
        }
     
        return next;
    }

}