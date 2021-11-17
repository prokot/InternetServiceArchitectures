package pl.edu.pg.eti.aui.aui.weapon.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;
import pl.edu.pg.eti.aui.aui.weapon.entity.Weapon;
import pl.edu.pg.eti.aui.aui.weaponType.entity.WeaponType;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.function.Function;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class GetWeaponResponse {
    private String name;

    private Double price;

    private Double weight;

    private Double length;

    private String material;

    private String weaponType;

    public static Function<Weapon, GetWeaponResponse> entityToDtoMapper() {
        return weapon -> GetWeaponResponse.builder()
                .name(weapon.getName())
                .price(weapon.getPrice())
                .weight(weapon.getWeight())
                .length(weapon.getLength())
                .material(weapon.getMaterial())
                .weaponType(weapon.getWeaponType().getName())
                .build();
    }


}
