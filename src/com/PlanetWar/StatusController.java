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

public class StatusController {

	public StatusController() {
		// TODO Auto-generated constructor stub
	}

	public void addStatus(int[] status, long idUser) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		long id = 1;

		Query q = new Query("Status");
		PreparedQuery pq = datastore.prepare(q);
		id = pq.asList(FetchOptions.Builder.withDefaults()).size();
		id += 1;
		Key key = KeyFactory.createKey("Status", id);
		Entity entStatus = new Entity(key);
int win= status[0];
int lost=status[1];
int draw=status[2];

 int total=win+lost+draw;
		entStatus.setProperty("id", id);
		entStatus.setProperty("userId", idUser);
		entStatus.setProperty("gameWin", win);
		entStatus.setProperty("gameLost",lost);
		entStatus.setProperty("gameDraw",draw);
		entStatus.setProperty("statusWin", ((double)(win*100)/total)+"%");
		entStatus.setProperty("statusLost", ((double)(lost*100)/total)+"%");
		entStatus.setProperty("statusDraw", ((double)(draw*100)/total)+"%");
		entStatus.setProperty("total", total);
		datastore.put(entStatus);

	}

	public int[] getStatus(long idUser) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query q = new Query("Status").setFilter(new FilterPredicate("userId", FilterOperator.EQUAL, idUser));
		Entity e = datastore.prepare(q).asSingleEntity();
		int[] status= new int[3];
		status[0]=Integer.parseInt(e.getProperty("gameWin").toString());
		status[1]=Integer.parseInt(e.getProperty("gameLost").toString());
		status[2]=Integer.parseInt(e.getProperty("gameDraw").toString());
		return status;
	}

	public void updateStatus(int[] status, long idUser) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		

		Query q = new Query("Status").setFilter(new FilterPredicate("userId", FilterOperator.EQUAL, idUser));
		Entity entStatus = datastore.prepare(q).asSingleEntity();
		
int win= status[0]+Integer.parseInt(entStatus.getProperty("gameWin").toString());
int lost=status[1]+Integer.parseInt(entStatus.getProperty("gameLost").toString());
int draw=status[2]+Integer.parseInt(entStatus.getProperty("gameDraw").toString());

 int total=win+lost+draw;
		
		entStatus.setProperty("gameWin", win);
		entStatus.setProperty("gameLost",lost);
		entStatus.setProperty("gameDraw",draw);
		entStatus.setProperty("statusWin", ((double)(win*100)/total)+"%");
		entStatus.setProperty("statusLost", ((double)(lost*100)/total)+"%");
		entStatus.setProperty("statusDraw", ((double)(draw*100)/total)+"%");
		entStatus.setProperty("total", total);
		datastore.put(entStatus);
		
	}

}
