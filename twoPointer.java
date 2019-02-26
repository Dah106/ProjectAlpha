public class twoPointer
{
	
	public void merge(int A[], int m, int B[], int n) 
	{  
	    if(A==null || B==null) 
	    { 
	        return;  
	    }

	    int a = m-1;  
	    int b = n-1;  
	    int len = m+n-1;  
	    while(a >= 0 && b >= 0)  
	    {  
	        if(A[a]>B[b])  
	        {  
	            A[len] = A[a];  
	            len--;
	            a--;
	        }  
	        else  
	        {  
	            A[len] = B[b];
	            len--;
	            b--;  
	        }  
	    }  
	    while(b >= 0)  
	    {  
	        A[len] = B[b];
	        len--;
	        b--;  
	    }          
	}
}