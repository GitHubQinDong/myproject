package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import model.Catalog;
import model.Goods;
import util.DBUtil;
import util.Page;

public class CatalogDao {
   //查询所有分类
	public List<Catalog> getCatalogs(){
	 List<Catalog> list=new ArrayList<Catalog>();
	 Connection con=null;
	 PreparedStatement pst=null;
	 ResultSet rs=null;
	 Catalog cg=null;
	 try{
		con=DBUtil.getCon();
		String sql="select * from catalog";
		pst=con.prepareStatement(sql);
		rs=pst.executeQuery();
		while(rs.next()){
			cg=new Catalog();
			cg.setCatalogid(rs.getInt("catalogid"));
			cg.setCatalogname(rs.getString("catalogname"));
			
			list.add(cg);
		}
	 }catch(Exception e){
		e.printStackTrace(); 
	 }finally{
		 DBUtil.closeAll(con, rs, pst);
	 }
	 return list;	
	}
// 通过cid查询产品列表
	public void getGoodsBycid(int cid,Page<?> page) {
		List<Goods> list=new ArrayList<Goods>();
		 Goods goods=null;
		 Connection con=null;
		 PreparedStatement pst=null;
		 ResultSet rs=null;
		 try{
			con=DBUtil.getCon();
			String count="select count(1) from flower where catalogid="+cid;
			pst=con.prepareStatement(count);
			rs=pst.executeQuery();
			if(rs.next())
				page.setTotalCount(rs.getInt(1));
			String sql="select * from flower where catalogid=? limit ?,?";
			pst=con.prepareStatement(sql);
			pst.setInt(1, cid);
			pst.setInt(2,(page.getCurrentPage()-1)*page.getPageCount());
			pst.setInt(3,page.getPageCount());
			rs=pst.executeQuery();
			while(rs.next()){
				goods=new Goods();
				goods.setCatalogid(cid);
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
