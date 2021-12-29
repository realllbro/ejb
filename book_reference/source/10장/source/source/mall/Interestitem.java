package kr.co.hanbitbook.ejb.examples.mall;

import javax.ejb.*;
import java.util.*;

public interface Interestitem extends javax.ejb.EJBLocalObject {
  public String getItemid();
  public void setItemname(String itemname);
  public String getItemname();
  public void setCustomer(Collection customer);
  public Collection getCustomer();
}