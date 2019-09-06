package br.pro.hashi.ensino.desagil.aps.model;

public class OrGate extends Gate {
    private final NandGate nandUp;
    private final NandGate nandDown;
    private final NandGate nandRight;


    public OrGate() {
        super(2);

        nandUp = new NandGate();
        nandDown = new NandGate();

        nandRight = new NandGate();
        nandRight.connect(0, nandUp);
        nandRight.connect(1, nandDown);

    }


    @Override
    public boolean read() {
        return nandRight.read();
    }


    @Override
    public void connect(int inputPin, SignalEmitter emitter) {
        switch (inputPin) {
            case 0:
                nandUp.connect(0, emitter);
                nandUp.connect(1, emitter);
                break;
            case 1:
                nandDown.connect(0, emitter);
                nandDown.connect(1, emitter);
                break;
            default:
                throw new IndexOutOfBoundsException(inputPin);
        }
    }
}
