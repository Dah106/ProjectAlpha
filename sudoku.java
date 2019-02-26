public class sudoku
{
	public boolean isValidSudoku(char[][] board) 
	{
    	if(board==null || board.length!=9 || board[0].length!=9)
    	{
    		return false; 
    	} 
        
        //each row
    	for(int i = 0;i < 9;i++)  
	    {   
	        boolean[] row = new boolean[9];  
	        for(int j=0;j<9;j++)  
	        {  
	            if(board[i][j] != '.')  
	            {  
	                if(row[(int)(board[i][j]-'1')])  
	                {  
	                    return false;  
	                }  
	                row[(int)(board[i][j]-'1')] = true;  
	            }  
	        }  
	    }

	    //each column
	    for(int j=0;j<9;j++)  
	    {  
	        boolean[] map = new boolean[9];  
	        for(int i=0;i<9;i++)  
	        {  
	            if(board[i][j] != '.')  
	            {  
	                if(map[(int)(board[i][j]-'1')])  
	                {  
	                    return false;  
	                }  
	                map[(int)(board[i][j]-'1')] = true;  
	            }  
	        }  
	    } 


	    for(int block=0;block<9;block++)  
	    {  	
	        boolean[] map = new boolean[9];  
	        for(int i=block/3 * 3;i<block/3*3+3;i++)  
	        {  
	            for(int j=block%3*3;j<block%3*3+3;j++)  
	            {  
	            	System.out.println("block: " + block + " i: " + i + " j:" + j);
	                if(board[i][j] != '.')  
	                {  
	                   if(map[(int)(board[i][j]-'1')])  
	                   {  
	                      return false;  
	                   }  
	                   map[(int)(board[i][j]-'1')] = true;      
	                }  
	            }  
	        }  
	    }

		return true;
    }

    public void solveSudoku(char[][] board) 
    {  
	    if(board == null || board.length != 9 || board[0].length !=9)
		{
	        return;  
	    }
	    helper(board,0,0);  
	}

    private boolean helper(char[][] board, int i, int j)  
	{  
	    if(j>=9)  
	        return helper(board,i+1,0);  
	    if(i==9)  
	    {  
	        return true;  
	    }  
	    if(board[i][j]=='.')  
	    {  
	        for(int k=1;k<=9;k++)  
	        {  
	            board[i][j] = (char)(k+'0');  
	            if(isValid(board,i,j))  
	            {  
	                if(helper(board,i,j+1))  
	                    return true;  
	            }  
	            board[i][j] = '.';  
	        }  
	    }  
	    else  
	    {  
	        return helper(board,i,j+1);  
	    }  
	    return false;  
	}  

	private boolean isValid(char[][] board, int i, int j)  
	{  
	    for(int k=0;k<9;k++)  
	    {  	
	    	System.out.println("i: " + i + " j: " + j);
	        if(k!=j && board[i][k]==board[i][j])  
	            return false;  
	    }  
	    for(int k=0;k<9;k++)  
	    {  
	        if(k!=i && board[k][j]==board[i][j])  
	            return false;  
	    }          
	    for(int row = i/3*3; row<i/3*3+3; row++)  
	    {  
	        for(int col = j/3*3; col<j/3*3+3; col++)  
	        {  	
	            if((row!=i || col!=j) && board[row][col]==board[i][j])  
	            {	
	                return false;  
	            }
	        }  
	    }  
	    return true;  
	}

    public static void main(String[] args) 
    {
    	
    	sudoku test = new sudoku();
    	char[][] board = new char[9][9];
    	for(int i = 0;i < board.length;i++)
    	{
    		for(int j = 0;j < board[0].length;j++)
    		{
    			board[i][j] = '.';
    		}
    	}

    	//test.isValidSudoku(board);
    	test.solveSudoku(board);
    }	
}