import burp.api.montoya.http.message.HttpRequestResponse;
import burp.api.montoya.ui.contextmenu.ContextMenuEvent;
import burp.api.montoya.ui.contextmenu.ContextMenuItemsProvider;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class ContextMenu implements ContextMenuItemsProvider{
    RequestTableModel requestTableModel;
    ResponseTableModel responseTableModel;
    TokenExtractorTab ui;
    public ContextMenu(RequestTableModel requestTableModel, ResponseTableModel responseTableModel, TokenExtractorTab ui){
        this.requestTableModel = requestTableModel;
        this.responseTableModel = responseTableModel;
        this.ui = ui;
    }

    private HttpRequestResponse getSelectedRequestResponse(ContextMenuEvent event){
        return event.messageEditorRequestResponse().get().requestResponse();
    }

    public List<Component> provideMenuItems(ContextMenuEvent event){
        // Takes the event and gets the list back
        HttpRequestResponse requestResponseList = getSelectedRequestResponse(event);
        List<Component> menuItemList = new ArrayList<>();
        JMenuItem RegisterResponse = new JMenuItem("Response to Extractor");
        RegisterResponse.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                responseTableModel.addResponse(requestResponseList.response());
                responseTableModel.fireTableDataChanged();
                ui.getRequestSelectorPanel().getResponseTable().revalidate();
                ui.getRequestSelectorPanel().getResponseTable().repaint();
                System.out.println("Added response");
            }
        });
        menuItemList.add(RegisterResponse);
        JMenuItem RegisterRequest = new JMenuItem("Request to Extractor");
        RegisterRequest.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                //requestResponseList.request()
                requestTableModel.addRequest(requestResponseList.request());
                requestTableModel.fireTableDataChanged();
                ui.getRequestSelectorPanel().getRequestTable().revalidate();
                ui.getRequestSelectorPanel().getRequestTable().repaint();
                System.out.println("Added request");
            }
        });
        menuItemList.add(RegisterRequest);
        return menuItemList;
    }
}
