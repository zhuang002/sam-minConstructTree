import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {
	
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
		return graph;
	}

}

class Path {
	int node1;
	int node2;
	int length;
}
