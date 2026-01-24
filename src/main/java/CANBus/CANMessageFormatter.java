package CANBus;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;

public class CANMessageFormatter {

    final String canFrame;
    final int header;
    final int flags;
    final long data;

    final DeviceTypes devicesTypes;
    final Manufacturer manufacturer;
    final int apiClass;
    final int apiIndex;
    final int deviceNumber;

    public CANMessageFormatter(final byte[] rawCANFrame) {
        this.canFrame = new String(rawCANFrame, StandardCharsets.UTF_8);
        this.header = getHeaderFromCANFrame(canFrame);
        this.flags = getFlagsFromCANFrame(canFrame);
        this.data = getDataFromCANFrame(canFrame);

        devicesTypes = decodeDevicesTypes(header);
        manufacturer = decodeManufacturer(header);

        apiClass = 0;
        apiIndex = 0;
        deviceNumber = 0;
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

    private DeviceTypes decodeDevicesTypes(final int message) {
        return DeviceTypes.getFromIdentification((message >> 24) & DeviceTypes.BIT_MASK);
    }

    private Manufacturer decodeManufacturer(final int message) {
        return Manufacturer.getFromIdentification((message >> 16) & Manufacturer.BIT_MASK);
    }

    private long getDataFromCANFrame(final String canFrame) {
        return  Long.parseUnsignedLong(canFrame.substring(10, 26), 16);
    }

    private int getFlagsFromCANFrame(final String canFrame) {
        return Integer.parseUnsignedInt(canFrame.substring(8, 10), 16);
    }

    private int getHeaderFromCANFrame(final String canFrame) {
        return Integer.parseUnsignedInt(canFrame.substring(0, 8), 16);
    }

    public String getFullCANFrame() {
        return canFrame;
    }

}
