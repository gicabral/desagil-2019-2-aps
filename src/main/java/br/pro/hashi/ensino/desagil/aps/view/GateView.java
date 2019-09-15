package br.pro.hashi.ensino.desagil.aps.view;

import br.pro.hashi.ensino.desagil.aps.model.Gate;
import br.pro.hashi.ensino.desagil.aps.model.Switch;

import javax.swing.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class GateView extends JPanel implements ItemListener {

    private final Gate gate;

    private final JCheckBox[] inputs;
    private final Switch[] switches;
    private final JCheckBox outputBox;

    public GateView(Gate gate) {
        this.gate = gate;

        inputs = new JCheckBox[gate.getInputSize()];
        switches = new Switch[gate.getInputSize()];
        outputBox = new JCheckBox();

        for (int i = 0; i < gate.getInputSize(); i++) {
            inputs[i] = new JCheckBox();
            switches[i] = new Switch();
            gate.connect(i, switches[i]);
        }

        JLabel inputLabel = new JLabel("Entrada:");
        JLabel outLabel = new JLabel("Saída:");

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(inputLabel);
        for (int j = 0; j < gate.getInputSize(); j++) {
            add(inputs[j]);
            inputs[j].addItemListener(this);
        }

        add(outLabel);
        add(outputBox);

        outputBox.setEnabled(false);

        update();
    }

    private void update() {
        for (int i = 0; i < gate.getInputSize(); i++) {
            if (inputs[i].isSelected()) {
                switches[i].turnOn();
            } else {
                switches[i].turnOff();
            }
        }

        outputBox.setSelected(gate.read());
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        update();
    }
}