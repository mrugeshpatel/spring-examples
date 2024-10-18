package com.intuit.mycraft.api.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    @Version
    private Long version;
    private String title;
    private LocalDateTime eventTime;

    @Column(updatable = false)
    private LocalDateTime createdTime = LocalDateTime.now(ZoneOffset.UTC);

    private LocalDateTime lastModifiedTime;

    @PrePersist
    public void onPrePersist() {
        this.eventTime = LocalDateTime.now(ZoneOffset.UTC);


    }

    @PreUpdate
    public void onPreUpdate() {
        this.lastModifiedTime = LocalDateTime.now(ZoneOffset.UTC);
    }



}
