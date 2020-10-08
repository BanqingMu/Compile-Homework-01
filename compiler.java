import java.util.Scanner;

public class compiler {
	public static int flag=0;
	public static void main(String[] args){
		Scanner sc=new Scanner(args[0]);
		String str=sc.next();
		while(flag==0)
		{
			switch(str)
			{
			case "BEGIN":
			case "END":
			case "FOR":
			case "IF":
			case "THEN":
			case "ELSE":
				System.out.println(transfer(str));
				break;
			case ":":
				System.out.println("Colon");
				break;
			case "+":
				System.out.println("Plus");
				break;
			case "*":
				System.out.println("Star");
				break;
			case ",":
				System.out.println("Comma");
				break;
			case "(":
				System.out.println("LParenthesis");
				break;
			case ")":
				System.out.println("RParenthesis");
				break;
			case ":=":
				System.out.println("Assign");
				break;
			default:
				identify(str);
			}
			str=sc.next();
		}
		}
	public static String transfer(String str)
	{
		return str.subSequence(0, 1)+str.substring(1).toLowerCase();
	}
	public static void identify(String str)
	{
		char c=str.charAt(0);
		if(Character.isLetter(c))
		{
			identify_ident(str);
		}else if(Character.isDigit(c))
		{
			identify_int(str);
		}else
			flag=1;
	}
	public static void identify_ident(String str)
	{
		char[]  c = str.toCharArray();
		for(char h:c)
		{
			if(Character.isLetter(h)||Character.isDigit(h))
			{
				continue;
			}else
			{
				flag=1;
				return;
			}
		}
		System.out.println("Ident("+str+")");
	}
	public static void identify_int(String str)
	{
		char[]  c = str.toCharArray();
		int sum=0;
		for(int i=0;i<str.length();i++)
		{
			if(Character.isDigit(c[i]))
			{
				sum=sum*10+c[i]-'0';
				continue;
			}else
			{
				System.out.println("Int("+sum+")");
				identify(str.substring(i));
				return;
			}
		}
		System.out.println("Int("+sum+")");
	}
}
