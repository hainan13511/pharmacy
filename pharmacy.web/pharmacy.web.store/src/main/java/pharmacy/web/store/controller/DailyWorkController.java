package pharmacy.web.store.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import pharmacy.common.model.DrugInf;
import pharmacy.common.model.DrugType;
import pharmacy.common.model.UserInfo;
import pharmacy.common.utils.StringUtil;
import pharmacy.web.store.model.dto.DrugApplyDto;
import pharmacy.web.store.model.dto.ListDrugInfDto;
import pharmacy.web.store.service.DailyWorkService;
import pharmacy.web.store.solr.DrugInfSolr;

@RequestMapping("home")
@Controller
public class DailyWorkController {
    @Autowired
    private DailyWorkService dailyWorkService;

  //根据药品大类ID查询下属分类
    @RequestMapping("listDrugType.ajax")
    @ResponseBody
    public JSONArray listDrugType(HttpServletRequest request,Integer fid) {
        System.out.println("查询了选中药品信息:");
        HttpSession session = request.getSession();
        UserInfo u = (UserInfo) session.getAttribute("user");
        if (u == null) {
            return null;
        }
        List<DrugType> list = dailyWorkService.listDrugType(fid);
        JSONArray arr = (JSONArray) JSONArray.toJSON(list);
        return arr;
    }
  //根据药品类ID查询下属药品
    @RequestMapping("sendListDrugInf.ajax")
    @ResponseBody
    public JSONArray sendListDrugInf(HttpServletRequest request,Integer dtId) {
        System.out.println("查询了选中药品信息:");
        HttpSession session = request.getSession();
        UserInfo u = (UserInfo) session.getAttribute("user");
        if (u == null) {
            return null;
        }
        List<DrugInf> list = dailyWorkService.listDrugInf(dtId);
        JSONArray arr = (JSONArray) JSONArray.toJSON(list);
        return arr;
    }
    // 查询药品申请表与总数
    @RequestMapping("listDrugApply.ajax")
    @ResponseBody
    public JSONObject listDrugApply(HttpServletRequest request, Integer start, Integer end, String drugName,
            String dateStart, String dateEnd, Integer checkState) {
        System.out.println("查询了药品申请表");
        if (StringUtil.isNotEmpty(drugName)) {
        }
        JSONObject data = new JSONObject();
        HttpSession session = request.getSession();
        UserInfo u = (UserInfo) session.getAttribute("user");
        if(u==null) {
            data.put("DrugApplyDto", null);
            return data;  
        }
        DrugApplyDto applyDto = dailyWorkService.listDrugApply(start, end, drugName, dateStart, dateEnd, checkState,u.getUid());
        data.put("DrugApplyDto", applyDto);
        return data;
    }
    // 查询药品表与总数
    @RequestMapping("listDrugInf.ajax")
    @ResponseBody
    public JSONObject listDrugInf(HttpServletRequest request, Integer start, Integer end, DrugInfSolr drugInf) {
        System.out.println("查询了药品表1:"+drugInf.getDrugName());
        if (StringUtil.isNotEmpty(drugInf.getDrugName())) {
        }
        JSONObject data = new JSONObject();
        HttpSession session = request.getSession();
        UserInfo u = (UserInfo) session.getAttribute("user");
        if(u==null) {
            data.put("listDrugInf", null);
            return data;  
        }
        ListDrugInfDto listDrugInfDto = dailyWorkService.listDrugInf(start, end, drugInf);
        data.put("listDrugInf", listDrugInfDto);
        return data;
    }

    // 修改请领单
    @RequestMapping("alterApply.action")
    @ResponseBody
    public Integer updateApply(Integer applyId, Integer altNum) {
        System.out.println("id:" + applyId + ",altNum:" + altNum);
        Integer i = dailyWorkService.updateApply(applyId, altNum);
        System.out.println("修改请领单"+i);
        return i;
    }
    
    // 新增请领单
    @RequestMapping("DrugApplyConfirm.ajax")
    @ResponseBody
    public Integer insertDrugApply(HttpServletRequest request,String drugName, Integer drugNum) {
        System.out.println("进来了");
        HttpSession session = request.getSession();
        UserInfo u = (UserInfo) session.getAttribute("user");
        if(u==null) {
            return null;  
        }
        System.out.println("drugName:" + drugName + ",drugNum:" + drugNum);
        Integer i = dailyWorkService.insertDrugApply(drugName, drugNum, u.getUid());
        return i;
    }
    // 调整药品零售价
    @RequestMapping("updateDrugPrice.ajax")
    @ResponseBody
    public Integer updateDrugPrice(HttpServletRequest request,Integer userId, Integer beforePrice,Integer price,Integer drugId) {
        System.out.println("进来了调整药品零售价");
        HttpSession session = request.getSession();
        UserInfo u = (UserInfo) session.getAttribute("user");
        if(u==null) {
            return null;  
        }
        System.out.println("userId:" + userId + ",price:" + price + ",drugId:" + drugId + ",beforePrice:" + beforePrice);
        Integer i = dailyWorkService.updateDrugPrice(userId,beforePrice, price, drugId);
        return i;
    }
}
