package com.motorlog;

import com.motorlog.entity.Garage;
import com.motorlog.security.WithMockUserAccount;
import com.motorlog.service.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ExportServiceTests {

    @TestConfiguration
    static class RecoveryServiceTestsConfiguration {

        @Bean
        public ExportService recoveryService() {
            return new ExportService();
        }
    }

    @Autowired
    private ExportService exportService;

    @Autowired
    private GarageService garageService;

    @Test
    @WithMockUserAccount(username = "sevillamotor")
    public void getJson() {
        Garage garage = this.garageService.findOne(52);
        String json = exportService.getJson(garage.getId(), true);
        System.out.println(json);
    }
}
