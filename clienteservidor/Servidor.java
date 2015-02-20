import java.net.*;
import java.io.*;

public class Servidor {

	public static void main(String[] args) {
        String data ="";
		try {
			/*El servidor se pone al a escucha por el puerto asignado*/
			ServerSocket socketServidor = new ServerSocket(30003);
			/*En el momento en el qu recibe la conexión, abre un nuevo 
			 * canal para la comunicación con el cliente*/
			Socket socketCliente = socketServidor.accept();
			/*El envío de informaciion se maneja como un descriptor 
			 * de archivo*/
			OutputStream flujoSalida = socketCliente.getOutputStream();
			DataOutputStream flujoDatosSalida = new DataOutputStream(flujoSalida);
			flujoDatosSalida.writeUTF("#SERVIDOR# ha aceptado la conexión");
			/*Ahora cerramos la comunicación con el cliente*/
			socketCliente.close();
			/*Cerramos la escucha del servidor, esto es ideal si nuestra aplicacion * no necesita estar siempre esperando datos*/
			socketServidor.close();
		} catch (Exception e){
			System.out.println("Excepcion: "+e.getMessage());
		}//catch
		System.out.println("#SERVIDOR# fin del programa");
	}//main
}//Servidor
