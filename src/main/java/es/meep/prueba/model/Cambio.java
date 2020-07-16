/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package es.meep.prueba.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Entidad que informa si ha habido un cambio, y en caso que lo haya, devolver las listas de Ingresos y Egresos.
 * @author Adolfo Urrutia
 * 14-jul-2020 14:43:11
 */
public class Cambio {
    private boolean modifado;
    private List<Vehiculo> ingresos;
    private List<Vehiculo> egresos;

    public Cambio(boolean modifado, List<Vehiculo> ingresos, List<Vehiculo> egresos) {
        this.modifado = modifado;
        this.ingresos = new ArrayList<>(ingresos);
        this.egresos = new ArrayList<>(egresos);
    }

    public boolean isModifado() {
        return modifado;
    }

    public void setModifado(boolean modifado) {
        this.modifado = modifado;
    }

    public List<Vehiculo> getIngresos() {
        return ingresos;
    }

    public void setIngresos(List<Vehiculo> ingresos) {
        this.ingresos = new ArrayList<>(ingresos);
    }

    public List<Vehiculo> getEgresos() {
        return egresos;
    }

    public void setEgresos(List<Vehiculo> egresos) {
        this.egresos = new ArrayList<>(egresos);
    }
    
}
