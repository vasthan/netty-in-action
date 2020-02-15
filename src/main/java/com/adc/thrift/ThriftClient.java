package com.adc.thrift;

import org.apache.thrift.protocol.TCompactProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TFramedTransport;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;

public class ThriftClient {

    public static void main(String[] args) {
        TTransport transport = new TFramedTransport(new TSocket("localhost", 8899), 1000);
        TProtocol protocol = new TCompactProtocol(transport);
        UserService.Client client = new UserService.Client(protocol);

        try {
            transport.open();

            User user = client.getByUsername("张三");
            System.out.println(user);

            User user2 = new User();
            user2.setUsername("李四");
            user2.setMarried(true);
            user2.setAge(60);
            client.save(user2);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            transport.close();
        }
    }
}
