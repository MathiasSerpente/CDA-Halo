package com.halo.ms_data_gateway.entity;

import com.halo.ms_data_gateway.enums.Convention;
import com.halo.ms_data_gateway.enums.RequestStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "requests",
        indexes = {
                @Index(name = "idx_request_user_id", columnList = "user_id"),
                @Index(name = "idx_request_profession_id", columnList = "profession_id"),
                @Index(name = "idx_request_status", columnList = "status")
        }
)

public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Le nom du projet est obligatoire")
    @Size(max = 255, message = "Le nom du projet ne doit pas dépasser 255 caractères")
    @Column(nullable = false, length = 255)
    private String projectName;

    @NotNull(message = "La convention est obligatoire")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private Convention convention;

    @NotNull(message = "Le métier est obligatoire")
    @ManyToOne(optional = false)
    @JoinColumn(name = "profession_id", nullable = false)
    private Profession profession;

    @NotNull(message = "Le taux horaire (brut) est obligatoire")
    @Digits(integer = 8, fraction = 2, message = "Le taux horaire (brut) doit être un nombre avec au maximum 2 décimales")
    @DecimalMin(value = "0.00", inclusive = true, message = "Le taux horaire (brut) doit être positif")
    @Column(precision = 10, scale = 2)
    private BigDecimal wage;

    @NotBlank(message = "Le prénom de l’intermittent est obligatoire")
    @Size(max = 50, message = "Le prénom ne doit pas dépasser 50 caractères")
    @Column(name = "intermittent_first_name", nullable = false, length = 50)
    private String intermittentFirstName;

    @NotBlank(message = "Le nom de l’intermittent est obligatoire")
    @Size(max = 50, message = "Le nom ne doit pas dépasser 50 caractères")
    @Column(name = "intermittent_last_name", nullable = false, length = 50)
    private String intermittentLastName;

    @NotBlank(message = "Le téléphone de l’intermittent est obligatoire")
    @Size(max = 20, message = "Le téléphone ne doit pas dépasser 20 caractères")
    @Column(name = "intermittent_phone", nullable = false, length = 20)
    private String intermittentPhone;

    @NotBlank(message = "L’email de l’intermittent est obligatoire")
    @Email(message = "L’email n’est pas valide")
    @Size(max = 100, message = "L’email ne doit pas dépasser 100 caractères")
    @Column(name = "intermittent_email", nullable = false, length = 100)
    private String intermittentEmail;

    @NotNull(message = "La date de début est obligatoire")
    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @NotNull(message = "La date de fin est obligatoire")
    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @NotBlank(message = "Le type d’horaire est obligatoire")
    @Size(max = 100, message = "Le type d’horaire ne doit pas dépasser 100 caractères")
    @Column(name = "time_type", nullable = false, length = 100)
    private String timeType;

    @NotBlank(message = "Le pays est obligatoire")
    @Size(max = 100, message = "Le pays ne doit pas dépasser 100 caractères")
    @Column(nullable = false, length = 100)
    private String country;

    @Size(max = 100, message = "L’adresse ne doit pas dépasser 255 caractères")
    @Column(name = "address", length = 100)
    private String address;

    @NotBlank(message = "Le code postal est obligatoire")
    @Pattern(regexp = "\\d{5}", message = "Le code postal doit contenir 5 chiffres")
    @Column(name = "postal_code", nullable = false, length = 10)
    private String postalCode;

    @NotBlank(message = "La ville est obligatoire")
    @Size(max = 100, message = "La ville ne doit pas dépasser 100 caractères")
    @Column(nullable = false, length = 100)
    private String city;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private RequestStatus status = RequestStatus.SUBMITTED;

    @Column(name = "processed_at")
    private LocalDateTime processedAt;

    @Column(name = "archived_at")
    private LocalDateTime archivedAt;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public String getPhoneFormatted() {
        if (intermittentPhone == null) return "";
        return intermittentPhone.replaceAll("(\\d{2})(?=\\d)", "$1 ").trim();
    }

    @AssertTrue(message = "La date de début doit être antérieure ou égale à la date de fin")
    public boolean isDateRangeValid() {
        return startDate == null || endDate == null || !startDate.isAfter(endDate);
    }

    @PrePersist
    void prePersist() {
        if (status == null) status = RequestStatus.SUBMITTED;
    }

    public Request() {
    }

    public Request(Long id, String projectName, Convention convention, Profession profession, BigDecimal wage, String intermittentFirstName, String intermittentLastName, String intermittentPhone, String intermittentEmail, LocalDate startDate, LocalDate endDate, String timeType, String country, String address, String postalCode, String city, User user, RequestStatus status, LocalDateTime processedAt, LocalDateTime archivedAt, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.projectName = projectName;
        this.convention = convention;
        this.profession = profession;
        this.wage = wage;
        this.intermittentFirstName = intermittentFirstName;
        this.intermittentLastName = intermittentLastName;
        this.intermittentPhone = intermittentPhone;
        this.intermittentEmail = intermittentEmail;
        this.startDate = startDate;
        this.endDate = endDate;
        this.timeType = timeType;
        this.country = country;
        this.address = address;
        this.postalCode = postalCode;
        this.city = city;
        this.user = user;
        this.status = status;
        this.processedAt = processedAt;
        this.archivedAt = archivedAt;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Convention getConvention() {
        return convention;
    }

    public void setConvention(Convention convention) {
        this.convention = convention;
    }

    public Profession getProfession() {
        return profession;
    }

    public void setProfession(Profession profession) {
        this.profession = profession;
    }

    public BigDecimal getWage() {
        return wage;
    }

    public void setWage(BigDecimal wage) {
        this.wage = wage;
    }

    public String getIntermittentFirstName() {
        return intermittentFirstName;
    }

    public void setIntermittentFirstName(String intermittentFirstName) {
        this.intermittentFirstName = intermittentFirstName;
    }

    public String getIntermittentLastName() {
        return intermittentLastName;
    }

    public void setIntermittentLastName(String intermittentLastName) {
        this.intermittentLastName = intermittentLastName;
    }

    public String getIntermittentPhone() {
        return intermittentPhone;
    }

    public void setIntermittentPhone(String intermittentPhone) {
        this.intermittentPhone = intermittentPhone;
    }

    public String getIntermittentEmail() {
        return intermittentEmail;
    }

    public void setIntermittentEmail(String intermittentEmail) {
        this.intermittentEmail = intermittentEmail;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getTimeType() {
        return timeType;
    }

    public void setTimeType(String timeType) {
        this.timeType = timeType;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public RequestStatus getStatus() {
        return status;
    }

    public void setStatus(RequestStatus status) {
        this.status = status;
    }

    public LocalDateTime getProcessedAt() {
        return processedAt;
    }

    public void setProcessedAt(LocalDateTime processedAt) {
        this.processedAt = processedAt;
    }

    public LocalDateTime getArchivedAt() {
        return archivedAt;
    }

    public void setArchivedAt(LocalDateTime archivedAt) {
        this.archivedAt = archivedAt;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
