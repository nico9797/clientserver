import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class Client
{
    Socket mioSocket=null;

    int porta=6789;
    DataInputStream in;
    DataOutputStream out;
    BufferedReader tastiera;
    public void comunica(){
        try {
            System.out.println("messaggio da inviare al server: ");

            tastiera=new BufferedReader(new InputStreamReader(System.in));
            String messaggio=tastiera.readLine();
            System.out.println("invio: "+messaggio);

            out.writeBytes(messaggio+"\n");
            System.out.println("attesa risposta ");

            String ricevuta=in.readLine();
            System.out.println("risposta del del server ricevuta:"+ricevuta);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public Socket connetti(){
        try {
            System.out.println("provo a connettermi al server");
            Socket mioSocket =new Socket(InetAddress.getLocalHost(),porta);
            System.out.println("connesso");

            in=new DataInputStream(mioSocket.getInputStream());
       out=new DataOutputStream(mioSocket.getOutputStream());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
return mioSocket;
    }
    public static void main(String[] args) {

        JFrame frameClient=new JFrame("client");
        BorderLayout bl=new BorderLayout();
        JTextField txt=new JTextField("weeeee");
        frameClient.setVisible(true);
        frameClient.setSize(500,500);
        JPanel panel=new JPanel();
        frameClient.add(panel);
        panel.setBackground(Color.blue);
        panel.setLayout(new BorderLayout());
        panel.add(txt);


       Client c=new Client();
        c.connetti();
        c.comunica();
    }
}
