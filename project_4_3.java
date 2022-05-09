import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class project_4_3
{

    public static class Global
    {   
        public static int array[];
        public static int n;

        public static void cria_array(int x , int y){
            n = x*y;
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
    static void opcoes(Scanner sc){
    
    int fim = 0;

    while(fim == 0)
    {

        String[] parametros;
        parametros = le_parametros(sc).split(" ");


        if(parametros[0].equals("RASTER")){
            
            int n,m;
            int indice = 0;

            n = Integer.parseInt(parametros[1]);
            m = Integer.parseInt(parametros[2]);

            Global.cria_array(n, m);

            for(int i = 0 ; i < n ; i++){

                parametros = le_parametros(sc).split(" ");

                for(int x = 0 ; x < m ; x++){
                    
                    Global.array[indice] = Integer.parseInt(parametros[x]);
                    indice++;

                }
            }

            quicksort(Global.array);

            System.out.println("RASTER GUARDADO");
        }
        
        if(parametros[0].equals("AMPLITUDE"))
            System.out.println(Global.array[Global.array.length - 1] - Global.array[0]);

    

        if(parametros[0].equals("PERCENTIL")){ 

            int valor = Integer.parseInt(parametros[1]);
            parametros = le_parametros(sc).split(" ");

            for(int c = 0 ; c < valor ; c++){

                int numero_limite = Integer.parseInt(parametros[c]);
                double contador = 0;

                contador = binarySearch(Global.array,0 , Global.n -1 , numero_limite);
                
                int percentil;
                percentil = (int) Math.floor((contador / Global.array.length) * 100);

                if(c == valor -1)
                    System.out.println(percentil);
                else
                System.out.print(percentil + " ");
            }
        }

        if(parametros[0].equals("MEDIANA")){

            int mediana = 0;

            if(Global.array.length % 2 == 0){
                mediana = (Global.array[Global.array.length / 2] + Global.array[(Global.array.length / 2) - 1]) / 2;
            }
            else
                mediana = Global.array[(Global.array.length / 2)] ;
                
            System.out.println(mediana);
        }

        if(parametros[0].equals("TCHAU")){
                
            fim++;
            sc.close();

        }

        }
    }


    public static void main(String[] args) 
    {   
        Scanner sc = new Scanner(System.in);

        opcoes(sc);
        sc.close();
    }
}