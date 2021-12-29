package kr.co.hanbitbook.ejb.examples.board;

import javax.ejb.*;
import java.util.*;
import java.rmi.*;

public interface BoardManagerHome extends javax.ejb.EJBHome {
  public BoardManager create() throws CreateException, RemoteException;
}