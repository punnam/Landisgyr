package com.landisgyr.project.domain;

import org.bson.types.ObjectId;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class Address {
	private ObjectId _id;
	private String homeNum;
	private String StreetName;
	private String City;
	private String State;
	private String zip;
	public Address(String homeNum, String streetName, String city,
			String state, String zip) {
		super();
		this.homeNum = homeNum;
		StreetName = streetName;
		City = city;
		State = state;
		this.zip = zip;
	}
	public String getHomeNum() {
		return homeNum;
	}
	public void setHomeNum(String homeNum) {
		this.homeNum = homeNum;
	}
	public String getStreetName() {
		return StreetName;
	}
	public void setStreetName(String streetName) {
		StreetName = streetName;
	}
	public String getCity() {
		return City;
	}
	public void setCity(String city) {
		City = city;
	}
	public String getState() {
		return State;
	}
	public void setState(String state) {
		State = state;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	
	public DBObject bsonFromPojo()
	{
		BasicDBObject document = new BasicDBObject();
		document.put( "_id",    this._id );
		document.put( "StreetName",   this.StreetName );
		document.put( "City", this.City );
		document.put( "State",   this.State );
		document.put( "zip",  this.zip );
		return document;
	}
}
