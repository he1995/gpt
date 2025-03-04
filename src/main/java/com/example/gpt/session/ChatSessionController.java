package com.example.gpt.session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@Controller // This means that this class is a Controller
@RequestMapping(path="/session") // This means URL's start with /mask (after Application path)
public class ChatSessionController {
    @Autowired // This means to get the bean called maskRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private ChatSessionRepository sessionRepository;

    @Autowired
    private ChatMessageRepository messageRepository;

    @PostMapping(path="/add") // Map ONLY POST Requests
    public @ResponseBody String addSession (@RequestBody ChatSession session) {
        // @ResponseBody means the rgeeturned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        sessionRepository.save(session);
        return "Saved";
    }

    @PostMapping(path="/message/add") // Map ONLY POST Requests
    public @ResponseBody String addMessage (@RequestParam String sessionId, @RequestBody ChatMessage message) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        ChatSession session = sessionRepository.findById(sessionId).get();
        message.setSession(session);
        messageRepository.save(message);
        return "Saved";
    }

    @PostMapping(path="/delete") // Map ONLY POST Requests
    public @ResponseBody String deleteSession (@RequestParam String sessionId) {
        // @ResponseBody means the returned String is the response, not a view name
        // @RequestParam means it is a parameter from the GET or POST request
        sessionRepository.deleteById(sessionId);
        return "Deleted";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<ChatSession> getAllSessions() {
        // This returns a JSON or XML with the users
        return sessionRepository.findAll();
    }
}
