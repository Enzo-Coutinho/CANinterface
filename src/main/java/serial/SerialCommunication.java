package serial;
import com.fazecast.jSerialComm.*;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

public class SerialCommunication {

    final int BAUDRATE = 115200;
    final int DATA_BITS = 8;
    final int STOP_BITS = SerialPort.ONE_STOP_BIT;
    final int PARITY = SerialPort.NO_PARITY;

    final int CAN_BUFFER_BYTES_LENGHT = 1024;

    final SerialPort serialPort;

    public SerialCommunication(String comPort) {
        serialPort = SerialPort.getCommPort(comPort);
        serialPort.setComPortParameters(BAUDRATE, DATA_BITS, STOP_BITS, PARITY);
    }

    public void start() {
        serialPort.openPort();
        serialPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING, 1000, 0);
    }

    public void close() {
        serialPort.closePort();
    }

    public int receiveMessage() {
        byte[] readBuffer = new byte[CAN_BUFFER_BYTES_LENGHT];
        serialPort.readBytes(readBuffer, readBuffer.length);
        return ByteBuffer.wrap(readBuffer).order(ByteOrder.LITTLE_ENDIAN).getInt();
    }
}
