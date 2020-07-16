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
import org.junit.Before;
import org.junit.BeforeClass;
import static org.junit.Assert.*;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;

/**
 *
 * @author Adolfo Urrutia 15-jul-2020 12:51:42
 */
@SpringBootTest
public class VehiculoServiceTest {

    @Autowired
    VehiculoService vehServicio;

    public VehiculoServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @Before
    public void setUp() {

    }

    @Test
    public void testDetectarCambios() {
        Cambio cambio;
        List<Vehiculo> listaPre, listaActual;

        Vehiculo veh1 = Mockito.mock(Vehiculo.class);
        Vehiculo veh2 = Mockito.mock(Vehiculo.class);
        Vehiculo veh3 = Mockito.mock(Vehiculo.class);

        listaPre = new ArrayList<>();
        listaPre.add(veh1);
        listaPre.add(veh2);

        listaActual = new ArrayList<>();
        listaActual.add(veh2);
        listaActual.add(veh3);

        cambio = vehServicio.detectarCambios(listaPre, listaActual);

        assertEquals(true, cambio.isModifado());
        assertEquals(1, cambio.getIngresos().size());
        assertEquals(1, cambio.getEgresos().size());

    }

}
