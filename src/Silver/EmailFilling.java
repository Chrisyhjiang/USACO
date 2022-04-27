package Silver;

// USACO 2022 February Contest, Silver - Problem 3. Email Filing
// http://www.usaco.org/index.php?page=viewproblem2&cpid=1208

import java.io.*;
import java.util.*;

public class EmailFilling {
	
	static int m;
	static int n;
	static int k;
	static ArrayList<Integer> folders;
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		// path for test data on my local machine
		String path = "/Users/wenbojiang/Downloads/prob3_silver_feb22/";
		for (int a = 1; a <= 12; a++) {
			String fn = path + a + "t.in";
			File file = new File(fn);
			BufferedReader br = new BufferedReader(new FileReader(file));
			
			String ofn = path + a + ".out";
			File out = new File(ofn);
			BufferedReader bw = new BufferedReader(new FileReader(out));
			
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
			}
			String p = succeed ? "succeed...  " : "failed....";
			System.out.println(String.format("test case %d %s", a, p));
			br.close();
			bw.close();
		}
	}
	
	/**
	 * This method implements a two phase algorithms. 
	 * 
	 * In phase one we process the original mail list into an unprocessed list and the current mail list with at most k entry. This step terminates when
	 * the last element in the original mail list is process.
	 * 
	 * In phase 2, we continue process the current mail list and the unprocessed mail list. The step terminates
	 * when it reached the last folder entry m
	 */
	public static boolean canFill() {
		ArrayList<Integer> curMails= new ArrayList<Integer>();
		ArrayList<Integer> unProcessedMails = new ArrayList<Integer>();
		 
		int startFolder = 1; // start folder
		int endFolder = k; // end folder
		int pos = find(startFolder); // the last index of the given entry in the original mail list;
		
		// phase one: process email on the original folder list and store all the currMails & unProcessedMails
		for(int i = 0 ; i < folders.size(); i++) {
			// check whether curMials can be moved to folders
			for(int j = curMails.size()-1; j >= 0; j--) {
				int q = curMails.get(j);
				if(q >= startFolder && q <= endFolder) {
					curMails.remove(j);
				}
			}
			
			// process the mail from original folder, there are 2 states for mailed that cannot be moved to folder. 
			// #1: moved to curMail list 
			// #2: moved the top mail from the curMail list to unProcessedMails and moved the mail into curMail.
			// In case the curMail list is full, we check the current index with the last entry of the startFolder
			// to decide whether move up the folder or move up the mail to unprocessed list.
			int current = folders.get(i);
			if(current < startFolder || current > endFolder) {
				if(curMails.size() < k) {
					curMails.add(current);
				}else {
					if(i <= pos) {
						// move up the top mail into unprocessed email list
						unProcessedMails.add(0, curMails.get(0));
						curMails.remove(0);
						curMails.add(current);
					}else {
						// move up the folder list;
						startFolder++;
						endFolder = Math.min(m, ++endFolder);
						i--;
						pos = find(startFolder);
					}
				}
			}
			
		}
		
		
		// phase 2: process email on the current list and unprocessed list
		while(startFolder <= m) {
			// processed the current mail list
			for(int i = curMails.size()-1; i >= 0; i--) {
				int q = curMails.get(i);
				if(q >= startFolder && q <= endFolder) {
					curMails.remove(i);
				}
			}
			
			// add unprocessed mail into curMail.
			while(curMails.size() < k) {
				if(!unProcessedMails.isEmpty()) {
					int j = unProcessedMails.remove(0);
					if(j < startFolder || j > endFolder) {
						curMails.add(0, j);
					}
				}else {
					break;
				}
			}
			startFolder++;
			endFolder = Math.min(m, ++endFolder);
		}
		return unProcessedMails.size() == 0 && curMails.size() == 0;
	}
	
	// find the last index of the given entry
	public static int find(int target) {
		int result = -1;
		for(int i = folders.size()-1; i >= 0; i--) {
			if(folders.get(i) == target) {
				result = i;
				break;
			}
		}
		return result;
	}
	
}
