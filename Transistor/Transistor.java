/**
 * Transistor :
 *     - It is main component of computer.
 *     - It acts like a switch.
 *     - Switch can be on or off.
 *     - So transistor has two states i.e. on or off.
 * 
 *      collector  >----\___/------>  emitter
 *                        |
 *                        ^
 *                       Base
 * 
 *     - when base has flowing current, then current will flow from collector to emitter
 */ 


package Transistor;

import Connectors.ConnectingWire;

// basic transistor with no voltage and current. only based on logic
public class Transistor {
    public static int transistor_count = 0;

    // there are three terminals in a transistor collector, base and emitter
    protected ConnectingWire collector;
    protected ConnectingWire base;
    protected ConnectingWire emitter;

    // initially all three terminals(wires) will don't have power
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

        // so basically what happens, current flows from collector to emitter when base has current flowing. else don't.
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