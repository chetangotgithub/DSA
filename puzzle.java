import java.util.*;
//First I thoght of swaping at each iteration i.e swap at arr[0][0] is different
// than that of arr[0][1],arr[0][2],arr[1][1] etc. Eight different methods was needed

//Second was the Devlopment of structure of Cube
//class cube{
//    int data;
//    cube right;
//    cube left;
//    cube up;
//    cube down;
//    public cube(int els){
//        this.data=els;
//        this.down=0 this.left=0 this.up=0 this.right=0;
//    }
//}

//Third was to develop 5*5 matrix so that only one function
// i.e 4 swaps can be done reccurcively
public class puzzle {
    static Queue<Integer>array=new LinkedList<>();
//    static int[] array=new int[9];
    static int count=0;
    static int place(int a,int b){
        int place=1;
        for(int i=1; i<4;i++){
            for(int j=1;j<4;j++){
                if(a==i && b==j){
                    return place;
                }
                place++;
            }
        }
        return 0;
    }
    static void print(int[][] a){
        for(int i=0;i<a.length;i++){
            for(int j=0;j<a.length;j++){
                System.out.print(a[i][j]+" ");
            }
            System.out.println();
        }
        System.out.println();
    }
    static int[][] swap(int a,int b,int x,int y,int[][]test,int [][]arr){
        int[][]test1=new int[test.length][test.length];
        for(int i=0;i<test.length;i++){
            for(int j=0;j<test.length;j++){
                test1[i][j]=test[i][j];
            }
        }
        int cc=0,ans=0;
        if(test1[a][b]==99 || test1[x][y]==99){
            test1[0][0]=99;
            return test1;
        }

        for(int i=1;i<4;i++ ){
            for(int j=1;j<4;j++){
                if(i==a && j==b){
                    int temp=test1[a][b];
                    test1[a][b]=test1[x][y];
                    test1[x][y]=temp;
                    System.out.println("Swaped :("+a+" "+b+") and ("+x+" "+y+")");
                    cc=1;
                    break;
                }
            }
            if(cc==1){
                break;
            }
        }
        for(int i=1;i<4;i++ ){
            for(int j=1;j<4;j++){
                if(test1[i][j]!=arr[i][j]){
                    ans+=1;
                }
            }
        }

        test1[0][0]=ans;
        System.out.println("ans="+test1[0][0]);
        Object[] give=array.toArray();
        for(int i=0;i<array.size();i++){
            if(place(x,y)==array.element()){
                test1[0][0]=99;
                return test1;
            }
        }
        return test1;
    }
    static void puzzle1(int [][] test,int [][] real){
        int min=999,i=0,j=0;
        for(int a=1;a<4;a++){
            for(int b=1;b<4;b++){
                if(test[a][b]==0){
                    i=a;
                    j=b;
                    break;
                }
            }
        }
        array.add(place(i,j));
        System.out.println(array);
        if(count>1){
            array.remove();
        }
        System.out.println(array);
        System.out.println(i+" "+j);
        int[][] ans1 =swap(i,j,i+1,j, test,real);
        int[][] ans2 =swap(i,j,i-1,j, test,real);
        int[][] ans3 =swap(i,j,i,j+1, test,real);
        int[][] ans4 =swap(i,j,i,j-1, test,real);
        print(ans1);
        print(ans2);
        print(ans3);
        print(ans4);
        min=Math.min(ans1[0][0],Math.min(ans2[0][0],Math.min(ans3[0][0],ans4[0][0])));
        if(min==0 || count==5){
            return;
        }
        count++;
        if(ans1[0][0]==min){
            System.out.println("ans1");
            puzzle1(ans1,real);
        }
        else if(ans2[0][0]==min){
            System.out.println("ans2");
            puzzle1(ans2,real);
        }
        else if(ans3[0][0]==min){
            System.out.println("ans3");
            puzzle1(ans3,real);
        }
        else{
            System.out.println("ans4");
            puzzle1(ans4,real);
        }

    }
    public static void main(String[] args) {
        Scanner s=new Scanner(System.in);
        int [][] a=new int[5][5];
        int [][] real={{99,99,99,99,99},{99,1,2,3,99},{99,8,0,4,99},{99,7,6,5,99},{99,99,99,99,99}};
        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                if(i==0 || i==4 || j==0 || j==4){
                    a[i][j]=99;
                }
                else{
                    a[i][j]=s.nextInt();
                }
            }
        }
        puzzle1(a,real);
    }
}
