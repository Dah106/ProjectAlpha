import java.util.*;

public class bullsAndCows
{
	public String getHint(String secret, String guess) 
	{
       	int bulls = 0;
	    int cows = 0;
	    int[] count = new int[10];
	    for (int i = 0; i < secret.length(); i++) 
	    {	
	    	char a = secret.charAt(i);
	    	char b = guess.charAt(i);
	        if (a == b) 
	        {
	        	bulls++;
	        }
	        else 
	        {
	            //if prev part of guess has curr digit in secret
	            //then we found a pair that has same digit with different position
	            if(count[a - '0'] < 0) cows ++;

	            //if prev part of secret has curr digit in guess
	            //then we found a pair that has same digit with different position                
	            if(count[b - '0'] > 0) cows ++;

	            count[a - '0'] ++;
	            count[b - '0'] --;
	        }
	    }
	    return bulls + "A" + cows + "B";
    }
}