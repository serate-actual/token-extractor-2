import burp.api.montoya.MontoyaApi;
import burp.api.montoya.http.message.requests.HttpRequest;
import burp.api.montoya.http.message.responses.HttpResponse;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.TableModelEvent;
import javax.swing.table.JTableHeader;
import java.awt.event.ActionEvent;

public class RequestResponseSelectorPanel {
    private JPanel ui;
    private JTable requestTable;
    private JTable responseTable;
    private JButton extractButton;
    private JButton removeRequestButton;
    private JButton clearButton;
    private JSplitPane splitPane1;
    private JEditorPane requestPane;
    private JEditorPane responsePane;
    private JButton removeResponseButton;
    private JScrollPane requestScrollPane;
    private RequestTableModel requestTableModel;
    private ResponseTableModel responseTableModel;
    private boolean requestSelected;
    private boolean responseSelected;
    private HttpRequest selectedRequest;
    private HttpResponse selectedResponse;
    private MontoyaApi api;
    private ExtractionPair[] extractionPairs;
    private JTabbedPane extractorTabs;

    public RequestResponseSelectorPanel(MontoyaApi api, RequestTableModel requestTableModel, ResponseTableModel responseTableModel){
        this.requestTableModel = requestTableModel;
        this.setRequestTableModel(requestTableModel);
        this.responseTableModel = responseTableModel;
        this.setResponseTableModel(responseTableModel);
        this.requestSelected = false;
        this.responseSelected = false;
        this.api = api;
    }
    public void setExtractorTabs(JTabbedPane extractorTabs){
        this.extractorTabs = extractorTabs;
    }
    public JPanel getUi() {
        return ui;
    }
    public JTable getRequestTable(){
        return requestTable;
    }
    public JTable getResponseTable(){
        return responseTable;
    }
    public RequestTableModel getRequestTableModel(){
        return requestTableModel;
    }
    public ResponseTableModel getResponseTableModel(){
        return responseTableModel;
    }
    public void setResponseTableModel(ResponseTableModel responseTableModel){
        this.responseTableModel = responseTableModel;
        this.responseTable.setModel(responseTableModel);
    }
    public void setRequestTableModel(RequestTableModel requestTableModel){
        this.requestTableModel = requestTableModel;
        this.requestTable.setModel(requestTableModel);
    }

    public void isExtractable(){
        if(this.requestSelected == true && responseSelected == true){
            this.extractButton.setEnabled(true);
        }else{
            this.extractButton.setEnabled(false);
        }
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        this.splitPane1 = new JSplitPane();
        splitPane1.setResizeWeight(0.5);
        this.requestPane = new JEditorPane();
        this.requestPane.setEditable(false);
        this.responsePane = new JEditorPane();
        this.responsePane.setEditable(false);
        //this.requestPane.setContentType("text/html");
        //splitPane1.setDividerLocation(.5);
        // Set up request section
        // Set up request inspector

        // Set up request selector
        this.requestTable = new JTable(requestTableModel){
            @Override
            protected JTableHeader createDefaultTableHeader() { return super.createDefaultTableHeader(); }

            @Override
            public void tableChanged(TableModelEvent e){
                //this.revalidate();
                super.tableChanged(e);
                this.revalidate();
                this.repaint();
            }

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (this.getSelectedRowCount() == 1) {
                    // one row is selected
                    requestSelected = true;
                    selectedRequest = HttpRequest.httpRequest(requestTableModel.getValueAt(this.getSelectedRow(), 3));
                    try {
                        requestPane.setText(selectedRequest.toString());
                    }catch(Exception exception) {
                        requestPane.setText("");
                    }
                } else {
                    // more than one row is selected - not allowed
                    requestSelected = false;
                    requestPane.setText("Please only select one request");
                }
                isExtractable();
                super.valueChanged(e);
            }
        };
        // Set up response section
        this.responseTable = new JTable(responseTableModel){
            @Override
            protected JTableHeader createDefaultTableHeader() { return super.createDefaultTableHeader(); }

            @Override
            public void tableChanged(TableModelEvent e){
                //this.revalidate();
                super.tableChanged(e);
                this.revalidate();
                this.repaint();
            }

            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (this.getSelectedRowCount() == 1) {
                    System.out.println(responseTableModel.getRowCount() + " rows in the tablemodel");
                    // one row is selected
                    responseSelected = true;
                    selectedResponse = HttpResponse.httpResponse(responseTableModel.getValueAt(this.getSelectedRow(), 1));
                    try {
                        responsePane.setText(selectedResponse.toString());
                    }catch(Exception exception) {
                        responsePane.setText("");
                    }
                } else {
                    // more than one row is selected - not allowed
                    responseSelected = false;
                    responsePane.setText("Please only select one response");
                    HttpResponse selectedResponse = null;
                }
                isExtractable();
                super.valueChanged(e);
            }

        };
        this.responseTable.repaint();
        this.requestTable.repaint();
        this.extractButton = new JButton(){
            @Override
            protected void fireActionPerformed(ActionEvent event){
                ExtractionPair newPair = new ExtractionPair(selectedRequest, selectedResponse, extractorTabs, api);
                super.fireActionPerformed(event);
            }
        };
    }
}
