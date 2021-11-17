package pl.edu.pg.eti.aui.aui.weaponType.dto;


import lombok.*;
import pl.edu.pg.eti.aui.aui.weaponType.entity.WeaponType;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class GetWeaponTypesResponse {

    @Singular
    private List<String> weaponTypes;

    public static Function<Collection<WeaponType>, GetWeaponTypesResponse> entityToDtoMapper() {
        return characters -> {
            GetWeaponTypesResponseBuilder response = GetWeaponTypesResponse.builder();
            characters.stream()
                    .map(WeaponType::getName)
                    .forEach(response::weaponType);
            return response.build();
        };
    }

}
