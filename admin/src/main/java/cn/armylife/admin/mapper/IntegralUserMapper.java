package cn.armylife.admin.mapper;

import cn.armylife.admin.domain.IntegralUser;

import java.util.List;

public interface IntegralUserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table integral_user
     *
     * @mbggenerated
     */
    int insert(IntegralUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table integral_user
     *
     * @mbggenerated
     */
    List<IntegralUser> selectAll();
}