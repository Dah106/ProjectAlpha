import java.util.*;
public class meetingRoom
{
	// Given [[0, 30],[5, 10],[15, 20]],
	// return false.
	public boolean canAttendMeetings(Interval[] intervals) 
	{		
		if(intervals == null || intervals.length == 0) return true;
        Arrays.sort(intervals, new Comparator<Interval>(){
            public int compare(Interval i1, Interval i2){
                return i1.start - i2.start;
            }
        });
        int end = intervals[0].end;
        // 检查每一个Interval
        for(int i = 1; i < intervals.length; i++){
            // 如果Interval的开始时间小于之前最晚的结束时间，就返回假
            if(intervals[i].start < end) return false;
            end = Math.max(end, intervals[i].end);
        }
        return true;
    }

    public int minMeetingRooms(Interval[] intervals) 
    {
        if(intervals == null || intervals.length == 0) return 0;
        Arrays.sort(intervals, new Comparator<Interval>(){
            public int compare(Interval i1, Interval i2){
                return i1.start - i2.start;
            }
        });

        // 用堆来管理房间的结束时间
        PriorityQueue<Integer> endTimes = new PriorityQueue<Integer>();
        endTimes.offer(intervals[0].end);
        for(int i = 1; i < intervals.length; i++)
        {	
        	System.out.println("the queue is : " + endTimes);
        	System.out.println("the interval start : " + intervals[i].start + " queue peek: " + endTimes.peek());
            // 如果当前时间段的开始时间大于最早结束的时间，则可以更新这个最早的结束时间为当前时间段的结束时间，如果小于的话，就加入一个新的结束时间，表示新的房间
            if(intervals[i].start >= endTimes.peek())
            {	
                endTimes.poll();
            }
            endTimes.offer(intervals[i].end);
        }
        // 有多少结束时间就有多少房间
        return endTimes.size();
    }

	// Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).

	// You may assume that the intervals were initially sorted according to their start times.

	// Example 1:
	// Given intervals [1,3],[6,9], insert and merge [2,5] in as [1,5],[6,9].
	// if given [s1, e1] [snew, enew] snew > e1 || enew < s1 
	// then newInterval does not overlap with current interval
	// otherwise, the new interval should be derived as [min(s1, snew), math(e1, enew)]
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) 
    {
        List<Interval> resultList = new ArrayList<Interval>();

        int inserPosition = 0;
        for(int i = 0;i < intervals.size();i++)
        {
        	Interval tempInterval = intervals.get(i);
        	//need to increment insert position as potential overlaps could happen in the rest of the list
        	if(newInterval.start > tempInterval.end)
        	{
        		resultList.add(tempInterval);
        		inserPosition++;
        	}
        	//simply adding the interval in the list as the newinterval is the earlist time interval
        	else if(newInterval.end < tempInterval.start)
        	{
        		resultList.add(tempInterval);
        	}
        	//just updating the newInterval thus skipping the overlapping one
        	else
        	{
        		newInterval.start = Math.min(newInterval.start, tempInterval.start);
        		newInterval.end = Math.max(newInterval.end, tempInterval.end);
        	}

        }

        //add the merged interval in the end
       	resultList.add(inserPosition, newInterval);

       	return resultList;

    }

 	// Given a collection of intervals, merge all overlapping intervals.

	// For example,
	// Given [1,3],[2,6],[8,10],[15,18],
	// return [1,6],[8,10],[15,18].
    public List<Interval> merge(List<Interval> intervals) 
    {
     	List<Interval> resultList = new ArrayList<Interval>();

     	if(intervals == null || intervals.size() == 0)
     	{
     		return resultList;
     	}

     	Comparator<Interval> intervalComparator = new Comparator<Interval>(){
     		public int compare(Interval i1, Interval i2){
                if(i1.start == i2.start)
                {
                	return i1.end - i2.end;
                }
                return i1.start - i2.start;
            }
     	};

     	Collections.sort(intervals, intervalComparator);


     	Interval lastInterval = intervals.get(0);
        for(int i = 1;i < intervals.size();i++)
        {
        	Interval currentInterval = intervals.get(i);

        	//if there is an overlap
        	//need to update last interval
        	//as the list has been sorted
        	//the lastInterval.start is the min (unless lastInterval.start == currentInterval.start)
        	if(lastInterval.end >= currentInterval.start)
        	{
        		lastInterval.end = Math.max(lastInterval.end, currentInterval.end);
        	}
        	else
        	{
        		resultList.add(lastInterval);
        		lastInterval = currentInterval;
        	}
        }

        resultList.add(lastInterval);
        return resultList;
    }

    public static void main(String[] args) 
    {


    	Interval i1 = new Interval(5, 8);
    	Interval i2 = new Interval(10, 12);

    	Interval i3 = new Interval(0, 30);
    	Interval i4 = new Interval(5, 10);
    	Interval i5 = new Interval(15, 20);


    	Interval i6 = new Interval(1, 3);
    	Interval i7 = new Interval(6, 9);
    	Interval i8 = new Interval(2, 5);

    	Interval[] intervalArr = {i1, i2};
    	Interval[] intervalArr1 = {i3, i4, i5};
    	List<Interval> intervalArr2 = new ArrayList<Interval>();
    	intervalArr2.add(i6);
    	intervalArr2.add(i7);


    	meetingRoom test = new meetingRoom();
    	System.out.println("current meeting room has conflict: " + test.canAttendMeetings(intervalArr)); 
    	System.out.println("min meeting rooms are: " + test.minMeetingRooms(intervalArr1));
    	//System.out.println("insert interval: " + test.insert(intervalArr2, i8));

    }
}

class Interval 
{
	int start;
	int end;
	Interval() 
	{ 
		start = 0; 
		end = 0; 
	}
	Interval(int s, int e) 
	{ 
		start = s; 
		end = e;
	}; 
}