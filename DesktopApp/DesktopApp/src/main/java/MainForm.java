import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainForm {

    JPanel mainPanel;
    private JTextField lastName;
    private JTextField firstName;
    private JTextField fatherName;
    private JTextField FIO;
    private JButton button;
    private JLabel log;

    boolean buttonPress;

    public MainForm() {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                log.setText("");

                if (buttonPress) {
                    if (checkFIO()) {
                        buttonPress = !buttonPress;
                        button.setText("Expand");
                        setVisible(buttonPress);
                        splitFIO();
                    }
                } else {
                    if (checkFieldFIO()) {
                        buttonPress = !buttonPress;
                        button.setText("Collapse");
                        setVisible(buttonPress);
                        FIO.setText(firstName.getText().trim() + " " +
                                lastName.getText().trim() + " " +
                                fatherName.getText().trim());
                    }
                }
            }
        });
    }

    private void setVisible(boolean visible) {
        lastName.setVisible(!visible);
        firstName.setVisible(!visible);
        fatherName.setVisible(!visible);
        FIO.setVisible(visible);
    }

    private boolean checkFIO() {
        String regex = "[a-zA-Zа-яА-Я]+\s+[a-zA-Zа-яА-Я]+[a-zA-Zа-яА-Я\s]*";
        if (FIO.getText().matches(regex)) {
            return true;
        }
        log.setText("Поле ФИО заполнено не верно");
        return false;
    }

    private boolean checkFieldFIO() {
        String regex = "[a-zA-Zа-яА-Я]+\s*";
        boolean result = true;
        if (!firstName.getText().matches(regex)) {
            log.setText("Полe с именем заполнено не верно");
            result = false;
        }
        if (!lastName.getText().matches(regex)) {
            String logText = log.getText();
            log.setText(logText +
                    (logText.length() > 0 ? System.lineSeparator() : "") +
                    "Полe с фамилией заполнено не верно");
            result = false;
        }
        return result;
    }

    private void splitFIO() {
        String[] strings = FIO.getText().trim().split("\s+");
        switch (strings.length) {
            case 2 -> {
                firstName.setText(strings[0].trim());
                lastName.setText(strings[1].trim());
                fatherName.setText("");
            }
            case 3 -> {
                firstName.setText(strings[0].trim());
                lastName.setText(strings[1].trim());
                fatherName.setText(strings[2].trim());
            }
            default -> {
                log.setText("Поле ФИО заполнено не верно");
                firstName.setText("");
                lastName.setText("");
                fatherName.setText("");
            }
        }
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public void setMainPanel(JPanel mainPanel) {
        this.mainPanel = mainPanel;
    }

}
