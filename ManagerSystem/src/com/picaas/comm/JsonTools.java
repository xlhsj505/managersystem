package com.picaas.comm;

import net.sf.json.JSONObject;;

public class JsonTools {
	/**����ת��json�ַ���*/
	public static String JsonToString(String msg, Object value) {
		JSONObject js = new JSONObject();
		js.put(msg, value);
		
		return js.toString();
	}
}
