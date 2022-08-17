package com.cj.freelanceapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * CLASS ANNOTATION
 */
@Entity
/**
 * DATA ANNOTATION FOR GETTER AND SETTER
 * METHODS
 */
@Data
/**
 * ALL ARGUMENT CONSTRUCTOR
 */
@AllArgsConstructor
/**
 * NO ARGUMENT CONSTRUCTOR
 */
@NoArgsConstructor
/**
 * BUILDER PATTERN
 */
@Builder
public class OtherPlatformAccountLinks {
    /**
     * ID PRIMARY KEY
     */
    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;
    /**
     * git link
     */
    @Column(length = 100)
    private String gitLink;

    /**
     * twitter link
     */
    private String twitterLink;

    /**
     * instagram link
     */
    private String instagramLink;

    /**
     * webstite link
     */
    private String websiteLink;

    /**
     * facebook link
     */
    private String facebookLink;

    /**
     * git link
     */
//    private String gitLink;

}
