package com.example.gpt.session;

import org.springframework.data.repository.CrudRepository;

public interface ChatMessageRepository  extends CrudRepository<ChatMessage, String> {
}
