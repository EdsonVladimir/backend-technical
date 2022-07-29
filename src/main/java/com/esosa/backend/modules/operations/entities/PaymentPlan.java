package com.esosa.backend.modules.operations.entities;
import lombok.Data;
@Data
public class PaymentPlan {
    private static final long serialVersionUID = 1L;
    private Long id_payment_plan;
    private int number_installments;
    private double interest;
    private double discount;
    private String name_plan;
}
