import java.util.*;

public class pascalTriangle
{	

	public static void main(String[] args) 
	{
		pascalTriangle test = new pascalTriangle();
		System.out.println(test.generate(3));	
        System.out.println(test.getRow(3));
	}
    
	public List<List<Integer>> generate(int numRows)
    {
        List<List<Integer>> resultList = new ArrayList<List<Integer>>();
        
        for (int i = 0; i < numRows; i++)
        {
            List<Integer> tempList = new ArrayList<Integer>();
            
            for (int j = 0; j < i + 1; j++)
            {
                if (j == 0 || j == i)
                {
                    tempList.add(1);
                }
                else
                {
                    tempList.add(resultList.get(i-1).get(j-1)+resultList.get(i-1).get(j));
                }
            }
            resultList.add(tempList);
        }
        
        return resultList;
    }

    public List<Integer> getRow(int rowIndex) 
    {
        List<Integer> resultRow = new ArrayList<Integer>();
        
        if(rowIndex <= 0) 
        {
            return resultRow;
        }

        resultRow.add(1);

        for (int i = 1; i <= rowIndex; i++)
        {   
            resultRow.add(1);

            for (int j = i - 1; j > 0; j--)
            {
                resultRow.set(j, resultRow.get(j-1)+resultRow.get(j)); 
            }
        }

        return resultRow;
    }
}