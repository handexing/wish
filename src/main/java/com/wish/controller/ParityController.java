package com.wish.controller;

import com.wish.dao.SkuInfoDao;
import com.wish.dao.SkuSrcDao;
import com.wish.entity.SkuInfo;
import com.wish.entity.SkuSrc;
import com.wish.model.ExecuteResult;
import com.wish.model.RetJson;
import com.wish.util.DateUtil;
import com.wish.vo.EchartsVo;

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

import java.util.Date;
import java.util.List;

/**
 * @author Pinky Lam 908716835@qq.com
 * @date 2017年7月24日 下午4:38:01
 */
@RestController
@RequestMapping("goods")
public class ParityController {

	@Autowired
	SkuSrcDao skuSrcDao;
	@Autowired
	SkuInfoDao skuInfoDao;

	Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping("createSkuSrc")
	public ExecuteResult<Boolean> createSkuSrc(String skuCode, String url, Integer platfmCode) {
		ExecuteResult<Boolean> result = new ExecuteResult<Boolean>();
		try {
			SkuSrc skuSrc = new SkuSrc();
			skuSrc.setPlatfmCode(platfmCode);
			skuSrc.setSkuCode(skuCode);
			skuSrc.setUrl(url);
			skuSrc.setCreateTime(new Date());
			skuSrcDao.save(skuSrc);
			result.setSuccess(true);
		} catch (Exception e) {
			result.setSuccess(false);
			logger.error("", e);
		}
		return result;
	}

	@RequestMapping("getSkuInfoList")
	public ExecuteResult<EchartsVo> getSkuInfoList(String skuSrcId) {
		ExecuteResult<EchartsVo> result = new ExecuteResult<EchartsVo>();
		try {
			List<SkuInfo> list = skuInfoDao.findSkuInfoBySkuSrcId(skuSrcId);
			EchartsVo echartsVo = new EchartsVo();
			
			if (!list.isEmpty()) {
				String[] categories = new String[list.size()];
				for (int i = 0; i < list.size(); i++) {
					categories[i] = DateUtil.getDateFormatStr(list.get(i).getDateId());
				}
				String[] data = new String[list.size()];
				for (int i = 0; i < list.size(); i++) {
					data[i] = String.valueOf(list.get(i).getPrice());
				}
				echartsVo.setCategories(categories);
				echartsVo.setData(data);
				result.setData(echartsVo);
				result.setSuccess(true);
			} else {
				result.setData(echartsVo);
				result.setSuccess(false);
			}
		} catch (Exception e) {
			result.setSuccess(false);
			logger.error("", e);
		}
		return result;
	}

	@RequestMapping("goodsList")
	public RetJson goodsList(Integer draw, Integer length, Integer start) {
		RetJson retJson = new RetJson();
		final Sort sort = new Sort(Sort.Direction.DESC, "id");
		final Pageable pageRequest = new PageRequest(start / length, length, sort);
		Page<SkuSrc> pageData = skuSrcDao.findAll(pageRequest);
		retJson.setData(pageData.getContent());
		retJson.setRecordsTotal(pageData.getTotalElements());
		retJson.setRecordsFiltered(pageData.getTotalElements());
		retJson.setDraw(draw == null ? 0 : draw);
		return retJson;
	}

	@RequestMapping("parityPage")
	public ModelAndView showParityPage() {
		return new ModelAndView("/parity/parityList");
	}

	@RequestMapping("skuInfoPage")
	public ModelAndView showSkuInfoPage() {
		return new ModelAndView("/parity/skuInfoList");
	}

	@RequestMapping("skuInfoList")
	public RetJson skuInfoList(Integer draw, Integer length, Integer start) {
		RetJson retJson = new RetJson();
		final Sort sort = new Sort(Sort.Direction.DESC, "id");
		final Pageable pageRequest = new PageRequest(start / length, length, sort);
		Page<SkuInfo> pageData = skuInfoDao.findAll(pageRequest);
		retJson.setData(pageData.getContent());
		retJson.setRecordsTotal(pageData.getTotalElements());
		retJson.setRecordsFiltered(pageData.getTotalElements());
		retJson.setDraw(draw == null ? 0 : draw);
		return retJson;
	}

}
