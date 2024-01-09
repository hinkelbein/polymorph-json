package org.example;

public class AnotherMessage extends BaseMessage {
    public int i;

    public AnotherMessage(int i) {
        super(ExtendedMessage.class);
        this.i = i;
    }

    public AnotherMessage() {

    }
}
