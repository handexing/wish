package com.wish.controller;

import com.wish.dao.RoleDao;
import com.wish.dao.UserDao;
import com.wish.entity.Role;
import com.wish.entity.User;
import com.wish.model.ErrorCode;
import com.wish.model.ExecuteResult;
import com.wish.model.RetJson;
import com.wish.service.UserService;
import com.wish.util.MD5Util;
import com.wish.util.PageUtil;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("user")
public class UserController {

	@Autowired
    UserDao backendUserDao;
	@Autowired
	RoleDao cmsGroupDao;

    @Autowired
	UserService backendUserService;

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping("addUser")
	public ExecuteResult<Boolean> addUser(String name, String account, Long roleId, String phone) {
		ExecuteResult<Boolean> result = new ExecuteResult<Boolean>();
		try {
			backendUserService.addUser(name, name, roleId, phone);
			result.setSuccess(true);
		} catch (Exception e) {
			result.setSuccess(false);
			logger.error("", e);
		}
		return result;
	}

	@RequestMapping(value = "getRoleList")
	public List<Role> getRoleList() {
		return cmsGroupDao.findAll();
	}

    @RequestMapping(value = "logout", method = RequestMethod.GET)
	public void logout(HttpSession session, HttpServletResponse response) throws IOException {
		session.invalidate();
		response.sendRedirect("../login.jsp");
	}

	@RequestMapping(value = "login", method = RequestMethod.POST)
    public ExecuteResult<User> newLogin(HttpSession session, final String account, final String password) {
        ExecuteResult<User> result = new ExecuteResult<User>();
		User user = backendUserDao.findByAccountAndPassword(account, MD5Util.md5(password));
        if (user != null) {
            session.setAttribute("user", user);
            result.setSuccess(true);
            result.setData(user);
        } else {
            result.setSuccess(false);
            result.setErrorCode(ErrorCode.USERNAME_OR_PWD_ERROR.name());
            result.setErrorMsg(ErrorCode.USERNAME_OR_PWD_ERROR.getErrorMsg());
        }
        return result;
    }

	@RequestMapping("userPage")
	public ModelAndView showUserPage() {
		return new ModelAndView("/system/userList");
	}

	@RequestMapping("updateUser")
	public ExecuteResult<Boolean> updateUser(Long id, String name, Long roleId) {
		ExecuteResult<Boolean> result = new ExecuteResult<Boolean>();
		try {
			backendUserService.updateUser(id, name, roleId);
			result.setSuccess(true);
		} catch (Exception e) {
			result.setSuccess(false);
			logger.error("", e);
		}
		return result;
	}

	@RequestMapping("userList")
	public RetJson userList(String account, Integer draw, Integer length, Integer start) {
		RetJson retJson = new RetJson();
		final Sort sort = new Sort(Sort.Direction.DESC, "id");
		final Pageable pageRequest = new PageRequest(PageUtil.calcPage(start), length, sort);

		if (StringUtils.isEmpty(account)) {
			Page<User> pageData = backendUserDao.findAll(pageRequest);
			retJson.setData(pageData.getContent());
			retJson.setRecordsTotal(pageData.getTotalElements());
			retJson.setRecordsFiltered(pageData.getTotalElements());
			retJson.setDraw(draw == null ? 0 : draw);
		} else {
			account = account.replaceAll("_", "\\\\_").replaceAll("%", "\\\\%").replaceAll(" ", "\\\\ ");
			Page<User> pageData = backendUserDao.findByAccount(account, pageRequest);
			retJson.setData(pageData.getContent());
			retJson.setRecordsTotal(pageData.getTotalElements());
			retJson.setRecordsFiltered(pageData.getTotalElements());
			retJson.setDraw(draw == null ? 0 : draw);
		}
		return retJson;
	}
}
