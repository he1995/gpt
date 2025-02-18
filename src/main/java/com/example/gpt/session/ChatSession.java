package com.example.gpt.session;

import com.example.gpt.mask.Mask;
import jakarta.persistence.*;

import java.util.List;


@Entity
public class ChatSession {
    @Id
    private String id;

    private String topic;

    @OneToMany(mappedBy = "session", cascade = CascadeType.PERSIST)
    private List<ChatMessage> messages;

    private long createTime;

    private long lastUpdate;

    @OneToOne
    @JoinColumn(name = "mask_id", referencedColumnName = "id")
    private Mask mask;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public List<ChatMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<ChatMessage> messages) {
        this.messages = messages;
    }

    public long getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(long lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Mask getMask() {
        return mask;
    }

    public void setMask(Mask mask) {
        this.mask = mask;
    }
}
