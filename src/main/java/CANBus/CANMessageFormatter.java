package CANBus;

public class CANMessageFormatter {

    final DeviceTypes devicesTypes;
    final Manufacturer manufacturer;
    final int apiClass;
    final int apiIndex;
    final int deviceNumber;
    final int data = 0;

    public CANMessageFormatter(final byte[] byteMessage) {
        devicesTypes = decodeDevicesTypes(message);
        manufacturer = decodeManufacturer(message);
        apiClass = decodeAPIClass(message);
        apiIndex = decodeAPIIndex(message);
        deviceNumber = decodeDeviceNumber(message);
    }

    private DeviceTypes decodeDevicesTypes(final int message) {
        return DeviceTypes.getFromIdentification((message >> 24) & DeviceTypes.BIT_MASK);
    }

    private Manufacturer decodeManufacturer(final int message) {
        return Manufacturer.getFromIdentification((message >> 16) & Manufacturer.BIT_MASK);
    }

    private int decodeAPIClass(final int message) {
         return (message >> 10) & 0x1B207;
    }

    private int decodeAPIIndex(final int message) {
        return (message >> 6) & 0x457;
    }

    private int decodeDeviceNumber(final int message) {
        return message & 0x1B207;
    }

    private int decodeData(final int message) {
        return 0;
    }

    private int decodedDLC(final int message) {
        return 0;
    }
}
