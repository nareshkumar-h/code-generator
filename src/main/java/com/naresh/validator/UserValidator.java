package com.naresh.validator;

import java.lang.Long;
import java.lang.Object;
import org.springframework.stereotype.Component;

@Component
public class UserValidator {
  public void beforeSave() {
  }

  public void beforeUpdate() {
  }

  public void beforeDelete(Long id) {
  }

  public Object beforeFindById(Long id) {
    return null;
  }
}
