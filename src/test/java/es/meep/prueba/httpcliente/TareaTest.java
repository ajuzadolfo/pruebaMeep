/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.meep.prueba.httpcliente;

import es.meep.prueba.model.Vehiculo;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 * @author Adolfo Urrutia 13-jul-2020 19:44:24
 */
@SpringBootTest
public class TareaTest {

    @Autowired
    private RestTemplate restTemplate;

    private final String rutaEndPoint = "https://apidev.meep.me/tripplan/api/v1/routers/lisboa/resources?lowerLeftLatLon=38.711046,-9.160096&upperRightLatLon=38.739429,-9.137115&companyZoneIds=545";

    @Test
    public void probarTarea() throws Exception {

        ResponseEntity<List<Vehiculo>> rateResponse
                = restTemplate.exchange(rutaEndPoint, HttpMethod.GET, null, new ParameterizedTypeReference<List<Vehiculo>>() {
                });

        assertEquals(200, 200);

    }

}
