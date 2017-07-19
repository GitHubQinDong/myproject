package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import util.DBUtil;
import model.Order;

public class OrderDao {

	public boolean gomai(Order order,String cids) {
		// 1.order�� insert �����ﳵcartɾ����orderid����ֵ��
		Connection con=null;
		   PreparedStatement st=null;
		   Statement st2=null;
		   boolean result=false;//ִ�н��
		   try{
		   //1.�������ݿ�
		    con=DBUtil.getCon();
		   //2.sql���
		    System.out.println(order.getSubtime());
		    String sql="insert into orders(subtime,totalmoney,telphone,address,userid) values (?,?,?,?,?)";
		   //3.����statement����
		    st=con.prepareStatement(sql,1);
		      st.setTimestamp(1, order.getSubtime());
		      st.setFloat(2, order.getTotalmoney());
		      st.setString(3, order.getTelphone());
		      st.setString(4, order.getAddress());
		      st.setInt(5, order.getUserid());
		   //4.ִ��sql
		     //����һ����
		     con.setAutoCommit(false);//��������
		     st.executeUpdate();
		     int orderid=0;//����������������ֵ
		     ResultSet rs=st.getGeneratedKeys();//��ȡ��������������id
		     if(rs.next()){
		    	 orderid=rs.getInt(1) ;
			 } 
		    
		     //update cart set orderid=1  where cartid in(1,2,3)
		     String sql2="update cart set orderid="+orderid+" where cartid in ("+cids+")";
		     System.out.println(sql2);
		     st2=con.createStatement();
		     st2.execute(sql2);
		     
		     con.commit();//�����ύ
		     
		     result=true;
		  }catch(Exception e){
			  System.out.println(e.getMessage());
			  try {
				con.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}//����ع�
			  
		  }finally{//�ͷ���Դ
			  DBUtil.closeAll(con, null, st);
		  }
		   return result;	
	}

}
