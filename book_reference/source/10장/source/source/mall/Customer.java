package kr.co.hanbitbook.ejb.examples.mall;

import javax.ejb.*;
import java.util.*;

public interface Customer extends javax.ejb.EJBLocalObject {
  public String getId();
  public void setPassword(String password);
  public String getPassword();
  public void setName(String name);
  public String getName();
  public void setSelectitem(Collection selectitem);
  public Collection getSelectitem();
  public void setMoreinfo(Moreinfo moreinfo);
  public Moreinfo getMoreinfo();
  public void setInterestitem(Collection interestitem);
  public Collection getInterestitem();
}