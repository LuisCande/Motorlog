package com.motorlog;


import com.motorlog.entity.Garage;
import com.motorlog.entity.Repair;
import com.motorlog.entity.Revision;
import com.motorlog.entity.Report;

import com.motorlog.entity.Vehicle;
import com.motorlog.security.WithMockUserAccount;
import com.motorlog.service.GarageService;
import com.motorlog.service.RevisionService;
import com.motorlog.service.RepairService;
import com.motorlog.service.ReportService;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.util.Collection;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GarageServiceTests {

    @TestConfiguration
    static class GarageServiceTestsConfiguration {

        @Bean
        public GarageService garageService() {
            return new GarageService();
        }
    }

    @Autowired
    private GarageService garageService;
    @Autowired
    private RevisionService revisionService;
    @Autowired
    private RepairService repairService;
    @Autowired
    private ReportService reportService;
    @Test
    public void contextLoads() throws Exception {
        assertThat(garageService).isNotNull();
    }

    @Test
    public void findOne() {
        Garage garage = this.garageService.findOne(52);
        Assert.notNull(garage, "Garage is null");
    }

    @Test
    public void create() {
        Garage garage = garageService.create();

    }

    @Test
    public void top5GaragesInTermsOfRevisions(){
        this.garageService.top5GaragesInTermsOfRevisions();
    }
    @Test
    public void top5GaragesInTermsOfRepairs(){
        this.garageService.top5GaragesInTermsOfRepairs();
    }



}
