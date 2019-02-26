  import java.util.*;

  public class dynamicProgramming
  { 
    public int climbStairs(int n) 
    {
          if(n <= 0) { return 0; }
          if(n == 1) { return 1; }
          if(n == 2) { return 2; }
          
          int twoStepBefore = 1;
          int oneStepBefore = 2;
          int stepToN = 0;
          
          for(int i = 2;i < n;i++)
          {   
              stepToN = twoStepBefore + oneStepBefore;
              twoStepBefore = oneStepBefore;
              oneStepBefore = stepToN;
          }
          
          return stepToN;
    }  
    // Given a triangle, find the minimum path sum from top to bottom. 
    // Each step you may move to adjacent numbers on the row below.

    // For example, given the following triangle
    // [
    //      [2],
    //     [3,4],
    //    [6,5,7],
    //   [4,1,8,3]
    // ]
    // The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
    // The dp equation is: dp[i + 1][j] = d[i + 1][j + 1] + triangle[i][j]
    // Use bottom-up solution is cleaner
    public int minimumTotal(List<List<Integer>> triangle) 
    { 
      if(triangle.size() == 0)
      {
              return 0;
      }
              
          int[] dp = new int[triangle.size()];

          //the dp length is the size of the bottom row
          //initializng dp by assigning bottom grid value to each slit
          for(int i = 0;i < triangle.size();i++)
          {
              dp[i] = triangle.get(triangle.size() - 1).get(i);
          }

          //starting from the second to last row
          for(int i = triangle.size() - 2;i >= 0;i--)
          {
              for(int j = 0;j <= i;j++)
              {
                  dp[j] = Math.min(dp[j], dp[j + 1]) + triangle.get(i).get(j); 
              }
          }
          return dp[0];
     
      }

      //if the input is a 2d Array
      //2
      //3 4
      //6 5 7
      //4 1 8 3
      //dp equation is the same
      //dp[i][j] = min(dp[i+1][j],dp[i+1][j+1]) + triangle[i][j]
      public int minimumTotalWith2DInput(int[][] triangle) 
      {
          if (triangle == null || triangle.length == 0) {
              return -1;
          }
          if (triangle[0] == null || triangle[0].length == 0) {
              return -1;
          }
          
          // state: dp[x][y] = minimum path value from 0,0 to x,y
          int n = triangle.length;
          int[] dp = new int[n];
          
          //the dp length is the size of the bottom row
          //initializng dp by assigning bottom grid value to each slit
          for(int i = 0;i < n;i++)
          {
              dp[i] = triangle[n - 1][i];
          }

          //starting from the second to last row
          for(int i = n - 2;i >= 0;i--)
          {
              for(int j = 0;j <= i;j++)
              {
                  dp[j] = Math.min(dp[j], dp[j + 1]) + triangle[i][j]; 
              }
          }
          return dp[0];
      }
      

      // Find the contiguous subarray within an array (containing at least one number) which has the largest product.
      // For example, given the array [2,3,-2,4],
      // the contiguous subarray [2,3] has the largest product = 6.
      // need to keep a min local to consider the product of two negative numbers
      // which results in a big number
      public int maxProduct(int[] A) 
      {
          if(A == null || A.length == 0)
          {
              return 0;
          }

          int lastLocalMax = A[0];
          int lastLocalMin = A[0];
          int currentLocalMax = A[0];
          int currentLocalMin = A[0];
          int globalMax = A[0];
          

          for(int i = 1;i < A.length;i++)
          {   
              currentLocalMax = Math.max(Math.max(lastLocalMax * A[i], lastLocalMin * A[i]), A[i]);
              currentLocalMin = Math.min(Math.min(lastLocalMax * A[i], lastLocalMin * A[i]), A[i]);
              globalMax = Math.max(currentLocalMax, globalMax);
              lastLocalMax = currentLocalMax;
              lastLocalMin = currentLocalMin;
          }

          return globalMax;
      }

      // Given an array of n integers where n > 1, nums, 
      // return an array output such that output[i] is equal to the product of all the elements of nums except nums[i].
      // Solve it without division and in O(n).
      // For example, given [1,2,3,4], return [24,12,8,6].
      public int[] productExceptSelf(int[] nums) 
      {
          int[] result = new int[nums.length];

          result[0] = 1;
          //the last result in the array is a1*a2...an-1
          for(int i = 1;i < nums.length;i++)
          {
             result[i] = result[i - 1] * nums[i - 1];
          }

          int right = 1;
          for(int i = nums.length - 1;i >= 0;i--)
          {
             result[i] *= right;
             right *= nums[i];
          }

          return result;
      }

      // You are a professional robber planning to rob houses along a street. 
      // Each house has a certain amount of money stashed, 
      // the only constraint stopping you from robbing each of them 
      // is that adjacent houses have security system connected 
      // and it will automatically contact the police if two adjacent houses were broken into on the same night.
      // Given a list of non-negative integers representing the amount of money of each house, 
      // determine the maximum amount of money you can rob tonight without alerting the police.
      //Time: O(N)
      //Space: O(N)
      public int rob(int[] nums) 
      {
         if(nums == null || nums.length == 0)
         {
              return 0;
         }

         if(nums.length == 1)
         {
              return nums[0];
         }

         if(nums.length == 2)
         {
              return Math.max(nums[0], nums[1]);
         }
         int[] dp = new int[nums.length];//record max profit when robbing non-adjacent houses
         dp[0] = nums[0];
         dp[1] = Math.max(nums[0], nums[1]);
         for(int i = 2;i < nums.length;i++)
         {
              dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
         }

         return dp[dp.length - 1];
      }

      //optimized space version
      //Time: O(N)
      //Space: O(1)
      public int robOptimized(int[] nums) 
      {
         if(nums == null || nums.length == 0)
         {
              return 0;
         }

         if(nums.length == 1)
         {
              return nums[0];
         }

         if(nums.length == 2)
         {
              return Math.max(nums[0], nums[1]);
         }

         int bestTwoHouseAway = nums[0];
         int bestNow = Math.max(nums[0],nums[1]);
         for(int i = 2;i < nums.length;i++)
         {    
              int tempBestNow = bestNow;
              bestNow = Math.max(bestTwoHouseAway + nums[i], bestNow);
              bestTwoHouseAway = tempBestNow;
         }

         return bestNow;
      }

      // After robbing those houses on that street, the thief has found himself a new place for his thievery so that he will not get too much attention. 
      // This time, all houses at this place are arranged in a circle. 
      // That means the first house is the neighbor of the last one. 
      // Meanwhile, the security system for these houses remain the same as for those in the previous street.
      // Given a list of non-negative integers representing the amount of money of each house, 
      // determine the maximum amount of money you can rob tonight without alerting the police.
      //Time: O(N)
      //Space: O(N)
      public int robII(int[] nums) 
      {
          if(nums == null || nums.length == 0)
          {
              return 0;
          }

          if(nums.length == 1)
          {
              return nums[0];
          }

          if(nums.length == 2)
          {
              return Math.max(nums[0], nums[1]);
          }

          int n = nums.length;
          //include 1st element, and not last element
          int[] dp = new int[n - 1];
          dp[0] = nums[0];
          dp[1] = Math.max(nums[0], nums[1]);
       
          for(int i = 2; i < n - 1; i++)
          {
              dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
          }
       
          //not include frist element, and include last element
          int[] dr = new int[n - 1];
          dr[0] = nums[1];
          dr[1] = Math.max(nums[1], nums[2]);
       
          for(int i = 2; i < n - 1; i++)
          {
              dr[i] = Math.max(dr[i - 1], dr[i - 2] + nums[i + 1]);
          }
       
          return Math.max(dp[dp.length - 1], dr[dr.length - 1]);
      }

      public int robIIOptimized(int[] nums) 
      {
         if(nums == null || nums.length == 0)
         {
              return 0;
         }

         if(nums.length == 1)
         {
              return nums[0];
         }

         if(nums.length == 2)
         {
              return Math.max(nums[0], nums[1]);
         }

         int n = nums.length;

         int b1 = nums[0];//if selecting first house, max two houses away
         int b2 = Math.max(nums[0],nums[1]);//if selecting first house, current max
         for(int i = 2;i < n - 1;i++)
         {    
              int tempBestNow = b2;
              b2 = Math.max(b1 + nums[i], b2);
              b1 = tempBestNow;
         }


         int b3 = nums[1];//if not selecting first house, max two houses away
         int b4 = Math.max(nums[1],nums[2]);//if not selecting first house, current max
         for(int i = 2; i < n - 1; i++)
         {
              int tempBestNow = b4;
              b4 = Math.max(b3 + nums[i + 1], b4);
              b3 = tempBestNow;
         }

         return Math.max(b2, b4);    
      }
      // The thief has found himself a new place for his thievery again. 
      // There is only one entrance to this area, called the "root." 
      // Besides the root, each house has one and only one parent house. 
      // After a tour, the smart thief realized that "all houses in this place forms a binary tree". 
      // It will automatically contact the police if two directly-linked houses were broken into on the same night.

      // Determine the maximum amount of money the thief can rob tonight without alerting the police.

      // Example 1:
      //      3
      //     / \
      //    2   3
      //     \   \ 
      //      3   1
      // Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
      // Example 2:
      //      3
      //     / \
      //    4   5
      //   / \   \ 
      //  1   3   1
      // Maximum amount of money the thief can rob = 4 + 5 = 9.
      public int robIII(TreeNode root) 
      {
          if (root == null) 
          {
            return 0;
          }

          int val = 0;

          if (root.left != null) {
              val += robIII(root.left.left) + robIII(root.left.right);
          }

          if (root.right != null) {
              val += robIII(root.right.left) + robIII(root.right.right);
          }

          return Math.max(val + root.val, robIII(root.left) + robIII(root.right));
      }

      public int robIIIOptimized(TreeNode root) 
      {
          int[] res = robIIIOptimizedHelper(root);
          return Math.max(res[0], res[1]);
      }

      public int[] robIIIOptimizedHelper(TreeNode root) 
      {
          if (root == null) 
          {
              return new int[2];
          }

          int[] left = robIIIOptimizedHelper(root.left);
          int[] right = robIIIOptimizedHelper(root.right);

          //res[0] -> max profit that root is not robbed, 
          //res[1] -> max profit that root is robbed 
          int[] res = new int[2];
          res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
          res[1] = root.val + left[0] + right[0];

          return res;
      }
      // There is a fence with n posts, each post can be painted with one of the k colors.
      // You have to paint all the posts such that no more than two adjacent fence posts have the same color.
      // Return the total number of ways you can paint the fence.
      // Note:
      // n and k are non-negative integers.
      public int numWays(int n, int k) 
      {
          if (n == 0) 
          {
            return 0;
          }
          if (n == 1) 
          {
            return k;
          }
          if (n == 2) 
          {
            return k * k;
          }

          int twoPostBefore = k;//n = 1, has k way of painting
          int onePostBefore = k * k;//n = 2, has k*k way of painting
          int result = 0;
          for(int i = 2;i < n;i++)
          { 
              result = (k - 1)*twoPostBefore + (k - 1)*onePostBefore;
              twoPostBefore = onePostBefore;
              onePostBefore = result;
          }

          return result;
      }

      // There are a row of n houses, each house can be painted with one of the three colors: red, blue or green. 
      // The cost of painting each house with a certain color is different. 
      // You have to paint all the houses such that no two adjacent houses have the same color.
      // The cost of painting each house with a certain color is represented by a n x 3 cost matrix. 
      // For example, costs[0][0] is the cost of painting house 0 with color red; costs[1][2] is the cost of painting house 1 with color green, and so on... Find the minimum cost to paint all houses.
      // All costs are positive integers.
      public int minCost(int[][] costs) 
      { 
          if(costs == null || costs.length==0) 
          {
            return 0;
          }

          int lastR = costs[0][0];
          int lastB = costs[0][1];
          int lastG = costs[0][2];
          if(costs.length <= 1)
          {
            return Math.min(Math.min(lastR, lastB), lastG);
          }
          int currentR = 0;
          int currentB = 0;
          int currentG = 0;
          for(int i = 1;i < costs.length;i++)
          {
            currentR = Math.min(lastB, lastG) + costs[i][0];
            currentB = Math.min(lastR, lastG) + costs[i][1];
            currentG = Math.min(lastR, lastB) + costs[i][2];
            lastR = currentR;
            lastB = currentB;
            lastG = currentG;
          }

          return Math.min(Math.min(currentR, currentB), currentG);
      }

      // The cost of painting each house with a certain color is represented by a n x k cost matrix. For example, costs[0][0] is the cost of painting house 0 with color 0; 
      // costs[1][2] is the cost of painting house 1 with color 2, and so on... Find the minimum cost to paint all houses.
      // All costs are positive integers.
      // Follow up:
      // Could you solve it in O(nk) runtime?
      public int minCostII(int[][] costs) 
      { 
          if(costs == null || costs.length==0) 
          {
            return 0;
          }

          int numOfHouse = costs.length;
          int numOfColor = costs[0].length;

          int prevMin = 0;
          int prevSecMin = 0;
          int prevColorIndex = -1;

          for(int i = 0;i < numOfHouse;i++)
          {
             int curMin = Integer.MAX_VALUE;
             int curSecMin = Integer.MAX_VALUE;
             int curIndex = -1;
             for(int j = 0; j < numOfColor; j++)
             {   
                //if the prevMin obtains color that has been used for the previous house
                //use the prevsecondMin
                if(prevColorIndex == j)
                {
                  costs[i][j] = costs[i][j] + prevSecMin;
                }
                else
                {
                  costs[i][j] = costs[i][j] + prevMin;
                }

                //if the costs are less than current min
                //update the current min
                if(costs[i][j] < curMin)
                {
                  curSecMin = curMin;
                  curMin = costs[i][j];
                  curIndex = j;
                }
                else if(costs[i][j] < curSecMin)
                {
                  curSecMin = costs[i][j];
                }
                
             }

             prevMin = curMin;
             prevSecMin = curSecMin;
             prevColorIndex = curIndex;
          }

          return prevMin;
      }

      // You are given coins of different denominations and a total amount of money amount. 
      // Write a function to compute the fewest number of coins that you need to make up that amount. 
      // If that amount of money cannot be made up by any combination of the coins, return -1.
      // coins = [1, 2, 5], amount = 11
      // return 3 (11 = 5 + 5 + 1)
      public int coinChange(int[] coins, int amount) 
      {
          if(amount == 0)
          {
            return 0;
          }

          if(coins == null || coins.length == 0)
          {
            return -1;
          }

          int dp[] = new int[amount + 1];//dp[i] stores min number of coins need to get amount i
          for(int i = 1;i <= amount;i++)
          {
            dp[i] = Integer.MAX_VALUE;//initially set it to infinite
            for(int j = 0;j < coins.length;j++)
            { 
              //if the coin is less than current amount
              //and last amount i - coins[j] has a valid number of coins
              if(coins[j] <= i && dp[i - coins[j]] != Integer.MAX_VALUE)
              {
                dp[i] = Integer.min(dp[i], 1 + dp[i - coins[j]]);
              }
            }
          }

          //if the final amount remains invalid
          //return -1
          if(dp[amount] == Integer.MAX_VALUE)
          {
            return -1;
          }

          return dp[amount];
      }


      // Given an unsorted array of integers, find the length of longest increasing subsequence.
      // For example,
      // Given [10, 9, 2, 5, 3, 7, 101, 18],
      // The longest increasing subsequence is [2, 3, 7, 101], 
      // therefore the length is 4.
      //  Note that there may be more than one LIS combination, 
      //  it is only necessary for you to return the length.
      public int longestIncreasingSubsequence(int[] nums) 
      {
            if(nums == null || nums.length == 0)
            {
              return 0;
            }

            //dp[i] stores the LCS till position i
            int [] dp = new int[nums.length];

            int maxLength = 0;
            for(int i = 0;i < nums.length;i++)
            {
              dp[i] = 1;
            }

            for(int i = 0;i < nums.length;i++)
            {
              for(int j = 0;j < i;j++)
              { 
                //if we found a smaller value
                //get the max val
                if(nums[j] < nums[i])
                {
                  dp[i] = Math.max(dp[i], dp[j] + 1);
                }
              }

              maxLength = Math.max(maxLength, dp[i]);
            }

            return maxLength;
      }

      //Given A = "ABCD", B = "CBCE", return 2.
      public int longestCommonSubstring(String A, String B) 
      {
          int l1 = A.length();
          int l2 = B.length();

          //dp[i][j] represents the longest common substring of first i and j chars
          //of string A and B
          int [][] dp = new int[l1 + 1][l2 + 1];

          int maxLength = 0;
          for(int i = 0;i <= l1;i++)
          {
            for(int j = 0;j <= l2;j++)
            { 
              //if either first row or first col
              //no substring since one of the strings have no chars
              if(i == 0 || j == 0)
              {
                dp[i][j] = 0;
              }
              else
              {
                if(A.charAt(i - 1) == B.charAt(j - 1))
                {
                  dp[i][j] = dp[i - 1][j - 1] + 1;
                }
                else
                {
                  dp[i][j] = 0;
                }
              }

              //update max length if necessary
              maxLength = Math.max(maxLength, dp[i][j]);
            }
          }

          return maxLength;
      }

      // For "ABCD" and "EDCA", the LCS is "A" (or "D", "C"), return 1.
      // For "ABCD" and "EACB", the LCS is "AC", return 2.
      public int longestCommonSubsequence(String A, String B) 
      {
        if (A == null || B == null) 
        {
          return 0;
        }
          
        int l1 = A.length();
        int l2 = B.length();
        int [][] dp = new int[l1 + 1][l2 + 1];
        
        int maxLength = 0;
        for (int i = 0; i <= l1; i++) 
        {
          for (int j = 0; j <= l2; j++) 
          {  
            if (i == 0 || j == 0) 
            {
              dp[i][j] = 0;
            } 
            else 
            {
              if(A.charAt(i - 1) == B.charAt(j - 1)) 
              {
                dp[i][j] = dp[i - 1][j - 1] + 1;
              } 
              //check the last bucket to see if the last char of s1 or s2
              //has appeared in the first half of s2 or s1
              //if yes, get the maximum 
              else 
              {
                dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
              }

              //update max length if necessary
              maxLength = Math.max(maxLength, dp[i][j]);
            }
          }
        }
          
        return maxLength;
      }

      //Given [5,1,5,5,2,5,4], return true
      //Given [1, 2, 3, 4, 5], return true
      //Given [5, 4, 3, 2, 1], return false
      //Time: O(N^2)
      //Space: O(N)
      public boolean increasingTripletUsingDP(int[] nums) 
      {
          if(nums == null || nums.length == 0)
          {
            return false;
          }

          if(nums.length < 3)
          {
            return false;
          }

          int[] dp = new int[nums.length];
          for (int i = 0; i < nums.length;i++) 
          {
              for (int j = 0; j < i; ++j) 
              {
                  if (nums[j] < nums[i]) 
                  {
                      dp[i] = Math.max(dp[i], dp[j] + 1);
                      if (dp[i] >= 3) return true;
                  }
              }
          }

          return false;
      }

      //keep two min value in the array
      //if there is a third min value
      //return true
      public boolean increasingTriplet(int [] nums)
      {
        if(nums == null || nums.length == 0)
          {
            return false;
          }

          if(nums.length < 3)
          {
            return false;
          }

          int firstMin = Integer.MAX_VALUE;
          int secondMin = Integer.MAX_VALUE;

          for (int i = 0; i < nums.length;i++) 
          {
            if(nums[i] <= firstMin)
            {
              firstMin = nums[i];
            }
            else if(nums[i] <= secondMin)
            {
              secondMin = nums[i];
            }
            else
            {
              return true;
            }
          }

        return false;
      }

      // Given two words word1 and word2, 
      // find the minimum number of steps required to convert word1 to word2. 
      // (each operation is counted as 1 step.)
      // You have the following 3 operations permitted on a word:
      // a) Insert a character
      // b) Delete a character
      // c) Replace a character
      // Time: O(m * n)
      // Space: O(m * n)
      public int minDistance(String word1, String word2) 
      {
          if(word1.length() == 0)
          {
            return word2.length();
          }

          if(word2.length() == 0)
          {
            return word1.length();
          }

          int len1 = word1.length();
          int len2 = word2.length();

          int [][] dp = new int[len1 + 1][len2 + 1];//dp[i][j] means min Ops from first i chars in word 1 to first j chars
          
          //Initialize case where word2 is length 0
          for(int i = 0;i <= len1;i++)
          {
            dp[i][0] = i;
          }

          //Initialize case where word1 is length 0
          for(int j = 0;j <= len2;j++)
          {
            dp[0][j] = j;
          }


          for(int i = 1;i <= len1;i++)
          {
            for(int j = 1;j <= len2;j++)
            {
              if(word1.charAt(i - 1) == word2.charAt(j - 1))
              {
                dp[i][j] = dp[i - 1][j - 1];
              }
              else
              {
                int insertInWord1 = dp[i][j - 1] + 1;
                int deleteInWord1 = dp[i - 1][j] + 1;
                int replaceInWord1 = dp[i - 1][j - 1] + 1;

                dp[i][j] = Math.min(Math.min(insertInWord1, deleteInWord1), replaceInWord1);
              }
            }
          }
          return dp[len1][len2];
      }

      //rolling array
      // Time: O(m * n)
      // Space: O(min(m, n))
      public int minDistanceRollingArray(String word1, String word2)
      {
          if(word1.length() == 0)
          {
            return word2.length();
          }

          if(word2.length() == 0)
          {
            return word1.length();
          }

          String minWord = word1.length() > word2.length()?word2:word1;  
          String maxWord = word1.length() > word2.length()?word1:word2;  
          int[] resulut = new int[minWord.length()+1];  
          
          for(int i = 0;i <= minWord.length();i++)  
          {  
              resulut[i] = i;  
          }  
          
          for(int i = 0;i < maxWord.length();i++)  
          {  
              int[] newResult = new int[minWord.length() + 1];  
              newResult[0] = i + 1;  
              for(int j = 0;j < minWord.length();j++)  
              {  
                  if(minWord.charAt(j) == maxWord.charAt(i))  
                  {  
                      newResult[j + 1] = resulut[j];  
                  }  
                  else  
                  {  
                      newResult[j + 1] = Math.min(resulut[j],Math.min(resulut[j + 1],newResult[j])) + 1;  
                  }  
              }  
              resulut = newResult;  
          }  
          return resulut[minWord.length()];
      }

      // Given a string S and a string T, count the number of distinct subsequences of T in S.
      // A subsequence of a string is a new string which is formed from the original string by deleting some (can be none) of the characters without disturbing the relative positions of the remaining characters. 
      // (ie, "ACE" is a subsequence of "ABCDE" while "AEC" is not).
      // Here is an example:
      // S = "rabbbit", T = "rabbit"
      // Return 3.
      // Time: O(m * n)
      // Space: O(min(m, n))
      public int numDistinct(String s, String t) 
      {   
          if(s.equals(t))
          {
            return 1;
          }

          if(s.length() <= t.length())
          {
            return 0;
          }

          //dp[i][j] represents numDistinct for [0...i] in s and [0...j] in t
          int[][] dp = new int[s.length() + 1][t.length() + 1];
          
          dp[0][0] = 1;
          for(int i = 1;i <= s.length();i++)
          {
            dp[i][0] = 1;
          }

          for(int j = 1;j <= t.length();j++)
          {
            dp[0][j] = 0;
          }

          for(int i = 1;i <= s.length();i++)
          {
            for(int j = 1;j <= t.length();j++)
            {
              dp[i][j] = dp[i - 1][j];// stays the same if there is no match

              if(s.charAt(i - 1) == t.charAt(j - 1))
              {
                dp[i][j] = dp[i - 1][j] + dp[i - 1][j - 1];
              }
    
            }
          }

          return dp[s.length()][t.length()];
      }

      // Time: O(m * n)
      // Space: O(min(t))
      public int numDistinctRollingArray(String s, String t) 
      {
          if(s.equals(t))
          {
            return 1;
          }

          if(s.length() <= t.length())
          {
            return 0;
          }

          int[] result = new int[t.length() + 1]; 
          result[0] = 1; 

          for(int i = 0;i < s.length();i++)  
          {   
              for(int j = t.length() - 1;j >= 0;j--)  
              {   
                  result[j + 1] = result[j + 1];
                  if(s.charAt(i) ==t.charAt(j))
                  {
                    result[j + 1] = result[j] + result[j + 1];
                  }  
              }  
          }  
          return result[t.length()];  
      }

      // Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.
      // For example,
      // Given:
      // s1 = "aabcc",
      // s2 = "dbbca",
      // When s3 = "aadbbcbcac", return true.
      // When s3 = "aadbbbaccc", return false.
      public boolean isInterleave(String s1, String s2, String s3) 
      {
        if(s1.length() + s2.length()!=s3.length())
        {
          return false;
        }
        
        //interleaved[i][j] means if s3 can be formed by the interleaving of [0...i] in s1 and [0...j] in s1
        boolean [][] interleaved = new boolean[s1.length() + 1][s2.length() + 1];
        interleaved[0][0] = true;
          
          for (int i = 1; i <= s1.length(); i++) 
          {
              if(s3.charAt(i - 1) == s1.charAt(i - 1) && interleaved[i - 1][0])
                  interleaved[i][0] = true;
          }
          
          for (int j = 1; j <= s2.length(); j++) 
          {
              if(s3.charAt(j - 1) == s2.charAt(j - 1) && interleaved[0][j - 1])
                  interleaved[0][j] = true;
          }
          
          for (int i = 1; i <= s1.length(); i++) 
          {
              for (int j = 1; j <= s2.length(); j++) 
              {
                  if(((s3.charAt(i + j - 1) == s1.charAt(i - 1) && interleaved[i - 1][j]))
                      || ((s3.charAt(i + j - 1)) == s2.charAt(j - 1) && interleaved[i][j - 1]))
                  {
                    interleaved[i][j] = true;
                  }
              }
          }
          
          return interleaved[s1.length()][s2.length()];
      }

      public boolean isInterleaveRollingArray(String s1, String s2, String s3)
      {
        if(s1.length() + s2.length()!=s3.length())
        {
          return false;
        }

        String minWord = s1.length()>s2.length()?s2:s1;
        String maxWord = s1.length()>s2.length()?s1:s2;

        boolean[] res = new boolean[minWord.length() + 1];
        res[0] = true;

        for(int i = 0;i < minWord.length();i++)
        {
            res[i + 1] = res[i] && minWord.charAt(i) == s3.charAt(i);
        }

        for(int i = 0;i < maxWord.length();i++)
        {
            res[0] = res[0] && maxWord.charAt(i) == s3.charAt(i);
            for(int j = 0;j < minWord.length();j++)
            {
                res[j + 1] = res[j + 1] && maxWord.charAt(i) == s3.charAt(i + j + 1) || res[j] && minWord.charAt(j) == s3.charAt(i + j + 1);
            } 
        }
        return res[minWord.length()];
      }

      public int guessNumber(int n) {
        int start = 1;
        int end = n;
        int middle = -1;

        while(start <= end) {
          middle = (end - start) /2 + start;
            if(guess(middle) == 1) {
              start = middle + 1;
            } else if(guess(middle) == -1) {
              end = middle;
            }
            else {
              break;
            }
        }
          
        return middle;
      }


    public int guess(int n) {
      if(n > 6) {
        return 1;
      } else if(n == 6) {
        return 0;
      } else {
        return -1;
      }
    }

    // We are playing the Guess Game. The game is as follows:

    // I pick a number from 1 to n. You have to guess which number I picked.

    // Every time you guess wrong, I'll tell you whether the number I picked is higher or lower.

    // However, when you guess a particular number x, and you guess wrong, you pay $x. You win the game when you guess the number I picked.

    // Example:

    // n = 10, I pick 8.

    // First round:  You guess 5, I tell you that it's higher. You pay $5.
    // Second round: You guess 7, I tell you that it's higher. You pay $7.
    // Third round:  You guess 9, I tell you that it's lower. You pay $9.

    // Game over. 8 is the number I picked.

    // You end up paying $5 + $7 + $9 = $21.
    // Given a particular n ≥ 1, find out how much money you need to have to guarantee a win.
    public int getMoneyAmount(int n) {
      return getMoneyAmountNaiveRecursive(1, n);
       //Call it dp instead of memo
    }

      public int getMoneyAmountNaiveRecursive(int start, int end) {

        if(start >= end) {
          // get the correct number, cost is 0
          return 0;
        }

        if(end - start == 1) {
          // for [2, 3], picking 2 only costs 2 even if it is wrong
          return start;
        }

        if(end - start == 2) {
          //for [1, 3], picking 1 costs 2 if it is wrong
          return start + 1;
        }


        // get min lost at worst case scenario 
        int min = Integer.MAX_VALUE;

        for(int i = start;i <= end;i++) {
          min = Math.min(i + Math.max(getMoneyAmountNaiveRecursive(start, i - 1), 
            getMoneyAmountNaiveRecursive(i + 1, end)), min);
        }

        return min;
      }

    public int getMoneyAmountDP(int n) {
              int [][] dp = new int[n][n];
        //Goes from n to 0 (from lower to upper)
        for(int i=n; i>=0; i--) {
            //Set values for base conditions
            if(i+1<n) {
                dp[i][i+1] = i+1; // add 1 to the values since i start from 0 not from 1
            }
            if(i+2<n) {
                dp[i][i+2] = i+2; 
            }
            //Goes from left to right
            for(int j=i+3; j<n ; j++) {
                //This part is similar to the for  loop in basic recursive function
                int min = Integer.MAX_VALUE;
                for(int t=i+1 ; t<j ; t++) {
                    min = Math.min(t + 1 + Math.max(dp[i][t-1], dp[t+1][j]), min);
                }
                dp[i][j] = min;
            }
        }
        return dp[0][n-1];
    }

    public static void main(String[] args) {
      dynamicProgramming app = new dynamicProgramming();
      System.out.println(app.getMoneyAmount(4));
    }
  }

  class TreeNode
  {
      
    public TreeNode left;
    public TreeNode right;
    public int val;

    public TreeNode(int x){ val = x; }
      
  }