package com.edlo.demodatabinding.model;

import com.google.gson.annotations.SerializedName;

import org.json.JSONObject;

import java.io.Serializable;

/**
 * Author: moi0312
 * Created by: ModelGenerator
 */
public class ItemSpot implements Serializable {
	@SerializedName("_id")
	private String Id;
	@SerializedName("ParkName")
	private String ParkName;
	@SerializedName("Name")
	private String Name;
	@SerializedName("YearBuilt")
	private String YearBuilt;
	@SerializedName("OpenTime")
	private String OpenTime;
	@SerializedName("Image")
	private String Image;
	@SerializedName("Introduction")
	private String Introduction;

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getParkName() {
		return ParkName;
	}

	public void setParkName(String parkName) {
		ParkName = parkName;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getYearBuilt() {
		return YearBuilt;
	}

	public void setYearBuilt(String yearBuilt) {
		YearBuilt = yearBuilt;
	}

	public String getOpenTime() {
		return OpenTime;
	}

	public void setOpenTime(String openTime) {
		OpenTime = openTime;
	}

	public String getImage() {
		return Image;
	}

	public void setImage(String image) {
		Image = image;
	}

	public String getIntroduction() {
		return Introduction;
	}

	public void setIntroduction(String introduction) {
		Introduction = introduction;
	}
}