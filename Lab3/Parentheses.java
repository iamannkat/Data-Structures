
import java.io.*;

public class Parentheses {

    private static void PrintOutput(Queue<Integer> Q) {
        while(!Q.isEmpty()){
			Integer ch1 = Q.get();
			Integer ch2 = Q.get();
            System.out.print(ch1+ "-"+ ch2+",");
        }
        System.out.println();
    }

    private static void ParseInput(String str) {
    	int N = str.length(); // number of characters in str

        Stack<Pair> S = new Stack<Pair>();
        Queue<Integer> Q = new Queue<Integer>(N);

        for (int i = 0; i < N; i++) {
			char c = str.charAt(i); // character at position i of str
			
			if (c =='{' || c == '(' || c == '['){
				S.push(new Pair(c,i));
				continue; //jump to the next	
			}
			
			else if((c == ')' || c == ']' || c == '}' )&& (!S.isEmpty())) // check if par is right
			{
				Pair pair = S.pop(); 
				char d = pair.getC();
			
				//check if par is of same type
				if( (d == '(' && c ==')')|| (d =='[' && c == ']')||(d == '{' && c == '}'))
				{
					Q.put(pair.getP());
					Q.put(i);	
				}
				else
				{
					System.out.println("Syntax Error");
					return;
				}
				
			}
			
			else
			{
				System.out.println("Syntax Error");
			}

        }
        if(S.isEmpty())
        {
        	PrintOutput(Q);
        }
        else{
        	System.out.println("Syntax Error");
        }
    }

    public static void main(String[] args) {
        System.out.print("Enter input string > ");
        In.init();
        String str = In.getString();   // read next character
        System.out.println("Input string = " + str + " , length = " + str.length());

        ParseInput(str);
    }
}
