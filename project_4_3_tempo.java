import java.io.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class project_4_3_tempo
{
    public static class Global
    {   
        public static int array[];
        public static int n;
        
        public static void cria_array(int x){
            n = x;
            array = new int[n];
        }
    }

    public static int binarySearch(int arr[], int l, int r, int x)
    {
        if(x > arr[arr.length -1]){
            return arr.length;
        }
        if(x <= arr[0]){
            return 0;
        }

        if (r >= l) {
            int mid = l + (r - l) / 2;
 
            if (arr[mid] > x && arr[mid-1] == x){
                while(arr[mid-1] == x){
                    mid--;
                }
                return mid;
            }
            if (arr[mid] > x && arr[mid-1] < x)
                return mid;
            else if(arr[mid] == x && arr[mid-1] == x){
                while(arr[mid-1] == x){
                    mid--;
                }
                return mid;
            }
            else if(arr[mid] == x){
                return mid;
            }
            else if(arr[mid] > x)
                return binarySearch(arr, l, mid - 1, x);
 
            return binarySearch(arr, mid + 1, r, x);
        }

        return 0;
    }    


    public static void insertionSort(int [] a, int low , int high){

        for(int i = low ; i <= high ; i++){
  
          int j = i;
          
          while(j > 0 && a[j-1] > a[j]){
  
            int key = a[j];
            a[j] = a[j-1];
            a[j-1] = key;
            j = j-1; 
  
          }
        }
      }


    public static void quicksort( int [] a )
    { 
        quicksort(a, 0, a.length-1);
    }

    private static final int CUTOFF = 30;

    public static void swapReferences( int [] a, int index1, int index2 )
    { 
        int tmp = a[index1];
        a[index1] = a[index2];
        a[index2] = tmp; 
    }


    private static void quicksort(int []a, int low, int high)
    {
        if(low + CUTOFF > high)
            insertionSort(a, low, high);
        else
        {
        // Sort low, middle, high
        int middle = (low + high) / 2;

        if(a[middle] < (a[low]))
        swapReferences(a, low, middle);

        if(a[high] < (a[low]))
        swapReferences(a, low, high);
        
        if(a[high] < (a[middle]))
        swapReferences(a, middle, high);

        // Place pivot at position high - 1
        swapReferences(a, middle, high-1);
        int pivot = a[high-1];
        // Begin partitioning
        int i, j;

        for(i = low, j = high - 1; ; ){

        while(a[++i] < (pivot));

        while(pivot < (a[--j]));

        if(i<j)
            swapReferences(a, i, j);
        else
        break;
        }
        // Restore pivot
        swapReferences(a, i, high - 1);
        quicksort(a, low, i-1); // Sort small elements
        quicksort(a, i + 1, high); // Sort large elements
    }
}

    static String le_parametros(Scanner sc)
    {
        String str;  
            
        try{
                
        str = sc.nextLine();

        }
        //Se o valor for um valor causar um erro, ira ser avisado ao usuario que o valor nao e valido.
        catch (java.util.InputMismatchException e){
            System.out.printf("Valor Introduzido nao e valido.");
            return null;
        }
        
        return str;
    }

    //permite receber o input do usuario e verificar qual a opcao inserida.
    static void opcoes(int num_elementos){
    
    Random rand = new Random();
    long start = 0;
    long end = 0;
    long total = 0;
    Global.cria_array(num_elementos);

    for(int x = 0 ; x < num_elementos ; x++){

        Global.array[x] = rand.nextInt((num_elementos / 10) + 1);
    }

    int valor = num_elementos;

    start = System.currentTimeMillis();
    quicksort(Global.array);
    end = System.currentTimeMillis()-start;

    total += end;

    for(int c = 0 ; c < valor ; c++){

        start = System.currentTimeMillis();
        int numero_limite = rand.nextInt((num_elementos / 10) + 1);

        double contador = 0;

        contador = binarySearch(Global.array,0 , Global.n -1 , numero_limite);
        
        int percentil;
        percentil = (int) Math.floor((contador / Global.array.length) * 100);

    end = System.currentTimeMillis()-start;
    total += end;

    }

    System.out.println(total/1000F);
}

    public static void main(String[] args) 
    {   
        opcoes(500000);
    }
}