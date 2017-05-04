package com.wish.controller;

import com.wish.dao.ArticleDao;
import com.wish.entity.Article;
import com.wish.model.ExecuteResult;
import com.wish.model.ImgaeVo;
import com.wish.model.RetJson;
import com.wish.service.ArticleService;
import com.wish.util.PageUtil;
import com.wish.util.TimeUtil;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Iterator;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("article")
public class ArticleController {

	/** 允许上传的扩展名 */
	private static final String[] extensionPermit = { "jpg", "png", "gif" };

	@Autowired
	ArticleDao articleDao;
	@Autowired
	ArticleService articleService;

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping("articleList")
	public RetJson articleList(String title, Integer draw, Integer length, Integer start) {
		RetJson retJson = new RetJson();
		final Sort sort = new Sort(Sort.Direction.DESC, "id");
		final Pageable pageRequest = new PageRequest(PageUtil.calcPage(start), length, sort);

		if (StringUtils.isEmpty(title)) {
			Page<Article> pageData = articleDao.findAll(pageRequest);
			retJson.setData(pageData.getContent());
			retJson.setRecordsTotal(pageData.getTotalElements());
			retJson.setRecordsFiltered(pageData.getTotalElements());
			retJson.setDraw(draw == null ? 0 : draw);
		} else {
			title = title.replaceAll("_", "\\\\_").replaceAll("%", "\\\\%").replaceAll(" ", "\\\\ ");
			Page<Article> pageData = articleDao.findByTitle(title, pageRequest);
			retJson.setData(pageData.getContent());
			retJson.setRecordsTotal(pageData.getTotalElements());
			retJson.setRecordsFiltered(pageData.getTotalElements());
			retJson.setDraw(draw == null ? 0 : draw);
		}
		return retJson;
	}

	@RequestMapping("createArticle")
	public ExecuteResult<Boolean> createArticle(String title, String content, Integer status, Long id) {
		ExecuteResult<Boolean> result = new ExecuteResult<Boolean>();
		try {
			articleService.saveArticle(title, content, status, id);
			result.setSuccess(true);
		} catch (Exception e) {
			result.setSuccess(false);
			logger.error("", e);
		}
		return result;
	}

	@RequestMapping("getArticle")
	public ExecuteResult<Article> getArticle(Long id) {
		ExecuteResult<Article> result = new ExecuteResult<Article>();
		try {
			result.setData(articleDao.findOne(id));
			result.setSuccess(true);
		} catch (Exception e) {
			result.setSuccess(false);
			logger.error("", e);
		}
		return result;
	}

	/**
	 * 多文件上传
	 * 
	 * @param multipartRequest
	 * @param response
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/imgUpload")
	@ResponseBody
	public String imgUpload(MultipartHttpServletRequest multipartRequest, HttpServletResponse response) {

		response.setContentType("image/jpeg");

		ImgaeVo pimg = null;
		String saveDirectoryPath = "D:/telecom/wish/upload/image/";

		for (Iterator it = multipartRequest.getFileNames(); it.hasNext();) {
			String key = (String) it.next();
			MultipartFile imgFile = multipartRequest.getFile(key);

			if (imgFile.getOriginalFilename().length() > 0) {
				try {
					saveDirectoryPath = saveDirectoryPath + TimeUtil.getTimeYMD();
					String uuid = UUID.randomUUID().toString();
					String name = "/" + uuid + ".jpg";
					File saveDirectory = new File(saveDirectoryPath);
					if (!saveDirectory.isDirectory() && !saveDirectory.exists()) {
						saveDirectory.mkdirs();
					}
					if (!imgFile.isEmpty()) {
						String fileName = imgFile.getOriginalFilename();
						String fileExtension = FilenameUtils.getExtension(fileName);
						if (!ArrayUtils.contains(extensionPermit, fileExtension)) {
							throw new Exception("No Support extension.");
						}

						saveDirectoryPath = saveDirectoryPath + name;
						FileOutputStream out = new FileOutputStream(saveDirectoryPath);
						// 写入文件
						out.write(imgFile.getBytes());
						out.flush();
						out.close();

						pimg = new ImgaeVo();
						String s = multipartRequest.getContextPath().toString();
						pimg.setImgurl(s + "/upload/image/" + TimeUtil.getTimeYMD() + name);
						pimg.setTitle(key);
					}
				} catch (Exception e) {
					logger.info("上传异常", e);
				}
			}
		}
		return pimg.getImgurl();
	}

	@RequestMapping("articlePage")
	public ModelAndView showArticlePage() {
		return new ModelAndView("/article/articleList");
	}

}
