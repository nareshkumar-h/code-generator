package com.naresh.controller;

import java.lang.Long;
import java.lang.Object;
import java.lang.String;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserController {
  @PostMapping
  public String save() {
    return null;
  }

  @PostMapping
  public String update() {
    return null;
  }

  @GetMapping
  public String delete(Long id) {
    return null;
  }

  @GetMapping
  public List findAll() {
    return null;
  }

  @GetMapping
  public Object findById(Long id) {
    return null;
  }
}
