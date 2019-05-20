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
    }
}