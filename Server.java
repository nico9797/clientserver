import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    ServerSocket server=null;
    Socket socketClient=null;

    int porta=6789;
    DataInputStream in;
    DataOutputStream out;
    public void comunica(){
        try {
            System.out.println("attendo messaggio");
            String letto=in.readLine();
            System.out.println("messaggio ricevuto: "+letto);
            String risposta=letto.toUpperCase();
            System.out.println("risposta: "+risposta);
            out.writeBytes(risposta+"\n");

            socketClient.close();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Socket attendi(){
        try {
            System.out.println("inizializzo server");
            server=new ServerSocket(porta);
            System.out.println("Server pronto in ascolto sulla porta"+porta);
            socketClient=server.accept();
            System.out.println("Connessione stabilita con client");

            server.close();


            in=new DataInputStream(socketClient.getInputStream());
            out=new DataOutputStream(socketClient.getOutputStream());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return socketClient;
    }
    public static void main(String[] args) {


        Server s=new Server();
        s.attendi();
        s.comunica();
    }
}
