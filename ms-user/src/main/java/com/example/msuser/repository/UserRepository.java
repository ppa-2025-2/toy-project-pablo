
package com.example.msuser.repository;

import com.example.msuser.repository.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Se quiser, pode adicionar métodos de busca depois, por exemplo:
    // Optional<User> findByEmail(String email);

}
