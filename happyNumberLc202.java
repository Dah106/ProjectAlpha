import java.util.*;

public class happyNumberLc202 
{
	public static void main(String[] args) 
	{
		happyNumberLc202 test = new happyNumberLc202();
		System.out.println(test.isHappy(11));	
	}

    public boolean isHappy(int n) 
    {
     	HashSet<Integer> hash = new HashSet<Integer>();
        while (n != 1) 
        {	
        	//detects if there is a potential infinite loop
            if (hash.contains(n)) 
            {
                return false;
            }

            hash.add(n);
            n = squareSumOfDigits(n);
        }

        return true;
    }
    
    public int squareSumOfDigits(int number)
    {
    	int result = 0;
    	int temp = 0;
    	while(number != 0)
    	{	
    		temp = number % 10;
    		result += temp * temp;
    		number = number / 10;
    	}

    	return result;
    }

}