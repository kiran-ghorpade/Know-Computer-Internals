/**
 * XOR gate - 
 *      - it special gate in computer science.
 *      - it produce output used for binary addition
 */

package logicGates;

import Connectors.ConnectingWire;
import Connectors.Pins;

public class XOR implements LogicGate {
    public static int gate_count = 0;

    /*
     * XOR Gate have
     * - two AND gates
     * - two NOT gates
     * - a OR gate
     * 
     * - two input pins
     * - a output pin
     */

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

    // connect power to transistors
    @Override
    public void connectToPower(ConnectingWire power) {
        and_gate_A.connectToPower(power);
        and_gate_B.connectToPower(power);
        not_gate_A.connectToPower(power);
        not_gate_B.connectToPower(power);
        or_gate_A.connectToPower(power);
    }

    @Override
    public void connectToInputPins(Pins inputPins) {
        this.input_pins = inputPins;
    }

    // connect input to input A
    public void connectToInputA(ConnectingWire input1) {
        input_pins.connectPinWire(1, input1);
    }

    // connect input to input B
    public void connectToInputB(ConnectingWire input2) {
        input_pins.connectPinWire(2, input2);
    }

    // return output
    public Pins getOutput() {
        connectTransistors();
        return output_pin;
    }

    private void connectTransistors() {
        // connect input 
        and_gate_A.connectToInputA(input_pins.getPinWire(1));
        not_gate_B.connectToInput(input_pins.getPinWire(1));
        not_gate_A.connectToInput(input_pins.getPinWire(2));
        and_gate_B.connectToInputA(input_pins.getPinWire(2));

        and_gate_A.connectToInputB(not_gate_A.getOutput().getPinWire());
        and_gate_B.connectToInputB(not_gate_B.getOutput().getPinWire());

        or_gate_A.connectToInputA(and_gate_A.getOutput().getPinWire());
        or_gate_A.connectToInputB(and_gate_B.getOutput().getPinWire());

        output_pin.connectPinWire(or_gate_A.getOutput().getPinWire());
    }

    public int totalGateCount() {
        return gate_count;
    }
}
