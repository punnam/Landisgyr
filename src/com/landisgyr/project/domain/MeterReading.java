package com.landisgyr.project.domain;

import java.util.Date;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class MeterReading {
	private ObjectId _id;
	Meter meter;
	Date readingTime;
	double kwh;
	public MeterReading(Meter meter, Date readingTime, double kwh) {
		super();
		this.meter = meter;
		this.readingTime = readingTime;
		this.kwh = kwh;
	}
	public DBObject bsonFromPojo()
	{
	
		BasicDBObject document = new BasicDBObject();
		document.put( "_id",    this._id );
		document.put( "meter",   this.meter.bsonFromPojo() );
		document.put( "readingTime", this.readingTime );
		document.put( "kwh",   this.kwh );
		return document;
	}
}
