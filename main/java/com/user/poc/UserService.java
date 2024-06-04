package com.user.poc;

public class UserService {
    private UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String getUserNameById(int id) {
        User user = userRepository.findUserById(id);
        if (user != null) {
            return user.getName();
        } else {
            return null;
        }
    }
}
