package logicGates;

import Connectors.ConnectingWire;
import Connectors.Pins;
import Transistor.Transistor;

public class AND implements LogicGate{
    // to track total gates in circuit
    public static int gate_count = 0;

    Transistor transistor_A;
    Transistor transistor_B;
    Pins input_pins;
    Pins output_pin;

    public AND() {
        ++gate_count;
        transistor_A = new Transistor();
        transistor_B = new Transistor();
        input_pins = new Pins(2);
        output_pin = new Pins();
    }

    @Override
    public void connectToPower(ConnectingWire power) {
        transistor_A.connectToPower(power);
    }

    @Override
    public void connectToPins(Pins inputPins) {
        input_pins = inputPins;
    }

    @Override
    public void connectToInputA(ConnectingWire input1) {
        input_pins.connectPin(1, input1);
    }

    @Override
    public void connectToInputB(ConnectingWire input2) {
        input_pins.connectPin(2, input2);
    }

    @Override
    public Pins getOutput() {
        connectTransistors();
        return output_pin;
    }

    private void connectTransistors() {
        transistor_A.connectToBase(input_pins.getPin(1));
        transistor_B.connectToBase(input_pins.getPin(2));

        // connect two transistors in series
        transistor_B.connectToPower(transistor_A.getEmitterStatus());

        output_pin.connectPin(transistor_B.getEmitterStatus());
    }

    @Override
    public int totalGateCount() {
        return gate_count;
    }

}