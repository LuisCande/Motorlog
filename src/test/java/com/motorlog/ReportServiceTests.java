package com.motorlog;


import com.motorlog.entity.Report;
import com.motorlog.service.ReportService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReportServiceTests {

    @TestConfiguration
    static class ReportServiceTestsConfiguration {

        @Bean
        public ReportService reportService() {
            return new ReportService();
        }
    }

    @Autowired
    private ReportService reportService;

    @Test
    public void contextLoads() throws Exception {
        assertThat(reportService).isNotNull();
    }

    @Test
    public void findOne() {
        Report report = this.reportService.findOne(500);
        Assert.notNull(report, "Report is null");
    }

    @Test
    public void saveAux() {
        Report report = this.reportService.findOne(500);
        this.reportService.saveAux(report);


    }

}
