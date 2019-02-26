public class sqrtLc69
{
	public static void main(String[] args) 
	{
			
	}	

	public int mySqrt(int x) 
	{
        
        // find the last number which square of it <= x
        long start = 1, end = x;
        while (start + 1 < end) {
            long mid = start + (end - start) / 2;
            if (mid * mid <= x) {
                start = mid;
            } else {
                end = mid;
            }
        }
        
        if (end * end <= x) {
            return (int) end;
        }
        return (int) start;  
    }

    public double sqrt(int x)
    {
    	while((end - start) > 1e-6)
    	{
    		double middle = start + (end - start) / 2;
    	}
    }
}