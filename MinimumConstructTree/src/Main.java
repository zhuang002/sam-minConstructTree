import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
	
	static int[] groups=null;
	static int groupId=0;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList<Path> graph=ReadInput();
		ArrayList<Path> tree=getMinConstructTreeKruscal(graph);
		PrintGraph(tree);
		
	}

	private static ArrayList<Path> getMinConstructTreeKruscal(ArrayList<Path> paths) {
		// TODO Auto-generated method stub
		
		Collections.sort(paths,(x,y)->x.length-y.length);
		ArrayList<Path> rt=new ArrayList<>();
		
		int count=0;
		for (Path path:paths) {
			int group1=groups[path.node1];
			int group2=groups[path.node2];
			if (group1==-1) {
				if (group2==-1) {
					groups[path.node1]=groups[path.node2]=groupId;
					groupId++;
					count+=2;
					rt.add(path);
					
				} else {
					groups[path.node1]=group2;
					count++;
					rt.add(path);
				}
			} else {
				if (group2==-1) {
					groups[path.node2]=group1;
					count++;
					rt.add(path);
				} else if (group1!=group2) {
					for (int i=0;i<groups.length;i++) {
						if (groups[i]==group2) {
							groups[i]=group1;
						}
					}
					rt.add(path);
				}
			}
			if (count>=groups.length) {
				break;
			}
		}
		return rt;
		
	}

	private static void PrintGraph(ArrayList<Path> tree) {
		// TODO Auto-generated method stub
		for (Path path:tree) {
			System.out.println(path.node1+" "+path.node2+" "+path.length);
		}
		
	}

	private static ArrayList<Path> ReadInput() {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		int lines=sc.nextInt();
		int nodes=sc.nextInt();
		ArrayList<Path> graph=new ArrayList();
		
		for (int i=0;i<lines;i++) {
			Path path=new Path();
			path.node1=sc.nextInt();
			path.node2=sc.nextInt();
			path.length=sc.nextInt();
			graph.add(path);
		}
		
		groups=new int[nodes];
		for (int i=0;i<nodes;i++)
			groups[i]=-1;
		return graph;
	}

}

class Path {
	int node1;
	int node2;
	int length;
}
