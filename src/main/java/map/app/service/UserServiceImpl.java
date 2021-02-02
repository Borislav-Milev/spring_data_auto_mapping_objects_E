package map.app.service;

import map.app.domain.dto.UserDto;
import map.app.repository.UserRepository;
import map.app.domain.dto.UserLoginDto;
import map.app.domain.dto.UserRegisterDto;
import map.app.domain.entity.User;
import map.app.domain.entity.enums.Role;
import map.app.service.contract.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static map.app.constant.Messages.*;
import static map.app.constant.Validations.*;
import static map.app.constant.Validations.NO_USER_LOGGED;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private UserDto userDto;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void registerUser(UserRegisterDto dto) {
        User user = this.modelMapper.map(dto, User.class);
        user.setRole(this.userRepository.count() == 0 ? Role.ADMIN : Role.USER);
        this.userRepository.saveAndFlush(user);
        System.out.printf(USER_REGISTERED, user.getFullName());
    }

    @Override
    public void loginUser(UserLoginDto userLoginDto) {
        if (this.userDto != null) {
            System.out.println(CANNOT_LOGIN_WHEN_OTHER_IS_LOGGED);
            return;
        }
        User user = this.findUserByEmail(userLoginDto.getEmail());

        if (user == null || !user.getPassword().equals(userLoginDto.getPassword())) {
            System.out.println(INCORRECT_NAME_PASSWORD);
            return;
        }
        this.userDto = this.modelMapper.map(user, UserDto.class);
        System.out.printf(LOGGED_IN, user.getFullName());
    }

    @Override
    public User findUserByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }

    @Override
    public void logout() {
        if (this.userDto == null) {
            System.out.println(CANNOT_LOG_OUT_NO_USER_LOGGED);
            return;
        }
        System.out.printf(LOGGED_OUT, this.userDto.getFullName());
        this.userDto = null;
    }

    @Override
    public boolean checkIfAdmin() {
        if (!this.checkIfLoggedIn()) {
            return false;
        }
        if (this.userDto.getRole().name().equals(Role.ADMIN.toString())) {
            return true;
        } else {
            System.out.println(USER_NOT_ADMIN);
            return false;
        }
    }

    @Override
    public boolean checkIfLoggedIn() {
        if (this.userDto == null) {
            System.out.println(NO_USER_LOGGED);
            return false;
        }
        return true;
    }

    @Override
    public UserDto getUserDto() {
        return this.userDto;
    }

    @Override
    public void saveUser(User user) {
        this.userRepository.saveAndFlush(user);
    }

    @Override
    @Transactional
    public void displayUserGames() {
        User user = this.userRepository.findByEmail(this.userDto.getEmail());
        user.getOwnedGames().forEach(game -> System.out.println(game.getTitle()));
    }
}
