package pl.edu.pg.eti.aui.aui.weaponType.entity;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author harmi
 */
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.SuperBuilder;
import pl.edu.pg.eti.aui.aui.weapon.entity.Weapon;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
@Entity
@Table(name="weapon_types")
public class WeaponType implements Serializable{

    @Id
    private String name;

    @OneToMany(mappedBy = "weaponType", cascade = CascadeType.REMOVE)
    @ToString.Exclude
    private List<Weapon> weapons;


}
