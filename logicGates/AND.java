package logicGates;

import Connectors.Pins;
import Connectors.ConnectingWire;
import Transistor.Transistor;

public class AND implements LogicGate{
    // to track total gates in circuit
    public static int gate_count = 0;


    // AND gate have two transistors
    Transistor transistor_A;
    Transistor transistor_B;

    // AND gate have two input pin and one output pin
    Pins input_pins;
    Pins output_pin;

    public AND() {
        ++gate_count;
        transistor_A = new Transistor();
        transistor_B = new Transistor();
        input_pins = new Pins(2);
        output_pin = new Pins();
    }


    // connect power to transistors
    @Override
    public void connectToPower(ConnectingWire power) {
        // both transistors connected in series
        transistor_A.connectToPower(power);
    }

    // connect input pins
    @Override
    public void connectToInputPins(Pins inputPins) {
        input_pins = inputPins;
    }

    // connect to input A
    public void connectToInputA(ConnectingWire input1) {
        input_pins.connectPinWire(1, input1);
    }

    // connect to input B
    public void connectToInputB(ConnectingWire input2) {
        input_pins.connectPinWire(2, input2);
    }

    // return output pins
    @Override
    public ConnectingWire getOutput() {
        connectTransistors();
        return output_pin.getPinWire();
    }

    // main gate logic
    private void connectTransistors() {
        // AND gate has two input which connected to each transistor's base terminal.
        transistor_A.connectToBase(input_pins.getPinWire(1));
        transistor_B.connectToBase(input_pins.getPinWire(2));

        // connect two transistors in series
        transistor_B.connectToPower(transistor_A.getEmitterStatus());

        output_pin.connectPinWire(transistor_B.getEmitterStatus());
    }

    // return gate count
    @Override
    public int totalGateCount() {
        return gate_count;
    }

}