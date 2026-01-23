package serial;
import com.fazecast.jSerialComm.*;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class SerialCommunication {

    final int BAUDRATE = 115200;
    final int DATA_BITS = 8;
    final int STOP_BITS = SerialPort.ONE_STOP_BIT;
    final int PARITY = SerialPort.NO_PARITY;

    final int TIMEOUT_TO_READ_MESSAGE_SECONDS = 0;
    final int TIMEOUT_TO_WRITE_MESSAGE_SECONDS  = 0;

    final int CAN_BUFFER_BYTES_LENGHT = 12;

    final SerialPort serialPort;

    public SerialCommunication(String comPort) {
        serialPort = SerialPort.getCommPort(comPort);
        serialPort.setComPortParameters(BAUDRATE, DATA_BITS, STOP_BITS, PARITY);
    }

    public void start() {
        serialPort.openPort();
        serialPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING,
                                        TIMEOUT_TO_READ_MESSAGE_SECONDS,
                                        TIMEOUT_TO_WRITE_MESSAGE_SECONDS);
    }

    public void close() {
        serialPort.closePort();
    }

    public byte[] receiveMessage() {
        byte[] readBuffer = new byte[CAN_BUFFER_BYTES_LENGHT];
        int bytesRead = serialPort.readBytes(readBuffer, readBuffer.length);
        return readBuffer;
    }
}
