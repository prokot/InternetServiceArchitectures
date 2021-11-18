package pl.edu.pg.eti.aui.aui.weaponType.dto;


import lombok.*;
import pl.edu.pg.eti.aui.aui.weapon.entity.Weapon;
import pl.edu.pg.eti.aui.aui.weaponType.entity.WeaponType;

import java.util.List;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode

public class CreateWeaponTypeRequest {

    private String WeaponTypeName;

    public static Function<CreateWeaponTypeRequest, WeaponType> dtoToEntityMapper() {
        return request -> WeaponType.builder()
                .name(request.getWeaponTypeName())
                .build();
    }


}