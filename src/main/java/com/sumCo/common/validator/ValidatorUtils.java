package com.sumCo.common.validator;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import com.sumCo.common.exception.AppException;

import java.util.Set;

/**
 * 參考文檔：http://docs.jboss.org/hibernate/validator/5.4/reference/en-US/html_single/
 * @author oplus
 * @Description: TODO(hibernate-validator校驗工具類)
 * @date 2017-6-23 15:07
 */
public class ValidatorUtils {
    private static Validator validator;

    static {
        validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    /**
     * 校驗對象
     * @param object        待校驗對象
     * @param groups        待校驗的組
     * @throws AppException  校驗不通過，AppException
     */
    public static void validateEntity(Object object, Class<?>... groups)
            throws AppException {
        Set<ConstraintViolation<Object>> constraintViolations = validator.validate(object, groups);
        if (!constraintViolations.isEmpty()) {
            StringBuilder msg = new StringBuilder();
            for(ConstraintViolation<Object> constraint:  constraintViolations){
                msg.append(constraint.getMessage()).append("<br>");
            }
            throw new AppException(msg.toString());
        }
    }
}
