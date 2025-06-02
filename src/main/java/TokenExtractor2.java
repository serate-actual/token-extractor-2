import burp.api.montoya.BurpExtension;
import burp.api.montoya.MontoyaApi;

public class TokenExtractor2 implements BurpExtension {
    private MontoyaApi api;

    @Override
    public void initialize(MontoyaApi montoyaApi) {
        this.api = montoyaApi;
        String tabname = "serate-token-extractor-2";
        // Creating table models
        RequestTableModel requestTableModel = new RequestTableModel();
        ResponseTableModel responseTableModel = new ResponseTableModel();

        montoyaApi.extension().setName(tabname);
        TokenExtractorTab extractorTab = new TokenExtractorTab(this.api, requestTableModel, responseTableModel);

        // setting table models here
        extractorTab.getRequestSelectorPanel().setRequestTableModel(requestTableModel);
        extractorTab.getRequestSelectorPanel().setResponseTableModel(responseTableModel);

        montoyaApi.userInterface().registerSuiteTab(tabname, extractorTab.getUI());

        // Registering context menu
        ContextMenu contextMenu = new ContextMenu(requestTableModel, responseTableModel, extractorTab);
        api.userInterface().registerContextMenuItemsProvider(contextMenu);
    }
}

// TODO
/*
Add mechanic to add requests/responses and display a table

*/