// Design a logger system that receive stream of messages along with its timestamps, each message should be printed if and only if it is not printed in the last 10 seconds.

// Given a message and a timestamp (in seconds granularity), return true if the message should be printed in the given timestamp, otherwise returns false.

// It is possible that several messages arrive roughly at the same time.

// Example:

// Logger logger = new Logger();

// // logging string "foo" at timestamp 1
// logger.shouldPrintMessage(1, "foo"); returns true; 

// // logging string "bar" at timestamp 2
// logger.shouldPrintMessage(2,"bar"); returns true;

// // logging string "foo" at timestamp 3
// logger.shouldPrintMessage(3,"foo"); returns false;

// // logging string "bar" at timestamp 8
// logger.shouldPrintMessage(8,"bar"); returns false;

// // logging string "foo" at timestamp 10
// logger.shouldPrintMessage(10,"foo"); returns false;

// // logging string "foo" at timestamp 11
// logger.shouldPrintMessage(11,"foo"); returns true;

import java.util.*;

public class loggerRateLimiter
{	
	public HashMap<String, Integer> log;

	public static void main(String[] args) {
		loggerRateLimiter logger = new loggerRateLimiter();

		boolean isLogged = false;

		isLogged = logger.shouldPrintMessage(0, "A");
		System.out.println("is logged: " + isLogged);

		isLogged = logger.shouldPrintMessage(0,"B");
		System.out.println("is logged: " + isLogged);

		isLogged = logger.shouldPrintMessage(0,"C");
		System.out.println("is logged: " + isLogged);

		isLogged = logger.shouldPrintMessage(0,"A");
		System.out.println("is logged: " + isLogged);

		isLogged = logger.shouldPrintMessage(0,"B");
		System.out.println("is logged: " + isLogged);

		isLogged = logger.shouldPrintMessage(0,"C");
		System.out.println("is logged: " + isLogged);

		isLogged = logger.shouldPrintMessage(11,"A");
		System.out.println("is logged: " + isLogged);

		isLogged = logger.shouldPrintMessage(11,"B");
		System.out.println("is logged: " + isLogged);

		isLogged = logger.shouldPrintMessage(11,"C");
		System.out.println("is logged: " + isLogged);

		isLogged = logger.shouldPrintMessage(11,"A");
		System.out.println("is logged: " + isLogged);
	}

	/** Initialize your data structure here. */
    public loggerRateLimiter() {
        log = new HashMap<>();
    }
    
    /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
        If this method returns false, the message will not be printed.
        The timestamp is in seconds granularity. */
    public boolean shouldPrintMessage(int timestamp, String message) {
        boolean result = true;


        if(log.containsKey(message))
        {	
        	//System.out.println("message: " + message + " timestamp: " + timestamp + " timestamp in the map: " + log.get(message));
        	if(timestamp - log.get(message) < 10 || timestamp == log.get(message)) 
        	{
        		result = false;
        	} 
        	else
        	{
				log.put(message, timestamp);
        	}
        	
        	
        }
        else 
        {
        	log.put(message, timestamp);	
        	result = true;
        }


        return result;
    }
}