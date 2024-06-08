package logicGates;

import Connectors.Pins;
import Connectors.ConnectingWire;
import Transistor.Transistor;

public class OR implements LogicGate{
    // to count number of gates used in circuit
    public static int gate_count = 0;

    // OR gate have two transistors
    Transistor transistor_1;
    Transistor transistor_2;

    // OR gate have two input pins and one output pin
    Pins input_pins;
    Pins output_pin;

    public OR() {
        gate_count++;
        transistor_1 = new Transistor();
        transistor_2 = new Transistor();
        input_pins = new Pins(2);
        output_pin = new Pins();
    }

    // connect power to transistors
    public void connectToPower(ConnectingWire power) {
        // both transistors connected in parallel
        transistor_1.connectToPower(power);
        transistor_2.connectToPower(power);
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
        // OR gate has two input which connected to each transistor's base terminal.
        transistor_1.connectToBase(input_pins.getPinWire(1));
        transistor_2.connectToBase(input_pins.getPinWire(2));

        // here is a catch when both input are false then output is false else true.
        // here we store result of each transistor in wires.
        ConnectingWire transistor_1_output = transistor_1.getEmitterStatus();
        ConnectingWire transistor_2_output = transistor_2.getEmitterStatus();
        ConnectingWire result_output = new ConnectingWire();

        if (transistor_1_output.getCurrentStatus() == ConnectingWire.CURRENT_FLOWING) {
            result_output.setCurrentStatus(ConnectingWire.CURRENT_FLOWING);
            output_pin.connectPinWire(result_output);
            return;
        }

        if (transistor_2_output.getCurrentStatus() == ConnectingWire.CURRENT_FLOWING) {
            result_output.setCurrentStatus(ConnectingWire.CURRENT_FLOWING);
            output_pin.connectPinWire(result_output);
            return;
        }

        result_output.setCurrentStatus(ConnectingWire.CURRENT_STOPPED);
        output_pin.connectPinWire(result_output);
    }


    // return gate count
    public int totalGateCount() {
        return gate_count;
    }
}
