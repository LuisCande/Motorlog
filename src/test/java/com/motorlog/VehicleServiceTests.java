package com.motorlog;


import com.motorlog.entity.Vehicle;
import com.motorlog.entity.Revision;
import com.motorlog.entity.Repair;
import java.util.Collection;

import com.motorlog.service.VehicleService;
import com.motorlog.service.RevisionService;
import com.motorlog.service.RepairService;
import com.motorlog.security.WithMockUserAccount;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import com.motorlog.entity.Revision;



import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VehicleServiceTests {

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
	private RevisionService revisionService;
	@Autowired
	private RepairService repairService;

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
	@Test
	@WithMockUserAccount(username = "cmmotorlog1")
	public void delete(){
		Vehicle vehicle = vehicleService.findOne(101);
		Assert.notNull(vehicle, "Item is null.");

		Collection<Revision> revisions = this.revisionService.getRevisionsByVehicle(vehicle.getId());
		Collection<Repair> repairs = this.repairService.getRepairsByVehicle(vehicle.getId());

		vehicleService.delete(vehicle);

	}

	@Test
	public void showByLicense(){
		String s = "1859CCR";
		this.vehicleService.vehicleByLicensePlate(s);

	}

	@Test
	public void showBySearch(){
		String s = "search";
		this.vehicleService.getFilteredVehicles(s);

	}
}
