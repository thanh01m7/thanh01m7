import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.ByteBuffer;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UDPDateServer {
    private static final int PORT = 13;
    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(PORT);
            System.out.println("Server da duoc tao");
            while (true) {
                // Nhận dữ liệu từ client
                byte[] inputByte = new byte[60000];
                DatagramPacket inputPack = new DatagramPacket(inputByte, inputByte.length);
                socket.receive(inputPack);

                // Xử lý
                byte[] request = inputPack.getData();
                int luachon = convertByteArrayToInt(request);
                Date date = new Date();
                SimpleDateFormat sdf;
                String outputStr;
                byte[] outputByte; 
                DatagramPacket outputPack;
                switch (luachon) {
                    case 1:                        
                        sdf = new SimpleDateFormat("h:mm:ss a");
                        outputStr = sdf.format(date).toString();
                        
                        // Gửi dữ liệu cho client
                        outputByte = outputStr.getBytes();
                        outputPack = new DatagramPacket(outputByte, outputByte.length, inputPack.getAddress(), inputPack.getPort());
                        socket.send(outputPack);
                        break;
                    case 2: 
                        sdf = new SimpleDateFormat("dd/MM/yyyy");
                        outputStr = sdf.format(date).toString();
                        
                        // Gửi dữ liệu cho client
                        outputByte = outputStr.getBytes();
                        outputPack = new DatagramPacket(outputByte, outputByte.length, inputPack.getAddress(), inputPack.getPort());
                        socket.send(outputPack);
                        break;
                    case 3: 
                        sdf = new SimpleDateFormat("dd/MM/yyyy h:mm:ss a");
                        outputStr = sdf.format(date).toString();
                        
                        // Gửi dữ liệu cho client
                        outputByte = outputStr.getBytes();
                        outputPack = new DatagramPacket(outputByte, outputByte.length, inputPack.getAddress(), inputPack.getPort());
                        socket.send(outputPack);
                        break;
                    case 4:
                        outputStr = "Da thoat chuong trinh";
                        outputByte = outputStr.getBytes();
                        outputPack = new DatagramPacket(outputByte, outputByte.length, inputPack.getAddress(), inputPack.getPort());
                        socket.send(outputPack);
                        socket.close();
                        break;
                    default:
                        break;
                }                        
            }
        } catch (IOException ex) {
            System.out.println("Loi Server: " + ex.toString());
        }
    }

    public static int convertByteArrayToInt(byte[] bytes) {
        return ByteBuffer.wrap(bytes).getInt();
    }


}
