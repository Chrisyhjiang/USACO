package Brown;

// http://www.usaco.org/index.php?page=viewproblem2&cpid=1108
import java.io.*;
public class ComfortableCows {
	// field keeps track of all the states 'c', 'u', empty
	// c is comfortable, u is uncomfortable, defualt to empyt
	static char[][] field = null;
	static int count = 0;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		// padding by 1 
		field = new char[1001][1001];
		
		for (int i = 0; i < n; i++) {
			String[] line = br.readLine().split(" ");
			int x = Integer.parseInt(line[0]);
			int y = Integer.parseInt(line[1]);
			// all cows start of ass uncomfortable 
			field[x][y] = 'u';
			
			if (isComfortable(x,y)) {
				field[x][y] = 'c';
				count++;
			}
			count += check(x,y);
			System.out.println(count);
		}
			
	}
	public static boolean isComfortable(int x, int y) {
		int count = 0;
		if (x>0 && ((field[x-1][y]=='u')||(field[x-1][y]=='c'))) {
			count++;
		}
		if ((field[x+1][y]=='u')||(field[x+1][y]=='c')) {
			count++;
		}
		if (y > 0 && ((field[x][y-1]=='u')||(field[x][y-1]=='c'))) {
			count++;
		}
		if ((field[x][y+1]=='u')||(field[x][y+1]=='c')) {
			count++;
		}
		return count == 3;
	}
	
	public static int check2 (int x, int y) {
		// if surroundin is uncomfortable check comfortable then add 1
		// if surrounding is comfortable it becomes uncomfortable
		char up = field[x][y];
		int result = 0;
		if(up == 'c') {
			field[x][y] = 'u';
			result --;
		}else if (up == 'u'){
			if(isComfortable(x,y)) {
				field[x][y] = 'c';
				result ++;
			}
		}
		return result;
	}
	
	public static int check(int x, int y) {
		int result = 0;
		// 0 out of bounds
		if (x>0) {
			result += check2(x-1,y);
		}
		// 0 out of bounds
		if (y>0) {
			result += check2(x,y-1);
		}
		result += check2(x+1,y);
		result += check2(x,y+1);
		return result;
	}
}	
