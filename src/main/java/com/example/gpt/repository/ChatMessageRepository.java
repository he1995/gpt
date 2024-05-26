package com.example.gpt.repository;

import com.example.gpt.entity.ChatMessage;
import org.springframework.data.repository.CrudRepository;

public interface ChatMessageRepository  extends CrudRepository<ChatMessage, String> {
}
