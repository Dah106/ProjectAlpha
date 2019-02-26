import java.util.*;

public class minStack
{	
	public Stack<Integer> stack = new Stack<Integer>();
    public Stack<Integer> minStack = new Stack<Integer>();

	public void push(int x) 
	{
        stack.push(x);
        //only update minStack whenever it is empty 
        //or the new value is less than the current min in minStack
        if(minStack.isEmpty() || minStack.peek() >= x)
        {
        	minStack.push(x);
        }
    }

    public void pop() 
    {
        if(stack.isEmpty())
        {
        	return;
        }

        int temp = stack.peek();
        stack.pop();
        //only pop min from minStack if the value that is popped out is the min
        if(!minStack.isEmpty() && minStack.peek() == temp)
        {
        	minStack.pop();
        }
    }

    public int top() 
    {	
    	if(!stack.isEmpty())
    	{
    		return stack.peek();
    	}
        
        return 0;
    }

    public int getMin() 
    {
        if(!minStack.isEmpty())
    	{
    		return minStack.peek();
    	}
        
        return 0;
    }
}