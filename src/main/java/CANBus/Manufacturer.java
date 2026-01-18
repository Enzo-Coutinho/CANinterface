package CANBus;

public enum Manufacturer {
    BROADCAST(0, "Broadcast"),
    NI(1,"National Instrument"),
    LUMINARY_MICRO(2, "Luminary Micro"),
    DEKA(3, "DEKA"),
    CTRE(4, "CTR Electronics"),
    REV(5, "REV Robotics"),
    GRAPPLE(6, "Grapple"),
    MINDSENSORS(7, "MindSensors"),
    TEAM_USE(8, "Team Use"),
    KAUAI_LABS(9, "Kuai Labs"),
    COPPERFORGE(10, "Copperforge"),
    PLAYING_WITH_FUSION(11, "Playing With Fusion"),
    STUDICA(12, "Studica"),
    THE_THRIFTY_BOT(13, "The Thrifty Bot"),
    REDUX_ROBOTICS(14, "Redux Robotics"),
    ANDYMARK(15, "AndyMark"),
    VIVID_HOSTING(16, "VIVID HOSTING"),
    VERTOS_ROBOTICS(17, "Vertos Robotics"),
    SWYFT_ROBOTICS(18, "SWYFT Robotics"),
    LUMYN_LABS(19, "Lumyn Labs"),
    BRUSHLAND_LABS(20, "Brushland Labs"),
    RESERVED(21, "Reserved");

    final int identification;
    final String name;

    Manufacturer(int identification, String name) {
        this.identification = identification;
        this.name = name;
    }


}
