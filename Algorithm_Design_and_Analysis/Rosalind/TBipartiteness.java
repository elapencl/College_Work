import java.util.ArrayList;

public class TBipartiteness {
    private static ArrayList<Integer>[] neighbors;
    private static boolean[] marked;
    private static boolean[] color;
    private static boolean[] visit;

    public TBipartiteness(int capacity) {
        neighbors = (ArrayList<Integer>[])new ArrayList[capacity];
        marked = new boolean[capacity];
        color=new boolean[capacity];
        visit=new boolean[capacity];
        for(int i=0;i<capacity;i++){
            neighbors[i]=new ArrayList<Integer>();
        }
    }

    public void addEdge(int i, int j){
        neighbors[i].add(j);
        neighbors[j].add(i);
    }

    public void traverseDepthFirst(int start) {
        traverseDepthFirst(start, new boolean[neighbors.length]);
    }

    void traverseDepthFirst(int start, boolean[] visited){
        if(!visited[start]){
            visit[start]=true;
            visited[start]=true;
            for (int neighbor : neighbors[start]) {
                if(!visited[neighbor]) {
                    if(!marked[neighbor]) {
                        marked[neighbor] = true;
                        color[neighbor] = !color[start];
                    }
                    traverseDepthFirst(neighbor,visited);
                }
            }
        }
    }

    public static void main(String[] args){
        In in = new In("dataAlgo.txt");
        int x = in.readInt();//number of graphs
        for(int i=0;i<x;i++) {
            int v = in.readInt();
            int e = in.readInt();
            TBipartiteness g = new TBipartiteness(v + 1);
            for (int j = 0; j < e; j++) {
                int p = in.readInt();
                int q = in.readInt();
                g.addEdge(p, q);
            }
            int y=0;
            int[] components = new int[100000];
            for (int k = 1; k < v+1; k++) {
                if(!visit[k]) {
                    components[y]=k;
                    y++;
                    g.traverseDepthFirst(k);
                }
            }
            boolean flag=true;
            for(int m=0;m<v+1;m++){
                for(int neighbor : neighbors[m]){
                    if(color[m]==color[neighbor]){
                        flag=false;
                        break;
                    }
                }
                if(!flag){
                    break;
                }
            }
            if(flag){
                StdOut.print("1 ");
            }else{
                StdOut.print("-1 ");
            }
        }

    }
}
