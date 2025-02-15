/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package definicionvariable;

/**
 *
 * @author NITRO
 */
public class DefinicionVariable {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        /**
         * ára definir una varible se le debe poner su tipo y 
         * de segundo el nombre de la variable
         * ejemplo:
         * int num1;
         * para asignar un valor o inicializar
         * num1=5;
         */
        int num1 = 5;
        //int num2;
        //num2 = 8;
        //int num3;
        System.out.println(num1);
        
        //TIPOS DE DATOS
        var variable = "hola";
        //Concatenar
        String nombre = "Sergio";
        int edad = 17;
        System.out.println("Mi nombres es "+nombre+" y mi edad es "+ edad);
        
        //System.out.println("Hola "+(5+5));
        
        
        //Ejecicio grados
        
        System.out.println("Ejercicio grados");
        
        double celsius = 20;
        double kelvin;
        double fahrenheit;
        kelvin = celsius + 273.15;
        System.out.println(celsius+"C = "+kelvin+"k");
        fahrenheit = ((celsius*9)/5)+32;
        System.out.println(celsius+"C = "+fahrenheit+"f");
        
        //Calcualo IMC
        System.out.println("Ejercicio IMC");
        
        double peso = 99.95;
        double estatura= 1.69;
        double imc;
        
        imc = peso/(estatura*estatura);
        System.out.println("IMC: "+imc);
        
        
        //Area y perimetro del triangulo
        System.out.println("Ejercicio area triangulo");
        
        double base = 5;
        double altura = 10;
       
        double area;
        double perimetro;
        
        area = (base*altura)/2;
        System.out.println("area: "+ area);
        
       double h;
       h= (base*base)+(altura*altura);
       h = Math.sqrt(h);
       
       perimetro = base + altura + h;
       System.out.println("perimetro: "+ perimetro);
        
        
        
        //Ejercicio numeros
        System.out.println("Ejercicio numeros");
        int numero1 = 10;
        int numero2 = 5;
        System.out.println("Suma: "+(numero1+numero2));
        System.out.println("Suma: "+(numero1-numero2));
        System.out.println("Multiplicacion: "+(numero1*numero2));
        System.out.println("Division: "+(numero1/numero2));
        
        //Ejercicio cambio de varibales
        int a = 5;
        int b = 10;
        
        
        a = a+b;
        b = a-b;
        a = a-b;
        
        
        
        
        
        System.out.println(a);
        System.out.println(b);
        
        System.out.println("");
        System.out.println("");
        
        System.out.println("Con ciclos");
        System.out.println("Ejercicio Negativo o positivo");
        int negPos = 7;
        if (negPos<0) {
            System.out.println(negPos+" es negativo");
        }
        if (negPos==0) {
            System.out.println(negPos+" es cero");
        }
        System.out.println("Es positivo");
        
        
        
        
        
    }
    public static int hola(int a, int b){
      return a+b;
    }
    
}
