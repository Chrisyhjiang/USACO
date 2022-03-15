package Brown;

// http://www.usaco.org/index.php?page=viewproblem2&cpid=1011
import java.io.*;
import java.util.Arrays;
public class Triangle {
	
	public static void main(String[] args) throws NumberFormatException, IOException {

		File file = new File("triangles.in");
		
		BufferedReader br = new BufferedReader(new FileReader(file));
		int n = Integer.parseInt(br.readLine());
		Point[] xPoints = new Point[n+1];
		Point[] yPoints = new Point[n+1];
		
		for(int i = 0; i < n; i++) {
			String[] line = br.readLine().split(" ");
			int x = Integer.parseInt(line[0]);
			int y = Integer.parseInt(line[1]);
			
			xPoints[i] = new Point(x,y);
			yPoints[i] = new Point(y,x);
		}
		// do not use max integer will cause overflow
		xPoints[n] = new Point(10002,-1);
		yPoints[n] = new Point(10002,-1);	
		Arrays.sort(xPoints);
		Arrays.sort(yPoints);
		int mw = 0;
		int mh = 0;
		int ma = Integer.MIN_VALUE;
		for (int i = 0; i < xPoints.length-1; i++) {
			Point p = xPoints[i];
			// get maximum height and maximum width logic is the same. 
			mh = getMax(p.getX(), p.getY(), xPoints);		
			mw = getMax(p.getY(), p.getX(), yPoints);
			ma = Math.max(ma, Math.abs(mh*mw));
		}
		
		File out = new File("triangles.out");
		BufferedWriter bw = new BufferedWriter(new FileWriter(out)); 
		bw.write(Integer.toString(ma));
		br.close();
		bw.close();
	}
	
	public static int getMax(int x, int y, Point[] points) {
		// find the starting point
		int left = -1;
		for(int i = 0; i < points.length; i++) {
			Point temp = points[i];
			if(temp.getX() == x) {
				left = i;
				break;
			}
		}
		// find the middle point of interest, both x and y have to be the same. 
		int mid = left;
		for(int i = left; i < points.length; i++) {
			Point temp = points[i];
			if ((temp.getX() == x) && (temp.getY() == y)) {
				mid = i;
				break;
			}
		}
		// find the largest point
		int end = mid;
		for (int i = mid; i < points.length; i++) {
			Point temp = points[i];
			if(temp.getX() > x) {
				end = i-1;
				break;
			}
		}
		// the maximum difference of leftmax and right max is the maximum side of the triangle.
		int leftMax = points[mid].getY() - points[left].getY(); 
		int rightMax = points[end].getY() - points[mid].getY();
		return Math.max(rightMax,leftMax);
		
	}
	

}

// specify point for comparable and use toString method to display data
class Point implements Comparable<Point>{
	private int x;
	private int y;
	public Point(int a, int b) {
		x = a;
		y = b;
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
	@Override
	public int compareTo(Point point) {
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
