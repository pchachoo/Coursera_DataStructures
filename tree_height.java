import java.util.*;
import java.io.*;

public class tree_height {
    class FastScanner {
		StringTokenizer tok = new StringTokenizer("");
		BufferedReader in;

		FastScanner() {
			in = new BufferedReader(new InputStreamReader(System.in));
		}

		String next() throws IOException {
			while (!tok.hasMoreElements())
				tok = new StringTokenizer(in.readLine());
			return tok.nextToken();
		}
		int nextInt() throws IOException {
			return Integer.parseInt(next());
		}
	}

	public class TreeHeight {
		int n;
		int parent[];
		int depth[];
		
		void read() throws IOException {
			FastScanner in = new FastScanner(); //System.out.println("input integer" );
			n = in.nextInt();
			depth =  new int[n];
			parent = new int[n];
			for (int i = 0; i < n; i++) {
				parent[i] = in.nextInt();
			}
		}

		void computedepth(int parent[], int i, int depth[] ){
		//	System.out.println("Processing tree depth" );
			if (depth[i]==0){
				 if (parent[i]==-1)//element is root
				{	depth[i] = 1;
				//	System.out.println("root index" );
				}
				else if (depth[parent[i]] == 0)// && parent[i]!=-1){
				{//	System.out.println("parent depth not calculated for element "+i+" at position parent[i] ="+parent[i] );
		            computedepth(parent, parent[i], depth);
		            depth[i] = depth[parent[i]] + 1;
		        }
				else{
				//	System.out.println("general case for element= "+i );
					depth[i] = depth[parent[i]] + 1;
				}
			}
			else {
			//	System.out.println("depth for element already calculated for element= "+i);
			}
			//	System.out.println(depth[i]);
		}
		
		int computeHeight() {
        // Replace this code with a faster implementation
		//	System.out.println("Processing tree height, n= "+n);
			int maxHeight = 0;
			
		//initialize depth array - java does that by default
		//	for (int j=0;j<n;j++){
		//		depth[j]=0;
		//	}
			
			// fill depth of all nodes
	        for (int i = 0; i < n; i++) {
	            computedepth(parent, i, depth);
	        }
	        int tempmax = 0;
	        for (int i = 0; i < n; i++) {
	        	if (tempmax<depth[i])
	        		tempmax = depth[i];
	        }
	        //replace with maxHeight = Math.max(maxHeight, height); if speed issues
	        maxHeight = tempmax;
			return maxHeight;
		}
	}

	static public void main(String[] args) throws IOException {
            new Thread(null, new Runnable() {
                    public void run() {
                        try {
                            new tree_height().run();
                        } catch (IOException e) {
                        }
                    }
                }, "1", 1 << 26).start();
	}
	public void run() throws IOException {
		TreeHeight tree = new TreeHeight();
		tree.read();
		System.out.println(tree.computeHeight());
	}
}
