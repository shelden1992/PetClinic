package com.udemy.spring.petclinic.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

/**
 * Created by Shelupets Denys on 12.07.2020.
 */
@Entity
@Table(name = "work_processing")
public class WorkProcessing extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "vet_id")
    private Vet vet;
    @Column(name = "hours_work")
    private double hoursWork;
    @Column(name = "salary")
    private double salary;
    @DateTimeFormat(pattern = "yyyy-mm-dd")
    @Column(name = "date_work")
    private LocalDate dateWork;

}
