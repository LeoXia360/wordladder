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
