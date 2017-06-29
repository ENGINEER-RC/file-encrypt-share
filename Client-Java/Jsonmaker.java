import java.util.Scanner;
import org.json.simple.JSONArray;  
public class Jsonmaker{
	public static String Userjson(){ 
	Scanner sc=new Scanner(System.in);
	int n;
	JSONArray arr = new JSONArray(); 
	String a;
	System.out.print("Enter Number of Users to Share This File With: ");
	n=sc.nextInt();
	for(int i=0;i<n;i++){
		System.out.print("User_No("+i+"): ");
		a=sc.next();
		arr.add(a);
	}
	return arr.toString();
	}
}