package com.retail.customer.application.validation;

import ch.qos.logback.core.util.StringUtil;
import com.retail.core.common.model.AlertModel;
import com.retail.core.common.model.AlertType;
import com.retail.core.common.validation.AbstractBeanValidator;
import com.retail.customer.application.common.ErrorConstants;
import com.retail.customer.application.model.Customer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerModelValidator extends AbstractBeanValidator<Customer> {

    @Override
    public List<AlertModel> apply(Customer request) {

        List<AlertModel> alerts = new ArrayList<>();

        if (request == null) {
            alerts.add(createAlert(
                    ErrorConstants.INVALID_LOGIN_REQUEST,
                    AlertType.ERROR));
            return alerts;
        }

        if (StringUtil.isNullOrEmpty(request.getEmail()) || StringUtil.isNullOrEmpty(request.getPassword())) {
            alerts.add(createAlert(
                    ErrorConstants.INVALID_CREDENTIAL,
                    AlertType.ERROR));
        }
        return alerts;
    }
}
