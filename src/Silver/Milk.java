package Silver;

// http://www.usaco.org/index.php?page=viewproblem2&cpid=763
import java.io.*;
import java.util.*;
public class Milk {
	static ArrayList<String> maxId;
	static HashMap<String,Integer> cows;
	public static void main(String[] args) throws IOException {
//		String path = "/Users/christopherjiang/Desktop/measurement_silver_dec17/";
		File file = new File("measurement.in");
		BufferedReader br = new BufferedReader(new FileReader(file));
//		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String[] line = br.readLine().split(" ");
		int n = Integer.parseInt(line[0]);
		int g = Integer.parseInt(line[1]);
		
		Entry[] logs = new Entry[n];
		int change = 0;
		int max = g;
		// do not need a separat class where hashmap can be used. 
		cows = new HashMap<String,Integer>(); 
		maxId = new ArrayList<String>();
		
		for(int i = 0; i < n; i++) {
			String[] l = br.readLine().split(" ");
			int day = Integer.parseInt(l[0]);
			String id = l[1];
			int u = Integer.parseInt(l[2]);
			logs[i] = new Entry(day, id, u);
			cows.put(id, g);
			// at the start everyone is at maximum ID
			maxId.add(id);
		}
		
		Arrays.sort(logs);
		for(int i = 0; i < logs.length; i++) {
			Entry e = logs[i];
			String cid = e.getId();
			int current = cows.get(cid) + e.getUpdate();
			cows.put(cid, current);
			boolean hasId = maxId.contains(cid);
			
			if (current > max) {
				// if there is the id 
				if(hasId) {
					if (maxId.size() > 1) {
						// reset maxid to 1 element
						// add the change
						maxId.clear();
						maxId.add(cid);
						change++;
					}
				}else {
					// reset maxId to 1 element
					// add 1 to change
					maxId.clear();
					maxId.add(cid);
					change++;
				}
				max = current;
			}else if (current == max) {
				// does not belong in maxId has to add the new id into maxid an dadd chage for 1 update
				if (!hasId) {
					change++;
					maxId.add(cid);
				}
			}else { // current < max
				// search for next 
				if (hasId) {
					if(maxId.size() == 1) {
						// find next biggest
						updateMaxList();
						// since lsit always has length of at least 1, access first value to get minimum
						max = cows.get(maxId.get(0));
						if(maxId.size() > 1) {
							// new cows are needed
							change++;
						}else {
							// there can be 1 and only 1 different id
							if(!maxId.get(0).contentEquals(cid)) {
								// different id needs a change
								change++;
							}
						}
						
					}else {
						// if size greater than 1, a new cow has been added so a new change is in order
						change++;
					}
				}
			}
		}
		
		File out = new File("measurement.out");
		BufferedWriter bw = new BufferedWriter(new FileWriter(out)); 
		bw.write(Integer.toString(change));
		br.close();
		bw.close();
//		System.out.println(change);
		
	}
	
	
	public static void updateMaxList(){
		// update maxID and process outside of function
		maxId.clear();
		Set<String> keys = cows.keySet();
		int max = -1;
		// loop through hashmap with key set
		for(String s: keys) {
			int cur = cows.get(s);
			if (cur > max) {
				maxId.clear();
				maxId.add(s);
				max = cur;
			}else if (cur == max) {
				maxId.add(s);
			}
		}
	}

}

class Entry implements Comparable<Entry>{
	private int day;
	private String id;
	private int update;
	public Entry(int d, String i, int u) {
		day = d;
		id = i;
		update = u;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public int getUpdate() {
		return update;
	}
	public void setUpdate(int update) {
		this.update = update;
	}
	@Override
	public int compareTo(Entry o) {
		return day - o.getDay();
	}
	
}


