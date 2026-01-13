package com.marketdata.market_data_service.cacp.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

/**
 * Base entity for all Corporate Action types
 * stockGroup and dateKey are TRANSIENT (not mapped to database columns)
 * They are calculated in memory after entity is loaded
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class BaseCAEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    /**
     * Stock group/code - NOT in database, calculated from actual columns
     */
    @Transient
    private String stockGroup;
    
    /**
     * Key date for sorting - NOT in database, calculated from actual date columns
     */
    @Transient
    private LocalDateTime dateKey;
    
    /**
     * Get the type identifier for this CA
     */
    public abstract String getCAType();
    
    /**
     * Update derived fields after loading from database
     * Override this method in child entities
     */
    protected abstract void updateDerivedFields();
    
    /**
     * JPA callback - called after entity is loaded/persisted/updated
     */
    @PostLoad
    @PostPersist
    @PostUpdate
    protected void afterLoad() {
        updateDerivedFields();
    }
}
