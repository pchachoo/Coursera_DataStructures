import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Stack;

class Bracket {
    Bracket(char type, int position) {
        this.type = type;
        this.position = position;
    }

    boolean Match(char c) {
        if (this.type == '[' && c == ']')
            return true;
        if (this.type == '{' && c == '}')
            return true;
        if (this.type == '(' && c == ')')
            return true;
        return false;
    }

    char type;
    int position;
}

class check_brackets {
    public static void main(String[] args) throws IOException {
    	
    	InputStreamReader input_stream = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input_stream);
        System.out.println("Please enter parentheses string" );
        String text = reader.readLine();
     // System.out.println(text);
        int counter = 0;
        int outofposition = 1;
        Stack<Character> opening_brackets_stack = new Stack<>();
        for (int position = 0; position < text.length(); ++position) {
            char next = text.charAt(position);
            
            if (next == '(' || next == '[' || next == '{') {
                // Process opening bracket, write your code here
            //	System.out.println("Processing opening parenthesis "+next );
            	opening_brackets_stack.push(next); 
            	counter++;
            }

            if (next == ')' || next == ']' || next == '}') {
                // Process closing bracket, write your code here
            //	System.out.println("Processing closing parenthesis "+next );
            	char openparen = opening_brackets_stack.pop();
            	if ((next == ')' && openparen == '(') || (next == '}' && openparen == '{') || (next == ']' && openparen == '[')) {
            		counter--;
            		continue;
            	}
            	else {
            		outofposition = position+1; //to adjust for 0 indexing
            		break;
            	}
            	
            		
            	
            }
        }
        
        // Printing answer, write your code here
        if (counter>0)
        //	System.out.println("counter: "+counter);
        	System.out.println(outofposition);
        else if (counter==0)
        	System.out.println("Success");
    }
}
