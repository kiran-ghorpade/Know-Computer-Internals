import ALU.Adder;
import ALU.FullAdder;
import ALU.HalfAdder;
import Connectors.ConnectingWire;
import Connectors.Pins;
import Transistor.Transistor;
import logicGates.*;

class TestLogicGates {

    public static void getResult(String optional, ConnectingWire wire) {
        if (wire.getCurrentStatus() == ConnectingWire.CURRENT_FLOWING)
            System.out.println(optional + " : TRUE");

        else
            System.out.println(optional + " : FALSE");

    }

    public static String getResult(ConnectingWire wire) {
        if (wire.getCurrentStatus() == ConnectingWire.CURRENT_FLOWING)
            return "1";

        else
            return "0";

    }

    public static void testGate(LogicGate gate, ConnectingWire power, ConnectingWire input1[], ConnectingWire input2[]) {

        Pins input_pins = new Pins(2);
        gate.connectToPower(power);

        System.out.println("Input1\tInput2\tOuput");
        System.out.println("----------------------------");
        for (int i = 0; i < input2.length && i < input1.length; i++) {
            input_pins.connectPinWire(1, input1[i]);
            input_pins.connectPinWire(2, input2[i]);

            gate.connectToInputPins(input_pins);

            System.out.println(getResult(input1[i]) + "\t" + getResult(input2[i]) + "\t"
                    + getResult(gate.getOutput()));
                    }
    }

    public static void testNOT(ConnectingWire power, ConnectingWire input1[]) {
        NOT gate = new NOT();
        
        gate.connectToPower(power);
        
        System.out.println("Input1\tOuput");
        System.out.println("--------------------");
        for (int i = 0; i < input1.length; i++) {
            gate.connectToInput(input1[i]);

            System.out.println(getResult(input1[i]) + "\t"
                    + getResult(gate.getOutput()));
        }
    }

    public static void main(String args[]) {
        final int INPUT_SIZE = 4;

        ConnectingWire power = new ConnectingWire();
        power.setCurrentStatus(ConnectingWire.CURRENT_FLOWING);

        ConnectingWire input1[] = new ConnectingWire[INPUT_SIZE];
        for (int i = 0; i < input1.length; i++) {
            input1[i] = new ConnectingWire();
        }
        ConnectingWire input2[] = new ConnectingWire[INPUT_SIZE];
        for (int i = 0; i < input2.length; i++) {
            input2[i] = new ConnectingWire();
        }

        input1[0].setCurrentStatus(ConnectingWire.CURRENT_STOPPED);
        input1[1].setCurrentStatus(ConnectingWire.CURRENT_STOPPED);
        input1[2].setCurrentStatus(ConnectingWire.CURRENT_FLOWING);
        input1[3].setCurrentStatus(ConnectingWire.CURRENT_FLOWING);

        input2[0].setCurrentStatus(ConnectingWire.CURRENT_STOPPED);
        input2[1].setCurrentStatus(ConnectingWire.CURRENT_FLOWING);
        input2[2].setCurrentStatus(ConnectingWire.CURRENT_STOPPED);
        input2[3].setCurrentStatus(ConnectingWire.CURRENT_FLOWING);




        System.out.println("ALL Logic Gate Test Cases - \n\n");
        System.out.println("\nAND GATE :");
        testGate(new AND(), power, input1, input2);

        System.out.println("\nOR GATE :");
        testGate(new OR(), power, input1, input2);

        System.out.println("\nNOT GATE :");
        testNOT(power, input1);

        System.out.println("\nXOR GATE :");
        testGate(new NOT(),power, input1, input2);
        
        System.out.println("\n\nComponents Used");
        System.out.println("____________________________________________________\n");
        System.out.println("Total Transistors : " + Transistor.transistor_count);
        System.out.println("Total Wires       : " + ConnectingWire.wire_count);
    }
}