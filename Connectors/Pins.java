package Connectors;

public class Pins {
    ConnectingWire wires[];

    public Pins() {
        wires = new ConnectingWire[1];
    }

    public Pins(int numberOfPins) {
        wires = new ConnectingWire[numberOfPins];
    }

    public void connectPin(ConnectingWire wire) {
            wires[0] = wire;
    }

    public void connectPin(int PinNumber, ConnectingWire wire) {
        if (PinNumber > 0)
            wires[PinNumber - 1] = wire;
    }

    public ConnectingWire getPin() {
            return wires[0];
    }

    public ConnectingWire getPin(int PinNumber) {
        if (PinNumber > 0)
            return wires[PinNumber - 1];
        else
            return null;
    }
}
