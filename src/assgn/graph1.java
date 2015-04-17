package assgn;

import java.util.ArrayList;
import java.util.Random;

public class graph1 {

	int limit;
	int[] degree;
	ArrayList<Edge>[] adj;
	int vertexcount;
	int[] colour = new int[5000];
	int[] CC = new int[5000];
	int CC_count=0;
	
	
	public void checkConnected()
	{
		for(int v=0;v<5000;v++)
		{
			if(colour[v]==-1)
			{
				CC_count+=1;
				this.DFS(v);
			}
		}
		ArrayList<Integer>[] components = (ArrayList<Integer>[])new ArrayList[CC_count];
		if(CC_count>0)
		{
			for(int i=0;i<5000;i++)
			{
				components[CC[i]].add(i);
			}
			
			Random rand = new Random();
			
			for(int i=0;i<CC_count;i++)
			{
				int u = components[i].get(rand.nextInt(components[i].size()));
				int v = components[i+1].get(rand.nextInt(components[i+1].size()));
				int w = rand.nextInt(1000)+1;
				Edge temp = new Edge(u, v, w);
				Edge temp1 = new Edge(v, u, w);
				adj[u].add(temp);						
				adj[v].add(temp1);
				degree[u]+=1;
				degree[v]+=1;
			}
		}
	}
	
	public void DFS(int v)
	{
		colour[v]=0;
		CC[v] = CC_count;
		
		for(Edge e: adj[v])
		{
			int w = e.getOtherEnd(v);
			
			if(colour[w]==-1)
				DFS(w);
		}
		colour[v]=1;
	}
	
	public graph1(int vertexcount, String S)
	{
		this.vertexcount=vertexcount;
		if (S.equals("sparse"))
		{
			limit=6;
		}
		else if (S.equals("dense"))
		{
			limit=1000;
		}
		
		//Edge[] E = new Edge[limit];
		
		degree = new int[vertexcount];
		for(int i=0;i<vertexcount;i++)
		{
			degree[i]=0;
		}
		adj = (ArrayList<Edge>[])new ArrayList[vertexcount];
		for(int i=0; i<vertexcount; i++){
			adj[i] = new ArrayList<Edge>();
		}
		boolean set;
		
		for(int i=0;i<vertexcount;i++)
		{			
			
			int u=i;
			Random rand = new Random();
			
			if(i < (0.98)*vertexcount)
			{	
				while(degree[u]<limit)		
				{	//System.out.println("Degree: " + degree[u]);
					set=false;		
					while(set==false)
					{	
						int v = rand.nextInt(5000);
						//System.out.println("Generated vertex: " + v);
						int w = rand.nextInt(1000) + 1;
						Edge temp = new Edge(u, v, w);
						Edge temp1 = new Edge(v, u, w);
						if(u!=v && degree[v]<limit && !adj[u].contains(temp) && !adj[v].contains(temp1))
						{						
							adj[u].add(temp);						
							adj[v].add(temp1);
							degree[u]+=1;
							degree[v]+=1;
							set=true;
						}	
					}	
				}
			}	
			else
			{
				for(int v=0;degree[u]<limit && v<vertexcount;v++)
				{
					int w = rand.nextInt(1000) + 1;
					Edge temp = new Edge(u, v, w);
					Edge temp1 = new Edge(v, u, w);
		
					if(u!=v && degree[v]<limit && !adj[u].contains(temp) && !adj[v].contains(temp1))
					{						
						adj[u].add(temp);						
						adj[v].add(temp1);
						degree[u]+=1;
						degree[v]+=1;
					}
				}
			}
			//if(degree[i]<1000)
				//System.out.println("Vertex: " + i+" Degree: " + degree[i]);	
		}	

	
	/*	for(int i=0;i<vertexcount;i++)
		{
			System.out.println(degree[i]+" "+adj[i]);
			if(degree[i]==5)
				sum++;
		}
		System.out.println(sum);
	}
	/*
	public void print()
	{
		int vertexcount=5000;
		for(int i=0;i<vertexcount;i++)
		{
			System.out.println(adj[i]);
		}*/
	}
	
	
	public static void main(String[] args)
	{
		final long start = System.nanoTime();
		new graph1(5000,"dense");
		final long duration = System.nanoTime()-start;
		final double total = (double)duration/1000000000;
		System.out.println(total);
	}
}