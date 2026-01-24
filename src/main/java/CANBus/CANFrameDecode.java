package CANBus;

import java.nio.charset.StandardCharsets;

public class CANFrameDecode {

    final short HEX = 16;

    final String canFrame;

    final int header;
    final int flags;
    final long data;

    final DeviceTypes devicesTypes;
    final Manufacturer manufacturer;
    final int apiClass;
    final int apiIndex;
    final int deviceNumber;

    public CANFrameDecode(final byte[] rawCANFrame) {
        this.canFrame = new String(rawCANFrame, StandardCharsets.UTF_8);

        this.header = getHeaderFromCANFrame(canFrame);
        this.flags = getFlagsFromCANFrame(canFrame);
        this.data = getDataFromCANFrame(canFrame);

        devicesTypes = decodeDevicesTypes(header);
        manufacturer = decodeManufacturer(header);
        apiClass = decodeAPIClass(header);
        apiIndex = decodeAPIIndex(header);
        deviceNumber = decodeDeviceNumber(header);
    }

    public long getData() {
        return data;
    }

    public int getFlags() {
        return flags;
    }

    public int getHeader() {
        return header;
    }

    public int getDeviceNumber() {
        return deviceNumber;
    }

    public int getApiIndex() {
        return apiIndex;
    }

    public int getApiClass() {
        return apiClass;
    }

    public String getManufacturerName() {
        return manufacturer.getName();
    }

    public String getDeviceName() {
        return devicesTypes.getName();
    }

    private DeviceTypes decodeDevicesTypes(final int header) {
        return DeviceTypes.getFromIdentification((header >> 24) & DeviceTypes.BIT_MASK);
    }

    private Manufacturer decodeManufacturer(final int header) {
        return Manufacturer.getFromIdentification((header >> 16) & Manufacturer.BIT_MASK);
    }

    private int decodeAPIClass(final int header) {
        return (header >> 10) & 0x3F;
    }

    private int decodeAPIIndex(final int header) {
        return (header >> 6) & 0xF;
    }

    private int decodeDeviceNumber(final int header) {
        return header & 0x3F;
    }

    private long getDataFromCANFrame(final String canFrame) {
        return  Long.parseUnsignedLong(canFrame.substring(10, 26), HEX);
    }

    private int getFlagsFromCANFrame(final String canFrame) {
        return Integer.parseUnsignedInt(canFrame.substring(8, 10), HEX);
    }

    private int getHeaderFromCANFrame(final String canFrame) {
        return Integer.parseUnsignedInt(canFrame.substring(0, 8), HEX);
    }

    public String getFullCANFrame() {
        return canFrame;
    }

}
