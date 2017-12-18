package com.yunengzhe.PVMTS.util;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.config.RequestConfig.Builder;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.yunengzhe.PVMTS.util.http.ResponseData;

public class ApiClient {

	public static final int connTimeout = 10000;
	public static final int readTimeout = 80000;
	public static final String charset = "UTF-8";
	private static HttpClient client = null;

	static {
		PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
		cm.setMaxTotal(128);
		cm.setDefaultMaxPerRoute(128);
		client = HttpClients.custom().setConnectionManager(cm).build();
	}

	private static final Logger logger = LoggerFactory.getLogger(ApiClient.class);
	public static long REFRESH_TAG = 600; // 秒
 
	public static ResponseData postJsonData(String url, String params) { 
	 
		return postJsonData(url, params, null);
	}
	public static ResponseData postJsonData(String url, String params, Map<String, String> headers) { 
		if (headers == null) {
			headers = new HashMap<String, String>();
		} 
		return postJsonData(url, params, headers, connTimeout, readTimeout);
	}
	public static ResponseData postJsonData(String url, String data, Map<String, String> headers,
			Integer connTimeout, Integer readTimeout) {

		if (headers == null) {
			headers = new HashMap<String, String>();
		}
	 
		HttpClient client = null;
		ResponseData resData = new ResponseData();
		HttpPost post = new HttpPost(url);
		try {
			String jsonData = data;
			StringEntity entity = new StringEntity(jsonData, "utf-8");// 解决中文乱码问题
			entity.setContentEncoding("UTF-8");
			entity.setContentType("application/json");
			post.setEntity(entity);

			if (headers != null && !headers.isEmpty()) {
				for (Entry<String, String> entry : headers.entrySet()) {
					post.addHeader(entry.getKey(), entry.getValue());
				}
			}
			// 设置参数
			Builder customReqConf = RequestConfig.custom();
			if (connTimeout != null) {
				customReqConf.setConnectTimeout(connTimeout);
			}
			if (readTimeout != null) {
				customReqConf.setSocketTimeout(readTimeout);
			}
			post.setConfig(customReqConf.build());
			HttpResponse res = null;
			if (url.startsWith("https")) {
				// 执行 Https 请求.
				client = createSSLInsecureClient();
				res = client.execute(post);
			} else {
				// 执行 Http 请求.
				client = ApiClient.client;
				res = client.execute(post);
			}
			// if(res.getStatusLine().getStatusCode()==HttpServletHttpServletResponse.SC_UNAUTHORIZED)
			// {
			// if(FORMAT_AJAX.equals(format))
			// {
			// throw new UserNotFoundAjaxException("用户登录过期");
			// }
			// throw new UserNotFoundException();
			// }
			resData.setCode(res.getStatusLine().getStatusCode());
			resData.setBackData(IOUtils.toString(res.getEntity().getContent(), "UTF-8"));
		 
		} catch (Exception e) {
			resData.setCode(500);
			resData.setBackData("请求其他系统失败");
		} finally {
			post.releaseConnection();
			if (url.startsWith("https") && client != null && client instanceof CloseableHttpClient) {
				try {
					((CloseableHttpClient) client).close();
				} catch (IOException e) {
					resData.setCode(500);
					resData.setBackData("请求其他系统失败");
					e.printStackTrace();
				}
			}
		}

		logger.info(resData.getBackData());
		return resData;
	}

	public static ResponseData post(String url, Map<String, String> params, Map<String, String> headers) {
		return post(url, params, headers, connTimeout, readTimeout);
	}

	public static ResponseData post(String url, Map<String, String> params, Map<String, String> headers,
			HttpServletRequest request) {

		String token = getToken(request);
		if (headers == null) {
			headers = new HashMap<String, String>();
		}
		if (!StringUtils.isEmpty(token)) {
			headers.put("Authorization", "bearer " + token);
		}
		return post(url, params, headers, connTimeout, readTimeout);
	}

	public static ResponseData postJson(String url, Map<String, String> params, Map<String, String> headers,
			HttpServletRequest request) {
		String token = getToken(request);
		if (headers == null) {
			headers = new HashMap<String, String>();
		}
		if (!StringUtils.isEmpty(token)) {
			headers.put("Authorization", "bearer " + token);
		}
		return postJson(url, params, headers, connTimeout, readTimeout);
	}

	public static String getToken(HttpServletRequest request) {
		if (request == null)
			return "";

		String token = "";
		String auth = request.getHeader("Authorization");
		if (StringUtils.isNotBlank(auth)) {
			String tokenLis[] = StringUtils.split(auth, " ");
			if (tokenLis.length == 2) {
				token = tokenLis[1];
			}
		}
		return token;
	}

	public static ResponseData postJson(String url, Map<String, String> params, Map<String, String> headers) {

		return postJson(url, params, headers, connTimeout, readTimeout);
	}

	public static ResponseData get(String url, Map<String, String> headers, HttpServletRequest request) {
		String token = getToken(request);
		if (headers == null) {
			headers = new HashMap<String, String>();
		}
		if (!StringUtils.isEmpty(token)) {
			headers.put("Authorization", "bearer " + token);
		}
		return get(url, headers, charset, connTimeout, readTimeout);
	}

	public static ResponseData get(String url, Map<String, String> headers) {
		return get(url, headers, charset, connTimeout, readTimeout);
	}

	public static ResponseData get(String url, Map<String, String> headers, String charset, Integer connTimeout,
			Integer readTimeout) {
		if (headers == null) {
			headers = new HashMap<String, String>();
		}
		 

		HttpClient client = null;
		HttpGet get = new HttpGet(url);
		ResponseData resData = new ResponseData();
		try {

			if (headers != null && !headers.isEmpty()) {
				for (Entry<String, String> entry : headers.entrySet()) {
					get.addHeader(entry.getKey(), entry.getValue());
				}
			}

			// 设置参数
			Builder customReqConf = RequestConfig.custom();
			if (connTimeout != null) {
				customReqConf.setConnectTimeout(connTimeout);
			}
			if (readTimeout != null) {
				customReqConf.setSocketTimeout(readTimeout);
			}
			get.setConfig(customReqConf.build());

			HttpResponse res = null;

			if (url.startsWith("https")) {
				// 执行 Https 请求.
				client = createSSLInsecureClient();
				res = client.execute(get);
			} else {
				// 执行 Http 请求.
				client = ApiClient.client;
				res = client.execute(get);
			}
			resData.setCode(res.getStatusLine().getStatusCode());
			resData.setBackData(IOUtils.toString(res.getEntity().getContent(), "UTF-8"));
		} catch (Exception e) {
			resData.setCode(500);
			resData.setBackData("请求其他系统失败");
		} finally {
			get.releaseConnection();
			if (url.startsWith("https") && client != null && client instanceof CloseableHttpClient) {
				try {
					((CloseableHttpClient) client).close();
				} catch (IOException e) {
					resData.setCode(500);
					resData.setBackData("请求其他系统失败");
					e.printStackTrace();
				}
			}
		}
		logger.info(url);
		try {
			logger.info(JsonUtil.beanToJson(headers));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info(resData.getBackData());
		return resData;
	}

	public static ResponseData postJson(String url, Map<String, String> params, Map<String, String> headers,
			Integer connTimeout, Integer readTimeout) {

		if (headers == null) {
			headers = new HashMap<String, String>();
		}
		 

		HttpClient client = null;
		ResponseData resData = new ResponseData();
		HttpPost post = new HttpPost(url);
		String jsonData = "";
		try {
			jsonData = JsonUtil.beanToJson(params);
			StringEntity entity = new StringEntity(jsonData, "utf-8");// 解决中文乱码问题
			entity.setContentEncoding("UTF-8");
			entity.setContentType("application/json");
			post.setEntity(entity);

			if (headers != null && !headers.isEmpty()) {
				for (Entry<String, String> entry : headers.entrySet()) {
					post.addHeader(entry.getKey(), entry.getValue());
				}
			}
			// 设置参数
			Builder customReqConf = RequestConfig.custom();
			if (connTimeout != null) {
				customReqConf.setConnectTimeout(connTimeout);
			}
			if (readTimeout != null) {
				customReqConf.setSocketTimeout(readTimeout);
			}
			post.setConfig(customReqConf.build());
			HttpResponse res = null;
			if (url.startsWith("https")) {
				// 执行 Https 请求.
				client = createSSLInsecureClient();
				res = client.execute(post);
			} else {
				// 执行 Http 请求.
				client = ApiClient.client;
				res = client.execute(post);
			}

			resData.setCode(res.getStatusLine().getStatusCode());
			resData.setBackData(IOUtils.toString(res.getEntity().getContent(), "UTF-8"));
		} catch (Exception e) {
			resData.setCode(500);
			resData.setBackData("请求其他系统失败");
		} finally {
			post.releaseConnection();
			if (url.startsWith("https") && client != null && client instanceof CloseableHttpClient) {
				try {
					((CloseableHttpClient) client).close();
				} catch (IOException e) {
					resData.setCode(500);
					resData.setBackData("请求其他系统失败");
					e.printStackTrace();
				}
			}
		}
		logger.info(url);
		try {
			logger.info(JsonUtil.beanToJson(headers));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info(jsonData);
		logger.info(resData.getBackData());
		return resData;
	}

	public static ResponseData post(String url, Map<String, String> params, Map<String, String> headers,
			Integer connTimeout, Integer readTimeout) {

		if (headers == null) {
			headers = new HashMap<String, String>();
		}
	 
		HttpClient client = null;
		HttpPost post = new HttpPost(url);
		ResponseData resData = new ResponseData();
		try {
			if (params != null && !params.isEmpty()) {
				List<NameValuePair> formParams = new ArrayList<org.apache.http.NameValuePair>();
				Set<Entry<String, String>> entrySet = params.entrySet();
				for (Entry<String, String> entry : entrySet) {
					formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
				}
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formParams, Consts.UTF_8);
				post.setEntity(entity);
			}

			if (headers != null && !headers.isEmpty()) {
				for (Entry<String, String> entry : headers.entrySet()) {
					post.addHeader(entry.getKey(), entry.getValue());
				}
			}
			// 设置参数
			Builder customReqConf = RequestConfig.custom();
			if (connTimeout != null) {
				customReqConf.setConnectTimeout(connTimeout);
			}
			if (readTimeout != null) {
				customReqConf.setSocketTimeout(readTimeout);
			}
			post.setConfig(customReqConf.build());
			HttpResponse res = null;
			if (url.startsWith("https")) {
				// 执行 Https 请求.
				client = createSSLInsecureClient();
				res = client.execute(post);
			} else {
				// 执行 Http 请求.
				client = ApiClient.client;
				res = client.execute(post);
			}

			resData.setCode(res.getStatusLine().getStatusCode());
			resData.setBackData(IOUtils.toString(res.getEntity().getContent(), "UTF-8"));
		} catch (Exception e) {
			resData.setCode(500);
			resData.setBackData("请求其他系统失败");
		} finally {
			post.releaseConnection();
			if (url.startsWith("https") && client != null && client instanceof CloseableHttpClient) {
				try {
					((CloseableHttpClient) client).close();
				} catch (IOException e) {
					resData.setCode(500);
					resData.setBackData("请求其他系统失败");
					e.printStackTrace();
				}
			}
		}

		logger.info(url);
		try {
			logger.info(JsonUtil.beanToJson(headers));
			logger.info(JsonUtil.beanToJson(params));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info(resData.getBackData());
		return resData;
	}

	public static ResponseData postNoToken(String url, Map<String, String> params, Map<String, String> headers,
			Integer connTimeout, Integer readTimeout) {

		HttpClient client = null;
		HttpPost post = new HttpPost(url);
		ResponseData resData = new ResponseData();
		try {
			if (params != null && !params.isEmpty()) {
				List<NameValuePair> formParams = new ArrayList<org.apache.http.NameValuePair>();
				Set<Entry<String, String>> entrySet = params.entrySet();
				for (Entry<String, String> entry : entrySet) {
					formParams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
				}
				UrlEncodedFormEntity entity = new UrlEncodedFormEntity(formParams, Consts.UTF_8);
				post.setEntity(entity);
			}

			if (headers != null && !headers.isEmpty()) {
				for (Entry<String, String> entry : headers.entrySet()) {
					post.addHeader(entry.getKey(), entry.getValue());
				}
			}
			// 设置参数
			Builder customReqConf = RequestConfig.custom();
			if (connTimeout != null) {
				customReqConf.setConnectTimeout(connTimeout);
			}
			if (readTimeout != null) {
				customReqConf.setSocketTimeout(readTimeout);
			}
			post.setConfig(customReqConf.build());
			HttpResponse res = null;
			if (url.startsWith("https")) {
				// 执行 Https 请求.
				client = createSSLInsecureClient();
				res = client.execute(post);
			} else {
				// 执行 Http 请求.
				client = ApiClient.client;
				res = client.execute(post);
			}

			resData.setCode(res.getStatusLine().getStatusCode());
			resData.setBackData(IOUtils.toString(res.getEntity().getContent(), "UTF-8"));
		} catch (Exception e) {
			resData.setCode(500);
			resData.setBackData("请求其他系统失败");
		} finally {
			post.releaseConnection();
			if (url.startsWith("https") && client != null && client instanceof CloseableHttpClient) {
				try {
					((CloseableHttpClient) client).close();
				} catch (IOException e) {
					resData.setCode(500);
					resData.setBackData("请求其他系统失败");
					e.printStackTrace();
				}
			}
		}

		logger.info(url);
		try {
			logger.info(JsonUtil.beanToJson(headers));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info(resData.getBackData());
		return resData;
	}

	/**
	 * 创建 SSL连接
	 * 
	 * @return
	 * @throws GeneralSecurityException
	 */
	private static CloseableHttpClient createSSLInsecureClient() throws GeneralSecurityException {
		try {
			SSLContext sslContext = new SSLContextBuilder().loadTrustMaterial(null, new TrustStrategy() {
				public boolean isTrusted(X509Certificate[] chain, String authType) throws CertificateException {
					return true;
				}
			}).build();

			SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, new HostnameVerifier() {
 
				public boolean verify(String arg0, SSLSession arg1) {
					return true;
				}
			});

			return HttpClients.custom().setSSLSocketFactory(sslsf).build();

		} catch (GeneralSecurityException e) {
			throw e;
		}
	}

	public static ResponseData postForm(String url, Map<String, String> params, Map<String, String> headers) {

		if (headers == null) {
			headers = new HashMap<String, String>();
		}
		
		HttpClient client = null;
		ResponseData resData = new ResponseData();
		HttpPost post = new HttpPost(url);
		String body = "";
		StringBuffer sb = new StringBuffer();
		try {
			if (params != null && !params.isEmpty()) {
				for (Entry<String, String> e : params.entrySet()) {
					sb.append(e.getKey());
					sb.append("=");
					sb.append(e.getValue());
					sb.append("&");
				}
				sb.substring(0, sb.length() - 1);
			}
			body = sb.toString().replaceAll(" ", "%20");
			HttpEntity entity = new StringEntity(body,
					ContentType.create("application/x-www-form-urlencoded", charset));
			post.setEntity(entity);

			if (headers != null && !headers.isEmpty()) {
				for (Entry<String, String> entry : headers.entrySet()) {
					post.addHeader(entry.getKey(), entry.getValue());
				}
			}
			// 设置参数
			Builder customReqConf = RequestConfig.custom();
			if (connTimeout > 0) {
				customReqConf.setConnectTimeout(connTimeout);
			}
			if (readTimeout > 0) {
				customReqConf.setSocketTimeout(readTimeout);
			}
			post.setConfig(customReqConf.build());
			HttpResponse res = null;
			if (url.startsWith("https")) {
				// 执行 Https 请求.
				client = createSSLInsecureClient();
				res = client.execute(post);
			} else {
				// 执行 Http 请求.
				client = ApiClient.client;
				res = client.execute(post);
			}

			resData.setCode(res.getStatusLine().getStatusCode());
			resData.setBackData(IOUtils.toString(res.getEntity().getContent(), "UTF-8"));
		} catch (Exception e) {
			resData.setCode(500);
			resData.setBackData("请求其他系统失败");
		} finally {
			post.releaseConnection();
			if (url.startsWith("https") && client != null && client instanceof CloseableHttpClient) {
				try {
					((CloseableHttpClient) client).close();
				} catch (IOException e) {
					resData.setCode(500);
					resData.setBackData("请求其他系统失败");
					e.printStackTrace();
				}
			}
		}
		logger.info(url);
		try {
			logger.info(JsonUtil.beanToJson(headers));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info(body);
		logger.info(resData.getBackData());
		return resData;
	}

}
