package rs.wepublishlaws.paymentserviceprovider.security.userdetailsservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import rs.wepublishlaws.paymentserviceprovider.model.Merchant;
import rs.wepublishlaws.paymentserviceprovider.repository.MerchantRepository;

import java.util.Collection;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private MerchantRepository repository;

    @Override
    public UserDetails loadUserByUsername(String merchantId) throws UsernameNotFoundException {
        Merchant merchant = repository.findMerchantByMerchantId(merchantId);
        if (merchant == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", merchantId));
        } else {
            return new UserDetails() {
                @Override
                public Collection<? extends GrantedAuthority> getAuthorities() {
                    return null;
                }

                @Override
                public String getPassword() {
                    return merchant.getMerchantPassword();
                }

                @Override
                public String getUsername() {
                    return merchant.getMerchantId();
                }

                @Override
                public boolean isAccountNonExpired() {
                    return true;
                }

                @Override
                public boolean isAccountNonLocked() {
                    return true;
                }

                @Override
                public boolean isCredentialsNonExpired() {
                    return true;
                }

                @Override
                public boolean isEnabled() {
                    return true;
                }
            };
        }
    }
}