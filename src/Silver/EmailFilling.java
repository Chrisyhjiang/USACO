package Silver;

// https://github.com/Chrisyhjiang/USACO

import java.io.*;
import java.util.*;

public class EmailFilling {
	
	static int m;
	static int n;
	static int k;
	static ArrayList<Integer> folders;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		String path = "/Users/wenbojiang/Downloads/prob3_silver_feb22/";
		for (int a = 5; a <= 5; a++) {
			String fn = path + a + ".in";
			File file = new File(fn);
			BufferedReader br = new BufferedReader(new FileReader(file));
			
			String ofn = path + a + ".out";
			File out = new File(ofn);
			BufferedReader bw = new BufferedReader(new FileReader(out));
			
			//BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			int s = Integer.parseInt(br.readLine());
			boolean succeed = true;
			for(int i = 0; i < s; i++) {
				String[] line = br.readLine().split(" ");
				m = Integer.parseInt(line[0]);
				n = Integer.parseInt(line[1]);
				k = Integer.parseInt(line[2]);
				folders = new ArrayList<Integer>();
				String[] data = br.readLine().split(" ");
				for(int j = 0; j < n; j++) {
					folders.add(Integer.parseInt(data[j]));
				}
				String result = canFill() ? "YES" : "NO";
				String target = bw.readLine();
				if(!result.contentEquals(target)) {
					System.out.println("failed on sub test cases: " + (i+1));
					succeed = false;
				}
				//System.out.println(result);
			}
			String p = succeed ? "succeed...  " : "failed....";
			System.out.println(String.format("test case %d %s", a, p));
			
			 
			//bw.write(Integer.toString(result));
			br.close();
			bw.close();
//			File out = new File(fn + ".out");
//			BufferedReader or = new BufferedReader(new FileReader(out));
//			
//			int ans = Integer.parseInt(or.readLine());
//			String passed = "passed";
//			if (ans != result) {
//				passed = "failed";
//			}
//			String msg = String.format("test case %d %s !!! mine: %d expected: %d", i, passed, result, ans);
//			
//			System.out.println(msg);
//			or.close();
			
			
		}
	}
	
	public static boolean canFill() {
		boolean ans = true;
		ArrayList<Integer> rest = new ArrayList<Integer>();
		ArrayList<Integer> store = new ArrayList<Integer>();
 		int target = 1;
 		int end = k;
		int pos = 0;
		int count = 0;
		while(target <= m) {
			if(pos < n) {
				 int s = find(target, pos);
				 for(int i = pos; i <= s; i++) {
					 int temp = folders.get(i);
					 if(temp < target || temp > end) {
						 rest.add(0, temp);
					 }else {
						 count++;
					 }
				 }
				 target = Math.min(m, ++target);
				 end = Math.min(m, ++end);
				 if(s != -1) {
					 pos = s + 1;
				 }
			}else {
				break;
			}
		}
		
		
		
			while(count < n) {
				if(store.size() < k) {
					if(rest.size() > 0) {
						int t = rest.remove(0);
						if(t < target || t > end) {
							store.add(t);
						}else {
							count++;
						}
					}else {
						target++;
						end = Math.min(m, ++end);
						if(target <= m) {
							for(int i = store.size()-1; i >= 0; i--) {
								if(store.get(i) >= target && store.get(i) <= end) {
									count++;
									store.remove(i);
								} 
							}
						}else {
							break;
						}
					}
				}else {
					target++;
					end = Math.min(m, ++end);
					if(target <= m) {
						for(int i = store.size()-1; i >= 0; i--) {
							if(store.get(i) >= target && store.get(i) <= end) {
								count++;
								store.remove(i);
							} 
						}
					}else {
						break;
					}
				
				}
		}
		
//		if(count != n) {
//			while(target <= m) {
//				if(store.size() < k) {
//					if(rest.size() > 0) {
//						int t = rest.remove(0);
//						if(t < target || t > end) {
//								store.add(t);
//							}else {
//								while(target < m) {
//									target++;
//									end = Math.min(m, ++end);
//									for(int j = 0; j < store.size(); j++) {
//										if(store.get(j) >= target && store.get(j) <= end) {
//											store.remove(j);
//											count++;
//											j--;
//										}
//									}
//									if(store.size() < k) {
//										store.add(t);
//										break;
//									}
//								}				
//							}
//						}else {
//							count++;
//						}
//					}else {
//						for(int i = store.size()-1; i >= 0; i--) {
//							if(store.get(i) >= target && store.get(i) <= end) {
//								count++;
//								store.remove(i);
//							} 
//						}
//						target++;
//						end = Math.min(m, ++end);
//					}
//					
//				}else {
//					for(int i = store.size()-1; i >= 0; i--) {
//						if(store.get(i) >= target && store.get(i) <= end) {
//							count++;
//							store.remove(i);
//						} 
//					}
//					target++;
//					end = Math.min(m, ++end);
//				}
//				
//			
//		}
		return count == n;
	}
	
	public static int find(int target, int start) {
		int result = -1;
		for(int i = start; i < folders.size(); i++) {
			if(folders.get(i) == target) {
				result = i;
			}
		}
		return result;
	}
	
}
