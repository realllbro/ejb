package kr.co.hanbitbook.ejb.guestbook;

import javax.ejb.SessionBean;
import javax.ejb.SessionContext;
import java.util.*;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;

public class GuestbookBean implements SessionBean{
	private Context ctx = null;
	private DataSource ds = null;

	public void addGuestbookBean(GuestbookDataBean gb){

		Connection con = null;
		PreparedStatement stmt = null;

		String sql = "insert into guestbook values(guestbook_seq.NEXTVAL, ?, ?)";

		try{
			con = ds.getConnection();
			con.setAutoCommit(false);
			stmt = con.prepareStatement(sql);
			stmt.setString(1, gb.getName());
			stmt.setString(2, gb.getContent());
			stmt.executeUpdate();
			con.commit();
		}catch(Exception e){
			System.out.println("���� ����� ���� :" + e.toString());
		}finally{
			if(stmt != null){
				try{
					stmt.close();
				}catch(SQLException ex){}
			}
			if(con != null){
				try{
					con.close();
				}catch(SQLException ex){}
			}
		} // finally
	}

	public ArrayList getGuestbookBean(){
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList list = new ArrayList();

		String sql = "select seq, name, content from guestbook order by seq desc";

		try{
			con = ds.getConnection();
			con.setAutoCommit(false);
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			while(rs.next()){
				GuestbookDataBean gdb = new GuestbookDataBean();
				gdb.setSeq(rs.getInt(1));
				gdb.setName(rs.getString(2));
				gdb.setContent(rs.getString(3));
				list.add(gdb);
			}
		}catch(Exception e){
			System.out.println("����Ʈ ������ ���� :" + e.toString());
		}finally{
			if(rs != null){
				try{
					rs.close();
				}catch(SQLException ex){}
			}
			if(stmt != null){
				try{
					stmt.close();
				}catch(SQLException ex){}
			}
			if(con != null){
				try{
					con.close();
				}catch(SQLException ex){}
			}
		} // finally
		return list;
	}

	public void deleteGuestbookBean(int seq){
		Connection con = null;
		PreparedStatement stmt = null;

		String sql = "delete from guestbook where seq = ?";

		try{
			con = ds.getConnection();
			con.setAutoCommit(false);
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, seq);
			stmt.executeUpdate();
			con.commit();
		}catch(Exception e){
			System.out.println("������ ����:" + e.toString());
		}finally{
			if(stmt != null){
				try{
					stmt.close();
				}catch(SQLException ex){}
			}
			if(con != null){
				try{
					con.close();
				}catch(SQLException ex){}
			}
		} // finally
	}


	public void ejbCreate(){
		try{
			ctx = new InitialContext();
			ds = (javax.sql.DataSource)ctx.lookup("ora9");
		}catch(Exception ex){}
	}

	public void setSessionContext(SessionContext c){}

	public void ejbRemove(){
		ctx = null;
		ds = null;
	}

	public void ejbActivate(){
		try{
			ctx = new InitialContext();
			ds = (javax.sql.DataSource)ctx.lookup("ora9");
		}catch(Exception ex){}	
	}

	public void ejbPassivate(){	
		ctx = null;
		ds = null;
	}
}