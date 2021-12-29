package kr.co.hanbitbook.ejb.examples.mall;

import javax.ejb.*;
import java.util.*;

public interface Selectitem extends javax.ejb.EJBLocalObject {
  public String getId();
  public void setItemid(String itemid);
  public String getItemid();
  public void setItemname(String itemname);
  public String getItemname();
  public void setCustomer(Customer customer);
  public Customer getCustomer();
}