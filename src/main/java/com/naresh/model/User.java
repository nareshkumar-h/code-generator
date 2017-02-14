package com.naresh.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(
    name = "users"
)
public class User {
}
