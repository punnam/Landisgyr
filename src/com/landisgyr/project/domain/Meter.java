package com.landisgyr.project.domain;

import java.util.Date;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class Meter{
	private ObjectId _id;
	String meterId;
	String meterModel;
	Date installDate;
	Address address;
	public Meter(String meterId, String meterModel, Date installDate,
			Address address) {
		super();
		this.meterId = meterId;
		this.meterModel = meterModel;
		this.installDate = installDate;
		this.address = address;
	}
	
	public DBObject bsonFromPojo()
	{
		BasicDBObject document = new BasicDBObject();
		document.put( "_id",    this._id );
		document.put( "meterId",   this.meterId );
		document.put( "meterModel", this.meterModel );
		document.put( "installDate",   this.installDate );
		document.put( "address",  this.address.bsonFromPojo() );
		return document;
	}
}
