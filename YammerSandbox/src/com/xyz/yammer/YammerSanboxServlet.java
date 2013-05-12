package com.xyz.yammer;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.xyz.yammer.model.OAuthResponse;

//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;

// https://developer.yammer.com/authentication/
// refer to section on server-side flow

@SuppressWarnings("serial")
public class YammerSanboxServlet extends HttpServlet {

	public static final String CLIENT_ID = "zPehWNzy0MWCgpiYzY8wQ";
	public static final String CLIENT_SECRET = "UvOSyIQ9pUCC4J70mNxgJkbgjdHUWI1mGxxmZHiKYc";

	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {

		String verifier = req.getParameter("code");

		if (verifier == null || verifier.length() == 0) {

			StringBuffer sb = new StringBuffer();
			sb.append("https://www.yammer.com/dialog/oauth?client_id=");
			sb.append(CLIENT_ID);
			sb.append("&redirect_uri=");
			sb.append(req.getRequestURL());

			String authUrl = sb.toString();

			resp.sendRedirect(authUrl);
			
		} else {

			// GET
			// https://www.yammer.com/oauth2/access_token.json?client_id=[:client_id]&client_secret=[:client_secret]&code=[:code]
			StringBuilder sbUrl = new StringBuilder();
			sbUrl.append("https://www.yammer.com/oauth2/access_token.json?client_id=");
			sbUrl.append(CLIENT_ID);
			sbUrl.append("&client_secret=");
			sbUrl.append(CLIENT_SECRET);
			sbUrl.append("&code=");
			sbUrl.append(verifier);

			String reqUrl = sbUrl.toString();

			resp.setContentType("text/plain");

			HttpURLConnection connection = null;
			URL serverAddress = null;

			try {
				serverAddress = new URL(reqUrl);

				connection = null;
				connection = (HttpURLConnection) serverAddress.openConnection();
				connection.setRequestMethod("GET");
				connection.setReadTimeout(10000);
				connection.connect();

				Gson gson = new Gson();
				OAuthResponse oaResp = gson.fromJson(new InputStreamReader(
						connection.getInputStream()), OAuthResponse.class);

				String userFullName = oaResp.user.full_name;
				String accessToken = oaResp.access_token.token;

				resp.getWriter().println(
						"Hello, " + userFullName + " (" + accessToken + ")!");

			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (ProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				connection.disconnect();
				connection = null;
			}

		} 
	}
}
