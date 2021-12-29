import javax.rmi.*;
import javax.naming.*;
import javax.transaction.*;
import kr.co.hanbitbook.ejb.examples.student2.*;

public class Student2Test{
	public static void main(String args[]){
		Context ctx = null;
		try{
			java.util.Properties p = new java.util.Properties();
			p.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
			p.put(Context.PROVIDER_URL, "t3://localhost:7001");
			ctx = new InitialContext(p);
			Object h = ctx.lookup("Student2EJB");
			Student2Home home = (Student2Home)PortableRemoteObject.narrow(h, Student2Home.class);
			Student2 Student2 = home.findByPrimaryKey(new Integer(10));
			System.out.println("�����̸Ӹ� Ű ���� 10�� �л��� ����");
			System.out.println("ssn :" + Student2.getSsn());
			System.out.println("�̸� :" + Student2.getName());
			System.out.println("Grade :" + Student2.getGrade());
			System.out.println("--------------------------------------------------");
			java.util.Collection col = home.findStudentsInGrade(85, 97);
			java.util.Iterator iter = col.iterator();
			System.out.println("Grade�� 85�̻� 97�̸��� �л����� ����");
			while(iter.hasNext()){
				Student2 tmp = (Student2)iter.next();
				System.out.println("ssn :" + tmp.getSsn());
				System.out.println("�̸� :" + tmp.getName());
				System.out.println("Grade :" + tmp.getGrade());
				System.out.println("---");
			}
		}catch(Exception e){
			System.out.println(e.toString());
		}
	}
}
