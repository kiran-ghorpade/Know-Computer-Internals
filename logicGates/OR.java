package logicGates;

import Connectors.ConnectingWire;
import Connectors.Pins;
import Transistor.Transistor;

public class OR {
    // to count number of gates used in circuit
    public static int gate_count = 0;

    Transistor transistor_1;
    Transistor transistor_2;

    Pins input_pins;
    Pins output_pin;

    public OR() {
        gate_count++;
        transistor_1 = new Transistor();
        transistor_2 = new Transistor();
        input_pins = new Pins(2);
        output_pin = new Pins();
    }

    public void connectToPower(ConnectingWire power) {
        transistor_1.connectToPower(power);
        transistor_2.connectToPower(power);
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
        transistor_1.connectToBase(input_pins.getPin(1));
        transistor_2.connectToBase(input_pins.getPin(2));

        ConnectingWire transistor_1_output = transistor_1.getEmitterStatus();
        ConnectingWire transistor_2_output = transistor_2.getEmitterStatus();
        ConnectingWire result_output = new ConnectingWire();

        if (transistor_1_output.getCurrentStatus() == ConnectingWire.CURRENT_FLOWING) {
            result_output.setCurrentStatus(ConnectingWire.CURRENT_FLOWING);
            output_pin.connectPin(result_output);
            return;
        }

        if (transistor_2_output.getCurrentStatus() == ConnectingWire.CURRENT_FLOWING) {
            result_output.setCurrentStatus(ConnectingWire.CURRENT_FLOWING);
            output_pin.connectPin(result_output);
            return;
        }

        result_output.setCurrentStatus(ConnectingWire.CURRENT_STOPPED);
        output_pin.connectPin(result_output);
    }

    public int totalGateCount() {
        return gate_count;
    }
}
