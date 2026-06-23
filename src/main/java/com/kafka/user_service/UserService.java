package com.kafka.user_service;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.ObjectMapper;

@Service
public class UserService {

  private final UserRepository userRepository;

  private final KafkaTemplate<String, String> kafkaTemplate;

  public UserService(UserRepository userRepository, KafkaTemplate<String, String> kafkaTemplate) {
    this.userRepository = userRepository;
    this.kafkaTemplate = kafkaTemplate;
  }

  public void signUp(SignUpRequestDto signUpRequestDto) {
    User user = new User(
            signUpRequestDto.getEmail(),
            signUpRequestDto.getName(),
            signUpRequestDto.getPassword()
    );

    User savedUser = userRepository.save(user);

    UserSignedUpEvent userSignedUpEvent = new UserSignedUpEvent(
            savedUser.getId(),
            savedUser.getEmail(),
            savedUser.getName()
    );

    this.kafkaTemplate.send("user.signed-up", toJsonString(userSignedUpEvent));
  }

  private String toJsonString(Object object) {
    ObjectMapper objectMapper = new ObjectMapper();

    try {
      String message = objectMapper.writeValueAsString(object);

      return message;
    } catch (JacksonException e) {
      throw new RuntimeException("Failed to serialize object to JSON", e);
    }
  }
}
