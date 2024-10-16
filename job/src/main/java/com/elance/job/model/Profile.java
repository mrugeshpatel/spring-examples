package com.elance.job.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Version
    private Integer version;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private String skillSet;
    private String headLine;
    @OneToMany(mappedBy = "profile", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<JobApplicant> applications = new HashSet<JobApplicant>();
    @CreationTimestamp(source = SourceType.DB)
    private Date createdOn;
    @UpdateTimestamp(source = SourceType.DB)
    private Date updatedOn;


}
