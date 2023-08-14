package com.sumCo.common.validator.group;

import javax.validation.GroupSequence;

/**
 * @author oplus
 * @Description: TODO(定義校驗顺序，如果AddGroup組失敗，則UpdateGroup組不會再校驗)
 * @date 2017-6-23 15:07
 */
@GroupSequence({AddGroup.class, UpdateGroup.class})
public interface Group {

}
