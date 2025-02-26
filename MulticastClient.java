import java.net.*;

public class MulticastClient {
    private static final String MULTICAST_GROUP = "230.0.0.1"; // Indirizzo multicast
    private static final int PORT = 4446;

    public static void main(String[] args) {
        try (MulticastSocket socket = new MulticastSocket(PORT)) {
            InetAddress group = InetAddress.getByName(MULTICAST_GROUP);
            NetworkInterface netIf = NetworkInterface.getByInetAddress(InetAddress.getLocalHost());

            if (netIf == null) {
                System.err.println("Nessuna interfaccia di rete trovata!");
                return;
            }

            socket.joinGroup(new InetSocketAddress(group, PORT), netIf);

            System.out.println("Client: In attesa di messaggi di allerta...");

            byte[] buffer = new byte[256];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            socket.receive(packet);

            String receivedMessage = new String(packet.getData(), 0, packet.getLength());
            System.out.println("🔔 Messaggio ricevuto: " + receivedMessage);

            socket.leaveGroup(new InetSocketAddress(group, PORT), netIf);

            System.out.println("Client: Uscito dal gruppo.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
