package ALU;

import Connectors.ConnectingWire;
import Connectors.Pins;
import logicGates.AND;
import logicGates.XOR;

public class HalfAdder {
    public static int half_adder_count = 0;

    private AND and_gate;
    private XOR xor_gate;
    private Pins input_pins;
    private Pins output_pins;

    public HalfAdder() {
        ++half_adder_count;
        and_gate = new AND();
        xor_gate = new XOR();
        input_pins = new Pins(2);
        output_pins = new Pins(2);
    }

    public void connectToPower(ConnectingWire power) {
        and_gate.connectToPower(power);
        xor_gate.connectToPower(power);
    }

    public void connectToInputA(ConnectingWire input1) {
        input_pins.connectPin(1, input1);
    }

    public void connectToInputB(ConnectingWire input2) {
        input_pins.connectPin(2, input2);
    }

    public Pins getOutput() {
        connectTransistors();
        return output_pins;
    }

    private void connectTransistors() {
        xor_gate.connectToInputA(input_pins.getPin(1));
        xor_gate.connectToInputB(input_pins.getPin(2));
        
        and_gate.connectToInputA(input_pins.getPin(1));
        and_gate.connectToInputB(input_pins.getPin(2));

        output_pins.connectPin(1, xor_gate.getOutput().getPin());
        output_pins.connectPin(2, and_gate.getOutput().getPin());

    }
    
    public int totalGateCount() {
        return half_adder_count;
    }
}
