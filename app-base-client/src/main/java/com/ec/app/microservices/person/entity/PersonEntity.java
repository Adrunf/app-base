package com.ec.app.microservices.person.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * @author arobayo
 */
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "person", schema = "base_app")
public class PersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "person_id")
    private Integer personId;
    
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    
    @Column(name = "gender", nullable = false, length = 20)
    private String gender;
    
    @Column(name = "age", nullable = false)
    private Integer age;
    
    @Column(name = "identification", nullable = false, length = 20, unique = true)
    private String identification;
    
    @Column(name = "status", nullable = false)
    private Boolean status;
    
    @Column(name = "created_date", updatable = false)
    private java.sql.Timestamp createdDate;
    
    @Column(name = "last_modified_date")
    private java.sql.Timestamp lastModifiedDate;
}
