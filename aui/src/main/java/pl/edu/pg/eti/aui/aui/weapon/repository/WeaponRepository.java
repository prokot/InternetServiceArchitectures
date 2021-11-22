/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.pg.eti.aui.aui.weapon.repository;

import org.springframework.beans.factory.annotation.Autowired;
import pl.edu.pg.eti.aui.aui.weapon.entity.Weapon;
import pl.edu.pg.eti.aui.aui.weaponType.entity.WeaponType;
import pl.edu.pg.eti.aui.aui.DataStorage;
import pl.edu.pg.eti.aui.aui.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 *
 * @author harmi
 */
@org.springframework.stereotype.Repository
public class WeaponRepository implements Repository<Weapon,String>{
    
    private DataStorage storage;
    
    @Autowired
    public WeaponRepository(DataStorage storage){
        this.storage = storage;
    }
    
    @Override
    public Optional<Weapon> find(String name) {
        return storage.findWeapon(name);
    }

    @Override
    public List<Weapon> findAll() {
        return storage.findAllWeapons();
    }

    @Override
    public void create(Weapon entity) {
        storage.createWeapon(entity);
    }

    @Override
    public void delete(Weapon entity) {
        storage.deleteWeapon(entity.getName());
    }


}
