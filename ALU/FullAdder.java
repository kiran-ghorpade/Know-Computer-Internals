/**
 * 
 * Full Adder :
 *     - overcome disadvantage of halfadder
 *     - consider carry during addition of inputs
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

    /*
     * FullAdder have
     * - two AND gates
     * - two XOR gates
     * - a OR gate
     * 
     * - Three Input pins
     * - Two Output Pins
     */
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

    // connect power to gates
    public void connectToPower(ConnectingWire power) {
        and_gate_A.connectToPower(power);
        and_gate_B.connectToPower(power);
        or_gate.connectToPower(power);
        xor_gate_A.connectToPower(power);
        xor_gate_B.connectToPower(power);
    }

    // connect to input A
    public void connectToInputA(ConnectingWire input1) {
        input_pins.connectPinWire(1, input1);
    }

    // connect to input B
    public void connectToInputB(ConnectingWire input2) {
        input_pins.connectPinWire(2, input2);
    }

    // connect to input carry
    public void connectToInputCarry(ConnectingWire carry) {
        input_pins.connectPinWire(3, carry);
    }

    // return output pins
    public Pins getOutput() {
        connectTransistors();
        return output_pins;
    }

    // Main Logic
    private void connectTransistors() {
        // so here first we add inputs and then add result with carry

        // connect two inputs to XOR gate A (produce sum)
        xor_gate_A.connectToInputA(input_pins.getPinWire(1));
        xor_gate_A.connectToInputB(input_pins.getPinWire(2));
        
        // connect two inputs to AND gate A (produce carry)
        and_gate_A.connectToInputA(input_pins.getPinWire(1));
        and_gate_A.connectToInputB(input_pins.getPinWire(2));
        
        // connect carry-input and output of XOR gate A to XOR gate B 
        // (produce sum of sum of inputs + carry)
        xor_gate_B.connectToInputA(input_pins.getPinWire(3));
        xor_gate_B.connectToInputB(xor_gate_A.getOutput().getPinWire());
        
        // connect carry-input and output of XOR gate A to AND gate B
        // (produce carry from above addition)
        and_gate_B.connectToInputA(input_pins.getPinWire(3));
        and_gate_B.connectToInputB(xor_gate_A.getOutput().getPinWire());
        
        // connect output of AND gate A and AND gate B to OR Gate
        // produce common carry from both carry-outputs
        or_gate.connectToInputA(and_gate_A.getOutput().getPinWire());
        or_gate.connectToInputB(and_gate_B.getOutput().getPinWire());

        // set result to output pins
        // XOR gate B gives sum
        // OR gate gives carry
        output_pins.connectPinWire(1, xor_gate_B.getOutput().getPinWire());
        output_pins.connectPinWire(2, or_gate.getOutput().getPinWire());

    }

    public int totalGateCount() {
        return full_adder_count;
    }
}
