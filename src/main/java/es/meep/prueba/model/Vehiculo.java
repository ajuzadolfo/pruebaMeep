/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.meep.prueba.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Objects;

/**
 * Representa la entidad vehículo que viene serializada en el Json devuelto por el EndPoint
 * @author Adolfo Urrutia 11-jul-2020 22:28:39
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Vehiculo implements Comparable<Vehiculo>{

    private String id;
    private String name;
    private double x;
    private double y;
    private String licencePlate;
    private int range;
    private int batteryLevel;
    private int helmets;
    private String model;
    private String resourceImageId;
    private boolean realTimeData;
    private String resourceType;
    private int companyZoneId;
    
    public Vehiculo(){}

    public Vehiculo(String id, String name, double x, double y, String licencePlate, int range, int batteryLevel, int helmets, String model, String resourceImageId, boolean realTimeData, String resourceType, int companyZoneId) {
        this.id = id;
        this.name = name;
        this.x = x;
        this.y = y;
        this.licencePlate = licencePlate;
        this.range = range;
        this.batteryLevel = batteryLevel;
        this.helmets = helmets;
        this.model = model;
        this.resourceImageId = resourceImageId;
        this.realTimeData = realTimeData;
        this.resourceType = resourceType;
        this.companyZoneId = companyZoneId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public String getLicencePlate() {
        return licencePlate;
    }

    public void setLicencePlate(String licencePlate) {
        this.licencePlate = licencePlate;
    }

    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }

    public int getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(int batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    public int getHelmets() {
        return helmets;
    }

    public void setHelmets(int helmets) {
        this.helmets = helmets;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getResourceImageId() {
        return resourceImageId;
    }

    public void setResourceImageId(String resourceImageId) {
        this.resourceImageId = resourceImageId;
    }

    public boolean isRealTimeData() {
        return realTimeData;
    }

    public void setRealTimeData(boolean realTimeData) {
        this.realTimeData = realTimeData;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public int getCompanyZoneId() {
        return companyZoneId;
    }

    public void setCompanyZoneId(int companyZoneId) {
        this.companyZoneId = companyZoneId;
    }

    @Override
    public String toString() {
        return "Vehiculo{" + "id=" + id + ", name=" + name + ", range=" + range + ", batteryLevel=" + batteryLevel + ", helmets=" + helmets + ", model=" + model + ", resourceType=" + resourceType + ", companyZoneId=" + companyZoneId + '}';
    }

    @Override
    public int compareTo(Vehiculo v) {
        int resultado;
        resultado = id.compareTo(v.id);

        return resultado;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 61 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Vehiculo other = (Vehiculo) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
}
