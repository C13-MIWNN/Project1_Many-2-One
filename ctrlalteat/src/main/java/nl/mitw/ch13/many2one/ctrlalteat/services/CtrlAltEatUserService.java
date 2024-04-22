package nl.mitw.ch13.many2one.ctrlalteat.services;

import nl.mitw.ch13.many2one.ctrlalteat.dtos.CtrlAltEatUserFormDTO;
import nl.mitw.ch13.many2one.ctrlalteat.model.CtrlAltEatUser;
import nl.mitw.ch13.many2one.ctrlalteat.repositories.CtrlAltEatUserRepository;
import nl.mitw.ch13.many2one.ctrlalteat.services.mappers.CtrlAltEatUserMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * Author Linda Munsterman
 * purpose for the class
 **/

@Service
public class CtrlAltEatUserService implements UserDetailsService {
    private final CtrlAltEatUserRepository ctrlAltEatUserRepository;
    private final PasswordEncoder passwordEncoder;

    public CtrlAltEatUserService(CtrlAltEatUserRepository ctrlAltEatUserRepository, PasswordEncoder passwordEncoder) {
        this.ctrlAltEatUserRepository = ctrlAltEatUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return ctrlAltEatUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }

    public void saveUser(CtrlAltEatUser user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        ctrlAltEatUserRepository.save(user);
    }

    public boolean isNotInitialised() {
        return ctrlAltEatUserRepository.count() == 0;
    }

    public boolean userExists(String username) {
        return ctrlAltEatUserRepository.findByUsername(username).isPresent();
    }

        public void saveUser(CtrlAltEatUserFormDTO dto) {
        saveUser(CtrlAltEatUserMapper.fromDTO(dto));
    }

}
