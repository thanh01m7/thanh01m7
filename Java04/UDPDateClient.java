import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.util.Scanner;

public class UDPDateClient {
    private static final int PORT = 13;
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress serveAddress = InetAddress.getByName("127.0.0.1");
            Scanner sc = new Scanner(System.in);
            while (true) {
                //Gửi dữ liệu qua server
                
                System.out.println("1. Time");
                System.out.println("2. Date");
                System.out.println("3. Date & Time");
                System.out.println("4. Exit");
                System.out.print("Nhap lua chon: ");
                int request = sc.nextInt();
                
                byte[] outputByte = convertIntToByteArray(request);
                // 4 đối số: mảng byte, length, address, port 
                DatagramPacket outputPack = new DatagramPacket(outputByte, outputByte.length, serveAddress, PORT);
                socket.send(outputPack);

                //Nhận dữ liệu từ server
                byte[] inputByte = new byte[60000];
                // 2 đối số: mảng byte, length
                DatagramPacket inputPack = new DatagramPacket(inputByte, inputByte.length);
                socket.receive(inputPack);

                // 3 đối số: mảng byte, 0, length
                String inputStr = new String(inputPack.getData(), 0, inputPack.getLength());
                System.out.println("Ngay gio he thong: " + inputStr);
            }
        } catch (IOException ex) {
            System.out.println("Loi Client: " + ex.toString());
        }
    }

    public static byte[] convertIntToByteArray(int value) {
        return  ByteBuffer.allocate(4).putInt(value).array();
    }
}
