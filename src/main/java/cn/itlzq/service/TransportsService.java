package cn.itlzq.service;


import cn.itlzq.db.TransportsDao;
import cn.itlzq.model.Transports;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 作者:李泽庆
 * @version 创建时间:2020/2/21 0:08
 * @email 邮箱:905866484@qq.com
 * @description 描述：
 */
@Service
public class TransportsService {
    @Resource
    private  TransportsDao dao ;
    /**
     * 获取所有的快递方式
     * @return 快递
     */
    public  List<Transports> getTransports(){
        return dao.getTransports();
    }

    public  Transports getById(int id){
        return dao.getById(id);
    }
}
