package com.example.gpt.repository;

import com.example.gpt.entity.ChatSession;
import org.springframework.data.repository.CrudRepository;

public interface ChatSessionRepository extends CrudRepository<ChatSession, String> {
}
