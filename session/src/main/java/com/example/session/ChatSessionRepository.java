package com.example.session;

import com.example.common.entity.ChatSession;
import org.springframework.data.repository.CrudRepository;

public interface ChatSessionRepository extends CrudRepository<ChatSession, String> {
}
