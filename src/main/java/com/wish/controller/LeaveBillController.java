package com.wish.controller;

import com.wish.dao.LeaveBillDao;
import com.wish.entity.LeaveBill;
import com.wish.entity.User;
import com.wish.model.ExecuteResult;
import com.wish.model.RetJson;
import com.wish.service.LeaveBillService;
import com.wish.util.PageUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * @author handx 908716835@qq.com
 * @date 2017年5月17日 下午10:01:11
 */

@RestController
@RequestMapping("leaveBill")
public class LeaveBillController {

	@Autowired
	LeaveBillDao leaveBillDao;
	@Autowired
	LeaveBillService leaveBillService;

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "addLeaveBill")
	public ExecuteResult<Boolean> addLeaveBill(HttpSession session, LeaveBill leaveBill) {
		ExecuteResult<Boolean> result = new ExecuteResult<Boolean>();
		try {
			User user = (User) session.getAttribute("user");
			leaveBillService.saveLeaveBill(leaveBill, user);
			result.setSuccess(true);
		} catch (Exception e) {
			result.setSuccess(false);
			logger.error("", e);
		}
		return result;
	}

	@RequestMapping("leaveBillList")
	public RetJson leaveBillList(Integer draw, Integer length, Integer start) {
		RetJson retJson = new RetJson();
		final Sort sort = new Sort(Sort.Direction.DESC, "id");
		final Pageable pageRequest = new PageRequest(PageUtil.calcPage(start), length, sort);
		Page<LeaveBill> pageData = leaveBillDao.findAll(pageRequest);
		retJson.setData(pageData.getContent());
		retJson.setRecordsTotal(pageData.getTotalElements());
		retJson.setRecordsFiltered(pageData.getTotalElements());
		retJson.setDraw(draw == null ? 0 : draw);
		return retJson;
	}

	@RequestMapping("leaveBillPage")
	public ModelAndView showArticlePage() {
		return new ModelAndView("/activiti/leaveBillList");
	}

}
