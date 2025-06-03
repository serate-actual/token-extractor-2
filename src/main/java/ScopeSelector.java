import javax.swing.*;
import java.awt.event.ItemEvent;

public class ScopeSelector {
    private JTextField afterRegex;
    private JTextField beforeRegex;
    private JRadioButton useSuiteScopeRadioButton;
    private JRadioButton useSelectedHostRadioButton;
    private JTextField scopeField;
    private JPanel ui;

    ScopeSelector(){

    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        this.ui = new JPanel();
        this.useSuiteScopeRadioButton = new JRadioButton(){
            @Override
            protected void fireItemStateChanged(ItemEvent event) {
                evaluateButtons(this, useSelectedHostRadioButton);
                super.fireItemStateChanged(event);
            }
        };
        this.useSelectedHostRadioButton = new JRadioButton(){
            @Override
            protected void fireItemStateChanged(ItemEvent event) {
                evaluateButtons(this, useSuiteScopeRadioButton);
                super.fireItemStateChanged(event);
                if(this.isSelected()){
                    scopeField.setEnabled(true);
                }else{
                    scopeField.setEnabled(false);
                }
            }
        };
        this.scopeField = new JTextField();
    }

    public JPanel getUi() {
        return ui;
    }

    public void evaluateButtons(JRadioButton caller, JRadioButton other){
        if (caller.isSelected() && other.isSelected()){
            // Caller is selected, other is selected. Turn off the other
            other.setSelected(false);
        } else if (!caller.isSelected() && !other.isSelected()) {
            // Caller is NOT selected, other is NOT selected. Turn on the other
            other.setSelected(true);
        }
    }

}
