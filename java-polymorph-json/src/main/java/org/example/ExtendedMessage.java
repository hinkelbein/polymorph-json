package org.example;

public class ExtendedMessage extends BaseMessage {
    public String additionalData;

    public ExtendedMessage(String additionalData) {
        super(ExtendedMessage.class);
        this.additionalData = additionalData;
    }

    public ExtendedMessage() {
    }
}
