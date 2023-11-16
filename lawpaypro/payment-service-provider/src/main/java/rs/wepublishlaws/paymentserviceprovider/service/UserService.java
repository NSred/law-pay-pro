package rs.wepublishlaws.paymentserviceprovider.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rs.wepublishlaws.paymentserviceprovider.model.User;
import rs.wepublishlaws.paymentserviceprovider.repository.UserRepository;

@Service
public class UserService implements  IUserService{
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public boolean existsByUsername(String username) {
        return repository.existsUserByUsername(username);
    }

    public boolean existsByEmail(String email) {
        return repository.existsUserByEmail(email);
    }

    public void save(User user) {
        repository.save(user);
    }
}
