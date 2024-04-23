package com.develup.noramore.member.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;


@Component("kakaoLoginAuth")
public class KakaoLoginAuth {
	//자바스크립트 앱키
	private final static String K_CLIENT_ID = "0c339e5679519c2d97da45c7d7f28876";
	//카카오 개발자에 등록된 로그인용 redirect uri
	private final static String K_REDIRECT_URI = "http://localhost:8080/noramore/kcallback.do";
	
	//카카오 로그인 요청시 카카오 로그인 페이지로 이동 url 리턴용
	public String getAuthorizationUrl(HttpSession session) {
		String kakaoURL = 
				"https://kauth.kakao.com/oauth/authorize?"
				+ "client_id=" +  K_CLIENT_ID
				+ "&redirect_uri=" +  K_REDIRECT_URI
				+ "&response_type=code";
		
		return kakaoURL;
	}
	
	//카카오 api 서버 접근 토큰 얻어와서 리턴용
	public JsonNode getAccessToken(String authorize_code) {  //인증코드를 받아서 액세스 토큰을 반환함
		//메서드는 'JsonNode'를 반환 함, 이는 Jackson라이브러리를 사용하여 JSON형식의 응답을 처리하기 위함
		
		final String RequestURL = "https://kauth.kakao.com/oauth/token";
		//이 URL은 카카오의 OAuth 인증 서버에 토큰을 요청하기 위해 사용됨. 카카오인증 서버의 엔드 포인트임
		//카카오 OAuth 인증 서버는 인증 코드를 전달받고, 해당 코드를 사용하여 액세스 토큰을 발급함.
		//  >> 이 액세스 토큰은 인증된 사용자를 나타내는데 사용되며, 보통은 사용자의 프로필 정보에 접근하기 위해 사용됨
		
		//postparams 변수에는 POST요청에 필요한 매개변수들이 저장됨
		final List<NameValuePair> postParams = new ArrayList<NameValuePair>();
		
		//이들은 카카오 OAuth 토큰 요청을 위한 필수 매개변수임
		postParams.add(new BasicNameValuePair("grant_type", "authorization_code"));
		postParams.add(new BasicNameValuePair("client_id", K_CLIENT_ID));
		postParams.add(new BasicNameValuePair("redirect_uri", K_REDIRECT_URI));
		postParams.add(new BasicNameValuePair("code", authorize_code));
		
		//HttpClient 및 HttpPost 인스턴스 생성함
		final CloseableHttpClient client = HttpClientBuilder.create().build();
		final HttpPost post = new HttpPost(RequestURL);  //요청할 URL을 설정함
		
		JsonNode returnNode = null;
		
		try {
			post.setEntity(new UrlEncodedFormEntity(postParams));  //POST요청의  매개변수들을 설정함
			final CloseableHttpResponse response = client.execute(post);  //POST요청을 실행하고, 그결과를 받아옴
			
			//POST에서 받아온 응답은 JSON형식이므로, ObjectMapper를 사용하여 JSON응답을 JsonNode로 변환함
			ObjectMapper mapper = new ObjectMapper();  
			returnNode = mapper.readTree(response.getEntity().getContent());
			//이렇게 변환된 JsonNode가 액세스 토큰을 포함하고 있음
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return returnNode;
	}
	
	//사용자 정보 얻어오기
	public JsonNode getKakaoUserInfo(JsonNode accessToken) {  
		//사용자가 로그인한 후에 발급된 카카오 OAuth 액세스 토큰
		//  >> 사용자를 인증하고 사용자의 프로필 정보에 접근할 권한을 부여하는 데 사용됨
		
		//url 버전 변경됨 v2로
		final String RequestURL = "https://kapi.kakao.com/v2/user/me";
		//카카오 사용자 정보 요청을 위한 엔드포인트 URL이 저장
		/* "https://kapi.kakao.com/v2/user/scopes"; */
		
		final CloseableHttpClient client = HttpClientBuilder.create().build(); 
		//HTTP 클라이언트를 생성함  >> 이 클라이언트는 HTTP POST 요청을 보내기 위해 사용됨
		
		final HttpPost post = new HttpPost(RequestURL);
		//사용자 정보 요청을 위한 POST 요청을 생성 >> 이 요청은 RequestURL 로 설정됨
		
		
		//add header
		//** 주의 : accessToken 값은 JsonNode 형이어야 함
		// Bearer 뒤에 스페이스바 꼭 추가
		post.addHeader("Authorization", "Bearer " + accessToken); //요청헤더에는 사용자의 액세스 토큰이 포함됨.
		
		JsonNode returnNode = null;
		
		try {
			final CloseableHttpResponse response = client.execute(post);  //응답은 json형식으로 받음
			ObjectMapper mapper = new ObjectMapper();
			returnNode = mapper.readTree(  //ObjectMapper를 사용하여 JSON 데이터를 JsonNode 객체로 변환함
					response.getEntity().getContent());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return returnNode;  //JsonNode 객체는 사용자 정보를 포함하고 있으며, 이를 반환함.
	}
	
	
	
	//카카오 로그아웃 처리용
	public void logout() {
		String url = "https://kauth.kakao.com/oauth/logout?"
				+ "client_id=" + K_CLIENT_ID
				+ "&logout_redirect_uri=http://localhost:8080/first/logout.do";		
	}
	
}

