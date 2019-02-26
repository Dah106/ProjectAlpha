import java.util.*;
public class hashTable
{
	public int hashCode(char[] key,int HASH_SIZE) 
    {
        long ans = 0;
        for(int i = 0; i < key.length;i++) {
            ans = (ans * 33 + (int)(key[i])) % HASH_SIZE; 
            ans %= HASH_SIZE;
        }
    	return (int)ans;
    }


    public char findTheDifference(String s, String t) {
        
        char result = b.charAt(b.length() - 1);
	    for (int i = 0; i < a.length(); i++) {
	        result += b.charAt(i);
	        result -= a.charAt(i);
	    }
	    return result;
    }

    public static void main(String[] args) {
    	
    	hashTable test = new hashTable();

    	String s = "abcd";
    	String t = "abecd";
    	System.out.println(test.findTheDifference(s, t));		
    }	
}