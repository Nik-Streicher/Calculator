package sample;

import interfaces.options.Divide;
import interfaces.options.Minus;
import interfaces.options.Multiply;
import interfaces.options.Plus;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import interfaces.*;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private TextField firstLabel;

    @FXML
    private TextField secondLabel;


    @FXML
    private TextField resultLabel;

    private Choose<Character> choose;

    @FXML
    private ComboBox<Character> chooseBox;

    @FXML
    void calculate(ActionEvent event) {
        resultLabel.setText(choose.choose(chooseBox.getSelectionModel().getSelectedItem()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        ObservableList<Character> operations = FXCollections.observableArrayList('+', '-', '*', '/');
        chooseBox.setItems(operations);
        chooseBox.getSelectionModel().selectFirst();

        choose = (Character sign) -> {
            try {
                switch (sign) {
                    case '+':
                        Plus<Long> plus = Long::sum;
                        return String.valueOf(plus.plus(Long.parseLong(firstLabel.getText()), Long.parseLong(secondLabel.getText())));
                    case '-':
                        Minus<Long> minus = (num1, num2) -> (num1 - num2);
                        return String.valueOf(minus.minus(Long.parseLong(firstLabel.getText()), Long.parseLong(secondLabel.getText())));
                    case '*':
                        Multiply<Long> multiply = (num1, num2) -> (num1 * num2);
                        return String.valueOf(multiply.multiply(Long.parseLong(firstLabel.getText()), Long.parseLong(secondLabel.getText())));
                    case '/':
                        Divide<Long> divide = (num1, num2) -> (num1 / num2);
                        return String.valueOf(divide.divide(Long.parseLong(firstLabel.getText()), Long.parseLong(secondLabel.getText())));
                    default:
                        return "ERROR";
                }
            } catch (Exception ignored) {
                return "ERROR";
            }
        };

    }
}
