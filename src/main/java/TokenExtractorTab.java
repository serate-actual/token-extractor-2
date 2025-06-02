import javax.swing.*;
import burp.api.montoya.MontoyaApi;

public class TokenExtractorTab {
    private JPanel ui;
    private JTabbedPane JTabs;
    private SettingsPanel settingsPanel;
    private RequestResponseSelectorPanel requestResponseSelectorPanel;
    private TokenSelectorPanel tokenSelectorPanel;
    private MontoyaApi api;

    public TokenExtractorTab(MontoyaApi api, RequestTableModel requestTableModel, ResponseTableModel responseTableModel){
        this.requestResponseSelectorPanel = new RequestResponseSelectorPanel(api, requestTableModel, responseTableModel);
        this.settingsPanel = new SettingsPanel();
        this.tokenSelectorPanel = new TokenSelectorPanel();
        this.JTabs.addTab("Selector", requestResponseSelectorPanel.getUi());
        this.JTabs.addTab("Settings",settingsPanel.getUi());
        this.JTabs.addTab("Token Selector",tokenSelectorPanel.getUi());
        this.api = api;
    }

    public JPanel getUI(){
        return this.ui;
    }
    public RequestResponseSelectorPanel getRequestSelectorPanel(){
        return this.requestResponseSelectorPanel;
    }
    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
