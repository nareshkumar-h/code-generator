package com.naresh.controller.rest;

import java.lang.Long;
import java.lang.Object;
import java.lang.String;
import java.util.List;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
  @PostMapping
  public String save() {
    return null;
  }

  @PutMapping("/{id}")
  public String update(@PathVariable Long id) {
    return null;
  }

  @DeleteMapping("/{id}")
  public String delete(@PathVariable Long id) {
    return null;
  }

  @GetMapping
  public List findAll() {
    return null;
  }

  @GetMapping("/{id}")
  public Object findById(@PathVariable Long id) {
    return null;
  }
}
