package cn.itlzq.service;


import cn.itlzq.db.PayMentsDao;
import cn.itlzq.model.Payments;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 作者:李泽庆
 * @version 创建时间:2020/2/21 0:03
 * @email 邮箱:905866484@qq.com
 * @description 描述：
 */
@Service
public class PayMentsService {
    @Resource
    private  PayMentsDao dao ;

    /**
     * 获取所有支付方式
     * @return 支付方式集合
     */
    public  List<Payments> getPayments(){
        return dao.getPayments();
    }

      /**
     * 通过id取得支付
     * @param id id
     * @return 结果
     */
    public  Payments getById(int id){
        return dao.getById(id);
    }
}
