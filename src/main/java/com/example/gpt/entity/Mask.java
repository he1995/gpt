package com.example.gpt.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Mask {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String avatar;

    private String name;

    @OneToMany(mappedBy = "mask", cascade = CascadeType.PERSIST)
    private List<ChatMessage> context;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ChatMessage> getContext() {
        return context;
    }

    public void setContext(List<ChatMessage> mesageList) {
        this.context = mesageList;
    }
}
