package logicGates;

import Connectors.ConnectingWire;
import Transistor.Transistor;

public class NOT extends Transistor {
    public static int gate_count = 0;

    ConnectingWire output;

    public NOT() {
        super();
        ++gate_count;
        output = new ConnectingWire();
    }

    @Override
    protected void changeEmitterCurrentStatus() {
        if (super.base.getCurrentStatus() == ConnectingWire.CURRENT_STOPPED) {
            emitter.setCurrentStatus(collector.getCurrentStatus());
            return;
        }

        emitter.setCurrentStatus(ConnectingWire.CURRENT_STOPPED);
    }

    public void connectToPower(ConnectingWire power) {
        super.connectToPower(power);
    }

    public void connectToInput(ConnectingWire input) {
        super.connectToBase(input);
    }

    public ConnectingWire getOutput() {
        this.changeEmitterCurrentStatus();
        return emitter;
    }

    public int totalGateCount() {
        return gate_count;
    }
}
