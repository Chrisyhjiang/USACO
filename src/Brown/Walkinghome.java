package Brown;

// http://usaco.org/index.php?page=viewproblem2&cpid=1157
import java.io.*;
public class Walkinghome {
	
	static boolean[][] field = null;
	static int totalTurns = 0;
	static int size = 0;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		
		for (int i = 0; i < n; i++) {
			String[] line = br.readLine().split(" ");
			size = Integer.parseInt(line[0]);
			totalTurns = Integer.parseInt(line[1]);
			field = new boolean[size][size];
			// data processing
			for (int j = 0; j < size; j++) {
				char[] line2 = br.readLine().toCharArray();
				for (int k = 0; k < size; k++) {
					char loc = line2[k];
					if (loc == '.') {
						field[j][k] = true;
					}else {
						field[j][k] = false;
					}
				}
			}
			
			int right = 0; 
			int down = 0;
			// corner cases checking if the beginning 2 cells are false. 
			if (field[0][1]) {
				right = traverse(0, 1, true, 0);
			}
			if (field[1][0]) {		
				down =  traverse(1, 0, false, 0); 
			}
			System.out.println(right+down);
		}
		
	}
	
	public static int traverse(int row, int col, boolean isRight, int turns) {
		if (row == size-1 && col == size-1) {
			return 1;
		}else {
			// recursive calls taking left steps and right steps sum to be answer
			int stepsFromRight = traverseRight(row, col, isRight, turns);		
			int stepsFromDown = traverseDown(row, col, isRight, turns);
			
			return stepsFromRight + stepsFromDown;
		}	
	}
	
	public static int traverseRight(int row, int col, boolean isRight, int turns) {
		int stepsFromRight = 0;
		// checking column to prevent index out of bounds. 
		if (col < size - 1 && field[row][col+1]) {
			if (isRight) {
				stepsFromRight = traverse(row, col+1, isRight, turns);
			}else {
				// switches direction from right to down and making 1 more turn
				if (turns < totalTurns) {
					stepsFromRight = traverse(row, col+1, !isRight, turns+1);
				}	
			}
		}
		return stepsFromRight;
	}
	
	public static int traverseDown(int row, int col, boolean isRight, int turns) {
		int stepsFromDown = 0;
		// checking index out of bounds for row before accessing
		if (row < size - 1 && field[row+1][col]) {
			if (isRight) {
				if (turns < totalTurns) {
					// switches direction from down to right and making 1 more turn
					stepsFromDown = traverse(row+1, col, !isRight, turns+1);
				}
			}else {
				stepsFromDown = traverse(row+1, col, isRight, turns);
			}
		}
		return stepsFromDown;
	}

}
