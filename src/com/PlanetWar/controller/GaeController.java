package com.PlanetWar.controller;

import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Query.FilterPredicate;

public class GaeController {

	public GaeController() {
		// TODO Auto-generated constructor stub
	}

public void add(String entityName,String[][] properties){

	DatastoreService datastore = DatastoreServiceFactory
			.getDatastoreService();
	Key userKey = KeyFactory.createKey(entityName, properties[0][0]);
	Entity entity = new Entity(userKey);

	for(int i=0;i<properties.length;i++){
		entity.setProperty(properties[i][0], properties[i][1]);
	}

	datastore.put(entity);
}

public String getkeyEntity(String entityName,String property,String value) {
	DatastoreService datastore = DatastoreServiceFactory
			.getDatastoreService();
	Query query = new Query(entityName).setFilter(new FilterPredicate(
			property, Query.FilterOperator.EQUAL, value));
	Entity entity = datastore.prepare(query).asSingleEntity();

	return entity.getKey().toString();
}

public List<Entity> getAllData(String entityName,String property, String value) {
	DatastoreService datastore = DatastoreServiceFactory
			.getDatastoreService();
	Query query = new Query(entityName).setFilter(new FilterPredicate(property,
			Query.FilterOperator.EQUAL, value));
	PreparedQuery p=  datastore.prepare(query);
	return (List<Entity>) p.asIterable();
}


public void delete(Key key){
	DatastoreService datastore = DatastoreServiceFactory
			.getDatastoreService();
	datastore.delete(key);
}

}
