package OpenFMS.field;

import OpenFMS.network.TCPConn;
import OpenFMS.model.Team;

public class AllianceStation {
    public DSConn dsconn;
    public TCPConn tcp_conn_thread;

    public boolean estop = false;
    public boolean bypass = false;

    public Team team;
}