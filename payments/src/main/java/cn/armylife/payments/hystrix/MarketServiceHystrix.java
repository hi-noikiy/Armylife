package cn.armylife.payments.hystrix;

import cn.armylife.common.domain.AfterOrder;
import cn.armylife.common.domain.ShopOrder;
import cn.armylife.payments.feignservice.MarketService;
import org.springframework.stereotype.Component;

@Component
public class MarketServiceHystrix implements MarketService {

    @Override
    public ShopOrder selectOrder(Long orderIds){
        ShopOrder shopOrder=new ShopOrder();
        return shopOrder;
    };

    @Override
    public int plusVipHairOrder(String out_trade_no){
        return 0;
    };

    @Override
    public AfterOrder SelectAfterOrder(Long orderId){
        return null;
    };
}