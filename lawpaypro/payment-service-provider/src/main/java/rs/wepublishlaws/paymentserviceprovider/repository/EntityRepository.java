package rs.wepublishlaws.paymentserviceprovider.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface EntityRepository<T> extends JpaRepository<T, Long> {
    default T findOne(Long id) {
        return findById(id).orElse(null);
    }
}
