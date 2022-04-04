import java.util.Scanner;
import java.util.Stack;

public class Maze {
    static Stack<Character>stack=new Stack<>();
    static boolean isSafe(int a,int b,int[][] pattern,int[][] visited,int n){
        return a >= 0 && b >= 0 && a < n && b < n && pattern[a][b] == 1 && visited[a][b] == 0;
    }
    static boolean path(int a,int b,int g1,int g2,int[][] pattern,int[][] visited,int n){
        if (a == g1 && b == g2) {
            visited[a][b] = 1;
            return true;
        }
        if (isSafe(a, b, pattern, visited, n)) {
            visited[a][b] = 1;
            if (path(a+1,b,g1,g2,pattern,visited,n)){
                stack.push('D');
                return true;
            }
            if (path(a-1,b,g1,g2,pattern,visited,n)) {
                stack.push('U');
                return true;
            }
            if(path(a,b+1,g1,g2,pattern,visited,n)) {
                stack.push('R');
                return true;
            }
            if(path(a,b-1,g1,g2,pattern,visited,n)) {
                stack.push('L');
                return true;
            }
            visited[a][b] = 0;
            return false;
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner s=new Scanner(System.in);
        Stack<Character>newstack=new Stack<>();
        StringBuilder st= new StringBuilder();
        System.out.println("Enter size of matrix");
        int n=s.nextInt();
        int a=-1,b=-1,g1=-1,g2=-1;
        int[][] pattern=new int[n][n];
        int[][] visited=new int[n][n];
        System.out.println("Enter initial index");
        a=s.nextInt();
        b=s.nextInt();
        System.out.println("Enter goal index");
        g1=s.nextInt();
        g2=s.nextInt();
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                pattern[i][j]=s.nextInt();
                visited[i][j]=0;
            }
        }
        boolean ans=path(a,b,g1,g2,pattern,visited,n);
        while(!stack.isEmpty()){
            Character x=stack.peek();
            st.append(x);
            stack.pop();
        }
        System.out.println(st);
    }
}
