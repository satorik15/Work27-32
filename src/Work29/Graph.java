package Work29;
import java.util.Scanner;
public class Graph
{
    public static void main(String[] args)
    {
        Graph graph = new Graph();
        System.out.println(graph.solve());



    }
    private int solve()
    {
        Scanner input = new Scanner(System.in);
        int N=input.nextInt();
        while (0>N || N>100)
        {
            N=input.nextInt();
        }
        int[][] g = new int[N][N];
        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < N; j++)
            {
                g[i][j] = input.nextInt();
            }
        }
        int solution=0;
        for (int i = 0; i < N; i++)
        {
            for (int j = 0; j < N; j++)
            {
                solution+=g[i][j];
            }
        }
        solution/=2;
        return solution;
    }
}
