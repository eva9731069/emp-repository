package com.sumCo.modules.sys.controller;

import com.sumCo.common.utils.PageUtils;
import com.sumCo.common.utils.Query;
import com.sumCo.common.utils.Result;
import com.sumCo.modules.sys.entity.CheckInVo;
import com.sumCo.modules.sys.formBean.CheckInFormBean;
import com.sumCo.modules.sys.service.CheckInService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/sys/checkIn")
public class CheckInController extends AbstractController {

    @Autowired
    @Qualifier("CheckInServiceImpl")
    private CheckInService checkInService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public Result list(@RequestParam Map<String, Object> params) {
        Query query = new Query(params);

        //避免返回按鈕未帶入查詢參數，導致查詢SQL有錯誤訊息
        if ("".equals(query.get("startDate")) || "".equals(query.get("endDate"))) {
            return null;
        }

        List<CheckInVo> checkInList = checkInService.queryList(query);
        int total = checkInList.size();

        PageUtils pageUtil = new PageUtils(checkInList, total, query.getLimit(), query.getPage());

        return Result.ok().put("page", pageUtil);
    }

    /**
     * 儲存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:checkIn:save")
    public Result checkIn(@RequestBody CheckInFormBean checkInFormBean) {

        String msg = "";

        if (checkInFormBean.getStatus().equals("0")) {
            msg = checkInService.checkIn(getUser().getId(), getUser().getUsername());
        } else {
            msg = checkInService.checkOut(getUser().getId(), getUser().getUsername(), checkInFormBean.getIsCheckOutConfirm());
        }

        return Result.ok(msg);
    }


}
