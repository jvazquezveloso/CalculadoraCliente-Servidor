import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;

public class ServidorCalculadora {
    public static void main(String args[]) throws IOException {

        ServerSocket serverSocket = new ServerSocket(1000);
        Socket s = serverSocket.accept();

        //prepara los inputstream
        DataInputStream dis = new DataInputStream(s.getInputStream());
        DataOutputStream dos = new DataOutputStream(s.getOutputStream());

        while (true)
        {
            String input = dis.readUTF(); //espera la cuenta del cliente

            if(input.equals("cerrar"))
                break;

            System.out.println("Cuenta recibida: " + input);
            int result;

            StringTokenizer str = new StringTokenizer(input);

            //tokeniza el string, va pillando los operandos que son string y los pasa a int
            int operando1 = Integer.parseInt(str.nextToken());
            String operacion = str.nextToken();
            int operando2 = Integer.parseInt(str.nextToken());

            if (operacion.equals("+"))
            {
                result = operando1 + operando2;
            }

            else if (operacion.equals("-"))
            {
                result = operando1 - operando2;
            }
            else if (operacion.equals("*"))
            {
                result = operando1 * operando2;
            }
            else
            {
                result = operando1 / operando2;
            }
            System.out.println("Enviando resultado...");

            dos.writeUTF(Integer.toString(result)); //envia el resultado como string al cliente
        }
    }
}
