import java.util.*;

public class math
{   
    //x^n = x^(n / 2) * x^(n / 2) * x^(n % 2)
    public double myPow(double x, int n)
    {
        if (n < 0) 
        {
            return 1 / power(x, -n);
        } 
        else 
        {
            return power(x, n);
        }
    }

    public double power(double x, int n) 
    {   
        //base case
        if (n == 0)
        {
            return 1;
        }
     
        double v = power(x, n / 2);
     
        if (n % 2 == 0) 
        {
            return v * v;
        } 
        else 
        {
            return v * v * x;
        }
    }

    public boolean isPowerOfTwo(int n) 
    {
        //if n is power  of  2 , n just has one bit is 1
        return n > 0 && (n & (n - 1)) == 0;
    }

    public boolean isPowerOfThree(int n) 
    {
        if(n>1)
            while(n%3==0) n /= 3;
        return n==1;
    }

	public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) 
	{
        int areaOfSqrA = (C - A) * (D - B);
        int areaOfSqrB = (G - E) * (H - F);

        int left = Math.max(A, E);
        int right = Math.min(G, C);
        int bottom = Math.max(F, B);
        int top = Math.min(D, H);

        //If overlap
        int overlap = 0;
        if(right > left && top > bottom)
        {
             overlap = (right - left) * (top - bottom);
        }

        return areaOfSqrA + areaOfSqrB - overlap;
    }	

    public String convertToTitle(int n) 
    {
        StringBuilder result = new StringBuilder();

        while(n > 0)
        {
            n--;
            result.insert(0, (char)('A' + n % 26));
            n /= 26;
        }

        return result.toString();
    }

    //Sieve_of_Eratosthenes
    public int countPrimes(int n) 
    {
        boolean[] m = new boolean[n];
        int count = 0;
        for (int i = 2; i<n; i++) 
        {
            if (m[i])
            {
                continue;
            }

            count++;
            for (int j = i; j < n; j = j + i)
            {
                m[j] = true;
            }
        }

        return count;
    }

    public int[] plusOne(int[] digits) 
    {
        
        for(int i = digits.length - 1;i >= 0;i--)
        {
            if(digits[i] < 9)
            {
                digits[i]++;
                return digits;
            }

            if(digits[i] == 9)
            {
                digits[i] = 0;
                if(i == 0)
                {
                    int [] resultDigits = new int[digits.length + 1];
                    resultDigits[0] = 1;
                    System.arraycopy(digits, 0, resultDigits, 1, digits.length);
                    return resultDigits;
                }
            }
        }

        return digits;
    }

    // Write a function that takes an unsigned integer and returns the number of ’1' bits it has 
    // (also known as the Hamming weight).
    // For example, the 32-bit integer ’11' has binary representation 00000000000000000000000000001011, 
    // so the function should return 3.
    public int hammingWeight(int n) 
    {   //A = 001011
        //A >>> 1 -> 000101
        int ones = 0;
        while(n != 0) 
        {   
            //unsigned right shift
            ones = ones + (n & 1);
            n = n >>> 1;
        }
        return ones;
    }

    // Reverse bits of a given 32 bits unsigned integer.
    // For example, given input 43261596 (represented in binary as 00000010100101000001111010011100), 
    // return 964176192 (represented in binary as 00111001011110000010100101000000).
    // Follow up:
    // If this function is called many times, how would you optimize it?
    public int reverseBits(int n) 
    {
        int reversed = 0;
        for (int i = 0; i < 32; i++) 
        {
            reversed = (reversed << 1) | (n & 1);
            n = (n >>> 1);
        }
        return reversed;
    }

    // Validate if a given string is numeric.
    // Some examples:
    // "0" => true
    // " 0.1 " => true
    // "abc" => false
    // "1 a" => false
    // "2e10" => true
    // Note: It is intended for the problem statement to be ambiguous. 
    // You should gather all requirements up front before implementing one.
    public boolean isNumber(String s) 
    {
        if(s == null)
        {
            return false;
        }  

        s = s.trim();  
        if(s.length() == 0)
        {
            return false;
        }  
        
        boolean dotFlag = false;  
        boolean eFlag = false;  
        for(int i = 0;i < s.length();i++)  
        {
            switch(s.charAt(i))  
            {  
                case '.':  
                    //the number is not valid if there are more than 1 '.' in the string
                    //or if there exists a 'e/E' in the string
                    //or if its predecessor is not a digit
                    //or if its successor is not a digit
                    if(dotFlag || eFlag   
                    || ((i == 0 || !(s.charAt(i - 1) >= '0' && s.charAt(i - 1) <= '9'))   
                        && (i == s.length() - 1 || !(s.charAt(i + 1) >= '0' && s.charAt(i + 1) <= '9'))))  
                    {
                        return false;
                    }

                    dotFlag = true;  
                    break;  
                case '+':  
                case '-':  
                    //the number is not valid if '+/-" is not at the first position of the string 
                    //and its predecessor is not 'e/E'
                    //or if it is placed at the last position of the string
                    //or if its successor is not digit or '.'
                    if((i > 0 && (s.charAt(i - 1) != 'e' && s.charAt(i - 1) != 'E'))  
                      || (i == s.length() - 1 || !(s.charAt(i + 1) >= '0' && s.charAt(i + 1) <='9'|| s.charAt(i + 1) == '.')))
                      {
                        return false;
                      }   
                    break;  
                case 'e':  
                case 'E':
                    //the number is not valid if there are more than 1 'e/E' in the string
                    //or if 'e/E' appear in the first or the last position of the string
                    if(eFlag || i == s.length() - 1 || i == 0)
                    {
                        return false;
                    } 

                    eFlag = true;  
                    break;  
                case '0':  
                case '1':  
                case '2':  
                case '3':  
                case '4':  
                case '5':  
                case '6':  
                case '7':  
                case '8':  
                case '9':  
                    break;  
                default:  
                    return false;  
            }
        }

        return true;
    }

    public boolean isNumberUsingHashSet(String s) 
    {
        if (s == null || s.trim().length() == 0) {
            return false;
        }
 
        s = s.trim();
        int n = s.length();
 
        // Prepare the valid characters
        char[] chars = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', '.', 'e', 'E', '+', '-'};
        Set<Character> set = new HashSet<Character>();
        for (char ch : chars) {
            set.add(ch);
        }
 
        // Define flags
        int opCount = 0;
 
        boolean hasE = false;
        boolean hasNum = false;
        boolean hasPoint = false;
 
        // Go through the characters
        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
 
            // invalid character
            if (!set.contains(ch)) {
                return false;
            }
 
            // number
            if (ch >= '0' && ch <= '9') {
                hasNum = true;
            }
 
            // E
            if (ch == 'e' || ch == 'E') {
                if (hasE || !hasNum) return false;
                if (i == n - 1) return false;
                hasE = true;
            }
 
            // decimal point
            if (ch == '.') 
            {
                if (hasPoint || hasE) return false;
                if (i == n - 1 && !hasNum) return false;
                hasPoint = true;
            }
 
            // sign
            if (ch == '+' || ch == '-') 
            {
                if (opCount == 2) return false;
                if (i == n - 1) return false;
                if (i > 0 && !hasE) return false;
                opCount++;
            }
        }
 
        return true;
    }

    // Given a non negative integer number num. 
    // For every numbers i in the range 0 ≤ i ≤ num 
    // calculate the number of 1's in their binary representation and return them as an array.
    // Example:
    // For num = 5 you should return [0,1,1,2,1,2].
    // A NAIVE SOLUTION !
    // Time: O(N * log(sizeOf(integer)))
    // Space: O(N)
    public int[] countBits(int num)
    {
        int[] result = new int[num+1];
 
        for(int i=0; i<=num; i++)
        {
            result[i] = countEach(i);
        }
     
        return result;
    }

    public int countEach(int num)
    {
        int result = 0;
     
        while(num!=0)
        {
            if(num % 2 == 1)
            {
                result++;
            }

            num = num / 2;
        }
     
        return result;
    }

    // For number 2(10), 4(100), 8(1000), 16(10000), ..., the number of 1's is 1. 
    // Any other number can be converted to be 2^m + x. 
    // For example, 9=8+1, 10=8+2. The number of 1's for any other number is 1 + # of 1's in x.
    // Time: O(N)
    // Space: O(N)
    public int[] countBitsOptimized(int num)
    {
        int[] result = new int[num+1];
 
        for(int i=1; i <= num; i++)
        {
            result[i] = i % 2 + result[i / 2];
     
        }
     
        return result;
    }

}