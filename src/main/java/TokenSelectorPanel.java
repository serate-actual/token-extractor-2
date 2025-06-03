import javax.swing.*;
import java.awt.event.ItemEvent;

public class TokenSelectorPanel {
    private JPanel ui;
    private JTabbedPane extractorTabs;
    private JButton extractorToggle;
    private JList toolSelector;
    private JCheckBox selectAllTools;
    private String[] tools;

    public TokenSelectorPanel(){
    }

    public JPanel getUi() {
        return ui;
    }

    public JTabbedPane getExtractorTabs() {
        return extractorTabs;
    }

    public void toggleTools(boolean state){
        if(state){
            this.toolSelector.setEnabled(false);
            this.toolSelector.setSelectionInterval(0,tools.length);
        } else {
            this.toolSelector.setEnabled(true);
            this.toolSelector.removeSelectionInterval(0,tools.length);
        }
    }
    private void createUIComponents() {
        // TODO: place custom component creation code here
        this.tools = new String[]{"Proxy", "Scanner", "Repeater", "Intruder", "Extensions"};
        this.toolSelector = new JList(){
        };
        this.toolSelector.setListData(tools);
        this.selectAllTools = new JCheckBox(){
            @Override
            protected void fireItemStateChanged(ItemEvent event) {
                toggleTools(this.isSelected());
                super.fireItemStateChanged(event);
            }
        };
    }
}
