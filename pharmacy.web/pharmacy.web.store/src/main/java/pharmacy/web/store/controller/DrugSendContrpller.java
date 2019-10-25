package pharmacy.web.store.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;

import pharmacy.common.model.DrugSales;
import pharmacy.common.model.UserInfo;
import pharmacy.web.store.model.dto.DrugInfDto;
import pharmacy.web.store.service.DrugSendService;
import pharmacy.web.store.solr.DrugInfSolr;

@RequestMapping("home")
@Controller
public class DrugSendContrpller {

    @Autowired
    private DrugSendService drugSendService;
    
  //查询了选中药品
    @RequestMapping("selectDrugInfo.ajax")
    @ResponseBody
    public JSONArray selectDrugInfo(HttpServletRequest request,DrugInfSolr drugInf) {
        System.out.println("查询了选中药品信息:");
        HttpSession session = request.getSession();
        UserInfo u = (UserInfo) session.getAttribute("user");
        if (u == null) {
            return null;
        }
        List<DrugInfDto> list = drugSendService.listDrugInf(drugInf);
        JSONArray arr = (JSONArray) JSONArray.toJSON(list);
        return arr;
    }
    
    // 药品发药
    @RequestMapping("drugSend.ajax")
    @ResponseBody
    public Integer drugSend(HttpServletRequest request,String idArr) {
        System.out.println("进来了药品发药");
        if(idArr==null) {
            System.out.println("空的");
            return -1;
        }
        HttpSession session = request.getSession();
        UserInfo u = (UserInfo) session.getAttribute("user");
        if(u==null) {
            return null;  
        }
        List<DrugSales> list = JSON.parseArray(idArr, DrugSales.class);
        System.out.println(list.toString());
        Integer i = drugSendService.drugSendLog(list, u.getUid());
        return i;
        
    }
}
