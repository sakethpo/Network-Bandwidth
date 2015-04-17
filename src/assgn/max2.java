package assgn;

import java.util.ArrayList;

public class max2 {
	
	int[] v;
	int[] dad;
	int[] d;
	int[] status;
	
	public max2(graph1 g)
	{
		v = new int[g.vertexcount];
		dad = new int[g.vertexcount];
		d = new int[g.vertexcount];
		status = new int[g.vertexcount];
	}
	
	int getCapacity(graph1 g, int source, int destination)
	{
		int vertexcount = g.vertexcount;
		heap h = new heap(vertexcount);
		g.checkConnected();
		for(int i=0;i<vertexcount;i++)
		{
			status[i]=0;
			d[i]=10000;
			dad[i]=-1;
		}
		status[source]=2;
		ArrayList<Edge> adj = g.adj[source];
		for(Edge e:adj)
		{
			//v[i.vertex1]
			status[e.getOtherEnd(source)]=1;
			dad[e.getOtherEnd(source)]=source;
			d[e.getOtherEnd(source)]=e.weight;
			h.insert(e);
		}
		Edge temp;
		while(!h.isEmpty())
		{
			temp = h.max();
			h.delete();
			
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
					h.insert(e);
				}
				else if(status[v]==1 && d[v]<min(d[u],e.weight))
				{
					h.delete(dad[v],v);
					d[v]=min(d[u],e.weight);
					dad[v]=u;
					h.insert(e);		
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
		
}
