package pl.edu.pg.eti.aui.aui.weaponType.dto;


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

    private String name;

    private String attackType;

    private int inventionYear;


    public static Function<CreateWeaponTypeRequest, WeaponType> dtoToEntityMapper() {
        return request -> WeaponType.builder()
                .name(request.getName())
                .attackType(request.getAttackType())
                .inventionYear(request.getInventionYear())
                .build();
    }


}
