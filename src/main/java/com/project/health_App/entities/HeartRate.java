package com.project.health_App.entities;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "heart_rates")
public class HeartRate {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;

    @Column(nullable = false)
    private int heartRate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "recorded_at", nullable = false, updatable = false)
    private Date recordedAt;

    public HeartRate() {
        this.recordedAt = new Date(); // Automatically set timestamp when created
    }

    public HeartRate(Patient patient, int heartRate) {
        this.patient = patient;
        this.heartRate = heartRate;
        this.recordedAt = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public int getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(int heartRate) {
        this.heartRate = heartRate;
    }

    public Date getRecordedAt() {
        return recordedAt;
    }

    public void setRecordedAt(Date recordedAt) {
        this.recordedAt = recordedAt;
    }

    @Override
    public String toString() {
        return "HeartRate [id=" + id + ", patient=" + (patient != null ? patient.getId() : "null") +
               ", heartRate=" + heartRate + ", recordedAt=" + recordedAt + "]";
    }
}
