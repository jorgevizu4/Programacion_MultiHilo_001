package prueba;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class HiloCliente implements Runnable{
	
	private Socket socket;
	private int kilosPlatanos;
	private int kilosCaparazones;
	
	public HiloCliente(Socket socket, int kilosPlatanos, int kilosCaparazones) {
        this.socket = socket;
        this.kilosPlatanos = kilosPlatanos;
        this.kilosCaparazones = kilosCaparazones;
    }

	// Esperar a que un cliente se conecte
    
@Override
	public void run() {
		try {	
	        // Recibir mensaje del cliente
	        InputStream inputStream = socket.getInputStream();
	        OutputStream outputStream = socket.getOutputStream();
	        
	        byte[] buffer = new byte[1024];
	        int bytesRead = inputStream.read(buffer);
	
	        if (bytesRead == -1) {
		        String mensajeCliente = new String(buffer, 0, bytesRead).trim();
		        System.out.println("Mensaje recibido del cliente: " + mensajeCliente);
		
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
		        		mensajeServidor = "Se ha usado 1 kg de plátanos. Te quedan: " + kilosPlatanos;
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
			        outputStream.write(mensajeServidor.getBytes());
			        outputStream.flush();
	        	}
	        // Enviar respuesta al cliente
	   
	        socket.close();
	        System.out.println("Conexión con el cliente cerrada.");
	
		} catch (Exception e) {
	    System.out.println("Error en el servidor: " + e.getMessage());
		}
	}
}
