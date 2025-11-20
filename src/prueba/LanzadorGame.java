package prueba;

import java.io.IOException;
import java.util.Scanner;

public class LanzadorGame {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
	    System.out.print("Introduce el primer número entero: ");
	    int num1 = scanner.nextInt();
	    scanner.close();   
	    
	    // Obtener el classpath actual del sistema
	    String classpath = System.getProperty("java.class.path");
	    
	    ProcessBuilder proceso = new ProcessBuilder("java", "-cp", classpath, "LanzadorGame", String.valueOf(num1));
        
        // Iniciar el proceso y manejar la salida
        try {
            // Iniciar el proceso y esperar que termine
            proceso.start().waitFor();

            System.out.println("El proceso ha sido lanzado con éxito");
            System.out.println("Examina los archivos salida.txt y errores.txt");

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

	}
	
}
