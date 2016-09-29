package assignment3;


import java.util.ArrayList;
import java.util.Set;
import java.util.TreeMap;

/**
 * Abstract class for nodes to use in DFS and BFS
 * @author Leo
 *
 */

public class Node {
	
	private String value;
	boolean visited;
	static TreeMap<String, Node> words = new TreeMap<String, Node>();
	static Set<String> dict = Main.makeDictionary();
	ArrayList<Node> adjacent;

	public Node(String val){
		value = val;
		visited = false;
		this.adjacent = new ArrayList<Node>();
		

	}
	/**
	 * Creates array list for each node
	 * @param words
	 */
	public static void create_dictionary(){
		for(String val : dict){
			Node n = new Node(val);
			words.put(val, n);
		}
	}
	public String toString(){
		return this.value;
	}
	
	public void create_adjacent(){
			for(Node n : words.values()){
		       //1st check is see if equal length
		       if(n.value.length() == this.value.length()){
		    	   int count = 0;
		    	   int length = n.value.length();
		    	   while(length > 0){
		    		   if(n.value.charAt(length-1) == this.value.charAt(length-1)){
		    			   count++;
		    		   }
		    		   length -= 1;
		    	   }
		    	   if (count == n.value.length() - 1){
		    		   this.adjacent.add(n);
		    	   }
		       }

			}

	}
	
	
	/**
	 * retrieve list of connected nodes
	 */
	public ArrayList<Node> adjacent(){
		return this.adjacent;
	}
	
	/**
	 * Set value function
	 */
	public void set_value(String value){
		this.value = value;
	}
	
	/**
	 * Get value function
	 */
	public String get_value(){
		return this.value;
	}
	
	/**
	 * Set Node to visited and get visited value
	 * Don't ever check this Node again
	 */
	public void set_visited(){
		this.visited = true;
	}
	public boolean get_visited(){
		return this.visited;
	}
}
	
	