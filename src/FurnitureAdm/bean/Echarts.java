package FurnitureAdm.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus.Series;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSON;
import org.springframework.http.HttpStatus.Series;

public class Echarts {
	public List<String> legend = new ArrayList<String>();// 数据分组
    public List<String> category = new ArrayList<String>();// 横坐标
    public List<Series> series = new ArrayList<Series>();// 纵坐标
    public List<String> color = new ArrayList<String>();// 颜色
 
    public ECharts(List<String> legendList, List<String> categoryList,
                      List<Series> seriesList,List<String> colorList) {
        super();
        this.legend = legendList;
        this.category = categoryList;
        this.series = seriesList;
        this.color = colorList;
    }
}
