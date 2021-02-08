import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
	
	static int[] groups=null;
	static int groupId=0;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] graph=ReadInput();
		int[][] tree=getMinConstructTreeKruscal(graph);
		PrintGraph(tree);
		
	}

	private static int[][] getMinConstructTreeKruscal(int[][] graph) {
		// TODO Auto-generated method stub
		ArrayList<Path> paths=retrievePaths(graph);
		Collections.sort(paths,(x,y)->x.length-y.length);
		int[][] rt=new int[graph.length][graph.length];
		for (int i=0;i<graph.length;i++) {
			for (int j=0;j<graph.length;j++) {
				rt[i][j]=0;
			}
		}
		int count=0;
		for (Path path:paths) {
			int group1=groups[path.node1];
			int group2=groups[path.node2];
			if (group1==-1) {
				if (group2==-1) {
					groups[path.node1]=groups[path.node2]=groupId;
					groupId++;
					count+=2;
					rt[path.node1][path.node2]=path.length;
					rt[path.node2][path.node1]=path.length;
				} else {
					groups[path.node1]=group2;
					count++;
					rt[path.node1][path.node2]=path.length;
					rt[path.node2][path.node1]=path.length;
				}
			} else {
				if (group2==-1) {
					groups[path.node2]=group1;
					count++;
					rt[path.node1][path.node2]=path.length;
					rt[path.node2][path.node1]=path.length;
				} else if (group1!=group2) {
					for (int i=0;i<groups.length;i++) {
						if (groups[i]==group2) {
							groups[i]=group1;
						}
					}
					rt[path.node1][path.node2]=path.length;
					rt[path.node2][path.node1]=path.length;
				}
			}
			if (count>=groups.length) {
				break;
			}
		}
		return rt;
		
	}

	private static ArrayList<Path> retrievePaths(int[][] graph) {
		// TODO Auto-generated method stub
		ArrayList<Path> paths=new ArrayList<>();
		for (int i=0;i<graph.length-1;i++) {
			for (int j=i+1;j<graph.length;j++) {
				if (graph[i][j]!=0) {
					Path path=new Path();
					path.node1=i;
					path.node2=j;
					path.length=graph[i][j];
					paths.add(path);
				}
			}
		}
		return paths;
	}

	private static void PrintGraph(int[][] tree) {
		// TODO Auto-generated method stub
		for (int node1=0;node1<tree.length;node1++) {
			for (int node2=node1+1;node2<tree.length;node2++) {
				if (tree[node1][node2]!=0) {
					System.out.println(node1+" "+node2+" "+tree[node1][node2]);
				}
			}
		}
		
	}

	private static int[][] ReadInput() {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		int lines=sc.nextInt();
		int nodes=sc.nextInt();
		int[][] graph=new int[nodes][nodes];
		for (int i=0;i<nodes;i++) {
			for (int j=0;j<nodes;j++) {
				graph[i][j]=0;
			}
		}
		for (int i=0;i<lines;i++) {
			int node1=sc.nextInt();
			int node2=sc.nextInt();
			graph[node1][node2]=graph[node2][node1]=sc.nextInt();
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
