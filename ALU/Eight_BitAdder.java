/**
 *  8 bit adder :
 *      - it is a component which produce sum of 8 bit inputs
 *      - it uses 8 full adder in series
 * 
 */

package ALU;

import Connectors.Pins;
import Connectors.ConnectingWire;

public class Eight_BitAdder {
    
    private final int BIT_SIZE = 8;
    private final int TOTAL_INPUT_PINS = 17;
    private final int TOTAL_OUTPUT_PINS = 9;
    private final int INPUT1_PIN_START = 1;
    private final int INPUT1_PIN_END = 8;
    private final int INPUT2_PIN_START = 9;
    private final int INPUT2_PIN_END = 16;
    private final int CARRY_INPUT_PIN = 17;
    private final int OUTPUT_CARRY_PIN = 9;

    /*
     * 8 Bit Adder have
     * - Eight Full Adder
     * 
     * - 17 Input pins (2 * 8 bit input , and a carry-input)
     * - 9 Output Pins (8 bit output and carry/overflow-output)
     */


    private FullAdder full_adders[];
    private Pins input_pins;
    private Pins output_pins;

    public Eight_BitAdder() {

        // create 8 full adders
        full_adders = new FullAdder[BIT_SIZE];

        for (int i = 0; i < full_adders.length; i++) {
            full_adders[i] = new FullAdder();
        }

        // Full adder has 16 input pins : two 8bit input
        input_pins = new Pins(TOTAL_INPUT_PINS);
        // Full adder has 9 output pins : 8bit output, carry/overflow
        output_pins = new Pins(TOTAL_OUTPUT_PINS);
    }

    // connect power to adders
    public void connectToPower(ConnectingWire power) {
        for (FullAdder adder : full_adders) {
            adder.connectToPower(power);
        }
    }

    // connect to 8bit input A
    public void connectToInputA(Pins input1) {
        // pin 1 to 8 first input
        for (int i = INPUT1_PIN_START; i <= INPUT1_PIN_END; i++) {
            input_pins.connectPinWire(i, input1.getPinWire(i));
        }
    }

    // connect to 8bit input B
    public void connectToInputB(Pins input2) {
        // pin 9 to 16 second input
        for (int i = INPUT2_PIN_START; i <= INPUT2_PIN_END; i++) {
            input_pins.connectPinWire(i, input2.getPinWire(i));
        }
    }

    // connect to input carry
    public void connectToInputCarry(ConnectingWire carry) {
        // pin 17 is carry-input
        input_pins.connectPinWire(CARRY_INPUT_PIN, carry);
    }

    // return output pins
    public Pins getOutput() {
        connectAdders();
        return output_pins;
    }

    // Main Logic
    private void connectAdders() {
        
        // Initial sum with input carry
        full_adders[0].connectToInputCarry(input_pins.getPinWire(CARRY_INPUT_PIN));
        full_adders[0].connectToInputA(input_pins.getPinWire(INPUT1_PIN_START));
        full_adders[0].connectToInputB(input_pins.getPinWire(INPUT2_PIN_START));
        output_pins.connectPinWire(1, full_adders[0].getSum());

        // remaining sum of inputs
        for (int i = 1; i < full_adders.length; i++) {
            full_adders[i].connectToInputA(input_pins.getPinWire(i+1));
            full_adders[i].connectToInputB(input_pins.getPinWire(i+1+BIT_SIZE));
            full_adders[i].connectToInputCarry(full_adders[i-1].getCarry());
            
            output_pins.connectPinWire(i+1, full_adders[i].getSum());
        }

        // set carry output
        output_pins.connectPinWire(OUTPUT_CARRY_PIN, full_adders[full_adders.length-1].getCarry());
    }
}
