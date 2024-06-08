/**
 *  Pins :
 *      - It is holder of wires.
 *      - I didn't find any perfect name of it.
 *      - It just have array of wire which can hold multiple input/output wires
 */


package Connectors;

public class Pins {
    // wire holder
    ConnectingWire wires[];

    // for single wire
    public Pins() {
        wires = new ConnectingWire[1];
    }

    // for multiple wires
    public Pins(int numberOfPins) {
        wires = new ConnectingWire[numberOfPins];
    }

    // set wire
    public void connectPinWire(ConnectingWire wire) {
            wires[0] = wire;
    }

    // set wire at particular index. NOTE: PinNumber starts from 1 not from 0
    public void connectPinWire(int PinNumber, ConnectingWire wire) {
        if (PinNumber > 0)
            wires[PinNumber - 1] = wire;
    }

    // return wire 
    public ConnectingWire getPinWire() {
            return wires[0];
    }

    // return wire at particular index
    public ConnectingWire getPinWire(int PinNumber) {
        if (PinNumber > 0)
            return wires[PinNumber - 1];
        else
            return null;
    }
}
