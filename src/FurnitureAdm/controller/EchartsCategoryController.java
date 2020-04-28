package FurnitureAdm.controller;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

//import javassist.bytecode.Descriptor.Iterator;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus.Series;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import FurnitureAdm.bean.goods;
import FurnitureAdm.bean.orderItem;
import FurnitureAdm.service.echartscategory.EchartsCategoryService;

@Controller
public class EchartsCategoryController {

	@Autowired
	@Qualifier("EchartsCategoryService")
	private EchartsCategoryService echartsCategoryService;
	
	@RequestMapping("/CategoryView")
	@ResponseBody
	public List<Map<String,Object>> CategoryView(orderItem OrderItem) {
		List<Map<String, Object>> SaleList = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> CategoryIDList = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> NumList = new ArrayList<Map<String, Object>>();
		Map<String, Object> CategoryNameList = new HashMap<String, Object>();
		List<Map<String,Object>> Category = new ArrayList<Map<String,Object>>(); 
		List<Map<String, Object>> FinaList = new ArrayList<Map<String, Object>>();
		List<Map<String, Object>> AllList = new ArrayList<Map<String, Object>>();

		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> name = new HashMap<String, Object>();

		Integer goodsid;
		String num;
		Integer FinalcategoryID ,categoryID;
		int code;
		String state, message;
		Integer finall = null;

		try{
			SaleList = echartsCategoryService.getSaleList(OrderItem);
//			System.out.println(JSON.toJSONString(SaleList));
		
				for(int i=0;i<SaleList.size();i++){
					goodsid = Integer.parseInt(SaleList.get(i).get("goodsid").toString());
					num = SaleList.get(i).get("num").toString();
					int idx = num.lastIndexOf(".");
					String strNum = num.substring(0,idx);
					int Newnum = Integer.valueOf(strNum);
//					System.out.println(Newnum);
					CategoryIDList = echartsCategoryService.getcategoryID(goodsid);

					for(int j = 0;j<CategoryIDList.size();j++){
					
						FinalcategoryID = Integer.parseInt(CategoryIDList.get(j).get("categoryID").toString());
//						System.out.println(FinalcategoryID);
						Category = echartsCategoryService.getcategoryName(FinalcategoryID);
						for(int k =0;k<Category.size();k++){
							String categoryname = Category.get(k).get("categoryName").toString();
							CategoryNameList.put("categoryName", categoryname);
						}
						
					
						CategoryNameList.put("newnum", Newnum);
//						System.out.println(JSON.toJSONString(CategoryNameList));
						CategoryIDList.get(j).putAll(CategoryNameList);

					}

					AllList.addAll(CategoryIDList);
//					System.out.println(JSON.toJSONString(AllList));
				}
				
				Map<String, Map<String, Object>> result = new HashMap<String, Map<String,Object>>();
		 
		        for(Map<String, Object> key : AllList){
		            String categoryName = key.get("categoryName").toString();
//		            System.out.println(categoryName);
		            String cID = key.get("categoryID").toString();
//		            System.out.println(cID);
		            String newId  = categoryName+cID;
		            Long newnum = Long.parseLong(key.get("newnum").toString());
//		            System.out.println(newnum);
		            if(result.containsKey(newId)){
		                Long temp = Long.parseLong(result.get(newId).get("newnum").toString());
//		                System.out.println(temp);
		                newnum += temp;
//		                System.out.println(newnum);
		                result.get(newId).put("newnum", newnum);
//		                System.out.println(JSON.toJSONString(result));
		                continue;
		            }
		            result.put(newId, key);
		            FinaList.add(key);
//		            System.out.println(JSON.toJSONString(FinaList));
		        }

				
//				
//				Map<String,Object> mapAll = new HashMap<String,Object>();
//				 for(Map<String,Object> key:AllList){
//			            for(Map.Entry<String, Object> entry:key.entrySet()){
//			                String categoryName = entry.getKey();
//			                System.out.println(categoryName);
//			                Object newnum = entry.getValue();
//			                System.out.println(newnum);
//			                Object newnumAll = mapAll.get(entry.getKey());
//			                if(newnumAll == null){
//			                    mapAll.put(categoryName, newnum);
//			                }else{
//			                	newnumAll = new Integer((((Integer)newnumAll).intValue() + ((Integer)newnum).intValue()));
//			                    mapAll.put(categoryName, newnumAll);
//			                }
//			            }
//			        }
//				 
//				 System.out.println(JSON.toJSONString(mapAll));
//				
//				
//				for(int i =0;i<AllList.size();i++){
//					String categoryName;
//					int newnum;
//					newnum = Integer.parseInt(AllList.get(i).get("newnum").toString());
//					categoryName = AllList.get(i).get("categoryName").toString();
////					System.out.println(categoryName);
//
//						for(int j = i+1;j<AllList.size();j++){
//							for(Entry<String,Object> key : AllList.get(j).entrySet()){
//								String str = "newnum";
//								String strcategoryName = "categoryName";
//								String eualcategoryName =null;
//								int eualnewnum = 0;
//						
//								if(str.equals(key.getKey())){
//									eualnewnum = (Integer) key.getValue();
////									System.out.println(eualnewnum);
//								}
//								if(strcategoryName.equals(key.getKey())){
//									eualcategoryName = (String) key.getValue();
////									System.out.println(eualcategoryName);
//								}
//								
//								FinaList.get(j).put("eualcategoryName", eualcategoryName);
//								FinaList.get(j).put("eualnewnum", eualnewnum);
//								System.out.println(JSON.toJSONString(FinaList));
//
//						}
//							
//							String eualName = FinaList.get(j).get("eualcategoryName").toString();
//							int eualnum = Integer.parseInt(FinaList.get(j).get("eualnewnum").toString());
//							
//							if(categoryName == eualName){
//								newnum = newnum + eualnum;
//								System.out.println(newnum);
//							}
//							
//							//遍历完成后，要删除User的name相同的数据
//							AllList.remove(AllList.get(j));
//							
//							//remove一个元素时，要把遍历的指针减一
//							j--;						
//					}
					
//				}
//				System.out.println("原始数据："+SaleList);
				
//				String name = SaleList.get("categoryName")
				
				return FinaList;	
		}catch(Exception e){
			System.out.println(e);


//			System.out.println(map);
			return null;
			}
		
	}
	
}
