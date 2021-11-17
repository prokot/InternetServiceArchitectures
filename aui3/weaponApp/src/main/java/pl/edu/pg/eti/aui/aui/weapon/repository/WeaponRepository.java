/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.pg.eti.aui.aui.weapon.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pg.eti.aui.aui.weapon.entity.Weapon;
import pl.edu.pg.eti.aui.aui.weaponType.entity.WeaponType;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author harmi
 */
@org.springframework.stereotype.Repository
public interface WeaponRepository extends JpaRepository<Weapon,String> {

    Optional<Weapon> findByNameAndWeaponType(String name, WeaponType weaponType);

    List<Weapon> findAllByWeaponType(WeaponType weaponType);

}
