/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ciclos;
import java.util.Scanner;
/**
 *
 * @author NITRO
 */
public class positivoNegativo{
    
    
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner scanner = new Scanner(System.in);
        System.out.println("Con ciclos");
        
     /**
     * Número positivo, negativo o cero: Pide un número al usuario e indica si es positivo, negativo o cero.
     *
     */
        
        System.out.println("Ejercicio Negativo o positivo");
        
        System.out.println("Ingrese un numero entero: ");
        int numero = scanner.nextInt();
        
        System.out.println(negativeOrPositive(numero));
        
        
         /**
         * Mayor de tres números: Solicita tres números e imprime cuál es el mayor.
         */
        System.out.println("Mayor de tres numeros");
        System.out.println("Ingrese 3 numero enteros: ");
        int num1 = scanner.nextInt();
        int num2 = scanner.nextInt();
        int num3 = scanner.nextInt();
        
        System.out.println(mayorTres(num1, num2, num3));
        
        /**
         *     Año bisiesto: Pide un año y determina si es bisiesto o no
         */
        
        System.out.println("Año bisiesto");
        System.out.println("Ingrese año para saber si es bisiesto");
        int año = scanner.nextInt();
        System.out.println(añoBisiesto(año));
        
        /**
         * Pide dos números y una operación (+, -, *, /) e imprime el resultado.
         */
        System.out.println("Calculadora");
        System.out.println("Ingresa el dos numeros numero para la calculadora");
        int calNum1 = scanner.nextInt();
        int calNum2 = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Ingrese la operacion que desea (+, -, *, /)");
        String operador = scanner.nextLine();
        
        System.out.println(calculadora(calNum1, calNum2, operador));
       
        /**
         * Pide los lados de un triángulo e indica si es equilátero, isósceles o escaleno.
         */
        System.out.println("Triangulos");
        System.out.println("Ingresa los lados del triangulo");
        int lado1 = scanner.nextInt();
        int lado2 = scanner.nextInt();
        int lado3 = scanner.nextInt();
        System.out.println(triangulo(lado1, lado2, lado3));
        
        /**
         * Pide un número y muestra su tabla de multiplicar del 1 al 10.
         */
        System.out.println("Tabla multiplicar");
        System.out.println("Ingrese un numero:");
        int numTabla = scanner.nextInt();
        tablaMulti(numTabla);
        
        /**
         *  Solicita un número N y suma los primeros N números naturales.
         */
        System.out.println("Suma N numeros");
        System.out.println("Ingresa un numero");
        int numeroN = scanner.nextInt();
        System.out.println(sumaN(numeroN));
        
        /**
         * Pide un número entero y cuenta cuántos dígitos tiene.
         */
        System.out.println("Contar digitos");
        System.out.println("Ingresa los digitos:");
        int digitos = scanner.nextInt();
        System.out.println(contarDigitos(digitos));
        
        /**
         *  Imprime los primeros N términos de la serie de Fibonacci.
         */
        System.out.println("Fibonacci");
        System.out.println("Ingrese un numero:");
        int numF = scanner.nextInt();
        fibonacci(numF);
        
        /**
         * Solicita un número límite y muestra todos los primos hasta ese número.
         */
        System.out.println("Numeros Primos:");
        System.out.println("Ingrese un numero:");
        double nPrimo = scanner.nextInt();
        imprimirPrimo(nPrimo);
        
        
        
        
        
        
        
        
    }
    /**
     * 
     * 
     * FUNCIONES
     * 
     */
    
    /**
     * Número positivo, negativo o cero: Pide un número al usuario e indica si es positivo, negativo o cero.
     *
     * @param x
     */
    public static String negativeOrPositive(int x){
        if (x<0) {
            return "Es negativo";
        }else if (x==0) {
            return "Es cero";
        }else{
            return "Es positivo";
        }
    }
    
    /**
         * Mayor de tres números: Solicita tres números e imprime cuál es el mayor.
         */
    
    public static int mayorTres(int x, int y, int z){
        if (x>y && x>z) {
            return x;
        }else if (y>x && y>z) {
            return y;
        }else{
            return z;
        }
    }
    
    /**
         *     Año bisiesto: Pide un año y determina si es bisiesto o no
    */
    public static String añoBisiesto(int x){
        if (x %4==0 && x%100!=0 || x%400==0) {
            return "El año  "+x+ " es Bisiesto";
        }else{
            return "El año  "+x+ " no es Bisiesto";
        }
    }
    
    /**
         * Pide dos números y una operación (+, -, *, /) e imprime el resultado.
         */
    public static double calculadora(double x, double y, String oper){
        switch (oper) {
            case "+":
                return x+y;
            case "-":
                return x-y;
           case "*":
               return x*y;
           case "/":
               return x/y;
            default:
                throw new AssertionError();
        }
    }
    
     /**
         * Pide los lados de un triángulo e indica si es equilátero, isósceles o escaleno.
         */
    public static  String triangulo(int x, int y, int z){
        if (x==y && y==z) {
            return "Es equlatero";
        }else if (x==y || x==z || y==z) {
            return "Es isoceles";
        }else {
            return "Es escaleno";
        }
    }
    
    /**
     * Pide un número y muestra su tabla de multiplicar del 1 al 10.
     */
    public static void tablaMulti(int x){
        for (int i = 1; i <= 10; i++) {
            System.out.println(x+"x"+i+" = "+(x*i));
        }
    }
    
    /**
         *  Solicita un número N y suma los primeros N números naturales.
         */
    public static int sumaN(int x){
        int suma = 0;
        for (int i = 0; i <= x; i++) {
            suma = suma+i;
        }
        return suma;
    }
    
    /**
     * Pide un número entero y cuenta cuántos dígitos tiene.
     */
    public static int contarDigitos(int x){
        x = Math.abs(x); 
        int contador = 0;
        
        do {
            contador++;
            x /= 10; 
        } while (x > 0);
        
        return contador;
    }
    
    /**
     *  Imprime los primeros N términos de la serie de Fibonacci.
     */
    public static void fibonacci(int x) {
        int a = 0, b = 1;
        for (int i = 0; i < x; i++) {
            System.out.println(a + " ");
            int suma = a + b;
            a = b;
            b = suma;
        }
    }
     /**
         * Solicita un número límite y muestra todos los primos hasta ese número.
         */
    public static boolean primos(double x){
        if (x <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(x); i++) {
            if (x % i == 0) {
                return false;
            }
        }
        return true;
    }
    public static void imprimirPrimo(double x){
        for (int i = 2; i < x; i++) {
            if (primos(i)) {
                System.out.println(i+" ");
            }
        }
    }
    
    
    
    
    
}
