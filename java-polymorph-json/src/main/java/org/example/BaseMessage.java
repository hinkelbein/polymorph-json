package org.example;

import java.time.Instant;

public class BaseMessage {
    public String type;
    public String instant;

    public BaseMessage() {
    }

    public BaseMessage(Class type) {
        this.type = type.getSimpleName();
        instant = Instant.now().toString();
    }
    public Class TypeFromTypeString() throws ClassNotFoundException {
        var packageName = this.getClass().getPackageName();
        var className = packageName + "." + type;
        return Class.forName(className);
    }
}
