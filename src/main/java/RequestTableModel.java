import burp.api.montoya.http.message.requests.HttpRequest;

import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class RequestTableModel extends DefaultTableModel{
    private final Vector dataVector;
    private final Vector columnIdentifiers;

    public RequestTableModel(){
        this.columnIdentifiers = new Vector();
        //this.columnIdentifiers.add("Request URL");
        //this.columnIdentifiers.add("Request Method");
        this.columnIdentifiers.add("Method");
        this.columnIdentifiers.add("URL");
        this.columnIdentifiers.add("Body");
        this.dataVector = new Vector();
    }
    public void removeRow(int item){
        this.dataVector.remove(item);
        this.fireTableDataChanged();
    }
    public HttpRequest getRequest(int index){
        return (HttpRequest) this.dataVector.get(index);
    }
    public void addRequest(HttpRequest request) {
        Object[] row = new Object[4];
        //method
        //url
        //body
        //actual request
        row[0]=request.method();
        row[1]=request.url();
        row[2]=request.body();
        row[3]=request;
        this.addRow(row);
    }

    public void setValueAt(Object aValue, int row, int column){
        Object[] item = (Object[]) this.dataVector.get(row);
        item[column] = aValue;
        this.dataVector.set(row, item);
        this.fireTableDataChanged();
    }

    @Override
    public void addRow(Object[] rowData){
        this.dataVector.add(rowData);
        System.out.println(rowData[0]);
        super.newDataAvailable(null);
    }

    @Override
    public String getValueAt(int RowIndex, int columnIndex){
        Object[] row = (Object[]) this.dataVector.get(RowIndex);
        return row[columnIndex].toString();
    }

    @Override
    public int getColumnCount() {
        return columnIdentifiers.size();
    }

    @Override
    public int getRowCount(){
        if (this.dataVector == null) {
            return 0;
        } else {
            return this.dataVector.size();
        }
    }

    @Override
    public String getColumnName(int columnIndex) {
        return (String) this.columnIdentifiers.get(columnIndex);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        /*return switch (columnIndex) {
            case 0 -> String.class;
            case 1 -> String.class;
            case 2 -> String.class;
            default -> String.class;
        };*/
        return String.class;
    }
    @Override
    public void addTableModelListener(TableModelListener l) {
        //super.addTableModelListener(l);
    }

    @Override
    public void removeTableModelListener(TableModelListener l) {
        //super.removeTableModelListener(l);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
