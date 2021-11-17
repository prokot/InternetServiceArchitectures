/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.pg.eti.aui.aui.weaponType.repository;

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
public class WeaponTypeRepository implements Repository<WeaponType, String> {
    private DataStorage storage;
    
    @Autowired
    public WeaponTypeRepository(DataStorage storage){
        this.storage = storage;
    }
    
    @Override
    public Optional<WeaponType> find(String name) {
        return storage.findWeaponType(name);
    }

    @Override
    public List<WeaponType> findAll() {
        return storage.findAllWeaponTypes();
    }

    @Override
    public void create(WeaponType entity) {
        storage.createWeaponType(entity);
    }

    @Override
    public void delete(WeaponType entity) {
        storage.deleteWeaponType(entity.getName());
    }
}
