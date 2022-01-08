package sample;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;

import java.io.IOException;
import java.util.Scanner;

public class Port {
    SerialPort[] portList;
    SerialPort port;

    public SerialPort[] getPortList() {
        SerialPort[] portList = SerialPort.getCommPorts();
        return portList;
    }

    public void setPort(String setPortName) {
        port = SerialPort.getCommPort(setPortName);
        port.setComPortParameters(9600, 8, 1, 0);
        port.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);
        //port.setComPortParameters(9600, 8, 1, 0);
        //port.setComPortTimeouts(SerialPort.TIMEOUT_SCANNER, 0, 0);
       /* Scanner in = new Scanner(port.getInputStream());
        //boolean RecevidFinal -> received;
        port.addDataListener(new SerialPortDataListener() {
            @Override
            public int getListeningEvents() {
                return SerialPort.LISTENING_EVENT_DATA_AVAILABLE; // return 1
            }

            @Override
            public void serialEvent(SerialPortEvent serialPortEvent) {
                //InputStream is = port.getInputStream();
                String data = "";
                data = in.nextLine();
                System.out.println("Arduino response :" + data);
                Controller.dataRecived.appendText(data);
                Controller.dataRecived.appendText("\n");

            }
        });*/
    }

    public Boolean openPort() {
        //System.out.println("open port" + port.openPort());
        return port.openPort();
    }

    public void sendData(String data) {
        try {
            port.getOutputStream().write(data.getBytes());
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void dataRecived(Port port) {
        Scanner in = new Scanner(port.port.getInputStream());
        //boolean RecevidFinal -> received;
        port.port.addDataListener(new SerialPortDataListener() {
            @Override
            public int getListeningEvents() {
                return SerialPort.LISTENING_EVENT_DATA_AVAILABLE; // return 1
            }

            @Override
            public void serialEvent(SerialPortEvent serialPortEvent) {
                //InputStream is = port.getInputStream();
                String dataRecived = "";
                dataRecived = in.nextLine();
                System.out.println("Arduino response :" + dataRecived);


            }
        });
        // return dataRecived;
    }
}
