/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.pg.eti.aui.aui;

import java.util.List;
import java.util.Optional;


/**
 *
 * @author harmi
 */
public interface Repository<E,K> {
    
    Optional<E> find(K id);

    List<E> findAll();
    
    void create(E entity);

   
    void delete(E entity);

}
