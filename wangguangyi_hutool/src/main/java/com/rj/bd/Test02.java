package com.rj.bd;

import java.util.List;

import cn.hutool.core.lang.Console;
import cn.hutool.core.util.ReUtil;
import cn.hutool.http.HttpUtil;
/**
 * @desc   爬取资讯
 * @author 56991
 */
public class Test02 {
	
	public static void main(String[] args)
	{
		//请求列表页
		String listContent = HttpUtil.get("https://www.oschina.net/action/ajax/get_more_news_list?newsType=&p=2");
		//使用正则获取所有标题
		List<String> titles = ReUtil.findAll("<span class=\"text-ellipsis\">(.*?)</span>", listContent, 1);
		for (String title : titles) {
		    //打印标题
		    Console.log(title);
		}
	}
}
