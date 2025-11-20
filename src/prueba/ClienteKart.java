package prueba;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class ClienteKart {

	public static void main(String[] args) {
		final String IP_SERVIDOR = "127.0.0.1";
	    final int PUERTO_SERVIDOR = 666;
	    InetSocketAddress direccion = new InetSocketAddress(IP_SERVIDOR, PUERTO_SERVIDOR);

	    Socket socket = null;  

	    try {
	    	socket = new Socket(); 
	    	socket.connect(direccion);

	    	System.out.println("Conectado con el servidor.");

	            // Mostrar menú al usuario
	    	System.out.println("MENÚ ALMACÉN MARIO KART");
	        System.out.println("1) KILOS DE PLATANOS EN EL ALMACÉN");
	        System.out.println("2) KILOS DE CAPARAZONES EN EL ALMACÉN");
	        System.out.println("3) RECOGER UN KILO DE PLATANOS");
	        System.out.println("4) USAR UN KILO DE PLATANOS");
	        System.out.println("5) RECOGER UN KILO DE CAPARAZONES");
	        System.out.println("6) USAR UN KILO DE CAPARAZONES");
	        System.out.print("Elige una opción (1-6): ");

	        Scanner teclado = new Scanner(System.in);
	        String opcion = teclado.nextLine().trim();
	        teclado.close();

	            // Enviar la opción al servidor
	        OutputStream outputStream = socket.getOutputStream();
	        outputStream.write(opcion.getBytes());
	        outputStream.flush();

	            // Esperar la respuesta del servidor
	        InputStream inputStream = socket.getInputStream();
	        byte[] buffer = new byte[1024];
	        int bytesRead = inputStream.read(buffer);

            if (bytesRead != -1) {
                String respuestaServidor = new String(buffer, 0, bytesRead).trim();
                System.out.println("Respuesta del servidor: " + respuestaServidor);
            } else {
                System.out.println("No se ha recibido respuesta del servidor.");
            }

            socket.close();
            System.out.println("Conexión cerrada.");

        } catch (Exception e) {
            System.out.println("Error en el cliente: " + e.getMessage());
            try {
                if (socket != null) socket.close();  
            } catch (Exception ex) {
               
            }
        }

	}

}
