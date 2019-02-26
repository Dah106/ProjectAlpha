import java.util.*;
/*
	Given two binary strings, return their sum (also a binary string).

    For example,
    a = "11"
    b = "1"
    Return "100".
*/
public class addBinaryLc67
{
	public static void main(String[] args) 
	{
		addBinaryLc67 test = new addBinaryLc67();
		String a = "11";
        String b = "11";
        System.out.println(test.addBinary(a,b));
	}

	public String addBinary(String a, String b) 
    {   
        if(a == null || a.isEmpty()) 
            return b;
        if(b == null || b.isEmpty()) 
            return a;


        StringBuilder stb = new StringBuilder();
        int i = a.length() - 1;
        int j = b.length() - 1;
        int aBit;
        int bBit;
        int carry = 0;
        int result;

        while(i >= 0 || j >= 0 || carry == 1) {
            aBit = (i >= 0) ? Character.getNumericValue(a.charAt(i--)) : 0;
            bBit = (j >= 0) ? Character.getNumericValue(b.charAt(j--)) : 0;
            result = aBit ^ bBit ^ carry;
            carry = ((aBit + bBit + carry) >= 2) ? 1 : 0;
            stb.append(result);
        }
        return stb.reverse().toString();
    }
}