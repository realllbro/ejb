package kr.co.hanbitbook.ejb.examples.board;

import javax.ejb.*;
import java.util.*;
import java.rmi.*;

public interface BoardManager extends javax.ejb.EJBObject {
  public void addBoardData(BoardData boardData) throws RemoteException;
  public void deleteBoardData(int seq) throws RemoteException;
  public void updateBoardData(BoardData boardData) throws RemoteException;
  public kr.co.hanbitbook.ejb.examples.board.BoardData getBoardData(int seq) throws RemoteException;
  public java.util.Collection getBoardDataList(int page) throws RemoteException;
  public int getBoardDataCount() throws RemoteException;
  public boolean isWriter(int seq, String passwd) throws RemoteException;
  public boolean isNextPage(int page) throws RemoteException;
  public boolean isPrevPage(int page) throws RemoteException;
  public int getPageCount() throws RemoteException;
  public void updateReadCount(int seq) throws RemoteException;
}