package com.dch.fileservice.model;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Entity
@Table(name = "user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    @NotBlank(message = "name不能为空！")
    private String name;

    @Column
    @NotBlank(message = "password不能为空！")
    private String password;

    @Column
    private String note;

    @Column
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @Column
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updated;
}
