package captherSeven;

import java.util.Scanner;

public class Fraction {
	private int numer, deno;
	
	Fraction(int a, int b)
	{
		numer = a;
		deno = b;
	}

	double toDouble()
	{
		double result = (double)numer/deno;
		return result;
	}
	
	Fraction plus(Fraction r)
	{
		Fraction copy = new Fraction(r.numer, r.deno);
		copy.numer = copy.deno*this.numer + this.deno*copy.numer;
		copy.deno = copy.deno*this.deno;
		return copy;
	}
	
	Fraction multiply(Fraction r)
	{
		Fraction copy = new Fraction(r.numer, r.deno);
		copy.numer = copy.numer*this.numer;
		copy.deno = copy.deno*this.deno;
		return copy;
	}
	
	void print()
	{
		if(numer == deno)
		{
			System.out.println("1");
		}
		else if(numer % deno == 0)
		{
			System.out.println(numer/deno);
		}
		else
		{
			int a = numer;
			int b = deno;
			do{
				int mid = a % b;
				a = b;
				b = mid;
				
			}while(a % b != 0);
			System.out.println(numer/b+"/"+deno/b);	
		}
		
			
		
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		Fraction a = new Fraction(in.nextInt(), in.nextInt());
		Fraction b = new Fraction(in.nextInt(),in.nextInt());
		a.print();
		b.print();
		a.plus(b).print();
		a.multiply(b).plus(new Fraction(5,6)).print();
		a.print();
		b.print();
		in.close();
	}

}

