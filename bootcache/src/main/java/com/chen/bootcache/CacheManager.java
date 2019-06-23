package com.chen.bootcache;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Map;

public class CacheManager {
	File file = null;

	public List<Map<String, Object>> cachedatas(String tableName) {
		List<Map<String, Object>> lists = null;

		String cachePath = "./cachedatas/" + tableName + ".data";

		file = new File(cachePath);

		if (!file.getParentFile().exists()) {
			System.out.println("现在是第一次访问，没有缓存目录，都应该到数据库中去访问数据...");
			file.getParentFile().mkdirs();
			lists = callDB(tableName);
		} else {
			File[] fs = file.getParentFile().listFiles();

			for (File f : fs) {
				System.out.println(f.getName());
				if (f.getName().contains(tableName)) {
					System.out.println("有这个缓存策略文件...");
					System.out.println("缓存中有数据，直接从缓存中拿数据");
					ObjectInputStream objin = null;

					try {
						objin = new ObjectInputStream(new FileInputStream(file));
						lists = (List<Map<String, Object>>) objin.readObject();
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} finally {
						if (null != objin) {
							try {
								objin.close();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
						break;
					}

				} else {
					System.out.println("缓存目录下没有这个文件");
					lists = callDB(tableName);
				}

			}
		}

		return lists;

	}

	public List<Map<String, Object>> callDB(String tableName) {
		// 1.去数据库查询数据
				DB db = new DB();
				List<Map<String, Object>> lists = db.queryAllDatas(tableName);

				ObjectOutputStream objectOut = null;

				try
				{
					// 2.menu表的数据是不经常变化的，实施缓存
					objectOut = new ObjectOutputStream(new FileOutputStream(file));
					objectOut.writeObject(lists);

				} catch (FileNotFoundException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				} finally
				{
					if (null != objectOut)
					{
						try
						{
							objectOut.close();
						} catch (IOException e)
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
		return lists;
	}

}
