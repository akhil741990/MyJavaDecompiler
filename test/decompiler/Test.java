package decompiler;

public class Test {
	private int sum;
	static int a,b;
	public static void main(String args[]){
		int a,b,c;
		a = 1;
		b = 2;
		printSum(a, b);
		
		
	}
	public static void printSum(int a, int b){
		int c ;
		c = a + b;
		System.out.println("Sum ="+c);
	}
}
