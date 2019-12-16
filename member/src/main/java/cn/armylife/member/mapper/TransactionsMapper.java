package cn.armylife.member.mapper;

import cn.armylife.common.domain.Transactions;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TransactionsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table transactions
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer tranactionsId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table transactions
     *
     * @mbggenerated
     */
    int insert(Transactions record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table transactions
     *
     * @mbggenerated
     */
    Transactions selectByPrimaryKey(Integer tranactionsId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table transactions
     *
     * @mbggenerated
     */
    List<Transactions> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table transactions
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Transactions record);

    /**
     * 查询零钱明细
     * @param payUser
     * @return
     */
    Page<Transactions> selectForDelivery(Long payUser);
}