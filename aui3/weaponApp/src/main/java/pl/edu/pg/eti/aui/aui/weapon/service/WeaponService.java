/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.pg.eti.aui.aui.weapon.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.edu.pg.eti.aui.aui.weapon.repository.WeaponRepository;
import pl.edu.pg.eti.aui.aui.weapon.entity.Weapon;
import pl.edu.pg.eti.aui.aui.weaponType.entity.WeaponType;
import pl.edu.pg.eti.aui.aui.weaponType.repository.WeaponTypeRepository;


import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author harmi
 */
@Service
public class WeaponService {

    private WeaponRepository repository;

    private WeaponTypeRepository weaponTypeRepository;

    @Autowired
    public WeaponService(WeaponRepository repository,WeaponTypeRepository weaponTypeRepository) {
        this.repository = repository;
        this.weaponTypeRepository = weaponTypeRepository;
    }

    public Optional<Weapon> find(String name) {
        return repository.findById(name);
    }

    public List<Weapon> findByWeaponType(WeaponType weaponType) {
        return repository.findAllByWeaponType(weaponType);
    }


    public Optional<Weapon> find(String weaponTypeName, String name) {
        Optional<WeaponType> weaponType = weaponTypeRepository.findById(weaponTypeName);
        if (weaponType.isPresent()) {
            return repository.findByNameAndWeaponType(name, weaponType.get());
        } else {
            return Optional.empty();
        }
    }


    public List<Weapon> findAll() {
        return repository.findAll();
    }

    @Transactional
    public Weapon create(Weapon weapon) {
        return repository.save(weapon);
    }
    @Transactional
    public void update(Weapon weapon) {
        repository.save(weapon);
    }
    @Transactional
    public void delete(String weapon) {
        repository.deleteById(weapon);
    }

}
