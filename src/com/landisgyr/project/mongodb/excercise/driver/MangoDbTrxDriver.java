package com.landisgyr.project.mongodb.excercise.driver;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.landisgyr.project.domain.Address;
import com.landisgyr.project.domain.Meter;
import com.landisgyr.project.domain.MeterReading;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
public class MangoDbTrxDriver {
	public static void main(String[] args) {
	      try{   
	    	 MangoDbTrxDriver driver = new MangoDbTrxDriver();
	    	 /** 1.Insert 10 Meter objects into the meters collection in Mongo.**/
	    	 driver.addMeters();
	    	 
	    	 /** 1.For each meter insert 24 Meter Reading objects into the meter reading collection in Mongo..**/
	    	 driver.addMeterReadings();
			 
			 /**2. calculate and print out the average kwh for one specific meter.**/
			 Meter meter = null;
			 driver.caluculateAvgKwhOfMeter(meter);
			 
			 /**3. Find and print out the address of the meter which has a meter reading 
			  * with the highest kwh among all meter readings.
			  */
			 driver.addressOfMeterHigestReading();
			 
			 /**
			  * 4.	Modify the kwh of a reading to a different value.
			  */
			 driver.modifyTheMeterreading(meter);
	      
	      }catch(Exception e){
		     System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		  }
	   }
	
	private void caluculateAvgKwhOfMeter(Meter meter) {
		DB db = getDB();
		db.mycol.find({"by":"meter"}).pretty()
		
		db.mycol.aggregate([{$group : {_id : "$by_user", num_tutorial : {$avg : "$likes"}}}])
	}

	private void addressOfMeterHigestReading() {
		DB db = getDB();
		// TODO Auto-generated method stub
		db.mycol.aggregate([{$group : {_id : "$by_user", num_tutorial : {$max : "$likes"}}}])
	}

	private void modifyTheMeterreading(Meter meter) {
		DB db = getDB();
		db.mycol.find({"by":"meter"}).pretty()
		>db.COLLECTION_NAME.update(SELECTIOIN_CRITERIA, UPDATED_DATA);
	}

	private void addMeters(){
	
		 DB db = getDB();
		 
		 DBCollection dbCollection = db.getCollection("Meter");

		 List<DBObject> meters = getMeterData(10);
		 dbCollection.insert(meters);

	} 
	private DB getDB() {
		 // To connect to mongodb server
        MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
        // Now connect to your databases
        DB db = mongoClient.getDB("test");
		 System.out.println("Connect to database successfully");
        //boolean auth = db.authenticate(myUserName, myPassword);
		 //System.out.println("Authentication: "+auth);
		return db;
	}

	private void addMeterReadings() {
		DB db = getDB();
		DBCollection dbCollection = db.getCollection("MeterReading");

		 DBCollection dbCollectionMeters = db.getCollection("Meter");
		 for (int i = 0; i < dbCollectionMeters.getCount(); i++) {
			 List<DBObject> meterReadings = getMeterReadings((Meter)dbCollectionMeters.get(i),10);
			 dbCollection.insert(meterReadings);			
		}


	}

	private static List<DBObject> getMeterData(int numberOfMeters){
		Date installDate = new Date(System.currentTimeMillis());
		Address address = new Address("100", "Davir Dr.", "Cary","NC", "27560");
		
		List<DBObject> meters = new ArrayList<DBObject>();
		for (int i = 0; i < numberOfMeters; i++) {
			address.setHomeNum(address.getHomeNum()+i);
			Meter meter = new Meter("0"+i, "model"+"0"+i,installDate,address);	
			DBObject  dbo = meter.bsonFromPojo();
			meters.add(dbo);
		}
		return meters;	
	}
	private List<DBObject> getMeterReadingsDataForMeter(Meter meter, int numOfMeterReadings){
		
		Date readingTime = new Date(System.currentTimeMillis()); 
		double kwh = 0.0;
		Date installDate = new Date(System.currentTimeMillis());
		Address address = new Address("100", "Davir Dr.", "Cary","NC", "27560");
		
		List<DBObject> meterReadings = new ArrayList<DBObject>();
		for (int i = 0; i < numOfMeterReadings; i++) {
			address.setHomeNum(address.getHomeNum()+i);
			MeterReading meterReading = new MeterReading(meter, readingTime, kwh+i+10);	
			DBObject  dbo = meterReading.bsonFromPojo();
			meterReadings.add(dbo);
		}
		return meterReadings;
	
	}
}	
