package pharmacy.web.store.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pharmacy.common.model.DrugSales;
import pharmacy.common.model.DrugType;
import pharmacy.common.model.HouseInventory;
import pharmacy.web.store.dao.DrugSendMapper;
import pharmacy.web.store.model.dto.DrugInfDto;
import pharmacy.web.store.service.DrugSendService;
import pharmacy.web.store.solr.DrugInfSolr;
import pharmacy.web.store.solr.SolrUtil;

@Service
public class DrugSendServiceImpl implements DrugSendService {

    @Autowired
    private DrugSendMapper drugSendMapper;
    // solr目录名
    private static String core = "new_core";
    
    @Transactional(rollbackFor = Exception.class)
    public List<DrugType> listDrugType(Integer fid) {
        // 根据药品大类ID查询下属分类
        List<DrugType> list = drugSendMapper.listDrugType(fid);
        return list;
    }

    @Transactional(rollbackFor = Exception.class)
    public List<DrugInfDto> listDrugInf(DrugInfSolr drugInf) {
        System.out.println("66"+drugInf.toString());
        // 搜索查询药品
        try {
            List<DrugInfDto> list = SolrUtil.getDocument(core, drugInf, true,0,100);
            for (DrugInfDto drugInfDto : list) {
                System.out.println(drugInfDto.toString());
                System.out.println();
            }
            return list;
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        };
        return null;
    }

    @Override
    public Integer drugSendLog(List<DrugSales> list,Integer uid) {
        // 药品销售记录
        int count = 0;
        //查询是否有药品数量大于剩余库存
        for (DrugSales ds : list) {
            Integer sum = drugSendMapper.getDrugCount(ds.getDrugId());
            System.out.println("sum:"+sum+"count:"+ds.getSalesCount()+"id:"+ds.getDrugId());
            if(sum==null||ds.getSalesCount()>sum) return -2;
        }
        //查询配伍禁忌
        for (DrugSales ds : list) {
            for (DrugSales ds2 : list) {
                if(ds2.getDrugId()!=ds.getDrugId()) {
                    Integer i = drugSendMapper.drugTabooCheck(ds.getDrugId(), ds2.getDrugId());
                    System.out.println("禁忌:"+i);
                    if(i!=null) return -3;
                }
            }
        }
        //计入销售记录
        for (DrugSales ds : list) {
            Integer i = drugSendMapper.drugSendLog(ds.getDrugId(), uid, ds.getSalesCount(), ds.getSalesCostPrice(), ds.getSalesSellPrice());
            count+=i;
            List<HouseInventory> listHouseInventory = drugSendMapper.listHouseInventory(ds.getDrugId());
            Integer num = ds.getSalesCount();
            //药房库存减少
            for (HouseInventory hi : listHouseInventory) {
                if(num<hi.getHouseCount()) {
                    drugSendMapper.updateDrugCount(hi.getHouseId(), num);
                  	break;
                }else {
                    drugSendMapper.updateDrugCount(hi.getHouseId(), hi.getHouseCount());
                    num-= hi.getHouseCount();
                }
            }
        }
        return count;
    }

}
