package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Goods;
import util.DBUtil;
import util.Page;

public class GoodsDao {

	public void getGoodsBystr(String str,Page<?> page) {
		List<Goods> list=new ArrayList<Goods>();
		 Goods goods=null;
		 Connection con=null;
		 PreparedStatement pst=null;
		 ResultSet rs=null;
		 try{
			con=DBUtil.getCon();
			String count="select count(1) from flower where flowername like '%"+str+"%'";
			pst=con.prepareStatement(count);
			rs=pst.executeQuery();
			if(rs.next())
				page.setTotalCount(rs.getInt(1));
			
			// Ä£ºý²éÑ¯ like  
			String sql="select * from flower where flowername like ? limit ?,?";
			pst=con.prepareStatement(sql);
			pst.setString(1, "%"+str+"%");
			pst.setInt(2,(page.getCurrentPage()-1)*page.getPageCount());
			pst.setInt(3,page.getPageCount());
			rs=pst.executeQuery();
			while(rs.next()){
				goods=new Goods();
				goods.setCatalogid(rs.getInt("catalogid"));
				goods.setGoodsname(rs.getString("flowername"));
				goods.setGoodsid(rs.getInt("flowerid"));
				goods.setPicture(rs.getString("picture"));
				goods.setPrice(rs.getFloat("price"));
				list.add(goods);
			}
		 }catch(Exception e){
			e.printStackTrace(); 
		 }finally{
			 DBUtil.closeAll(con, rs, pst);
		 }
		 page.setPageData(list);
	}
   
}
