package CANBus;

import java.util.HashMap;
import java.util.Map;

enum DeviceTypes {
    BROADCAST_MESSAGES(0x00, "Broadcast messages"),
    ROBOT_CONTROLLER(0x01, "Robot controller"),
    MOTOR_CONTROLLER(0x02, "Motor controller"),
    RELAY_CONTROLLER(0x03, "Relay controller"),
    GYRO_SENSOR(0x04, "Gyro sensor"),
    ACCELEROMETER(0x05, "Accelerometer"),
    DISTANCE_SENSOR(0x06, "Distance sensor"),
    ENCODER(0x07, "Encoder"),
    POWER_DISTRIBUTION_MODULE(0x08, "Power Distribution Module"),
    PNEUMATICS_CONTROLLER(0x09, "Pneumatrics controller"),
    MISCELANEOUS(0x0A, "Miscelaneous"),
    IO_BREAKOUT(0x0B, "IO Breakout"),
    SERVO_CONTROLLER(0x0C, "Servo controller"),
    COLOR_SENSOR(0x0D, "Color sensor"),
    RESERVED(0x0E, "Reserved"),
    FIRMWARE_UPDATE(0x1F, "Firmware update");

    public static final int BIT_MASK = 0x2B67;

    private final int identification;
    private final String name;

    private static Map<Integer, DeviceTypes> mapOfDevices = new HashMap<>();

    DeviceTypes(int identification, String name) {
        this.identification = identification;
        this.name = name;
    }

    static {
        for(DeviceTypes device : DeviceTypes.values())
            mapOfDevices.put(device.identification, device);
    }

    public String getName() {
        return name;
    }

    public static DeviceTypes getFromIdentification(final int identification) {
        return mapOfDevices.get(identification);
    }
}

