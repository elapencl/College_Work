public class SquareInAGraph {
    public static int[][] graph;
    public static int[][] squareGraph;

    public SquareInAGraph(int v) {
        graph=new int[v][v];
        squareGraph=new int[v][v];
    }

    public void addEdge(int p,int q){
        graph[p][q]=1;
        graph[q][p]=1;
    }

    public void matMult(int m, int n){
        int total=0;
        for(int i=1;i<m;i++){
            for(int j=1;j<m;j++){//each item in a row
                for(int k=1;k<n;k++){//each item in a column
                    total+=graph[i][k]*graph[k][j];
                }
                squareGraph[i][j]=total;
                total=0;
            }
        }
    }

    public static boolean checkSquare(int m, int n){
        for(int i=1;i<m;i++){
            for(int j=1;j<n;j++){
                if(i!=j){
                    if(squareGraph[i][j]>1){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void main(String args[]){
        In in = new In("dataAlgo.txt");
        int i = in.readInt();
        for(int j=0; j<i;j++) {
            int v = in.readInt();
            SquareInAGraph g = new SquareInAGraph(v + 1);
            int e = in.readInt();
            for(int k=0;k<e;k++){
                int p = in.readInt();
                int q = in.readInt();
                g.addEdge(p, q);
            }
            g.matMult(v + 1, v + 1);
            if (checkSquare(v + 1, v + 1)) {
                StdOut.print("1 ");
            } else {
                StdOut.print("-1 ");
            }
        }
    }
}