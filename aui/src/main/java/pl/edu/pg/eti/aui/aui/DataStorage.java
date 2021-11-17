package pl.edu.pg.eti.aui.aui;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author harmi
 */

import pl.edu.pg.eti.aui.aui.weaponType.entity.WeaponType;
import pl.edu.pg.eti.aui.aui.weapon.entity.Weapon;


import org.springframework.stereotype.Component;

import java.util.*;


@Component
public class DataStorage {
    
    private Set<Weapon> weapons = new HashSet<>();
    
    private Set<WeaponType> weaponTypes = new HashSet<>();
    
    public synchronized List<Weapon> findAllWeapons(){
        return new ArrayList<>(weapons);
    }
    
    public synchronized Optional<Weapon> findWeapon(String name) {
        
        return weapons.stream().filter(weapon -> weapon.getName().equals(name)).findFirst();
    }

    public synchronized void createWeapon(Weapon weapon) throws IllegalArgumentException {
        findWeapon(weapon.getName()).ifPresentOrElse(
                original -> {
                    throw new IllegalArgumentException(String.format("The weapon name \"%s\" is not unique", weapon.getName()));
                }, () -> weapons.add(weapon));
    }

    public synchronized void deleteWeapon(String name) throws IllegalArgumentException {
        findWeapon(name).ifPresentOrElse(
                original -> weapons.remove(original),
                () -> {
                    throw new IllegalArgumentException(String.format("The weapon name \"%s\" does not exist", name));
                });
    }
    
    
    public synchronized List<WeaponType> findAllWeaponTypes(){
        return new ArrayList<>(weaponTypes);
    }
    
    public synchronized Optional<WeaponType> findWeaponType(String name) {
        
        return weaponTypes.stream().filter(weaponType -> weaponType.getName().equals(name)).findFirst();
    }

    public synchronized void createWeaponType(WeaponType weaponType) throws IllegalArgumentException {
       findWeaponType(weaponType.getName()).ifPresentOrElse(
                original -> {
                    throw new IllegalArgumentException(String.format("The weaponType name \"%s\" is not unique", weaponType.getName()));
       }, () -> weaponTypes.add(weaponType));
    }
    
    public synchronized void deleteWeaponType(String name) throws IllegalArgumentException {
        findWeaponType(name).ifPresentOrElse(
                original -> weaponTypes.remove(original),
                () -> {
                    throw new IllegalArgumentException(String.format("The weaponType name \"%s\" does not exist", name));
                });

    }
}
