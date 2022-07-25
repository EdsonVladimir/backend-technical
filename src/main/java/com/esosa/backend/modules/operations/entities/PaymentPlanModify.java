package com.esosa.backend.modules.operations.entities;

import lombok.Data;
@Data
public class PaymentPlanModify {
    private static final long serialVersionUID = 1L;
    private int number_installments;
    private double interest;
    private double discount;
}
