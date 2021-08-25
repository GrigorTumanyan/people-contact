package eu.contacts.peoplecontact.service;

import java.util.List;

import eu.contacts.peoplecontact.dto.UserDto;
import eu.contacts.peoplecontact.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserDto getUserByEmail(String email);
    UserDto addUser(UserDto user);
    void deleteUserByEmail(String email);
    UserDto updateUserByEmail(String email);
    public List<UserDto> getAllUsers();

}
