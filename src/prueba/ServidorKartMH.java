package prueba;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorKartMH {
	public static void main(String[] args) {
		int kilosPlatanos = 300;
		int kilosCaparazones = 150;
        System.out.println("APLICACIÓN DE SERVIDOR MULTITAREA");
        System.out.println("----------------------------------");
        try {
            ServerSocket servidor = new ServerSocket();
            InetSocketAddress direccion = new InetSocketAddress("192.168.0.124",666);
            servidor.bind(direccion);
            System.out.println("Servidor listo para aceptar solicitudes");
            System.out.println("Dirección IP: " + direccion.getAddress());
            while (true) {
                Socket enchufeAlCliente = servidor.accept();
                System.out.println("Comunicación entrante");
                new HiloCliente(enchufeAlCliente,kilosPlatanos,kilosCaparazones);
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

