package br.pro.hashi.ensino.desagil.aps.model;

public class TripleInputOrGate extends Gate {
    private final NandGate nandTop;
    private final NandGate nandMiddle;
    private final NandGate nandBottom;
    private final NandGate nandRight1;
    private final NandGate nandRight2;
    private final NandGate nandRight3;


    public TripleInputOrGate() {
        super("TripleInputOR", 3);

        nandTop = new NandGate();

        nandBottom = new NandGate();

        nandMiddle = new NandGate();

        nandRight1 = new NandGate();
        nandRight1.connect(0, nandTop);
        nandRight1.connect(1, nandMiddle);

        nandRight2 = new NandGate();
        nandRight2.connect(0, nandRight1);
        nandRight2.connect(1, nandRight1);

        nandRight3 = new NandGate();
        nandRight3.connect(0, nandRight2);
        nandRight3.connect(1, nandBottom);
    }


    @Override
    public boolean read(int outputPin) {
        if (outputPin != 0) {
            throw new IndexOutOfBoundsException(outputPin);
        }
        return nandRight3.read();
    }


    @Override
    public void connect(int inputPin, SignalEmitter emitter) {
        switch (inputPin) {
            case 0:
                nandTop.connect(0, emitter);
                nandTop.connect(1, emitter);
                break;
            case 1:
                nandMiddle.connect(0, emitter);
                nandMiddle.connect(1, emitter);
                break;
            case 2:
                nandBottom.connect(0, emitter);
                nandBottom.connect(1, emitter);
                break;

            default:
                throw new IndexOutOfBoundsException(inputPin);
        }
    }
}
