package serial;
import com.fazecast.jSerialComm.*;
import java.nio.charset.StandardCharsets;

public class SerialCommunication {

    final int BAUDRATE = 115200;
    final int DATA_BITS = 8;
    final int STOP_BITS = SerialPort.ONE_STOP_BIT;
    final int PARITY = SerialPort.NO_PARITY;

    final int TIMEOUT_TO_READ_MESSAGE_SECONDS = 1000;
    final int TIMEOUT_TO_WRITE_MESSAGE_SECONDS  = 0;

    final int CAN_BUFFER_BINARY_BYTES_LENGHT = 14;
    final int CAN_BUFFER_BINARY_BYTES_START_PROTOCOL = 1;

    final int CAN_BUFFER_TEXT_BYTES_LENGHT = CAN_BUFFER_BINARY_BYTES_LENGHT * 2;
    final int CAN_BUFFER_TEXT_BYTES_START_PROTOCOL = CAN_BUFFER_BINARY_BYTES_START_PROTOCOL * 2;

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
        byte[] readStartProtocolBuffer = new byte[CAN_BUFFER_TEXT_BYTES_START_PROTOCOL];
        byte[] buffer = new byte[CAN_BUFFER_TEXT_BYTES_LENGHT];

        while(!getHexStringFromBytes(readStartProtocolBuffer).equals("0A")) {
            serialPort.readBytes(readStartProtocolBuffer, readStartProtocolBuffer.length);
        }
        serialPort.readBytes(buffer, buffer.length);

        byte[] canFrame = new byte[CAN_BUFFER_TEXT_BYTES_LENGHT - 2];

        System.arraycopy(buffer, 0, canFrame, 0, CAN_BUFFER_TEXT_BYTES_LENGHT - 2);

        return canFrame;
    }

    public String getHexStringFromBytes(byte[] bytes) {
        return new String(bytes, StandardCharsets.UTF_8);
    }
}
