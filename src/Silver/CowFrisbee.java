package Silver;

//http://www.usaco.org/index.php?page=viewproblem2&cpid=1183
import java.io.*;
import java.util.*;

public class CowFrisbee {
	static int n;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		String[] arr = br.readLine().split(" ");
		Integer[] height = new Integer[n];
		for(int i = 0; i < n; i++) {
			height[i] = Integer.parseInt(arr[i]);
		}
		long results = 0;
		// reverse to cover next smallest as reversing the list ensure the 1st largest on the left, or 1st smallest on the right.
		results += getDist(height);
		Collections.reverse(Arrays.asList(height));
		results += getDist(height);
		System.out.println(results);
		
	}
	
	public static long getDist(Integer[] height) {
		// monotonic stack implementation,, elements of stack are indices
		// monotonic stack finds first largest on the right, so the stack is decreasing. 
		// if need to find first smallest on the right, the stack is increasing. 
		Stack<Integer> st = new Stack<Integer>();
		long result = 0;
		for(int i = 0; i < n; i++) {
			while(!st.isEmpty() && height[st.peek()] < height[i]) {
				// keep poping when smaller. 
				result += i - st.pop() + 1;
			}
			st.push(i);
		}
		return result;
	}
	
	
}
