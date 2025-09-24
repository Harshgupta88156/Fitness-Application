package com.fitness.Service;


import com.fitness.Repository.UserRepository;
import com.fitness.dto.RegisterRequest;
import com.fitness.dto.UserResponse;
import com.fitness.models.User;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository repository;


    public UserResponse getUserResponseById(String id){
        User user = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("User Not Found"));

        UserResponse response = new UserResponse();
        response.setId(user.getId());
        response.setEmail(user.getEmail());
        response.setPassword(user.getPassword());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setCreatedAt(user.getCreatedAt());
        response.setUpdateAt(user.getUpdateAt());
        return response;

    }

    public UserResponse register(RegisterRequest registerRequest){
    User user = new User();


    if(repository.existsByEmail(registerRequest.getEmail())){
        throw new RuntimeException("User already exists");
    }

    user.setEmail(registerRequest.getEmail());
    user.setPassword(registerRequest.getPassword());
    user.setFirstName(registerRequest.getFullName());
    user.setLastName(registerRequest.getLastName());

    User savedUser = repository.save(user);
    UserResponse response = new UserResponse();
    response.setId(savedUser.getId());
    response.setEmail(savedUser.getEmail());
    response.setPassword(savedUser.getPassword());
    response.setFirstName(savedUser.getFirstName());
    response.setLastName(savedUser.getLastName());
    response.setCreatedAt(user.getCreatedAt());
    response.setUpdateAt(user.getUpdateAt());

    return response;
    }


    public Boolean existsByUserId(String id) {

        return repository.existsById(id);

    }
}
