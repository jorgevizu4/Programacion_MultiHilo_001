package prueba;

public class Game {

	public static void main(String[] args) {
		System.out.println();
		if (args.length == 0 || args.length > 1) {
            System.out.println("Se requiere un argumento");
            return;
        }
        try {
            // Convertir los argumentos a enteros
            int num = Integer.parseInt(args[0]);
  
            // Mostrar el resultado
            System.out.println("Resultado de la entrada: " + num);
        } catch (NumberFormatException e) {
            System.out.println("Error: Los argumentos deben ser números enteros.");
        }

	}

}
