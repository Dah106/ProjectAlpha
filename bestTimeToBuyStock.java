public class bestTimeToBuyStock
{
	// Say you have an array for which the ith element is the price of a given stock on day i.
	// If you were only permitted to complete at most one transaction (ie, buy one and sell one share of the stock), 
	// design an algorithm to find the maximum profit.
	public int maxProfit(int[] prices) 
	{
        if(prices == null || prices.length <= 1)
        {
        	return 0;
        }

        int localMinBuyPrice = prices[0];
        int maxProfit = 0;

        for(int i = 1;i < prices.length;i++)
        {	
        	maxProfit = Math.max(prices[i] - localMinBuyPrice, maxProfit);// get current max profit first
        	localMinBuyPrice = Math.min(localMinBuyPrice, prices[i]);//calculate min buy in price
        }

        return maxProfit;
    }

    // Design an algorithm to find the maximum profit. 
    // You may complete as many transactions as you like
    // You must sell the stock before you buy again
    public int maxProfitII(int[] prices) 
    {
        if(prices == null || prices.length <= 1)
        {
        	return 0;
        }

        int maxProfit = 0;

        for(int i = 1;i < prices.length;i++)
        {	
        	if(prices[i] > prices[i - 1])
        	{
        		maxProfit += prices[i] - prices[i - 1];
        	}
        }

        return maxProfit;
    }

    // Design an algorithm to find the maximum profit. 
    // You may complete at most two transactions.
    public int maxProfitIII(int[] prices) 
    {	
        if(prices == null || prices.length <= 1)
        {
        	return 0;
        }

        int len = prices.length;

        int[][] localMaxProfit = new int[len][3];//max profit after k transactions and must sell on ith day
        int[][] globalMaxProfit = new int[len][3];//max profit regardless if there must be a sell on ith day

        //no profit if no transactions on ith day
        for(int i = 1;i < len;i++)
        {
        	localMaxProfit[i][0] = 0;
        }

        //no profit before the first day
        for(int j = 0;j <= 2;j++)
        {
        	localMaxProfit[0][j] = 0;
        }

        for(int i = 1;i < len;i++)
        {
        	int profit = prices[i] - prices[i - 1];
        	for(int j = 1;j <= 2;j++)
        	{	
        		localMaxProfit[i][j] = Math.max(globalMaxProfit[i - 1][j - 1] + profit, localMaxProfit[i - 1][j] + profit);
        		globalMaxProfit[i][j] = Math.max(localMaxProfit[i][j], globalMaxProfit[i - 1][j]);
        	}
        }

        return globalMaxProfit[len - 1][2];
    }

    // Say you have an array for which the ith element is the price of a given stock on day i.
    // Design an algorithm to find the maximum profit.
    // You may complete at most k transactions.
    // You may not engage in multiple transactions at the same time
    // You must sell the stock before you buy again
    // Time: O(n * k)
    // Space: O(n^2)
    public int maxProfitIV(int k, int[] prices) 
    {	
        if(prices == null || prices.length <= 1 || k <= 0)
        {
        	return 0;
        }

        int len = prices.length;

        //if k >= n/2, then you can make maximum number of transactions.
	    if (k >=  len / 2) 
	    {
	        int maxProfit = 0;
	        for(int i = 1;i < len;i++)
	        {	
	        	if(prices[i] > prices[i - 1])
	        	{
	        		maxProfit += prices[i] - prices[i - 1];
	        	}
	        }

	        return maxProfit;
	    }

        int[][] localMaxProfit = new int[len][k + 1];//max profit after k transactions and must sell on ith day
        int[][] globalMaxProfit = new int[len][k + 1];//max profit regardless if there must be a sell on ith day

        //no profit if no transactions on ith day
        for(int i = 0;i < len;i++)
        {
        	localMaxProfit[i][0] = 0;
        }

        //no profit before the first day
        for(int j = 0;j <= k;j++)
        {
        	localMaxProfit[0][j] = 0;
        }

        for(int i = 1;i < len;i++)
        {
        	int profit = prices[i] - prices[i - 1];
        	for(int j = 1;j <= k;j++)
        	{	
        		localMaxProfit[i][j] = Math.max(globalMaxProfit[i - 1][j - 1] + profit, localMaxProfit[i - 1][j] + profit);
        		globalMaxProfit[i][j] = Math.max(localMaxProfit[i][j], globalMaxProfit[i - 1][j]);
        	}
        }

        return globalMaxProfit[len - 1][k];
    }

    // Time: O(n * k)
    // Space: O(n)
    public int maxProfitIVRollingArray(int k, int[] prices) 
    {	
    	if(prices == null || prices.length <= 1 || k <= 0)
        {
        	return 0;
        }

        int len = prices.length;

        //if k >= n/2, then you can make maximum number of transactions.
	    if (k >=  len / 2) 
	    {
	        int maxProfit = 0;
	        for(int i = 1;i < len;i++)
	        {	
	        	if(prices[i] > prices[i - 1])
	        	{
	        		maxProfit += prices[i] - prices[i - 1];
	        	}
	        }

	        return maxProfit;
	    }

		int[] local = new int[k + 1];
		int[] global = new int[k + 1];
 
		for (int i = 0; i < len - 1; i++) 
		{
			int diff = prices[i + 1] - prices[i];
			for (int j = k; j >= 1; j--) 
			{
				local[j] = Math.max(global[j - 1] + Math.max(diff, 0), local[j] + diff);
				global[j] = Math.max(local[j], global[j]);
			}
		}
 
		return global[k];
    }
}