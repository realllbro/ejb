package kr.co.hanbitbook.ejb.examples.mall;

import javax.ejb.*;
import java.util.*;

public interface MoreinfoHome extends javax.ejb.EJBLocalHome {
  public Moreinfo create(String id) throws CreateException;
  public Moreinfo create(String id, int point) throws CreateException;
  public Moreinfo findByPrimaryKey(String id) throws FinderException;
}