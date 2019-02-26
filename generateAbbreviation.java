import java.util.*;

public class generateAbbreviation
{
	// Write a function to generate the generalized abbreviations of a word.
	// Given word = "word", return the following list (order does not matter):
	// ["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
	public List<String> generateAbbreviations(String word) 
	{
        List<String> resultList = new ArrayList<String>();

        if(word == null)
        {
        	return resultList;
        }

        generateAbbreviations(resultList, word, "", 0, 0);
        return resultList;
    }

    public void generateAbbreviations(List<String> resultList, String word, String currentString, int position, int abbrCount)
    {
    	if(position == word.length())
    	{

    		if(abbrCount > 0)
    		{
    			currentString = currentString + abbrCount;
    		}
    		
    		resultList.add(currentString);
    	}
    	else
    	{
    		generateAbbreviations(resultList, word, currentString, position + 1, abbrCount + 1);
    		generateAbbreviations(resultList, word, currentString + (abbrCount > 0 ? abbrCount : "") + word.charAt(position), position + 1, 0);
    	}
    }
}