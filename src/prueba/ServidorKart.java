package prueba;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorKart {

	public static void main(String[] args) {
		final int PUERTO = 666;
        int kilosPlatanos = 300;
        int kilosCaparazones = 150;

        // Dirección donde se va a quedar escuchando el servidor
        InetSocketAddress direccion = new InetSocketAddress(PUERTO);

        try (ServerSocket serverSocket = new ServerSocket()) {
            // Asociamos el ServerSocket a esa dirección/puerto
            serverSocket.bind(direccion);
            System.out.println("Servidor escuchando en el puerto " + PUERTO + "...");

            // Esperar a que un cliente se conecte
            try (Socket clientSocket = serverSocket.accept()) {
                System.out.println("Cliente conectado.");

                // Recibir mensaje del cliente
                InputStream inputStream = clientSocket.getInputStream();
                byte[] buffer = new byte[1024];
                int bytesRead = inputStream.read(buffer);

                if (bytesRead == -1) {
                    System.out.println("No se ha recibido ningún dato del cliente.");
                    return; // termina el main
                }

                String mensajeCliente = new String(buffer, 0, bytesRead).trim();
                System.out.println("Mensaje recibido del cliente: " + mensajeCliente);

                // Preparar respuesta
                String mensajeServidor;
                
                switch(mensajeCliente) {
                	case "1":
                		mensajeServidor = "Kilos de plátanos disponibles: " + kilosPlatanos;
                		break;
                	case "2":
                		mensajeServidor = "Kilos de caparazones disponibles: " + kilosCaparazones;
                		break;
                	case "3":
                		kilosPlatanos++;
                		mensajeServidor = "Se ha recogido un 1kg de plátanos. Te quedan: " + kilosPlatanos;
                		break;
                	case "4":
                		kilosPlatanos--;
                		mensajeServidor = "Se ha usado 1 kg de plátanos. Te quedan: " +kilosPlatanos;
                		break;
                	case "5":
                		kilosCaparazones++;
                		mensajeServidor = "Se ha recogido 1 kg de caparazones. Te quedan: " + kilosCaparazones;
                		break;
                	case "6":
                		kilosCaparazones--;
                		mensajeServidor = "Se ha usado 1 kg de caparazones. Te quedan: " + kilosCaparazones;
                		break;
                	default:
                		mensajeServidor = "Opción no válida";
                		
                }
                // Enviar respuesta al cliente
                OutputStream outputStream = clientSocket.getOutputStream();
                outputStream.write(mensajeServidor.getBytes());
                outputStream.flush();

                System.out.println("Mensaje enviado al cliente: " + mensajeServidor);
                System.out.println("Conexión con el cliente cerrada.");
            }

            System.out.println("Servidor cerrado (fin del programa).");
        } catch (Exception e) {
            System.out.println("Error en el servidor: " + e.getMessage());
        }

	}

}
