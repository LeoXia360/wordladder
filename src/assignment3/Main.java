/* WORD LADDER Main.java
 * EE422C Project 3 submission by
 * Replace <...> with your actual data.
 * <Student1 Name>
 * <Student1 EID>
 * <Student1 5-digit Unique No.>
 * <Student2 Name>
 * <Student2 EID>
 * <Student2 5-digit Unique No.>
 * Slip days used: <0>
 * Git URL:
 * Fall 2016
 */


package assignment3;
import java.util.*;

import sun.awt.SunHints.Value;

import java.io.*;

public class Main {
	
	// static variables and constants only here.
	static ArrayList<String> DFS_list = new ArrayList<String>();
	static ArrayList<String> input = new ArrayList<String>();
	
	public static void main(String[] args) throws Exception {
		
		Scanner kb;	// input Scanner for commands
		PrintStream ps;	// output file
		// If arguments are specified, read/write from/to files instead of Std IO.
		if (args.length != 0) {
			kb = new Scanner(new File(args[0]));
			ps = new PrintStream(new File(args[1]));
			System.setOut(ps);			// redirect output to ps
		} else {
			kb = new Scanner(System.in);// default from Stdin
			ps = System.out;			// default to Stdout
		}
		initialize();
		input = parse(kb);
		getWordLadderDFS(input.get(0), input.get(1));
		printLadder(DFS_list);
		// TODO methods to read in words, output ladder
	}
	
	public static void initialize() {
		// initialize your static variables or constants here.
		// We will call this method before running our JUNIT tests.  So call it 
		// only once at the start of main.	
		Node.create_dictionary();
		for(Node temp : Node.words.values()){
			temp.create_adjacent();
		}

	}
	
	/**
	 * @param keyboard Scanner connected to System.in
	 * @return ArrayList of 2 Strings containing start word and end word. 
	 * If command is /quit, return empty ArrayList. 
	 */
	public static ArrayList<String> parse(Scanner keyboard) {
		ArrayList<String> input = new ArrayList<String>();
		keyboard = new Scanner(System.in);
		System.out.println("Enter the start word: ");
		input.add(keyboard.nextLine());
		if(input.contains("/quit")){
			System.exit(0);
		}
		System.out.println("Enter the end word: ");
		input.add(keyboard.nextLine());
		if(input.contains("/quit")){
			System.exit(0);
		}
		return input;

	}
	
	public static ArrayList<String> getWordLadderDFS(String start, String end) throws IOException {
		// Returned list should be ordered start to end.  Include start and end.
		// Return empty list if no ladder.
		if(start.length() == end.length()){
	    	   int count = 0;
	    	   int length = start.length();
	    	   while(length > 0){
	    		   if(start.charAt(length-1) == end.charAt(length-1)){
	    			   count++;
	    		   }
	    		   length -= 1;
	    	   }
	    	   if (count == start.length() - 1){
	    		   System.out.println("a " + 0 + "-rung word ladder exists between " + start + " and " + end + ".");
	    		   return DFS_list;
	    	   }
	       }
		
		try{
			wordLadderRecursionDFS(start, end);
		}catch(StackOverflowError t){
			wordLadderRecursionDFS(end, start);
		}
		
		if(DFS_list.isEmpty()){
			System.out.println("no word ladder can be found between " + start + " and " + end + ".");
			return DFS_list;
		}
		DFS_list.add(0, start.toUpperCase());
		DFS_list.remove(DFS_list.size()-1);
		int length = DFS_list.size()-2;
		System.out.println("a " + length + "-rung word ladder exists between " + start + " and " + end + ".");
		return DFS_list;
		
	}
	
	   public static ArrayList<String> getWordLadderBFS(String start, String end) {
			
			// TODO some code
			Set<String> dict = makeDictionary();
			// TODO more code
			Queue<BFSNode> q = new LinkedList<BFSNode>();
			ArrayList<BFSNode> graph = new ArrayList<BFSNode>();
			Iterator<String> it = dict.iterator();
			BFSNode test = new BFSNode();
			while(it.hasNext())
			{
				graph.add(new BFSNode(null,-1,it.next()));
			}
			int num = findNodeIndex(start, graph);
			BFSNode beg = graph.get(num);
			beg.setDistance(0);
			q.add(beg);
			int iq=0;
			boolean endFound=false;
			while(q.peek()!=null&&!endFound)
			{
				BFSNode n1 = q.remove();
				String s1 = n1.getWord();
				for(int i=0; i<graph.size();i++)
				{
					String s2=graph.get(i).getWord();
					for(int a=0;a<s1.length();a++)
					{
						if(graph.get(i).getDistance()==-1)
						{	
							String st1=s1.substring(0, a)+"-"+s1.substring(a+1);
							String st2=s2.substring(0, a)+"-"+s2.substring(a+1);
							if(st1.equals(st2))
							{
								graph.get(i).setPre(s1);
								graph.get(i).setDistance(n1.getDistance()+1);
								q.add(graph.get(i));
								if(s2.equalsIgnoreCase(end))
									endFound=true;
							}
						}
					}
				}
			}
			int indexOfFinalWord = findNodeIndex(end,graph);
			int currentIndex=indexOfFinalWord;
			ArrayList<String> finalLadder = new ArrayList<String>();
			if(graph.get(indexOfFinalWord).getDistance()!=-1)
			{
				for(int a=0; a<=graph.get(indexOfFinalWord).getDistance();a++)
				{
					finalLadder.add(graph.get(currentIndex).getWord());
					if(a!=graph.get(indexOfFinalWord).getDistance())
						currentIndex=findNodeIndex(graph.get(currentIndex).getPre(),graph);
				}
			}
			else
			{
				finalLadder.add(end);
				finalLadder.add("");
				finalLadder.add(start);
			}
			Collections.reverse(finalLadder);
			return finalLadder; // replace this line later with real return
		}
	public static int findNodeIndex(String s, ArrayList<BFSNode> a){
	   	for(int p=0;p<a.size();p++){	
	   		if(s.equals(a.get(p).getWord())){
	   			return p;
	   		}
	   	}
	   	return -1;
	   }
	public static Set<String>  makeDictionary () {
		Set<String> words = new HashSet<String>();
		Scanner infile = null;
		try {
			infile = new Scanner (new File("five_letter_words.txt"));
		} catch (FileNotFoundException e) {
			System.out.println("Dictionary File not Found!");
			e.printStackTrace();
			System.exit(1);
		}
		while (infile.hasNext()) {
			words.add(infile.next().toUpperCase());
		}
		return words;
	}
	
	public static void printLadder(ArrayList<String> ladder) {
		for(int a=0; a<ladder.size(); a++){
			System.out.println(ladder.get(a).toLowerCase());
		}
	}
	
	/**
	 * Finds the word ladder between two words using the DFS algorithm
	 * keeps going down one rabbit hole 
	 * if start is equal to end then it'll append to the ArrayList and return DFS_list
	 * if it is not equal then it'll return an empty DFS_list 
	 * if the method sees an empty list, it knows it went down the wrong rabbit hole
	 *
	 * @param start
	 * @param end
	 * @return
	 * @throws IOException
	 */
	public static ArrayList<String> wordLadderRecursionDFS(String start, String end) throws IOException{
		start = start.toUpperCase();
		end = end.toUpperCase();
		DFS_list.add(start);
		Node current = Node.words.get(start);
		Node.words.get(start).visited = true;
		
		// Base case, returns if true
		if(start.equals(end)){
			DFS_list.add(end);
			return DFS_list;
		}
		//cycle through all adjacent non-visited nodes
		int adj_index = 0;
		while(adj_index<current.adjacent.size()){
			Node val = current.adjacent.get(adj_index);
			if(val.get_value() != null && val.visited == false){
				val.visited = true;
				DFS_list = wordLadderRecursionDFS(val.get_value(), end);
				if(!DFS_list.isEmpty()){
					DFS_list.add(0,val.get_value());
					return DFS_list;
				}
			
			}
			adj_index++;
		}
		//clear the DFS_list and return if not found in this rabbit hole
		DFS_list.clear();
		return DFS_list;
	}
}
