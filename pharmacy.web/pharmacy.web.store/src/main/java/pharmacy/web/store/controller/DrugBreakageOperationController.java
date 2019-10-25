package pharmacy.web.store.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import pharmacy.common.model.UserInfo;
import pharmacy.common.utils.StringUtil;
import pharmacy.web.store.model.dto.DrugAndInventoryDto;
import pharmacy.web.store.service.DrugBreakageOperationService;

@RequestMapping("home")
@Controller
public class DrugBreakageOperationController {

    @Autowired
    private DrugBreakageOperationService drugBreakageOperationService;

    // 查询药品表与药房药库库存与药品表总数
    @RequestMapping("listDrugAndInventory.ajax")
    @ResponseBody
    public JSONObject listDrugInf(HttpServletRequest request, Integer start, Integer end, String drugName) {
        System.out.println("查询了药品表");
        if (StringUtil.isNotEmpty(drugName)) {
        }
        JSONObject data = new JSONObject();
        HttpSession session = request.getSession();
        UserInfo u = (UserInfo) session.getAttribute("user");
        if (u == null) {
            data.put("drugAndInventory", null);
            return data;
        }
        DrugAndInventoryDto drugAndInventory = drugBreakageOperationService.listDrugAndInventory(start, end,
                drugName);
        data.put("drugAndInventory", drugAndInventory);
        return data;
    }
    // 药品报损
    @RequestMapping("drugBreakage.ajax")
    @ResponseBody
    public Integer drugBreakage(HttpServletRequest request,Integer num, String cause,Integer drugId,Integer batch) {
        System.out.println("进来了药品报损");
        HttpSession session = request.getSession();
        UserInfo u = (UserInfo) session.getAttribute("user");
        if(u==null) {
            return null;  
        }
        System.out.println("num:" + num + ",cause:" + cause + ",drugId:" + drugId  + ",batch:" + batch);
        Integer i = drugBreakageOperationService.drugBreakage(drugId, u.getUid(), batch, num, cause);
        System.out.println("i:"+i);
        return i;  
    }
}
