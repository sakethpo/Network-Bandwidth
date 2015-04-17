package assgn;

import java.util.ArrayList;
import java.util.Stack;

public class kru {

	ArrayList<Edge> T;
	int[] dad;
	int[] rank;
	
	public kru(graph1 g)
	{
		dad = new int[5000];
		rank = new int[5000];
		T = new ArrayList<Edge>();
	}
	
	public int getCapacity(graph1 g, int source, int destination)
	{
		heap h = new heap(5000);
		
		
		for(int i=0;i<5000;i++)
		{
			dad[i]=-1;
			rank[i]=0;
		}
		
		
		//int q=0;
		for(int i=0;i<5000;i++)
		{
			for(Edge e:g.adj[i])
			{
				h.insert(e);
				/*if(e.weight==1000)
					q+=1;*/
			}
			//System.out.println(g.adj[i]);
		}
		//System.out.println("inseted into heap of size "+h.size);
		//int count=0;
		//System.out.println(h.size);
		while(!h.isEmpty())
		{	
			Edge e = h.max();
			h.delete();
			int u = e.vertex1;
			int v = e.vertex2;
			
			int r1 = this.find(u);
			int r2 = this.find(v);
			
			if(r1!=r2)
			{
				T.add(e);
				this.union(r1,r2);
			}
		}
		int k=destination;
		int child;
		int capacity=-1;
		while(k!=-1 && dad[k]!=source)
		{
			child=k;
			k=dad[k];
			if(k!=-1)	
			for(Edge temp: g.adj[k])
			{

				if(temp.getOtherEnd(k)==child)
				{
					if(temp.weight<capacity)
					{
						capacity = temp.weight;
					}
				}
			}
		}
		//System.out.println(T.size());
		/*for(Edge e:T)
		{	System.out.print(++i);
			System.out.println(e);
		}
		System.out.println(count+" "+q);
		*/
		return capacity;
		
	}
	
	int find(int i)
	{
		int h=i;
		int w=0;
		Stack<Integer> H = new Stack<Integer>();
		
		while(dad[h]!=-1)
		{
			H.push(h);
			h=dad[h];
		}
		
		while(!H.isEmpty())
		{
			w = H.pop();
			dad[w] = h;
		}
		return h;
	}
	
	void union(int r1, int r2)
	{
		if(rank[r1]>rank[r2])
			dad[r2]=r1;
		else if(rank[r1]<rank[r2])
			dad[r1]=r2;
		else if(rank[r1]==rank[r2])
		{
			dad[r2]=r1;
			rank[r1]+=1;
		}
		
	}
	
	public static void main(String[] args)
	{
		graph1 g = new graph1(5000,"dense");
		System.out.println("graph done");
		final long start = System.nanoTime();
		kru k =new kru(g);
		k.getCapacity(g, 0, 2525);
		final long duration = System.nanoTime()-start;
		final double total = (double)duration/1000000000;
		System.out.println(total);
	}
}
