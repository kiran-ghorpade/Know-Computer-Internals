import ALU.Adder;
import ALU.FullAdder;
import ALU.HalfAdder;
import Connectors.ConnectingWire;
import Transistor.Transistor;
import logicGates.*;

class TestAdders {

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

    public static void testHalfAdder(ConnectingWire power, ConnectingWire input1[], ConnectingWire input2[]) {

        Adder adder = new HalfAdder();

        adder.connectToPower(power);

        System.out.println("Input1\tInput2\tSum\tCarry");
        System.out.println("----------------------------------------");
        for (int i = 0; i < input2.length && i < input1.length; i++) {
            adder.connectToInputA(input1[i]);
            adder.connectToInputB(input2[i]);

            System.out.println(getResult(input1[i]) + "\t" + getResult(input2[i]) + "\t"
                    + getResult(adder.getSum()) + "\t" + getResult(adder.getCarry()));
        }

    }

    public static void testFullAdder(ConnectingWire power, ConnectingWire carry[], ConnectingWire input1[], ConnectingWire input2[]) {

        FullAdder adder = new FullAdder();
        
        adder.connectToPower(power);
        
        System.out.println("Carry\tInput1\tInput2\tSum\tCarry");
        System.out.println("----------------------------------------");
        for (int i = 0; i < input2.length && i < input1.length; i++) {
            adder.connectToInputCarry(carry[i]);
            adder.connectToInputA(input1[i]);
            adder.connectToInputB(input2[i]);

            System.out.println(getResult(carry[i])+"\t"+ getResult(input1[i]) + "\t" + getResult(input2[i]) + "\t"
                    + getResult(adder.getSum()) + "\t" + getResult(adder.getCarry()));
        }

    }

    public static void main(String args[]) {
        final int INPUT_SIZE = 8;

        ConnectingWire power = new ConnectingWire();
        power.setCurrentStatus(ConnectingWire.CURRENT_FLOWING);

        ConnectingWire input1[] = new ConnectingWire[INPUT_SIZE];
        for (int i = 0; i < input1.length; i++) {
            input1[i] = new ConnectingWire();
        }
        ConnectingWire input2[] = new ConnectingWire[INPUT_SIZE];
        for (int i = 0; i < input1.length; i++) {
            input2[i] = new ConnectingWire();
        }
        ConnectingWire carry[] = new ConnectingWire[INPUT_SIZE];
        for (int i = 0; i < input1.length; i++) {
            carry[i] = new ConnectingWire();
        }

        carry[0].setCurrentStatus(ConnectingWire.CURRENT_STOPPED);
        carry[1].setCurrentStatus(ConnectingWire.CURRENT_STOPPED);
        carry[2].setCurrentStatus(ConnectingWire.CURRENT_STOPPED);
        carry[3].setCurrentStatus(ConnectingWire.CURRENT_STOPPED);
        carry[4].setCurrentStatus(ConnectingWire.CURRENT_FLOWING);
        carry[5].setCurrentStatus(ConnectingWire.CURRENT_FLOWING);
        carry[6].setCurrentStatus(ConnectingWire.CURRENT_FLOWING);
        carry[7].setCurrentStatus(ConnectingWire.CURRENT_FLOWING);

        input1[0].setCurrentStatus(ConnectingWire.CURRENT_STOPPED);
        input1[1].setCurrentStatus(ConnectingWire.CURRENT_STOPPED);
        input1[2].setCurrentStatus(ConnectingWire.CURRENT_FLOWING);
        input1[3].setCurrentStatus(ConnectingWire.CURRENT_FLOWING);
        input1[4].setCurrentStatus(ConnectingWire.CURRENT_STOPPED);
        input1[5].setCurrentStatus(ConnectingWire.CURRENT_STOPPED);
        input1[6].setCurrentStatus(ConnectingWire.CURRENT_FLOWING);
        input1[7].setCurrentStatus(ConnectingWire.CURRENT_FLOWING);

        input2[0].setCurrentStatus(ConnectingWire.CURRENT_STOPPED);
        input2[1].setCurrentStatus(ConnectingWire.CURRENT_FLOWING);
        input2[2].setCurrentStatus(ConnectingWire.CURRENT_STOPPED);
        input2[3].setCurrentStatus(ConnectingWire.CURRENT_FLOWING);
        input2[4].setCurrentStatus(ConnectingWire.CURRENT_STOPPED);
        input2[5].setCurrentStatus(ConnectingWire.CURRENT_FLOWING);
        input2[6].setCurrentStatus(ConnectingWire.CURRENT_STOPPED);
        input2[7].setCurrentStatus(ConnectingWire.CURRENT_FLOWING);

        System.out.println("ALL Adders Test Cases - \n\n");
        System.out.println("\nHalfAdder :");
        testHalfAdder(power, input1, input2);

        System.out.println("\nFullAdder :");
        testFullAdder(power, carry, input1, input2);

        
        System.out.println("\n\nComponents Used");
        System.out.println("____________________________________________________\n");
        System.out.println("Total AND Gates   : " + AND.gate_count);
        System.out.println("Total OR Gates    : " + OR.gate_count);
        System.out.println("Total NOT Gates   : " + NOT.gate_count);
        System.out.println("Total XOR Gates   : " + XOR.gate_count);
        System.out.println("Total Transistors : " + Transistor.transistor_count);
        System.out.println("Total Wires       : " + ConnectingWire.wire_count);
    }
}