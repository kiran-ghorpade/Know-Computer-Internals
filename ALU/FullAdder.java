/**
 * 
 * 
 *          X ------|-------------------------------|     |
 *                  |                               |[AND]|-------
 *               |--]-------------------------------|     |
 *               |  |                 
 *               |  |-----             |--------|     |
 *          Y ---|  |                  |        |[AND]|-------
 *               |  |                  |  |-----|     |
 *               |  |                  |  |
 *               |  |--------|     |   |  |      
 *               |           |[XOR]|---|  |       
 *               |-----------|     |   |          
 *                                     |-----|     |
 *                                           |[XOR]|
 *      Carry -------------------------------|     |
 * 
 */


package ALU;

import Connectors.ConnectingWire;
import Connectors.Pins;
import logicGates.AND;
import logicGates.OR;
import logicGates.XOR;

public class FullAdder {
    public static int full_adder_count = 0;

    private AND and_gate_A;
    private AND and_gate_B;
    private OR or_gate;
    private XOR xor_gate_A;
    private XOR xor_gate_B;
    private Pins input_pins;
    private Pins output_pins;

    public FullAdder() {
        ++full_adder_count;
        and_gate_A = new AND();
        and_gate_B = new AND();
        or_gate = new OR();
        xor_gate_A = new XOR();
        xor_gate_B = new XOR();

        // Full adder has 3 input pins : x , y , carry
        input_pins = new Pins(3);
        // Full adder has 2 output pins : sum, carry
        output_pins = new Pins(2);
    }

    public void connectToPower(ConnectingWire power) {
        and_gate_A.connectToPower(power);
        and_gate_B.connectToPower(power);
        or_gate.connectToPower(power);
        xor_gate_A.connectToPower(power);
        xor_gate_B.connectToPower(power);
    }

    public void connectToInputA(ConnectingWire input1) {
        input_pins.connectPin(1, input1);
    }

    public void connectToInputB(ConnectingWire input2) {
        input_pins.connectPin(2, input2);
    }

    public void connectToInputCarry(ConnectingWire carry){
        input_pins.connectPin(3, carry);
    }

    public Pins getOutput() {
        connectTransistors();
        return output_pins;
    }

    private void connectTransistors() {
        // connect input1 to xor gate pin1
        xor_gate_A.connectToInputA(input_pins.getPin(1));
        // connect input2 to xor gate pin1
        xor_gate_A.connectToInputB(input_pins.getPin(2));

        and_gate_A.connectToInputA(input_pins.getPin(1));
        and_gate_A.connectToInputB(input_pins.getPin(2));

        output_pins.connectPin(1, xor_gate_A.getOutput().getPin());
        output_pins.connectPin(2, and_gate_A.getOutput().getPin());

    }

    public int totalGateCount() {
        return full_adder_count;
    }
}
