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
				
				
				

				map.put("date", dateMatcher.group(1)+'��'+dateMatcher.group(2));
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

		String text = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\"><html xmlns=\"http://www.w3.org/1999/xhtml\"><head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" /><title>��������Ԥ��15��|��������Ԥ��15���ѯ - ����Ԥ��15��</title><link href=\"/css.css\" rel=\"stylesheet\" type=\"text/css\" /><link href=\"/tianqi.css\" rel=\"stylesheet\" type=\"text/css\" /><meta http-equiv=\"X-UA-Compatible\" content=\"IE=7\" /><meta name=\"keywords\" content=\"��������Ԥ��15�죬��������Ԥ��15���ѯ\" /><meta name=\"description\" content=\"��������Ԥ��15�죬�����������£���ѹ�����ʪ�ȣ���ˮ��Ԥ����ѯ\" /></head><body><div id=\"top\">  <div id=\"ulink\"><a href=\"http://www.15tianqi.com\">����Ԥ��15��</a> </div></div><div id=\"header\">  <h1>��������Ԥ��15��</h1>  <span class=\"pl8\"><a href=\"http://www.15tianqi.com\">����ҳ��</a>ȫ����ʡ������Ԥ��15���ѯ</span></div><div id=\"mainbox\">  <div class=\"leftbox\"> <div class=\"panel\">  <div class=\"mtitle\">    <div class=\"fleft\">����Ԥ��15���ѯ</div>  </div>  <div class=\"mcon center\">    <p>    <form action=\"/?action=Cha\" method=\"post\" class=\"f14\">      <input name=\"value\" id=\"q\" type=\"text\" size=\"18\" class=\"inp\" onmouseover=\"this.className='inp_2';\" onblur=\"this.className='inp'\" value=\"��������м�ƴ���������\" onfocus=\"this.value=''\"/>      <input type=\"submit\" value=\"��ѯ\" class=\"but_L1\" onmouseout=\"this.className='but_L1'\" onmouseover=\"this.className='but_L2'\" />    </form>    </p>    <p class=\"center\">���磺��ѯ�������������룺<b class=\"red\">bj</b>��<b class=\"red\">����</b>��<b class=\"red\">beijing</b></p>  </div></div>  <div style=\"text-align:center\"><script type=\"text/javascript\">/*15���� 760*90��������2014-3-12*/var cpro_id = \"u1486796\";</script><script src=\"http://cpro.baidustatic.com/cpro/ui/c.js\" type=\"text/javascript\"></script></div>    <div class=\"panel\">      <div class=\"mtitle\">        <div class=\"fleft\"><a href=\"http://www.15tianqi.com\" title=\"����Ԥ��15���ѯ\">����Ԥ��15���ѯ</a>&gt;��������Ԥ��<span class=\"gray\">(beijing)</span></div>      </div>      <table border=\"0\" width=\"100%\" cellpadding=\"0\" cellspacing=\"8\">        <tr>          <td valign=\"top\" width=\"192\" bgcolor=\"#ebeff8\" class=\"center\"><dl class=\"city\">              <dt>��������Ԥ��</dt>              <dd><img src=\"http://www.15tianqi.com/Images/Uploads/city/101010100m.jpg\" width=\"180\" alt=\"��������Ԥ��\" /></dd>            </dl></td>          <td valign=\"top\" bgcolor=\"#ebeff8\" class=\"center\"><dl class=\"city\">              <dt>����ʵʱ����10:40</dt>              <dd>                <table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"2\">                  <tr>                    <td width=\"60%\" bgcolor=\"white\" valign=\"top\"><div style=\"width:80px;height:129px;background:#fff url(/images/Uploads/twc/tempbg.gif) no-repeat 10px 10px;float:left;\">                        <div style=\"width:80px;height:102px;background:transparent url(/images/Uploads/twc/temp.gif) no-repeat 40px 48px;\"> </div>                      </div>                      <div align=\"left\"><span class=\"f14 b l200\">����</span><br />                        <span class=\"f16 b l200 green\">0��</span><br />                        <br />                        <span class=\"l200\">ʪ�ȣ�17%<br />                        ��ѹ��</span></div></td>                    <td bgcolor=\"white\" align=\"center\" width=\"40%\" valign=\"top\"><div class=\"f14 b l200\">����</div>                      <div style=\"width:98px;height:96px;line-height:98px;font-size:12px;font-weight:bold;background:#fff url(/images/Uploads/twc/e.gif) no-repeat;\">m/s</div>                      <div>������ 2��</div></td>                  </tr>                </table>              </dd>            </dl></td>          <td valign=\"top\" bgcolor=\"#ebeff8\" width=\"170\" class=\"center\"><dl class=\"city\">              <dt>������������Ԥ��</dt>              <dd class=\"l200\"><img src=\"/Images/weather/d00.gif\" width=\"40\" height=\"40\" style=\"margin-top:5px\"/><img src=\"/Images/weather/n01.gif\" width=\"40\" height=\"40\" style=\"margin-top:5px\"/></dd>              <dd class=\"l200 f12\">������ת��ת����</dd>              <dd class=\"l200 f12\">-6��C~4��C              </dd>              <dd class=\"l200 f12\">�����޳�������</dd>              <dd class=\"l200 f12\">������΢��</dd>            </dl></td>        </tr>      </table>    </div>	<div class=\"center\"><script type=\"text/javascript\"><!--google_ad_client = \"pub-7398492995893558\";google_ad_slot = \"2085200610\";google_ad_width = 728;google_ad_height = 90;//--></script><script type=\"text/javascript\"src=\"http://pagead2.googlesyndication.com/pagead/show_ads.js\"></script></div>    <div class=\"panel\">      <div class=\"mtitle\">        <div class=\"fleft\">����15������Ԥ��</div>      </div>      <div class=\"mcon\">        <table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"yubao wkbx\" style=\"border-collapse:collapse;\" id=\"yubao\">           <thead>            <tr height=\"40\">              <th colspan=\"7\"> <!-- Baidu Button BEGIN -->    <div id=\"bdshare\" class=\"bdshare_t bds_tools get-codes-bdshare\">        <a class=\"bds_qzone\">QQ�ռ�</a>        <a class=\"bds_tsina\">����΢��</a>        <a class=\"bds_tqq\">��Ѷ΢��</a>        <a class=\"bds_renren\">������</a>        <span class=\"bds_more\">����</span>		<a class=\"shareCount\"></a>    </div><script type=\"text/javascript\" id=\"bdshare_js\" data=\"type=tools&amp;uid=679164\" ></script><script type=\"text/javascript\" id=\"bdshell_js\"></script><script type=\"text/javascript\">	document.getElementById(\"bdshell_js\").src = \"http://bdimg.share.baidu.com/static/js/shell_v2.js?cdnversion=\" + new Date().getHours();</script><!-- Baidu Button END --></th>             </tr>          </thead>          <thead>            <tr>              <th>����</th>              <th colspan=\"2\">����Ԥ��</th>              <th width=\"120\">����</th>              <th width=\"120\">����</th>              <th width=\"100\">����</th>            </tr>          </thead>          <tbody>                      <tr>              <td width=\"100\" bgcolor=\"white\"><span class=\"Blue\">1��31�� ������</span></td>              <td><img src=\"/Images/weather/d00.gif\" width=\"25\" height=\"25\" /><img src=\"/Images/weather/n01.gif\" width=\"25\" height=\"25\" /></td>              <td>������ת��ת����</td>              <td>-6��C~4��C</td>              <td>�޳�������</td>              <td>΢��</td>            </tr>                      <tr>              <td width=\"100\" bgcolor=\"white\"><span class=\"Blue\">2��01�� ������</span></td>              <td><img src=\"/Images/weather/d01.gif\" width=\"25\" height=\"25\" /><img src=\"/Images/weather/n01.gif\" width=\"25\" height=\"25\" /></td>              <td>����</td>              <td>-6��C~4��C</td>              <td>�޳�������</td>              <td>΢��</td>            </tr>                      <tr>              <td width=\"100\" bgcolor=\"white\"><span class=\"Blue\">2��02�� ����һ</span></td>              <td><img src=\"/Images/weather/d01.gif\" width=\"25\" height=\"25\" /><img src=\"/Images/weather/n02.gif\" width=\"25\" height=\"25\" /></td>              <td>����ת��</td>              <td>-5��C~4��C</td>              <td>�޳�������</td>              <td>΢��</td>            </tr>                      <tr>              <td width=\"100\" bgcolor=\"white\"><span class=\"Blue\">2��03�� ���ڶ�</span></td>              <td><img src=\"/Images/weather/d02.gif\" width=\"25\" height=\"25\" /><img src=\"/Images/weather/n00.gif\" width=\"25\" height=\"25\" /></td>              <td>��ת��</td>              <td>-3��C~7��C</td>              <td>�޳�������</td>              <td>΢��ת3-4��</td>            </tr>                      <tr>              <td width=\"100\" bgcolor=\"white\"><span class=\"Blue\">2��04�� ������</span></td>              <td><img src=\"/Images/weather/d00.gif\" width=\"25\" height=\"25\" /><img src=\"/Images/weather/n00.gif\" width=\"25\" height=\"25\" /></td>              <td>��</td>              <td>-5��C~7��C</td>              <td>����</td>              <td>3-4��ת΢��</td>            </tr>                      <tr>              <td width=\"100\" bgcolor=\"white\"><span class=\"Blue\">2��05�� ������</span></td>              <td><img src=\"/Images/weather/d00.gif\" width=\"25\" height=\"25\" /><img src=\"/Images/weather/n01.gif\" width=\"25\" height=\"25\" /></td>              <td>��ת����</td>              <td>-4��C~7��C</td>              <td>�޳�������</td>              <td>΢��</td>            </tr>                      <tr>              <td width=\"100\" bgcolor=\"white\"><span class=\"Blue\">2��06�� ������</span></td>              <td><img src=\"/Images/weather/d01.gif\" width=\"25\" height=\"25\" /><img src=\"/Images/weather/n01.gif\" width=\"25\" height=\"25\" /></td>              <td>����</td>              <td>-4��C~8��C</td>              <td>�޳�������</td>              <td>΢��</td>            </tr>                      <tr>              <td width=\"100\" bgcolor=\"white\"><span class=\"Blue\">2��07�� ������</span></td>              <td><img src=\"/Images/weather/d00.gif\" width=\"25\" height=\"25\" /><img src=\"/Images/weather/n01.gif\" width=\"25\" height=\"25\" /></td>              <td>��ת����</td>              <td>-3��C~2��C</td>              <td>�޳�������ת����</td>              <td>΢��</td>            </tr>                      <tr>              <td width=\"100\" bgcolor=\"white\"><span class=\"Blue\">2��08�� ������</span></td>              <td><img src=\"/Images/weather/d01.gif\" width=\"25\" height=\"25\" /><img src=\"/Images/weather/n01.gif\" width=\"25\" height=\"25\" /></td>              <td>����</td>              <td>-8��C~2��C</td>              <td>�Ϸ�ת����</td>              <td>΢��</td>            </tr>                      <tr>              <td width=\"100\" bgcolor=\"white\"><span class=\"Blue\">2��09�� ����һ</span></td>              <td><img src=\"/Images/weather/d02.gif\" width=\"25\" height=\"25\" /><img src=\"/Images/weather/n14.gif\" width=\"25\" height=\"25\" /></td>              <td>��תСѩ</td>              <td>-15��C~2��C</td>              <td>����ת�Ϸ�</td>              <td>΢��</td>            </tr>                      <tr>              <td width=\"100\" bgcolor=\"white\"><span class=\"Blue\">2��10�� ���ڶ�</span></td>              <td><img src=\"/Images/weather/d01.gif\" width=\"25\" height=\"25\" /><img src=\"/Images/weather/n01.gif\" width=\"25\" height=\"25\" /></td>              <td>����</td>              <td>-3��C~1��C</td>              <td>�޳�������</td>              <td>΢��</td>            </tr>                      <tr>              <td width=\"100\" bgcolor=\"white\"><span class=\"Blue\">2��11�� ������</span></td>              <td><img src=\"/Images/weather/d01.gif\" width=\"25\" height=\"25\" /><img src=\"/Images/weather/n01.gif\" width=\"25\" height=\"25\" /></td>              <td>����</td>              <td>-3��C~1��C</td>              <td>�޳�������</td>              <td>΢��</td>            </tr>                      <tr>              <td width=\"100\" bgcolor=\"white\"><span class=\"Blue\">2��12�� ������</span></td>              <td><img src=\"/Images/weather/d01.gif\" width=\"25\" height=\"25\" /><img src=\"/Images/weather/n01.gif\" width=\"25\" height=\"25\" /></td>              <td>����</td>              <td>-3��C~1��C</td>              <td>�޳�������</td>              <td>΢��</td>            </tr>                      <tr>              <td width=\"100\" bgcolor=\"white\"><span class=\"Blue\">2��13�� ������</span></td>              <td><img src=\"/Images/weather/d01.gif\" width=\"25\" height=\"25\" /><img src=\"/Images/weather/n01.gif\" width=\"25\" height=\"25\" /></td>              <td>����</td>              <td>-3��C~1��C</td>              <td>�޳�������</td>              <td>΢��</td>            </tr>                      <tr>              <td width=\"100\" bgcolor=\"white\"><span class=\"Blue\">2��14�� ������</span></td>              <td><img src=\"/Images/weather/d01.gif\" width=\"25\" height=\"25\" /><img src=\"/Images/weather/n01.gif\" width=\"25\" height=\"25\" /></td>              <td>����</td>              <td>-3��C~1��C</td>              <td>�޳�������</td>              <td>΢��</td>            </tr>                      </tbody>        </table>      </div>         </div>    <div class=\"panel\" id=\"share\">      <div class=\"mtitle\">����15������Ԥ�� - ����</div>      <div class=\"mcon\">      <script language=\"javascript\">      	function SendFriend(){			var a=document.getElementById('tq7').value;			window.clipboardData.setData(\"Text\",a);			alert(a+'\n�ɹ����Ƶ����а�');		}      </script>        <table border=\"0\" width=\"100%\">          <tr>            <td class=\"f14\">���͸�QQ����              <input type=\"button\" onclick='SendFriend()' value=\"���Ϸ���\" class=\"but_L1\" onmouseout=\"this.className='but_L1'\" onmouseover=\"this.className='but_L2'\" />              <br />              <br />              <textarea style=\"width:400px;height:200px;\" class=\"p8 f14\" id=\"tq7\">�ﱱ��δ��15������Ԥ����                ��������Ԥ��1��31�� ������: ������ת��ת���ƣ�-6��C~4��C�޳�������΢��                ��������Ԥ��2��01�� ������: ���ƣ�-6��C~4��C�޳�������΢��                ��������Ԥ��2��02�� ����һ: ����ת����-5��C~4��C�޳�������΢��                ��������Ԥ��2��03�� ���ڶ�: ��ת�磬-3��C~7��C�޳�������΢��ת3-4��                ��������Ԥ��2��04�� ������: �磬-5��C~7��C���磬3-4��ת΢��                ��������Ԥ��2��05�� ������: ��ת���ƣ�-4��C~7��C�޳�������΢��                ��������Ԥ��2��06�� ������: ���ƣ�-4��C~8��C�޳�������΢��                ��������Ԥ��2��07�� ������: ��ת���ƣ�-3��C~2��C�޳�������ת���磬΢��                ��������Ԥ��2��08�� ������: ���ƣ�-8��C~2��C�Ϸ�ת���磬΢��                ��������Ԥ��2��09�� ����һ: ��תСѩ��-15��C~2��C����ת�Ϸ磬΢��                ��������Ԥ��2��10�� ���ڶ�: ���ƣ�-3��C~1��C�޳�������΢��                ��������Ԥ��2��11�� ������: ���ƣ�-3��C~1��C�޳�������΢��                ��������Ԥ��2��12�� ������: ���ƣ�-3��C~1��C�޳�������΢��                ��������Ԥ��2��13�� ������: ���ƣ�-3��C~1��C�޳�������΢��                ��������Ԥ��2��14�� ������: ���ƣ�-3��C~1��C�޳�������΢��                    ����δ��15������Ԥ����15tianqi.com�ṩ  http://www.15tianqi.com/beijing/</textarea></td>            <td width=\"310\" align=\"right\"><script type=\"text/javascript\"><!--google_ad_client = \"pub-7398492995893558\";/* 300x250, ������ 11-2-18 */google_ad_slot = \"7344245188\";google_ad_width = 300;google_ad_height = 250;//--></script><script type=\"text/javascript\"src=\"http://pagead2.googlesyndication.com/pagead/show_ads.js\"></script></td>          </tr>        </table>      </div>    </div>    <div class=\"panel\">      <div class=\"mtitle\">��������Ԥ�� - �����������</div>      <div class=\"mcon center\">        <iframe width=\"650\" scrolling=\"no\" height=\"212\" frameborder=\"0\" style=\"border-bottom:1px #C2D0E7 solid;\" src=\"/weather_flash.php?id=101010100\"></iframe>      </div>    </div>    <div class=\"panel\">      <div class=\"mtitle\">����ͬ������Ԥ�� - ������쵽ҹ��</div>      <div class=\"mcon center\">                <a href=\"http://www.15tianqi.com/daxingqu/\" title=\"��������Ԥ��15��\" target=\"_blank\" class=\"zb\"><span class=\"area\">����</span><span class=\"img\"><img src=\"/Images/weather/d00.gif\" width=\"24\" height=\"24\" /><img src=\"/Images/weather/n01.gif\" width=\"24\" height=\"24\" /></span><span class=\"tmp\">-6��C~4��C</span></a>                <a href=\"http://www.15tianqi.com/chaoyangqu/\" title=\"��������Ԥ��15��\" target=\"_blank\" class=\"zb\"><span class=\"area\">����</span><span class=\"img\"><img src=\"/Images/weather/d00.gif\" width=\"24\" height=\"24\" /><img src=\"/Images/weather/n01.gif\" width=\"24\" height=\"24\" /></span><span class=\"tmp\">-6��C~4��C</span></a>                <a href=\"http://www.15tianqi.com/beijingfengtai/\" title=\"��̨����Ԥ��15��\" target=\"_blank\" class=\"zb\"><span class=\"area\">��̨</span><span class=\"img\"><img src=\"/Images/weather/d00.gif\" width=\"24\" height=\"24\" /><img src=\"/Images/weather/n01.gif\" width=\"24\" height=\"24\" /></span><span class=\"tmp\">-6��C~4��C</span></a>                <a href=\"http://www.15tianqi.com/shijingshan/\" title=\"ʯ��ɽ����Ԥ��15��\" target=\"_blank\" class=\"zb\"><span class=\"area\">ʯ��ɽ</span><span class=\"img\"><img src=\"/Images/weather/d00.gif\" width=\"24\" height=\"24\" /><img src=\"/Images/weather/n01.gif\" width=\"24\" height=\"24\" /></span><span class=\"tmp\">-6��C~4��C</span></a>                <a href=\"http://www.15tianqi.com/haidian/\" title=\"��������Ԥ��15��\" target=\"_blank\" class=\"zb\"><span class=\"area\">����</span><span class=\"img\"><img src=\"/Images/weather/d00.gif\" width=\"24\" height=\"24\" /><img src=\"/Images/weather/n01.gif\" width=\"24\" height=\"24\" /></span><span class=\"tmp\">-6��C~4��C</span></a>                <a href=\"http://www.15tianqi.com/mentougou/\" title=\"��ͷ������Ԥ��15��\" target=\"_blank\" class=\"zb\"><span class=\"area\">��ͷ��</span><span class=\"img\"><img src=\"/Images/weather/d00.gif\" width=\"24\" height=\"24\" /><img src=\"/Images/weather/n01.gif\" width=\"24\" height=\"24\" /></span><span class=\"tmp\">-6��C~4��C</span></a>                <a href=\"http://www.15tianqi.com/beijingfangshan/\" title=\"��ɽ����Ԥ��15��\" target=\"_blank\" class=\"zb\"><span class=\"area\">��ɽ</span><span class=\"img\"><img src=\"/Images/weather/d00.gif\" width=\"24\" height=\"24\" /><img src=\"/Images/weather/n01.gif\" width=\"24\" height=\"24\" /></span><span class=\"tmp\">-7��C~4��C</span></a>                <a href=\"http://www.15tianqi.com/shunyi/\" title=\"˳������Ԥ��15��\" target=\"_blank\" class=\"zb\"><span class=\"area\">˳��</span><span class=\"img\"><img src=\"/Images/weather/d00.gif\" width=\"24\" height=\"24\" /><img src=\"/Images/weather/n01.gif\" width=\"24\" height=\"24\" /></span><span class=\"tmp\">-7��C~3��C</span></a>                <a href=\"http://www.15tianqi.com/changping/\" title=\"��ƽ����Ԥ��15��\" target=\"_blank\" class=\"zb\"><span class=\"area\">��ƽ</span><span class=\"img\"><img src=\"/Images/weather/d00.gif\" width=\"24\" height=\"24\" /><img src=\"/Images/weather/n01.gif\" width=\"24\" height=\"24\" /></span><span class=\"tmp\">-6��C~4��C</span></a>                <a href=\"http://www.15tianqi.com/huairou/\" title=\"��������Ԥ��15��\" target=\"_blank\" class=\"zb\"><span class=\"area\">����</span><span class=\"img\"><img src=\"/Images/weather/d00.gif\" width=\"24\" height=\"24\" /><img src=\"/Images/weather/n01.gif\" width=\"24\" height=\"24\" /></span><span class=\"tmp\">-11��C~3��C</span></a>                <a href=\"http://www.15tianqi.com/pinggu/\" title=\"ƽ������Ԥ��15��\" target=\"_blank\" class=\"zb\"><span class=\"area\">ƽ��</span><span class=\"img\"><img src=\"/Images/weather/d00.gif\" width=\"24\" height=\"24\" /><img src=\"/Images/weather/n01.gif\" width=\"24\" height=\"24\" /></span><span class=\"tmp\">-11��C~3��C</span></a>                <a href=\"http://www.15tianqi.com/miyun/\" title=\"��������Ԥ��15��\" target=\"_blank\" class=\"zb\"><span class=\"area\">����</span><span class=\"img\"><img src=\"/Images/weather/d00.gif\" width=\"24\" height=\"24\" /><img src=\"/Images/weather/n01.gif\" width=\"24\" height=\"24\" /></span><span class=\"tmp\">-12��C~3��C</span></a>                <a href=\"http://www.15tianqi.com/yanqing/\" title=\"��������Ԥ��15��\" target=\"_blank\" class=\"zb\"><span class=\"area\">����</span><span class=\"img\"><img src=\"/Images/weather/d00.gif\" width=\"24\" height=\"24\" /><img src=\"/Images/weather/n01.gif\" width=\"24\" height=\"24\" /></span><span class=\"tmp\">-13��C~1��C</span></a>                <div class=\"cboth\"></div>      </div>    </div>           <div class=\"cboth\"></div>  </div>  <div class=\"rightbox\">    <div class=\"r_panel\">      <div class=\"mtitle b\">�ص��������Ԥ��</div>      <ul class=\"tl\">                <li><a href=\"http://www.15tianqi.com/shanghai/\" target=\"_blank\" title=\"�Ϻ�����Ԥ��15��\">�Ϻ�</a></li>                <li><a href=\"http://www.15tianqi.com/beijing/\" target=\"_blank\" title=\"��������Ԥ��15��\">����</a></li>                <li><a href=\"http://www.15tianqi.com/shanxixian/\" target=\"_blank\" title=\"��������Ԥ��15��\">����</a></li>                <li><a href=\"http://www.15tianqi.com/zhengzhou/\" target=\"_blank\" title=\"֣������Ԥ��15��\">֣��</a></li>                <li><a href=\"http://www.15tianqi.com/guangzhou/\" target=\"_blank\" title=\"��������Ԥ��15��\">����</a></li>                <li><a href=\"http://www.15tianqi.com/wuhan/\" target=\"_blank\" title=\"�人����Ԥ��15��\">�人</a></li>                <li><a href=\"http://www.15tianqi.com/tianjin/\" target=\"_blank\" title=\"�������Ԥ��15��\">���</a></li>                <li><a href=\"http://www.15tianqi.com/chongqing/\" target=\"_blank\" title=\"��������Ԥ��15��\">����</a></li>                <li><a href=\"http://www.15tianqi.com/hefei/\" target=\"_blank\" title=\"�Ϸ�����Ԥ��15��\">�Ϸ�</a></li>                <li><a href=\"http://www.15tianqi.com/hangzhou/\" target=\"_blank\" title=\"��������Ԥ��15��\">����</a></li>                <li><a href=\"http://www.15tianqi.com/jiangsunanjing/\" target=\"_blank\" title=\"�Ͼ�����Ԥ��15��\">�Ͼ�</a></li>                <li><a href=\"http://www.15tianqi.com/haerbin/\" target=\"_blank\" title=\"����������Ԥ��15��\">������</a></li>                <li><a href=\"http://www.15tianqi.com/anhuihuangshan/\" target=\"_blank\" title=\"��ɽ����Ԥ��15��\">��ɽ</a></li>                <li><a href=\"http://www.15tianqi.com/jinan/\" target=\"_blank\" title=\"��������Ԥ��15��\">����</a></li>                <li><a href=\"http://www.15tianqi.com/qingdao/\" target=\"_blank\" title=\"�ൺ����Ԥ��15��\">�ൺ</a></li>                <li><a href=\"http://www.15tianqi.com/taian/\" target=\"_blank\" title=\"̩������Ԥ��15��\">̩��</a></li>                <li><a href=\"http://www.15tianqi.com/shandonglinyi/\" target=\"_blank\" title=\"��������Ԥ��15��\">����</a></li>                <li><a href=\"http://www.15tianqi.com/changchun/\" target=\"_blank\" title=\"��������Ԥ��15��\">����</a></li>                <li><a href=\"http://www.15tianqi.com/changshashi/\" target=\"_blank\" title=\"��ɳ����Ԥ��15��\">��ɳ</a></li>                <li><a href=\"http://www.15tianqi.com/jiangsusuzhou/\" target=\"_blank\" title=\"��������Ԥ��15��\">����</a></li>                <li><a href=\"http://www.15tianqi.com/xinjiangwulumuqi/\" target=\"_blank\" title=\"��³ľ������Ԥ��15��\">��³ľ��</a></li>                <li><a href=\"http://www.15tianqi.com/heze/\" target=\"_blank\" title=\"��������Ԥ��15��\">����</a></li>                <li><a href=\"http://www.15tianqi.com/shenyang/\" target=\"_blank\" title=\"��������Ԥ��15��\">����</a></li>                <li><a href=\"http://www.15tianqi.com/sanya/\" target=\"_blank\" title=\"��������Ԥ��15��\">����</a></li>              </ul>      <div class=\"cboth\"></div>    </div>    <div class=\"r_panel\" style=\"text-align:center\"><script type=\"text/javascript\"> /*200*200��������2010-7-11*/ var cpro_id = 'u102219';</script><script type=\"text/javascript\" src=\"http://cpro.baidu.com/cpro/ui/c.js\"></script>            <div class=\"cboth\"></div>    </div>    <div class=\"r_panel\" style=\"text-align:center\">      <script type=\"text/javascript\"><!--google_ad_client = \"pub-7398492995893558\";/* 160x600, ������ 11-2-18 */google_ad_slot = \"9284785983\";google_ad_width = 160;google_ad_height = 600;//--></script><script type=\"text/javascript\"src=\"http://pagead2.googlesyndication.com/pagead/show_ads.js\"></script>      <div class=\"cboth\"></div>    </div>    <div class=\"r_panel\">      <div class=\"mtitle b\">����Ԥ����ʡ�е���</div>      <ul class=\"tl\">                <li><a href=\"http://www.15tianqi.com/beijing/\" target=\"_blank\" title=\"����\">������</a></li>                <li><a href=\"http://www.15tianqi.com/tianjin/\" target=\"_blank\" title=\"���\">�����</a></li>                <li><a href=\"http://www.15tianqi.com/hebei/\" target=\"_blank\" title=\"�ӱ�\">�ӱ�ʡ</a></li>                <li><a href=\"http://www.15tianqi.com/shanxisheng/\" target=\"_blank\" title=\"ɽ��\">ɽ��ʡ</a></li>                <li><a href=\"http://www.15tianqi.com/namenggu/\" target=\"_blank\" title=\"���ɹ�\">���ɹ�</a></li>                <li><a href=\"http://www.15tianqi.com/liaoning/\" target=\"_blank\" title=\"����\">����ʡ</a></li>                <li><a href=\"http://www.15tianqi.com/jilinsheng/\" target=\"_blank\" title=\"����\">����ʡ</a></li>                <li><a href=\"http://www.15tianqi.com/heilongjiang/\" target=\"_blank\" title=\"������\">������ʡ</a></li>                <li><a href=\"http://www.15tianqi.com/shanghai/\" target=\"_blank\" title=\"�Ϻ�\">�Ϻ���</a></li>                <li><a href=\"http://www.15tianqi.com/jiangsu/\" target=\"_blank\" title=\"����\">����ʡ</a></li>                <li><a href=\"http://www.15tianqi.com/zhejiang/\" target=\"_blank\" title=\"�㽭\">�㽭ʡ</a></li>                <li><a href=\"http://www.15tianqi.com/anhui/\" target=\"_blank\" title=\"����\">����ʡ</a></li>                <li><a href=\"http://www.15tianqi.com/fujian/\" target=\"_blank\" title=\"����\">����ʡ</a></li>                <li><a href=\"http://www.15tianqi.com/jiangxi/\" target=\"_blank\" title=\"����\">����ʡ</a></li>                <li><a href=\"http://www.15tianqi.com/shandong/\" target=\"_blank\" title=\"ɽ��\">ɽ��ʡ</a></li>                <li><a href=\"http://www.15tianqi.com/henansheng/\" target=\"_blank\" title=\"����\">����ʡ</a></li>                <li><a href=\"http://www.15tianqi.com/hubei/\" target=\"_blank\" title=\"����\">����ʡ</a></li>                <li><a href=\"http://www.15tianqi.com/hunan/\" target=\"_blank\" title=\"����\">����ʡ</a></li>                <li><a href=\"http://www.15tianqi.com/guangdong/\" target=\"_blank\" title=\"�㶫\">�㶫ʡ</a></li>                <li><a href=\"http://www.15tianqi.com/guangxi/\" target=\"_blank\" title=\"����\">����</a></li>                <li><a href=\"http://www.15tianqi.com/hainan/\" target=\"_blank\" title=\"����\">����ʡ</a></li>                <li><a href=\"http://www.15tianqi.com/chongqing/\" target=\"_blank\" title=\"����\">������</a></li>                <li><a href=\"http://www.15tianqi.com/sichuan/\" target=\"_blank\" title=\"�Ĵ�\">�Ĵ�ʡ</a></li>                <li><a href=\"http://www.15tianqi.com/guizhou/\" target=\"_blank\" title=\"����\">����ʡ</a></li>                <li><a href=\"http://www.15tianqi.com/yunnan/\" target=\"_blank\" title=\"����\">����ʡ</a></li>                <li><a href=\"http://www.15tianqi.com/xicang/\" target=\"_blank\" title=\"����\">����</a></li>                <li><a href=\"http://www.15tianqi.com/shanxi/\" target=\"_blank\" title=\"����\">����ʡ</a></li>                <li><a href=\"http://www.15tianqi.com/gansu/\" target=\"_blank\" title=\"����\">����ʡ</a></li>                <li><a href=\"http://www.15tianqi.com/qinghai/\" target=\"_blank\" title=\"�ຣ\">�ຣʡ</a></li>                <li><a href=\"http://www.15tianqi.com/ningxia/\" target=\"_blank\" title=\"����\">����</a></li>                <li><a href=\"http://www.15tianqi.com/xinjiangsheng/\" target=\"_blank\" title=\"�½�\">�½�</a></li>                <li><a href=\"http://www.15tianqi.com/hongkong/\" target=\"_blank\" title=\"���\">���</a></li>                <li><a href=\"http://www.15tianqi.com/macao/\" target=\"_blank\" title=\"����\">����</a></li>                <li><a href=\"http://www.15tianqi.com/taiwansheng/\" target=\"_blank\" title=\"̨��\">̨��</a></li>              </ul>      <div class=\"cboth\"></div>    </div>    <div class=\"r_panel\" style=\"text-align:center\">      <script type=\"text/javascript\">/*15����160*600��������2014-3-12*/var cpro_id = \"u1486806\";</script><script src=\"http://cpro.baidustatic.com/cpro/ui/c.js\" type=\"text/javascript\"></script>      <div class=\"cboth\"></div>    </div>  </div>  <div class=\"cboth\"></div></div><div id=\"footer\">  &copy;2010 <a href=\"http://www.15tianqi.com\">����Ԥ��15��</a> ��ICP��11014391��-199  </div>  <!--������--><script type=\"text/javascript\">/*120*270��������2011-4-7*/ var cpro_id = 'u436696';</script><script src=\"http://cpro.baidu.com/cpro/ui/f.js\" type=\"text/javascript\"></script><!--������--><div style=\"display:none\"><script language=\"javascript\" src=\"http://count22.51yes.com/click.aspx?id=228064896&logo=1\" charset=\"gb2312\"></script></div><script type=\"text/javascript\">function tColor(o,a,b,c,d){ //����ɫ//o��������a��������ɫ��bż������ɫ��c��꾭����ɫ��d�����ɫvar t=document.getElementById(o).getElementsByTagName(\"tr\");for(var i=1;i<t.length;i++){t[i].style.backgroundColor=(t[i].sectionRowIndex%2==0)?a:b;t[i].onclick=function(){if(this.x!=\"1\" && d!=\"\"){this.x=\"1\";this.style.backgroundColor=d;}else{this.x=\"0\";this.style.backgroundColor=(this.sectionRowIndex%2==0)?a:b;}}t[i].onmouseover=function(){if(this.x!=\"1\" && c!=\"\")this.style.backgroundColor=c;}t[i].onmouseout=function(){if(this.x!=\"1\")this.style.backgroundColor=(this.sectionRowIndex%2==0)?a:b;}}}</script></body></html>";

		HtmlParser htmlParser = new HtmlParser(text);

		System.out.println(htmlParser.parse());

	}

}