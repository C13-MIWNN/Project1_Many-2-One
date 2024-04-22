package nl.mitw.ch13.many2one.ctrlalteat.repositories;

import nl.mitw.ch13.many2one.ctrlalteat.model.CtrlAltEatUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CtrlAltEatUserRepository extends JpaRepository<CtrlAltEatUser, Long> {
    Optional<CtrlAltEatUser> findByUsername(String username);
}
