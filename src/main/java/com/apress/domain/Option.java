package com.apress.domain;

import com.sun.istack.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.Valid;

@Entity
public class Option {
    @Id
    @GeneratedValue
    @Column(name="OPTION_ID")
    private Long id;

    @Column(name="OPTION_VALUE")
    private String value;

    public Option(Long id, String value) {
        this.id = id;
        this.value = value;
    }


    private Option() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        if (value == null){
            throw new NullPointerException("Value cannot be null");
        }
        if (value.equals("")) {
            throw new IllegalArgumentException("Value cannot be empty");
        } else{
        this.value = value;}
    }
}
