/**
 * Logic gate :
 *      - It is just arrangement of transistors to achieve desired output.
 */


package logicGates;

import Connectors.Pins;
import Connectors.ConnectingWire;

public interface LogicGate {

    // connect power to gate
    void connectToPower(ConnectingWire power);

    // connect input pins to gate
    void connectToInputPins(Pins inputPins);

    // return output pins
    ConnectingWire getOutput();

    // just gate counter. NOTE: we don't need it.
    int totalGateCount();

}