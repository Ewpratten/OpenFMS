package OpenFMS.field;

import java.util.Map;
import java.util.HashMap;
import java.lang.Thread;

import OpenFMS.Config;
import OpenFMS.model.MatchState;

public class Arena {
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

    private void update() {

    }
    
    private void end() {
        System.out.println("[Arena] closing connections and exiting");
    }

}