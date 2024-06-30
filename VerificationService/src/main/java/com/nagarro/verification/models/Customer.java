package com.nagarro.verification.models;

import java.util.List;

import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;



@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "verified_customer_data")
public class Customer {
	
    @Id
    private String customerId;

    private String customerCode;
    private boolean emailVerificationStatus;
    private String firstName;
    private String lastName;
    private String contactNumber;
    private String preferredLanguage;
    private boolean paymentResponsible;
    private boolean emailSent;
    private Long msisdn;

    @Embedded
    private Geolocation geolocation;

 
    @Enumerated(EnumType.STRING)
    private ActivationStatus activationStatus ;

    
    @Transient
    private List<String> roles;

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
    
	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCustomerCode() {
		return customerCode;
	}

	public void setCustomerCode(String customerCode) {
		this.customerCode = customerCode;
	}

	public boolean isEmailVerificationStatus() {
		return emailVerificationStatus;
	}

	public void setEmailVerificationStatus(boolean emailVerificationStatus) {
		this.emailVerificationStatus = emailVerificationStatus;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getPreferredLanguage() {
		return preferredLanguage;
	}

	public void setPreferredLanguage(String preferredLanguage) {
		this.preferredLanguage = preferredLanguage;
	}

	public boolean isPaymentResponsible() {
		return paymentResponsible;
	}

	public void setPaymentResponsible(boolean paymentResponsible) {
		this.paymentResponsible = paymentResponsible;
	}

	public boolean isEmailSent() {
		return emailSent;
	}

	public void setEmailSent(boolean emailSent) {
		this.emailSent = emailSent;
	}

	public Geolocation getGeolocation() {
		return geolocation;
	}

	public void setGeolocation(Geolocation geolocation) {
		this.geolocation = geolocation;
	}



	public ActivationStatus getActivationStatus() {
		return activationStatus;
	}

	public void setActivationStatus(ActivationStatus activationStatus) {
		this.activationStatus = activationStatus;
	}

	@Override
	public String toString() {
		return "Customer [customerId=" + customerId + ", customerCode=" + customerCode + ", emailVerificationStatus="
				+ emailVerificationStatus + ", firstName=" + firstName + ", lastName=" + lastName + ", contactNumber="
				+ contactNumber + ", preferredLanguage=" + preferredLanguage + ", paymentResponsible="
				+ paymentResponsible + ", emailSent=" + emailSent + ", msisdn=" + msisdn + ", geolocation="
				+ geolocation + ", activationStatus=" + activationStatus + ", roles=" + roles + "]";
	}

	public Long getMsisdn() {
		return msisdn;
	}

	public void setMsisdn(Long msisdn) {
		this.msisdn = msisdn;
	}


}
