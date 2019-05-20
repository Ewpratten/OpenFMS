package OpenFMS.field;

public class DSConn {
    // Team info
    public int team_id = 0;
    public AllianceStation alliance_station;

    // Networking info
    public class Status {
        public boolean auto, enabled, estop, ds_link, robot_link;
    }

    public double battery_voltage = 0.0;

}