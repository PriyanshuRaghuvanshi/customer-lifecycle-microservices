package com.nagarro.verification.models;

import jakarta.persistence.Embeddable;

@Embeddable
public class Geolocation {

	private String latitude;
    private String longitude;
    
    
    
	public String getLatitude() {
		return latitude;
	}



	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}



	public String getLongitude() {
		return longitude;
	}



	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}



	@Override
	public String toString() {
		return "Geolocation [latitude=" + latitude + ", longitude=" + longitude + "]";
	}
    
    
}
