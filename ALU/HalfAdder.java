/**
 * HalfAdder :
 *   - used for binary addition.
 *   - it doesn't consider carry during addition so known as half adder.
 */

package ALU;

import Connectors.Pins;
import Connectors.ConnectingWire;
import logicGates.AND;
import logicGates.XOR;

public class HalfAdder implements Adder {
    public static int half_adder_count = 0;

    // HalfAdder have a XOR gate (for sum) and a AND gate ( for carry)
    // HalfAdder has two input pins and two output pins (sum and carry)
    private AND and_gate;
    private XOR xor_gate;
    private Pins input_pins;
    private Pins output_pins;

    public HalfAdder() {
        ++half_adder_count;
        and_gate = new AND();
        xor_gate = new XOR();

        // Half adder has 2 input pins : x , y
        input_pins = new Pins(2);
        // Half adder has 2 output pins : sum , carry
        output_pins = new Pins(2);
    }

    // connect power to gates
    @Override
    public void connectToPower(ConnectingWire power) {
        and_gate.connectToPower(power);
        xor_gate.connectToPower(power);
    }

    // connect to input A
    @Override
    public void connectToInputA(ConnectingWire input1) {
        input_pins.connectPinWire(1, input1);
    }

    // connect to input B
    @Override
    public void connectToInputB(ConnectingWire input2) {
        input_pins.connectPinWire(2, input2);
    }

    @Override
    public ConnectingWire getSum(){
        connectGates();
        return output_pins.getPinWire(1);
    }

    // return output pins
    @Override
    public ConnectingWire getCarry() {
        connectGates();
        return output_pins.getPinWire(2);
    }

    // main logic
    private void connectGates() {
        // connect two inputs to XOR gate to produce sum
        xor_gate.connectToInputA(input_pins.getPinWire(1));
        xor_gate.connectToInputB(input_pins.getPinWire(2));
        
        // connect two inputs to AND gate to produce carry
        and_gate.connectToInputA(input_pins.getPinWire(1));
        and_gate.connectToInputB(input_pins.getPinWire(2));

        // set result to output pins
        // XOR gate gives sum and AND gives carry
        output_pins.connectPinWire(1, xor_gate.getOutput());
        output_pins.connectPinWire(2, and_gate.getOutput());

    }
    
    // return adder count NOTE: Unnecessary
    public int totalGateCount() {
        return half_adder_count;
    }
}
