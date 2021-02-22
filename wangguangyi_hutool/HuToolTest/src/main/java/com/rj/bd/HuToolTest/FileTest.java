package com.rj.bd.HuToolTest;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.WatchEvent;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileAppender;
import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.io.file.FileWriter;
import cn.hutool.core.io.watch.SimpleWatcher;
import cn.hutool.core.io.watch.WatchMonitor;
import cn.hutool.core.io.watch.Watcher;
import cn.hutool.core.lang.Console;

public class FileTest {
	public static void main(String[] args) {
		File file2 = FileUtil.file("d:/桌面/");
		//这里只监听文件或目录的修改事件
		WatchMonitor watchMonitor = WatchMonitor.create(file2, WatchMonitor.ENTRY_MODIFY);
		watchMonitor.setWatcher(new Watcher(){
		    @Override
		    public void onCreate(WatchEvent<?> event, Path currentPath) {
		        Object obj = event.context();
		        Console.log("创建：{}-> {}", currentPath, obj);
		    }

		    @Override
		    public void onModify(WatchEvent<?> event, Path currentPath) {
		        Object obj = event.context();
		        Console.log("修改：{}-> {}", currentPath, obj);
		    }

		    @Override
		    public void onDelete(WatchEvent<?> event, Path currentPath) {
		        Object obj = event.context();
		        Console.log("删除：{}-> {}", currentPath, obj);
		    }

		    @Override
		    public void onOverflow(WatchEvent<?> event, Path currentPath) {
		        Object obj = event.context();
		        Console.log("Overflow：{}-> {}", currentPath, obj);
		    }
		});

		//设置监听目录的最大深入，目录层级大于制定层级的变更将不被监听，默认只监听当前层级目录
		watchMonitor.setMaxDepth(3);
		//启动监听
		watchMonitor.start();

		
		WatchMonitor.createAll(file2, new SimpleWatcher(){
		    @Override
		    public void onModify(WatchEvent<?> event, Path currentPath) {
		        Console.log("EVENT modify");
		    }
		}).start();
		
		
		//默认UTF-8编码，可以在构造中传入第二个参数做为编码
		FileReader fileReader = new FileReader("d:/桌面/HuTool测试.txt");
		String result = fileReader.readString();
		//System.out.println(result);
		
		FileWriter writer = new FileWriter("d:/桌面/HuTool测试.txt");
		writer.write("添加文本",true);
		
		
		File file = new File("d:/桌面/HuTool测试.txt");
		FileAppender appender = new FileAppender(file, 16, true);
		appender.append("lolly1023");
		appender.append("追加");
		appender.append("成功");

		appender.flush();
		appender.toString();
		
		
		
	}
}
