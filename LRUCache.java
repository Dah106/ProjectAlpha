import java.util.*;

// Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and set.

// get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
// set(key, value) - Set or insert the value if the key is not already present. 
// When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
public class LRUCache
{	
	public static void main(String[] args) 
	{
		LRUCache test = new LRUCache(2);
		test.set(2,1);
		test.set(1,1);
		System.out.println("result: " + test.get(2));
		test.set(4,1);
		System.out.println("result: " + test.get(1));
		System.out.println("result: " + test.get(2));
	}
	//queue like structure
	//head points to the dummy head that its next is the least recently used item
	//tail points to the dummy tail that its prev is the newest item
	HashMap<Integer, cacheNode> map; 
	private cacheNode head;
	private cacheNode tail;
	private int capacity;
	public LRUCache(int capacity) 
	{
        map = new HashMap<Integer, cacheNode>();
        head = new cacheNode(-1, -1);
        tail = new cacheNode(-1, -1);
        this.capacity = capacity;
        head.next = tail;//connect head and tail
        tail.prev = head;
    }
    
    public int get(int key) 
    {	
    	if(!map.containsKey(key))
    	{
    		return -1;
    	}

    	cacheNode current = map.get(key);

    	//since the item is just called
    	//move it to the tail so that the most recently used item is recorded
    	current.prev.next = current.next;
    	current.next.prev = current.prev;

    	updateMostRecentNode(current);
        return current.value;
    }
    
    public void set(int key, int value) 
    {
        if(get(key) != -1)
        {
        	map.get(key).value = value;
        	return;
        }

        if(map.size() == capacity)
        {
        	map.remove(head.next.key);
        	head.next = head.next.next;
        	head.next.prev = head;
        }

        cacheNode newNode = new cacheNode(key, value);
        map.put(key, newNode);
        updateMostRecentNode(newNode);
    }

    public void updateMostRecentNode(cacheNode current)
    {	

    	current.prev = tail.prev;
    	tail.prev = current;
    	current.prev.next = current;
    	current.next = tail;
    }

    class cacheNode
    {
    	private int key;
    	private int value;
    	private cacheNode prev;
    	private cacheNode next;

    	public cacheNode(int key, int value)
    	{
    		this.key = key;
    		this.value = value;
    		this.prev = null;
    		this.next = null;
    	}
    }
}