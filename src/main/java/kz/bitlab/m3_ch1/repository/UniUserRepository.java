package kz.bitlab.m3_ch1.repository;

import kz.bitlab.m3_ch1.entities.UniUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UniUserRepository extends JpaRepository<UniUser, Long> {
    UniUser findByEmail(String email);
}
