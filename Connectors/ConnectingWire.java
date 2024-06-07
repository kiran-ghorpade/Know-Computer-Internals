package Connectors;

public class ConnectingWire {

    // CURRENT_FLOWING represent 5V
    public static final boolean CURRENT_FLOWING = true;
    // CURRENT_STOPPED represnt 0V
    public static final boolean CURRENT_STOPPED = false;

    public static int wire_count = 0; // keep track of wires
    private boolean current_status; // current status in wire

    public ConnectingWire() {
        wire_count++;
        current_status = ConnectingWire.CURRENT_STOPPED;
    }

    // set current status in wire
    public void setCurrentStatus(boolean status) {
        current_status = status;
    }

    // return current status in wire
    public boolean getCurrentStatus() {
        return current_status;
    }

    // return total wires used till now
    public int getTotalWiresCount() {
        return wire_count;
    }
}
