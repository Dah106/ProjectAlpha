import java.util.*;

public class flipGame
{
	public static void main(String[] args) 
	{
		flipGame test = new flipGame();

		String s = "++++";	

		System.out.println("flip game: " + test.generatePossibleNextMoves(s));
	}

	public List<String> generatePossibleNextMoves(String s) 
	{
        List<String> resultList = new ArrayList<String>();

        if(s == null || s.length() == 0)
        {
        	return resultList;
        }

        StringBuilder tempString = new StringBuilder(s);

        for(int i = 1;i < s.length();i++)
        {
        	if(s.charAt(i) == '+' && s.charAt(i - 1) == '+')
        	{
        		tempString.setCharAt(i, '-');
        		tempString.setCharAt(i - 1, '-');
        		resultList.add(tempString.toString());
        		tempString = new StringBuilder(s);
        	}
        }

        return resultList;
    }

    public boolean canWin(String s) 
    {	
    	if(s == null || s.length() == 0)
        {
        	return false;
        }

        return canWinBackTracking(s);
    }

    //use backtracking 
    public boolean canWinBackTracking(String s)
    {	
    	StringBuilder buffer = new StringBuilder(s);
    	for(int i = 1;i < s.length();i++)
    	{
    		if(s.charAt(i) == s.charAt(i - 1) && s.charAt(i - 1)=='+')
    		{
    			buffer.setCharAt(i,'-');
                buffer.setCharAt(i - 1,'-');
                if(!canWinBackTracking(buffer.toString())) return true;
                buffer.setCharAt(i,'+');
                buffer.setCharAt(i - 1,'+');
    		}
    	}

    	return false;
    }
}