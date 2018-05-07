package com.example.demo.domain;

import sun.security.util.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "student")
public class Student {

    @Id
    @Column(length = 100)
    private String id;
    @Column
    private String name;
    @Column
    private String sex;
    @Column
    private String telephone;
    @Column
    private String symptom;
    @Column
    private String medicine;
    @Column
    private String date;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getSymptom() {
        return symptom;
    }

    public void setSymptom(String symptom) {
        this.symptom = symptom;
    }

    public String getMedicine() {
        return medicine;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Student(String id, String name, String sex, String telephone, String symptom, String medicine, String date) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.telephone = telephone;
        this.symptom = symptom;
        this.medicine = medicine;
        this.date = date;
    }

    public Student() {
    }
}
