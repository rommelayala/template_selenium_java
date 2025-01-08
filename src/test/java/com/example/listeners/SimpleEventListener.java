package com.example.listeners;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.events.WebDriverListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SimpleEventListener implements WebDriverListener {
  @Override
  public void beforeClick(WebElement element) {
    System.out.println("------->>>>> Aqui mi listener");
    System.out.println("Click detectado en: " + element);
  }

  @Override
  public void onError(Object target, Method method, Object[] args, InvocationTargetException e) {
    System.out.println("Para por favarrrr");
    System.out.println("SimpleEventListener ----> Error detectado: " + e.getTargetException().getMessage());
  }
}