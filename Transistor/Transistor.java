package Transistor;

import Connectors.ConnectingWire;

// basic transistor with no voltage and current. only based on logic
public class Transistor {
    public static int transistor_count = 0;

    protected ConnectingWire collector;
    protected ConnectingWire base;
    protected ConnectingWire emitter;

    // initially all three nodes will don't have power
    public Transistor() {
        ++transistor_count;
       collector = new ConnectingWire();
       base = new ConnectingWire();
       emitter = new ConnectingWire();
    
       collector.setCurrentStatus(ConnectingWire.CURRENT_STOPPED);
       base.setCurrentStatus(ConnectingWire.CURRENT_STOPPED);
       emitter.setCurrentStatus(ConnectingWire.CURRENT_STOPPED);
    }

    // set collector to power
    public void connectToPower(ConnectingWire power) {
        collector.setCurrentStatus(power.getCurrentStatus());
    }

    // set base to power
    public void connectToBase(ConnectingWire input) {
        base.setCurrentStatus(input.getCurrentStatus());
    }

    // set emitter based on base status
    protected void changeEmitterCurrentStatus() {
        if (base.getCurrentStatus() == ConnectingWire.CURRENT_FLOWING) {
            emitter.setCurrentStatus(collector.getCurrentStatus());

            return;
        }

        emitter.setCurrentStatus(ConnectingWire.CURRENT_STOPPED);
    }

    // return emitter power
    public ConnectingWire getEmitterStatus() {
        changeEmitterCurrentStatus();
        return emitter;
    }

    // return total wires used till now
    public int getTotalWiresCount() {
        return transistor_count;
    }
}