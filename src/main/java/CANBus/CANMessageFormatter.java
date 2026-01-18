package CANBus;

public class CANMessageFormatter {

    final DeviceTypes devicesTypes;
    final Manufacturer manufacturer;

    public CANMessageFormatter(final int message) {
        devicesTypes = decodeDevicesTypes(message);
    }

    private DeviceTypes decodeDevicesTypes(final int message) {
        return DeviceTypes.getFromIdentification((message >> 24) & DeviceTypes.BIT_MASK);
    }

    private Manufacturer decodeManufacutrer(final int message) {

    }
}
