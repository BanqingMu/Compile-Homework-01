import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class compiler {
	public static int flag=0;
	public static void main(String[] args) throws IOException{
		FileInputStream fis=new FileInputStream(args[0]);
		BufferedReader br=new BufferedReader(new InputStreamReader(fis));
		String string="";
		String temp="";
		while((temp=br.readLine())!=null)
		{
			string+=temp+' ';
		}
		br.close();
		fis.close();
		String[] sstr=string.split(" |\\n|\\t|\\r");
		int i=-1;
		while(flag==0&&i+1<sstr.length)
		{

			String str=sstr[++i];
			if(str.length()!=0)
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
			}
		}
		System.out.println("******");
		}
	public static String transfer(String str)
	{
		return ((String)str.subSequence(0, 1)).toUpperCase()+str.substring(1).toLowerCase();
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
		{
			flag=1;
			System.out.println("Unknown");
		}
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
				System.out.println("Unknown");
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
				identify_ident(str.substring(i));
				return;
			}
		}
		System.out.println("Int("+sum+")");
	}
}
