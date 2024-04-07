package com.balkis.delivery.controllers;

import com.balkis.delivery.models.Client;
import com.balkis.delivery.models.Message;
import com.balkis.delivery.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/messages")
public class MessageController {

    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/send")
    public ResponseEntity<Map<String, String>> sendMessage(@RequestBody Message message) {
        System.out.println("Received Message: " + message);

        messageService.saveMessage(message);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Message sent successfully");

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @GetMapping("/{id}")
    public Optional<Message> getMessageById(@PathVariable Long id) {
        return messageService.getMessageById(id);
    }
    @PostMapping("/saveMessage")
    public Message saveMessage(@org.springframework.web.bind.annotation.RequestBody Message message) {
        return messageService.saveMessage(message);
    }


    @DeleteMapping("/{id}")
    public void deleteMessage(@PathVariable Long id) {
        messageService.deleteMessage(id);
    }

    @GetMapping("/all")
    public List<Message> getAllMessages() {
        // Implement logic to retrieve all messages
        return messageService.getAllMessages();
    }
}
