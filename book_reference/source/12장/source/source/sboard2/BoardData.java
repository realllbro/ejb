package kr.co.hanbitbook.ejb.examples.board;

public class BoardData implements java.io.Serializable{
  private int seq;
  private String name;
  private String passwd;
  private String title;
  private String content;
  private java.sql.Timestamp regdate;
  private int readcount;

  public BoardData(){
  }

  public BoardData(int seq, String name, String passwd, String title, String content, java.sql.Timestamp regdate, int readcount){
    setSeq(seq);
    setName(name);
    setPasswd(passwd);
    setTitle(title);
    setContent(content);
    setRegdate(regdate);
    setReadcount(readcount);
  }

  public void setSeq(int seq){
    this.seq = seq;
  }
  public void setName(String name){
    this.name = name;
  }
  public void setPasswd(String passwd){
    this.passwd = passwd;
  }
  public void setTitle(String title){
    this.title = title;
  }
  public void setContent(String content){
    this.content = content;
  }
  public void setRegdate(java.sql.Timestamp regdate){
    this.regdate = regdate;
  }
  public void setReadcount(int readcount){
    this.readcount = readcount;
  }

  public int getSeq(){
    return seq;
  }
  public String getName(){
    return name;
  }
  public String getPasswd(){
    return passwd;
  }
  public String getTitle(){
    return title;
  }
  public String getContent(){
    return content;
  }
  public java.sql.Timestamp getRegdate(){
    return regdate;
  }
  public int getReadcount(){
    return readcount;
  }
  public String getRegdateString(){
    java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm");
    String regdateString = sdf.format(regdate);
    return regdateString;
  }

  public java.sql.Timestamp getCurrentTimestamp(){
    long time = new java.util.Date().getTime();
    java.sql.Timestamp timeStamp = new java.sql.Timestamp(time);
    return timeStamp;
  }

  public String toString(){
    StringBuffer sb = new StringBuffer();
    sb.append("seq :" + seq + "\n");
    sb.append("name :" + name + "\n");
    sb.append("password :" + passwd + "\n");
    sb.append("title :" + title + "\n");
    sb.append("content :" + content + "\n");
    sb.append("regdate :" + regdate + "\n");
    sb.append("readcount :" + readcount + "\n");
    return sb.toString();
  }

}