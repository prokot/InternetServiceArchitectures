package pl.edu.pg.eti.aui.aui.weaponType.dto;


import lombok.*;
import pl.edu.pg.eti.aui.aui.weapon.dto.UpdateWeaponRequest;
import pl.edu.pg.eti.aui.aui.weapon.entity.Weapon;
import pl.edu.pg.eti.aui.aui.weaponType.entity.WeaponType;

import java.util.function.BiFunction;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class UpdateWeaponTypeRequest {

    private String attackType;

    private int inventionYear;

    public static BiFunction<WeaponType, UpdateWeaponTypeRequest, WeaponType> dtoToEntityUpdater() {
        return (weaponType, request) -> {
            weaponType.setAttackType(request.getAttackType());
            weaponType.setInventionYear(request.getInventionYear());
            return weaponType;
        };
    }

}
