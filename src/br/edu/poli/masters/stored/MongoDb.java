package br.edu.poli.masters.stored;

import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import br.edu.poli.masters.tags.RootTag;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.Mongo;
import com.mongodb.MongoException;


public class MongoDb implements IStored {

	private Mongo mongo;
	private DB db;
	private DBCollection coll;
	private Gson mGson;
	private static final String DATABASENAME = "aggregatorDomainDb";
	private static final String COLLECTIONNAME = "domain";
	
	
	private static final File file = new File("");
	private static final String EXECUTABLE_DB_PATH = file.getAbsolutePath()+"\\db";
	private static final String MONGODB_EXECUTABLE = "\\mongod.exe";
	private static final String MONGODB_PARAM = "--dbpath "+"\""+EXECUTABLE_DB_PATH+"\"";
	private static Process p;
	
	public MongoDb() throws MongoException, UnknownHostException{
		openConnection();
		
		mongo = new Mongo( "localhost" , 27017 );
		db = mongo.getDB(DATABASENAME);
		coll = db.getCollection(COLLECTIONNAME);
		mGson = new Gson();
	}
	
	@Override
	public void openConnection() {
		try {
			String strConnection = "\""+EXECUTABLE_DB_PATH+MONGODB_EXECUTABLE+"\" "+MONGODB_PARAM;
			System.out.println(strConnection);
			p = Runtime.getRuntime().exec(strConnection);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void closeConnection() {
		p.destroy();
		
	}

	

	@Override
	public void insertNode(RootTag rootTag) {
		
		BasicDBObject basicDBObject = new BasicDBObject();
		basicDBObject.put(DocumentCte.DOMAIN_NAME, rootTag.getDomainNode().getTextNode());
		
		String mindMapGson = mGson.toJson(rootTag);
		System.out.println(mindMapGson);
		basicDBObject.put(DocumentCte.MIND_MAP, mindMapGson);
		
		coll.insert(basicDBObject);

	}
	
	public void updateDomain(RootTag rootTag){
		deleteNodeByDomainName(rootTag.getDomainNode().getTextNode());
		insertNode(rootTag);
	}
	
	@Override
	public void deleteNodeByDomainName(String domainName) {
		BasicDBObject query = new BasicDBObject();
		query.put(DocumentCte.DOMAIN_NAME,domainName);
		coll.remove(query);
	}

	@Override
	public RootTag getNodeByDomain(String domain) {
		String mindMap;
		BasicDBObject Domain;
		BasicDBObject query = new BasicDBObject();
		query.put(DocumentCte.DOMAIN_NAME,domain);
		RootTag auxRoot = null;
		DBCursor cursor = coll.find(query);
		while(cursor.hasNext()) {
		 	Domain = (BasicDBObject) cursor.next();
	        System.out.println( "DomainName: "+Domain.getString("DomainName") );
	        mindMap = Domain.getString("MindMap");
	        System.out.println("MindMap: "+mindMap);
	        auxRoot = mGson.fromJson(mindMap, RootTag.class);
	        System.out.println( "======================================" );
	        
		}
		return auxRoot;
	}

	@Override
	public RootTag getNodeByID(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RootTag> getAllDomain() {
		String mindMap;
		BasicDBObject Domain;
		List<RootTag> listRootTags = new ArrayList<RootTag>();
		RootTag auxRoot;
 		DBCursor cursor = coll.find();
			while(cursor.hasNext()) {
				 	Domain = (BasicDBObject) cursor.next();
			        System.out.println( "DomainName: "+Domain.getString("DomainName") );
			        mindMap = Domain.getString("MindMap");
			        System.out.println("MindMap: "+mindMap);
			        auxRoot = mGson.fromJson(mindMap, RootTag.class);
			        listRootTags.add(auxRoot);
			        System.out.println( "======================================" );
			        
			}
		return listRootTags;
	}
	
	public List<String> getAllDomainName(){
		BasicDBObject Domain;
		List<String> listRootTags = new ArrayList<String>();
 		DBCursor cursor = coll.find();
			while(cursor.hasNext()) {
				 	Domain = (BasicDBObject) cursor.next();
			        listRootTags.add(Domain.getString("DomainName"));
			        
			}
		return listRootTags;
	}
	


}
