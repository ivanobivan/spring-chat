package ru.messenger;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import jdk.nashorn.internal.ir.debug.JSONWriter;
import ru.messenger.client.Client;

import java.io.*;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class JsonTransform {
    private static int PORT;
    private static String LOCAL_IP;
    private static JsonReader jsonReader;
    private static Gson gson;

    public static int getPORT() throws IOException {
        jsonReader = new JsonReader(new FileReader("src/main/resources/parameters.json"));
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            if (JsonToken.NAME.equals(jsonReader.peek())) {
                if ("PORT".equals(jsonReader.nextName())) {
                    PORT = jsonReader.nextInt();
                    break;
                }
            }
        }
        return PORT;
    }

    public static String getLocalIp() throws IOException {
        jsonReader = new JsonReader(new FileReader("src/main/resources/parameters.json"));
        jsonReader.beginObject();
        while (jsonReader.hasNext()) {
            if (JsonToken.NAME.equals(jsonReader.peek())) {
                if ("LOCAL_IP".equals(jsonReader.nextName())) {
                    LOCAL_IP = jsonReader.nextString();
                    break;
                }jsonReader.nextString();
            }
        }
        return LOCAL_IP;
    }

    public static void putClientDataToJson(ArrayList<Client> arrayList) throws IOException {
        JsonWriter jsonWriter = new JsonWriter(new FileWriter("src/main/resources/clientInfo.json"));
        jsonWriter.beginObject();
        for(Client client : arrayList){
            jsonWriter.name(client.getUserName());
            jsonWriter.beginArray();
            jsonWriter.value(client.getCurrentDate());
            jsonWriter.value(client.getIp());
            jsonWriter.endArray();
        }
        jsonWriter.endObject();
        jsonWriter.close();

    }
}
