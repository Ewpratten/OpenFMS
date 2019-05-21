package OpenFMS.field;

import java.util.Map;
import java.util.HashMap;
import java.lang.Thread;

import OpenFMS.Config;
import OpenFMS.model.MatchState;

public class Arena extends Thread{
    // Match abore status
    boolean match_aborted = false;

    // For keeping track of control data
    double last_packet_time = 0.0;

    // Real-time scores
    int blue_rt_score = 0;
    int red_rt_score = 0;

    // times
    double match_start_time = 0.0;
    double match_time = 0.0;
    double last_match_time = 0.0;

    // Match states
    MatchState match_state;
    MatchState last_match_state;

    // Webserver data
    String current_sound = "";

    // Alliance stations
    Map<String, AllianceStation> alliance_stations = new HashMap<String, AllianceStation>();

    public Arena() {

        // Set up alliance stations
        alliance_stations.put("R1", new AllianceStation());
        alliance_stations.put("R2", new AllianceStation());
        alliance_stations.put("R3", new AllianceStation());

        alliance_stations.put("B1", new AllianceStation());
        alliance_stations.put("B2", new AllianceStation());
        alliance_stations.put("B3", new AllianceStation());

        // Set current state
        match_state = MatchState.pre;
        last_match_state = MatchState.pre;

    }

    public void run() {
        // Start new connection listener

        // Start data listener

        // Main update loop
        System.out.println("[Arena] All systems ready. Starting field");
        while (true) {

            // Update, sleep, and exit if intrrupted
            try {

                update();
                Thread.sleep(Config.Arena.loop_period_ms);

            } catch (InterruptedException e) {
                System.out.println("[Arena] updater interrupted. Shutting down");
                end();
                return;
            }
        }
    }
    
    private double getMatchTime() {
        if (match_state == MatchState.pre || match_state == MatchState.start) {
            return 0.0;
        }

        return (System.currentTimeMillis() - match_start_time);
    }

    private void sendDSPacket(boolean auto, boolean enabled) {

    }

    private void update() {
        boolean auto = false;
        boolean enabled = false;
        boolean send_packet = false;
        double match_time_ms = getMatchTime();

        if (match_state == MatchState.pre) {
            auto = true;
            enabled = false;

        } else if (match_state == MatchState.start){
            this.match_start_time = System.currentTimeMillis();
            this.last_match_time = -1;

            auto = true;
            enabled = true;
            send_packet = true;

            System.out.println("[Game] Starting match");

            this.match_state = MatchState.auto;
            this.current_sound = "auto";

        } else if (match_state == MatchState.auto) {
            auto = true;
            enabled = true;

            if (match_time_ms >= Config.Timing.auto_period_ms) {
                auto = false;
                enabled = true;
                send_packet = true;

                this.current_sound = "teleop";

                System.out.println("[Game] Switching mode to teleop");

                this.match_state = MatchState.teleop;
            }
            
        } else if (match_state == MatchState.teleop) {
            auto = false;
            enabled = true;

            if (match_time_ms > Config.Timing.auto_period_ms + Config.Timing.teleop_period_ms) {
                auto = false;
                enabled = false;
                send_packet = true;

                this.match_state = MatchState.pre;

                System.out.println("[Game] Match finished");

                this.current_sound = "matchend";
            }

        }
        
        // TODO: notify displays ws

        this.last_match_time = match_time_ms;
        this.last_match_state = match_state;

        if (send_packet || System.currentTimeMillis() - last_packet_time >= Config.Arena.packet_period_ms) {
            sendDSPacket(auto, enabled);
        }

    }
    
    private void end() {
        System.out.println("[Arena] closing connections and exiting");
    }

}