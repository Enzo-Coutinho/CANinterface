package CANBus;

public class CANMessageFormatter {

    final int timestamp;

    final DeviceTypes devicesTypes;
    final Manufacturer manufacturer;
    final boolean isRemoteFrame;
    final boolean isErrorFrame;
    final boolean b;
    final boolean iErrorFrame;
    final int data;

    public CANMessageFormatter(final int message) {
        this.timestamp = getTimestampFromFullMessage(message);
        final int header = getHeaderFromFullMessage(message);
        devicesTypes = decodeDevicesTypes(header);
        manufacturer = decodeManufacturer(header);
    }

    private int getHeaderFromFullMessage(final int message) {
        return 0;
    }

    private int getTimestampFromFullMessage(final int message) {
        return 0;
    }



    private DeviceTypes decodeDevicesTypes(final int message) {
        return DeviceTypes.getFromIdentification((message >> 24) & DeviceTypes.BIT_MASK);
    }

    private Manufacturer decodeManufacturer(final int message) {
        return Manufacturer.getFromIdentification((message >> 16) & Manufacturer.BIT_MASK);
    }
}
