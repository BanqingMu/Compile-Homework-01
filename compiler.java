import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class compiler {
	public static String[] reserved= {"BEGIN","END","FOR","IF","THEN", "ELSE",":","+","*",",","(",")",":="};
	public static String[] reserved_to= {"Begin","End","For","If","Then", "Else","Colon","Plus","Star","Comma","LParenthesis","RParenthesis","Assign"};
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
		char[] ch=string.toCharArray();
		int len=ch.length;
		int i=0;
		while(i<len)
		{
			if(ch[i]!=' '&&ch[i]!='\n'&&ch[i]!='\t'&&ch[i]!='\r')
			{
				for(int j=0;j<reserved.length;j++)
				{
					if(i+reserved[j].length()<len && reserved[j].equals(string.subSequence(i, i+reserved[j].length())))
					{
						System.out.println(reserved_to[j]);
						i+=reserved[j].length();
						break;
					}
				}
				if(Character.isDigit(ch[i]))
				{
					int sum=ch[i]-'0';
					while(i<len&&Character.isDigit(ch[++i]))
					{
						sum=sum*10+ch[i]-'0';
					}
					System.out.println("Int("+sum+")");
					continue;
				}else if(Character.isLetter(ch[i]))
				{
					temp=""+ch[i++];
					while(i<len&&Character.isLetter(ch[i])||Character.isDigit(ch[i]))
					{
						temp+=ch[i];
						i++;
					}
					for(int j=0;j<reserved.length;j++)
					{
						if(reserved[j].equals(temp))
						{
							temp=reserved_to[j];
						}
					}
					System.out.println("Ident("+temp+")");
					continue;
				}else
				{
					System.out.println("Unknown");
					break;
				}
			}
			i++;
		}
		}
}
