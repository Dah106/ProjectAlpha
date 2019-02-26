public class uglyNumber
{
	public static void main(String[] args) 
    {
        uglyNumber test = new uglyNumber();
        System.out.println("the 10th ugly number is: " + test.nthUglyNumber(10));    
    }
	public boolean isUgly(int num) 
	{
        
        if(num == 1) 
        {	
        	return true;
        }
        
        if(num <= 0) 
        {
        	return false;
        }

        while(num % 2 == 0) 
        {
        	num = num / 2;
        }

        while(num % 3 == 0) 
        {
        	num=num/3;
        }

        while(num % 5 == 0) 
        {
        	num = num / 5;
        }
        
        return num == 1;
    }

    //Write a program to find the n-th ugly number.
    //Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. 
    //For example, 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.
    public int nthUglyNumber(int n) 
    {
        if(n == 1)
        {
            return 1;
        }

        // 1*2, 2*2, 3*2, 4*2, 5*2, 6*2, 8*2, etc..
        // 1*3, 2*3, 3*3, 4*3, 5*3, 6*3, 8*3, etc..
        // 1*5, 2*5, 3*5, 4*5, 5*5, 6*5, 8*5, etc...
        int [] uglyNumber = new int[n];
        int i = 0;//for list 1
        int j = 0;//for list 2
        int k = 0;//for list 3

        uglyNumber[0] = 1;
        for(int index = 1;index < n;index++)
        {   
            int n1 = uglyNumber[i] * 2;
            int n2 = uglyNumber[j] * 3;
            int n3 = uglyNumber[k] * 5;

            int nthNum = Math.min(Math.min(n1, n2), n3);
            uglyNumber[index] = nthNum;
            if(nthNum == n1)
            {
                i++;
            }

            if(nthNum == n2)
            {
                j++;
            }

            if(nthNum == n3)
            {
                k++;
            }
        }

        return uglyNumber[n - 1];
    }
}