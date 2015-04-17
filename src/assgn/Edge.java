package assgn;

public class Edge {

	int vertex1;
	int vertex2;
	int weight;
	

	public Edge(int u, int v, int weight)
	{
		this.vertex1 = u;
		this.vertex2 = v;
		this.weight = weight;
	}


	@Override
	public String toString() {
		return "Edge [" + vertex1 + ", " + vertex2
				+ ", " + weight + "]";
	}
	
	public int getOtherEnd(int e)
	{
		if(this.vertex1 == e)
			return vertex2;
		
		else
			return vertex1;
	}
	
}
