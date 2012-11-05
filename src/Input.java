import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: anton
 * Date: 11/5/12
 * Time: 1:38 AM
 * To change this template use File | Settings | File Templates.
 */
public class Input {
    private JTable table1;
    private JPanel panel1;
    private JTextArea textArea1;
    private JTextArea textArea2;
    private JButton button1;
    private JButton button2;
    private final DefaultTableModel defaultTableModel;


    public Input() {

        defaultTableModel = new DefaultTableModel();
        table1.setModel(defaultTableModel);
        textArea1.setText("nci3 y 2p7, h2p g2b 24pc m37xz njbs5bz 7pbz 7pbz i4vscl d4cy43o3jbme y mpyc4uc66p 6cs635p7xz d3426c4py, 5pri3 1bu5b n4puc6x.;");



        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                String text = textArea1.getText();
                Set<Character> characters = new HashSet<Character>();
                for (int i = 0; i < text.length(); i++){
                    characters.add(text.charAt(i));
                }

                int i = 0;

                String lettersAndDigits = "abcdefghijklmnopqrstuvwxyz1234567890";
                final Map<Character, Double> messageRate = new HashMap<Character, Double>();
                for (int j = 0; j<text.length(); j++){
                    Character character = text.charAt(j);
                    //Character character = text.charAt(i);
                    if (lettersAndDigits.contains(character.toString())){
                        // only letters and digits
                        if (messageRate.containsKey(character)) messageRate.put(character, messageRate.get(character)+1);
                        else messageRate.put(character,1d);
                    }
                }

                Double length = new Double(text.length());
                for (Character key : messageRate.keySet()){
                    messageRate.put(key, messageRate.get(key) / length * 100);
                }

                defaultTableModel.setRowCount(characters.size());
                defaultTableModel.setColumnCount(3);
                for (Character character : characters){

                    defaultTableModel.setValueAt(character, i, 0);
                    defaultTableModel.setValueAt(character, i, 1);
                    defaultTableModel.setValueAt(messageRate.get(character), i++ , 2);
                }





            }
        });
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                int count = defaultTableModel.getRowCount();

                String result = textArea1.getText();

                for (int i = 0; i < count; i++){
                    try{
                    result = result.replace((Character)defaultTableModel.getValueAt(i, 0),((Character)defaultTableModel.getValueAt(i, 1)));
                    }
                    catch (Exception ex){
                        result = result.replace((Character)defaultTableModel.getValueAt(i, 0),((String)defaultTableModel.getValueAt(i, 1)).charAt(0));
                    }
                }
                textArea2.setText(result);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Input");
        frame.setContentPane(new Input().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
