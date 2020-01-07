package cn.armylife.admin.mapper;

import cn.armylife.admin.domain.DeliveryOrder;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DeliveryOrderMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table delivery_order
     *
     * @mbggenerated
     */
    int insert(DeliveryOrder record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table delivery_order
     *
     * @mbggenerated
     */
    List<DeliveryOrder> selectAll();
}