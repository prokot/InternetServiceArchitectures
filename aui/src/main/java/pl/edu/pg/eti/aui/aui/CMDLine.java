/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.pg.eti.aui.aui;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import pl.edu.pg.eti.aui.aui.weapon.entity.Weapon;
import pl.edu.pg.eti.aui.aui.weapon.service.WeaponService;
import pl.edu.pg.eti.aui.aui.weaponType.entity.WeaponType;
import pl.edu.pg.eti.aui.aui.weaponType.service.WeaponTypeService;

@Component
public class CMDLine implements CommandLineRunner{
    
    private WeaponService weaponService;
    private WeaponTypeService weaponTypeService;
    
    @Autowired
    public CMDLine(WeaponService weaponservice,WeaponTypeService weaponTypeService){
        this.weaponService = weaponservice;
        this.weaponTypeService = weaponTypeService;
    }
    
    @Override
    public void run(String... args) throws Exception{
        weaponService.findAll().forEach(System.out::println);
        weaponTypeService.findAll().forEach(System.out::println);
        Scanner scanner = new Scanner(System.in);
        printHelp();
        while(true){
            String command = scanner.nextLine();
            scanner.useLocale(Locale.ENGLISH);
            switch (command) {
                case "help": printHelp();
                break;
                case "listWeaponTypes": weaponTypeService.findAll().forEach(System.out::println);
                break;
                case "listWeapons": weaponService.findAll().forEach(System.out::println);
                break;
                case "addWeapon": addWeapon(scanner);
                break;
                case "deleteWeapon": deleteWeapon(scanner);
                break;
                case "exit": System.exit(1);
                break;
                default: System.out.println("Invalid command.");
                printHelp();
                break;
            }

        }
    }
    public void addWeapon(Scanner scanner){
            try {
                System.out.println("Please type the name of the new weapon: ");
                String name = scanner.nextLine();
                System.out.println("Please type the price of the new weapon: ");
                Double price = scanner.nextDouble();
                System.out.println("Please type the weight of the new weapon: ");
                Double weight = scanner.nextDouble();
                System.out.println("Please type the length of the new weapon: ");
                Double length = scanner.nextDouble();
                System.out.println("Please type the material of the new weapon: ");
                String material = scanner.nextLine();
                System.out.println("Please type the weapon type from ones listed below: ");
                weaponTypeService.findAll().forEach(type -> System.out.println(" - " + type.getName()));
                String type = scanner.nextLine();
                Weapon weapon = Weapon.builder()
                        .name(name)
                        .price(price)
                        .length(length)
                        .weight(weight)
                        .material(material)
                        .weaponType(weaponTypeService.find(type).orElseThrow())
                        .build();
                weaponService.create(weapon);
            }catch(Exception ex){
                System.out.println("Invalid data/weapon type.");
            }
        }


    public void deleteWeapon(Scanner scanner){
        System.out.println("Enter the name of weapon you would like to delete.");
        String name = scanner.nextLine();
        try{
            weaponService.delete(name);
        }catch(Exception ex){
            System.out.println("Invalid weapon name.");
        }


    }
    public void printHelp(){
        System.out.print("Enter a command from the list:\n" +
                "   help - displays this message;\n" +
                "   listWeaponTypes - lists all weapon types;\n" +
                "   listWeapons - lists all weapons;\n" +
                "   addWeapon - allows adding a new weapon;\n" +
                "   deleteWeapon - allows to delete weapon;\n" +
                "   exit - exists the app;\n");

    }
}
