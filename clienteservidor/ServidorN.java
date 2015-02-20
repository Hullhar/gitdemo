/**
 * Esta clase lo que va a hacer es aceptar el numero de conexiones establecidas como argumento 
 * conexiones de forma simultánea
 * @author Jose García
 *
 */
import java.net.*;
import java.io.*;
public class ServidorN {
	public static void main(String[] args) {
        if ( args.length != 1 ){
            System.out.println("Uso: java Servidor #numeroDePeticionesQueQueremosAceptar");
        } else {
            int nPeticiones = 1; // Por defecto 0 peticiones
            String lastConection =""; //String para almacenar la informacion de la ultima conexion
            try {
                //Intentamos convertir el String a entero. Si no puede lanzamos excepcion, si puede
                //y es negativo, terminamos el programa
                nPeticiones = Integer.parseInt(args[0]);
                if (nPeticiones > 0 ){
                    /*Apertura del socket del servidor*/
                    ServerSocket socketServidor = new ServerSocket(30003);
                    /*Aceptamos 3 conexiones*/
                    for (int peticion = 1; peticion <= nPeticiones; peticion++){
                        /*Se bloquea el servidor a al espera de la peticion
                         * del siguiente cliente*/
                        Socket socketCliente = socketServidor.accept();
                        /*Envía un mensaje al cliente. Recordemos que se hace con un 
                         * flujo de salida que obtenemos a partir de un socket.*/
                        lastConection =" cliente: "+socketCliente.getInetAddress().getHostName()+" con IP: "+socketCliente.getInetAddress().getHostAddress() +" Desde el puerto: "+socketCliente.getPort();
                        System.out.println(lastConection);
                        OutputStream flujoSalida = socketCliente.getOutputStream();
                        DataOutputStream flujoDatosSalida = new DataOutputStream(flujoSalida);
                        flujoDatosSalida.writeUTF("Aceptada petición "+ peticion + " del cliente: "+socketCliente.getInetAddress().getHostName()+" con IP: "+socketCliente.getInetAddress().getHostAddress() +" Desde el puerto: "+socketCliente.getPort());
                        if (peticion == nPeticiones){
                            lastConection =" cliente: "+socketCliente.getInetAddress().getHostName()+" con IP: "+socketCliente.getInetAddress().getHostAddress() +" Desde el puerto: "+socketCliente.getPort();
                        }
                        /*Una vez que escribimos la información en el socket, cerramos el socket
                         * del cliente*/
                        socketCliente.close();
                        /*Finaliza la comunicación*/
                    }//for peticion
                    /*Cerramos el socket de servidor*/
                    socketServidor.close();
                    System.out.println("Ultima conexion");
                    System.out.println(lastConection);
                } else {
                    System.out.println("Para aceptar 0 conexiones, o negativas conexoines. Me enfado y no ejecuto ");
                }
            }catch (NumberFormatException nfe){
                System.out.println("Hasta donde yo conozco "+args[0]+" no es un numero entero. Me enfado y no ejecuto");

            } catch (Exception e){
                System.out.println("Excepcion: "+e.getMessage());
            }//catch
        }
		System.out.println("#SERVIDOR 3# Fin del programa");
	}//main
}//Servidor3
