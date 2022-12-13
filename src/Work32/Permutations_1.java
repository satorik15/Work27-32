package Work32;
import java.util.Scanner;
public class Permutations_1
{
    public void swap(int[] a, int i, int j)
    {
        int s=a[i];
        a[i]=a[j];
        a[j]=s;
    }
    public boolean NextSet(int[] a ,int n)
    {
        int j=n-2;
        while (j != -1 && a[j]>=a[j+1])
            j--;
        if(j==-1)
            return false;
        int k=n-1;
        while (a[j]>=a[k])
            k--;
        swap(a,j,k);
        int l=j+1,r=n-1;
        while(l<r)
            swap(a,l++,r--);
        return true;
    }
    void print(int[] a,int n,int num)
    {
        System.out.print(num+": ");
        for (int i=0;i<n;i++)
        {

            System.out.print(a[i] +" ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Permutations_1 permutations_1 = new Permutations_1();
        Scanner input= new Scanner(System.in);
        int n,a[];
        System.out.print("N = ");
        n=input.nextInt();
        a= new int [n];
        for (int i=0;i<n;i++)
        {
            a[i]=i+1;
        }
        int num=1;
        permutations_1.print(a,n,num);
        while (permutations_1.NextSet(a,n))
        {
            num++;
            permutations_1.print(a, n, num);
        }
    }
}
