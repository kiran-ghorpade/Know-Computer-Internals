import ALU.HalfAdder;
import Connectors.ConnectingWire;
import Transistor.Transistor;
import logicGates.*;

class Computer {

    public static void getResult(String optional, ConnectingWire wire) {
        if (wire.getCurrentStatus() == ConnectingWire.CURRENT_FLOWING)
            System.out.println(optional + " : TRUE");

        else
            System.out.println(optional + " : FALSE");

    }

    public static String getResult(ConnectingWire wire) {
        if (wire.getCurrentStatus() == ConnectingWire.CURRENT_FLOWING)
            return "TRUE";

        else
            return "FALSE";

    }

    public static void testGate(ConnectingWire power, ConnectingWire input1[], ConnectingWire input2[]) {
        AND gate = new AND();

        gate.connectToPower(power);

        System.out.println("Input1\tInput2\tOuput");
        for (int i = 0; i < input2.length && i < input1.length; i++) {
            gate.connectToInputA(input1[i]);
            gate.connectToInputB(input2[i]);

            System.out.println(getResult(input1[i]) + "\t" + getResult(input2[i]) + "\t"
                    + getResult(gate.getOutput().getPin()));
        }
    }

    public static void testOR(ConnectingWire power, ConnectingWire input1[], ConnectingWire input2[]) {
        OR gate = new OR();

        gate.connectToPower(power);

        System.out.println("Input1\tInput2\tOuput");
        for (int i = 0; i < input2.length && i < input1.length; i++) {
            gate.connectToInputA(input1[i]);
            gate.connectToInputB(input2[i]);

            System.out.println(getResult(input1[i]) + "\t" + getResult(input2[i]) + "\t"
                    + getResult(gate.getOutput().getPin()));
        }
    }

    public static void testXOR(ConnectingWire power, ConnectingWire input1[], ConnectingWire input2[]) {
        XOR gate = new XOR();

        gate.connectToPower(power);

        System.out.println("Input1\tInput2\tOuput");
        for (int i = 0; i < input2.length && i < input1.length; i++) {
            gate.connectToInputA(input1[i]);
            gate.connectToInputB(input2[i]);

            System.out.println(getResult(input1[i]) + "\t" + getResult(input2[i]) + "\t"
                    + getResult(gate.getOutput().getPin()));
        }
    }

    public static void testNOT(ConnectingWire power, ConnectingWire input1[]) {
        NOT gate = new NOT();

        gate.connectToPower(power);

        System.out.println("Input1\tOuput");
        for (int i = 0; i < input1.length; i++) {
            gate.connectToInput(input1[i]);

            System.out.println(getResult(input1[i]) + "\t"
                    + getResult(gate.getOutput()));
        }
    }

    public static void testHalfAdder(ConnectingWire power, ConnectingWire input1[], ConnectingWire input2[]) {

        HalfAdder adder = new HalfAdder();

        adder.connectToPower(power);

        System.out.println("Input1\tInput2\tSum\tCarry");
        for (int i = 0; i < input2.length && i < input1.length; i++) {
            adder.connectToInputA(input1[i]);
            adder.connectToInputB(input2[i]);

            System.out.println(getResult(input1[i]) + "\t" + getResult(input2[i]) + "\t"
                    + getResult(adder.getOutput().getPin(1)) + "\t" + getResult(adder.getOutput().getPin(2)));
        }

    }

    public static void main(String args[]) {
        ConnectingWire power = new ConnectingWire();
        power.setCurrentStatus(ConnectingWire.CURRENT_FLOWING);

        ConnectingWire input1[] = new ConnectingWire[4];
        for (int i = 0; i < input1.length; i++) {
            input1[i] = new ConnectingWire();
        }
        ConnectingWire input2[] = new ConnectingWire[4];
        for (int i = 0; i < input1.length; i++) {
            input2[i] = new ConnectingWire();
        }

        input1[0].setCurrentStatus(ConnectingWire.CURRENT_STOPPED);
        input1[1].setCurrentStatus(ConnectingWire.CURRENT_FLOWING);
        input1[2].setCurrentStatus(ConnectingWire.CURRENT_STOPPED);
        input1[3].setCurrentStatus(ConnectingWire.CURRENT_FLOWING);

        input2[0].setCurrentStatus(ConnectingWire.CURRENT_STOPPED);
        input2[1].setCurrentStatus(ConnectingWire.CURRENT_STOPPED);
        input2[2].setCurrentStatus(ConnectingWire.CURRENT_FLOWING);
        input2[3].setCurrentStatus(ConnectingWire.CURRENT_FLOWING);

        // testAND(power, input1, input2);
        // testOR(power, input1, input2);
        // testNOT(power, input1);
        // testXOR(power, input1, input2);
        testHalfAdder(power, input1, input2);

        System.out.println("____________________________________________________");
        System.out.println("Total HalfAdder   : " + HalfAdder.half_adder_count);
        System.out.println("Total AND Gates   : " + AND.gate_count);
        System.out.println("Total OR Gates    : " + OR.gate_count);
        System.out.println("Total NOT Gates   : " + NOT.gate_count);
        System.out.println("Total XOR Gates   : " + XOR.gate_count);
        System.out.println("Total Transistors : " + Transistor.transistor_count);
        System.out.println("Total Wires       : " + ConnectingWire.wire_count);
    }
}