package com.example.gpt.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
public class ChatMessage {
    @Id
    private String id;

    private String role;

    @Column(length = 2000)
    private String content;

    private String date;

    @ManyToOne
    @JoinColumn(name = "mask_id")
    @JsonIgnore
    private Mask mask;

    @ManyToOne
    @JoinColumn(name = "session_id")
    @JsonIgnore
    private ChatSession session;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String data) {
        this.date = data;
    }

    public Mask getMask() {
        return mask;
    }

    public void setMask(Mask mask) {
        this.mask = mask;
    }

    public ChatSession getSession() {
        return session;
    }

    public void setSession(ChatSession session) {
        this.session = session;
    }
}
