package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static ObjectMapper mapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .configure(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES, false);

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        if (args.length > 0) {
            var reader = new BufferedReader(new InputStreamReader(System.in));
            while (true) {
                var line = reader.readLine();
                if (null == line) {
                    break;
                }
                handle(line);
            }
            return;
        }

        var extendedMessage = new ExtendedMessage("some additional +- data");
        var json = serialize(extendedMessage);
        handle(json);
        var anotherJson = "{\"type\":\"AnotherMessage\",\"instant\":\"2023-09-13T16:17:41.560687400Z\",\"i\":\"42\"}";
        handle(anotherJson);
        var stillAnotherJsonWithMissingFields = "{\"type\":\"AnotherMessage\",\"instant\":\"2023-09-13T16:17:41.560687400Z\"}";
        handle(stillAnotherJsonWithMissingFields);
    }

    private static void handle(String json) throws JsonProcessingException, ClassNotFoundException {
        var o = deserialize(json);
        var j = serialize(o);
        print(j);
    }

    private static Object deserialize(String json) throws JsonProcessingException, ClassNotFoundException {
        var base = mapper.readValue(json, BaseMessage.class);
        var v = mapper.readValue(json, base.TypeFromTypeString());
        return v;
    }

    private static String serialize(Object o) throws JsonProcessingException {
        var json = mapper.writeValueAsString(o);
        return json;
    }

    private static void print(String s, Object... args) {
        System.out.printf((s) + "%n", args);
    }
}


