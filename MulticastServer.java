import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class MulticastServer {
    private static final String MULTICAST_GROUP = "230.0.0.1"; // Indirizzo di gruppo multicast
    private static final int PORT = 4446;

    public static void main(String[] args) {
        try (DatagramSocket socket = new DatagramSocket()) {
            InetAddress group = InetAddress.getByName(MULTICAST_GROUP);
            String message = "Allerta Protezione Civile! Rischio imminente!";

            byte[] buffer = message.getBytes();
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, group, PORT);

            System.out.println("Server: Invio messaggio di allerta...");
            socket.send(packet);
            System.out.println("Messaggio inviato: " + message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

