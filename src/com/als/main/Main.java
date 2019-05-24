package com.als.main;

import java.nio.ByteBuffer;

public class Main {
    public static void main(String[] args) {
        Person p1 = new Person("Ayush", "9849935051", 12);

        // Serializing this Person p1 object and putting it in byteBufferArray
        byte[] serializedName = p1.name.getBytes();
        int sizeOfName = serializedName.length;
        byte[] serializedPhone = p1.phone.getBytes();
        int sizeOfPhone = serializedPhone.length;

        ByteBuffer buf = ByteBuffer.allocate(4 + 4 + sizeOfName + 4 + sizeOfPhone);
        buf.putInt(p1.id);
        buf.putInt(sizeOfName);
        buf.put(serializedName);
        buf.putInt(sizeOfPhone);
        buf.put(serializedPhone);

        // Serialized data ByteBufferArray
        byte[] byteBufferArray = buf.array();

        ByteBuffer byteBuffer = ByteBuffer.wrap(byteBufferArray);
        System.out.println(byteBuffer.getInt());

        int nameSize = byteBuffer.getInt();
        byte[] nameBytes = new byte[nameSize];
        byteBuffer.get(nameBytes);
        String deserializedName = new String(nameBytes);
        System.out.println(deserializedName);

        int phoneSize = byteBuffer.getInt();
        byte[] phoneBytes = new byte[phoneSize];
        byteBuffer.get(phoneBytes);
        String deserializedPhone = new String(phoneBytes);
        System.out.println(deserializedPhone);
    }
}

class Person {
  String name;
  String phone;
  int id;

  public Person(String name, String phone, int id){
      this.name = name;
      this.phone = phone;
      this.id = id;
  }



}