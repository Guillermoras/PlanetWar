package com.PlanetWar.test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.PlanetWar.MedalsController;
import com.PlanetWar.StatusController;
import com.PlanetWar.User;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

public class LocalDatastoreTest {
	private final LocalServiceTestHelper helper = new LocalServiceTestHelper(
			new LocalDatastoreServiceTestConfig());

	public LocalDatastoreTest() {
		// TODO Auto-generated constructor stub
	}

	@Before
	public void setUp() {
		helper.setUp();
	}

	@After
	public void tearDown() {
		helper.tearDown();
	}

	@Test
	public void isRegisterUser() {
		User userController = new User();
		userController.add("gamer2", "gamer2@gamer.com");
		assertEquals("gamer2@gamer.com",
				userController.dataUser("gamer2@gamer.com"));

	}

	@Test
	public void isAddMedalsType() {
		MedalsController medalsType = new MedalsController();
		String[][] medalsTypes = {
				{ "First strike", "Double kill", "Triple kill", "Impressive",
						"Perfection", "Interception", "Eagle eye", "Sniper",
						"Armageddon", "Last strike" },
				{ "Earn the first building kill in the round",
						"successive kills with less than 4 seconds in between",
						"successive kills with less than 4 seconds in between",
						"successive kills with less than 4 seconds in between",
						"Defeat a player without loosing any buildings",
						"Destroy a projectile with another projectile",
						"3 building kill spree without missing",
						"5 building kill spree without missing",
						"10 building kill spree without missing",
						"Earn the last strike in a game" } };
medalsType.addMedalsType(medalsTypes);
		assertTrue(medalsType.getMedalsTypeId("Armageddon")>0);
	}

	@Test
	public void isAddMedalsUser() {
		
		MedalsController medals = new MedalsController();
		User userController = new User();
		userController.add("gamer2", "gamer2@gamer.com");
		long id= userController.getId("gamer2@gamer.com");
		String[][] medalsTypes = {
				{ "First strike", "Double kill", "Triple kill", "Impressive",
						"Perfection", "Interception", "Eagle eye", "Sniper",
						"Armageddon", "Last strike" },
				{ "Earn the first building kill in the round",
						"successive kills with less than 4 seconds in between",
						"successive kills with less than 4 seconds in between",
						"successive kills with less than 4 seconds in between",
						"Defeat a player without loosing any buildings",
						"Destroy a projectile with another projectile",
						"3 building kill spree without missing",
						"5 building kill spree without missing",
						"10 building kill spree without missing",
						"Earn the last strike in a game" } };
medals.addMedalsType(medalsTypes);
		medals.addMedalsUser(id);
		assertTrue(medals.getMedalsUser(id)>0);
		
	}
	
	@Test
	public void isUpdateMedalsUser() {
		
		MedalsController medals = new MedalsController();
		User userController = new User();
		userController.add("gamer3", "gamer3@gamer.com");
		long id= userController.getId("gamer2@gamer.com");
		String[][] medalsTypes = {
				{ "First strike", "Double kill", "Triple kill", "Impressive",
						"Perfection", "Interception", "Eagle eye", "Sniper",
						"Armageddon", "Last strike" },
				{ "Earn the first building kill in the round",
						"successive kills with less than 4 seconds in between",
						"successive kills with less than 4 seconds in between",
						"successive kills with less than 4 seconds in between",
						"Defeat a player without loosing any buildings",
						"Destroy a projectile with another projectile",
						"3 building kill spree without missing",
						"5 building kill spree without missing",
						"10 building kill spree without missing",
						"Earn the last strike in a game" } };
		String[][] medalsWon = {
				{ "First strike", "Double kill", "Triple kill", "Impressive",
						"Perfection", "Interception", "Eagle eye", "Sniper",
						"Armageddon", "Last strike" },
				{ "1","1","1","1","1","1","1","1","1","1" } };
medals.addMedalsType(medalsTypes);
		medals.addMedalsUser(id);
		medals.updateMedalsUser(medalsWon,id);
		assertEquals(10,medals.getTotalMedalsWon(id));
		
	}
@Test
public void isGetMedalsWonUser(){

	MedalsController medals = new MedalsController();
	User userController = new User();
	userController.add("gamer3", "gamer3@gamer.com");
	long id= userController.getId("gamer2@gamer.com");
	String[][] medalsTypes = {
			{ "First strike", "Double kill", "Triple kill", "Impressive",
					"Perfection", "Interception", "Eagle eye", "Sniper",
					"Armageddon", "Last strike" },
			{ "Earn the first building kill in the round",
					"successive kills with less than 4 seconds in between",
					"successive kills with less than 4 seconds in between",
					"successive kills with less than 4 seconds in between",
					"Defeat a player without loosing any buildings",
					"Destroy a projectile with another projectile",
					"3 building kill spree without missing",
					"5 building kill spree without missing",
					"10 building kill spree without missing",
					"Earn the last strike in a game" } };
	String[][] medalsWon = {
			{ "First strike", "Double kill", "Triple kill", "Impressive",
					"Perfection", "Interception", "Eagle eye", "Sniper",
					"Armageddon", "Last strike" },
			{ "1","1","1","1","1","1","1","1","1","1" } };
medals.addMedalsType(medalsTypes);
	medals.addMedalsUser(id);
	medals.updateMedalsUser(medalsWon,id);
	
	assertArrayEquals(medalsWon, medals.getMedalsWon(id));

}

@Test
public void isAddStatus(){
	User userController = new User();
	userController.add("gamer3", "gamer3@gamer.com");
	long id= userController.getId("gamer2@gamer.com");
	int[] status= {1,1,1};
	StatusController statusController= new StatusController();
	statusController.addStatus(status, id);
	assertArrayEquals(status, statusController.getStatus(id));
}

@Test
public void isUpdateStatus(){
	User userController = new User();
	userController.add("gamer3", "gamer3@gamer.com");
	long id= userController.getId("gamer2@gamer.com");
	int[] status= {1,1,1};
	int[] newStatus={1,1,1};
	int[] updateStatus={2,2,2};
	StatusController statusController= new StatusController();
	statusController.addStatus(status, id);
	statusController.updateStatus(newStatus,id);
	assertArrayEquals(updateStatus, statusController.getStatus(id));
}


}
