package assgn;

public class heap{
	Edge[] H;
	int size;
	
	public heap(int size)
	{
		H = new Edge[size*1000];
	}	
	
	public void insert(Edge e)
	{
		H[size]=e;
		size=size+1;
		shiftUp(size-1);
	}
	
	public void delete()
	{
		H[0]=H[size-1];
		size=size-1;
		shiftdown(0);
	}
	
	public void shiftUp(int size)
	{
		int parent = (int)Math.floor((size-1)/2);
		
		if(H[size].weight>H[parent].weight)
		{
			Edge temp = H[size];
			H[size] = H[parent];
			H[parent] = temp;
			shiftUp(parent);
		}	
	}
	
	public Edge max()
	{
		return this.H[0];
	}
	
	public void delete(int u, int v)
	{
		for(int i=0;i<this.size;i++)
		{
			if(this.H[i].getOtherEnd(u)==v || this.H[i].getOtherEnd(v)==u)
			{
				H[i]=H[size-1];
				size-=1;
				shiftdown(i);
				break;
			}
		}
	}
	
	public void shiftdown(int index)
	{
		int childindex=-1;
		
		if(2*index+2>=this.size)
		{
			if(2*index+1<this.size)
			{
				childindex=2*index+1;
			}	
		}
		else
		{
			if(H[2*index+1].weight>H[2*index+2].weight)
				childindex=2*index+1;
	
			else
				childindex=2*index+2;
		}
		
		if(childindex!=-1)
		{
			Edge temp = H[index];
			H[index] = H[childindex];
			H[childindex] = temp;
			shiftdown(childindex);
		}
	
	}

	public boolean isEmpty()
	{
		if(this.size==0)
			return true;
		else
			return false;
	}
	
	public void print()
	{
		//System.out.print("i H[i] D[i] R[i]");
		for(int i=0;i<this.size;i++)
			System.out.print(H[i]+" ");
		System.out.println();
	}
	
	public static void main(String[] args)
	{
		heap H = new heap(500);
		H.insert(new Edge(1,2,8));
		H.print();
		H.insert(new Edge(2,3,9));
		H.print();
		H.insert(new Edge(1,3,11));
		H.print();
		H.insert(new Edge(1,4,7));
		H.print();
		H.delete();
		H.print();
		System.out.println(H.max());
	}
}