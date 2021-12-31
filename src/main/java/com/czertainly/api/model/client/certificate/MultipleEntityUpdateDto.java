package com.czertainly.api.model.client.certificate;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public class MultipleEntityUpdateDto {
	
	@Schema(
            description = "UUID of Entity",
            required = true
    )
	private String uuid;
	
	@Schema(
            description = "List of UUIDs of the Certificates",
            required = true
    )
	private List<String> certificateUuids;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public List<String> getCertificateUuids() {
		return certificateUuids;
	}

	public void setCertificateUuids(List<String> certificateUuids) {
		this.certificateUuids = certificateUuids;
	}
}
