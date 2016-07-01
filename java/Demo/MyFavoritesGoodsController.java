package com.gome.memberFront.web.controller.myFavorites;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSONArray;
import com.dell.atpdto.d8_2.request.AreaAtpRequestDetailDto;
import com.dell.atpdto.d8_2.request.AreaAtpRequestDto;
import com.dell.atpdto.d8_2.response.AreaAtpResponseDetailDto;
import com.gome.atpservice.api.query.IAreaQueryService;
import com.gome.framework.base.ResultDTO;
import com.gome.frontSe.interfaces.IProdDetailService;
import com.gome.memberCenter.common.domain.Pagination;
import com.gome.memberCenter.common.util.RepeatingRequestFilter;
import com.gome.memberCenter.common.util.RetrieveProductDetailUrl;
import com.gome.memberCenter.facade.reserve.IReserveManagerFacade;
import com.gome.memberCenter.model.enu.MemberCenterEnum;
import com.gome.memberCore.lang.model.UserResult;
import com.gome.memberCore.utils.utils.DateUtil;
import com.gome.memberCore.utils.utils.JsonSerializeUtil;
import com.gome.memberCore.utils.utils.StringUtil;
import com.gome.memberFront.service.tools.PromptMsgTools;
import com.gome.memberFront.service.tools.ResultTools;
import com.gome.memberFront.web.controller.BaseController;
import com.gome.memberFront.web.controller.myFavorites.model.CategoryInfo;
import com.gome.memberFront.web.controller.myFavorites.model.GiftListItemModel;
import com.gome.memberFront.web.controller.myFavorites.model.MyFavoritesEnum;
import com.gome.memberFront.web.controller.myFavorites.model.MyFavoritesInfoModel;
import com.gome.memberFront.web.controller.myFavorites.model.MyFavoritesListModel;
import com.gome.memberFront.web.controller.myFavorites.model.MyFavoritesResultModel;
import com.gome.pangu.prepaid.card.client.PrepaidCardQueryServiceClient;
import com.gome.stage.bean.favoriteItem.FavoriteItemBean;
import com.gome.stage.bean.favoriteItem.ThirdCategory;
import com.gome.stage.interfaces.item.IPriceService;
import com.gome.stage.interfaces.item.IProductInfoService;
import com.gome.stage.interfaces.item.IShopService;
import com.gome.stage.interfaces.seo.favorite.FavoriteItemService;
import com.gome.stage.interfaces.whale.ICategoryDBService;
import com.gome.stage.item.PriceInfo;
import com.gome.stage.item.ShopItem;
import com.gome.stage.item.SkuItem;
import com.gome.userBase.facade.address.IUserAddressLinkFacade;
import com.gome.userBase.model.address.GomeMstDivision;


@Controller
@RequestMapping("myFavorites")
public class MyFavoritesGoodsController extends BaseController {

	/** 每页显示记录数默认15 **/
	private static final int DEFAUT_PAGESIZE = 15;

	/** 分类：全部  **/
//	private static final String CATEGORYNAME_ALL = "全部";

	/**降价商品**/
//	private static final String FILTER_PRICE_DOWN = "1";

	/**现货商品**/
	private static final String FILTER_SPOTS = "2";

	/**促销商品**/
//	private static final String FILTER_PROMOTION = "3";

	private static final int buid = 8270;

	/** 正常商品：N，赠送商品： G **/
	private static final String itemFlag = "N";

	/** 商品库存状态 N:无货，Y:有货  **/
	private static final String noGoods = "N";

	/** 商品的sku库存状态 1:上架，2：下架，6：归档，只做显示  **/
	private static final Integer offSell = 2;

	/** 商品的sku库存状态 1:上架，2：下架，6：归档，只做显示  **/
	private static final Integer filed = 6;

	/** 默认仓库编号  **/
	private static final String SUPPLY_REGION_ID = "99999999";

	/** 根据需求规定，在预热或预约阶段，如果gcc后台设置不显示价格，则页面需要提示敬请期待  **/
	private static final String noSale = "敬请期待";

	/** 没有收藏价格，也没有销售价格  **/
	private static final String noPrice = "暂无售价";

	/** 我的收藏夹-商品  **/
//	@Resource
//	private IMyFavoritesGoodsFacade myFavoritesGoodsFacade;

	/** 获取区域信息  **/
	@Resource
	private IUserAddressLinkFacade userAddressLinkFacade;

	/** 预约信息接口-memberCenter  **/
	@Resource
	private IReserveManagerFacade reserveManagerFacade;

	/** 前台接口：根据商品编号(批量)获取商品三级分类名称  **/
	@Resource
	private IProductInfoService productInfoService;

	/** 前台接口：根据分类编号获取商品三级分类名称  **/
	@Resource
	private ICategoryDBService categoryServiceWhale;

	/** 前台接口：根据商品sku编号(批量)获取商品sku信息  **/
	@Resource
	private IProdDetailService itemService;

	/** 获取商品价格、促销信息  **/
	/*@Resource  调用新的价格接口
	private IGomePriceService gomePriceService;*/

	/** 获取商品价格  **/
	@Resource
	private IPriceService priceService;

	/** 获取商品库存信息  **/
	@Resource
	private IAreaQueryService areaQueryService;
	
	/**商品收藏 新的接口信息**/
	@Resource
	private FavoriteItemService favoriteItemService;
	
	/** 获取商品的店铺信息  **/
	@Resource
	private IShopService shopService;

	/** 组织商品详情页的link **/
	@Resource(name = "productDetailUrl")
	private RetrieveProductDetailUrl productDetailUrl;

	/** 返回前端的提示信息  **/
	@Resource
	private PromptMsgTools promptMsgTools;
	
	/** 预付电子卡库存查询 **/
	@Resource
	private PrepaidCardQueryServiceClient prepaidCardQueryFacade;

	/** 防止重复提交  **/
	@Resource(name = "repeatingRequestFilter")
	private RepeatingRequestFilter repeatingRequestFilter;

	/** 默认4级区域编号  **/
	@Value("#{favoritesConfig[defaultDivisionCode]}")
	private String defaultDivisionCode;

	/**批量调用获取商品信息dubbo接口的(记录数:15)**/
	@Value("#{favoritesConfig[batchCallNum]}")
	private int batchCallNum;

	/**批量调用获取商品价格、促销信息dubbo接口的()**/
	@Value("#{favoritesConfig[priceCallNum]}")
	private int priceCallNum;

	/**批量调用库存信息dubbo接口的()**/
	@Value("#{favoritesConfig[stockCallNum]}")
	private int stockCallNum;

	/** 金象网店铺编号  **/
	@Value("#{favoritesConfig[kingShopNo]}")
	private String mKingShopNo;

	/**店铺首页**/
	@Value("#{favoritesConfig[coo8Site]}")
	private String coo8Site;

	/**是否打印日志**/
	@Value("#{favoritesConfig[isDisplayLog]}")
	private boolean isDisplayLog;

	/**判断是否收藏cookie名字**/
	@Value("#{favoritesConfig[proidCookieName]}")
	private String proidCookieName;

	/**
	 * 我的收藏夹-商品-分类信息
	 * @param categoryId 当前选中三级分类的编号
	 */
	@RequestMapping("getMyFavoritesGoodsCategory")
	@ResponseBody
	public Object getMyFavoritesGoodsCategory(HttpServletRequest request, String categoryId) {

		String callBackName = getCallBackName(request);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		MyFavoritesListModel favoritesList = null;
		String userId = null;
		try {
			// 1、检查用户是否登陆
			userId = getUserId(request);
			if (StringUtil.isEmpty(userId)) {
				resultMap = ResultTools.getErrorMap(101, "请登录，在操作");
				return getCallBackString(callBackName, resultMap);
			}

			logger.info("[userId=" + userId + "][我的收藏夹-商品]params==>categoryId=" + categoryId);

			// 2、获取收藏商品的分类信息
			favoritesList = getGoodsCategoryInfos(userId, categoryId);
			if (null == favoritesList) { // 用户未收藏任何商品，或是在获取收藏商品信息过程中出现异常(包括数据异常)
				favoritesList = new MyFavoritesListModel();
				logger.warn("[userId:" + userId + "]==>未查询到任何收藏信息，检查日志是否业务逻辑是否出现异常.");
			}
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("favoritesList", favoritesList);
			resultMap.put("result", result);

		} catch (Exception e) {
			logger.error("[userId:" + userId + "]==>获取收藏商品的分类信息时出现错误.error=", e);
			resultMap = ResultTools.getErrorMap(404, "加载我的收藏商品分类失败,请稍后再试!");
			return getCallBackString(callBackName, resultMap);
		} finally {
			if (isDisplayLog) {
				logger.info("[userId:" + userId + "]==>收藏商品的分类信息.[result=" + JsonSerializeUtil.serialize(favoritesList) + "]");
			}
		}
		return getCallBackString(callBackName, resultMap);
	}

	/**
	 * 我的国美-收藏的商品
	 * @MigrationSource 参考ATG 
	 * com.gome.userprofiling.dispatcher.adaptor.general.MyGomeDetailsAdaptor.getProfileFavorite
	 */
	@RequestMapping("getProfileFavorite")
	@ResponseBody
	public Object getProfileFavorite(HttpServletRequest request) {

		String callBackName = getCallBackName(request);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<MyFavoritesInfoModel> favoritesList = null;
		String userId = null;
		try {
			// 1、检查用户是否登陆
			userId = getUserId(request);
			if (StringUtil.isEmpty(userId)) {
				resultMap = ResultTools.getErrorMap(101, "请登录，在操作");
				return getCallBackString(callBackName, resultMap);
			}

			// 2、获取我的国美-收藏的商品信息
			favoritesList = getFavoritesList(userId);
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("pFavorites", favoritesList);
			resultMap.put("result", result);

		} catch (Exception e) {
			logger.error("[userId:" + userId + "]==>获取我的国美-收藏商品信息时出现错误.error=", e);
			resultMap = ResultTools.getErrorMap(404, "加载收藏商品信息失败,请稍后再试!");
			return getCallBackString(callBackName, resultMap);
		} finally {
			if (isDisplayLog) {
				logger.info("[userId:" + userId + "]==>我的国美-收藏商品信息.[result=" + JsonSerializeUtil.serialize(favoritesList) + "]");
			}
		}
		return getCallBackString(callBackName, resultMap);

	}

	/**
	 * 我的收藏夹-商品列表
	 * @param categoryId 	分类编号
	 * @param districtCode	城市区域编码(四级)
	 * @param filter		过滤器：(1：降价商品；2：现货商品；3：促销商品)
	 * @param currPageNum	当前页数
	 * @param pageSize		每页显示记录数
	 * @MigrationSource 参考ATG
	 *   MyFavoritesList com.gome.userprofiling.tools.MyFavorityProductTools.getMyFavoritesList
	 */
	@RequestMapping("getMyFavoritesGoods")
	@ResponseBody
	public Object getMyFavoritesGoods(HttpServletRequest request, String categoryId, String districtCode, String filter, String currPageNum, String pageSize) {

		String callBackName = getCallBackName(request);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		MyFavoritesListModel favoritesList = null;
		String userId = null;
		try {
			// 1、检查用户是否登陆
			userId = getUserId(request);
			if (StringUtil.isEmpty(userId)) {
				resultMap = ResultTools.getErrorMap(101, "请登录，在操作");
				return getCallBackString(callBackName, resultMap);
			}
			logger.info("[userId:" + userId + "][我的收藏夹-商品]params==>categoryId:" + categoryId + ";districtCode:" + districtCode + ";filter=" + filter + ";currPageNum=" + currPageNum + ";pageSize=" + pageSize);

			// 2、检查区域编码是否为空
			if (StringUtil.isEmpty(districtCode)) {
				logger.warn("[userId:" + userId + "]==>我的收藏夹-商品,districtCode is null");
			}

			int currPageNumInt = StringUtil.isEmpty(currPageNum) ? 1 : Integer.valueOf(currPageNum);
			int pageSizeInt = StringUtil.isEmpty(pageSize) ? DEFAUT_PAGESIZE : Integer.valueOf(pageSize);

			// 3、获取我的收藏夹-商品列表数据
			favoritesList = getFavoritesList(userId, categoryId, districtCode, filter, currPageNumInt, pageSizeInt, false);
			if (null == favoritesList) {
				favoritesList = new MyFavoritesListModel();
				Pagination<MyFavoritesInfoModel> pagination = new Pagination<MyFavoritesInfoModel>(0, 0, pageSizeInt);
				pagination.setList(new ArrayList<MyFavoritesInfoModel>());
				favoritesList.setPagination(pagination);
				logger.error("[userId:" + userId + "]==>未查询到任何收藏信息，检查日志是否业务逻辑是否出现异常.");
			}
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("favoritesList", favoritesList);
			resultMap.put("result", result);

		} catch (Exception e) {
			logger.error("[userId:" + userId + "]==>获取我的收藏夹-商品信息时出现错误.error=", e);
			resultMap = ResultTools.getErrorMap(404, "加载我的收藏商品失败,请稍后再试!");
			return getCallBackString(callBackName, resultMap);
		} finally {
			if (isDisplayLog) {
				logger.info("[userId:" + userId + "]==>我的收藏夹-商品.[result=" + JsonSerializeUtil.serialize(favoritesList) + "]");
			}
		}
		return getCallBackString(callBackName, resultMap);
	}

	/**
	 * 我的收藏夹-商品-取消收藏
	 * @param removeGomeGiftitemIds 商品收藏编号
	 * @param removeGomeProductIds	商品编号
	 * @param removeGomeSkuIds		商品sku编号
	 * 	取消多个商品时，使用","进行分隔
	 */
	@RequestMapping("cancelFavoritesGoods")
	@ResponseBody
	public Object cancelFavoritesGoods(HttpServletRequest request, String removeGomeGiftitemIds, String removeGomeProductIds, String removeGomeSkuIds) {

		String callBackName = getCallBackName(request);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		long callStart = 0;
		UserResult<Integer> operateResult = new UserResult<Integer>();
		String requestEntry = "MyFavoritesGoodsController.cancelFavoritesGoods";
		String userId = null;
		try {
			// 1、检查是否重复提交
			boolean bool = repeatingRequestFilter.isUniqueRequestEntry(requestEntry);
			if (!bool) {
				resultMap = ResultTools.getErrorMap(401, "不能重复提交");
				return getCallBackString(callBackName, resultMap);
			}

			// 2、检查用户是否登陆
			userId = getUserId(request);
			if (StringUtil.isEmpty(userId)) {
				resultMap = ResultTools.getErrorMap(101, "请登录，在操作");
				return getCallBackString(callBackName, resultMap);
			}
			
			//3、判断SkuId是否为空
			if (StringUtil.isEmpty(removeGomeSkuIds)) {
				logger.info("[cancelFavoritesGoods()]==>param removeGomeSkuIds>>>"+removeGomeSkuIds);
				resultMap = ResultTools.getErrorMap(MyFavoritesEnum.params_format_error.getCode(),MyFavoritesEnum.params_format_error.getMsg());
				return getCallBackString(callBackName, resultMap);
			}
			//4、判断SkuId是否超过15条限制
			String[] skuIds = removeGomeSkuIds.split(",");
			/** 最多一次取消收藏商品的个数  **/
			int MAX_CANCEL_COUNT = 15;
			if (skuIds.length > MAX_CANCEL_COUNT) {
				logger.info("[cancelFavoritesGoods()]==>error: " + MyFavoritesEnum.over_max_cancel.getMsg());
				resultMap = ResultTools.getErrorMap(MyFavoritesEnum.over_max_cancel.getCode(),MyFavoritesEnum.over_max_cancel.getMsg());
				return getCallBackString(callBackName, resultMap);
			}
			
			logger.info("[userId:" + userId + "][我的收藏夹-取消收藏]params==>removeGomeGiftitemIds=" + removeGomeGiftitemIds + ",removeGomeProductIds=" + removeGomeProductIds + ",removeGomeSkuIds=" + removeGomeSkuIds);
			
			callStart = System.currentTimeMillis();
			boolean flag = favoriteItemService.cancelfavorite(userId, skuIds);
			logger.info("[userId:" + userId + "][favoriteItemService.cancelfavorite(userId,skuIds)]==>总执行时间：" + (System.currentTimeMillis() - callStart) + "ms");
			
			
			if(flag){
				operateResult.setCodeAndMessage(MemberCenterEnum.success);
				operateResult.setSuccess(true);
			}else{
				//取消收藏失败
				operateResult.setCodeAndMessage(MyFavoritesEnum.cancel_goods_fail);
			}
//			operateResult = myFavoritesGoodsFacade.cancelFavoritesGoods(userId, removeGomeGiftitemIds, removeGomeProductIds, removeGomeSkuIds);
			if (operateResult == null || !operateResult.isSuccess()) {
				logger.error("[cancelFavoritesGoods()]==>[userId:" + userId + "].[result=" + JsonSerializeUtil.serialize(operateResult) + "]");
				resultMap = ResultTools.getErrorMap(operateResult.getCode(), operateResult.getMessage());
				return getCallBackString(callBackName, resultMap);
			}

			resultMap = ResultTools.getSuccMap(200, "取消收藏成功!");

		} catch (Exception e) {
			logger.error("[userId=" + userId + "]取消收藏时出现错误", e);
			resultMap = ResultTools.getErrorMap(405, "请退出后重新登陆");
			return getCallBackString(callBackName, resultMap);
		} finally {
			// 去掉登陆标志(防重复提交)
			repeatingRequestFilter.removeRequestEntry(requestEntry);
			logger.info("[userId:" + userId + "]==>取消收藏.[result=" + JsonSerializeUtil.serialize(operateResult) + "]");
		}
		return getCallBackString(callBackName, resultMap);

	}

	/**
	 * 我的国美-收藏的商品
	 * 获取用户收藏的商品信息，默认取前3条，根据收藏时间倒序
	 * @param userId 会员编号
	 * @throws Exception 
	 */
	private List<MyFavoritesInfoModel> getFavoritesList(String userId) throws Exception {

		long start = System.currentTimeMillis();
		long callStart = 0 ;
		long executeTime = 0;
//		Map<String, Object> skuInfos = new HashMap<String, Object>(); // 【缓存】记录商品的详细信息
//		Map<String, Object> shopInfos = new HashMap<String, Object>(); // 【缓存】记录商品的店铺信息
		Map<String, PriceInfo> priceInfos = new HashMap<String, PriceInfo>(); // 【缓存】记录商品的价格信息
		List<GiftListItemModel> giftlistItem = new ArrayList<GiftListItemModel>();

		int currPageNum = 1; // 第几页
		int pageSize = 3; // 每页数量(默认固定显示3条)
		int cycleTimes = 0; // 循环次数(一次获取3条，如果其中有无效数据(被过滤掉),会再次向后读取三条(收藏数量大于3条的情况))
		boolean exitLoop = false; // 是否继续循环向后读取数据
		while (giftlistItem.size() < pageSize) {
			try {
				// 1、从数据库中获取收藏的商品，每次取3条.
//				List<GiftListItemModel> nextBatch = myFavoritesGoodsFacade.getFirstFewGiftItem(userId, currPageNum, pageSize);
				int startNum = (currPageNum - 1) * pageSize + 1;
				callStart =System.currentTimeMillis();
				List<FavoriteItemBean>  favoriteList = favoriteItemService.pageQuery(userId, startNum, pageSize, "100", null);
				executeTime=System.currentTimeMillis()-callStart;
				logger.info("[userId:" + userId + "][favoriteItemService.pageQuery(userId,"+startNum+","+pageSize+")]==>总执行时间：" + executeTime + "ms");
//				Map<String, Object> nextSkuInfos = new HashMap<String, Object>(); // 【缓存】记录商品的详细信息
				
				if (favoriteList == null || favoriteList.size() < 1) { // 用户没有收藏任何商品，或是memberCenter的getFirstFewGiftItem服务出现异常，检查下日志
					break;
				} else if (favoriteList.size() < pageSize) { // 用户收藏的商品数量小于3，不需要再次循环取数据
					exitLoop = true;
				}
				//排除逻辑放到转换Model的地方做了
//				for (Iterator<FavoriteItemBean> it = favoriteList.iterator(); it.hasNext();) {
//					FavoriteItemBean item =  it.next();
//					// 检查数据库中的商品收藏数据是否记录三级分类Id(为null即为无效数据，不显示)
//					if (StringUtil.isEmpty(item.getThirdCatId())) {
//						it.remove();
//						logger.warn("[userId=" + userId + "]==>我的国美-收藏的商品.{" + JsonSerializeUtil.serialize(item) + "}未记录三级分类Id.");
//						continue;
//					}
//				}
//				for (Iterator<FavoriteItemBean> it = favoriteList.iterator(); it.hasNext();) {
//					FavoriteItemBean item =  it.next();
//					String productId = item.getProduct_id();
//					String skuId = item.getCatalog_ref_id();
//					SkuItem skuItem = (SkuItem) nextSkuInfos.get(productId + ":" + skuId);
//					// 检查此商品的skuId是否与商品productId关联
//					if (skuItem == null || StringUtil.isEmpty(skuItem.getSkuId()) || StringUtil.isEmpty(skuItem.getProductIds())) {
//						it.remove();
//						logger.warn("[userId=" + userId + "]我的国美-收藏商品==>获取收藏商品信息[productId=" + productId + ",skuId=" + skuId + "].调用前台getSkus()服务未找到对应的商品信息.");
//						continue;
//					}
//				}
				if (favoriteList != null && favoriteList.size() > 0) {
					//替换原有得逻辑，需要转换为新的对象。
					for (FavoriteItemBean favoriteItemBean : favoriteList) {
						//排除逻辑,之前会三级分类Id为空的排除，现在增加skuNo的也排除
						if(skuNoNotEmpty(favoriteItemBean.getGifitem_id()) && StringUtil.notEmpty(favoriteItemBean.getThirdCatId())){
							GiftListItemModel itemModel = new GiftListItemModel();
							convertModel(favoriteItemBean, itemModel);
							giftlistItem.add(itemModel);
						}else{
							logger.info("[userId=" + userId + "]排除skuNo为空或者ThirdCatId(三级分类Id)为空的数据(skuNo小于5)，[skuNo=" + favoriteItemBean.getGifitem_id()+"]  [skuId="+favoriteItemBean.getCatalog_ref_id()+"]"+"  [thirdCatId="+favoriteItemBean.getThirdCatId()+"]");
						}
					}
				}
				currPageNum++;
				cycleTimes++;
				if (exitLoop) {
					break;
				}
				if (cycleTimes >= 10) {
					logger.warn("[userId=" + userId + "]我的国美-收藏商品==>获取收藏商品信息已循环了【" + cycleTimes + "】次，检查数据是否正确或业务逻辑是否出现了死循环！");
					break;
				}

			} catch (Exception e) {
				logger.error("[userId=" + userId + "]我的国美-收藏商品==>获取收藏商品信息时出现错误.error==>", e);
				break;
			}
		}

		logger.info("[userId=" + userId + "]我的国美-收藏商品==>获取收藏商品信息循环了【" + cycleTimes + "】次.");

		List<MyFavoritesInfoModel> infoList = new ArrayList<MyFavoritesInfoModel>();
		if (giftlistItem.size() < 1) { // 未找到商品收藏数据
			executeTime = System.currentTimeMillis() - start;
			logger.info("[userId=" + userId + "]我的国美-收藏商品==>获取收藏商品信息，执行时间：" + executeTime + "ms");
			return infoList;
		}

		giftlistItem = giftlistItem.subList(0, giftlistItem.size() > 3 ? 3 : giftlistItem.size()); // 返回前端的收藏商品列表(默认显示3条)
		// 4、获取商品的店铺信息 
//		getGoodsShopInfo(userId, giftlistItem, shopInfos); //店铺信息可能去掉了

		// 5、获取商品的价格信息
		getGoodsPriceInfo(userId, null, giftlistItem, priceInfos);

		// 6、填充商品信息
		infoList = fillModel(userId, giftlistItem);
		executeTime = System.currentTimeMillis() - start;
		logger.info("[userId=" + userId + "]我的国美-收藏商品==>获取收藏商品信息，执行时间：" + executeTime + "ms");
		return infoList;
	}

	/**
	 * 我的国美-收藏的商品-填充数据信息
	 * @param userId 		会员编号
	 * @param giftlistItem 	收藏商品列表
	 * @param skuInfos 		商品的sku信息
	 * @param shopInfos 	商品的店铺信息
	 */
	private List<MyFavoritesInfoModel> fillModel(String userId, List<GiftListItemModel> giftlistItem) throws Exception {

		long start = System.currentTimeMillis();
		List<MyFavoritesInfoModel> infoList = new ArrayList<MyFavoritesInfoModel>();
		for (GiftListItemModel item : giftlistItem) {
			String productId = item.getProductId();
			String skuId = item.getCatalogRefId();
			String productType = item.getProductType();
					
//			if (skuItem == null || StringUtil.isEmpty(skuItem.getSkuId()) || StringUtil.isEmpty(skuItem.getProductIds())) {
//				logger.warn("[userId=" + userId + "]我的国美-收藏商品-填充数据==>[productId=" + productId + ",skuId=" + skuId + "].调用前台getSkus()服务未找到对应的商品信息.");
//				continue;
//			}
			
			MyFavoritesInfoModel favorites = new MyFavoritesInfoModel(); // 收藏商品对象
			/**金象网逻辑已删除
			String shopNo = skuItem.getShopNo(); // 店铺编号
			ShopItem shopItem = null;
			if (StringUtil.notEmpty(shopNo)) {
				shopItem = (ShopItem) shopInfos.get(shopNo);
			}
			if (shopItem != null) { // 店铺商品
				if (shopItem.getShopId().equalsIgnoreCase(mKingShopNo)) {
					favorites.setIsJinXiangProduct("true");
				} else {
					favorites.setIsJinXiangProduct("false");
				}
			} else {
				favorites.setIsJinXiangProduct("false");
			}*/
			favorites.setIsJinXiangProduct("false");
//			favorites.setProductType(skuItem.getProductType());
			favorites.setProductType(productType);
			
			
//			List<FSImgBase> skuImgs = skuItem.getSkuImgs();
//			if (skuImgs == null || skuImgs.isEmpty()) {
//				logger.warn("[userId:" + userId + "]我的国美-收藏商品-填充数据==>[productId=" + productId + ",skuId=" + skuId + "].调用前台getSkus()服务返回的商品图片地址为空");
//			} else {
//				skuImgUrl = skuImgs.get(0).getSrc();
//			}
			String skuImgUrl = item.getSkuImgUrl();
			/* 前台组不在维护sku中的价格信息，此处改为调用getAreaPrices
			 * Double gomePrice = 0d;
			boolean isOnSale = skuItem.getOnSale() == null || skuItem.getOnSale() == 0 ? false : true;
			if (isOnSale) {
				gomePrice = skuItem.getSalePrice(); // 直降价
			} else {
				gomePrice = skuItem.getListPrice(); // 国美价
			}*/

			long startReserve = System.currentTimeMillis();
			String reserveStatus = "";// 预约商品的状态
			Map<String, String> reserveBuyStatus = reserveManagerFacade.getReserveBuyStatus(productId, skuId, userId);
			String isReserveSku = reserveBuyStatus.get("isReserveSku"); // 是预约商品
			reserveStatus = reserveBuyStatus.get("reserveStatus"); // 商品的预约状态
			logger.info("[userId:" + userId + "]我的国美-收藏商品-填充数据==>获取预约信息信息，执行时间：" + (System.currentTimeMillis() - startReserve) + "ms");

			DecimalFormat df = new DecimalFormat("0.00");
			// gomePrice = (Double) (gomePrice != null ? gomePrice : 0.00D);
			favorites.setProductId(productId);
			favorites.setSkuId(skuId);
			favorites.setImageUrl(skuImgUrl);
			favorites.setDisplayName(item.getDisplayName());

			String productUrl = productDetailUrl.retrieveProductDetailUrl(productId, skuId, null);

			// 商品价格
			boolean isShowPrice = isShowPrice(productId, skuId);
			favorites.setProductUrl(productUrl);
			if (("01".equals(reserveStatus) || "02".equals(reserveStatus)) && !isShowPrice) {
				favorites.setSkuPrice(noSale);
			} else {
				if (null == item.getProductPrice()) {
					favorites.setSkuPrice(noPrice);
				} else {
					favorites.setSkuPrice(df.format(item.getProductPrice()));
				}
			}

			favorites.setIsReserveSku(isReserveSku);
			favorites.setReserveStatus(reserveStatus);

//			favorites.setShopFlag(skuItem.getShopFlag()); // 海外购标识
			favorites.setShopFlag(item.getHwgFlag());
			infoList.add(favorites);
		}

		long executeTime = System.currentTimeMillis() - start;
		logger.info("[userId:" + userId + "]我的国美-收藏商品-填充数据==>执行时间：" + executeTime + "ms");
		return infoList;
	}

	/**
	 * 获取收藏商品的三级分类信息
	 * @param userId 		会员编号
	 * @param categoryId 	当前选中三级分类的编号
	 */
	private MyFavoritesListModel getGoodsCategoryInfos(String userId, String categoryId) {
		MyFavoritesListModel favoritesList = new MyFavoritesListModel(); // 返回前端的收藏商品对象-只赋值分类对象
		long start = System.currentTimeMillis();
		long callStart = System.currentTimeMillis(); // 调用方法前的系统时间
		long executeTime = 0;
		List<CategoryInfo> categoryItem = null;
		

		// 1、获取用户收藏商品的分类信息
		try {
			callStart = System.currentTimeMillis();
//收藏新接口废弃			categoryItem = myFavoritesGoodsFacade.getProductCategoryInfo(userId); 
			
			//从新的接口获取第三方分类信息
			List<ThirdCategory> thirdCategoryList =  favoriteItemService.sumThirdQuery(userId);
			executeTime = System.currentTimeMillis() - callStart;
			logger.info("[userId:" + userId + "][favoriteItemService.sumThirdQuery(userId)]==>总执行时间：" + executeTime + "ms");
			if (null == thirdCategoryList || thirdCategoryList.size() == 0) {
				logger.warn("[userId:" + userId + "][getProductCategoryInfo()]==>未查询到任何收藏的分类信息.");
				return null;
			}
			//转换为CategoryInfo列表
			categoryItem =  new ArrayList<CategoryInfo>(thirdCategoryList.size());
			for (ThirdCategory item : thirdCategoryList) {
				String categoryName =item.getName();
				String thirdCatId = item.getId();
				if (StringUtil.isEmpty(categoryName)) { // 收藏商品的分类名称为空则不显示
					logger.warn("[userId:" + userId + "]==>三级分类编号[" + thirdCatId + "]没有取到对应的分类名称.");
					continue;
				} else {
					CategoryInfo category = new CategoryInfo();
					category.setThirdCatId(item.getId());
					category.setCategoryName(item.getName());
					category.setCategorySize((int)item.getTotal());
					if (categoryId != null && categoryId.equalsIgnoreCase(thirdCatId)) {
						category.setIsSelected("Y");
					} else {
						category.setIsSelected("N");
					}
					categoryItem.add(category);
				}
				
			}
			

		} catch (Exception e) {
			logger.error("[userId:" + userId + "][getProductCategoryInfo()]==>从数据库中读取分类信息时，出现错误.error==>", e);
			return null;
		} finally {
			logger.info("[userId:" + userId + "][getProductCategoryInfo()]==>从数据库中读取分类信息. 执行时间：" + executeTime + "ms");
			if (isDisplayLog) {
				logger.info("[userId:" + userId + "][getProductCategoryInfo()]==>从数据库中读取分类信息. result：" + JsonSerializeUtil.serialize(categoryItem));
			}
		}

		//注释掉去分类名称的逻辑，接口已经提供了
		/**收藏新接口废弃
		List<String> categoryIds = new ArrayList<String>();
		for (CategoryInfo item : categoryItem) {
			categoryIds.add(item.getThirdCatId());
		}

		// 2、调用前台提供接口获取收藏商品的三级分类名称
		Map<String, String> map = null;
		try {
			callStart = System.currentTimeMillis();
			map = categoryServiceWhale.findCategoryNameByCategoryIds(categoryIds);
			executeTime = System.currentTimeMillis() - callStart;
			if (map == null || map.size() == 0) {
				logger.warn("[userId:" + userId + "][findCategoryNameByCategoryIds()]==>获取三级分类名称，返回值为null. params:" + JsonSerializeUtil.serialize(categoryIds));
				return null;
			}
		} catch (Exception e) {
			logger.error("[userId:" + userId + "][findCategoryNameByCategoryIds()]==>获取三级分类名称时出错. params:{" + JsonSerializeUtil.serialize(categoryIds) + "}.error==>", e);
			return null;
		} finally {
			logger.info("[userId:" + userId + "][findCategoryNameByCategoryIds()]==>获取三级分类名称.执行时间：" + executeTime + "ms");
			if (isDisplayLog) {
				logger.info("[userId:" + userId + "][findCategoryNameByCategoryIds()]==>获取三级分类名称. result：" + JsonSerializeUtil.serialize(map));
			}
		}*/

		// 3、填充分类名称
//		int totalNumber = 0; // 收藏商品的总数量
//		for (Iterator<CategoryInfo> it = categoryItem.iterator(); it.hasNext();) {
//			CategoryInfo item = (CategoryInfo) it.next();
//			String thirdCatId = item.getThirdCatId();
//			String categoryName = map.get(thirdCatId);
//			String categoryName = item.getCategoryName();
//			if (StringUtil.isEmpty(categoryName)) { // 收藏商品的分类名称为空则不显示
//				logger.warn("[userId:" + userId + "]==>三级分类编号[" + thirdCatId + "]没有取到对应的分类名称.");
//				it.remove();
//				continue;
//			} else {
//				totalNumber += item.getCategorySize();
//				item.setCategoryName(categoryName);
//				if (categoryId != null && categoryId.equalsIgnoreCase(thirdCatId)) {
//					item.setIsSelected("Y");
//				} else {
//					item.setIsSelected("N");
//				}
//			}
//		}
//		CategoryInfo total = new CategoryInfo(CATEGORYNAME_ALL, totalNumber); // 全部
//		if (StringUtil.isEmpty(categoryId)) {
//			total.setIsSelected("Y");
//		}
//		categoryItem.add(0, total);
		if (StringUtil.isEmpty(categoryId)) {
			categoryItem.get(0).setIsSelected("Y");
		}
		favoritesList.setCategoryList(categoryItem);

		executeTime = System.currentTimeMillis() - start;
		logger.info("[userId:" + userId + "]==>获取用户收藏商品的三级分类信息，总执行时间：" + executeTime + "ms");
		return favoritesList;

	}

	/**
	 * 获取收藏商品列表信息【 根据【三级分类编号】、【显示状态】过滤】
	 * @param userId 			会员编号
	 * @param categoryId 		三级分类编号
	 * @param districtCode		城市区域编码
	 * @param filter			过滤器：(1：降价商品；2：现货商品；3：促销商品)
	 * @param currentPage		当前页数
	 * @param pageSize			每页显示记录数
	 * @param isShopCart 		是否是购物车调用(true:是,false:不是).购物车不显示三级分类信息
	 * @return 
	 * @throws Exception 
	 */
	private MyFavoritesListModel getFavoritesList(String userId, String categoryId, String districtCode, String filter, int currentPage, int pageSize, boolean isShopCart) throws Exception {

		long start = System.currentTimeMillis();
		List<GiftListItemModel> showGiftItem = new ArrayList<GiftListItemModel>(); // 记录分页后返回前端，用户收藏的商品列表

		Map<String, PriceInfo> priceInfos = new HashMap<String, PriceInfo>(); // 【缓存】记录商品的价格信息
		Map<String, String> productStocks = new HashMap<String, String>(); // 【缓存】记录商品的库存信息
		Map<String, Object> skuInfos = new HashMap<String, Object>(); // 【缓存】记录商品的详细信息
		Map<String, Object> shopInfos = new HashMap<String, Object>(); // 【缓存】记录商品的店铺信息
		Map<String, Object> catInfos = new HashMap<String, Object>(); // 【缓存】记录商品的分类信息

		MyFavoritesListModel favoritesList = new MyFavoritesListModel(); // 返回前端的收藏商品对象
		List<MyFavoritesInfoModel> myFavoritesList = new ArrayList<MyFavoritesInfoModel>(); // 返回前端的收藏的商品对象列表
		MyFavoritesResultModel resultModel = new MyFavoritesResultModel(); // 从数据库中读取的收藏商品数据
		Pagination<MyFavoritesInfoModel> pagination = null; // 返回前端的收藏的商品对象(包括分页信息)

		boolean isPagination = false; // 是否分页

		// 1、从数据库中获取指定用户收藏的商品列表
		long callStart = System.currentTimeMillis(); // 从数据库中读取数据的执行开始时间
		// 如果过滤器[降价商品、现货商品、促销商品]为空时，只取当前页面默认显示的记录数(分页)，否则需要取出全部数据(当前用户)过滤后再分页
		if (StringUtil.isEmpty(filter)) {
			isPagination = true;
		}
		List<FavoriteItemBean>  newFavoriteList = null;
		//判断是否分页决定分页参数
		//正常流程走原有的分页流程
		boolean priceIsGot = false;
		boolean stockIsGot = false;
		if (isPagination) { //分页的时候调用分页方法，后面填充分页信息
			int startRow = (currentPage - 1) * pageSize + 1;
//			resultModel = myFavoritesGoodsFacade.getGiftListItemsByCatId(userId, categoryId, currentPage, pageSize, isPagination);
			//try_catch外层已经加上
			
			//查询总条数
			callStart = System.currentTimeMillis();
			int total = favoriteItemService.numToTals(userId, StringUtil.isEmpty(categoryId)?"":categoryId);
			logger.info("[userId=" + userId + "][favoriteItemService.numToTals(userId,"+categoryId+")]==>查询总收藏条数:"+total);
			logger.info("[userId=" + userId + "][favoriteItemService.numToTals(userId)]==>查询总收藏条数，执行时间：" + (System.currentTimeMillis() - callStart) + "ms.");
			if (total < 1) {
				logger.info("[userId=" + userId + "]==>查询总条数没收藏任何商品.");
				pagination = new Pagination<MyFavoritesInfoModel>(0, 0, pageSize);
				pagination.setList(myFavoritesList);
				favoritesList.setPagination(pagination);
				return favoritesList;
			}
			newFavoriteList = favoriteItemService.pageQuery(userId, startRow, pageSize, "100", StringUtil.isEmpty(categoryId)?"":categoryId);
			logger.info("[userId=" + userId + "][favoriteItemService.pageQuery(userId,"+startRow+","+pageSize+","+(StringUtil.isEmpty(categoryId)?"null":categoryId)+")]==>调用dubbo读取收藏数据，执行时间：" + (System.currentTimeMillis() - callStart) + "ms.");

			if (newFavoriteList == null || newFavoriteList.size() == 0) {
				
				logger.info("[userId=" + userId + "]==>没有收藏任何商品.");
				pagination = new Pagination<MyFavoritesInfoModel>(0, 0, pageSize);
				pagination.setList(myFavoritesList);
				favoritesList.setPagination(pagination);
				return favoritesList;
			}
			List<GiftListItemModel>  giftlistItem = new ArrayList<GiftListItemModel>();
			//转换为原有的对象
			for (FavoriteItemBean favoriteItemBean : newFavoriteList) {
				//接口定义Gifitem_id为skuNo,catalog_ref_id为skuId
				if(skuNoNotEmpty(favoriteItemBean.getGifitem_id())){
					GiftListItemModel itemModel = new GiftListItemModel();
					convertModel(favoriteItemBean, itemModel);
					giftlistItem.add(itemModel);
				}else{
					logger.info("[userId=" + userId + "]排除skuNo为空的数据(skuNo长度小于5也会排除)，[skuNo==>" + favoriteItemBean.getGifitem_id()+"][skuId==>"+favoriteItemBean.getCatalog_ref_id()+"]");
				}
				
			}
			//根据原有逻辑设置值
			resultModel.setCurrPageNum(currentPage);
			resultModel.setFavoritesList(giftlistItem);
			
			//设置总页数和总条数等信息
			int pages = (int) (total / pageSize + ((total % pageSize == 0) ? 0 : 1));
			resultModel.setTotalPageNum(pages);
			resultModel.setTotalRecord(total);
			
			/**
			//设置总页数和总条数等信息
			List<ThirdCategory> thirdCategoryList =  favoriteItemService.sumThirdQuery(userId);
			
			logger.info("[userId=" + userId + "][favoriteItemService.sumThirdQuery(userId)]==>调用dubbo读取收藏数据，执行时间：" + (System.currentTimeMillis() - callStart) + "ms.");
			
			ThirdCategory cureentCategory = null;
			//查找本次分类所选的分类
			if(StringUtil.isEmpty(categoryId)){//全部分类直接那第一条总数
				cureentCategory = thirdCategoryList.get(0);
			}else {//非全部分类就去列表中查找当前分类
				for (ThirdCategory category : thirdCategoryList) {
					if(categoryId.equals(category.getId())){
						cureentCategory = category;
						break;
					}
				}
			}
			if(cureentCategory != null){
				int total = (int)cureentCategory.getTotal();
				int pages = (int) (total / pageSize + ((total % pageSize == 0) ? 0 : 1));
				resultModel.setTotalPageNum(pages);
				resultModel.setTotalRecord(total);
			}else{
				logger.warn("[userId=" + userId + "][favoriteItemService.sumThirdQuery()]==>获得的分类信息不包含["+categoryId+"]");
			}
			*/
//			// 2、获取收藏商品的sku信息
//			getGoodsSkuInfo(userId, giftlistItem, skuInfos);

			// 3、检查返回的商品信息是否正确.如sku信息或skuId或productIds不存在则从收藏商品列表中移除，不在页面展示.
//			for (Iterator<GiftListItemModel> it = giftlistItem.iterator(); it.hasNext();) {
//				GiftListItemModel item = (GiftListItemModel) it.next();
//				String productId = item.getProductId();
//				String skuId = item.getCatalogRefId();
//				SkuItem skuItem = (SkuItem) skuInfos.get(productId + ":" + skuId);
//				if (skuItem == null || StringUtil.isEmpty(skuItem.getSkuId()) || StringUtil.isEmpty(skuItem.getProductIds())) {
//					logger.warn("[userId=" + userId + "][productId=" + productId + ",skuId=" + skuId + "]==>调用前台getSkus()服务未找到对应的商品信息.");
//					it.remove();
//				}
//			}

			// 4、判断是否需要提前获取价格(降价商品)、库存(现货商品)、促销(促销商品--目前逻辑已排除促销)信息用于过滤
//			boolean priceIsGot = false;
//			boolean stockIsGot = false;
//			if (StringUtil.notEmpty(filter)) { //为空的时候不穿
//				String[] filters = filter.split(",");
//				for (String filterTemp : filters) {
//					if (FILTER_PRICE_DOWN.equals(filterTemp)) { // 降价商品
//																// (需要获取商品价格与收藏时的价格比较,判断是否显示)
//						priceIsGot = true;
//						getGoodsPriceInfo(userId, districtCode, giftlistItem, priceInfos);
//
//					} else if (FILTER_PROMOTION.equals(filterTemp)) { // 促销商品
//						// 目前逻辑已排除促销
//
//					} else if (FILTER_SPOTS.equals(filterTemp)) { // 现货商品(需要获取商品库存,判断是否显示)
//						stockIsGot = true;
//						getGoodsStockInfo(userId, districtCode, giftlistItem, productStocks);
//					}
//				}
//			}

			// 5、根据过滤器[1：降价商品，2：现货商品，3：促销商品]过滤用户收藏的商品
//			filterByStatus(userId, giftlistItem, productStocks, priceInfos, filter);
			if (giftlistItem == null || giftlistItem.size() < 1) {
				logger.info("[userId:" + userId + "][categoryId =" + categoryId + "][filter=" + filter + "]==>没有收藏此类商品.");
				pagination = new Pagination<MyFavoritesInfoModel>(0, 0, pageSize);
				pagination.setList(myFavoritesList);
				favoritesList.setPagination(pagination);
				return favoritesList;
			}

			// 6 、分页
			// 已分页.resultModel.getTotalRecord() 为用户收藏的商品总数
			pagination = new Pagination<MyFavoritesInfoModel>(currentPage, resultModel.getTotalRecord(), pageSize);
			showGiftItem = giftlistItem;
		} else {
			// 不分页时，取当前用户所有收藏商品,处理分页逻辑。
			//map格式：第一个参数是skuid,第二个参数是skuno
			callStart = System.currentTimeMillis();
			Map<String, String> map =  favoriteItemService.allfavorite(userId, StringUtil.isEmpty(categoryId)?"":categoryId);
			logger.info("[userId=" + userId + "][favoriteItemService.allfavorite(userId,"+categoryId+")]==>调用dubbo读取收藏分类数据，执行时间：" + (System.currentTimeMillis() - callStart) + "ms.");
			
			if(map == null || map.size() < 1){
				logger.info("[userId=" + userId + "]==>没有收藏任何商品.");
				pagination = new Pagination<MyFavoritesInfoModel>(0, 0, pageSize);
				pagination.setList(myFavoritesList);
				favoritesList.setPagination(pagination);
			    return favoritesList;
			}

			
			// 转换为list防止操作后顺序变乱
			List<SkuItem> list = new ArrayList<SkuItem>();
			for (String key : map.keySet()) {
				String skuId = key;
				String skuNo = map.get(key).toString();
				//map数据值为skuNo如果为null表示为垃圾数据
				
				if(skuNoNotEmpty(skuNo)){
					SkuItem skuItem = new SkuItem();
					skuItem.setSkuId(skuId);
					skuItem.setSkuNo(skuNo);
					list.add(skuItem);
				}
				

			}
			// 调用新方法填充skuInfos，此次读取只为排除 非现货信息使用，使用完毕会按照原来的方式重新获取。
			
			getGoodsSkuInfoNew(userId, list, skuInfos);
			// 4、判断是否需要提前获取价格(降价商品)、库存(现货商品)、促销(促销商品--目前逻辑已排除促销)信息用于过滤
			String[] filters = filter.split(",");
//			Map<String, Integer> clearMap =  new HashMap<String, Integer>();//存放经过排查是无货的商品
			for (String filterTemp : filters) {
//				if (FILTER_PRICE_DOWN.equals(filterTemp)) { // 降价商品
//					// (需要获取商品价格与收藏时的价格比较,判断是否显示)
//					priceIsGot = true;
//					// getGoodsPriceInfo(userId, districtCode, giftlistItem,
//					// priceInfos);
//
//				} else if (FILTER_PROMOTION.equals(filterTemp)) { // 促销商品
//					// 目前逻辑已排除促销
//
//				} else 
				//需要从GCC获取库存的SkuNos
				List<String> prepaidSkuNos = new ArrayList<String>();
				if (FILTER_SPOTS.equals(filterTemp)) { // 现货商品(需要获取商品库存,判断是否显示)
					stockIsGot = true;
					AreaAtpRequestDto requestDto = new AreaAtpRequestDto();
					String daId = getStockId(districtCode);
					requestDto.setDaId(daId);
					requestDto.setBuid(buid);
					int size = list.size();
					for (int i = 0, requestCount = 0; i < size; i++) {
						SkuItem item = list.get(i);
						SkuItem skuItem = (SkuItem) skuInfos.get(item.getSkuNo());
						if(skuItem == null){
							logger.info("[userId=" + userId + "]==>过滤现货商品时获取SkuItem信息失败![skuId="+skuItem.getSkuId()+"][skuNo="+skuItem.getSkuNo()+"]");
							continue;
						}
						if (!"ZDZK".equals(skuItem.getSapSkuType())) {
							// 组装数据
							AreaAtpRequestDetailDto detail = new AreaAtpRequestDetailDto();
							detail.setPartNum(item.getSkuNo());
							detail.setItemFlag(itemFlag);
							detail.setQty(1); // 想要的数量
							requestDto.getDetails().add(detail);
							requestCount++;
						} else {
							prepaidSkuNos.add(item.getSkuNo());
						}
	
						if ((requestCount + 1) % stockCallNum == 0 || (i == size - 1)) {
							getProductStock(userId, productStocks, requestDto);
							
//							List<AreaAtpResponseDetailDto> details = areaQueryService
//									.getAreaAtpStatus(requestDto);
//							/** 无货：N, 有货： Y **/
//							for (AreaAtpResponseDetailDto detail : details) {
//								// partNum即skuNo为key值
//								if (noGoods.equals(detail.getStatus())) {
//									// 讲无货状态的放在将要排除的map中
//									clearMap.put(detail.getPartNum(), 1);
//								}
//							}
							
							requestDto.getDetails().clear();
						}
					}
					//美通卡信息过滤
					if(prepaidSkuNos != null){
						getProductStockFromGCC(userId,productStocks,prepaidSkuNos);
						prepaidSkuNos.clear();
//						if(productStocks.size()>0){
//							for (String skuNo : productStocks.keySet()) {
//								String status = productStocks.get(skuNo);
//								if (noGoods.equals(status)) {
//									clearMap.put(skuNo, 1);
//								}
//							}
//						}
						
					}
				}
			}
			//如果排除map有要无货的商品 就从列表删除
			if(productStocks!=null && productStocks.size()>0){
				for (Iterator<SkuItem> it = list.iterator(); it.hasNext();) {
					SkuItem item =  it.next();
					String  skuNo = item.getSkuNo();
					// filter 过滤商品：仅显示[现货商品]
					boolean isFiltered = noGoods.equalsIgnoreCase(productStocks.get(skuNo));
					if (isFiltered) {
						it.remove();
					}
				}
			}
			//排除完毕分页
			pagination = new Pagination<MyFavoritesInfoModel>(currentPage, list.size(), pageSize);
			List<SkuItem> showList = list.subList(pagination.getStartNum(), pagination.getEndNum());
			List<String> paramList = new ArrayList<String>();
			for (SkuItem skuItem : showList) {
				paramList.add(skuItem.getSkuNo());
			}
			//查询本页数据
			callStart = System.currentTimeMillis();
			newFavoriteList = favoriteItemService.allfavoriteItems(userId, paramList.toArray(new String[paramList.size()]), "100");
			logger.info("[userId=" + userId + "][favoriteItemService.allfavoriteItems()]==>从数据库中读取收藏数据，执行时间：" + (System.currentTimeMillis() - callStart) + "ms.");
			//转换为原有的对象
			for (FavoriteItemBean favoriteItemBean : newFavoriteList) {
				GiftListItemModel itemModel = new GiftListItemModel();
				convertModel(favoriteItemBean, itemModel);
				showGiftItem.add(itemModel);
			}

		}
		// 7、获取商品的分类信息
		if (!isShopCart) {
			getCatInfo(showGiftItem, catInfos);
		}

		// 8、获取收藏商品的店铺信息
		getGoodsShopInfo(userId, showGiftItem, shopInfos);

		// 9、获取收藏商品的价格信息
		if (!priceIsGot) {
			getGoodsPriceInfo(userId, districtCode, showGiftItem, priceInfos);
		}

		// 10、获取收藏商品的库存信息
		if (!stockIsGot) {
			getGoodsStockInfo(userId, districtCode, showGiftItem, productStocks);
		}

		// 11、填充数据
		fillData(showGiftItem, skuInfos, productStocks, userId, myFavoritesList, catInfos);
		pagination.setList(myFavoritesList);
		favoritesList.setPagination(pagination);

		long executeTime = System.currentTimeMillis() - start;
		logger.info("[userId:" + userId + "]获取收藏商品列表==>执行时间：" + executeTime + "ms.数量：" + resultModel.getTotalRecord());
		return favoritesList;
		
		
	}
	/**
	 * 
	 * Description:判断skuNo是否为空 . <br/>
	 * @param skuNo
	 * @return
	 */
	private boolean skuNoNotEmpty(String skuNo){
		return StringUtil.notEmpty(skuNo) && skuNo.length()>5;
	}
	/**
	 * 
	 * Description: 调用新接口获取Sku信息. <br/>
	 * @param userId
	 * @param giftlistItem
	 * @param skuInfos
	 * @throws Exception
	 
	private void getGoodsSkuInfo(String userId, List<GiftListItemModel> giftlistItem, Map<String, Object> skuInfos) throws Exception {
		try {
			long start = System.currentTimeMillis();
			List<String> skuList = new ArrayList<String>(); // 记录productId:skudId，用于批量获取商品详细信息
			// 获取商品的sku信息
			int size = giftlistItem.size(); // 收藏商品数量
			int count = 0; // 调用前台获取商品信息服务次数(getSkus)
			for (int i = 0; i < size; i++) {
				GiftListItemModel item = giftlistItem.get(i);
				// 组装数据--sku 参数格式==>productId:skudId
				skuList.add(item.getProductId() + ":" + item.getCatalogRefId());
				if ((i + 1) % batchCallNum == 0 || (i == size - 1)) {
					count++;
					if (isDisplayLog) {
						logger.info("[userId=" + userId + "][getSkus()]==>获取收藏商品的SKU信息, 第[" + count + "]次调用.数量[count=" + skuList.size() + "].");
					}
					getSkuItem(userId, skuList, skuInfos);
					skuList.clear();
				}
			}
			long executeTime = System.currentTimeMillis() - start;
			logger.info("[userId:" + userId + "][getSkus()]==>获取收藏商品的SKU信息，商品数量[size=" + size + "].批量个数[batchCallNum=" + batchCallNum + "].执行次数[count=" + count + "].执行总时间：" + executeTime + "ms");

		} catch (Exception e) {
			logger.error("[userId=" + userId + "]==>获取收藏商品的SKU信息时，出现错误.error==>", e);
			throw new Exception(e);
		}
	}
	*/

	/**
	 * 获取收藏商品的sku信息，并缓存在skuInfos中
	 * @param userId 		会员Id
	 * @param giftlistItem 	收藏商品的列表
	 * @param skuInfos 		记录商品的Sku的详细信息
	 * @throws Exception 
	 */
	private void getGoodsSkuInfoNew(String userId, List<SkuItem> list, Map<String, Object> skuInfos) throws Exception {
		try {
			long start = System.currentTimeMillis();
			List<String> skuList = new ArrayList<String>(); // 记录productId:skudId，用于批量获取商品详细信息
			// 获取商品的sku信息
			int size = list.size(); // 收藏商品数量
			int count = 0; // 调用前台获取商品信息服务次数(getSkus)
			for (int i = 0 ; i < size ; i++) {
				SkuItem skuItem = list.get(i);
				// 组装数据--sku 参数格式skudId
				skuList.add(skuItem.getSkuId());
				if ((i + 1) % batchCallNum == 0 || (i == size - 1)) {
					count++;
					if (isDisplayLog) {
						logger.info("[userId=" + userId + "][getSkus()]==>获取收藏商品的SKU信息, 第[" + count + "]次调用.数量[count=" + skuList.size() + "].");
					}
					getSkuItemNew(userId, skuList, skuInfos);
				}
			}
			long executeTime = System.currentTimeMillis() - start;
			logger.info("[userId:" + userId + "][getSkus()]==>获取收藏商品的SKU信息，商品数量[size=" + size + "].批量个数[batchCallNum=" + batchCallNum + "].执行次数[count=" + count + "].执行总时间：" + executeTime + "ms");

		} catch (Exception e) {
			logger.error("[userId=" + userId + "]==>获取收藏商品的SKU信息时，出现错误.error==>", e);
			throw new Exception(e);
		}
	}

	/**
	 * 获取收藏商品的价格信息
	 * @param userId 		会员Id
	 * @param districtCode	城市区域编码(四级)
	 * @param giftlistItem 	收藏商品的列表
	 * @param priceInfos 	记录商品的价格信息
	 * @throws Exception 
	 */
	private void getGoodsPriceInfo(String userId, String districtCode, List<GiftListItemModel> giftlistItem, Map<String, PriceInfo> priceInfos) throws Exception {
		try {
			long start = System.currentTimeMillis();
			String areaCode = null;
			if (StringUtil.notEmpty(districtCode)) { // 我的收藏夹调用.我的国美--收藏的收藏只显示()
				GomeMstDivision twoLevelArea = getDivisionItem(districtCode, 2); // 获取二级区域code
				if (twoLevelArea == null || StringUtil.isEmpty(twoLevelArea.getDivisionCode())) {
					logger.error("[userId:" + userId + "][districtCode=" + districtCode + "][getDivisionItem]未找到2级区域code");
					return;
				}
				areaCode = twoLevelArea.getDivisionCode();
				logger.info("[userId=" + userId + "][districtCode=" + districtCode + "]==>获取二级区域code，返回值[areaCode=" + areaCode + "]");
			} else {
				logger.info("[userId=" + userId + "][districtCode=" + districtCode + "]==>我的国美-收藏商品，此时区域为空.");
			}

			List<String> priceList = new ArrayList<String>(); // 记录skuId用于批量获取商品价格信息
			int size = giftlistItem.size(); // 收藏商品数量
			int count = 0;
			for (int i = 0; i < size; i++) {
				GiftListItemModel item = giftlistItem.get(i);
				// 组装数据
				priceList.add(item.getCatalogRefId());

				if ((i + 1) % priceCallNum == 0 || (i == size - 1)) {
					count++;
					if (isDisplayLog) {
						logger.info("[userId=" + userId + "][getPriceInfoList()]==>获取收藏商品的价格信息, 第[" + count + "]次调用.数量[count=" + priceList.size() + "].");
					}
					batchFetchDataFromDubbo(userId, areaCode, priceList, priceInfos);
					priceList.clear();
				}
			}
			long executeTime = System.currentTimeMillis() - start;
			logger.info("[userId=" + userId + "]==>获取收藏商品的价格信息，商品数量[size=" + size + "].批量个数[priceCallNum=" + priceCallNum + "].执行次数[count=" + count + "].执行总时间：" + executeTime + "ms");

			// 填充价格信息、是否降价标志
			for (int i = 0; i < size; i++) {
				GiftListItemModel item = giftlistItem.get(i);

				String productId = item.getProductId();
				String skuId = item.getCatalogRefId();
				PriceInfo priceInfo = priceInfos.get(skuId);
				Double productPrice = null; // 收藏商品现在的价格(与产品详情页相同)
				// 价格优先级:areaPrice(区域售价)>originalAreaPrice(区域原价)>price(售价)>originalPrice(原价)
				if (priceInfo != null) {
					if (null != priceInfo.getAreaPrice()) { // 区域售价
						productPrice = priceInfo.getAreaPrice();

					} else if (null != priceInfo.getOriginalAreaPrice()) { // 区域原价
						productPrice = priceInfo.getOriginalAreaPrice();

					} else if (null != priceInfo.getPrice()) { // 售价
						productPrice = priceInfo.getPrice();

					} else if (null != priceInfo.getOriginalPrice()) { // 原价
						productPrice = priceInfo.getOriginalPrice();
					}
				}
				Double skuPrice = (Double) item.getFavoritesPrice(); // 商品收藏时的价格
				String priceDown = null; // 获得降价的值(对比收藏时)
				if (skuPrice == null) {
					logger.warn("[userId=" + userId + "][productId=" + productId + ",skuId=" + skuId + "]==>商品收藏时，存储的价格是null.");
					// skuPrice = 10000000.00;
					priceDown = null;
				} else if (productPrice == null) {
					logger.warn("[userId=" + userId + "][productId=" + productId + ",skuId=" + skuId + "]==>调用价格的dubbo服务，获取的价格为null");
					productPrice = skuPrice;
					priceDown = null;
				} else {
					priceDown = getProductDownPrice(productPrice, skuPrice);
				}

				item.setPriceDown(priceDown);
				item.setProductPrice(productPrice);
			}

		} catch (Exception e) {
			logger.error("[userId=" + userId + "]==>获取收藏商品的价格信息，出现错误.error==>", e);
			throw new Exception(e);
		}
	}

	/**
	 * 获取收藏商品的库存信息
	 * @param userId 		会员Id
	 * @param districtCode	城市区域编码(四级)
	 * @param giftlistItem 	收藏商品的列表
	 * @param skuInfos 		记录商品的Sku的详细信息
	 * @param productStocks 记录商品的库存信息
	 * @throws Exception 
	 */
	private void getGoodsStockInfo(String userId, String districtCode, List<GiftListItemModel> giftlistItem, Map<String, String> productStocks) throws Exception {
		try {
			long start = System.currentTimeMillis();

			// 获取商品的sku信息
			int size = giftlistItem.size(); // 收藏商品数量
			int count = 0; // 调用dragon获取商品库存信息服务次数(getAreaAtpStatus)
			AreaAtpRequestDto requestDto = new AreaAtpRequestDto();
			String daId = getStockId(districtCode);
			requestDto.setDaId(daId);
			requestDto.setBuid(buid);
			
//			int areaAtpCount = 0; //从dragon获取库存信息sku计数
			////崔金鹏的代码
			//需要从GCC获取库存的SkuNos
			List<String> prepaidSkuNos = new ArrayList<String>();//存放请求gcc 接口的 skuNo
			//requestCount 为之前的count因为多了美瞳卡就不可以用I来计数去取前端库存信息
			for (int i = 0,requestCount=0; i < size; i++) {

				GiftListItemModel item = giftlistItem.get(i);
//				String productId = item.getProductId();
//				String skuId = item.getCatalogRefId();
				
				String skuNo = item.getGiftitemId();
				
//				SkuItem sku = (SkuItem) skuInfos.get(productId + ":" + skuId);
				
//				if (StringUtil.isEmpty(sku.getSkuNo())) {
//					logger.warn("[userId=" + userId + "]获取收藏商品的库存信息==>[productId=" + productId + ",skuId=" + skuId + "].调用前台getSkus().返回值skuNo is null.");
//					continue;
//				}

				//崔金鹏的代码
				//ZDZK 美通电子卡 之前为 sku.getSapSkuType()
				if(!"ZDZK".equals(item.getSapSkuType())){
//					// 组装数据
//					AreaAtpRequestDetailDto detail = new AreaAtpRequestDetailDto();
//					detail.setPartNum(sku.getSkuNo());
//					detail.setItemFlag(itemFlag);
//					detail.setQty(1); // 想要的数量
//					requestDto.getDetails().add(detail);
					
					// 组装数据
					AreaAtpRequestDetailDto detail = new AreaAtpRequestDetailDto();
					detail.setPartNum(item.getGiftitemId());
					detail.setItemFlag(itemFlag);
					detail.setQty(1); // 想要的数量
					requestDto.getDetails().add(detail);
					requestCount++;
				}else{
//					prepaidSkuNos.add(sku.getSkuNo());
					prepaidSkuNos.add(skuNo);
				}
//				if(requestDto.getDetails() != null){
//					areaAtpCount = requestDto.getDetails().size();
//				}
	
				//if ( (areaAtpCount + 1) % stockCallNum == 0 || (i == size - 1)) {
				if ((requestCount + 1) % stockCallNum == 0 || (i == size - 1)) {
					count++;
					if (isDisplayLog) {
						logger.info("[userId=" + userId + "][getAreaAtpStatus()]==>获取收藏商品的库存信息, 第[" + count + "]次调用.数量[count=" + requestDto.getDetails().size() + "]. ");
					}
					getProductStock(userId, productStocks, requestDto);
					requestDto.getDetails().clear();
				}
			}
			if (requestDto != null && requestDto.getDetails() != null && requestDto.getDetails().size() > 0) {
				getProductStock(userId, productStocks, requestDto);
				requestDto.getDetails().clear();
			}

			//崔金鹏的代码
			if(prepaidSkuNos != null){
				getProductStockFromGCC(userId,productStocks,prepaidSkuNos);
				prepaidSkuNos.clear();
			}
			long executeTime = System.currentTimeMillis() - start;
			logger.info("[userId=" + userId + "]==>获取收藏商品的库存信息，商品数量[size=" + size + "].批量个数[stockCallNum=" + stockCallNum + "].执行次数[count=" + count + "].执行总时间：" + executeTime + "ms");
		} catch (Exception e) {
			logger.error("[userId=" + userId + "]==>获取收藏商品的库存信息时出现错误.error==>", e);
			throw new Exception(e);
		}
	}

	/**
	 * 获取收藏商品的店铺信息
	 * @param userId 		会员Id
	 * @param giftlistItem 	收藏商品的列表
	 * @param skuInfos 		记录商品的Sku的详细信息
	 * @param shopInfos 	记录商品的店铺信息
	 * @throws Exception 
	 */
	private void getGoodsShopInfo(String userId, List<GiftListItemModel> giftlistItem, Map<String, Object> shopInfos) throws Exception {

		List<String> merchantIds = new ArrayList<String>();
		try {

			long start = System.currentTimeMillis();

			int size = giftlistItem.size(); // 收藏商品数量
			for (int i = 0; i < size; i++) {
				GiftListItemModel item = giftlistItem.get(i);
//				SkuItem sku = (SkuItem) skuInfos.get(item.getProductId() + ":" + item.getCatalogRefId());
				
//				if (StringUtil.isEmpty(sku.getShopNo())) {
//					continue;
//				}
				if (StringUtil.isEmpty(item.getShopId())) {
					continue;
				}
				merchantIds.add(item.getShopId());
			}
			if (merchantIds.size() > 0) {
				getShopItem(userId, merchantIds, shopInfos);
			}

			long executeTime = System.currentTimeMillis() - start;
			logger.info("[userId=" + userId + "]==>获取收藏商品的店铺信息，执行总时间：" + executeTime + "ms");
		} catch (Exception e) {
			logger.error("[userId=" + userId + "]==>获取收藏商品的店铺信息. params: " + JsonSerializeUtil.serialize(merchantIds));
			logger.error("[userId=" + userId + "]==>获取收藏商品的店铺信息时，出现错误.error==> ", e);
			throw new Exception(e);
		}
	}

	/**
	 * 根据4级区域编号获取仓库Id
	 * 仓库Id在3级区域下
	 * @param districtCode	城市区域编码(四级) 
	 * @throws Exception 
	 */
	private String getStockId(String pDivisionCode) throws Exception {

		try {
			long start = System.currentTimeMillis();
			String stockId = null;
			// 区域等级默认为3
			GomeMstDivision divisionItem = getDivisionItem(pDivisionCode, 3);
			if (divisionItem != null) {
				stockId = divisionItem.getDaId();
				logger.info("根据区域编号获取仓库Id==>[pDivisionCode=" + pDivisionCode + "].返回值:[stockId=" + stockId + "]");
				return stockId;
			}
			GomeMstDivision defaultDivisionItem = getDivisionItem(defaultDivisionCode, 3);

			if (defaultDivisionItem != null && defaultDivisionItem.getDivLevel().intValue() == 3) {
				stockId = defaultDivisionItem.getDaId();
				logger.warn("根据区域编号获取仓库Id==>[pDivisionCode=" + pDivisionCode + "].未找到仓库编号,使用默认区域编号[defaultDivisionCode=" + defaultDivisionCode + "].返回值:[stockId=" + stockId + "]");
				return stockId;
			}

			long executeTime = System.currentTimeMillis() - start;
			logger.info("根据区域编号获取仓库Id==>执行时间：" + executeTime + "ms");
			logger.warn("根据区域编号获取仓库Id==>[pDivisionCode=" + pDivisionCode + "].默认区域编号[defaultDivisionCode=" + defaultDivisionCode + "]均为找到对应的仓库编号 .使用默认仓库编号:[" + SUPPLY_REGION_ID + "]");
			return SUPPLY_REGION_ID;
		} catch (Exception e) {
			logger.error("根据4级区域编号[" + pDivisionCode + "]获取仓库Id时出现错误. error==>", e);
			throw new Exception(e);
		}
	}

	/**
	 * 根据区域编号和等级获取区域对象(向上查找，传入的区域等级应该大于等于3)
	 * @param pDivisionCode	城市区域编码
	 * @param pDivLevel		城市区域编码等级
	 * @MigrationSource 参考ATG  
	 * com.gome.api.pojo.builder.DisplayAddressService.getDivisionItem(String pDivisionCode, int pDivLevel)
	 */
	private GomeMstDivision getDivisionItem(String pDivisionCode, int pDivLevel) {
		try {
			logger.info("获取区域对象==>params[pDivisionCode:" + pDivisionCode + ";pDivLevel:" + pDivLevel + "]");
			String divisionCode = pDivisionCode;
			boolean flag = true;
			do {
				GomeMstDivision divisionItem = userAddressLinkFacade.getGomeMstDivision(divisionCode);
				if (divisionItem != null) {
					int divLevel = divisionItem.getDivLevel().intValue();
					String parentDivisionCode = divisionItem.getParentDivisionCode();

					if (divLevel < pDivLevel) {
						return null;
					} else if (divLevel == pDivLevel) {
						logger.info("获取区域对象==>[divisionCode=" + divisionCode + "] result{daId:" + divisionItem.getDaId() + ";divisionCode:" + divisionItem.getDivisionCode() + ";divLevel=" + divisionItem.getDivLevel() + ";}");
						return divisionItem;
					} else {
						divisionCode = parentDivisionCode;
					}
				} else {
					flag = false;
				}
			} while (flag);

		} catch (Exception e) { // 原ATG代码逻辑，此方法出现错误时未抛出异常，而是直接返回null
			logger.error("获取区域对象==>params[pDivisionCode:" + pDivisionCode + ";pDivLevel:" + pDivLevel + "].出现错误.error==>", e);
		}

		return null;

	}

	/**
	 * 填充价格信息到pPriceInfoDatas中
	 * @param userId 			会员Id
	 * @param areaCode 			二级区域code
	 * @param pPriceInfos 		收藏的商品信息
	 * @param pPriceInfoDatas 	记录搜藏商品的价格信息
	 * @throws Exception 
	 */
	private void batchFetchDataFromDubbo(String userId, String areaCode, List<String> pPriceInfos, Map<String, PriceInfo> pPriceInfoDatas) throws Exception {

		try {
			if (pPriceInfos == null || pPriceInfos.isEmpty()) {
				return;
			}
			long start = System.currentTimeMillis();
			List<PriceInfo> priceInfoList = priceService.getAreaPrices(pPriceInfos, areaCode);
			long executeTime = System.currentTimeMillis() - start;

			if (priceInfoList == null || priceInfoList.isEmpty()) {
				logger.error("[userId=" + userId + "][getAreaPrices()]==>params: [areaCode=" + areaCode + "].skuIds=" + JsonSerializeUtil.serialize(pPriceInfos));
				logger.error("[userId=" + userId + "][getAreaPrices()]==>调用服务 com.gome.stage.interfaces.item.IPriceService.getAreaPrices: 返回null ");
				return;
			}

			for (PriceInfo priceInfo : priceInfoList) {
				if (StringUtil.notEmpty(priceInfo.getSkuId())) {
					pPriceInfoDatas.put(priceInfo.getSkuId(), priceInfo);
				} else {
					logger.error("[userId=" + userId + "]数据异常，检查前台组接口返回的价格对象:" + JsonSerializeUtil.serialize(priceInfo));
				}
			}

			if (isDisplayLog) {
				logger.info("[userId=" + userId + "][getAreaPrices()]==>获价价格信息，执行时间：" + executeTime + "ms");
				logger.info("[userId=" + userId + "][getAreaPrices()]==>获取价格信息.params:[areaCode=" + areaCode + "] " + JsonSerializeUtil.serialize(pPriceInfos) + ".result: " + JsonSerializeUtil.serialize(pPriceInfoDatas));
			}
		} catch (Exception e) {
			logger.error("[userId=" + userId + "][getAreaPrices()]==>获取价格信息时，出现错误.error: ", e);
			throw new Exception(e);
		}
	}

	/**
	 * 获取商品的库存状态，并填充到pProductStocks中
	 * @param userId 			会员Id
	 * @param pProductStocks 	保存商品的库存状态：键值为skuNo,status
	 * @param requestDto 		获取库存信息的商品参数列表
	 * @throws Exception 
	 */
	private void getProductStock(String userId, Map<String, String> pProductStocks, AreaAtpRequestDto requestDto) throws Exception {

		try {
			long start = System.currentTimeMillis();
			List<AreaAtpResponseDetailDto> details = areaQueryService.getAreaAtpStatus(requestDto);
			long executeTime = System.currentTimeMillis() - start;

			if (isDisplayLog) {
				logger.info("[userId=" + userId + "][getAreaAtpStatus()]==>获取商品库存信息，执行时间：" + executeTime + "ms");
				logger.info("[userId=" + userId + "][getAreaAtpStatus()]==>获取商品库存信息. params: " + JsonSerializeUtil.serialize(requestDto));
				logger.info("[userId=" + userId + "][getAreaAtpStatus()]==>获取商品库存信息. result: " + JsonSerializeUtil.serialize(details));
			}

			/** 无货：N, 有货： Y  **/
			for (AreaAtpResponseDetailDto detail : details) {
				pProductStocks.put(detail.getPartNum(), detail.getStatus()); // partNum即skuNo为key值
			}
		} catch (Exception e) {
			logger.error("[userId=" + userId + "][getAreaAtpStatus()]==>获取商品库存信息. params: " + JsonSerializeUtil.serialize(requestDto));
			logger.error("[userId=" + userId + "][getAreaAtpStatus()]==>获取商品库存信息时，出现错误.error: ", e);
			throw new Exception(e);
		}
	}
	
	private void getProductStockFromGCC(String userId, Map<String, String> pProductStocks, List<String> prepaidSkuNos) throws Exception {

		try {
			long start = System.currentTimeMillis();
			ResultDTO<Map<String, Long>> result = prepaidCardQueryFacade.queryPrepaidCardStock(prepaidSkuNos);
			long executeTime = System.currentTimeMillis() - start;

			if (isDisplayLog) {
				logger.info("[userId=" + userId + "][queryPrepaidCardStock()]==>获取商品库存信息，执行时间：" + executeTime + "ms");
				logger.info("[userId=" + userId + "][queryPrepaidCardStock()]==>获取商品库存信息. params: " + JsonSerializeUtil.serialize(prepaidSkuNos));
				logger.info("[userId=" + userId + "][queryPrepaidCardStock()]==>获取商品库存信息. result: " + JsonSerializeUtil.serialize(result));
			}

			/** 无货：N, 有货： Y  **/
			if (result.isSuccess() && result.getData() != null) {
				for (Map.Entry<String, Long> entry : result.getData().entrySet()) {
					if (entry.getValue() != null && entry.getValue() > 0) {
						pProductStocks.put(entry.getKey(), "Y");
					} else {
						pProductStocks.put(entry.getKey(), "N");
					}
				}
			}
		} catch (Exception e) {
			logger.error("[userId=" + userId + "][queryPrepaidCardStock()]==>获取商品库存信息. params: " + JsonSerializeUtil.serialize(prepaidSkuNos));
			logger.error("[userId=" + userId + "][queryPrepaidCardStock()]==>获取商品库存信息时，出现错误.error: ", e);
			throw new Exception(e);
		}
	}

	/**
	 * 获取商品的sku详细信息，并填充到skuInfos中
	 * @param userId 	会员Id
	 * @param skuList 	商品的店铺编号列表
	 * @param skuInfos 	商品的sku信息
	 * @throws Exception 
	 
	private void getSkuItem(String userId, List<String> skuList, Map<String, Object> skuInfos) throws Exception {
		try {
			if (skuList.size() < 0) {
				return;
			}
			long start = System.currentTimeMillis();
			// 获取商品详细信息.参数格式为 productId:skuId
			// 1、如果productId为空，返回的productIds为根据skuId取到的信息中的默认第一个。不为null时为传递进去的参数productId
			// 2、如果skuId不正确，则返回的productIds为传递进去的productId，skuId=null，且state=2(下架)
			List<SkuItem> skuItems = itemService.getSkus(skuList, "100");
			long executeTime = System.currentTimeMillis() - start;
			if (isDisplayLog) {
				logger.info("[userId=" + userId + "][getSkus()]==>获取商品sku信息, 执行时间：" + executeTime + "ms");
				logger.info("[userId=" + userId + "][getSkus()]==>获取商品sku信息. params: " + JsonSerializeUtil.serialize(skuList));
				logger.info("[userId=" + userId + "][getSkus()]==>获取商品sku信息. return: " + JsonSerializeUtil.serialize(skuItems));
			}

			for (SkuItem item : skuItems) {
				skuInfos.put(item.getProductIds() + ":" + item.getSkuId(), item); // 参数格式为==>productId:skuId
			}

		} catch (Exception e) {
			logger.error("[userId=" + userId + "][getSkus()]==>获取商品sku信息. params: " + JsonSerializeUtil.serialize(skuList));
			logger.error("[userId=" + userId + "][getSkus()]==>获取商品sku信息时，出现错误.error: ", e);
			throw new Exception(e);
		}
	}*/
	
	/**
	 * 调用新接口获取商品的sku详细信息，并填充到skuInfos中
	 * @param userId 	会员Id
	 * @param skuList 	商品的店铺编号列表
	 * @param skuInfos 	商品的sku信息
	 * @throws Exception 
	 */
	private void getSkuItemNew(String userId, List<String> skuList, Map<String, Object> skuInfos) throws Exception {
		String[] queryPoperties = new String[]{"skuNo","sapSkuType"};
		try {
			if (skuList.size() < 1) {
				return;
			}
			long start = System.currentTimeMillis();
			// 获取商品详细信息.参数格式为skuId
			// 1、如果productId为空，返回的productIds为根据skuId取到的信息中的默认第一个。不为null时为传递进去的参数productId
			// 2、如果skuId不正确，则返回的productIds为传递进去的productId，skuId=null，且state=2(下架)
			List<Map<String, Object>> list = itemService.getSkusMultiProperties(skuList,queryPoperties);
			long executeTime = System.currentTimeMillis() - start;
			if(list == null || list.size()<1){
				logger.warn("[userId=" + userId + "][itemService.getSkusMultiProperties(skuList,)]==>获取商品列表信息,返回为空!,skuList==>"+JsonSerializeUtil.serialize(skuList) );
				return;
			}
			if(list.size() < skuList.size()){
				logger.warn("[userId=" + userId + "][itemService.getSkusMultiProperties(skuList,)]==>获取商品列表信息[ 小于 ]传入参数个数!传入参数："+skuList.size()+"个   ,返回列表："+skuList.size()+"个;  skuList==>"+JSONUtils.toJSONString(skuList) );
			}
			logger.info("[userId=" + userId + "][itemService.getSkusMultiProperties()]==>获取商品sku信息, 执行时间：" + executeTime + "ms");	
			if (isDisplayLog) {
				logger.info("[userId=" + userId + "][getSkuItemNew()]==>获取商品sku信息. params: " + JsonSerializeUtil.serialize(skuList));
				logger.info("[userId=" + userId + "][getSkuItemNew()]==>获取商品sku信息. return: " + JsonSerializeUtil.serialize(list));
			}
			
			for (Map<String, Object> item : list) {
				SkuItem skuItem = new SkuItem();
				skuItem.setSkuNo((String)item.get("skuNo"));
				skuItem.setSapSkuType((String)item.get("sapSkuType"));
//				skuItem.setSkuId((String)item.get("skuId"));
//				skuItem.setSkuType((String)item.get("skuType"));
//				skuItem.setProductIds((String)item.get("productIds"));
//				skuItem.setProductType((String)item.get("productType"));
//				skuItem.setShopFlag((String)item.get("shopFlag"));
//				skuItem.setSkuImgs((List)item.get("skuImgs"));
				skuInfos.put(skuItem.getSkuNo(),skuItem);
			}

		} catch (Exception e) {
			logger.error("[userId=" + userId + "][getSkuItemNew()]==>获取商品sku信息. params: " + JsonSerializeUtil.serialize(skuList));
			logger.error("[userId=" + userId + "][getSkuItemNew()]==>获取商品sku信息时，出现错误.error: ", e);
			throw new Exception(e);
		}
	}

	/**
	 * 获取商品的店铺信息，并填充到shopInfos中
	 * @param userId 		会员Id
	 * @param merchantIds 	店铺编号列表
	 * @param shopInfos 	店铺对象列表
	 * @throws Exception 
	 */
	private void getShopItem(String userId, List<String> merchantIds, Map<String, Object> shopInfos) throws Exception {
		try {
			long start = System.currentTimeMillis();
			List<ShopItem> shopItem = shopService.getShopInfosByShopIds(merchantIds);
			long executeTime = System.currentTimeMillis() - start;
			if (isDisplayLog) {
				logger.info("[userId=" + userId + "][getShopInfosByShopIds()]==>获取商品店铺信息，执行时间：" + executeTime + "ms");
				logger.info("[userId=" + userId + "][getShopInfosByShopIds()]==>获取商品店铺信息. params: " + JsonSerializeUtil.serialize(merchantIds));
				logger.info("[userId=" + userId + "][getShopInfosByShopIds()]==>获取商品店铺信息. result: " + JsonSerializeUtil.serialize(shopItem));
			}
			for (ShopItem item : shopItem) {
				shopInfos.put(item.getShopId(), item);
			}
		} catch (Exception e) {
			logger.error("[userId=" + userId + "][getShopInfosByShopIds()]==>获取商品店铺信息. params: " + JsonSerializeUtil.serialize(merchantIds));
			logger.error("[userId=" + userId + "][getShopInfosByShopIds()]==>获取商品店铺信息时出错.error: ", e);
			throw new Exception(e);
		}
	}

	/**
	 * 根据过滤器[1：降价商品，2：现货商品，3：促销商品]过滤用户收藏的商品.
	 * @param userId 			会员Id
	 * @param filterGiftItem 	用户收藏的商品列表(已过滤过分类)
	 * @param productStocks 	记录商品的库存信息
	 * @param skuInfos 			记录商品的Sku的详细信息
	 * @param priceInfos 		记录商品的价格和促销信息
	 * @param pFilter 			过滤器：(1：降价商品；2：现货商品；3：促销商品).多选时以","分隔
	 * @throws Exception 
	 
	private void filterByStatus(String userId, List<GiftListItemModel> filterGiftItem, Map<String, String> productStocks, Map<String, PriceInfo> priceInfos, String filter) throws Exception {

		try {
			if (StringUtil.isEmpty(filter)) {
				return;
			}
			long start = System.currentTimeMillis();
			for (Iterator<GiftListItemModel> it = filterGiftItem.iterator(); it.hasNext();) {

				GiftListItemModel item = (GiftListItemModel) it.next();
//				SkuItem skuItem = (SkuItem) skuInfos.get(item.getProductId() + ":" + item.getCatalogRefId());
				SkuItem skuItem =  new SkuItem();
				skuItem.setProductIds(item.getProductId());
				skuItem.setState(Integer.parseInt(item.getState()));
				skuItem.setSkuId(item.getCatalogRefId());
				// filter 过滤商品：仅显示[1：降价商品，2：现货商品，3：促销商品]
				boolean isFiltered = filteredRepositoryItem(filter, item.getPriceDown(), skuItem, productStocks);
				if (isFiltered) {
					it.remove();
				}
			}
			long executeTime = System.currentTimeMillis() - start;
			logger.info("[userId=" + userId + "][根据【过滤器】过滤用户收藏的商品]==>执行时间：" + executeTime + "ms");
		} catch (Exception e) {
			logger.error("[userId=" + userId + "][根据【过滤器】过滤用户收藏的商品]==>出现错误.error: ", e);
			throw new Exception(e);
		}
	} */


	/**
	 * 此收藏商品是否可以被显示状态条件过滤掉
	 * @param pFilter 			过滤器：(1：降价商品；2：现货商品；3：促销商品).多选时以","分隔
	 * @param pPriceDown 		收藏商品现在的价格比收藏时下降量
	 * @param pPromoInfos 		商品的促销信息
	 * @param pSkuItem 			商品的sku信息
	 * @param pProductStocks 	商品的库存信息
	 * @return true：商品不在此显示状态下, false：商品在此显示状态下
	 * @throws Exception 
	 
	private boolean filteredRepositoryItem(String pFilter, String pPriceDown, SkuItem pSkuItem, Map<String, String> pProductStocks) throws Exception {
		try {
			if (StringUtil.isEmpty(pFilter)) {
				return false;
			}

			Integer skuState = pSkuItem.getState();
			// 2表示下架，6表示归档(商品无效)
			if (offSell == skuState || filed == skuState) { // 商品的sku库存状态
				logger.warn("[productIds=" + pSkuItem.getProductIds() + ",skuId=" + pSkuItem.getSkuId() + "]==>filter the item by spots(sold out).[state=" + skuState + "]");
				return true;
			}

			String[] filters = pFilter.split(",");
			for (String filterTemp : filters) {
				if (FILTER_PRICE_DOWN.equals(filterTemp)) { // 降价商品
					if (StringUtil.isEmpty(pPriceDown)) {
						if (isDisplayLog) {
							logger.info("[productIds=" + pSkuItem.getProductIds() + ",skuId=" + pSkuItem.getSkuId() + "]=>filter the item by price down.pPriceDown is null");
						}
					} else {
						return false;
					}
				} else if (FILTER_PROMOTION.equals(filterTemp)) { // 促销商品(已删除此过滤项)
//之前明飞就删除了这些					if (pPromoInfos == null || pPromoInfos.isEmpty()) {
//						if (isDisplayLog) {
//							logger.info("[productIds=" + pSkuItem.getProductIds() + ",skuId=" + pSkuItem.getSkuId() + "]==>filter the item by promotion");
//						}
//					} else {
//						return false;
//					}
				} else if (FILTER_SPOTS.equals(filterTemp)) { // 现货商品
					String skuNo = pSkuItem.getSkuNo();
					if (pProductStocks != null && !pProductStocks.isEmpty()) {
						String productStock = pProductStocks.get(skuNo);
						if (noGoods.equalsIgnoreCase(productStock)) { // 库存状态
							if (isDisplayLog) {
								logger.info("[productIds=" + pSkuItem.getProductIds() + ",skuId=" + pSkuItem.getSkuId() + "]==>filter the item by spots(no inventory).[productStock=" + productStock + "]");
							}
						} else {
							return false;
						}
					}
				}
			}
			return true;
		} catch (Exception e) {
			logger.error("检查收藏商品是否可以被显示状态条件过滤掉时出错==>error: ", e);
			throw new Exception(e);
		}
	}
	*/
	/**
	 * 对比收藏时的价格和现在的价格，返回差价，如未降价则返回null
	 * @param nowPrice 		现在的价格
	 * @param favorityPrice 收藏时的价格
	 */
	private String getProductDownPrice(Double nowPrice, Double favorityPrice) {

		BigDecimal favPriceBD = new BigDecimal(favorityPrice);
		BigDecimal nowPriceBD = new BigDecimal(nowPrice);
		BigDecimal downPriceBD = favPriceBD.subtract(nowPriceBD);
		if (downPriceBD.doubleValue() > 0.00) {
			DecimalFormat df = new DecimalFormat("#0.00");
			return df.format(downPriceBD);
		}
		return null;
	}

	/**
	 * 填充数据
	 * @param showGiftItem 		用户收藏的商品列表
	 * @param skuInfos 			记录商品的Sku的详细信息
	 * @param shopInfos 		记录商品的店铺信息
	 * @param productStocks 	记录商品的库存信息
	 * @param userId 			会员编号
	 * @param myFavoritesList 	返回前端的收藏的商品对象列表
	 * @param catInfos 			记录商品的三级分类信息
	 * @throws Exception 
	 */
	private void fillData(List<GiftListItemModel> showGiftItem, Map<String, Object> shopInfos, Map<String, String> productStocks, String userId, List<MyFavoritesInfoModel> myFavoritesList, Map<String, Object> catInfos) throws Exception {

		try {
			long start = System.currentTimeMillis();

			for (GiftListItemModel item : showGiftItem) {
				String productId = item.getProductId();
				String skuId = item.getCatalogRefId();
//				SkuItem skuItem = (SkuItem) skuInfos.get(productId + ":" + skuId);

//				if (skuItem == null || StringUtil.isEmpty(skuItem.getSkuId()) || StringUtil.isEmpty(skuItem.getProductIds())) {
//					logger.warn("[userId:" + userId + "][productId=" + productId + ",skuId=" + skuId + "]==>调用前台getSkus()服务未找到对应的商品信息.");
//					continue;
//				}

				MyFavoritesInfoModel favorites = new MyFavoritesInfoModel(); // 收藏商品对象
//				String shopNo = skuItem.getShopNo(); // 店铺编号
				String shopNo = item.getShopId(); // 店铺编号
				ShopItem shopItem = null;
				if (StringUtil.notEmpty(shopNo)) {
					shopItem = (ShopItem) shopInfos.get(shopNo);
				}

				String reserveStatus = "";// 预约商品的状态
				if (shopItem != null) { // 店铺商品
					if (shopItem.getShopId().equalsIgnoreCase(mKingShopNo)) {
						favorites.setIsJinXiangProduct("true");
					} else {
						favorites.setIsJinXiangProduct("false");
					}

					String merchantId = shopItem.getShopId();
					String merchantName = shopItem.getShopName();
					favorites.setMerchantName(merchantName);
					favorites.setMerchantUrl(getMerchantUrl(merchantId));

				} else { // 非店铺商品，预约购买增加相关参数
					favorites.setIsJinXiangProduct("false");
					Map<String, String> reserveBuyStatus = reserveManagerFacade.getReserveBuyStatus(productId, skuId, userId);
					String isReserveSku = reserveBuyStatus.get("isReserveSku"); // 是预约商品
					reserveStatus = reserveBuyStatus.get("reserveStatus"); // 商品的预约状态
					favorites.setIsReserveSku(isReserveSku);
					favorites.setReserveStatus(reserveStatus);
				}

//				String skuImgUrl = null;
//				List<FSImgBase> skuImgs = skuItem.getSkuImgs();
//				if (skuImgs == null || skuImgs.isEmpty()) {
//					logger.warn("[userId:" + userId + "]填充数据==>[productId=" + productId + ",skuId=" + skuId + "].调用前台getSkus()服务返回的商品图片地址为空");
//				} else {
//					skuImgUrl = skuImgs.get(0).getSrc();
//				}
				String skuImgUrl = item.getSkuImgUrl();
				
				favorites.setPriceDown(item.getPriceDown());
				/*favorites.setPromoInfo(item.getPromoInfo());
				boolean is3D = skuItem.getIs3D() == null ? false : Boolean.parseBoolean(skuItem.getIs3D().toString());
				favorites.setIs3DProduct(is3D);*/

				favorites.setImageUrl(skuImgUrl);
				favorites.setProductId(productId);
				String productUrl = productDetailUrl.retrieveProductDetailUrl(productId, skuId, null);
				favorites.setProductUrl(productUrl);
				favorites.setDisplayName(item.getDisplayName());
				favorites.setCollectDate(DateUtil.DateToStr(item.getGiftCollectDate(), "yyyy-MM-dd HH:mm:ss"));
				favorites.setSkuId(skuId);
				favorites.setGiftItemId(item.getGiftitemId());

//				String skuNo = skuItem.getSkuNo();
				String skuNo = item.getGiftitemId();
				
				if (StringUtil.notEmpty(skuNo)) {
					favorites.setSkuNo(skuNo);

//					Integer skuState = skuItem.getState(); // 商品的sku库存状态
					Integer skuState = Integer.valueOf(item.getState());
					if (offSell == skuState || filed == skuState) { // 2表示下架，6表示归档
						favorites.setPrdstock("X");
					} else {
						String productStock = productStocks.get(skuNo);
						favorites.setPrdstock(productStock);
					}
				}

				// 商品分类信息
				if (null != catInfos && !catInfos.isEmpty()) {
					com.gome.stage.item.CategoryInfo catInfo = (com.gome.stage.item.CategoryInfo) catInfos.get(productId);
					if (catInfo != null) {
						favorites.setFirstCategoryId(catInfo.getFirstCatId());
						favorites.setThirdCategoryId(catInfo.getThdCatId());
					}
				}

				// 商品价格
				boolean isShowPrice = isShowPrice(productId, skuId);
				if (("01".equals(reserveStatus) || "02".equals(reserveStatus)) && !isShowPrice) {
					favorites.setSkuPrice(noSale);
				} else {
					if (null == item.getProductPrice()) {
						favorites.setSkuPrice(noPrice);
					} else {
						DecimalFormat df = new DecimalFormat("#0.00");
						favorites.setSkuPrice(df.format(item.getProductPrice()));
					}
				}
				favorites.setProductType(item.getProductType());

				/*// 检查此商品的skuId是否与商品productId关联
				if (StringUtil.isEmpty(skuItem.getProductIds())) {
					favorites.setIsOnlyShow(true);
					logger.warn("[fillData()]==>商品无效,sku信息中返回的productIds为null.sku: " + JsonSerializeUtil.serialize(skuItem));
					continue;
				} else {
					List<String> productIds = new ArrayList<String>(Arrays.asList(skuItem.getProductIds().split(",")));
					if (!productIds.contains(productId)) {
						favorites.setIsOnlyShow(true);
						logger.warn("[fillData()]==>商品skuId与productId不匹配. productId[" + productId + "]. skuId: " + skuItem.getSkuId() + ". productIds: " + skuItem.getProductIds());
						continue;
					}
				}*/

//				favorites.setShopFlag(skuItem.getShopFlag()); // 海外购标识
				favorites.setShopFlag(item.getHwgFlag());
				myFavoritesList.add(favorites);
			}

			long executeTime = System.currentTimeMillis() - start;
			logger.info("[userId:" + userId + "]填充数据==>执行时间：" + executeTime + "ms");
		} catch (Exception e) {
			logger.error("[userId:" + userId + "]填充数据==>出现错误.error: ", e);
			throw new Exception(e);
		}

	}

	/**
	 * 根据店铺编号获取店铺地址
	 * @param merchantId 店铺Id
	 * @return 店铺地址url
	 */
	private String getMerchantUrl(String merchantId) {
		if (StringUtil.notEmpty(merchantId)) {
			return coo8Site + merchantId;
		}
		return null;
	}

	/**
	 * 判断对应的商品是否显示价格，根据需求规定，在预热或预约阶段，如果gcc后台设置不显示价格，则页面需要提示敬请期待
	 * @param pProductId 	商品Id
	 * @param pSkuId		商品skuId
	 * @return
	 * @throws Exception 
	 * @MigrationSource 参考ATG
	 *  com.gome.commerce.reserve.ReserveManager.isShowPrice(String pProductId, String pSkuId)
	 */
	private boolean isShowPrice(String pProductId, String pSkuId) throws Exception {
		boolean result = false;
		try {
			Map<String, String> item = reserveManagerFacade.getSkuPromotionItem(pProductId, pSkuId);
			if (isDisplayLog) {
				logger.info("是否显示价格==>params[pProductId:" + pProductId + ",pSkuId=" + pSkuId + "]. return: " + JsonSerializeUtil.serialize(item));
			}
			if (null != item) {
				Long nowTime = Calendar.getInstance().getTimeInMillis();
				String isShowPrice = item.get("isShowPrice");// 价格显示
																// 0:显示;1:隐藏
				if ("0".equals(isShowPrice)) {
					String reserveStartTime = item.get("reserveStartTime");
					String reserveEndTime = item.get("reserveEndTime");
					String preheatStartTime = item.get("preheatStartTime");
					String preheatEndTime = item.get("preheatEndTime");

					if (!StringUtil.isEmpty(reserveStartTime) && !StringUtil.isEmpty(reserveEndTime)) {
						Long lReserveStartTime = Long.valueOf(reserveStartTime);
						Long lReserveEndTime = Long.valueOf(reserveEndTime);
						if (nowTime > lReserveStartTime && nowTime < lReserveEndTime) {
							result = true;
						}
					}
					if (!StringUtil.isEmpty(preheatStartTime) && !StringUtil.isEmpty(preheatEndTime)) {
						Long lPreheatStartTime = Long.valueOf(preheatStartTime);
						Long lPreheatEndTime = Long.valueOf(preheatEndTime);
						if (nowTime > lPreheatStartTime && nowTime < lPreheatEndTime) {
							result = true;
						}
					}
				}
			}
		} catch (Exception e) {
			logger.error("是否显示价格==>pProductId:" + pProductId + "--pSkuId:" + pSkuId + "-> 判断是否显示商品价格时出错.error: ", e);
			throw new Exception(e);
		}
		return result;
	}

	/**
	 * 获取商品的分类信息，并填充到catInfos中
	 * @param showGiftItem 	收藏的商品列表
	 * @param catInfos 		商品的分类信息
	 * @throws Exception 
	 */
	private void getCatInfo(List<GiftListItemModel> showGiftItem, Map<String, Object> catInfos) throws Exception {

		try {
			List<String> lstProdIds = new ArrayList<String>();
			for (GiftListItemModel model : showGiftItem) {
				lstProdIds.add(model.getProductId());
			}
			long start = System.currentTimeMillis();
			List<com.gome.stage.item.CategoryInfo> catItem = productInfoService.getCatInfoByProdId(lstProdIds);
			long executeTime = System.currentTimeMillis() - start;
			logger.info("[getCatInfoByProdId()]==>获取商品分类信息，执行时间：" + executeTime + "ms");
			if (isDisplayLog) {
				logger.info("[getCatInfoByProdId()]==>获取商品分类信息. params: " + JsonSerializeUtil.serialize(lstProdIds));
				logger.info("[getCatInfoByProdId()]==>获取商品分类信息. return: " + JsonSerializeUtil.serialize(catItem));
			}

			for (com.gome.stage.item.CategoryInfo item : catItem) {
				if (StringUtil.notEmpty(item.getProductId())) {
					catInfos.put(item.getProductId(), item);
				}
			}
		} catch (Exception e) {
			logger.error("[getCatInfoByProdId()]==>获取商品的分类信息时，出现错误.error==>", e);
			throw new Exception(e);
		}
	}

	/**
	 * 判断浏览过的商品是否加入收藏夹
	 * @throws 
	 */
	@RequestMapping("getIsFavorit")
	@ResponseBody
	public Object getIsFavorit(HttpServletRequest request) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, Object> errorMsgMap = new HashMap<String, Object>();
		List<String> productIdList = new ArrayList<String>();
		String productIdsString = "";
		String userId = "";
		String callBackName = getCallBackName(request);
		try {
			userId = getUserId(request);
			errorMsgMap = promptMsgTools.valLoginErrorInfo(userId);
			if (errorMsgMap != null) {
				logger.error("[getIsFavorit()]==>[userId:" + userId + "].error:" + JsonSerializeUtil.serialize(errorMsgMap));
				return getCallBackString(callBackName, errorMsgMap);
			}

			// 获取名字为proid120517atg的Cookie
			productIdsString = getCookies(request);
			if (StringUtil.isEmpty(productIdsString)) {
				result.put("isFavorits", null);
				resultMap.put("result", result);
				return getCallBackString(callBackName, resultMap);
			}

			// cookie转换为参数
			logger.info("[getIsFavorit()][userId: " + userId + "][productIdsString: " + productIdsString + "]");
			JSONArray productJSON = (JSONArray) JSONArray.parse(productIdsString);
			for (int i = 0; i < productJSON.size(); i++) {
				productIdList.add((String) productJSON.get(i));
			}

			if (productIdList == null || productIdList.size() < 0) {
				result.put("isFavorits", null);
				resultMap.put("result", result);
				return getCallBackString(callBackName, resultMap);
			}
			//过滤出所有已经收藏的商品列表
    		List<String> skuParamList = new ArrayList<String>(productIdList.size());
    		for(Iterator<String> it=productIdList.iterator();it.hasNext();){
    			String item = it.next();
    			String[] prdSkuIds = item.split("-");
    			String skuId=null;
    			if (prdSkuIds.length > 1) {
    				skuId = prdSkuIds[1];
    				skuParamList.add(skuId);
    			}else{
    				logger.warn("[getIsFavoritList()]==>[userId:" + userId + "].cookie收藏数据错误：prdSkuIds==>"+ item);
    				//不符合格式的数据排除掉
    			}
			}
    		
//			UserResult<List<String>> isFavoritsResult = myFavoritesGoodsFacade.getIsFavoritList(productIdList, userId);
    		//调用接口查询浏览列表的数据是否被收藏
    		long callStart = System.currentTimeMillis();
    		Map<String,Boolean> existsMap = favoriteItemService.existsfavorits(userId, skuParamList.toArray(new String[skuParamList.size()]));
    		logger.info("[userId:" + userId + "][favoriteItemService.existsfavorits(userId,skuParamList)]==>总执行时间：" + (System.currentTimeMillis() - callStart) + "ms");
    		
    		List<String> isOrNoFavoritList=new ArrayList<String>();
    		for(Iterator<String> it=productIdList.iterator();it.hasNext();){
    			String item = it.next();
    			String isInWishlist = fillDataInWishlist(item, userId,existsMap);
    			if (StringUtil.notEmpty(isInWishlist)) {
    				isOrNoFavoritList.add(isInWishlist);
    			}
    		}
//			if (!isFavoritsResult.isSuccess() || isFavoritsResult.getBuessObj() == null) {
//				result.put("isFavorits", null);
//				resultMap.put("result", result);
//				return getCallBackString(callBackName, resultMap);
//			}
			if (isOrNoFavoritList == null || isOrNoFavoritList.size() < 1) {
				result.put("isFavorits", null);
				resultMap.put("result", result);
				return getCallBackString(callBackName, resultMap);
			}

//			result.put("isFavorits", isFavoritsResult.getBuessObj());
			result.put("isFavorits", isOrNoFavoritList);
			
			resultMap.put("result", result);
		} catch (Exception e) {
			logger.error("[getIsFavorit()][userId: " + userId + "]判断是否收藏出错", e);
			result.put("isFavorits", null);
			resultMap.put("result", result);
			return getCallBackString(callBackName, resultMap);
		} finally {
			logger.info("[getIsFavorit()][userId: " + userId + "]result: " + JsonSerializeUtil.serialize(resultMap));
		}

		return getCallBackString(callBackName, resultMap);
	}

	private String getCookies(HttpServletRequest request) {
		String productIdList = "";
		if (request == null) {
			return null;
		}
		Cookie[] cookies = request.getCookies();
		if ((cookies == null) || (cookies.length < 1)) {
			return null;
		}
		for (Cookie ck : cookies) {
			if (ck == null) {
				continue;
			}
			if (proidCookieName.equalsIgnoreCase(ck.getName())) {
				try {
					productIdList = URLDecoder.decode(ck.getValue(), "UTF-8");
				} catch (UnsupportedEncodingException e) {
					logger.error("[getIsFavorit()][UnsupportedEncodingException]:cookie数据转码报错", e);
					return null;
				} catch (Exception e2) {
					logger.error("[getIsFavorit()]:cookie数据转码报错", e2);
				}
				return productIdList;
			}

		}

		return null;
	}
	/**
	 * 
	 * Description: 我的国美- 最近浏览的商品. <br/>
	 * @param idSet cookie里面单个productId:skuNo
	 * @param userId
	 * @return
	 */
	private String fillDataInWishlist(String idSet,String userId, Map<String,Boolean> existsMap){
		boolean inWishlist = false;
    	String result=null;
		if(StringUtil.isEmpty(idSet)){
			if (logger.isInfoEnabled()) {
				logger.info("[verifyIsInWishlist()]==>cookie中获取的Product-Sku编号为null.");
			}
			return null;
		}
		String[] prdSkuIds = idSet.split("-");
		String productId = null;
		String skuId=null;
		if (prdSkuIds.length > 0) {
			productId = prdSkuIds[0];
		}
		if (prdSkuIds.length > 1) {
			skuId = prdSkuIds[1];
		}
		SkuItem skuItem = itemService.getSku(productId, null, "50");
		if(StringUtil.isEmpty(skuId)){
			skuId=skuItem.getSkuId();
		}
		if(StringUtil.isEmpty(productId) || StringUtil.isEmpty(skuId)){
			logger.info("verifyIsInWishlist[] 传入的productId或者skuId为空");
			return null;
		}
		
		// 是否金象网产品
		boolean isJinXiang=mKingShopNo.equalsIgnoreCase(skuItem.getShopNo())? true:false;
		
		inWishlist=(existsMap.get(skuId)==null)?false:existsMap.get(skuId);
		
	   result=productId+":"+skuId+":"+inWishlist+":"+isJinXiang;
	   return result;
    	
    }

	/**
	 * 购物车-我的收藏
	 * @param userId		会员Id
	 * @param categoryId 	分类编号
	 * @param districtCode	城市区域编码(四级)
	 * @param currPageNum	当前页数
	 * @param pageSize		每页显示记录数
	 */
	@RequestMapping("getFavoritesGoodsForShopCart")
	@ResponseBody
	public Object getFavoritesGoodsForShopCart(HttpServletRequest request, String districtCode, String currPageNum, String pageSize) {

		String callBackName = getCallBackName(request);
		Map<String, Object> resultMap = new HashMap<String, Object>();
		MyFavoritesListModel favoritesList = null;
		String userId = null;
		try {
			userId = getUserId(request);
			// 1、检查用户是否为null
			if (StringUtil.isEmpty(userId)) {
				resultMap = ResultTools.getErrorMap(101, "请登录，在操作");
				return getCallBackString(callBackName, resultMap);
			}

			logger.info("购物车-我的收藏.params==>[userId=" + userId + "]districtCode:" + districtCode + ";currPageNum=" + currPageNum + ";pageSize=" + pageSize);

			// 2、检查区域编码是否为空
			if (StringUtil.isEmpty(districtCode)) {
				logger.warn("[userId:" + userId + "]==>购物车-我的收藏.districtCode is null");
			}

			int currPageNumInt = StringUtil.isEmpty(currPageNum) ? 1 : Integer.valueOf(currPageNum);
			int pageSizeInt = StringUtil.isEmpty(pageSize) ? DEFAUT_PAGESIZE : Integer.valueOf(pageSize);

			// 3、获取我的收藏夹-商品列表数据
			favoritesList = getFavoritesList(userId, null, districtCode, null, currPageNumInt, pageSizeInt, true);
			if (null == favoritesList) {
				favoritesList = new MyFavoritesListModel();
				Pagination<MyFavoritesInfoModel> pagination = new Pagination<MyFavoritesInfoModel>(0, 0, pageSizeInt);
				pagination.setList(new ArrayList<MyFavoritesInfoModel>());
				favoritesList.setPagination(pagination);
				logger.error("[userId:" + userId + "]购物车-我的收藏==>未查询到任何收藏信息，检查日志是否业务逻辑是否出现异常.");
			}

			Map<String, Object> result = new HashMap<String, Object>();
			result.put("favoritesList", favoritesList);
			resultMap.put("result", result);

		} catch (Exception e) {
			logger.error("[userId:" + userId + "]购物车-我的收藏==>获取收藏商品时，出现错误.error==>", e);
			resultMap = ResultTools.getErrorMap(404, "加载我的收藏商品失败,请稍后再试!");
			return getCallBackString(callBackName, resultMap);
		} finally {
			if (isDisplayLog) {
				logger.info("[userId:" + userId + "]购物车-我的收藏==>[result=" + JsonSerializeUtil.serialize(favoritesList) + "]");
			}
		}

		return getCallBackString(callBackName, resultMap);
	}
	private void convertModel(FavoriteItemBean orignModel,GiftListItemModel toModel){
		toModel.setGiftitemId(orignModel.getGifitem_id());
		toModel.setCatalogRefId(orignModel.getCatalog_ref_id());
		toModel.setProductId(orignModel.getProduct_id());
		toModel.setDisplayName(orignModel.getDisplay_name());
		toModel.setGiftCollectDate(orignModel.getGift_collect_date());
		toModel.setFavoritesPrice(orignModel.getFavorites_price());
		toModel.setThirdCatId(orignModel.getThirdCatId());
		
		toModel.setProductType(orignModel.getProductType());
		toModel.setSkuImgUrl(orignModel.getImgURL());
		toModel.setHwgFlag(orignModel.getHwgPorduct());
		toModel.setState(orignModel.getState());
		toModel.setShopId(orignModel.getShopid());
		toModel.setSapSkuType(orignModel.getSapSkuType());
	}

}
