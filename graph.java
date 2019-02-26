import java.util.*;

public class graph
{	
	private int numTicketsUsed = 0;
	private int numTickets = 0;

	public static void main(String[] args) 
	{
		graph test = new graph();

		String[][] tickets = {{"MUC","LHR"}, {"JFK","MUC"},{"SFO","SJC"},{"LHR","SFO"}};
		List<String> iternary = test.findItinerary(tickets);	
		System.out.println("the iternary is: " + iternary);

        DirectedGraphNode zero = new DirectedGraphNode(0);
        DirectedGraphNode one = new DirectedGraphNode(1);
        DirectedGraphNode two = new DirectedGraphNode(2);
        DirectedGraphNode three = new DirectedGraphNode(3);
        DirectedGraphNode four = new DirectedGraphNode(4);
        DirectedGraphNode five = new DirectedGraphNode(5);
        zero.neighbors.add(one);
        zero.neighbors.add(two);
        zero.neighbors.add(three);

        one.neighbors.add(four);

        two.neighbors.add(four);
        two.neighbors.add(five);

        three.neighbors.add(four);
        three.neighbors.add(five);

        ArrayList<DirectedGraphNode> graph = new  ArrayList<DirectedGraphNode>();
        graph.add(zero);
        graph.add(one);
        graph.add(two);
        graph.add(three);
        graph.add(four);
        graph.add(five);

        ArrayList<DirectedGraphNode> resultTopSort = test.topSortDFS(graph);

        for(DirectedGraphNode node : resultTopSort)
        {
             System.out.println("node label is: " + node.label);
        }

        String[] alienWords = { "wrt",
                              "wrf",
                              "er",
                              "ett",
                              "rftt"};
        String resultAlienOrder = test.alienOrderDFS(alienWords);
        System.out.println("the order of the alien dictionary is: " + resultAlienOrder);
        
        int[][] prerequisites = {{1, 0}};

        int[] courseSchedule = test.findOrderDFS(2, prerequisites);
        System.out.println("the course schedule is: " + Arrays.toString(courseSchedule));
	}
	//Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.
	//BFS Approach
	//Time: O(N)
	//Space: O(N)
	public UndirectedGraphNode cloneGraphBFS(UndirectedGraphNode node) 
	{
		if(node == null)
		{
			return null;
		}

		List<UndirectedGraphNode> queue = new ArrayList<UndirectedGraphNode>();
		HashMap<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();

		UndirectedGraphNode copyNode = new UndirectedGraphNode(node.label);
		map.put(node, copyNode);
		queue.add(node);

		while(!queue.isEmpty())
		{
			UndirectedGraphNode curNode = queue.remove(0);
			for(int i = 0;i < curNode.neighbors.size();i++)
			{	
				UndirectedGraphNode neighborNode = curNode.neighbors.get(i);
				if(!map.containsKey(neighborNode))
				{
					copyNode = new UndirectedGraphNode(neighborNode.label);
					map.put(neighborNode, copyNode);
					queue.add(neighborNode);
				}

				map.get(curNode).neighbors.add(map.get(neighborNode));
			}
		}

		return map.get(node);
    }

    //Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors.
	//DFS Approach
	//Time: O(N)
	//Space: O(N)
    public UndirectedGraphNode cloneGraphDFS(UndirectedGraphNode node) 
	{
		if(node == null)
		{
			return null;
		}

		List<UndirectedGraphNode> stack = new ArrayList<UndirectedGraphNode>();
		HashMap<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();

		UndirectedGraphNode copyNode = new UndirectedGraphNode(node.label);
		map.put(node, copyNode);
		stack.add(node);

		while(!stack.isEmpty())
		{
			UndirectedGraphNode curNode = stack.remove(stack.size() - 1);
			for(int i = 0;i < curNode.neighbors.size();i++)
			{	
				UndirectedGraphNode neighborNode = curNode.neighbors.get(i);
				if(!map.containsKey(neighborNode))
				{
					copyNode = new UndirectedGraphNode(neighborNode.label);
					map.put(neighborNode, copyNode);
					stack.add(neighborNode);
				}

				map.get(curNode).neighbors.add(map.get(neighborNode));
			}
		}

		return map.get(node);
	}

	// Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the itinerary in order. All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.
	// If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string. For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
	// All airports are represented by three capital letters (IATA code).
	// You may assume all tickets form at least one valid itinerary.
	// Example 1:
	// tickets = [["MUC", "LHR"], ["JFK", "MUC"], ["SFO", "SJC"], ["LHR", "SFO"]]
	// Return ["JFK", "MUC", "LHR", "SFO", "SJC"].
	// Example 2:
	// tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
	// Return ["JFK","ATL","JFK","SFO","ATL","SFO"].
	// Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"]. But it is larger in lexical order.
	public List<String> findItinerary(String[][] tickets) 
	{
        List<String> resultList = new ArrayList<String>();
        if (tickets == null || tickets.length == 0)
        {
        	return resultList;
        } 

        HashMap<String, List<String>> adjList = new HashMap<>();
        numTickets = tickets.length;

        //build graph
        for(int i = 0;i < tickets.length;i++)
        {
        	if(!adjList.containsKey(tickets[i][0]))
        	{
        		List<String> newList = new ArrayList<>();
        		newList.add(tickets[i][1]);
        		adjList.put(tickets[i][0], newList);
        	}
        	else
        	{
        		adjList.get(tickets[i][0]).add(tickets[i][1]);
        	}
        }

        // sort vertices in the adjacency list so they appear in lexical order
        for (Map.Entry<String, List<String>> entry : adjList.entrySet()) 
        {
            Collections.sort(entry.getValue());
        }

        resultList.add("JFK");
        findItineraryDfs(resultList, adjList, "JFK");
        return resultList;
    }

    public void findItineraryDfs(List<String> resultList, HashMap<String, List<String>> adjList, String vertex)
    {
    	// base case: vertex v is not in adjacency list
        // v is not a starting point in any itinerary, or we would have stored it
        // thus we have reached end point in our DFS
        if(!adjList.containsKey(vertex))
        {
        	return;
        }

        List<String> tempList = adjList.get(vertex);

        for(int i = 0;i < tempList.size();i++)
        {
        	String neighbor = tempList.get(i);
        	// remove ticket(route) from graph
            tempList.remove(i);
            resultList.add(neighbor);
            numTicketsUsed++;

            findItineraryDfs(resultList, adjList, neighbor);
            System.out.println("numTickets: " + numTickets + " numTicketsUsed: " + numTicketsUsed);
            // we only return when we have used all tickets
            if (numTicketsUsed == numTickets) 
            {
            	return;
            }
            // otherwise we need to revert the changes and try other tickets
            tempList.add(i, neighbor);
            // we must remove the last airport, since in an itinerary, the same airport can appear many times!!
            resultList.remove(resultList.size() - 1);
            numTicketsUsed--;
        }
    }

 	// Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), 
 	// write a function to find the number of connected components in an undirected graph.
	// Example 1:
	//      0          3
	//      |          |
	//      1 --- 2    4
	// Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], return 2.

	// Example 2:
	//      0           4
	//      |           |
	//      1 --- 2 --- 3
	// Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [3, 4]], return 1.

	// Note:
	// You can assume that no duplicate edges will appear in edges. 
	// Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.
    public int countComponents(int n, int[][] edges) 
    {
        int[] id = new int[n];
        
        // 初始化
        for (int i = 0; i < n; i++) {
            id[i] = i;
        }
        
        // union
        for (int[] edge : edges) 
        {              
            int i = find(id, edge[0]);
            int j = find(id, edge[1]);
            id[i] = j;
        }
        
        // 统计根节点个数
        int count = 0;
        for (int i = 0; i < n; i++) 
        {
            if (id[i] == i)
            {
                count++;
            }
        }
        return count;
    }

    public int find(int[] root, int id)
    {
    	while(root[id] != id)
    	{
    		root[id] = root[root[id]];
    		id = root[id];
    	}

    	return id;
    }

    public List<List<Integer>> connectedSetBFS(ArrayList<UndirectedGraphNode> nodes)
    {

        List<List<Integer>> result = new ArrayList<>();

        if(nodes == null || nodes.size() == 0)
        {
            return result;
        }
        
        HashSet<UndirectedGraphNode> visited = new HashSet<>();
        
        for (UndirectedGraphNode node : nodes)
        {
            if (!visited.contains(node))
            {   
                visited.add(node);
                connectedSetBFSHelper(node, visited, result);
            }
        }
        
        return result;
    }

    public void connectedSetBFSHelper(UndirectedGraphNode node, HashSet<UndirectedGraphNode> visited, List<List<Integer>> result){
        
        List<Integer> path = new ArrayList<>();
        
        Queue<UndirectedGraphNode> queue = new LinkedList<>();
        queue.offer(node);
        
        while (!queue.isEmpty())
        {
            UndirectedGraphNode u = queue.poll();
            path.add(u.label);
            
            for (UndirectedGraphNode vertex : u.neighbors)
            {
                if (!visited.contains(vertex))
                {
                    visited.add(vertex);
                    queue.offer(vertex);
                }
            }
        }
        
        Collections.sort(path);
        result.add(path);
        
    }

    public List<List<Integer>> connectedSetDFS(ArrayList<UndirectedGraphNode> nodes)
    {
        List<List<Integer>> result = new ArrayList<>();

        if(nodes == null || nodes.size() == 0)
        {
            return result;
        }

        HashSet<UndirectedGraphNode> visited = new HashSet<>();
        List<Integer> path = new ArrayList<>();

        for (UndirectedGraphNode node : nodes)
        {
            if (!visited.contains(node))
            {
                connectedSetDFSHelper(node, visited, path);
                Collections.sort(path);//keep the result in ascending order
                result.add(new ArrayList<Integer>(path));
                path = new ArrayList<>();
            }
        }

        return result;
    }    

    public void connectedSetDFSHelper(UndirectedGraphNode node, HashSet<UndirectedGraphNode> visited, List<Integer> path)
    {
        visited.add(node);
        path.add(node.label);
        for(UndirectedGraphNode vertex : node.neighbors)
        {
            if(!visited.contains(vertex))
            {
                connectedSetDFSHelper(vertex, visited, path);
            }
        }
    }

    // Given an directed graph, a topological order of the graph nodes is defined as follow:
    // For each directed edge A -> B in graph, A must before B in the order list.
    // The first node in the order can be any node in the graph with no nodes direct to it.
    // Find any topological order for the given graph.
    // Time: O(V + E)
    // Space: O(V)
    public ArrayList<DirectedGraphNode> topSortDFS(ArrayList<DirectedGraphNode> graph) 
    {   
        ArrayList<DirectedGraphNode> resultList = new ArrayList<>();

        if(graph == null || graph.size() == 0)
        {
            return resultList;
        }

        HashSet<DirectedGraphNode> visited = new HashSet<>();
        HashSet<DirectedGraphNode> isLoop = new HashSet<>();
        for(DirectedGraphNode node: graph)
        {
            if(topSortDFSHelper(resultList, visited, isLoop, node))
            {
                System.out.println("a cycle has been found");
                //the problem should assume the graph is a DAG
                return resultList;
            }
            
        }

        return resultList;
    }

    public boolean topSortDFSHelper(ArrayList<DirectedGraphNode> resultList, HashSet<DirectedGraphNode> visited, HashSet<DirectedGraphNode> isLoop, DirectedGraphNode vertex)
    {
        //if it has been visited before
        //simply skip it 
        if(visited.contains(vertex))
        {
            return false;
        }
        
        //if there is a cycle
        if(isLoop.contains(vertex))
        {
            return true;
        }
        
        isLoop.add(vertex);
        
        for(DirectedGraphNode neighbor: vertex.neighbors)
        {
            if(topSortDFSHelper(resultList, visited, isLoop, neighbor))
            {
                return true;
            }
        }
    
        visited.add(vertex);
        //add to the head of the list to mock a stack
        resultList.add(0, vertex);
        return false;
    }

    // Time: O(V + E)
    // Space: O(V)
    public ArrayList<DirectedGraphNode> topSortBFS(ArrayList<DirectedGraphNode> graph) 
    { 
        ArrayList<DirectedGraphNode> resultList = new ArrayList<>();

        if(graph == null || graph.size() == 0)
        {
            return resultList;
        }

        //map to store indegree of all nodes
        //a node that has 0 indegree will not be marked in this map
        HashMap<DirectedGraphNode, Integer> map = new HashMap<>();

        for(DirectedGraphNode node: graph)
        {
            for(DirectedGraphNode neighbor: node.neighbors)
            {   
                //if the node has been marked in previous iterations
                //simply add 1 to the exisitng indegrees
                //if not, set it to 1
                if(map.containsKey(neighbor))
                {
                    map.put(neighbor, map.get(neighbor) + 1);
                }
                else
                {
                    map.put(neighbor, 1);
                }
            }
        }

        Queue<DirectedGraphNode> queue = new LinkedList<>();
        for(DirectedGraphNode node: graph)
        {   
            //add all nodes that have 0 indegree to the queue
            //also add to the result list
            if(!map.containsKey(node))
            {
                queue.add(node);
                resultList.add(node);
            }
        }

        while(!queue.isEmpty())
        {
            DirectedGraphNode node = queue.poll();
            for(DirectedGraphNode neighbor: node.neighbors)
            {   
                //check all neighbor nodes
                //update the map by decrementing indegree by 1
                //if the indegree of a neighbor node is now 0
                //add it to the queue and resultlist as it is the smallest indegree node now
                map.put(neighbor, map.get(neighbor) - 1);
                int indegree = map.get(neighbor);
                if(indegree == 0)
                {
                    queue.add(neighbor);
                    resultList.add(neighbor);
                }
            }
        }
        return resultList;
    }

    // Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to check whether these edges make up a valid tree.
    // For example:
    // Given n = 5 and edges = [[0, 1], [0, 2], [0, 3], [1, 4]], return true.
    // Given n = 5 and edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]], return false.
    // Given n = 5 and edges = [[0, 1], [1, 2], [3, 4]], what should your return? Is this case a valid tree?
    // According to the definition of tree on Wikipedia: “a tree is an undirected graph in which any two vertices are connected by exactly one path. 
    // In other words, any connected graph without simple cycles is a tree.”
    // Note: you can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.
    // Time: O(V + E)
    // Space: O(H)
    public boolean validTreeDFS(int n, int[][] edges) 
    {
        boolean[] visited = new boolean[n];
        List<List<Integer>> adjList = new ArrayList<List<Integer>>(n);

        for (int i = 0; i < n; i++)
        {
            adjList.add(i, new ArrayList<Integer>());
        } 
        
        //construct adjacency list
        for (int[] edge : edges) 
        {
            adjList.get(edge[0]).add(edge[1]);
            adjList.get(edge[1]).add(edge[0]);
        }

        if(hasCyclesDFSHelper(0, -1, visited, adjList))
        {
            return false;
        }

        for(int i = 0;i < visited.length;i++)
        {   
            //if after DFS, there are still vertices unvisited
            //it means the graph is not fully connected 
            if(!visited[i])
            {
                return false;
            }
        }

        return true;        
    }

    public boolean hasCyclesDFSHelper(int vertex, int parentVertex, boolean[] visited, List<List<Integer>> adjList)
    {
        visited[vertex] = true;

        List<Integer> neighbors = adjList.get(vertex);
        for(int i = 0;i < neighbors.size();i++)
        {
            int nextVertex = neighbors.get(i);

            //if the vertex has not been visited
            //visit this vertex
            if(!visited[nextVertex])
            {
                if(hasCyclesDFSHelper(nextVertex, vertex, visited, adjList))
                {
                    return true;
                }
            }

            //if the vertex has been visited and its not the parent vertex of vertex
            //then there is a cycle
            else if(visited[nextVertex] && nextVertex != parentVertex)
            {
                return true;
            }
        }

        return false;
    }

    // Time: O(V + E)
    // Space: O(H)
    public boolean validTreeBFS(int n, int[][] edges) 
    {
        boolean[] visited = new boolean[n];
        List<List<Integer>> adjList = new ArrayList<List<Integer>>(n);

        for (int i = 0; i < n; i++)
        {
            adjList.add(i, new ArrayList<Integer>());
        } 
        
        //construct adjacency list
        for (int[] edge : edges) 
        {
            adjList.get(edge[0]).add(edge[1]);
            adjList.get(edge[1]).add(edge[0]);
        }

        Queue<Integer> queue = new LinkedList<Integer>();
        //start at vertex "0"
        queue.add(0);
        visited[0] = true;
        while(!queue.isEmpty())
        {
            int vertex = queue.poll();
            List<Integer> neighbors = adjList.get(vertex);
            for(int i = 0;i < neighbors.size();i++)
            {
                int neighbor = neighbors.get(i);
                //if the vertex has been visited before
                //a cycle exists
                if(visited[neighbor])
                {
                    return false;
                }

                visited[neighbor] = true;
                queue.offer(neighbor);
                //we have get vertex -> neighbor
                //in order to not report cycle when we have neighbor -> vertex
                //need to delete vertex from neighbor's adjlist
                adjList.get(neighbor).remove((Integer)vertex);
            }
        }

        for(int i = 0;i < visited.length;i++)
        {   
            //if after BFS, there are still vertices unvisited
            //it means the graph is not fully connected 
            if(!visited[i])
            {
                return false;
            }
        }

        return true;
    }

    // There are a total of n courses you have to take, labeled from 0 to n - 1.
    // Some courses may have prerequisites, 
    // for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
    // Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?
    // For example:
    // 2, [[1,0]]
    // There are a total of 2 courses to take. To take course 1 you should have finished course 0. So it is possible.
    // 2, [[1,0],[0,1]]
    // There are a total of 2 courses to take. To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.
    public boolean canFinishBFS(int numCourses, int[][] prerequisites) 
    {
        int[] courseInDegree = new int[numCourses];

        //mark indegree for all nodes at first position of prerequisites 
        //[1, 0] -> courseInDegree[1]++, [2, 3] -> courseInDegree[2]++
        for(int[] courses : prerequisites)
        {
            int required = courses[0];
            courseInDegree[required]++;
        }

        Queue<Integer> queue = new LinkedList<Integer>();
        int coursesCanTake = 0;
        for(int i = 0;i < numCourses;i++)
        {
            //add to the queue if nodes have 0 indegree
            if(courseInDegree[i] == 0)
            {
                queue.offer(i);
            }
        }

        //if there is a cycle
        //then there is at least one course that will not be added to the queue
        while(!queue.isEmpty())
        {
            int required = queue.poll();
            coursesCanTake++;
            for(int[] course : prerequisites)
            {   
                int courseNext = course[0];
                int prerequisite = course[1];

                //if course prerequisite is found
                //decrement the indegree of the next course by 1
                //add to the queue if the indegree is 0 after derementing by 1
                if(required == prerequisite)
                {
                    courseInDegree[courseNext]--;
                    if(courseInDegree[courseNext] == 0)
                    {
                        queue.offer(courseNext);
                    }
                }
            }
        }

        return coursesCanTake == numCourses;
    }

    public boolean canFinishDFS(int numCourses, int[][] prerequisites)
    {
        //Key required course, value, other available courses after required finished
        HashMap<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
        for(int[] courseRelation : prerequisites) 
        {
            int required = courseRelation[1];
            int availableThen = courseRelation[0];
            if(map.containsKey(required)) 
            {
                map.get(required).add(availableThen);
            } else 
            {
                List<Integer> availableCourse = new ArrayList<Integer>();
                availableCourse.add(availableThen);
                map.put(required, availableCourse);
            }
        }

        int[] completed = new int[numCourses];
        for(int i = 0; i < numCourses; i++) 
        {
            if(hasLoop(completed, map, i)) 
            {
                return false;
            }
        }

        return true;
    }

    public boolean hasLoop(int[] completed, HashMap<Integer, List<Integer>> map, int course) 
    {
        if(completed[course] == 1)
        {
            return true;
        }
            
        //this is pruning, if it's already visited and safe, set to -1 in order avoid re-test
        if(completed[course] == -1)
        {
            return false;
        }
            
        completed[course] = 1;
        if (map.containsKey(course)) 
        {
            for(int availableCourse : map.get(course)) 
            {
                if(hasLoop(completed, map, availableCourse)) 
                {
                    return true;
                }
            }
        }

        completed[course] = -1;
        return false;
    }

    // 4, [[1,0],[2,0],[3,1],[3,2]]
    // There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0. 
    // So one correct course order is [0,1,2,3]. Another correct ordering is[0,2,1,3].
    public int[] findOrderBFS(int numCourses, int[][] prerequisites) 
    {
        int[] courseInDegree = new int[numCourses];
        int[] result = new int[numCourses];

        int len = prerequisites.length;
        if(len == 0)
        {
            for(int i = 0;i < numCourses;i++)
            {
                result[i] = i;
            }

            return result;
        }

        //mark indegree for all nodes at first position of prerequisites 
        //[1, 0] -> courseInDegree[1]++, [2, 3] -> courseInDegree[2]++
        for(int[] courses : prerequisites)
        {
            int required = courses[0];
            courseInDegree[required]++;
        }

        Queue<Integer> queue = new LinkedList<Integer>();
        int coursesCanTake = 0;
        for(int i = 0;i < numCourses;i++)
        {
            //add to the queue if nodes have 0 indegree
            if(courseInDegree[i] == 0)
            {
                queue.offer(i);
            }
        }

        //if there is a cycle
        //then there is at least one course that will not be added to the queue
        while(!queue.isEmpty())
        {
            int required = queue.poll();
            result[coursesCanTake] = required;
            coursesCanTake++;
            for(int[] course : prerequisites)
            {   
                int courseNext = course[0];
                int prerequisite = course[1];

                //if course prerequisite is found
                //decrement the indegree of the next course by 1
                //add to the queue if the indegree is 0 after derementing by 1
                if(required == prerequisite)
                {
                    courseInDegree[courseNext]--;
                    if(courseInDegree[courseNext] == 0)
                    {
                        queue.offer(courseNext);
                    }
                }
            }
        }

        //if there is a cycle or disconnected courses
        if(coursesCanTake != numCourses)
        {
            return new int[0];
        }

        return result;   
    }

    public int[] findOrderDFS(int numCourses, int[][] prerequisites)
    {   
        int[] result = new int[numCourses];

        int len = prerequisites.length;
        if(len == 0)
        {
            for(int i = 0;i < numCourses;i++)
            {
                result[i] = i;
            }

            return result;
        }
        
        //Key required course, value, other available courses after required finished
        HashMap<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
        for(int[] courseRelation : prerequisites) 
        {
            int required = courseRelation[1];
            int availableThen = courseRelation[0];
            if(map.containsKey(required)) 
            {
                map.get(required).add(availableThen);
            } else 
            {
                List<Integer> availableCourse = new ArrayList<Integer>();
                availableCourse.add(availableThen);
                map.put(required, availableCourse);
            }
        }

        int[] completed = new int[numCourses];
        Stack<Integer> stack = new Stack<Integer>();
        for(int i = 0; i < numCourses; i++) 
        {
            if(hasLoopAndStash(completed, map, i, stack)) 
            {
                return new int[0];
            }
        }

        int counter = 0;
        while(!stack.isEmpty())
        {
            result[counter] = stack.pop();
            counter++;
        }

        return result;
    }

    public boolean hasLoopAndStash(int[] completed, HashMap<Integer, List<Integer>> map, int course, Stack<Integer> stack) 
    {
        if(completed[course] == 1)
        {
            return true;
        }
            
        //this is pruning, if it's already visited and safe, set to -1 in order avoid re-test
        if(completed[course] == -1)
        {
            return false;
        }
            
        completed[course] = 1;
        if (map.containsKey(course)) 
        {
            for(int availableCourse : map.get(course)) 
            {
                if(hasLoopAndStash(completed, map, availableCourse, stack)) 
                {
                    return true;
                }
            }
        }

        completed[course] = -1;
        stack.push(course);

        return false;
    }


    // There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. 
    // You receive a list of words from the dictionary, where words are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.
    // For example,
    // Given the following words in dictionary,
    // [
    //   "wrt",
    //   "wrf",
    //   "er",
    //   "ett",
    //   "rftt"
    // ]
    // The correct order is: "wertf".

    // Note:
    // You may assume all letters are in lowercase.
    // If the order is invalid, return an empty string.
    // There may be multiple valid order of letters, return any one of them is fine.
    // Time: O(VE)
    // Space: O(VE)
    public String alienOrderDFS(String[] words) 
    {
        String result = "";
        Map<Character, List<Character>> graph = new HashMap<Character, List<Character>>();

        //initialize graph by adding empty adjacency list for each character
        for(int i = 0;i < words.length;i++)
        {
            String word = words[i];
            for(int j = 0;j < word.length();j++)
            {
                char c = word.charAt(j);
                if(!graph.containsKey(c))
                {
                    graph.put(c, new ArrayList<Character>());
                }
            }

            //comparing previous word and next word char by char
            //add the edge to the graph whenever the char is different
            if(i > 0)
            {   
                String word1 = words[i - 1];
                String word2 = words[i];
                int counter = 0;
                while(counter < word1.length() && counter < word2.length() 
                    && word1.charAt(counter) == word2.charAt(counter))
                {
                    counter++;
                }

                if(counter < word1.length() && counter < word2.length())
                {
                    graph.get(word1.charAt(counter)).add(word2.charAt(counter));
                }
            }
        }
        
        //System.out.println("the graph is: " + graph);
        Set<Character> visited = new HashSet<Character>();
        Set<Character> isLoop = new HashSet<Character>();
        Stack<Character> stack = new Stack<Character>();
        StringBuilder sb = new StringBuilder();
        for(char c : graph.keySet())
        {   
            //if there is a cycle
            //then such order does not exist
            if(alienOrderDFSHelper(graph, visited, isLoop, c, stack))
            {   
                //System.out.println("there is a cycle");
                return "";
            }
        }

        while(!stack.isEmpty())
        {
            sb.append(stack.pop());
        }

        result = sb.toString();
        return result;
    }

    //return true if there is a cycle
    public boolean alienOrderDFSHelper(Map<Character, List<Character>> graph, Set<Character> visited, 
                                        Set<Character> isLoop, char c, Stack<Character> stack)
    {   
        if(visited.contains(c))
        {
            return false;
        }

        //check if there is a cycle
        if(isLoop.contains(c))
        {
            return true;
        }

        isLoop.add(c);

        for(char next : graph.get(c))
        {
            if(alienOrderDFSHelper(graph, visited, isLoop, next, stack))
            {
                return true;
            }
        }

        visited.add(c);
        stack.push(c);
        return false;
    }

    // Among all possible rooted trees, those with minimum height are called minimum height trees (MHTs). 
    // Given such a graph, write a function to find all the MHTs and return a list of their root labels.
    // The graph contains n nodes which are labeled from 0 to n - 1. 
    // You will be given the number n and a list of undirected edges (each edge is a pair of labels).

    // You can assume that no duplicate edges will appear in edges. 
    // Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.
    // Example 1:

    // Given n = 4, edges = [[1, 0], [1, 2], [1, 3]]

    //         0
    //         |
    //         1
    //        / \
    //       2   3
    // return [1]

    // Example 2:

    // Given n = 6, edges = [[0, 3], [1, 3], [2, 3], [4, 3], [5, 4]]

    //      0  1  2
    //       \ | /
    //         3
    //         |
    //         4
    //         |
    //         5
    // return [3, 4]

    //Time: O(N)
    //Space: O(N)
    public List<Integer> findMinHeightTrees(int n, int[][] edges) 
    {
        List<Integer> leaves = new ArrayList<Integer>();
        Map<Integer, Set<Integer>> graph = new HashMap<Integer, Set<Integer>>();
        
        if(n == 1)
        {
            leaves.add(0);
        }

        for(int[] edge : edges)
        {
            //put edge[1] into edge[0] neighbours
            if(graph.containsKey(edge[0])) {
                Set<Integer> neighbor = graph.get(edge[0]);
                neighbor.add(edge[1]);
            } else {
                Set<Integer> neighbor = new HashSet<Integer>();
                neighbor.add(edge[1]);
                graph.put(edge[0],neighbor);
 
            }
            
            //put edge[0] into edge[1] neighbours
            if(graph.containsKey(edge[1])) {
                Set<Integer> anotherneighbor = graph.get(edge[1]);
                anotherneighbor.add(edge[0]);
            } else {
                Set<Integer> anotherneighbor = new HashSet<Integer>();
                anotherneighbor.add(edge[0]);
                graph.put(edge[1],anotherneighbor);
            }
        }

        // In order to manipulate the graph map, I need to find leaves first
        // leaves are nodes that have 1 indegree
        for(Map.Entry<Integer, Set<Integer>> node : graph.entrySet()) 
        {
            if(node.getValue().size() == 1) 
            {
                leaves.add(node.getKey());
            }
        }

        //each iteration gets rid of all leaves
        //until the number of vertices left is less than 3
        //this way the remaining nodes are roots that form MITS
        while(n > 2)
        {
            n -= leaves.size();
            List<Integer> newLeaves = new ArrayList<Integer>();

            for(Integer leaf : leaves)
            {
                Set<Integer> neigbours = graph.get(leaf);
                for(Integer neighbor : neigbours)
                {   
                    //remove each leaf in its neighbor's list
                    //if after removel, its neighbor is now 1 indegree
                    graph.get(neighbor).remove(leaf);
                    if(graph.get(neighbor).size() == 1)
                    {
                        newLeaves.add(neighbor);
                    }
                }
            }

            leaves = newLeaves;
        }

        return leaves;
    }
}


// Definition for undirected graph.
class UndirectedGraphNode 
{
    int label;
	List<UndirectedGraphNode> neighbors;
    UndirectedGraphNode(int x) 
    { 
    	label = x; 
    	neighbors = new ArrayList<UndirectedGraphNode>(); 
    }
};

class DirectedGraphNode 
{
    int label;
    ArrayList<DirectedGraphNode> neighbors;
    DirectedGraphNode(int x) 
    { 
        label = x; 
        neighbors = new ArrayList<DirectedGraphNode>(); 
    }
};
