package pl.edu.pg.eti.aui.aui.weaponType.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import pl.edu.pg.eti.aui.aui.weaponType.dto.CreateWeaponTypeRequest;
import pl.edu.pg.eti.aui.aui.weaponType.entity.WeaponType;
import pl.edu.pg.eti.aui.aui.weaponType.service.WeaponTypeService;

import java.util.Optional;

@RestController
@RequestMapping("api/weaponTypes")
public class WeaponTypeController {

    private WeaponTypeService weaponTypeService;
    
    @Autowired
    public WeaponTypeController(WeaponTypeService weaponTypeService) {
        this.weaponTypeService = weaponTypeService;
    }

    @DeleteMapping("{weaponTypeName}")
    public ResponseEntity<Void> deleteWeaponType(@PathVariable("weaponTypeName") String weaponTypename) {
        Optional<WeaponType> weaponType = weaponTypeService.find(weaponTypename);
        if (weaponType.isPresent()) {
            weaponTypeService.delete(weaponType.get().getName());
            return ResponseEntity
                    .accepted()
                    .build();
        } else {
            return ResponseEntity
                    .notFound()
                    .build();
        }
    }
    
    @PostMapping("")
    public ResponseEntity<Void> createWeaponType(@RequestBody CreateWeaponTypeRequest request, UriComponentsBuilder builder) {
        WeaponType weaponType = CreateWeaponTypeRequest.dtoToEntityMapper().apply(request);
        if(weaponTypeService.find(weaponType.getName()).isPresent()){
            return ResponseEntity
                    .badRequest()
                    .build();
        }
        else{
        weaponTypeService.create(weaponType);
        return ResponseEntity
                .created(builder
                        .pathSegment("api", "weaponTypes", "{weaponTypeName}")
                        .buildAndExpand(weaponType.getName()).toUri())
                .build();
        }
    }
}
