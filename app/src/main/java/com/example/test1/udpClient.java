package com.example.test1;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.charset.StandardCharsets;

public class udpClient {
    public void udpClientOn() throws IOException {
        System.out.println("Этап 1");
        new Thread(new Runnable() {                             //работа с сетью должна запускаться в отдельном потоке
            @Override
            public void run() {

                try {

                    int service_port = 8080;                     //порт для UDP соединения

                    DatagramSocket clientSocket = new DatagramSocket();                 //переменная DatagramSocket, чтобы создать сокет для обмена данных
                    //InetAddress IPAddress = InetAddress.getByName("localhost");         //получить IP адрес сервера
                    byte[] ipAddr = new byte[] {(byte) 10, (byte) 0, (byte) 2, (byte) 2 };
                    InetAddress IPAddress = InetAddress.getByAddress(ipAddr);

                    System.out.println("IP адрес: " + IPAddress);

                    byte[] recivingDataBuffer = new byte[1024];                         //переменная для временного хранения получаемых данных (если будут задержки в приёме данных)
                    byte[] sendingDataBuffer = new byte[1024];                         //переменная для временного хранения передаваемых данных (если будут задержки в передаче данных)

                    String sentece = "Hellow from UDP client";                          //переменная с данными, для отправки серверу
                    sendingDataBuffer = sentece.getBytes();                             //преобразование данных в байты и запись данных в буфер
                    System.out.println("Данные для отправки готовы");

                    DatagramPacket sendingPacket = new DatagramPacket(sendingDataBuffer, sendingDataBuffer.length, IPAddress, service_port); //переменная, для хранения данных для отправки с использование буфера

                    //clientSocket.setBroadcast(true);

                    clientSocket.send(sendingPacket);                                  //запись sendingPacket в пакет отсылаемому серверу
                    System.out.println("Данные отправляются ...");

                    DatagramPacket recivingPacket = new DatagramPacket(recivingDataBuffer, recivingDataBuffer.length); //переменная буфер, для хранения получаемого пакета

                    clientSocket.receive(recivingPacket);                               //запись в recivingPacket полученных данных

                    String recivedData = new String(recivingPacket.getData());              //переменная для вывода на экран полученных данных
                    System.out.println("Полученные данные: " + recivedData);

                    clientSocket.close();                                               //закрытие соединения сокетов


                } catch (SocketException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
