package com.PlanetWar;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;
 
public class User {

	public User() {
		// TODO Auto-generated constructor stub
	}

	public void add(String gamerUser, String email) {

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Key userKey = KeyFactory.createKey("User", email);
		Entity user = new Entity(userKey);

		user.setProperty("gamerUser", gamerUser);
		user.setProperty("id", userKey.getId());
		user.setProperty("email", email);
		user.setProperty("totalPoints", 50);
		datastore.put(user);
		
	}
	
	public long getId(String email){
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query query= new Query("User").setFilter(new FilterPredicate("email", FilterOperator.EQUAL, email));
		Entity entity= datastore.prepare(query).asSingleEntity();
		return Long.parseLong(entity.getProperty("id").toString());
		
	}
	
	

	public String dataUser(String email) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query query = new Query("User").setFilter(new FilterPredicate("email",Query.FilterOperator.EQUAL, email));
		Entity user = datastore.prepare(query).asSingleEntity();
		
		return user.getProperty("email").toString();
	
	}

	
	

}
