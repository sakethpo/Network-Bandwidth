package assgn;

import java.util.Random;

public class testDense {
	public static void main(String[] args)
	{
		//double a=0,b=0,c=0,d=0;
	
		Random rand = new Random();
		int source = rand.nextInt(5000);
		int destination = rand.nextInt(5000);
		
		final long start0 = System.nanoTime();
		graph1 g = new graph1(5000,"dense");
		final long duration0 = System.nanoTime()-start0;
		final double total0 = (double)duration0/1000000000;
		
		final long start1 = System.nanoTime();
		max1 m1 = new max1(g);
		m1.getCapacity(g, source, destination);
		final long duration1 = System.nanoTime()-start1;
		final double total1 = (double)duration1/1000000000;
		
		final long start2 = System.nanoTime();
		max2 m2 = new max2(g);
		m2.getCapacity(g, source, destination);
		final long duration2 = System.nanoTime()-start2;
		final double total2 = (double)duration2/1000000000;
		
		final long start3 = System.nanoTime();
		kru k = new kru(g);
		k.getCapacity(g, source, destination);
		final long duration3 = System.nanoTime()-start3;
		final double total3 = (double)duration3/1000000000;
		
		System.out.println("graph generation: "+total0);
		System.out.println("without heap: "+total1);
		System.out.println("with heap: "+total2);
		System.out.println("kruskal: "+total3);
		System.out.println();
		System.out.println();
		//a+=total0;
		//b+=total1;
		//c+=total2;
		//d+=total3;
		//System.out.println((2.306543636+1.599918067+1.127234031+0.377329178+0.418668746+0.624687084+0.527326228+0.560241113+0.527282609)/9);
		
	//System.out.println(a/9);
	//System.out.println(b/9);
	//System.out.println(c/9);
	//System.out.println(d/9);
	}
}
