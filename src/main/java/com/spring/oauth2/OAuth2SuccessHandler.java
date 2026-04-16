package com.spring.oauth2;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.spring.security.JwtUtil;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class OAuth2SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	private final JwtUtil jwtUtil;

	public OAuth2SuccessHandler(JwtUtil jwtUtil) {
		super();
		this.jwtUtil = jwtUtil;
	}

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication auth)
			throws java.io.IOException {

		OAuth2User user = (OAuth2User) auth.getPrincipal();

		String email = user.getAttribute("email");
		String token = jwtUtil.generateToken(email);

		// ✅ Return JSON response
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		String jsonResponse =  token;

		response.getWriter().write(jsonResponse);
	}
}