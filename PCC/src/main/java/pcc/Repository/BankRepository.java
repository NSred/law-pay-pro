package pcc.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pcc.Model.Bank;

public interface BankRepository extends JpaRepository<Bank,Long> {
}
