package assgn;

import java.util.ArrayList;
import java.util.Random;

public class max1 {
	
	int[] v;
	int[] dad;
	int[] d;
	int[] status;
	
	public max1(graph1 g)
	{
		v = new int[g.vertexcount];
		dad = new int[g.vertexcount];
		d = new int[g.vertexcount];
		status = new int[g.vertexcount];
	}	
	
	int getCapacity(graph1 g, int source, int destination)
	{
		int vertexcount=g.vertexcount;
		g.checkConnected();
		ArrayList<Edge> fringe = new ArrayList<Edge> ();
		for(int i=0;i<vertexcount;i++)
		{
			status[i]=0;
			d[i]=10000;
			dad[i]=-1;
		}
		status[source]=2;
		ArrayList<Edge> adj = g.adj[source];
		for(Edge i:adj)
		{
			fringe.add(i);
			status[i.vertex2]=1;
			dad[i.vertex2]=source;
			d[i.vertex2]=i.weight;
		}
		Edge temp;
		
		while(!fringe.isEmpty())
		{
			temp = maximum(fringe);
			fringe.remove(temp);
			
			int u;
			if(status[temp.vertex2]==2)
				u = temp.vertex1;
			else
				u = temp.vertex2;
			
			status[u]=2;
			
			adj = g.adj[u];
			
			for(Edge e:adj)
			{
				int v = e.getOtherEnd(u);
				if(status[v]==0)
				{
					d[v]=min(d[u],e.weight);
					status[v]=1;
					dad[v]=u;
					fringe.add(e);
				}
				else if(status[v]==1 && d[v]<min(d[u],e.weight))
				{
					d[v]=min(d[u],e.weight);
					for(Edge i:fringe)
					{
						if(i.vertex1==v && i.vertex2==dad[v] || i.vertex2==v && i.vertex1==dad[v])
						{
							fringe.remove(i);
							break;
							
						}
						
					}
					dad[v]=u;
					fringe.add(e);
					
				}
			}
			//System.out.println(++count+" fringes: "+fringe.size());
		}
		//System.out.println("iteration = "+(count));
		return d[destination];
	}
	
	public int min(int a, int b)
	{
		if(a>=b)
			return b;
		else 
			return a;
	}
	
	public Edge maximum(ArrayList<Edge> L)
	{
		Edge max = L.get(0);
		for(Edge i:L)
		{
			if(i.weight>max.weight)
				max=i;
		}
		return max;
	}
	
	
	public static void main(String[] args)
	{
		Random rand = new Random();
		
		graph1 g = new graph1(5000,"dense");
		final long start = System.nanoTime();
		max1 m = new max1(g);
		int source = rand.nextInt(5000);
		int destination = rand.nextInt(5000);
		int capacity = m.getCapacity(g, source, destination);
		System.out.println("capacity = "+capacity);
		final long duration = System.nanoTime()-start;
		final double total = (double)duration/1000000000;
		System.out.println(total);
	}
}
