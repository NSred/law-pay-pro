package rs.wepublishlaws.paymentserviceprovider.repository;

import rs.wepublishlaws.paymentserviceprovider.model.User;

public interface UserRepository extends EntityRepository<User> {
    boolean existsByUsername(String username);

    User findByUsername(String username);
}
