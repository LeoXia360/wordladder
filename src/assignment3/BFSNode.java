/* WORD LADDER Main.java
 * EE422C Project 3 submission by
 * Replace <...> with your actual data.
 * Leo Xia
 * LX939
 * 16450
 * Tim Yoder
 * TJY263
 * <Student2 5-digit Unique No.>
 * Slip days used: <0>
 * Git URL: https://github.com/LeoXia360/wordladder
 * Fall 2016
 */

package assignment3;

public class BFSNode {
	
	private  String pre;
	private  int distance;
	private String word;
	
	public BFSNode(String p, int d, String s)
	{
		pre=p;
		distance=d;
		word=s;
	}
	public BFSNode()
	{
		pre="";
		distance=0;
		word="";
	}
	public  String getPre()
	{
		return pre;
	}
	public int getDistance()
	{
		return distance;
	}
	public String getWord()
	{
		return word;
	}
	public void setPre(String s)
	{
		pre=s;
	}
	public void setDistance(int d)
	{
		distance=d;
	}
	public void setWord(String s)
	{
		word=s;
	}
	public static BFSNode generateBFSNode(final String p, final int d, final String s)
	{
		BFSNode bfs = new BFSNode();
		
		bfs.setPre(p);
		bfs.setDistance(d);
		bfs.setWord(s);
		
		return bfs;
	}
}
