package map.app.service.contract;

import map.app.domain.dto.UserDto;
import map.app.domain.dto.UserLoginDto;
import map.app.domain.dto.UserRegisterDto;
import map.app.domain.entity.User;

public interface UserService {

    void registerUser(UserRegisterDto dto);

    void loginUser(UserLoginDto userLoginDto);

    User findUserByEmail(String email);

    void logout();

    boolean checkIfAdmin();

    boolean checkIfLoggedIn();

    UserDto getUserDto();

    void saveUser(User user);

    void displayUserGames();
}
