package com.motorlog;

import com.motorlog.entity.Garage;
import com.motorlog.entity.Revision;
import com.motorlog.security.WithMockUserAccount;
import com.motorlog.service.ActorService;
import com.motorlog.service.GarageService;
import com.motorlog.service.RevisionService;
import com.motorlog.service.VehicleService;
import org.springframework.util.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;


import java.util.Date;
import java.util.List;

import java.util.GregorianCalendar;


import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RevisionServiceTests {

	@TestConfiguration
	static class RevisionServiceTestsConfiguration {

		@Bean
		public RevisionService revisionService() {
			return new RevisionService();
		}
	}

	@Autowired
	private RevisionService revisionService;

	@Autowired
	private VehicleService vehicleService;

	@Autowired
	private GarageService garageService;

	@Autowired
	private ActorService actorService;

	@Test
	public void contextLoads() throws Exception {
		assertThat(revisionService).isNotNull();
	}

	/*@Test
	public void create() {
		Revision revision = this.revisionService.create(100);
		System.out.println("revision: "+revision);
		Assert.notNull(revision, "The revision is null.");
	}*/

	@Test
	public void findOne() {
		Revision revision = this.revisionService.findOne(251);
		System.out.println("revision: "+revision);
		Assert.notNull(revision, "The revision is null.");
	}

	@Test
	public void findAll() {
		List<Revision> revisions = this.revisionService.findAll();
		System.out.println("revisions: "+ revisions.toString());
		Assert.isTrue(!revisions.isEmpty(), "The revisions were not retrieved correctly.");
	}

	@Test
	@WithMockUserAccount(username = "sevillamotor")
	public void save() {
		Garage garage = this.garageService.findOne(52);
		Revision revision = this.revisionService.create(100);
		revision.setGarage(garage);
		revision.setComments("comments");
		revision.setPersonalInfo("personal info");
		revision = this.revisionService.save(revision);
		System.out.println("revision: "+revision);
		revision = this.revisionService.findOne(revision.getId());
		Assert.notNull(revision, "The revision was not correctly saved.");
	}
	@Test
	public void revisionsByVehicle(){
		int id = 100;
		this.revisionService.getRevisionsByVehicle(id);
	}

	@Test
	public void pendingRevisionsByVehicle() {
		int id = 101;
		this.revisionService.getPendingRevisionsByVehicle(id);
	}
	@Test
	public void closedRevisionsByVehicle() {
		int id = 101;
		this.revisionService.getClosedRevisionsByVehicle(id);
	}
	@Test
	public void revisionsPendingByGarageAndVehicle(){
		int garageId = 52;
		int vehicleId = 101;
		this.revisionService.revisionsPendingByGarageAndVehicle(garageId,vehicleId);
	}
	@Test
	public void filteredRevisionsByeVehicle(){
		int varId = 2;
		String s = "search";

		Date lowerEntryDate = new GregorianCalendar(2019, 05, 02).getTime();
		Date upperEntryDate = new GregorianCalendar(2019, 05, 07).getTime();
		Date lowerDepartureDate = new GregorianCalendar(2019, 06, 5).getTime();
		Date upperDepartureDate = new GregorianCalendar(2019, 06, 9).getTime();


		this.revisionService.getFilteredRevisionsByVehicle(varId,s,lowerEntryDate,upperEntryDate,lowerDepartureDate,upperDepartureDate);



	}


	//@Test
	//@WithMockUserAccount(username = "garage1")
	//public void getFilteredRevisionsByGarage() throws ParseException {
	//	int garageId = actorService.findByPrincipal().getId();
	//	Collection<Revision> container = this.revisionService.getRevisionsByGarage(garageId);
	//
	//	// No parameters provided
	//	Collection<Revision> revisions = this.revisionService.getFilteredRevisionsByGarage(
	//			garageId, null, null, null, null, null);
	//	Assert.isTrue(revisions.size() == container.size());
	//
	//	// Filter by comments
	//	revisions = this.revisionService.getFilteredRevisionsByGarage(
	//			garageId, "comments1", null, null, null, null);
	//	long count = container.stream().filter(r -> r.getComments().equals("comments1")).count();
	//	Assert.isTrue(count == revisions.size());
	//
	//	// Filter by items
	//	revisions = this.revisionService.getFilteredRevisionsByGarage(
	//			garageId, "item", null, null, null, null);
	//	count = container.stream().filter(r -> r.getItems().keySet().contains("item")).count();
	//	Assert.isTrue(count == revisions.size());
	//
	//	// Filter by entry date
	//	Date lowerEntryDate = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("01/01/2018 16:11");
	//	Date upperEntryDate = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("02/01/2018 16:13");
	//	revisions = this.revisionService.getFilteredRevisionsByGarage(
	//			garageId, null, lowerEntryDate, upperEntryDate, null, null);
	//	count = container.stream().filter(r -> r.getEntryDate().after(lowerEntryDate)
	//			&& r.getEntryDate().before(upperEntryDate)).count();
	//	Assert.isTrue(count == revisions.size());
	//
	//	// Filter by departure date
	//	Date lowerDepartureDate = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("01/01/2019 16:11");
	//	Date upperDepartureDate = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("03/01/2019 16:13");
	//	revisions = this.revisionService.getFilteredRevisionsByGarage(
	//			garageId, null, null, null, lowerDepartureDate, upperDepartureDate);
	//	count = container.stream().filter(r -> r.getDepartureDate() == null
	//			|| (r.getDepartureDate().after(lowerDepartureDate)
	//			&& r.getDepartureDate().before(upperDepartureDate))).count();
	//	Assert.isTrue(count == revisions.size());
	//
	//	// Complete filter
	//	revisions = this.revisionService.getFilteredRevisionsByGarage(
	//			garageId, "comments1", lowerEntryDate, upperEntryDate, lowerDepartureDate, upperDepartureDate);
	//	count = container.stream()
	//			.filter(r -> r.getComments().equals("comments1"))
	//			.filter(r -> r.getEntryDate().after(lowerEntryDate)
	//					&& r.getEntryDate().before(upperEntryDate))
	//			.filter(r -> r.getDepartureDate() == null
	//					|| (r.getDepartureDate().after(lowerDepartureDate)
	//					&& r.getDepartureDate().before(upperDepartureDate)))
	//			.count();
	//	Assert.isTrue(count == revisions.size());
	//}

	//@Test
	//@WithMockUserAccount(username = "garage1")
	//public void getFilteredRevisionsByVehicle() throws ParseException {
	//	int vehicleId = vehicleService.vehicleByLicensePlate("2598JTL").getId();
	//	Collection<Revision> container = this.revisionService.getRevisionsByVehicle(vehicleId);
	//
	//	// No parameters provided
	//	Collection<Revision> revisions = this.revisionService.getFilteredRevisionsByVehicle(
	//			vehicleId, null, null, null, null, null);
	//	Assert.isTrue(revisions.size() == container.size());
	//
	//	// Filter by comments
	//	revisions = this.revisionService.getFilteredRevisionsByVehicle(
	//			vehicleId, "comments2", null, null, null, null);
	//	long count = container.stream().filter(r -> r.getComments().equals("comments2")).count();
	//	Assert.isTrue(count == revisions.size());
	//
	//	// Filter by items
	//	revisions = this.revisionService.getFilteredRevisionsByVehicle(
	//			vehicleId, "item", null, null, null, null);
	//	count = container.stream().filter(r -> r.getItems().keySet().contains("item")).count();
	//	Assert.isTrue(count == revisions.size());
	//
	//	// Filter by entry date
	//	Date lowerEntryDate = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("01/01/2018 16:11");
	//	Date upperEntryDate = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("02/01/2018 16:13");
	//	revisions = this.revisionService.getFilteredRevisionsByVehicle(
	//			vehicleId, null, lowerEntryDate, upperEntryDate, null, null);
	//	count = container.stream().filter(r -> r.getEntryDate().after(lowerEntryDate)
	//			&& r.getEntryDate().before(upperEntryDate)).count();
	//	Assert.isTrue(count == revisions.size());
	//
	//	// Filter by departure date
	//	Date lowerDepartureDate = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("01/01/2019 16:11");
	//	Date upperDepartureDate = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse("03/01/2019 16:13");
	//	revisions = this.revisionService.getFilteredRevisionsByVehicle(
	//			vehicleId, null, null, null, lowerDepartureDate, upperDepartureDate);
	//	count = container.stream().filter(r -> r.getDepartureDate() == null
	//			|| (r.getDepartureDate().after(lowerDepartureDate)
	//			&& r.getDepartureDate().before(upperDepartureDate))).count();
	//	Assert.isTrue(count == revisions.size());
	//
	//	// Complete filter
	//	revisions = this.revisionService.getFilteredRevisionsByVehicle(
	//			vehicleId, "comments2", lowerEntryDate, upperEntryDate, lowerDepartureDate, upperDepartureDate);
	//	count = container.stream()
	//			.filter(r -> r.getComments().equals("comments2"))
	//			.filter(r -> r.getEntryDate().after(lowerEntryDate)
	//					&& r.getEntryDate().before(upperEntryDate))
	//			.filter(r -> r.getDepartureDate() == null
	//					|| (r.getDepartureDate().after(lowerDepartureDate)
	//					&& r.getDepartureDate().before(upperDepartureDate)))
	//			.count();
	//	Assert.isTrue(count == revisions.size());
	//}
}
