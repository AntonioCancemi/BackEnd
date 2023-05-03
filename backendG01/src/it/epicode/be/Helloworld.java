package it.epicode.be;


import java.util.Scanner;

public class Helloworld {

	public static void main (String[] args) {
	
		System.out.println("This is my first exercise with JAVA!");
		//ES 1-2)
		//Moltiplicazione fra interi e concatenazione di stringa
		System.out.println("______________________");
		int num1=5;
		int num2=4;
		int res=num1*num2;
		System.out.println(num1+" per "+num2+" uguale "+ res);
		
		//ES 3)
		System.out.println("______________________");
		String arr[] = {"primo","secondo","terzo","quarto","quinto"};
		String string = "newString";
		arr[2] = string;
		
		String newArr[]= {arr[0],arr[1],string,arr[3],arr[0],arr[1]};
		
		System.out.println("Arr di 5:");
		for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
		
		System.out.println("-------------");
		System.out.println("stringa:");
		System.out.println(string);
		
		System.out.println("-------------");
		System.out.println("nuovo arrey modificato:");
		for (int i = 0; i < newArr.length; i++) {
			System.out.println(newArr[i]);
		}
		
		System.out.println("______________________");
		
		//ES 4)
		Scanner sc1= new Scanner(System.in);
		
		System.out.print("inserisci una parola: ");
		String str1=sc1.nextLine();
		
		Scanner sc2= new Scanner(System.in);
		
		System.out.print("inserisci un'altra parola: ");
		String str2=sc2.nextLine();
		
		String oArr[]= {str1,str2};
		
		System.out.print("");
		
		for (int i = 0; i < oArr.length; i++) {
			System.out.print(oArr[i]);
			System.out.print(" ");
		}
		
		System.out.print("->reverse= ");
		for (int i = oArr.length-1; i >= 0; i--) {
			System.out.print(oArr[i]);
			System.out.print(" ");
		}
		
		System.out.println("______________________");
		
		Scanner sc3= new Scanner(System.in);
		System.out.print("base : ");
		float base=sc3.nextFloat();
		Scanner sc4= new Scanner(System.in);
		float altezza=sc4.nextFloat();
		System.out.print("altezza : ");
		float area =base*altezza;
		System.out.println("area del reattangolo = "+area);
		
		
		}
}



