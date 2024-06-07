package logicGates;

import Connectors.ConnectingWire;
import Connectors.Pins;

public interface LogicGate {

    void connectToPower(ConnectingWire power);

    void connectToPins(Pins inputPins);

    void connectToInputA(ConnectingWire input1);

    void connectToInputB(ConnectingWire input2);

    Pins getOutput();

    int totalGateCount();

}