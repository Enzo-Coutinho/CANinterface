package serial;
import com.fazecast.jSerialComm.*;

public class SerialCommunication {

    final int BAUDRATE;
    final SerialPort serialPort;

    SerialCommunication(int BAUDRATE, String comPort) {
        this.BAUDRATE = BAUDRATE;
        serialPort = SerialPort.getCommPort(comPort);
    }

    public void start() {
        serialPort.openPort();
        setModeToReadBlocking();
    }

    public void close() {
        serialPort.closePort();
    }

    public int receiveMessage() {
        byte[] readBuffer = new byte[1024];
        return serialPort.readBytes(readBuffer, readBuffer.length);
    }

    private void setModeToReadBlocking() {
        setPortMode(SerialPort.TIMEOUT_READ_BLOCKING);
    }

    private void setPortMode(final int mode) {
        serialPort.setComPortTimeouts(mode, 1000, 0);
    }
}
