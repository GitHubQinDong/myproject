package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Cart;
import model.Goods;

import util.DBUtil;

public class CartDao {

    //添加购物车
	public boolean addCart(int gid_, int userid) {
		boolean flag=false;
		Connection con=null;
		PreparedStatement pst=null;
		//如果购物车有产品编号为gid_，就数量+1,否则插入数据库
		try{
			con=DBUtil.getCon();
		    String selSql="select * from cart where flowerid=?";
		    pst=con.prepareStatement(selSql);
		    pst.setInt(1, gid_);
		    if(pst.executeQuery().next()){
		    	String updatesql="update  cart set quantity=quantity+1 where flowerid=?";
		    	pst=con.prepareStatement(updatesql);
		    	pst.setInt(1, gid_);
		    	pst.execute();
		    }else{
		    	String sql="insert into cart(userid,flowerid,quantity) values(?,?,?)";
				pst=con.prepareStatement(sql);
				pst.setInt(1, userid);
				pst.setInt(2, gid_);
				pst.setInt(3, 1);
				pst.execute();
		    }
		    flag=true;
		    
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtil.closeAll(con, null, pst);
		}
		return flag;
	}
// 查看购物车
	public List<Cart> showCartByUserid(int uid){
		List<Cart> list=new ArrayList<Cart>();
		Connection con=null;
		PreparedStatement pst=null;
		ResultSet rs=null;
		Cart cart=null;
		Goods goods=null;
		try{
		   	con=DBUtil.getCon();
		   	String sql="select c.*,f.* from cart c,flower f where c.userid=? and c.flowerid=f.flowerid and c.orderid is null";
			pst=con.prepareStatement(sql);
		   	pst.setInt(1, uid);
		   	rs=pst.executeQuery();
		  while(rs.next()){
			  cart=new Cart();
			  cart.setCartid(rs.getInt("cartid"));
			  cart.setUserid(uid);
			  cart.setFlowerid(rs.getInt(3));
			  cart.setQuantity(rs.getInt("quantity"));
			  
			  goods=new Goods();
			  goods.setCatalogid(rs.getInt("catalogid"));
			  goods.setGoodsid(rs.getInt(5));
			  goods.setGoodsname(rs.getString("flowername"));
			  goods.setPicture(rs.getString("picture"));
			  goods.setPrice(rs.getFloat("price"));
			  
			  cart.setGoods(goods);
			  
			  list.add(cart);		  
		  } 	 	
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtil.closeAll(con, rs, pst);
		}
		return list;
	}
	//删除购物车信息
	public boolean delCartItem(int cartid) {
		boolean result=false;
		Connection con=null;
		PreparedStatement pst=null;
		try{
			con=DBUtil.getCon();
			String sql="delete from cart where cartid=?";
			pst=con.prepareStatement(sql);
			pst.setInt(1, cartid);
			pst.execute();
			result=true;
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			DBUtil.closeAll(con, null, pst);
		}
		return result;
	}

   //修改购物车产品数量
	
	
	
}
