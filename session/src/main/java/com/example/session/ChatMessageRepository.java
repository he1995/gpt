package com.example.session;

import com.example.common.entity.ChatMessage;
import org.springframework.data.repository.CrudRepository;

public interface ChatMessageRepository  extends CrudRepository<ChatMessage, String> {
}
