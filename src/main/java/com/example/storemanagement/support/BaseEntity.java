package com.example.storemanagement.support;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;

import static com.example.storemanagement.utils.GlobalParameters.TableColumns.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = CREATED_BY, nullable = false, updatable = false)
    @CreatedBy
    private String createdBy;

    @Column(name = CREATED_AT, nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;

    @Column(name = UPDATED_AT, insertable = false)
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Column(name = UPDATED_BY, insertable = false)
    @LastModifiedBy
    private String updatedBy;

    @Version
    @Column(name = "_version")
    private Long version;
}
