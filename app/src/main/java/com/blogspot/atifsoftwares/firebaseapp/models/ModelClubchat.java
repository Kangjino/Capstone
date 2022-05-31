package com.blogspot.atifsoftwares.firebaseapp.models;

public class ModelClubchat {
    String name, chat;

    public ModelClubchat(String name, String chat) {
        this.name = name;
        this.chat = chat;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChat() {
        return chat;
    }

    public void setChat(String chat) {
        this.chat = chat;
    }
}
