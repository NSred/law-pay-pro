package rs.wepublishlaws.paymentserviceprovider.service;

import rs.wepublishlaws.paymentserviceprovider.model.User;

public interface IUserService {
    public boolean existsByUsername(String username) ;
    public boolean existsByEmail(String email) ;
    public void save(User user) ;
}
