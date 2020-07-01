package cn.itlzq.service;


import cn.itlzq.db.RegoinDao;
import cn.itlzq.model.CnRegion;
import cn.itlzq.util.DataUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 作者:李泽庆
 * @version 创建时间:2020/2/17 21:27
 * @email 邮箱:905866484@qq.com
 * @description 描述：
 */
@Service
public class RegionService {
    @Resource
    private RegoinDao dao ;

    public List<CnRegion> findByLevel(int level){
        if(level == 1){
            List<CnRegion > citys = (List<CnRegion>) DataUtil.data.get("citys");
            if(citys == null){
                citys = dao.findByLevel(level);
                DataUtil.data.put("citys",citys);
            }
            return citys;
        }
        return dao.findByLevel(level);
    }


    public  CnRegion findByCode(String code){
        return dao.findByCode(code);
    }


    public  List<CnRegion> findByParentCode(String parentCode){
        return dao.findByParentCode(parentCode);
    }
}
