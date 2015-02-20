/** Programa que implementa un cliente que se conecta a un servidor a partir
 * del nombre de la maquina en que se encuentra el servidor y el numero de
 * puerto por el que esta escuchando dicho servidor (en este caso el 30003).
 * Una vez conectado, lee una cadena de caracteres que le proporciona el 
 * servidor y la muestra en la salida estandar.
 * Recibira por argumento la ip a la que quiere conectarse
 * @author Jose Garcia
 * @version 1.0
 */
import java.io.*;
import java.net.*;

public class Cliente {
	public static void main(String[] args) {
        if (args.length != 1){
            System.out.println("Uso: java Cliente ipServidor|HostnameServidor");
        } else {
            try{
                /* Se crea el socket que se utilizara para la comunicacion
                 * con el servidor. */
                Socket socket = new Socket(args[0],30003);
                /* Se asocia el socket a un objeto InputStream, dado que la
                 * informacion fluira del servidor al cliente. Este, a su 
                 * vez, se asocia a un objeto DataInputStream para poder
                 * utilizar la informacion enviada por el servidor como si
                 * fuera un archivo. */
                InputStream flujoEntrada = socket.getInputStream();
                DataInputStream flujoDatosEntrada = new DataInputStream(flujoEntrada);
                /* Se muestra por pantalla el mensaje recibido. */
                System.out.println("#Cliente# Lee: " + flujoDatosEntrada.readUTF());
                /* El cliente finaliza la comunicacion cerrando el socket. */
                socket.close();
            }catch(UnknownHostException uhe){
                System.out.println("No ha sido posible conectarse al host .");
            }catch(SocketException se){
                System.out.println("No ha sido posible conectarse al host en el puerto remoto.");
            }catch(IOException ioe){
                System.out.println("Error de Entrada/Salida.");
            }// catch
        }//else
		System.out.println("#Cliente# Fin del programa.");
	}// main()
}// Clase Cliente
