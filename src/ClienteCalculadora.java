import java.io.DataInputStream;
        import java.io.DataOutputStream;
        import java.io.IOException;
        import java.net.InetAddress;
        import java.net.Socket;
        import java.util.Scanner;

public class ClienteCalculadora {

    public static void main(String[] args) throws IOException {

        InetAddress ip = InetAddress.getLocalHost();
        int port = 1000;

        Scanner sc = new Scanner(System.in);

        Socket socket = new Socket(ip, port);

        DataInputStream input = new DataInputStream(socket.getInputStream());
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());

        while (true)
        {
            System.out.print("Introduce la operación: ");
            String msg = sc.nextLine();

            if (msg.equals("cerrar"))
                break;

            output.writeUTF(msg);

            String str = input.readUTF(); //creo el string espero a que el servidor lo envie de vuelta
            System.out.println("Solución :" + str);
        }
    }
}