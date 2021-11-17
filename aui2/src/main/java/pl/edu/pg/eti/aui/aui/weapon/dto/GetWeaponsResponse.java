package pl.edu.pg.eti.aui.aui.weapon.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class GetWeaponsResponse {

    @Getter
    @Setter
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    @ToString
    @EqualsAndHashCode
    public static class Weapon {
        private String name;
    }
    @Singular
    private List<Weapon> weapons;


    public static Function<Collection<pl.edu.pg.eti.aui.aui.weapon.entity.Weapon>, GetWeaponsResponse> entityToDtoMapper() {
        return weapons -> {
            GetWeaponsResponseBuilder response = GetWeaponsResponse.builder();
            weapons.stream()
                    .map(weapon -> Weapon.builder()
                            .name(weapon.getName())
                            .build())
                    .forEach(response::weapon);
            return response.build();
        };
    }

}
