package Silver;

//http://www.usaco.org/index.php?page=viewproblem2&cpid=1015
import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
public class SilverTriangles {
	public static void main(String[] args) throws NumberFormatException, IOException {
//		String fName = "/Users/christopherjiang/Desktop/triangles_silver_feb20/7.in";
		File file = new File("triangles.in");
//		File file = new File(fName);
		BufferedReader br = new BufferedReader(new FileReader(file));
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		int n = Integer.parseInt(br.readLine());
		Point2[] xPoints = new Point2[n+1];
		Point2[] yPoints = new Point2[n+1];
		int[] sumX = new int[100000];
		//int[] sumY = new int[100000];
		HashMap<String, Integer> hm; 
		for(int i = 0; i < n; i++) {
			String[] line = br.readLine().split(" ");
			int x = Integer.parseInt(line[0]);
			int y = Integer.parseInt(line[1]);
			xPoints[i] = new Point2(x,y);
			yPoints[i] = new Point2(y,x);
		}
		// padding so the last test case is considered sort after padding is inserted. 
		xPoints[n] = new Point2(10003,-1);
		yPoints[n] = new Point2(10003,-1);
		Arrays.sort(xPoints);
		Arrays.sort(yPoints);
		sumX = buildSumX(xPoints);
		hm = buildSumY(yPoints);
		long area = 0;
		for(int i = 0; i < n; i++) {
			int height = sumX[i];			
			if(height != 0) {
				// ony call after the height is not 0 to save time. 
				int x = xPoints[i].getX();
				int y = xPoints[i].getY();
				String s = Integer.toString(y) + Integer.toString(x);
				int width = hm.get(s);
				area += (long) height * (long) width;
			}
			
		}
		long result = area % (long)(1e9+7);
//		System.out.println(result);
		
		File out = new File("triangles.out");
		BufferedWriter bw = new BufferedWriter(new FileWriter(out)); 
		bw.write(Long.toString(result));
		br.close();
		bw.close();
		
	}
	
	public static int[] buildSumX(Point2[] points) {
		int[]sum = new int[100000];
		int start = 0;
		for(int i = 0; i < points.length-1; i++) {
			Point2 p = points[i];
			Point2 q = points[i+1];
			if(p.getX() != q.getX()) {
				int yStart = points[start].getY();
				int s = 0;
				for(int j = i; j >=start; j--) {
					// the first one is all the y distances. 
					s += points[j].getY()-yStart;
				}
				sum[start] = s;
				// can be proven using a mathematic series. 
				int k = (i - start + 1);
				for(int j = start + 1; j <= i; j++) {
					int diff = points[j].getY() - points[j-1].getY();
					// j - start since j is always movin so the position needs to be shifted. 
					s += diff * (2 * (j - start) - k);
					sum[j] = s;
				}
				start = i+1;
			}
		}
		return sum;
	}
	// building y needs a hashmap so the order is kept. the key is a string of x and y but otherwise largely the same logic. 
	public static HashMap<String,Integer> buildSumY(Point2[] points) {
		HashMap<String, Integer> hm = new HashMap<String, Integer>();
		int start = 0;
		
		for(int i = 0; i < points.length-1; i++) {
			Point2 p = points[i];
			Point2 q = points[i+1];
			if(p.getX() != q.getX()) {
				int yStart = points[start].getY();
				int s = 0;
				for(int j = i; j >=start; j--) {
					s += points[j].getY()-yStart;
				}
				
				String key = Integer.toString(points[start].getX()) + Integer.toString(points[start].getY());
				hm.put(key, s);
				
				int k = (i - start + 1);
				for(int j = start + 1; j <= i; j++) {
					int diff = points[j].getY() - points[j-1].getY();
					s += diff * (2 * (j - start) - k);
					key = Integer.toString(points[j].getX()) + Integer.toString(points[j].getY());
					hm.put(key,s);
				}
				start = i+1;
			}
		}
		return hm;
	}
	
	

}
class Point2 implements Comparable<Point2>{
	private int x;
	private int y;
	private int value;
	public Point2(int a, int b) {
		x = a;
		y = b;
	}
	public Point2(int a, int b, int v) {
		x = a;
		y = b;
		value = v;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getValue() {
		return value;
	}
	public void setValue(int value) {
		this.value = value;
	}
	@Override
	public int compareTo(Point2 point) {
		// TODO Auto-generated method stub
		
		if (x == point.getX()) {
			return y - point.getY();
		}else {
			return x - point.getX();
		} 
	}
	
	public String toString() {
		return "x = " + x + " y = " + y;
		
	}

	
 
	
}