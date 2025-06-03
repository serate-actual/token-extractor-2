import burp.api.montoya.MontoyaApi;
import burp.api.montoya.http.message.requests.HttpRequest;
import burp.api.montoya.http.message.responses.HttpResponse;

import javax.swing.*;

public class ExtractionPair {
    private String tabName;
    private Boolean isEnabled;
    private HttpRequest request;
    private HttpResponse response;

    public ExtractionPair(HttpRequest request, HttpResponse response, JTabbedPane targetPanel, MontoyaApi api){
        this.request = request;
        this.response = response;
        //start as disabled
        this.isEnabled = false;
        this.tabName = this.request.url().substring(this.request.url().length()-10,this.request.url().length());
        RequestResponseTab requestResponseTab = new RequestResponseTab(this, api);
        targetPanel.addTab(this.tabName, requestResponseTab.getUi());
    }

    public HttpRequest getRequest(){
        return this.request;
    }

    public HttpResponse getResponse(){
        return this.response;
    }
}
