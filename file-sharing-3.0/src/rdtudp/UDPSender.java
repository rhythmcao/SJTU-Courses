package rdtudp;

/**
 * UDPSender: An unreliable data sender.
 */
import java.io.*;
import java.util.*;
import java.net.*;

/**
 * UDPSender
 *
 * This class represents the network channel that deliver packets unreliably.
 * This implementation ensures that data, if delivered, are in order. Packets,
 * however, can be lost or corrupted with a certain probability given determined
 * by the member P_DROP and P_CORRUPT.
 *
 * UDPSender is SIMULATED over a UDP connection (to ensure in order delivery)
 * with a manual, random, packet drop and packet corruption.
 */
public class UDPSender {

	Socket s;
	ObjectInputStream ois;
	ObjectOutputStream oos;
	Random random;

	// Set the packet loss and corruption probability.
	static double P_DROP = 0.01;
	static double P_CORRUPT = 0.05;

	UDPSender(String hostname, int port) throws IOException {
		// Connect to TCP server at the given hostname and port.
		s = new Socket(hostname, port);
		oos = new ObjectOutputStream(s.getOutputStream());
		ois = new ObjectInputStream(s.getInputStream());
		// Initialize random number generator.
		random = new Random();
	}

	/**
	 * Given a data packet from RDTSender, this method sends the packet to the
	 * receiver by writing it into the object output stream.
	 */
	synchronized void send(DataPacket p) throws IOException {
		System.out.println("S: send " + p.seq);
		oos.writeObject(p);
		oos.flush();
	}

	/**
	 * Reads an acknowledge packet from the socket and returns the packet.
	 *
	 * Packets read can be randomly ignored to simulate packet drops, or
	 * randomly marked as corrupted.
	 */
	AckPacket recv() throws IOException, ClassNotFoundException {
		Object obj = ois.readObject();
		while (random.nextDouble() < P_DROP) {
			System.out.println("S: packet drop");
			// Ignore the lost packet
			obj = ois.readObject();
		}
		AckPacket ack = (AckPacket) obj;
		// randomly corrupt the received packet.
		if (random.nextDouble() < P_CORRUPT) {
			System.out.println("S: corrupt ACK " + ack.ack);
			// create a new corrupted ACK.
			ack = new AckPacket(random.nextInt(2));
			ack.isCorrupted = true;
		} 
		System.out.println("S: recv ACK " + ack.ack);
		return ack;
	}

	void close() throws IOException {
		s.close();
	}
}
