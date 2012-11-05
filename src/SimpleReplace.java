import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import javax.swing.*;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class SimpleReplace {

    public static void main(String[] args) {

        String lettersAndDigits = "abcdefghijklmnopqrstuvwxyz1234567890";

        BiMap<Character, Double> rate = HashBiMap.create(new HashMap<Character, Double>(){

            {
                put('a', 8.167);put('b', 1.492);put('c', 2.782);put('d', 4.253);put('e', 12.702);
                put('f', 2.228);put('g', 2.015);put('h', 6.094);put('i', 6.966);put('j', 0.153);
                put('k', 0.772);put('l', 4.025);put('m', 2.406);put('n', 6.749);put('o', 7.507);
                put('p', 1.929);put('q', 0.095);put('r', 5.987);put('s', 6.327);put('t', 9.056);
                put('u', 2.758);put('v', 0.978);put('w', 2.360);put('x', 0.150);put('y', 1.974);
                put('z', 0.074);
            }
        });

        Map sortedFreq = MapUtils.sortByValue(rate);

        BiMap<Double,Character> inverseRate = rate.inverse();
        Map<Double,Character> sortedInverseRate = MapUtils.sortByValue(inverseRate);


        MapUtils.printMap(sortedInverseRate);
        System.out.println("=================================================");

        final String message = "nci3 y 2p7, h2p g2b 24pc m37xz njbs5bz 7pbz i4vscl d4cy43o3jbme " +
                "y mpyc4uc66p 6sc635p7xz d3426c5py, 5pri3 1bu5b n4puc6x.;";

        final Map<Character, Double> messageRate = new HashMap<Character, Double>();
        for (int i = 0; i < message.length(); i++){
            Character character = message.charAt(i);
            if (lettersAndDigits.contains(character.toString())){
                // only letters and digits
                if (messageRate.containsKey(character)) messageRate.put(character, messageRate.get(character)+1);
                    else messageRate.put(character,1d);
            }
        }

        Double length = new Double(message.length());
        for (Character key : messageRate.keySet()){
            messageRate.put(key, messageRate.get(key) / length * 100);
        }

        System.out.println("==========Message Characters Rate, %==================");


        MapUtils.printMap(messageRate);











        final Map<Character,Double> sortedMessageRate = MapUtils.sortByValue(messageRate);

        System.out.println("=======================Sorted===================");
        MapUtils.printMap(sortedMessageRate);


        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame jFrame = new JFrame();
                jFrame.setLayout(new BorderLayout());
                JTable jTable = new JTable();

                final JTextArea jTextArea = new JTextArea();
                jTextArea.append(message);
                jTextArea.setPreferredSize(new Dimension(600,50));
                jFrame.add(jTextArea,BorderLayout.NORTH );

                final JTextArea jTextArea2 = new JTextArea();

                jTextArea2.setPreferredSize(new Dimension(600,50));
                jFrame.add(jTextArea2,BorderLayout.SOUTH );

                JButton jButton = new JButton("replace");
                jButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        jTextArea2.setText("");
                        jTextArea2.setText(replace());
                    }

                    private String replace() {
                        StringBuilder sb = new StringBuilder();
                        String src = jTextArea.getText();
                        for (int i = 0; i < src.length(); i++){
                            Character character = src.charAt(i);

                        }
                        return "new value";
                    }
                });
                jFrame.add(jButton, BorderLayout.WEST);

                DefaultTableModel defaultTableModel = new DefaultTableModel();
                jTable.setModel(defaultTableModel);
                defaultTableModel.setRowCount(messageRate.size());
                defaultTableModel.setColumnCount(3);
                TableColumn column = jTable.getColumnModel().getColumn(2);
                Character[] values = new Character[] {'a', 'b', 'c', 'd','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
                column.setCellRenderer(new MyComboBoxCellRenderer(values));
                column.setCellEditor(new MyComboBoxEditor(values));
                jFrame.add(jTable, BorderLayout.EAST);

                int i = 0;
                for (Character character : sortedMessageRate.keySet()){
                    defaultTableModel.setValueAt(character.toString(), i, 0);
                    defaultTableModel.setValueAt(sortedMessageRate.get(character).toString(), i, 1);
                    JComboBox jComboBox = new JComboBox<Character>(new DefaultComboBoxModel<Character>());
                    defaultTableModel.setValueAt(jComboBox, i++, 2 );
                }



                jFrame.setVisible(true);
            }


        });


        Set<Double> usedSet = new HashSet<Double>();


        StringBuilder newMessage = new StringBuilder();
        for (int i = 0 ; i < message.length(); i++){
            Character character = message.charAt(i);
            if (lettersAndDigits.contains(character.toString())){
            Double charFreq = sortedMessageRate.get(character);
            Double minDelta = 100d;
            Character minValue;
            boolean moreThanOne = false;
            for (Double otherFreq : inverseRate.keySet()){
                Double d = charFreq - otherFreq;
                if (usedSet.contains(otherFreq)){

                }
                if (d < minDelta && !usedSet.contains(otherFreq)) { minDelta = d; minValue = inverseRate.get(minDelta); usedSet.add(otherFreq); }
                if (minDelta == d) { moreThanOne = true; return; }
            }

            if (!moreThanOne) {
                //newMessage.append()
            }
            }


        }


    }




    private static class MyComboBoxCellRenderer extends  JComboBox<Character> implements TableCellRenderer {
        public MyComboBoxCellRenderer(Character[] characters) {

        }




        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            if (isSelected) {
                setForeground(table.getSelectionForeground());
                super.setBackground(table.getSelectionBackground());
            } else {
                setForeground(table.getForeground());
                setBackground(table.getBackground());
            }

            // Select the current value
            setSelectedItem(value);
            return this;
        }


    }

    private static class MyComboBoxEditor extends  DefaultCellEditor{
        public MyComboBoxEditor(Character[] items) {
            super(new JComboBox(items));
            addCellEditorListener(new CellEditorListener() {
                @Override
                public void editingStopped(ChangeEvent e) {
                    System.err.println("editing stopped");

                }

                @Override
                public void editingCanceled(ChangeEvent e) {
                    System.err.println("editing cancelled");
                }


            });
        }


    }
}
