package com.als.serializerDeserializer;

import java.nio.ByteBuffer;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import com.als.entity.LogRecord;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.UnsupportedEncodingException;
import java.util.Map;

public class LogRecordDeserializer implements Deserializer<LogRecord> {
    private String encoding = "UTF8";

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        //Nothing to configure
    }

    @Override
    public LogRecord deserialize(String topic, byte[] data) {

        try {
            if (data == null) {
                System.out.println("Null recieved, nothing to deserialize");
                return null;
            }
            ByteBuffer buf = ByteBuffer.wrap(data);

            int sizeOfLogTS = buf.getInt();
            byte[] serializedLogTS = new byte[sizeOfLogTS];
            buf.get(serializedLogTS);
            String logTS = new String(serializedLogTS);

            int sizeOfMessage = buf.getInt();
            byte[] serializedMessage = new byte[sizeOfMessage];
            buf.get(serializedMessage);
            String message = new String(serializedMessage);

            int sizeOfDeviceIP = buf.getInt();
            byte[] serializedDeviceIP = new byte[sizeOfDeviceIP];
            buf.get(serializedDeviceIP);
            String deviceIP = new String(serializedDeviceIP);

            return new LogRecord(logTS, message, deviceIP);
        } catch (Exception e) {
            throw new SerializationException("Error when deserializing byte[] to Supplier");
        }
    }

    @Override
    public void close() {
        // nothing to do
    }
}
