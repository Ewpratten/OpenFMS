package OpenFMS;

public class Config {

    // Internal configs
    public static class DriverStation {
        // Network ports
        public static int tcp_port = 1750;
        public static int udp_send_port = 1121;
        public static int udp_recv_port = 1160;

        // Timeouts
        public static int tcp_link_timeout_ms = 5000;
        public static int udp_link_timeout_ms = 1000;

        public static int max_tcp_msg_buffer = 4096;
    }

    public static class Arena {
        public static int loop_period_ms = 10;
        public static int packet_period_ms = 250;
    }

    public static class Timing {
        public static int auto_period_ms = 15000;
        public static int teleop_period_ms = 135000;
    }
}