package com.example.employee1.service;

import com.example.employee1.model.Message;
import com.example.employee1.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {
    @Autowired
    private MessageRepository messageRepository;

    // Get all messages
    public List<Message> getAllMessages(){
        return messageRepository.findAll();
    }

    // Save a new message
    public Message saveMessage(Message message) {
        return messageRepository.save(message);
    }

    // Update an existing message
    public Message updateMessage(Long id, Message message) {
        return  messageRepository.findById(id)
                .map(existingMessage -> {
                        existingMessage.setContent(message.getContent());
                        return messageRepository.save(existingMessage);
                })
                .orElseThrow(() -> new RuntimeException("Message not found"));
    }

    // Delete a message
    public  void deleteMessage(Long id){
        messageRepository.deleteById(id);
    }
}
