package pl.edu.pg.eti.aui.aui.weaponType.event.dto;


import lombok.*;
import pl.edu.pg.eti.aui.aui.weaponType.entity.WeaponType;

import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class CreateWeaponTypeRequest {
    private String weaponTypeName;

    public static Function<WeaponType, CreateWeaponTypeRequest> entityToDtoMapper() {
        return entity -> CreateWeaponTypeRequest.builder()
                .weaponTypeName(entity.getName())
                .build();
    }




}
