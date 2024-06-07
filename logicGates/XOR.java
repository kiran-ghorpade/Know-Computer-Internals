package logicGates;

import Connectors.ConnectingWire;
import Connectors.Pins;

public class XOR {
    public static int gate_count = 0;

    private AND and_gate_A, and_gate_B;
    private OR or_gate_A;
    private NOT not_gate_A, not_gate_B;
    private Pins input_pins;
    private Pins output_pin;

    public XOR() {
        ++gate_count;
        and_gate_A = new AND();
        and_gate_B = new AND();
        not_gate_A = new NOT();
        not_gate_B = new NOT();
        or_gate_A = new OR();

        input_pins = new Pins(2);
        output_pin = new Pins();
    }

    public void connectToPower(ConnectingWire power) {
        and_gate_A.connectToPower(power);
        and_gate_B.connectToPower(power);
        not_gate_A.connectToPower(power);
        not_gate_B.connectToPower(power);
        or_gate_A.connectToPower(power);
    }

    public void connectToInputA(ConnectingWire input1) {
        input_pins.connectPin(1, input1);
    }

    public void connectToInputB(ConnectingWire input2) {
        input_pins.connectPin(2, input2);
    }

    public Pins getOutput() {
        connectTransistors();
        return output_pin;
    }

    private void connectTransistors() {
        and_gate_A.connectToInputA(input_pins.getPin(1));
        not_gate_B.connectToInput(input_pins.getPin(1));
        not_gate_A.connectToInput(input_pins.getPin(2));
        and_gate_B.connectToInputA(input_pins.getPin(2));
        
        and_gate_A.connectToInputB(not_gate_A.getOutput());
        and_gate_B.connectToInputB(not_gate_B.getOutput());

        or_gate_A.connectToInputA(and_gate_A.getOutput().getPin());
        or_gate_A.connectToInputB(and_gate_B.getOutput().getPin());

        output_pin.connectPin(or_gate_A.getOutput().getPin());
    }

    public int totalGateCount() {
        return gate_count;
    }
}
