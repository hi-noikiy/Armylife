package cn.armylife.admin.mapper;

import cn.armylife.admin.domain.Product;

import java.util.List;

public interface ProductMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product
     *
     * @mbggenerated
     */
    int insert(Product record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table product
     *
     * @mbggenerated
     */
    List<Product> selectAll();
}