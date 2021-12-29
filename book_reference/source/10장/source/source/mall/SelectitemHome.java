package kr.co.hanbitbook.ejb.examples.mall;

import javax.ejb.*;
import java.util.*;

public interface SelectitemHome extends javax.ejb.EJBLocalHome {
  public Selectitem create(String id) throws CreateException;
  public Selectitem createMethod1(String id, String itemid, String itemname) throws CreateException;
  public Selectitem findByPrimaryKey(String id) throws FinderException;
}