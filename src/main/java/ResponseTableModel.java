import burp.api.montoya.http.message.requests.HttpRequest;
import burp.api.montoya.http.message.responses.HttpResponse;

import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.util.Vector;

public class ResponseTableModel extends DefaultTableModel{
    private final Vector dataVector;
    private final Vector columnIdentifiers;

    public ResponseTableModel(){
        this.columnIdentifiers = new Vector();
        this.columnIdentifiers.add("Status Code");
        this.columnIdentifiers.add("Body");
        this.dataVector = new Vector();
    }
    public void addResponse(HttpResponse response){
        Object[] row = new Object[4];
        //method
        //url
        //body
        //actual request
        row[0]=response.statusCode();
        row[1]=response;
        this.addRow(row);
    }
    public void removeRow(int item){
        this.dataVector.remove(item);
        this.fireTableDataChanged();
    }
    public HttpResponse getResponse(int index){
        return (HttpResponse) this.dataVector.get(index);
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
