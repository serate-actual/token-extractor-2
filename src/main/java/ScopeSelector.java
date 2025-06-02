import javax.swing.*;

public class ScopeSelector {
    private JTextField afterRegex;
    private JTextField beforeRegex;
    private JRadioButton useSuiteScopeRadioButton;
    private JRadioButton useSelectedHostRadioButton;
    private JTextField textField1;
    private JPanel ui;

    private void createUIComponents() {
        // TODO: place custom component creation code here
        this.ui = new JPanel();
    }

    public JPanel getUi() {
        return ui;
    }
}
