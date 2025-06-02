import javax.swing.*;

public class TokenSelectorPanel {
    private JPanel ui;
    private JTabbedPane extractorTabs;
    private JButton extractorToggle;
    private JComboBox comboBox1;
    private JScrollPane requestText;
    private JPanel requestScopePanel;
    private JScrollPane responseText;
    private JPanel responseScopePanel;
    private ScopeSelector requestScope;
    private ScopeSelector responseScope;

    public TokenSelectorPanel(){

    }

    public JPanel getUi() {
        return ui;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        this.requestScopePanel = new JPanel();
        this.responseScopePanel = new JPanel();
        this.requestScope = new ScopeSelector();
        this.responseScope = new ScopeSelector();
        this.responseScopePanel.add(responseScope.getUi());
        this.requestScopePanel.add(requestScope.getUi());
    }
}
