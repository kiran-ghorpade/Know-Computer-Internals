/**
 * 
 * Full Adder :
 *     - overcome disadvantage of halfadder
 *     - consider carry during addition of inputs
 * 
 */

package ALU;

import Connectors.Pins;
import Connectors.ConnectingWire;
import logicGates.OR;

public class FullAdder implements Adder{

    /*
     * FullAdder have
     * - two HalfAdders
     * - a OR gate
     * 
     * - Three Input pins
     * - Two Output Pins
     */

    private Adder adder_A, adder_B;
    private OR or_gate;
    private Pins input_pins;
    private Pins output_pins;


    public FullAdder() {
        
        adder_A = new HalfAdder();
        adder_B = new HalfAdder();
        
        or_gate = new OR();
        
        // Full adder has 3 input pins : x , y , carry
        input_pins = new Pins(3);
        // Full adder has 2 output pins : sum, carry
        output_pins = new Pins(2);
    }

    // connect power to gates
    public void connectToPower(ConnectingWire power) {
        
        adder_A.connectToPower(power);
        adder_B.connectToPower(power);
        
        or_gate.connectToPower(power);
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

    // return sum
    @Override
    public ConnectingWire getSum() {
       connectAdders();
       return output_pins.getPinWire(1);
    }

    // return carry
    @Override
    public ConnectingWire getCarry() {
       connectAdders();
       return output_pins.getPinWire(2);
    }

    // Main Adder Logic
    private void connectAdders() {
        // so here first we add inputs and then add result with carry

        adder_A.connectToInputA(input_pins.getPinWire(1));
        adder_A.connectToInputB(input_pins.getPinWire(2));

        adder_B.connectToInputA(input_pins.getPinWire(3));
        adder_B.connectToInputB(adder_A.getSum());

        or_gate.connectToInputA(adder_A.getCarry());
        or_gate.connectToInputB(adder_B.getCarry());
        
        // set result to output pins
        
        // HalfAdder B gives sum
        output_pins.connectPinWire(1, adder_B.getSum());
        // OR gate gives carry
        output_pins.connectPinWire(2, or_gate.getOutput());
    }

}
