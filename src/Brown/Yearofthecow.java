package Brown;

// http://www.usaco.org/index.php?page=viewproblem2&cpid=1107

import java.io.*;
import java.util.*;
public class Yearofthecow {

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		Hashtable<String, Integer> zodiacTable = new Hashtable<String, Integer>();		
		String[] zodiac = {"Ox","Tiger","Rabbit","Dragon","Snake"
				,"Horse","Goat","Monkey","Rooster","Dog","Pig","Rat"};
		
		for(int i = 0; i < zodiac.length; i++) {
			zodiacTable.put(zodiac[i],i);
		}
		Hashtable<String, Cow> cows = new Hashtable<String, Cow>();
		Cow Bessie = new Cow("Bessie", "Ox",0);
		cows.put("Bessie", Bessie);
		
		for(int i = 0; i < n; i++) {
			String[] line = br.readLine().split(" ");
			String currentCowName = line[0];
			String currentCowZodiac = line[4];
			String currentStatus = line[3];
			String parentCowName = line[line.length-1];
			Cow parentCow = cows.get(parentCowName); 
			
			int current = zodiacTable.get(currentCowZodiac);
			int parent = zodiacTable.get(parentCow.getZodiac());
			int diff = current - parent + parentCow.getYear();
			if (currentStatus.equals("previous")) {
				
				if (parent <= current) {
					diff -= 12;
				}
				
			}else {
				if (parent >= current) {
					diff += 12;
				}
			}
			
			Cow cow = new Cow(currentCowName,currentCowZodiac,diff);
			cows.put(currentCowName,cow);
			
		}
		int result = Math.abs(cows.get("Elsie").getYear());
		System.out.println(result);
	}
	
	
	public static class Cow{
		private  String name;
		private  String zodiac;
		private  int year;
		
		public Cow(String n, String z, int y) {
			name = n;
			zodiac = z;
			year = y;
		}

		public String getName() {
			return name;
		}

		public void setName(String mname) {
			name = mname;
		}

		public String getZodiac() {
			return zodiac;
		}

		public void setZodiac(String mzodiac) {
			zodiac = mzodiac;
		}

		public int getYear() {
			return year;
		}

		public void setYear(int myear) {
			year = myear;
		}
	}

}
