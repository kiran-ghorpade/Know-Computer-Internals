package logicGates;

import Connectors.ConnectingWire;
import Connectors.Pins;
import Transistor.Transistor;

public class NOT extends Transistor implements LogicGate {
    public static int gate_count = 0;


    // NOT gate has only one input and one output
    ConnectingWire output;
    Pins input_pin;
    Pins output_pin;

    public NOT() {
        super();
        ++gate_count;
        output = new ConnectingWire();
        input_pin = new Pins();
        output_pin = new Pins();
    }

    // Main gate logic
    @Override
    protected void changeEmitterCurrentStatus() {

        // NOT gate is basically a transistor with different configuration.
        // The ouput pin is connect to collector and emitter.
        // When base current is on , output will off and vice versa.
        // It is special feature of transistor
        if (super.base.getCurrentStatus() == ConnectingWire.CURRENT_STOPPED) {
            emitter.setCurrentStatus(collector.getCurrentStatus());
            return;
        }

        emitter.setCurrentStatus(ConnectingWire.CURRENT_STOPPED);
    }

    // connect input pins
    @Override
    public void connectToInputPins(Pins inputPin) {
        input_pin = inputPin;
    }

    // connect power to collector
    public void connectToPower(ConnectingWire power) {
        super.connectToPower(power);
    }

    // connect input to base
    public void connectToInput(ConnectingWire input) {
        super.connectToBase(input);
    }

    // return output pin
    @Override
    public ConnectingWire getOutput() {
        this.changeEmitterCurrentStatus();
        output_pin.connectPinWire(emitter);
        return output_pin.getPinWire();
    }

    // return gate count
    public int totalGateCount() {
        return gate_count;
    }
}
