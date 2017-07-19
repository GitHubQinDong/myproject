package dao;

import java.sql.*;

import util.DBUtil;

import model.User;

public class UserDao {
  // 登录（查看数据库有没有该用户）
   public User login(String uname, String psw) {
	 User user=null;
	 Connection con=null;
	 PreparedStatement st=null; 
	 ResultSet rs=null;
	  try{
		  //1.连接数据库
		  con= DBUtil.getCon();
		  String sql="select * from user where username=? and password=?";
		 //2 创建prepareStatement对象
		  st=con.prepareStatement(sql);
		  st.setString(1,uname);
		  st.setString(2, psw);
		// 3.执行sql
		  rs=st.executeQuery();
		//4.处理结果
		  if(rs.next()){
			  user=new User();
			  user.setUserid(rs.getInt("userid"));
			  user.setUsername(uname);
			  user.setPassword(psw);
			  user.setRole(rs.getString("role"));
		  }  
	  }catch(Exception e){
		 e.printStackTrace(); //打印异常堆栈信息
		  System.out.println(e.getMessage());
	  }finally{
		  //释放资源
		  DBUtil.closeAll(con, rs, st);
	  }
	  return user;
  }
  // 注册（user表insert 信息）	
   public boolean register(User user){
	   Connection con=null;
	   PreparedStatement st=null;
	   boolean result=false;//执行结果
	   try{
	   //1.连接数据库
	    con=DBUtil.getCon();
	   //2.sql语句
	    String sql="insert into user(username,password,role) values(?,?,?)";
	   //3. 创建statement对象
	      st= con.prepareStatement(sql);
	    st.setString(1, user.getUsername());
	    st.setString(2,user.getPassword());
	    st.setString(3, "customer");
	   //4.执行sql
	    st.execute();
	    result=true;
	  }catch(Exception e){
		  System.out.println(e.getMessage());
	  }finally{//释放资源
		  DBUtil.closeAll(con, null, st);
	  }
	   return result;
   }
  
//查询用户名是否重复   
public boolean checkUserName(String user) {
	boolean result=false;
	Connection con=null;
	PreparedStatement pst=null;
	ResultSet rs=null;
	try{
		  //1.连接数据库
		  con= DBUtil.getCon();
		  String sql="select * from user where username=?";
		 //2 创建prepareStatement对象
		  pst=con.prepareStatement(sql);
		  pst.setString(1,user);
		// 3.执行sql
		  rs=pst.executeQuery();
		//4 处理结果集
		  if(rs.next()){
			  result=true;
		  }
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		DBUtil.closeAll(con, rs, pst);
	}
	return result;
}


//修改密码	
  // 管理订单地址	

}
