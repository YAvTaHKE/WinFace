package ru.GUI;

import ru.FindWindow;
import ru.GUI.Form;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class WindowsList extends JFrame implements MouseListener {
    private JPanel panel1;
    private JTextField textField1;
    private JTable table1;
    private DefaultTableModel tableModel;
    private TableRowSorter<TableModel> sorter;
    private Form mainFrame;

    public WindowsList(JFrame mainFrame){
        if (mainFrame instanceof Form) this.mainFrame = (Form) mainFrame;

        setTitle("Windows name list");
        setSize(new Dimension(400, 310));
        setContentPane(panel1);
        tableModel = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int row, int column) {
                //all cells false
                return false;
            }
        };
        table1.setModel(tableModel);

        //Установка сортировщика
        sorter = new TableRowSorter<>(tableModel);
        table1.setRowSorter(sorter);
        textField1.getDocument().addDocumentListener(
                new DocumentListener() {
                    public void changedUpdate(DocumentEvent e) {
                        newFilter();
                    }
                    public void insertUpdate(DocumentEvent e) {
                        newFilter();
                    }
                    public void removeUpdate(DocumentEvent e) {
                        newFilter();
                    }
                });

        //table1.setFillsViewportHeight(true);
        table1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //получить размеры экрана
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;

        //установить положение фрейма
        setLocation(screenWidth / 3 + 608, screenHeight / 3);
        tableModel.addColumn("HWND");
        tableModel.addColumn("Window name");

        FindWindow.getWindows().forEach((k, v) ->
                tableModel.addRow(new String[]{k.toString().replace("native@", ""), v}));

        //Автонастройка ширины столбцов на основе содержимого
        table1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        int prefWidth = 0;
        JTableHeader th = table1.getTableHeader();
        for (int i = 0; i < table1.getColumnCount(); i++) {
            TableColumn column = table1.getColumnModel().getColumn(i);
            int prefWidthMax = 0;
            for (int j = 0; j <table1.getRowCount(); j++) {
                String s = table1.getModel().getValueAt(j, i).toString();
                prefWidth =
                        Math.round(
                                (float) th.getFontMetrics(
                                        th.getFont()).getStringBounds(s,
                                        th.getGraphics()
                                ).getWidth()
                        );
                if ( prefWidth > prefWidthMax ) prefWidthMax = prefWidth;


            }
            column.setPreferredWidth(prefWidthMax + 10);
        }
        table1.addMouseListener(this);
    }



    /**
     * Update the row filter regular expression from the expression in
     * the text box.
     */
    private void newFilter() {
        RowFilter<TableModel, Object> rf = null;
        //If current expression doesn't parse, don't update.
        try {
            rf = RowFilter.regexFilter(textField1.getText(), 1);
        } catch (java.util.regex.PatternSyntaxException e) {
            return;
        }
        sorter.setRowFilter(rf);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getClickCount() == 2 ){
            JTable obj = (JTable)e.getSource();
            mainFrame.setWindowName(obj.getValueAt(obj.getSelectedRow(), 1).toString());
            obj.getValueAt(obj.getSelectedRow(), 1);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
