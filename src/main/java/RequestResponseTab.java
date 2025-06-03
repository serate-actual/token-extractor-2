import burp.api.montoya.MontoyaApi;
import burp.api.montoya.ui.editor.HttpRequestEditor;
import burp.api.montoya.ui.editor.HttpResponseEditor;

import static burp.api.montoya.ui.editor.EditorOptions.READ_ONLY;
import javax.swing.*;

public class RequestResponseTab {
    private MontoyaApi api;
    private JPanel ui;
    private JSplitPane splitPane;
    private JPanel requestScopePanel;
    private JPanel responseScopePanel;
    private JPanel responsePanel;
    private JPanel requestPanel;
    private ExtractionPair extractionPair;
    private ScopeSelector requestScopeSelector;
    private ScopeSelector responseScopeSelector;
    private HttpRequestEditor requestViewer;
    private HttpResponseEditor responseViewer;
    public RequestResponseTab(ExtractionPair extractionPair, MontoyaApi api){
        this.extractionPair = extractionPair;
        this.api = api;

        this.requestViewer = api.userInterface().createHttpRequestEditor(READ_ONLY);
        this.requestViewer.setRequest(extractionPair.getRequest());
        this.requestPanel.add(requestViewer.uiComponent());

        this.responseViewer = api.userInterface().createHttpResponseEditor(READ_ONLY);
        System.out.println(this.responseViewer);
        this.responseViewer.setResponse(extractionPair.getResponse());
        this.responsePanel.add(responseViewer.uiComponent());
    }
    public JPanel getUi(){
        return ui;
    }
    private void createUIComponents() {
        // TODO: place custom component creation code here
        this.splitPane = new JSplitPane();
        this.splitPane.setResizeWeight(0.5);
        this.requestScopePanel = new JPanel();
        this.responseScopePanel = new JPanel();
        this.requestScopeSelector = new ScopeSelector();
        this.responseScopeSelector = new ScopeSelector();
        this.requestScopePanel.add(requestScopeSelector.getUi());
        this.responseScopePanel.add(responseScopeSelector.getUi());
        this.requestPanel = new JPanel();
        this.responsePanel = new JPanel();


        //this.test = new HttpRequestEditor();
        //api.userInterface().registerHttpRequestEditorProvider(test);
        //this.responseText.add(test.uiComponent());
    }
}
