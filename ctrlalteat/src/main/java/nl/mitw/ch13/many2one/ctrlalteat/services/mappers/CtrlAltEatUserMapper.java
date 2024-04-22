package nl.mitw.ch13.many2one.ctrlalteat.services.mappers;

import nl.mitw.ch13.many2one.ctrlalteat.dtos.CtrlAltEatUserFormDTO;
import nl.mitw.ch13.many2one.ctrlalteat.model.CtrlAltEatUser;

/**
 * Author Linda Munsterman
 * purpose for the class
 **/

public class CtrlAltEatUserMapper {
    public static CtrlAltEatUser fromDTO(CtrlAltEatUserFormDTO dto) {
        CtrlAltEatUser user = new CtrlAltEatUser();
        user.setUsername(dto.getName());
        user.setPassword(dto.getPassword());
        return user;
    }
}
