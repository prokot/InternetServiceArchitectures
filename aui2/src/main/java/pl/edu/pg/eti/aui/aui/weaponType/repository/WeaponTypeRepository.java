/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.pg.eti.aui.aui.weaponType.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.edu.pg.eti.aui.aui.weapon.entity.Weapon;
import pl.edu.pg.eti.aui.aui.weaponType.entity.WeaponType;
import pl.edu.pg.eti.aui.aui.Repository;
import pl.edu.pg.eti.aui.aui.DataStorage;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author harmi
 */
@org.springframework.stereotype.Repository
public interface WeaponTypeRepository extends JpaRepository<WeaponType,String> {
    //void deleteByIdInWeapons(List<Weapon> weapons);
}
