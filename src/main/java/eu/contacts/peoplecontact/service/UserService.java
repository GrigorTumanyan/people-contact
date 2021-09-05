package eu.contacts.peoplecontact.service;

import java.util.List;
import java.util.Optional;

import eu.contacts.peoplecontact.dto.UserDto;
import eu.contacts.peoplecontact.entity.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserDto getUserByEmail(String email);
    ResponseEntity<String> addUser(UserDto user);
    void deleteUserByEmail(String email);
    UserDto updateUserByEmail(String email, UserDto userDto);
    List<UserDto> getAllUsers();

}
