/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.meep.prueba.httpcliente;

import es.meep.prueba.model.Cambio;
import es.meep.prueba.model.Vehiculo;
import es.meep.prueba.service.VehiculoService;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author Adolfo Urrutia 11-jul-2020 20:35:14
 */
@Component
public class Tarea {

    private List<Vehiculo> preVehiculos = null;

    @Autowired
    private RestTemplate restTemplateConGestorDeErrores;

    @Autowired
    VehiculoService vehServicio;

    @Value("${endpoint.uri}")
    private String rutaEndPoint;

    @Value("${lower.left}")
    private String lowerLeft;

    @Value("${upper.right}")
    private String upperRight;

    @Value("${company.zoneids}")
    private String zoneIds;

    //Para mostrar colores al momento de imprimir en consola los datos
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";


    private final Logger logger = LogManager.getLogger(Tarea.class);

    public Tarea() {}

    /***
     * Función principal que hace la llamada al EndPoint de Meep y si todo va bien obtiene un Json con la información.
     * La que luego es procesada y presentada por consola y fichero de log.
     */
    @Scheduled(fixedRateString = "${tarea.frecuencia}")
    public void llamarEndPoint() {

        Cambio cambios;

        ResponseEntity<List<Vehiculo>> rateResponse
                = restTemplateConGestorDeErrores.exchange(rutaEndPoint + lowerLeft + upperRight + zoneIds, HttpMethod.GET, null, new ParameterizedTypeReference<List<Vehiculo>>() {
                });

        List<Vehiculo> vehiculos = new ArrayList<>(rateResponse.getBody());
        if (vehiculos != null) {
            logger.info("----------------------------------------------------------------------------------------------------------");
            logger.info("cantidad de vehículos: " + vehiculos.size());
            logger.info("----------------------------------------------------------------------------------------------------------");

            if (preVehiculos != null) {

                //Sacar las diferencias
                cambios = vehServicio.detectarCambios(preVehiculos, vehiculos);

                if (cambios.isModifado()) {
                    //Mostrar datos estadísticos
                     mostrarDatosEstadisticos(vehiculos);
                   
                    //Ingresos
                    mostrarListaCambios("Ingresos", cambios.getIngresos(), ANSI_GREEN, ANSI_RESET);

                    //Egresos
                    mostrarListaCambios("Egresos", cambios.getEgresos(), ANSI_RED, ANSI_RESET);

                    // Mostrar nuevamente la lista de los vehículos
                    logger.info(" ------------------------- ");
                    vehiculos.forEach(v -> {
                        logger.info(v.toString());

                    });

                    //Actualizado la lista de los vehículos
                    preVehiculos = new ArrayList<>(vehiculos);
                } else {
                    logger.info("SIN CAMBIOS");
                }

            } else {
                
                //Mostrar todos los vehiculos por primera vez
                 mostrarDatosEstadisticos(vehiculos);
                
                vehiculos.forEach(v -> {
                    logger.info(v.toString());
                });

                preVehiculos = new ArrayList<>(vehiculos);
            }

        }

    }

    /**
     * *
     * Muestra una lista de vehículos en la consola y archivo de logs,
     * utilizando los colores que se le pasan como parámetros. Se utilza sobre
     * todo para destacar los vehículos ingresados y los que egresan.
     *
     * @param encabezado
     * @param vehiculos
     * @param color
     * @param colorFinal
     */
    private void mostrarListaCambios(String encabezado, List<Vehiculo> vehiculos, String color, String colorFinal) {
        logger.info(encabezado + ": " + vehiculos.size() + " ------------- ");

        vehiculos.forEach(v -> {
            System.out.print(color);
            logger.info(v);
            System.out.print(colorFinal);
        });
    }

    /***
     * Muestra en consola los datos estadísticos sobre el nivel bajo, o alto, de batería.
     * @param vehiculos 
     */
    private void mostrarDatosEstadisticos(List<Vehiculo> vehiculos) {
        Predicate<Vehiculo> pBateriaBaja = v -> v.getBatteryLevel() <= 25;
        Predicate<Vehiculo> pBateriaAlta = v -> v.getBatteryLevel() >= 75;
        
        long cantBateriaBaja = vehiculos.stream().filter(pBateriaBaja).count();
        long cantBateriaAlta = vehiculos.stream().filter(pBateriaAlta).count();
        
        logger.info("Vehículos con bateria al 25% o menos: " + cantBateriaBaja);
        logger.info("Vehículos con bateria al 75% o mas: " + cantBateriaAlta);
        
    }

}
