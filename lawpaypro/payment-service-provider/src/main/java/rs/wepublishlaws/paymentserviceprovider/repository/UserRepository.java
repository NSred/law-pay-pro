package rs.wepublishlaws.paymentserviceprovider.repository;

import rs.wepublishlaws.paymentserviceprovider.model.User;

public interface UserRepository extends EntityRepository<User> {
    boolean existsUserByUsername(String username);

    boolean existsUserByEmail(String email);

    User findByUsername(String username);
}
