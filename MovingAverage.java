import java.util.*;

// Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.

// For example,
// MovingAverage m = new MovingAverage(3);
// m.next(1) = 1
// m.next(10) = (1 + 10) / 2
// m.next(3) = (1 + 10 + 3) / 3
// m.next(5) = (10 + 3 + 5) / 3
public class MovingAverage {

    public int size;

    public int queueSize;

    public double sum;

    public LinkedList<Integer> queue;
	/** Initialize your data structure here. */
    public MovingAverage(int size) {
        this.size = size;
        this.queueSize = 0;
        this.sum = 0;
        this.queue = new LinkedList<Integer>();
    }
    
    public double next(int val) {


        if(queueSize < size) {
            queue.addLast(val);
            sum += val;
            queueSize++;
        }
        else {
            int dataRemoved = queue.poll();
            sum -= dataRemoved;
            queue.addLast(val);
            sum += val;
        }

        return sum/queueSize;
    }

    public static void main(String[] args) {

        /**
    	 * Your MovingAverage object will be instantiated and called as such:
    	 * MovingAverage obj = new MovingAverage(size);
    	 * double param_1 = obj.next(val);
    	 */

        MovingAverage test = new MovingAverage(3);
        System.out.println(test.next(1));
        System.out.println(test.next(10));
        System.out.println(test.next(3));
        System.out.println(test.next(5));

    }
}