import java.util.ArrayList;
import java.util.Scanner;

public class Paceman {
    static Scanner s=new Scanner(System.in);
    static ArrayList<Integer>a1=new ArrayList<>();
    static ArrayList<Integer>b1=new ArrayList<>();
    static int[] a12=new int[2];

    static void print(int n){
        for(int i=0;i<n;i++){
            for(int j=0;j<n;j++){
                if(a12[0]==i && a12[1]==j){
                    System.out.print('m');
                }
                else if(a1.get(0)==i && b1.get(0)==j || a1.get(1)==i && b1.get(1)==j){
                    System.out.print('b');
                }
                else{
                    System.out.print('-');
                }
            }
            System.out.println();
        }
        System.out.println("X-X-X-X-X");
    }
    static int ismax(int a,int b,int n){
        if(a==n || a<0 || b==n || b<0){ //|| Math.abs(a12[0]-a1.get(0))==0 || Math.abs(a12[1]-a1.get(1))==0 || Math.abs(a12[0]-b1.get(0))==0 || Math.abs(a12[1]-b1.get(1))==0) {
            return 0;
        }
        return Math.abs(a-a1.get(0))+Math.abs(a-a1.get(1))+Math.abs(b-b1.get(0))+Math.abs(b-b1.get(1));
    }
    static int catch1(int x,int y,int a,int b ,int n){
        if(x==a && y==b){
            System.out.println("You stepped on bot 1");
            print(n);
            return 1;
        }
        if (a<x)
        {
            a1.set(0,a1.get(0)+1);
        }

        else if (a>x){
            a1.set(0,a1.get(0)-1);;
        }

        else if (b>y)
        {
            b1.set(0,b1.get(0)-1);;
        }

        else {
            b1.set(0,b1.get(0)+1);;
        }
        if (a1.get(0)==x && b1.get(0)==y){
            System.out.println("Caught by first bot");
            print(n);
            return 1;
        }
        print(n);
        return 0;
    }
    static int catch2(int x,int y,int a,int b,int n){
        if(x==a && y==b){
            System.out.println("You stepped on bot 2");
            print(n);
            return 1;
        }
        if (b>y)
        {
            b1.set(1,b1.get(1)-1);
        }

        else if (b<y)
        {
            b1.set(1,b1.get(1)+1);
        }

        else if (a<x)
        {
            a1.set(1,a1.get(1)+1);
        }

        else {
            a1.set(1,a1.get(1)-1);
        }
        if (a1.get(1)==x && b1.get(1)==y){
            System.out.println("Caught by second bot");
            print(n);
            return 1;
        }
        print(n);
        return 0;
    }
    static void moveme(int n){

        int a=ismax(a12[0]+1,a12[1],n);
        int b=ismax(a12[0],a12[1]+1,n);
        int c=ismax(a12[0]-1,a12[1],n);
        int d=ismax(a12[0],a12[1]-1,n);
        System.out.println(a12[0]+" "+a12[1]+" "+a+" "+b+" "+" "+c+" "+d);
        int max=Math.max(a,Math.max(b,Math.max(c,d)));
        if(max==a){
            a12[0]=a12[0]+1;
        }
        else if(max==b){
            a12[1]=a12[1]+1;
        }
        else if(max==c){
            a12[0]=a12[0]-1;
        }
        else{
            a12[1]=a12[1]-1;
        }
        System.out.println(a12[0]+" "+a12[1]+" "+a+" "+b+" "+" "+c+" "+d);
        print(n);
    }
    public static void main(String[] args) {
        int ans=0,ans1=0,n,count=0;
        System.out.println("Enter n");
        n=s.nextInt();
        for(int i=0;i<n;i++){
            String sc=s.next();
            for(int j=0;j<sc.length();j++){
                if(sc.charAt(j)=='b'){
                    a1.add(i);
                    b1.add(j);
                }
                if(sc.charAt(j)=='m'){
                    a12[0]=i;
                    a12[1]=j;
                }
            }
        }

        while(ans1==0 && ans==0 ){
            count+=1;
            moveme(n);
            ans=catch1(a12[0],a12[1],a1.get(0),b1.get(0),n);
            ans1=catch2(a12[0],a12[1],a1.get(1),b1.get(1),n);
            if(count==7){
                break;
            }
        }
    }
}
