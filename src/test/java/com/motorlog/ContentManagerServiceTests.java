package com.motorlog;


import com.motorlog.entity.Vehicle;
import com.motorlog.security.WithMockUserAccount;
import com.motorlog.service.VehicleService;
import com.motorlog.entity.ContentManager;
import com.motorlog.service.ContentManagerService;


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
public class ContentManagerServiceTests {

	@TestConfiguration
	static class VehicleServiceTestsConfiguration {

		@Bean
		public VehicleService vehicleService() {
			return new VehicleService();
		}
	}

	@Autowired
	private VehicleService vehicleService;
	@Autowired
	private ContentManagerService contentManagerService;

	@Test
	public void contextLoads() throws Exception {
		assertThat(vehicleService).isNotNull();
	}

	@Test
	public void findOne() {
		Vehicle vehicle = this.vehicleService.findOne(100);
		Assert.notNull(vehicle, "Vehicle is null");
	}

	@Test
	public void create() {
		Vehicle vehicle = vehicleService.create();

	}

}
