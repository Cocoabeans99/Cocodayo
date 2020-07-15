package coco;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

public class GetStatsMap {
    public static Map<String, Object> get() throws ParseException {

        Stats stats = new Stats();
        Map<String, Object> map = new HashMap<String, Object>();

        //遍历全部玩家
        String allplayers = GetStringFromJson.GetJson("C:\\Users\\Administrator\\Desktop\\KS\\KedamaAllStats.json");
        String uuid = "";
        int Banned = 0;
        int Active = 0;
        long[] months = GetDateArray.get();
        String[] MONTHS = new String[1];
        for (long month : months){
            if(month != 0){
                MONTHS = InsertArray.insert(MONTHS, TimeTrans.secondToTime(month).substring(0, 7));
            }
        }
        String[] MONTHS_LAST = new String[MONTHS.length - 1];
        for(int l = 0; l < MONTHS_LAST.length; l++){
            MONTHS_LAST[l] = MONTHS[l];
        }
        stats.setMONTHS_LAST(MONTHS_LAST);

        int[] PLAYERS = new int[MONTHS_LAST.length];
        for (int num: PLAYERS){
            num = 0;
        }

        //1.把字符串类型的json数组对象转化JSONArray
        JSONArray players=JSONArray.fromObject(allplayers);
        stats.setAllNum(players.size());

        //2、循环遍历这个数组
        for(int i=0;i<players.size();i++) {
            //3、把里面的对象转化为JSONObject
            JSONObject player = players.getJSONObject(i);
            // 4、属性名的方式获取到
            uuid = player.getString("uuid");
            System.out.println("C:\\Users\\Administrator\\Desktop\\KS\\Player\\Kedama-" + uuid + "-Stats.json");
            JSONObject PlayerJson=JSONObject.fromObject(GetStringFromJson.GetJson("C:\\Users\\Administrator\\Desktop\\KS\\Player\\Kedama-" + uuid + "-Stats.json"));
            JSONObject PlayerDataJson = PlayerJson.getJSONObject("data");
            if(PlayerDataJson.getBoolean("banned")){
                Banned++;
            }else if(Long.parseLong(PlayerDataJson.getString("time_last")) >= (System.currentTimeMillis() - 1209600000)){
                Active++;
            }
            for(int j =0; j < PLAYERS.length; j++){
                if(Long.parseLong(PlayerDataJson.getString("time_start")) <= months[j]){
                    PLAYERS[j]++;
                    break;
                }
            }
        }
        int[] PLAYERS_LAST = new int[PLAYERS.length];
        for (int num: PLAYERS_LAST){
            num = 0;
        }
        for(int m = 0; m < PLAYERS_LAST.length; m++){
            for (int n = 0; n <= m; n++){
                PLAYERS_LAST[m] = PLAYERS_LAST[m] + PLAYERS[n];
            }
        }
        int[] INCREASE = new int[PLAYERS_LAST.length];
        System.arraycopy(PLAYERS_LAST, 0, INCREASE, 0, PLAYERS_LAST.length);
        for (int x = 1; x < INCREASE.length; x++){
            INCREASE[x] = INCREASE[x] - PLAYERS_LAST[x-1];
        }

        stats.setPLAYERS_LAST(PLAYERS_LAST);
        stats.setINCREASE(INCREASE);
        stats.setActiveNum(Active);
        stats.setBannedNum(Banned);
        map.put("stats", stats);
        return map;
    }
}
