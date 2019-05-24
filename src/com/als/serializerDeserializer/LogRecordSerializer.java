package com.als.serializerDeserializer;

import org.apache.kafka.common.serialization.Serializer;
import org.apache.kafka.common.errors.SerializationException;
import java.io.UnsupportedEncodingException;
import java.util.Map;
import java.nio.ByteBuffer;
import com.als.entity.LogRecord;

public class LogRecordSerializer implements Serializer<LogRecord> {
    private String encoding = "UTF8";

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {
        // nothing to configure
    }

    @Override
    public byte[] serialize(String topic, LogRecord logRecord) {
        int sizeOfLogTS;
        int sizeOfMsg;
        int sizeOfDeviceIP;
        byte[] serializedLogTS;
        byte[] serializedMsg;
        byte[] serializedDeviceIP;

        try {
            if (logRecord == null) {
                return null;
            }
            serializedLogTS = logRecord.getLogTS().getBytes(encoding);
            sizeOfLogTS = serializedLogTS.length;
            serializedMsg = logRecord.getMsg().getBytes(encoding);
            sizeOfMsg = serializedMsg.length;
            serializedDeviceIP = logRecord.getDeviceIP().getBytes(encoding);
            sizeOfDeviceIP = serializedDeviceIP.length;

            ByteBuffer buf = ByteBuffer.allocate(4 + sizeOfLogTS + 4 + sizeOfMsg + 4 + sizeOfDeviceIP);
            buf.putInt(sizeOfLogTS);
            buf.put(serializedLogTS);
            buf.putInt(sizeOfMsg);
            buf.put(serializedMsg);
            buf.putInt(sizeOfDeviceIP);
            buf.put(serializedDeviceIP);

            return buf.array();
        } catch (Exception e) {
            throw new SerializationException("Error when serializing Supplier to byte[]");
        }
    }

    @Override
    public void close() {
        // nothing to do
    }
}
