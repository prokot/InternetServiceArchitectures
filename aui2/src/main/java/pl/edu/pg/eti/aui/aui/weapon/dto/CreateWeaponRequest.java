package pl.edu.pg.eti.aui.aui.weapon.dto;


import lombok.*;
import pl.edu.pg.eti.aui.aui.weapon.entity.Weapon;
import pl.edu.pg.eti.aui.aui.weaponType.entity.WeaponType;

import java.util.function.Function;
import java.util.function.Supplier;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class CreateWeaponRequest {

    private String name;

    private Double price;

    private Double weight;

    private Double length;

    private String material;

    public static Function<CreateWeaponRequest, Weapon> dtoToEntityMapper(
            Function<String, Weapon> professionFunction,
            Supplier<WeaponType> weaponTypeSupplier) {
        return request -> Weapon.builder()
                .name(request.getName())
                .price(request.getPrice())
                .weight(request.getWeight())
                .length(request.getLength())
                .material(request.getMaterial())
                .weaponType(weaponTypeSupplier.get())
                .build();
    }



}
