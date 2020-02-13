package com.adc.protobuf;

import com.google.protobuf.InvalidProtocolBufferException;

public class ProtoBufTest {
    public static void main(String[] args) throws InvalidProtocolBufferException {

        DataInfo.Student student = DataInfo.Student.newBuilder()
                .setName("vasthan")
                .setAge(28)
                .setAddress("beijing")
                .build();

        byte[] byteArray = student.toByteArray();

        DataInfo.Student student2 = DataInfo.Student.parseFrom(byteArray);

        System.out.println(student2.getName());
        System.out.println(student2.getAge());
        System.out.println(student2.getAddress());

    }
}
