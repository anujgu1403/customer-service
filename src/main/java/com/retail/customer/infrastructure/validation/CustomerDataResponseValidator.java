package com.retail.customer.infrastructure.validation;

import com.retail.core.common.model.AlertModel;
import com.retail.core.common.model.AlertType;
import com.retail.core.common.validation.AbstractBeanValidator;
import com.retail.customer.application.common.ErrorConstants;
import com.retail.customer.infrastructure.entity.CustomerEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerDataResponseValidator extends AbstractBeanValidator<CustomerEntity> {

    @Override
    public List<AlertModel> apply(CustomerEntity customerEntity) {
        List<AlertModel> alerts = new ArrayList<>();
        if (null!= customerEntity && customerEntity.isUserExists()) {
            alerts.add(createAlert(
                    ErrorConstants.EMAIL_ALREADY_EXISTS,
                    AlertType.ERROR));

        }

        if (null== customerEntity) {
            alerts.add(createAlert(
                    ErrorConstants.EMAIL_NOT_EXISTS,
                    AlertType.ERROR));

        }
        return alerts;
    }
}
