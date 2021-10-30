package com.example.demo1;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTest {

    @Autowired
    private UserRepository repo;

    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreatUser(){
        User user = new User();
        user.setEmail("1949@gmail.com");
        user.setFirstName("Cas");
        user.setLastName("Che");
        user.setPassword("mao1226");

        User savedUser = repo.save(user);

        User exitUser = entityManager.find(User.class,savedUser.getId());
        assertThat(exitUser.getEmail()).isEqualTo(user.getEmail());

    }
    @Test
    public void testFindUserByEmail(){
        String email = "name@codejava.net";
        User user = repo.findByEmail(email);

        assertThat(user).isNotNull();
    }
}
