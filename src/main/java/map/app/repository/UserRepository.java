package map.app.repository;

import map.app.domain.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);

    @Query("select u from User u join u.ownedGames g where g.id = ?1")
    List<User> findAllUsersWithGame(Integer id);
}
