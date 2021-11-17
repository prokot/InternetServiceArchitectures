/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.pg.eti.aui.aui.weaponType.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pg.eti.aui.aui.weaponType.entity.WeaponType;

/**
 *
 * @author harmi
 */
@org.springframework.stereotype.Repository
public interface WeaponTypeRepository extends JpaRepository<WeaponType,String> {
    //void deleteByIdInWeapons(List<Weapon> weapons);
}
