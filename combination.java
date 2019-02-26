import java.util.*;

public class combination
{
	public static void main(String[] args) 
	{	
		combination test = new combination();

		int n = 4;
		int k = 2;	

		List<List<Integer>> combineList = test.combine(4, 2);
		System.out.println("the combine list: " + combineList);


        int [] candidatesComboSum = {2, 3, 6, 7};
        int target1 = 7;
 
        List<List<Integer>> combineSumList = test.combinationSum(candidatesComboSum, target1);
        System.out.println("the combine sum list: " + combineSumList);

        int [] candidatesComboSum2 = {10, 1, 2, 7, 6, 1, 5};
        int target2 = 8;
 
        List<List<Integer>> combineSumList2 = test.combinationSum2(candidatesComboSum2, target2);
        System.out.println("the combine sum 2 list: " + combineSumList2);

        List<List<Integer>> combineSumList3 = test.combinationSum3(3, 9);
        System.out.println("the combine sum 3 list: " + combineSumList3);


        List<String> letterCombination = test.letterCombinations("23");
        System.out.println("letter combination of 2 is: " + letterCombination);

        // List<List<Integer>> factorCombination = test.getFactors(12);
        // System.out.println("factor sum of 4 is: " + factorCombination);

        // List<String> parenthesesCombination = test.generateParenthesis(2);
        // System.out.println("parentheses combination is: " + parenthesesCombination);
	} 

	//Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
	public List<List<Integer>> combine(int n, int k) 
	{	
		List<List<Integer>> resultList = new ArrayList<List<Integer>>(); 

        if(n <= 0 || n < k || k == 0)
        {
        	return resultList;
        }

        List<Integer> eachCombo = new ArrayList<Integer>();
        combineDfs(n, k, 1, eachCombo, resultList);
        
        return resultList;
    }	

    public void combineDfs(int n, int k, int start, List<Integer> eachCombo, List<List<Integer>> resultList)
    {	
    	//if the combine list has been filled up to k
    	if(eachCombo.size() == k)
    	{
    		resultList.add(new ArrayList<Integer>(eachCombo));
    		return;
    	}

    	for(int i = start;i <= n;i++)
    	{
    		eachCombo.add(i);
    		combineDfs(n, k, i + 1, eachCombo, resultList);
    		eachCombo.remove(eachCombo.size() - 1);
    	}
    }

    public List<List<Integer>> combineIterative(int n, int k) 
    {
        List<List<Integer>> resultList = new ArrayList<List<Integer>>();
        

        if(n <= 0 || n < k || k == 0)
        {
            return resultList;
        }   

        for(int i = 1;i <= n;i++)
        {
            List<Integer> eachCombo = new ArrayList<Integer>();
            eachCombo.add(i);
            resultList.add(eachCombo);
        }

        for(int i = 2;i <= k;i++)
        {
            List<List<Integer>> newCombs = new ArrayList<>();
            for(int j = i;j <= n;j++)
            {
                for(List<Integer> comb: resultList)
                {
                    if(comb.get(comb.size() - 1) < j)
                    {
                        List<Integer> newComb = new ArrayList<>(comb);
                        newComb.add(j);
                        newCombs.add(newComb);
                    }
                }
            }
            resultList = newCombs;
        }

        return resultList;
    }

    // For example, given candidate set 2,3,6,7 and target 7, 
    // A solution set is: 
    // [7] 
    // [2, 2, 3]
    public List<List<Integer>> combinationSum(int[] candidates, int target) 
    {
        List<List<Integer>> resultList = new ArrayList<List<Integer>>(); 

        if(candidates == null || candidates.length == 0)
        {
            return resultList;
        }   

        Arrays.sort(candidates);

        List<Integer> combineSum = new ArrayList<Integer>();

        combinationSumDfs(candidates, target, 0, combineSum, resultList);
        
        return resultList;
    }

    public void combinationSumDfs(int[] candidates, int target, int start, List<Integer> combineSum, List<List<Integer>> resultList)
    {   

        if(target == 0)
        {
            resultList.add(new ArrayList<Integer>(combineSum));
            return;
        }

        if(target < 0)
        {
            return;
        }

        for(int i = start;i < candidates.length;i++)
        {   
            //skip repeated values as we have already try duplicate values
            if(i > start && candidates[i] == candidates[i - 1])
            {
                continue;
            }
            combineSum.add(candidates[i]);
            combinationSumDfs(candidates, target - candidates[i], i, combineSum, resultList);
            combineSum.remove(combineSum.size() - 1);
        }

    }

    
    // Given a collection of candidate numbers (C) and a target number (T), find all unique combinations in C where the candidate numbers sums to T.

    // Each number in C may only be used once in the combination.

    // Note:
    // All numbers (including target) will be positive integers.
    // Elements in a combination (a1, a2, … , ak) must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak).
    // The solution set must not contain duplicate combinations.

    // For example, given candidate set 10,1,2,7,6,1,5 and target 8, 
    // A solution set is: 
    // [1, 7] 
    // [1, 2, 5] 
    // [2, 6] 
    // [1, 1, 6]
    public List<List<Integer>> combinationSum2(int[] candidates, int target) 
    {
        List<List<Integer>> resultList = new ArrayList<List<Integer>>(); 

        if(candidates == null || candidates.length == 0)
        {
            return resultList;
        }   

        Arrays.sort(candidates);

        List<Integer> combineSum = new ArrayList<Integer>();

        combinationSum2Dfs(candidates, target, 0, combineSum, resultList);
        
        return resultList;    
    }


    public void combinationSum2Dfs(int[] candidates, int target, int start, List<Integer> combineSum, List<List<Integer>> resultList)
    {
        if(target == 0)
        {
            resultList.add(new ArrayList<Integer>(combineSum));
            return;
        }

        if(target < 0)
        {
            return;
        }

        for(int i = start;i < candidates.length;i++)
        {   
            //skip repeated values as we have already try duplicate values
            if(i > start && candidates[i] == candidates[i - 1])
            {
                continue;
            }
            combineSum.add(candidates[i]);
            combinationSum2Dfs(candidates, target - candidates[i], i + 1, combineSum, resultList);
            combineSum.remove(combineSum.size() - 1);
        }
    }

    // Find all possible combinations of k numbers that add up to a number n, given that only numbers from 1 to 9 can be used and each combination should be a unique set of numbers.

    // Ensure that numbers within the set are sorted in ascending order.

    // Example 1:

    // Input: k = 3, n = 7

    // Output:

    // [[1,2,4]]

    // Example 2:

    // Input: k = 3, n = 9

    // Output:

    // [[1,2,6], [1,3,5], [2,3,4]]
    public List<List<Integer>> combinationSum3(int k, int n) 
    {
        List<List<Integer>> resultList = new ArrayList<List<Integer>>(); 
        
        if(n <= 0)
        {
            return resultList;
        }

        List<Integer> combineSum = new ArrayList<Integer>();
        combinationSum3Dfs(k, n, 1, combineSum, resultList);
        
        return resultList;
    }

    public void combinationSum3Dfs(int k, int sum, int start, List<Integer> combineSum, List<List<Integer>> resultList)
    {
        if(combineSum.size() == k && sum == 0)
        {
            resultList.add(new ArrayList<Integer>(combineSum));   
        }

        if(sum < 0)
        {
            return;
        }

        for(int i = start;i <= 9;i++)
        {   
            if(sum - i < 0 || combineSum.size() > k)
            {
                break;
            }


            combineSum.add(i);
            combinationSum3Dfs(k, sum - i, i + 1, combineSum, resultList);
            combineSum.remove(combineSum.size() - 1);
        }
    }

    // Given a digit string, return all possible letter combinations that the number could represent.
    // A mapping of digit to letters (just like on the telephone buttons) is given below.
    public List<String> letterCombinations(String digits) 
    {
        List<String> resultList = new ArrayList<String>();
        if(digits == null || digits.length() == 0)
        {
            return resultList;
        }

        int length = digits.length();

        for(int i = 0;i < length;i++)
        {
            if(digits.charAt(i) == '1' || digits.charAt(i) == '*' || digits.charAt(i) == '0' || digits.charAt(i) == '#')
            {
                return resultList;
            }
        }

        HashMap<Integer, String> digitsToLetters = new HashMap<Integer, String>();
        digitsToLetters.put(2, "abc");
        digitsToLetters.put(3, "def");
        digitsToLetters.put(4, "ghi");
        digitsToLetters.put(5, "jkl");
        digitsToLetters.put(6, "mno");
        digitsToLetters.put(7, "pqrs");
        digitsToLetters.put(8, "tuv");
        digitsToLetters.put(9, "wxyz");

        StringBuilder tempString = new StringBuilder();
        letterCombinationsHelper(resultList, digitsToLetters, tempString, digits, length);

        return resultList;   
    }

    public void letterCombinationsHelper(List<String> resultList, HashMap<Integer, String> digitsToLetters, StringBuilder tempString, String digits, int size)
    {   
        //System.out.println("digits.length: " + digits.length());
        if(digits.length() == 0 && tempString.length() == size)
        {   
            resultList.add(tempString.toString());
            return;
        }

        for(int i = 0;i < digits.length();i++)
        {   
            int digit = Character.getNumericValue(digits.charAt(i));
            String letters = digitsToLetters.get(digit);
            {
                for(int j = 0;j < letters.length();j++)
                {
                    tempString.append(letters.charAt(j));
                    letterCombinationsHelper(resultList, digitsToLetters, tempString, digits.substring(i + 1), size);
                    tempString.deleteCharAt(tempString.length() - 1);
                }
            }
        }
    }

    // Write a function that takes an integer n and return all possible combinations of its factors.
    public List<List<Integer>> getFactors(int n) 
    {
        List<List<Integer>> resultList = new ArrayList<List<Integer>>(); 
        List<Integer> tempList = new ArrayList<Integer>();

        if(n <= 3)
        {
            return resultList;
        }

        getFactorsHelper(resultList, tempList, 2, n);

        return resultList;
    }

    public void getFactorsHelper(List<List<Integer>> resultList, List<Integer> tempList, int start, int n)
    {
        if(n == 1)
        {   
            //Don't include n itself as the factor
            if(tempList.size() > 1)
            {
                resultList.add(new ArrayList<Integer>(tempList));
            }
            return;
        }   

        for(int i = start;i <= n;i++)
        {
            if(n % i != 0)
            {
                continue;
            }

            tempList.add(i);
            getFactorsHelper(resultList, tempList, i, n / i);
            tempList.remove(tempList.size() - 1);
        }
    }

    // Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
    // For example, given n = 3, a solution set is:
    // "((()))", "(()())", "(())()", "()(())", "()()()"
    public List<String> generateParenthesis(int n) 
    {
        List<String> resultList = new ArrayList<String>();
        if(n < 1)
        {
            return resultList;
        }

        StringBuilder tempString = new StringBuilder();
        generateParenthesisDFS(resultList, tempString, 0, 0, n);
        return resultList;
    }

    public void generateParenthesisDFS(List<String> resultList, StringBuilder tempString, int numOfLeftP, int numOfRightP, int n) 
    {
        if(numOfLeftP == n && numOfRightP == n)
        {
            resultList.add(tempString.toString());
            return;
        }

        if(numOfLeftP < n)
        {   
            tempString.append('(');
            generateParenthesisDFS(resultList, tempString, numOfLeftP + 1, numOfRightP, n);
            tempString.deleteCharAt(tempString.length() - 1);
        }

        if(numOfRightP < numOfLeftP)
        {   
            tempString.append(')');
            generateParenthesisDFS(resultList, tempString, numOfLeftP, numOfRightP + 1, n);
            tempString.deleteCharAt(tempString.length() - 1);
        }
    }

}