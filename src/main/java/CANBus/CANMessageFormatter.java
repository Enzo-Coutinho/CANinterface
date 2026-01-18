package CANBus;

public class CANMessageFormatter {

    final DeviceTypes devicesTypes;
    final Manufacturer manufacturer;

    public CANMessageFormatter(final int message) {
        devicesTypes = decodeDevicesTypes(message);
        manufacturer = decodeManufacturer(message);
    }

    private DeviceTypes decodeDevicesTypes(final int message) {
        return DeviceTypes.getFromIdentification((message >> 24) & DeviceTypes.BIT_MASK);
    }

    private Manufacturer decodeManufacturer(final int message) {
        return Manufacturer.getFromIdentification((message >> 16) & Manufacturer.BIT_MASK);
    }
}
