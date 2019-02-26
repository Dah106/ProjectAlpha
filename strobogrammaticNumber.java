import java.util.*;

public class strobogrammaticNumber
{
	public boolean isStrobogrammatic(String num) 
	{
		Map<Character, Character> map = new HashMap<Character, Character>();
	    map.put('6', '9');
	    map.put('9', '6');
	    map.put('0', '0');
	    map.put('1', '1');
	    map.put('8', '8');

	    int left = 0;
	    int right = num.length() - 1;

	    while(left <= right)
	    {
	    	if(!map.containsKey(num.charAt(left)))
	    	{
	    		return false;
	    	}

	    	if(map.get(num.charAt(left)) != num.charAt(right))
	    	{
	    		return false;
	    	}

	    	left++;
	    	right--;
	    }

	    return true;
	}

	public List<String> findStrobogrammatic(int n) 
	{
    	return findStrobogrammaticHelper(n, n);
	}

	public List<String> findStrobogrammaticHelper(int n, int m) {
	    if (n == 0) return new ArrayList<String>(Arrays.asList(""));
	    if (n == 1) return new ArrayList<String>(Arrays.asList("0", "1", "8"));

	    List<String> list = findStrobogrammaticHelper(n - 2, m);

	    List<String> res = new ArrayList<String>();

	    for (String s: list) 
	    {

	        if (n != m) res.add("0" + s + "0");

	        res.add("1" + s + "1");
	        res.add("6" + s + "9");
	        res.add("8" + s + "8");
	        res.add("9" + s + "6");
	    }

	    return res;
	}

	public int strobogrammaticInRange(String low, String high) 
	{
        int count = 0;
        List<String> rst = new ArrayList<String>();
        for(int n = low.length(); n <= high.length(); n++){
            rst.addAll(findStrobogrammaticHelper(n, n));
        }
        for(String num : rst){

            if((num.length() == low.length()&&num.compareTo(low) < 0 ) ||(num.length() == high.length() && num.compareTo(high) > 0)) continue;
                count++;
        }
        return count;
    }
}