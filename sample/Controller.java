package sample;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Controller implements Initializable {
    Port port = new Port();

    @FXML
    private TextArea dataSent;

    @FXML
    private Button connectBTN;

    @FXML
    private ChoiceBox<String> chooseMenu;
    @FXML
    private TextField writeDataField;

    @FXML
    private Button sendDataField;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        SerialPort[] portList = port.getPortList();
        //System.out.print(portList);
        for (SerialPort eachPort : portList) {
            chooseMenu.getItems().add(eachPort.getSystemPortName());
            System.out.println(eachPort.getSystemPortName());
            chooseMenu.setValue(eachPort.getSystemPortName());
        }

    }

    public void setPort() {
        String dataReciving = "";
        if (chooseMenu.getValue() == null) {
            System.out.println("the port name is empty! , please write something");
        } else {
            String portName = chooseMenu.getValue();
            //port =new Port();
            port.setPort(portName);

            System.out.println("open Port:" + port.openPort());
            dataSent.appendText("open Port:" + port.openPort());
            dataSent.appendText("\n");
            port.dataRecived(port);

        }
    }


    public void sendData(){
        String data = writeDataField.getText();
        if (data == null || port.openPort() == false) {
            System.out.println("data field is null");
        } else {
            port.sendData(data);
            dataSent.appendText("data sent" + data);
            dataSent.appendText("\n");
        }
    }
/*
    public void dataRecived() {
        String dataRecive = null;
        port.sendData(dataRecive);
        if (dataRecive == null) {
            System.out.println("nothing here");
        } else {
            dataRecived.appendText("data revevied" + dataRecive);
        }
    }*/

}


