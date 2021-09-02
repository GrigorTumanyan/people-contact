package eu.contacts.peoplecontact.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import eu.contacts.peoplecontact.dto.UserDto;
import eu.contacts.peoplecontact.entity.User;
import eu.contacts.peoplecontact.exception.ResourceNotFoundException;
import eu.contacts.peoplecontact.mapper.UserMapper;
import eu.contacts.peoplecontact.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    private final String NOT_FOUND = " Email not found";

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDto getUserByEmail(String email) {
        return userMapper.toDto(userRepository.findByEmail(email).orElseThrow(() ->
            new ResourceNotFoundException(email + NOT_FOUND)));
    }

    public List<UserDto> getAllUsers() {
        List<UserDto> allUsersDto = new ArrayList<>();
        List<User> allUsers = userRepository.findAll();
        allUsers.forEach(user -> allUsersDto.add(userMapper.toDto(user)));
//        for (User user : allUsers) {
//            allUsersDto.add(userMapper.ToDto(user));
//        }
        return allUsersDto;
    }

    @Override
    @Transactional
    public UserDto addUser(UserDto userDto) {
        if (userRepository.existsByEmail(userDto.getEmail())) {
//            stex xndir ka
//            stex xndir ka
//            stex xndir ka
//            stex xndir ka
//            stex xndir ka
            throw new RuntimeException(userDto.getEmail() + " is already exists");
        } else {
            User createdUser = userRepository.save(userMapper.toEntity(userDto));
            return userMapper.toDto(createdUser);
        }
    }

    @Override
    @Transactional
    public void deleteUserByEmail(String email) {
//        User user = userRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException(email + NOT_FOUND));
        if (userRepository.existsByEmail(email)) {
            userRepository.deleteByEmail(email);
        }else
        {
         throw new ResourceNotFoundException(email + NOT_FOUND);
        }
    }

    @Override
    @Transactional
    public UserDto updateUserByEmail(String email, UserDto userDto) {
        Optional<User> findUserByEmail = userRepository.findByEmail(email);
        if (findUserByEmail.isPresent()) {
            UserDto userDtoFromData = userMapper.toDto(findUserByEmail.get());
            userDtoFromData.setEmail(userDto.getEmail());
            userDtoFromData.setName(userDto.getName());
            User updatedUser = userRepository.save(userMapper.toEntity(userDtoFromData));
            return userMapper.toDto(updatedUser);
        }
        throw new ResourceNotFoundException(userDto.getEmail() + " not found");

    }
}
