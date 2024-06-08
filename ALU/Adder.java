package ALU;

import Connectors.ConnectingWire;

public interface Adder {

    // connect power to gates
    void connectToPower(ConnectingWire power);

    // connect to input A
    void connectToInputA(ConnectingWire input1);

    // connect to input B
    void connectToInputB(ConnectingWire input2);

    // return sum
    ConnectingWire getSum();

    // return carry
    ConnectingWire getCarry();

}