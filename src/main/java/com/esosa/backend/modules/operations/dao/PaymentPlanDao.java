package com.esosa.backend.modules.operations.dao;

import com.esosa.backend.modules.operations.entities.PaymentPlan;
import com.esosa.backend.modules.operations.entities.PaymentPlanModify;
import org.apache.ibatis.annotations.*;

import java.util.List;
@Mapper
public interface PaymentPlanDao {
    @Select("SELECT id_payment_plan, number_installments,interest, discount, name_plan FROM operations.payment_plan")
    List<PaymentPlan> paimentPlanList();
    @Insert("insert into operations.payment_plan(number_installments,interest, discount) values (#{number_installments}, #{interest}, #{discount})")
    void createPaimentPlan(PaymentPlanModify data);
    @Update("update operations.payment_plan set number_installments=#{number_installments},interest=#{interest}, discount=#{discount}  where id_payment_plan=#{id_payment_plan}")
    void updatePaimentPlan(PaymentPlan data);
    @Delete("update operations.payment_plan set status=0 where id_payment_plan=#{id_payment_plan}")
    void deletePaimentPlan(Long id);
}
