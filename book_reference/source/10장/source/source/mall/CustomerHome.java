package kr.co.hanbitbook.ejb.examples.mall;

import javax.ejb.*;
import java.util.*;

public interface CustomerHome extends javax.ejb.EJBLocalHome {
  public Customer create(String id) throws CreateException;
  public Customer create(String id, String password, String name) throws CreateException;
  public Customer findByPrimaryKey(String id) throws FinderException;
}