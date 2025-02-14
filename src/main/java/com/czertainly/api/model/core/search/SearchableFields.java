package com.czertainly.api.model.core.search;

import com.czertainly.api.model.common.enums.IPlatformEnum;
import com.czertainly.api.model.common.enums.cryptography.KeyAlgorithm;
import com.czertainly.api.model.common.enums.cryptography.KeyFormat;
import com.czertainly.api.model.common.enums.cryptography.KeyType;
import com.czertainly.api.model.core.certificate.CertificateStatus;
import com.czertainly.api.model.core.compliance.ComplianceStatus;
import com.czertainly.api.model.core.cryptography.key.KeyState;
import com.czertainly.api.model.core.cryptography.key.KeyUsage;
import com.czertainly.api.model.core.discovery.DiscoveryStatus;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum SearchableFields {
    COMMON_NAME("commonName", null),
    SERIAL_NUMBER("serialNumber", null),
    RA_PROFILE_NAME("raProfile.name", null),
    STATUS("status", CertificateStatus.class),
    COMPLIANCE_STATUS("complianceStatus", ComplianceStatus.class),
    GROUP_NAME("group.name", null),
    OWNER("owner", null),
    ISSUER_COMMON_NAME("issuerCommonName", null),
    SIGNATURE_ALGORITHM("signatureAlgorithm", null),
    FINGERPRINT("fingerprint", null),
    NOT_AFTER("notAfter", null),
    NOT_BEFORE("notBefore", null),
    PUBLIC_KEY_ALGORITHM("publicKeyAlgorithm", null),
    KEY_SIZE("keySize", null),
    KEY_USAGE("keyUsage", null),
    BASIC_CONSTRAINTS("basicConstraints", null),
    META("meta", null),
    SUBJECT_ALTERNATIVE_NAMES("subjectAlternativeNames", null),
    SUBJECTDN("subjectDn", null),
    ISSUERDN("issuerDn", null),
    ISSUER_SERIAL_NUMBER("issuerSerialNumber", null),
    OCSP_VALIDATION("ocspValidation", null),
    CRL_VALIDATION("crlValidation", null),
    SIGNATURE_VALIDATION("signatureValidation", null),
    START_TIME("startTime", null),
    END_TIME("endTime", null),
    TOTAL_CERT_DISCOVERED("totalCertificatesDiscovered", null),
    CONNECTOR_NAME("connectorName", null),
    KIND("kind", null),
    NAME("name", null),
    CK_GROUP("cryptographicKey.group.name", null),
    CK_OWNER("cryptographicKey.owner", null),
    CK_TOKEN_PROFILE("cryptographicKey.tokenProfile.name", null),
    CK_TOKEN_INSTANCE("cryptographicKey.tokenInstanceReference.name", null),
    CKI_TYPE("type", KeyType.class),
    CKI_FORMAT("format", KeyFormat.class),
    CKI_STATE("state", KeyState.class),
    CKI_LENGTH("length", null),
    CKI_USAGE("usage", KeyUsage.class),
    CKI_CRYPTOGRAPHIC_ALGORITHM("cryptographicAlgorithm", KeyAlgorithm.class),
    DISCOVERY_STATUS("status", DiscoveryStatus.class),

    ENTITY_NAME("entityInstanceReference.name", null),
    ENTITY_CONNECTOR_NAME("entityInstanceReference.connectorName", null),
    ENTITY_KIND("entityInstanceReference.kind", null),
    ENTITY_INSTANCE_NAME("entityInstanceName", null),
    ENABLED("enabled", null),
    SUPPORT_MULTIPLE_ENTRIES("supportMultipleEntries", null),
    SUPPORT_KEY_MANAGEMENT("supportKeyManagement", null);

    private final String field;

    private final Class<? extends IPlatformEnum> enumClass;


    private final Map<String, String> nativeCodeMap = Stream.of(new String[][]{
            {"commonName", "common_name"},
            {"serialNumber", "serial_number"},
            {"raProfile", "ra_profile_id"},
            {"entity", "entity_id"},
            {"status", "status"},
            {"group", "group_id"},
            {"owner", "owner"},
            {"issuerCommonName", "issuer_common_name"},
            {"signatureAlgorithm", "signature_algorithm"},
            {"fingerprint", "fingerprint"},
            {"notAfter", "not_after"},
            {"notBefore", "not_before"},
            {"publicKeyAlgorithm", "public_key_algorithm"},
            {"keySize", "key_size"},
            {"keyUsage", "key_usage"},
            {"basicConstraints", "basic_constraints"},
            {"meta", "meta"},
            {"subjectAlternativeNames", "subject_alternative_names"},
            {"subjectDn", "subject_dn"},
            {"issuerDn", "issuer_dn"},
            {"issuerSerialNumber", "issuer_serial_number"},
            {"ocspValidation", "ocspValidation"},
            {"crlValidation", "ocspValidation"},
            {"signatureValidation", "ocspValidation"},
            {"name", "name"},

    }).collect(Collectors.toMap(data -> data[0], data -> data[1]));

    SearchableFields(String string, Class<? extends IPlatformEnum> enumClass) {
        this.field = string;
        this.enumClass = enumClass;
    }

    @JsonValue
    public String getCode() {
        return field;
    }

    public String getNativeCode() {
        return nativeCodeMap.get(field);
    }

    public Class<? extends IPlatformEnum> getEnumClass() {
        return enumClass;
    }

    @JsonCreator
    public static SearchableFields fromCode(String field) {
        return Arrays.stream(values())
                .filter(e -> e.field.equals(field))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("Unsupported type %s.", field)));
    }
}
