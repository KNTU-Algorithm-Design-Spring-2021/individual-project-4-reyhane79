import java.lang.*;
import java.util.*;
class DisjointPath {
    static int N = 1000;
    static int s;
    static int t;
    static int path_number = 0;
    static int[][] graph = new int[N][N];
    static boolean[] mark = new boolean[N];
    static Stack<Integer> path = new Stack<>();
    static int[][] disjoint_paths = new int[N][N];
    static void dfs(int x) {
        mark[x] = true;
        if (x == t){
            path_number += 1;
            int tail = x;
            while (path.size() != 0) {
                int now = path.pop();
                // agar bar akse yal dar masire digar amade bood har 2 ra hazf mikonim dar gheyre in soorat yal ra be graph disjoint_paths ezafe mikonim
                if (disjoint_paths[tail][now] == 1)
                    disjoint_paths[tail][now] = 0;
                else
                    disjoint_paths[now][tail] = 1;

                // yal dar jahate masir ra hazf va yek yal mokhalefe jahate an gharar midahim
                graph[now][tail] -= 1;
                graph[tail][now] += 1;
                tail = now;
            }
            // kolle araye mark ra true mikonim ke digar dfs edame peyda nakonad va motevaghef shavad
            for (int v = 0; v < N; v++)
                mark[v] = true;
        } else
            path.add(x);

        for (int v = 0; v < N; v++) {
            if (!mark[v] && graph[x][v] > 0) {
                dfs(v);
            }
        }
        if (path.size() != 0)
            path.pop();
    }
    static void printAns(int x) {
        mark[x] = true;
        path.add(x);
        for (int v = 0; v < N; v++) {
            // agar rasi ke dashtim behesh miraftim rase t bood masiri ke ta alan too stack darim ro chap mikonim
            if (v == t && disjoint_paths[x][v] > 0){
                System.out.println("Path: ");
                for(Object s : path) {
                    System.out.print(s.toString() + " ");
                }
                System.out.println(t);
            }
            if (!mark[v] && disjoint_paths[x][v] > 0) {
                printAns(v);
            }
        }
        path.pop();
    }
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
        System.out.println("Enter number of V:");
        int n = sc.nextInt();
        System.out.println("Enter number of E:");
        int m = sc.nextInt();
        System.out.println("Enter Edges:");
        for (int i = 0; i < m; i++){
            int fi= sc.nextInt();
            int se= sc.nextInt();
            graph[fi][se] = 1;
        }
        System.out.println("S:");
        s = sc.nextInt();
        System.out.println("T:");
        t = sc.nextInt();

        for (int i = 0; i < N; ++i)
            mark[i] = false;
        dfs(s);

        for (int i = 0; i < N; ++i)
            mark[i] = false;
        dfs(s);
        if (path_number == 2){
            System.out.println("2 separate Edge path: ");
            for (int i = 0; i < N; ++i)
                mark[i] = false;
            printAns(s);
        } else {
            System.out.println("2 separate Edge path not exist.");
        }
    }
}
