package coco;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.text.ParseException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class main {

    public static String loadJson(String url) {
        StringBuilder json = new StringBuilder();
        try {
            URL urlObject = new URL(url);
            URLConnection uc = urlObject.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream(), "UTF-8"));
            String inputLine = null;
            while ((inputLine = in.readLine()) != null) {
                json.append(inputLine);
            }
            in.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return json.toString();
    }

    // 写所有玩家数据
    public static void GetAllStats(String data) {
        BufferedWriter writer = null;
        File file = new File("C:\\Users\\Administrator\\Desktop\\KS\\KedamaAllStats.json");
        if (!file.getParentFile().exists()) { // 如果父目录不存在，创建父目录
            file.getParentFile().mkdirs();
        }
        if (file.exists()) { // 如果已存在,删除旧文件
            file.delete();
        }
        // 如果文件不存在，则新建一个
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 写入
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, false), "UTF-8"));
            writer.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("获取全部玩家数据！");
    }

    // 写统计数据
    public static void GetStats(String data) {
        BufferedWriter writer = null;
        File file = new File("C:\\Users\\Administrator\\Desktop\\KS\\AllStats.json");
        if (!file.getParentFile().exists()) { // 如果父目录不存在，创建父目录
            file.getParentFile().mkdirs();
        }
        if (file.exists()) { // 如果已存在,删除旧文件
            file.delete();
        }
        // 如果文件不存在，则新建一个
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        // 写入
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, false), "UTF-8"));
            writer.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("获取Stat数据！");
    }



    // 写玩家数据
    public static void GetPlayerStats(String data) {

        String uuid = "";

        //1.把字符串类型的json数组对象转化JSONArray
        JSONArray players=JSONArray.fromObject(data);
        //2、循环遍历这个数组
        for(int i=0;i<players.size();i++){
            //3、把里面的对象转化为JSONObject
            JSONObject job = players.getJSONObject(i);
            // 4、属性名的方式获取到
            uuid = job.getString("uuid");
            System.out.println(uuid) ;
            String singledata = loadJson("https://stats.craft.moe/data/" + uuid + "/stats.json");

            BufferedWriter writer = null;
            File file = new File("C:\\Users\\Administrator\\Desktop\\KS\\Player\\Kedama-" + uuid + "-Stats.json");
            if (!file.getParentFile().exists()) { // 如果父目录不存在，创建父目录
                file.getParentFile().mkdirs();
            }
            if (file.exists()) { // 如果已存在,删除旧文件
                file.delete();
            }
            // 如果文件不存在，则新建一个
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            // 写入
            try {
                writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file, false), "UTF-8"));
                writer.write(singledata);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (writer != null) {
                        writer.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println("获取单个玩家数据！");
    }

	// 取数据
	public static String GetStringFromJson(String Path) {

		BufferedReader reader = null;
		String laststr = "";
		try {
			FileInputStream fileInputStream = new FileInputStream(Path);
			InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, "UTF-8");
			reader = new BufferedReader(inputStreamReader);
			String tempString = null;
			while ((tempString = reader.readLine()) != null) {
				laststr += tempString;
			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return laststr;
	}

    public static void main(String[] args) throws ParseException {
        // TODO 自动生成的方法存根
        String json;
        Map<String, Object> map = new HashMap<String, Object>();
        System.out.println("开始运行");
        json = loadJson("https://stats.craft.moe/data/players.json");
        System.out.println(json);
        GetAllStats(json);
        GetPlayerStats(json);

        map = GetStatsMap.get();
        JSONObject statjson = JSONObject.fromObject(map);
        GetStats(statjson.toString());

        GetMineData.get();
        GetRankData.get();
        GetPlayerName.get();
        System.out.println("运行完成");
    }
}

