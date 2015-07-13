package cn.utils.http;

import android.annotation.SuppressLint;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

public class GetPostUtil {
	//post请求
	public String sendPost(String url, String params) {
		System.gc(); // /垃圾回收
		URL postUrl;
		DataOutputStream out = null;
		BufferedReader reader = null;
		String result = "";
		try {
			postUrl = new URL(url);
			// 打开连接
			HttpURLConnection connection = (HttpURLConnection) postUrl
					.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod("POST");
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(true);
			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");
			connection.connect();
			out = new DataOutputStream(connection.getOutputStream());
			String prix = params.substring(0, params.indexOf("{"));
			String what = params
					.substring(params.indexOf("{"), params.length());
			// String content = "" + "&userinfo=" + URLEncoder.encode("久酷博客",
			// "utf-8");
			String content = "" + "&" + prix + URLEncoder.encode(what, "utf-8");
			out.writeBytes(content);
			out.flush();
			out.close(); // flush and close
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"));// 设置编码,否则中文乱码
			String line = "";
			while ((line = reader.readLine()) != null) {
				// line = new String(line.getBytes(), "utf-8");
				result += "\n" + line;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (reader != null) {
					reader.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
}


//get请求(参数都写在串中，这是reslset的规则)
public static String sendGet(String url, String params)
{
	String result = "";
	BufferedReader in = null;
	try
	{    //！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！！
		//拼串时注意
		//String urlName = url + "?" + params;
		String urlName = url;
		URL realUrl = new URL(urlName);
		// 打开和URL之间的连接
		URLConnection conn = realUrl.openConnection();
		// 设置通用的请求属性
		conn.setRequestProperty("accept", "*/*");
		conn.setRequestProperty("connection", "Keep-Alive");
		conn.setRequestProperty("user-agent",
			"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
		// 建立实际的连接
		conn.connect();  //
		// 获取所有响应头字段
		Map<String, List<String>> map = conn.getHeaderFields();
		// 遍历所有的响应头字段
		for (String key : map.keySet())
		{
//			System.out.println(key + "--->" + map.get(key));
		}
		// 定义BufferedReader输入流来读取URL的响应
		in = new BufferedReader(
			new InputStreamReader(conn.getInputStream(),"utf-8"));
		String line;
		while ((line = in.readLine()) != null)
		{
			result += "\n" + line;
		}
	}
	catch (Exception e)
	{
		System.out.println("发送GET请求出现异常！" + e);
		e.printStackTrace();
	}
	// 使用finally块来关闭输入流
	finally
	{
		try
		{
			if (in != null)
			{
				in.close();
			}
		}
		catch (IOException ex)
		{
			ex.printStackTrace();
		}
	}
	return result;
}
}
