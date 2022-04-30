
// USACO 2022 February Contest, Silver- Problem 2. Robot Instructions
// http://www.usaco.org/index.php?page=viewproblem2&cpid=1207

package Silver;

import java.io.*;
import java.util.*;
public class RobotInstructions {
	static Integer n;
	static int[][]instructions;
	static long destX;
	static long destY;
	public static void main(String[] args) throws NumberFormatException, IOException {
		
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			n = Integer.parseInt(br.readLine());
			instructions = new int[n][2];
			long[] ans = new long[n+1];
			String[] line = br.readLine().split(" ");
			destX = Long.parseLong(line[0]);
			destY = Long.parseLong(line[1]);
			
			for(int i = 0; i < n; i++) {
				String[] l = br.readLine().split(" ");
				instructions[i][0] = Integer.parseInt(l[0]);
				instructions[i][1] = Integer.parseInt(l[1]);
			}
			// meet in the middle algorithm, if N/2 can be calculated easily, calculate half and find merging
			ArrayList<Point> fH = allSubset(0,n/2);
			fH.add(new Point(Long.MAX_VALUE, Long.MAX_VALUE, -1));
			ArrayList<Point> sH = allSubset(n/2,n);
			
			long prevX = Long.MAX_VALUE;
			long prevY = Long.MAX_VALUE;
			HashMap<Integer, Integer> prevMap = null;
			
			for(Point p: fH){
				long tX = destX - p.getX();
				long tY = destY - p.getY();
				if(prevX != tX || prevY != tY) {
					prevMap = binSearch(sH, 0, sH.size()-1, tX, tY);
					prevX = tX;
					prevY = tY;
				}
				for(int key: prevMap.keySet()) {
					ans[key + p.getZ()]+= prevMap.get(key);
				}
			}
			for(int i = 1; i < ans.length; i++) {
				System.out.println(ans[i]);
			}
			br.close();

	}
	
	public static HashMap<Integer, Integer> binSearch(ArrayList<Point> src, int start, int end, long x, long y){
		HashMap<Integer, Integer> ans = new HashMap<Integer, Integer>();
		// use hashmap to keep track of duplicates to prevent slow adding. 
		// separate binary search to be more concise. 
		if(start <= end) {
			int mid = (start + end)/2;
			Point p = src.get(mid);
			if(p.getX() == x) {
				if(p.getY() == y) {
					int left = mid - 1;
					while(left >= 0) {
						Point l = src.get(left);
						if((l.getX() == x) && (l.getY() == y)) {
							int key = l.getZ();
							if(ans.containsKey(key)) {
								ans.put(key, ans.get(key) + 1);
							}else {
								ans.put(key, 1);
							}
						}else {
							break;
						}
						left--;
					}
					while(mid < src.size()) {
						Point r = src.get(mid);
						if((r.getX() == x) && (r.getY() == y)) {
							int key = r.getZ();
							if(ans.containsKey(key)) {
								ans.put(key, ans.get(key) + 1);
							}else {
								ans.put(key, 1);
							}
						}else {
							break;
						}
						mid++;
					}
				}else if (p.getY() > y) {
					ans = binSearch(src, start, mid - 1, x, y);
				}else {
					ans = binSearch(src, mid + 1, end, x, y);
				}
			}else if (p.getX() > x) {
				// call smaller half
				ans = binSearch(src, start, mid - 1, x, y); 
			}else {
				// call bigger half
				ans = binSearch(src, mid + 1, end, x, y);
			}
		}
		return ans;
	}
	
	public static ArrayList<Point> allSubset(int start, int end){
		// power set iteration instead of binary string method
		ArrayList<Point> result = new ArrayList<Point>();
		result.add(new Point(0,0,0));
		for(int i = start; i < end; i++) {
			int k = result.size();
			for(int j = 0; j < k; j++) {
				long x = instructions[i][0] + result.get(j).getX();
				long y = instructions[i][1] + result.get(j).getY();
				int z = result.get(j).getZ() + 1;
				result.add(new Point(x,y,z));
			}
		}
		Collections.sort(result);
		return result;
	}
}

class Point implements Comparable<Point>{
	private long x;
	private long y;
	private int z;
	
	public Point(long a, long b, int s) {
		x = a;
		y = b;
		z = s;
	}

	public long getX() {
		return x;
	}

	public void setX(long x) {
		this.x = x;
	}

	public long getY() {
		return y;
	}

	public void setY(long y) {
		this.y = y;
	}

	public int getZ() {
		return z;
	}

	public void setZ(int steps) {
		this.z = steps;
	}

	@Override
	public int compareTo(Point o) {
		int n = Long.compare(x, o.getX());
		if(n == 0) {
			n = Long.compare(y, o.getY());
		}
		return n;
	}
}
