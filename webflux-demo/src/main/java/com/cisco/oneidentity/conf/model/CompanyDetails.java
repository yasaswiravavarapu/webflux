package com.cisco.oneidentity.conf.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Document(collection = "CompanyDetails")
public class CompanyDetails {
    @Id
    private String id;

    @NotBlank
    @Size(max = 140)
    private String companyName;
    
    @NotBlank
    @Size(max = 140)
    private String domain;
    
    @NotBlank
    @Size(max = 140)
    private String email;

	@NotNull
    private Date createdAt = new Date();

    public CompanyDetails() {

    }

    public CompanyDetails(String text) {
        this.id = id;
        this.companyName = text;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
 
    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
    

    public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
