package Silver;

//http://www.usaco.org/index.php?page=viewproblem2&cpid=1038
import java.io.*;
import java.util.Arrays;
public class SocialDistancing {
	static Interval[] intervals;
	static int m;
	static long maxVal;
	public static void main(String[] args) throws IOException {
		String path = "/Users/christopherjiang/Desktop/socdist_silver_open20/";
		File file = new File(path + "10.in");
		BufferedReader br = new BufferedReader(new FileReader(file));
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] l1 = br.readLine().split(" ");
		m = Integer.parseInt(l1[0]);
		int n = Integer.parseInt(l1[1]);
		intervals = new Interval[n]; 
		
		for(int i = 0; i < n; i++){
			String[] line = br.readLine().split(" ");
			long start = Long.parseLong(line[0]);
			long end = Long.parseLong(line[1]);
			intervals[i] = new Interval(start, end);
		}
		// sorted base on the start
		Arrays.sort(intervals);
		long ub = intervals[intervals.length-1].getEnd();
		maxVal = binSearch2(1, ub);
		System.out.println(maxVal);
//		File out = new File("socdist.out");
//		BufferedWriter bw = new BufferedWriter(new FileWriter(out)); 
//		bw.write(Long.toString(maxVal));
		br.close();
//		bw.close();
	}
	
	public static boolean check(long d) {
		long cur = intervals[0].getStart();
		int tCows = 0;
		for(int i = 0; i < intervals.length; i++) {
			Interval k  = intervals[i];
			long start = k.getStart();
			long end = k.getEnd();
			if(cur <= end) {
				// if current is smaller than start, update start
				cur = Math.max(cur, start);
				// this applies for both when the current is smaller or in between start and end, finds how many cows fit
				long numOfCows = (end - cur) / d + 1; 
				tCows += numOfCows;
				// updates currrent position to the new place
				cur += (numOfCows) * d;
			}
		}
		return tCows >= m;	
	}
	
	public static void binSearch(long start, long end) {
		while(start <= end) {
			long mid = (start + end)/2;
			if(check(mid)) {
				// only update maxVal for the first one since we are looking for max. 
				maxVal = mid;
				start = mid+1;
			}else {
				end = mid-1;
			}
		}
	}
	
	public static long binSearch2(long start, long end) {
		long result = -1; 
		
		if (start <= end) {
			long k = 0;
			long mid = (start + end) / 2;
			if(check(mid)) {
				// only update value when check is passed. 
				result = mid;
				k = binSearch2(mid+1, end);
			}else {
				k = binSearch2(start, mid-1);
			}
			if(k != -1) {
				// updates the value
				result = k;
			}
		}
		return result;
	}

}
class Interval implements Comparable<Interval>{
	private long start;
	private long end;
	public Interval(long s, long e) {
		start = s;
		end = e;
	}
	public long getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public long getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	@Override
	public int compareTo(Interval o) {
		return Long.compare(this.start, o.getStart());
	}
	
	public String toString() {
		return "start: " + start + " end: " + end;
	}
	
}