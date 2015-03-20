package com;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;
import org.htmlcleaner.XPatherException;

public class HtmlParser {

	String text;

	HtmlCleaner htmlCleaner;

	public HtmlParser(String text) {
		// TODO Auto-generated constructor stub

		this.text = text;

		this.htmlCleaner = new HtmlCleaner();

	}

	public ArrayList parse() throws XPatherException {

		TagNode tagNode = this.htmlCleaner.clean(text);

		String trXpath = "//*[@id=\"yubao\"]/tbody/tr";

		Object[] objArray = tagNode.evaluateXPath(trXpath);

		ArrayList<HashMap> result = new ArrayList();

		if (objArray != null && objArray.length > 0) {

			for (Object obj : objArray) {

				HashMap map = new HashMap();

				TagNode tagNodeTr = (TagNode) obj;

				String dateXpath = "//td[1]/span";
				String temperatureXpath = "//td[4]";

				String dateString = ((TagNode) tagNodeTr
						.evaluateXPath(dateXpath)[0]).getText().toString()
						.trim();
				

				
				String date = dateString.split("\\s")[0].trim();
				
				Pattern datePattern = Pattern.compile(
						"^(\\d+).*?(\\d+).*$", Pattern.DOTALL);

				Matcher dateMatcher = datePattern
						.matcher(date);


				dateMatcher.find();
				
				
				

				map.put("date", dateMatcher.group(1)+'月'+dateMatcher.group(2));
				map.put("week", dateString.split("\\s")[1].trim());
				
				
				
				String temperatureString = ((TagNode) tagNodeTr
						.evaluateXPath(temperatureXpath)[0]).getText()
						.toString().trim();

				
				
				Pattern temperaturePattern = Pattern.compile(
						"^(-?\\d+).*?(-?\\d+).*$", Pattern.DOTALL);

				Matcher temperatureMatcher = temperaturePattern
						.matcher(temperatureString);


				temperatureMatcher.find();
				map.put("lowTemperature", temperatureMatcher.group(1));
				map.put("highTemperature", temperatureMatcher.group(2));

				result.add(map);
			}
		}

		return result;
	}

	public static void main(String[] args) throws XPatherException {

		String text = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\"><html xmlns=\"http://www.w3.org/1999/xhtml\"><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" /><title>北京天气预报15天|北京天气预报15天查询 - 天气预报15天</title><link href=\"/css.css\" rel=\"stylesheet\" type=\"text/css\" /><link href=\"/tianqi.css\" rel=\"stylesheet\" type=\"text/css\" /><meta http-equiv=\"X-UA-Compatible\" content=\"IE=7\" /><meta name=\"keywords\" content=\"北京天气预报15天，北京天气预报15天查询\" /><meta name=\"description\" content=\"北京天气预报15天，北京天气气温，气压，相对湿度，降水量预报查询\" /></head><body><div id=\"top\">  <div id=\"ulink\"><a href=\"http://www.15tianqi.com\">天气预报15天</a> </div></div><div id=\"header\">  <h1>北京天气预报15天</h1>  <span class=\"pl8\"><a href=\"http://www.15tianqi.com\">【首页】</a>全国各省市天气预报15天查询</span></div><div id=\"mainbox\">  <div class=\"leftbox\"> <div class=\"panel\">  <div class=\"mtitle\">    <div class=\"fleft\">天气预报15天查询</div>  </div>  <div class=\"mcon center\">    <p>    <form action=\"/?action=Cha\" method=\"post\" class=\"f14\">      <input name=\"value\" id=\"q\" type=\"text\" size=\"18\" class=\"inp\" onmouseover=\"this.className='inp_2';\" onblur=\"this.className='inp'\" value=\"请输入城市简拼或城市名称\" onfocus=\"this.value=''\"/>      <input type=\"submit\" value=\"查询\" class=\"but_L1\" onmouseout=\"this.className='but_L1'\" onmouseover=\"this.className='but_L2'\" />    </form>    </p>    <p class=\"center\">例如：查询北京天气请输入：<b class=\"red\">bj</b>或<b class=\"red\">北京</b>或<b class=\"red\">beijing</b></p>  </div></div>  <div style=\"text-align:center\"><script type=\"text/javascript\">/*15天气 760*90，创建于2014-3-12*/var cpro_id = \"u1486796\";</script><script src=\"http://cpro.baidustatic.com/cpro/ui/c.js\" type=\"text/javascript\"></script></div>    <div class=\"panel\">      <div class=\"mtitle\">        <div class=\"fleft\"><a href=\"http://www.15tianqi.com\" title=\"天气预报15天查询\">天气预报15天查询</a>&gt;北京天气预报<span class=\"gray\">(beijing)</span></div>      </div>      <table border=\"0\" width=\"100%\" cellpadding=\"0\" cellspacing=\"8\">        <tr>          <td valign=\"top\" width=\"192\" bgcolor=\"#ebeff8\" class=\"center\"><dl class=\"city\">              <dt>北京天气预报</dt>              <dd><img src=\"http://www.15tianqi.com/Images/Uploads/city/101010100m.jpg\" width=\"180\" alt=\"北京天气预报\" /></dd>            </dl></td>          <td valign=\"top\" bgcolor=\"#ebeff8\" class=\"center\"><dl class=\"city\">              <dt>北京实时天气10:40</dt>              <dd>                <table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"2\">                  <tr>                    <td width=\"60%\" bgcolor=\"white\" valign=\"top\"><div style=\"width:80px;height:129px;background:#fff url(/images/Uploads/twc/tempbg.gif) no-repeat 10px 10px;float:left;\">                        <div style=\"width:80px;height:102px;background:transparent url(/images/Uploads/twc/temp.gif) no-repeat 40px 48px;\"> </div>                      </div>                      <div align=\"left\"><span class=\"f14 b l200\">气温</span><br />                        <span class=\"f16 b l200 green\">0℃</span><br />                        <br />                        <span class=\"l200\">湿度：17%<br />                        气压：</span></div></td>                    <td bgcolor=\"white\" align=\"center\" width=\"40%\" valign=\"top\"><div class=\"f14 b l200\">风向</div>                      <div style=\"width:98px;height:96px;line-height:98px;font-size:12px;font-weight:bold;background:#fff url(/images/Uploads/twc/e.gif) no-repeat;\">m/s</div>                      <div>东北风 2级</div></td>                  </tr>                </table>              </dd>            </dl></td>          <td valign=\"top\" bgcolor=\"#ebeff8\" width=\"170\" class=\"center\"><dl class=\"city\">              <dt>北京今日天气预报</dt>              <dd class=\"l200\"><img src=\"/Images/weather/d00.gif\" width=\"40\" height=\"40\" style=\"margin-top:5px\"/><img src=\"/Images/weather/n01.gif\" width=\"40\" height=\"40\" style=\"margin-top:5px\"/></dd>              <dd class=\"l200 f12\">晴间多云转晴转多云</dd>              <dd class=\"l200 f12\">-6°C~4°C              </dd>              <dd class=\"l200 f12\">风向：无持续风向</dd>              <dd class=\"l200 f12\">风力：微风</dd>            </dl></td>        </tr>      </table>    </div>	<div class=\"center\"><script type=\"text/javascript\"><!--google_ad_client = \"pub-7398492995893558\";google_ad_slot = \"2085200610\";google_ad_width = 728;google_ad_height = 90;//--></script><script type=\"text/javascript\"src=\"http://pagead2.googlesyndication.com/pagead/show_ads.js\"></script></div>    <div class=\"panel\">      <div class=\"mtitle\">        <div class=\"fleft\">北京15天天气预报</div>      </div>      <div class=\"mcon\">        <table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"yubao wkbx\" style=\"border-collapse:collapse;\" id=\"yubao\">           <thead>            <tr height=\"40\">              <th colspan=\"7\"> <!-- Baidu Button BEGIN -->    <div id=\"bdshare\" class=\"bdshare_t bds_tools get-codes-bdshare\">        <a class=\"bds_qzone\">QQ空间</a>        <a class=\"bds_tsina\">新浪微博</a>        <a class=\"bds_tqq\">腾讯微博</a>        <a class=\"bds_renren\">人人网</a>        <span class=\"bds_more\">更多</span>		<a class=\"shareCount\"></a>    </div><script type=\"text/javascript\" id=\"bdshare_js\" data=\"type=tools&amp;uid=679164\" ></script><script type=\"text/javascript\" id=\"bdshell_js\"></script><script type=\"text/javascript\">	document.getElementById(\"bdshell_js\").src = \"http://bdimg.share.baidu.com/static/js/shell_v2.js?cdnversion=\" + new Date().getHours();</script><!-- Baidu Button END --></th>             </tr>          </thead>          <thead>            <tr>              <th>日期</th>              <th colspan=\"2\">天气预报</th>              <th width=\"120\">气温</th>              <th width=\"120\">风向</th>              <th width=\"100\">风力</th>            </tr>          </thead>          <tbody>                      <tr>              <td width=\"100\" bgcolor=\"white\"><span class=\"Blue\">1月31日 星期六</span></td>              <td><img src=\"/Images/weather/d00.gif\" width=\"25\" height=\"25\" /><img src=\"/Images/weather/n01.gif\" width=\"25\" height=\"25\" /></td>              <td>晴间多云转晴转多云</td>              <td>-6°C~4°C</td>              <td>无持续风向</td>              <td>微风</td>            </tr>                      <tr>              <td width=\"100\" bgcolor=\"white\"><span class=\"Blue\">2月01日 星期日</span></td>              <td><img src=\"/Images/weather/d01.gif\" width=\"25\" height=\"25\" /><img src=\"/Images/weather/n01.gif\" width=\"25\" height=\"25\" /></td>              <td>多云</td>              <td>-6°C~4°C</td>              <td>无持续风向</td>              <td>微风</td>            </tr>                      <tr>              <td width=\"100\" bgcolor=\"white\"><span class=\"Blue\">2月02日 星期一</span></td>              <td><img src=\"/Images/weather/d01.gif\" width=\"25\" height=\"25\" /><img src=\"/Images/weather/n02.gif\" width=\"25\" height=\"25\" /></td>              <td>多云转阴</td>              <td>-5°C~4°C</td>              <td>无持续风向</td>              <td>微风</td>            </tr>                      <tr>              <td width=\"100\" bgcolor=\"white\"><span class=\"Blue\">2月03日 星期二</span></td>              <td><img src=\"/Images/weather/d02.gif\" width=\"25\" height=\"25\" /><img src=\"/Images/weather/n00.gif\" width=\"25\" height=\"25\" /></td>              <td>阴转晴</td>              <td>-3°C~7°C</td>              <td>无持续风向</td>              <td>微风转3-4级</td>            </tr>                      <tr>              <td width=\"100\" bgcolor=\"white\"><span class=\"Blue\">2月04日 星期三</span></td>              <td><img src=\"/Images/weather/d00.gif\" width=\"25\" height=\"25\" /><img src=\"/Images/weather/n00.gif\" width=\"25\" height=\"25\" /></td>              <td>晴</td>              <td>-5°C~7°C</td>              <td>北风</td>              <td>3-4级转微风</td>            </tr>                      <tr>              <td width=\"100\" bgcolor=\"white\"><span class=\"Blue\">2月05日 星期四</span></td>              <td><img src=\"/Images/weather/d00.gif\" width=\"25\" height=\"25\" /><img src=\"/Images/weather/n01.gif\" width=\"25\" height=\"25\" /></td>              <td>晴转多云</td>              <td>-4°C~7°C</td>              <td>无持续风向</td>              <td>微风</td>            </tr>                      <tr>              <td width=\"100\" bgcolor=\"white\"><span class=\"Blue\">2月06日 星期五</span></td>              <td><img src=\"/Images/weather/d01.gif\" width=\"25\" height=\"25\" /><img src=\"/Images/weather/n01.gif\" width=\"25\" height=\"25\" /></td>              <td>多云</td>              <td>-4°C~8°C</td>              <td>无持续风向</td>              <td>微风</td>            </tr>                      <tr>              <td width=\"100\" bgcolor=\"white\"><span class=\"Blue\">2月07日 星期六</span></td>              <td><img src=\"/Images/weather/d00.gif\" width=\"25\" height=\"25\" /><img src=\"/Images/weather/n01.gif\" width=\"25\" height=\"25\" /></td>              <td>晴转多云</td>              <td>-3°C~2°C</td>              <td>无持续风向转北风</td>              <td>微风</td>            </tr>                      <tr>              <td width=\"100\" bgcolor=\"white\"><span class=\"Blue\">2月08日 星期日</span></td>              <td><img src=\"/Images/weather/d01.gif\" width=\"25\" height=\"25\" /><img src=\"/Images/weather/n01.gif\" width=\"25\" height=\"25\" /></td>              <td>多云</td>              <td>-8°C~2°C</td>              <td>南风转西风</td>              <td>微风</td>            </tr>                      <tr>              <td width=\"100\" bgcolor=\"white\"><span class=\"Blue\">2月09日 星期一</span></td>              <td><img src=\"/Images/weather/d02.gif\" width=\"25\" height=\"25\" /><img src=\"/Images/weather/n14.gif\" width=\"25\" height=\"25\" /></td>              <td>阴转小雪</td>              <td>-15°C~2°C</td>              <td>东风转南风</td>              <td>微风</td>            </tr>                      <tr>              <td width=\"100\" bgcolor=\"white\"><span class=\"Blue\">2月10日 星期二</span></td>              <td><img src=\"/Images/weather/d01.gif\" width=\"25\" height=\"25\" /><img src=\"/Images/weather/n01.gif\" width=\"25\" height=\"25\" /></td>              <td>多云</td>              <td>-3°C~1°C</td>              <td>无持续风向</td>              <td>微风</td>            </tr>                      <tr>              <td width=\"100\" bgcolor=\"white\"><span class=\"Blue\">2月11日 星期三</span></td>              <td><img src=\"/Images/weather/d01.gif\" width=\"25\" height=\"25\" /><img src=\"/Images/weather/n01.gif\" width=\"25\" height=\"25\" /></td>              <td>多云</td>              <td>-3°C~1°C</td>              <td>无持续风向</td>              <td>微风</td>            </tr>                      <tr>              <td width=\"100\" bgcolor=\"white\"><span class=\"Blue\">2月12日 星期四</span></td>              <td><img src=\"/Images/weather/d01.gif\" width=\"25\" height=\"25\" /><img src=\"/Images/weather/n01.gif\" width=\"25\" height=\"25\" /></td>              <td>多云</td>              <td>-3°C~1°C</td>              <td>无持续风向</td>              <td>微风</td>            </tr>                      <tr>              <td width=\"100\" bgcolor=\"white\"><span class=\"Blue\">2月13日 星期五</span></td>              <td><img src=\"/Images/weather/d01.gif\" width=\"25\" height=\"25\" /><img src=\"/Images/weather/n01.gif\" width=\"25\" height=\"25\" /></td>              <td>多云</td>              <td>-3°C~1°C</td>              <td>无持续风向</td>              <td>微风</td>            </tr>                      <tr>              <td width=\"100\" bgcolor=\"white\"><span class=\"Blue\">2月14日 星期六</span></td>              <td><img src=\"/Images/weather/d01.gif\" width=\"25\" height=\"25\" /><img src=\"/Images/weather/n01.gif\" width=\"25\" height=\"25\" /></td>              <td>多云</td>              <td>-3°C~1°C</td>              <td>无持续风向</td>              <td>微风</td>            </tr>                      </tbody>        </table>      </div>         </div>    <div class=\"panel\" id=\"share\">      <div class=\"mtitle\">北京15天天气预报 - 分享</div>      <div class=\"mcon\">      <script language=\"javascript\">      	function SendFriend(){			var a=document.getElementById('tq7').value;			window.clipboardData.setData(\"Text\",a);			alert(a+'\n成功复制到剪切板');		}      </script>        <table border=\"0\" width=\"100%\">          <tr>            <td class=\"f14\">发送给QQ好友              <input type=\"button\" onclick='SendFriend()' value=\"马上发送\" class=\"but_L1\" onmouseout=\"this.className='but_L1'\" onmouseover=\"this.className='but_L2'\" />              <br />              <br />              <textarea style=\"width:400px;height:200px;\" class=\"p8 f14\" id=\"tq7\">★北京未来15天天气预报★                北京天气预报1月31日 星期六: 晴间多云转晴转多云，-6°C~4°C无持续风向，微风                北京天气预报2月01日 星期日: 多云，-6°C~4°C无持续风向，微风                北京天气预报2月02日 星期一: 多云转阴，-5°C~4°C无持续风向，微风                北京天气预报2月03日 星期二: 阴转晴，-3°C~7°C无持续风向，微风转3-4级                北京天气预报2月04日 星期三: 晴，-5°C~7°C北风，3-4级转微风                北京天气预报2月05日 星期四: 晴转多云，-4°C~7°C无持续风向，微风                北京天气预报2月06日 星期五: 多云，-4°C~8°C无持续风向，微风                北京天气预报2月07日 星期六: 晴转多云，-3°C~2°C无持续风向转北风，微风                北京天气预报2月08日 星期日: 多云，-8°C~2°C南风转西风，微风                北京天气预报2月09日 星期一: 阴转小雪，-15°C~2°C东风转南风，微风                北京天气预报2月10日 星期二: 多云，-3°C~1°C无持续风向，微风                北京天气预报2月11日 星期三: 多云，-3°C~1°C无持续风向，微风                北京天气预报2月12日 星期四: 多云，-3°C~1°C无持续风向，微风                北京天气预报2月13日 星期五: 多云，-3°C~1°C无持续风向，微风                北京天气预报2月14日 星期六: 多云，-3°C~1°C无持续风向，微风                    北京未来15天天气预报由15tianqi.com提供  http://www.15tianqi.com/beijing/</textarea></td>            <td width=\"310\" align=\"right\"><script type=\"text/javascript\"><!--google_ad_client = \"pub-7398492995893558\";/* 300x250, 创建于 11-2-18 */google_ad_slot = \"7344245188\";google_ad_width = 300;google_ad_height = 250;//--></script><script type=\"text/javascript\"src=\"http://pagead2.googlesyndication.com/pagead/show_ads.js\"></script></td>          </tr>        </table>      </div>    </div>    <div class=\"panel\">      <div class=\"mtitle\">北京天气预报 - 整点天气情况</div>      <div class=\"mcon center\">        <iframe width=\"650\" scrolling=\"no\" height=\"212\" frameborder=\"0\" style=\"border-bottom:1px #C2D0E7 solid;\" src=\"/weather_flash.php?id=101010100\"></iframe>      </div>    </div>    <div class=\"panel\">      <div class=\"mtitle\">北京同城天气预报 - 今天白天到夜间</div>      <div class=\"mcon center\">                <a href=\"http://www.15tianqi.com/daxingqu/\" title=\"大兴天气预报15天\" target=\"_blank\" class=\"zb\"><span class=\"area\">大兴</span><span class=\"img\"><img src=\"/Images/weather/d00.gif\" width=\"24\" height=\"24\" /><img src=\"/Images/weather/n01.gif\" width=\"24\" height=\"24\" /></span><span class=\"tmp\">-6°C~4°C</span></a>                <a href=\"http://www.15tianqi.com/chaoyangqu/\" title=\"朝阳天气预报15天\" target=\"_blank\" class=\"zb\"><span class=\"area\">朝阳</span><span class=\"img\"><img src=\"/Images/weather/d00.gif\" width=\"24\" height=\"24\" /><img src=\"/Images/weather/n01.gif\" width=\"24\" height=\"24\" /></span><span class=\"tmp\">-6°C~4°C</span></a>                <a href=\"http://www.15tianqi.com/beijingfengtai/\" title=\"丰台天气预报15天\" target=\"_blank\" class=\"zb\"><span class=\"area\">丰台</span><span class=\"img\"><img src=\"/Images/weather/d00.gif\" width=\"24\" height=\"24\" /><img src=\"/Images/weather/n01.gif\" width=\"24\" height=\"24\" /></span><span class=\"tmp\">-6°C~4°C</span></a>                <a href=\"http://www.15tianqi.com/shijingshan/\" title=\"石景山天气预报15天\" target=\"_blank\" class=\"zb\"><span class=\"area\">石景山</span><span class=\"img\"><img src=\"/Images/weather/d00.gif\" width=\"24\" height=\"24\" /><img src=\"/Images/weather/n01.gif\" width=\"24\" height=\"24\" /></span><span class=\"tmp\">-6°C~4°C</span></a>                <a href=\"http://www.15tianqi.com/haidian/\" title=\"海淀天气预报15天\" target=\"_blank\" class=\"zb\"><span class=\"area\">海淀</span><span class=\"img\"><img src=\"/Images/weather/d00.gif\" width=\"24\" height=\"24\" /><img src=\"/Images/weather/n01.gif\" width=\"24\" height=\"24\" /></span><span class=\"tmp\">-6°C~4°C</span></a>                <a href=\"http://www.15tianqi.com/mentougou/\" title=\"门头沟天气预报15天\" target=\"_blank\" class=\"zb\"><span class=\"area\">门头沟</span><span class=\"img\"><img src=\"/Images/weather/d00.gif\" width=\"24\" height=\"24\" /><img src=\"/Images/weather/n01.gif\" width=\"24\" height=\"24\" /></span><span class=\"tmp\">-6°C~4°C</span></a>                <a href=\"http://www.15tianqi.com/beijingfangshan/\" title=\"房山天气预报15天\" target=\"_blank\" class=\"zb\"><span class=\"area\">房山</span><span class=\"img\"><img src=\"/Images/weather/d00.gif\" width=\"24\" height=\"24\" /><img src=\"/Images/weather/n01.gif\" width=\"24\" height=\"24\" /></span><span class=\"tmp\">-7°C~4°C</span></a>                <a href=\"http://www.15tianqi.com/shunyi/\" title=\"顺义天气预报15天\" target=\"_blank\" class=\"zb\"><span class=\"area\">顺义</span><span class=\"img\"><img src=\"/Images/weather/d00.gif\" width=\"24\" height=\"24\" /><img src=\"/Images/weather/n01.gif\" width=\"24\" height=\"24\" /></span><span class=\"tmp\">-7°C~3°C</span></a>                <a href=\"http://www.15tianqi.com/changping/\" title=\"昌平天气预报15天\" target=\"_blank\" class=\"zb\"><span class=\"area\">昌平</span><span class=\"img\"><img src=\"/Images/weather/d00.gif\" width=\"24\" height=\"24\" /><img src=\"/Images/weather/n01.gif\" width=\"24\" height=\"24\" /></span><span class=\"tmp\">-6°C~4°C</span></a>                <a href=\"http://www.15tianqi.com/huairou/\" title=\"怀柔天气预报15天\" target=\"_blank\" class=\"zb\"><span class=\"area\">怀柔</span><span class=\"img\"><img src=\"/Images/weather/d00.gif\" width=\"24\" height=\"24\" /><img src=\"/Images/weather/n01.gif\" width=\"24\" height=\"24\" /></span><span class=\"tmp\">-11°C~3°C</span></a>                <a href=\"http://www.15tianqi.com/pinggu/\" title=\"平谷天气预报15天\" target=\"_blank\" class=\"zb\"><span class=\"area\">平谷</span><span class=\"img\"><img src=\"/Images/weather/d00.gif\" width=\"24\" height=\"24\" /><img src=\"/Images/weather/n01.gif\" width=\"24\" height=\"24\" /></span><span class=\"tmp\">-11°C~3°C</span></a>                <a href=\"http://www.15tianqi.com/miyun/\" title=\"密云天气预报15天\" target=\"_blank\" class=\"zb\"><span class=\"area\">密云</span><span class=\"img\"><img src=\"/Images/weather/d00.gif\" width=\"24\" height=\"24\" /><img src=\"/Images/weather/n01.gif\" width=\"24\" height=\"24\" /></span><span class=\"tmp\">-12°C~3°C</span></a>                <a href=\"http://www.15tianqi.com/yanqing/\" title=\"延庆天气预报15天\" target=\"_blank\" class=\"zb\"><span class=\"area\">延庆</span><span class=\"img\"><img src=\"/Images/weather/d00.gif\" width=\"24\" height=\"24\" /><img src=\"/Images/weather/n01.gif\" width=\"24\" height=\"24\" /></span><span class=\"tmp\">-13°C~1°C</span></a>                <div class=\"cboth\"></div>      </div>    </div>           <div class=\"cboth\"></div>  </div>  <div class=\"rightbox\">    <div class=\"r_panel\">      <div class=\"mtitle b\">重点城市天气预报</div>      <ul class=\"tl\">                <li><a href=\"http://www.15tianqi.com/shanghai/\" target=\"_blank\" title=\"上海天气预报15天\">上海</a></li>                <li><a href=\"http://www.15tianqi.com/beijing/\" target=\"_blank\" title=\"北京天气预报15天\">北京</a></li>                <li><a href=\"http://www.15tianqi.com/shanxixian/\" target=\"_blank\" title=\"西安天气预报15天\">西安</a></li>                <li><a href=\"http://www.15tianqi.com/zhengzhou/\" target=\"_blank\" title=\"郑州天气预报15天\">郑州</a></li>                <li><a href=\"http://www.15tianqi.com/guangzhou/\" target=\"_blank\" title=\"广州天气预报15天\">广州</a></li>                <li><a href=\"http://www.15tianqi.com/wuhan/\" target=\"_blank\" title=\"武汉天气预报15天\">武汉</a></li>                <li><a href=\"http://www.15tianqi.com/tianjin/\" target=\"_blank\" title=\"天津天气预报15天\">天津</a></li>                <li><a href=\"http://www.15tianqi.com/chongqing/\" target=\"_blank\" title=\"重庆天气预报15天\">重庆</a></li>                <li><a href=\"http://www.15tianqi.com/hefei/\" target=\"_blank\" title=\"合肥天气预报15天\">合肥</a></li>                <li><a href=\"http://www.15tianqi.com/hangzhou/\" target=\"_blank\" title=\"杭州天气预报15天\">杭州</a></li>                <li><a href=\"http://www.15tianqi.com/jiangsunanjing/\" target=\"_blank\" title=\"南京天气预报15天\">南京</a></li>                <li><a href=\"http://www.15tianqi.com/haerbin/\" target=\"_blank\" title=\"哈尔滨天气预报15天\">哈尔滨</a></li>                <li><a href=\"http://www.15tianqi.com/anhuihuangshan/\" target=\"_blank\" title=\"黄山天气预报15天\">黄山</a></li>                <li><a href=\"http://www.15tianqi.com/jinan/\" target=\"_blank\" title=\"济南天气预报15天\">济南</a></li>                <li><a href=\"http://www.15tianqi.com/qingdao/\" target=\"_blank\" title=\"青岛天气预报15天\">青岛</a></li>                <li><a href=\"http://www.15tianqi.com/taian/\" target=\"_blank\" title=\"泰安天气预报15天\">泰安</a></li>                <li><a href=\"http://www.15tianqi.com/shandonglinyi/\" target=\"_blank\" title=\"临沂天气预报15天\">临沂</a></li>                <li><a href=\"http://www.15tianqi.com/changchun/\" target=\"_blank\" title=\"长春天气预报15天\">长春</a></li>                <li><a href=\"http://www.15tianqi.com/changshashi/\" target=\"_blank\" title=\"长沙天气预报15天\">长沙</a></li>                <li><a href=\"http://www.15tianqi.com/jiangsusuzhou/\" target=\"_blank\" title=\"苏州天气预报15天\">苏州</a></li>                <li><a href=\"http://www.15tianqi.com/xinjiangwulumuqi/\" target=\"_blank\" title=\"乌鲁木齐天气预报15天\">乌鲁木齐</a></li>                <li><a href=\"http://www.15tianqi.com/heze/\" target=\"_blank\" title=\"菏泽天气预报15天\">菏泽</a></li>                <li><a href=\"http://www.15tianqi.com/shenyang/\" target=\"_blank\" title=\"沈阳天气预报15天\">沈阳</a></li>                <li><a href=\"http://www.15tianqi.com/sanya/\" target=\"_blank\" title=\"三亚天气预报15天\">三亚</a></li>              </ul>      <div class=\"cboth\"></div>    </div>    <div class=\"r_panel\" style=\"text-align:center\"><script type=\"text/javascript\"> /*200*200，创建于2010-7-11*/ var cpro_id = 'u102219';</script><script type=\"text/javascript\" src=\"http://cpro.baidu.com/cpro/ui/c.js\"></script>            <div class=\"cboth\"></div>    </div>    <div class=\"r_panel\" style=\"text-align:center\">      <script type=\"text/javascript\"><!--google_ad_client = \"pub-7398492995893558\";/* 160x600, 创建于 11-2-18 */google_ad_slot = \"9284785983\";google_ad_width = 160;google_ad_height = 600;//--></script><script type=\"text/javascript\"src=\"http://pagead2.googlesyndication.com/pagead/show_ads.js\"></script>      <div class=\"cboth\"></div>    </div>    <div class=\"r_panel\">      <div class=\"mtitle b\">天气预报各省市导航</div>      <ul class=\"tl\">                <li><a href=\"http://www.15tianqi.com/beijing/\" target=\"_blank\" title=\"北京\">北京市</a></li>                <li><a href=\"http://www.15tianqi.com/tianjin/\" target=\"_blank\" title=\"天津\">天津市</a></li>                <li><a href=\"http://www.15tianqi.com/hebei/\" target=\"_blank\" title=\"河北\">河北省</a></li>                <li><a href=\"http://www.15tianqi.com/shanxisheng/\" target=\"_blank\" title=\"山西\">山西省</a></li>                <li><a href=\"http://www.15tianqi.com/namenggu/\" target=\"_blank\" title=\"内蒙古\">内蒙古</a></li>                <li><a href=\"http://www.15tianqi.com/liaoning/\" target=\"_blank\" title=\"辽宁\">辽宁省</a></li>                <li><a href=\"http://www.15tianqi.com/jilinsheng/\" target=\"_blank\" title=\"吉林\">吉林省</a></li>                <li><a href=\"http://www.15tianqi.com/heilongjiang/\" target=\"_blank\" title=\"黑龙江\">黑龙江省</a></li>                <li><a href=\"http://www.15tianqi.com/shanghai/\" target=\"_blank\" title=\"上海\">上海市</a></li>                <li><a href=\"http://www.15tianqi.com/jiangsu/\" target=\"_blank\" title=\"江苏\">江苏省</a></li>                <li><a href=\"http://www.15tianqi.com/zhejiang/\" target=\"_blank\" title=\"浙江\">浙江省</a></li>                <li><a href=\"http://www.15tianqi.com/anhui/\" target=\"_blank\" title=\"安徽\">安徽省</a></li>                <li><a href=\"http://www.15tianqi.com/fujian/\" target=\"_blank\" title=\"福建\">福建省</a></li>                <li><a href=\"http://www.15tianqi.com/jiangxi/\" target=\"_blank\" title=\"江西\">江西省</a></li>                <li><a href=\"http://www.15tianqi.com/shandong/\" target=\"_blank\" title=\"山东\">山东省</a></li>                <li><a href=\"http://www.15tianqi.com/henansheng/\" target=\"_blank\" title=\"河南\">河南省</a></li>                <li><a href=\"http://www.15tianqi.com/hubei/\" target=\"_blank\" title=\"湖北\">湖北省</a></li>                <li><a href=\"http://www.15tianqi.com/hunan/\" target=\"_blank\" title=\"湖南\">湖南省</a></li>                <li><a href=\"http://www.15tianqi.com/guangdong/\" target=\"_blank\" title=\"广东\">广东省</a></li>                <li><a href=\"http://www.15tianqi.com/guangxi/\" target=\"_blank\" title=\"广西\">广西</a></li>                <li><a href=\"http://www.15tianqi.com/hainan/\" target=\"_blank\" title=\"海南\">海南省</a></li>                <li><a href=\"http://www.15tianqi.com/chongqing/\" target=\"_blank\" title=\"重庆\">重庆市</a></li>                <li><a href=\"http://www.15tianqi.com/sichuan/\" target=\"_blank\" title=\"四川\">四川省</a></li>                <li><a href=\"http://www.15tianqi.com/guizhou/\" target=\"_blank\" title=\"贵州\">贵州省</a></li>                <li><a href=\"http://www.15tianqi.com/yunnan/\" target=\"_blank\" title=\"云南\">云南省</a></li>                <li><a href=\"http://www.15tianqi.com/xicang/\" target=\"_blank\" title=\"西藏\">西藏</a></li>                <li><a href=\"http://www.15tianqi.com/shanxi/\" target=\"_blank\" title=\"陕西\">陕西省</a></li>                <li><a href=\"http://www.15tianqi.com/gansu/\" target=\"_blank\" title=\"甘肃\">甘肃省</a></li>                <li><a href=\"http://www.15tianqi.com/qinghai/\" target=\"_blank\" title=\"青海\">青海省</a></li>                <li><a href=\"http://www.15tianqi.com/ningxia/\" target=\"_blank\" title=\"宁夏\">宁夏</a></li>                <li><a href=\"http://www.15tianqi.com/xinjiangsheng/\" target=\"_blank\" title=\"新疆\">新疆</a></li>                <li><a href=\"http://www.15tianqi.com/hongkong/\" target=\"_blank\" title=\"香港\">香港</a></li>                <li><a href=\"http://www.15tianqi.com/macao/\" target=\"_blank\" title=\"澳门\">澳门</a></li>                <li><a href=\"http://www.15tianqi.com/taiwansheng/\" target=\"_blank\" title=\"台湾\">台湾</a></li>              </ul>      <div class=\"cboth\"></div>    </div>    <div class=\"r_panel\" style=\"text-align:center\">      <script type=\"text/javascript\">/*15天气160*600，创建于2014-3-12*/var cpro_id = \"u1486806\";</script><script src=\"http://cpro.baidustatic.com/cpro/ui/c.js\" type=\"text/javascript\"></script>      <div class=\"cboth\"></div>    </div>  </div>  <div class=\"cboth\"></div></div><div id=\"footer\">  &copy;2010 <a href=\"http://www.15tianqi.com\">天气预报15天</a> 闽ICP备11014391号-199  </div>  <!--广告代码--><script type=\"text/javascript\">/*120*270，创建于2011-4-7*/ var cpro_id = 'u436696';</script><script src=\"http://cpro.baidu.com/cpro/ui/f.js\" type=\"text/javascript\"></script><!--广告代码--><div style=\"display:none\"><script language=\"javascript\" src=\"http://count22.51yes.com/click.aspx?id=228064896&logo=1\" charset=\"gb2312\"></script></div><script type=\"text/javascript\">function tColor(o,a,b,c,d){ //表格换色//o表格名，a奇数行颜色，b偶数行颜色，c鼠标经过颜色，d点击颜色var t=document.getElementById(o).getElementsByTagName(\"tr\");for(var i=1;i<t.length;i++){t[i].style.backgroundColor=(t[i].sectionRowIndex%2==0)?a:b;t[i].onclick=function(){if(this.x!=\"1\" && d!=\"\"){this.x=\"1\";this.style.backgroundColor=d;}else{this.x=\"0\";this.style.backgroundColor=(this.sectionRowIndex%2==0)?a:b;}}t[i].onmouseover=function(){if(this.x!=\"1\" && c!=\"\")this.style.backgroundColor=c;}t[i].onmouseout=function(){if(this.x!=\"1\")this.style.backgroundColor=(this.sectionRowIndex%2==0)?a:b;}}}</script></body></html>";

		HtmlParser htmlParser = new HtmlParser(text);

		System.out.println(htmlParser.parse());

	}

}
