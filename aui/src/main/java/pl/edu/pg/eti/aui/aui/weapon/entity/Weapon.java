package pl.edu.pg.eti.aui.aui.weapon.entity;

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

import java.io.Serializable;
import pl.edu.pg.eti.aui.aui.weaponType.entity.WeaponType;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class Weapon implements Serializable{
    
    private String name;
    
    private Double price;
    
    private Double weight;
    
    private Double length;
    
    private String material;
    
    private WeaponType weaponType;
    
}
