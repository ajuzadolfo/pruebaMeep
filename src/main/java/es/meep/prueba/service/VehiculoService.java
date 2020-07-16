/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.meep.prueba.service;

import es.meep.prueba.model.Cambio;
import es.meep.prueba.model.Vehiculo;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

/**
 *
 * @author Adolfo Urrutia
 * 14-jul-2020 14:39:17
 */
@Service
public class VehiculoService {
    
    /**
     * *
     * Detecta las entidades diferentes entre las listas pasadas como parámetros
     *
     * @param preLista
     * @param lista
     * @return
     */
    public Cambio detectarCambios(List<Vehiculo> preLista, List<Vehiculo> lista) {
        Cambio resultado;
        List<Vehiculo> ingresos, egresos;
        boolean hayCambio = false;
        int preCantidad = preLista.size();
        int cantidad = lista.size();

        //Mostrar elementos que solo estén en la lista 1 y no en la 2 +++++++++++++++++++++++++++
        ingresos = lista.stream().filter(vehiculo -> !preLista.contains(vehiculo)).collect(Collectors.toList());
        
        if(ingresos.size() + preCantidad != cantidad){
            // Ha habido al menos 1 egreso, porque de los contrario deberían de cuadrar.
             egresos = preLista.stream().filter(vehiculo -> !lista.contains(vehiculo)).collect(Collectors.toList());
        } else{
            //No ha habido egresos
            egresos = new ArrayList<>();
        }

        if(ingresos.size() > 0 || egresos.size() > 0){
            hayCambio = true;
        }
        
        resultado = new Cambio(hayCambio,ingresos,egresos);

        return resultado;
    }
    
}
