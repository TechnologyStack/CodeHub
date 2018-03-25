package com.jira.rest.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FileUploadController {

	private static final String JIRA_USERNAME = "mail.ramireddy.srikanth@gmail.com";
	private static final String JIRA_PASSWORD = "Kanth@2018";

	@RequestMapping(value = "/uploadMultipleFiles", method = RequestMethod.GET)
	public ModelAndView uploadMultipleFilesFormDisplay() {

		return new ModelAndView("uploadMultipleFiles");

	}

	@RequestMapping(value = "/uploadMultipleFiles", method = RequestMethod.POST)
	public void addAttacmentToJiraIssue(String key,@RequestParam("file") MultipartFile[] files) {

		/* Jira credentials */
		String jira_attachment_authentication = new String(
				org.apache.commons.codec.binary.Base64.encodeBase64((JIRA_USERNAME + ":" + JIRA_PASSWORD).getBytes()));

		String jira_attachment_baseURL = "https://srikanth99.atlassian.net/rest/api/2/issue/DOC-13/attachments";

		HttpPost httppost = new HttpPost(jira_attachment_baseURL);
		httppost.setHeader("X-Atlassian-Token", "nocheck");
		httppost.setHeader("Authorization", "Basic " + jira_attachment_authentication);

		MultipartEntityBuilder builder = MultipartEntityBuilder.create();
		builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);

		if (files.length == 0)
			System.out.println("Required information missing");

		for (int i = 0; i < files.length; i++) {
			MultipartFile file = files[i];
			String filename = file.getOriginalFilename();
			byte[] bytes;
			try {
				bytes = file.getBytes();
				//InputStream inputStream= new ByteArrayInputStream(bytes);
				builder.addBinaryBody("file", bytes, ContentType.MULTIPART_FORM_DATA, filename);
				//builder.addBinaryBody("file", inputStream, ContentType.MULTIPART_FORM_DATA, filename);
			} catch (IOException e) {
				System.out.println("Not able to read file.......Operation Failed");
				e.printStackTrace();
			}
		}
		HttpEntity entity = builder.build();
		httppost.setEntity(entity);
		HttpResponse response = null;
		try (CloseableHttpClient httpclient = HttpClients.createDefault();) {
			response = httpclient.execute(httppost);
		} catch (ClientProtocolException e) {
			System.out.println("ProtocolException...........");
		} catch (IOException e) {
			System.out.println("IOException...........");
		}
		if (response.getStatusLine().getStatusCode() == 200)
			System.out.println("Operation Suuceess..........." + response.getStatusLine().getStatusCode());
		else
			System.out.println("Operation Failed..........." + response.getStatusLine().getStatusCode());

	}

}