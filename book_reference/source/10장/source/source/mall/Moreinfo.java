package kr.co.hanbitbook.ejb.examples.mall;

import javax.ejb.*;
import java.util.*;
import java.math.*;

public interface Moreinfo extends javax.ejb.EJBLocalObject {
  public String getId();
  public void setPoint(int point);
  public int getPoint();
  public void setCustomer(Customer customer);
  public Customer getCustomer();
}