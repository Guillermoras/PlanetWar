package com.PlanetWar;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

import com.google.appengine.api.datastore.Query.FilterOperator;
import com.google.appengine.api.datastore.Query.FilterPredicate;

public class MedalsController {

	public MedalsController() {
		// TODO Auto-generated constructor stub
	}

	public void addMedalsType(String[][] medalsType) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		long id = 1;
		for (int i = 0; i < medalsType[0].length; i++) {
			Query q = new Query("MedalTyp");
			PreparedQuery pq = datastore.prepare(q);
			id=pq.asList(FetchOptions.Builder.withDefaults()).size();
			id+=1;
			Key userKey = KeyFactory.createKey("MedalTyp", id);
			Entity user = new Entity(userKey);
			user.setProperty("id", userKey.getId());
			user.setProperty("name", medalsType[0][i]);
			user.setProperty("desciption", medalsType[1][i]);
			datastore.put(user);

		}
	}

	public long getMedalsTypeId(String nameMedal) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query q = new Query("MedalTyp").setFilter(new FilterPredicate("name",
				FilterOperator.EQUAL, nameMedal));

		Entity e = datastore.prepare(q).asSingleEntity();

		return (long) e.getProperty("id");
	}

	public PreparedQuery getMedalsType() {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query q = new Query("MedalTyp");

		return datastore.prepare(q);

	}

	public void addMedalsUser(long idUser) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		long id = 1;
		PreparedQuery pq = getMedalsType();

		for (Entity e : pq.asIterable()) {
			Query q = new Query("Medal");
			PreparedQuery pq1 = datastore.prepare(q);
			id=pq1.asList(FetchOptions.Builder.withDefaults()).size();
			id+=1;
			Key userKey = KeyFactory.createKey("Medal", id);
			Entity user = new Entity(userKey);
			user.setProperty("id", userKey.getId());
			user.setProperty("idUser", idUser);
			user.setProperty("idMedalType", (long) e.getProperty("id"));
			user.setProperty("total", 0);
			datastore.put(user);

		}

	}

	public long getMedalsUser(long idUser) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query q = new Query("Medal").setFilter(new FilterPredicate("idUser",
				FilterOperator.EQUAL, idUser));
		PreparedQuery pq = datastore.prepare(q);
		long id = 0;
		id=pq.asList(FetchOptions.Builder.withDefaults()).size();
		id+=1;
		return id;
	}

	public void updateMedalsUser(String[][] medalsWon, long idUser) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		long id = 0;

		for (int i = 0; i < medalsWon[0].length; i++) {
			id = getMedalsTypeId(medalsWon[0][i]);
			if (id > 0) {

				Query q = new Query("Medal").setFilter(
						new FilterPredicate("idUser", FilterOperator.EQUAL,
								idUser)).setFilter(
						new FilterPredicate("idMedalType",
								FilterOperator.EQUAL, id));
				PreparedQuery pq = datastore.prepare(q);
				for (Entity e : pq.asIterable()) {
					e.setUnindexedProperty(
							"total",
							(long) e.getProperty("total")
									+ Long.parseLong(medalsWon[1][i]));
					datastore.put(e);
				}

			} else {

			}
		}

	}

	public long getTotalMedalsWon(long idUser) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		long total = 0;
		Query q = new Query("Medal").setFilter(new FilterPredicate("idUser",
				FilterOperator.EQUAL, idUser));
		PreparedQuery pq = datastore.prepare(q);
		for (Entity e : pq.asIterable()) {
			total += (long) e.getProperty("total");

		}
		return total;
	}

	public String[][] getMedalsWon(long idUser) {
		// TODO Auto-generated method stub
		//HashMap<String,Long> medals= new HashMap<String,Long>();
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query qm = new Query("Medal").setFilter(new FilterPredicate("idUser",
				FilterOperator.EQUAL, idUser));
		Query qMedalsType = new Query("MedalTyp");
		PreparedQuery pq = datastore.prepare(qm);
		PreparedQuery pqMedalsType = datastore.prepare(qMedalsType);
		int i=0;
		String[][] medals= new String[2][pq.asList(FetchOptions.Builder.withDefaults()).size()];
		for (Entity e : pq.asIterable()) {
			for (Entity eMedalsType: pqMedalsType.asIterable()){
				if((long)e.getProperty("idMedalType")==(long) eMedalsType.getProperty("id")){
					medals[0][i]=eMedalsType.getProperty("name").toString();
					medals[1][i]=e.getProperty("total").toString();
					break;
				}
			}
			i++;
		}

		return medals;
	}

}
