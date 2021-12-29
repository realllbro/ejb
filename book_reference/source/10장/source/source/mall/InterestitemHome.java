package kr.co.hanbitbook.ejb.examples.mall;

import javax.ejb.*;
import java.util.*;

public interface InterestitemHome extends javax.ejb.EJBLocalHome {
  public Interestitem create(String itemid) throws CreateException;
  public Interestitem create(String itemid, String itemname) throws CreateException;
  public Interestitem findByPrimaryKey(String itemid) throws FinderException;
}