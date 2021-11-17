package pl.edu.pg.eti.aui.aui.weapon.dto;


import lombok.*;
import pl.edu.pg.eti.aui.aui.weapon.entity.Weapon;

import java.util.function.BiFunction;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class UpdateWeaponRequest {

    private Double price;

    private Double weight;

    private Double length;

    private String material;

    public static BiFunction<Weapon, UpdateWeaponRequest, Weapon> dtoToEntityUpdater() {
        return (weapon, request) -> {
            weapon.setPrice(request.getPrice());
            weapon.setWeight(request.getWeight());
            weapon.setLength(request.getLength());
            weapon.setMaterial(request.getMaterial());
            return weapon;
        };
    }


}
