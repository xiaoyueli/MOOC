package lab5;

public class Y extends X implements Test{
	
	@Override
	public void print(String name) {
		// TODO Auto-generated method stub
		super.print("X");
		System.out.println("Hi,Y!");
	}

	public static void main(String[] args){
//		X[] xa = new X[3];
//		Y[] ya = new Y[3];
//		xa = ya;
//		ya = (Y[])xa;
//		ya[0] = new Y();
//		ya[0].print("ni");
//		System.out.println(NUM);
//		System.out.println(X.NUM);
		
		Y test = new Y();
		((X)test).print("what ever!");
		X test2 = new X();
		((Y)test2).print("Hi, there!"); //error!
	}



}
