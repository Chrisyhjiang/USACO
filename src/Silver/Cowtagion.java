package Silver;

// http://www.usaco.org/index.php?page=viewproblem2&cpid=1062
import java.io.*;
import java.util.ArrayList;
public class Cowtagion {
	// use recursion to traverse all nodes on the given tree
	// trees have n - 1 number of paths, this problem has two paths. 
	static int days;
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(br.readLine());
		node[] farms = new node[n];
		
		for(int i = 0; i < n; i++) {
			ArrayList<node> children = new ArrayList<node>();
			farms[i] = new node(i+1, false, children);
		}
		
		for(int i = 0; i < n-1; i++) {
			String[] line = br.readLine().split(" ");
			int id = Integer.parseInt(line[0]);
			int childID = Integer.parseInt(line[1]);
			farms[id-1].getChildren().add(farms[childID-1]);
			farms[childID-1].getChildren().add(farms[id-1]);
		}
		days = -1;
		for(int i = 0; i < farms.length; i++) {
			if(!farms[i].isSick()) {
				traverse(farms[0]);
			}
		}
		System.out.println(days);
	}
	
	public static void traverse(node n) {
		// call or decide whether there is enough virus
		if(!n.isSick()) {
			days++;
			n.setSick(true);
			ArrayList<node> children = n.getChildren();
			int length = 0;
			for(node child: children) {
				if(!child.isSick()) {
					length++;
				}
			}
			if(length != 0) {
				// length + 1 days since 2^k has to greater than N + 1 since there has to be one left for the child itself
				days += Math.ceil((Math.log10(length+1) / Math.log10(2)));
				for(int i = 0; i < children.size(); i++) {
					node child = children.get(i);
					if(!child.isSick()) {
						// traverse everytime child is not sick
						traverse(child);
					}
				}
			}
		}
	}

}
class node{
	
	private int id;
	private boolean sick;
	private ArrayList<node> children;
	
	public node(int myid, boolean mysick, ArrayList<node> mychildren) {
		id = myid;
		sick = mysick;
		children = mychildren;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isSick() {
		return sick;
	}

	public void setSick(boolean sick) {
		this.sick = sick;
	}

	public ArrayList<node> getChildren() {
		return children;
	}

	public void setChildren(ArrayList<node> children) {
		this.children = children;
	}
	
}

