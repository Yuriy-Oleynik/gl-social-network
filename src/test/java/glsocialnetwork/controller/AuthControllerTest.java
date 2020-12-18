package glsocialnetwork.controller;

import glsocialnetwork.dto.AuthenticationResponse;
import glsocialnetwork.dto.LoginRequest;
import glsocialnetwork.dto.RefreshTokenRequest;
import glsocialnetwork.dto.RegisterRequest;
import glsocialnetwork.service.AuthService;
import glsocialnetwork.service.RefreshTokenService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class AuthControllerTest {

    @InjectMocks
    AuthController authController;

    @Mock
    AuthService authService;

    @Mock
    RefreshTokenService refreshTokenService;

    @Test
    public void signup() {
        RegisterRequest request = RegisterRequest.builder().email("some@gmail.com").password("password").username("username").build();
        doNothing().when(authService).signup(request);
        ResponseEntity<String> response = authController.signup(request);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void verifyAccount() {
        String token = "testToken123";
        doNothing().when(authService).verifyAccount(token);
        ResponseEntity<String> response = authController.verifyAccount(token);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void login() {
        LoginRequest request = LoginRequest.builder()
                .username("username")
                .password("password")
                .build();
        AuthenticationResponse mock = AuthenticationResponse.builder()
                .authenticationToken("testToken123")
                .refreshToken("testToken123")
                .build();
        when(authService.login(request)).thenReturn(mock);
        AuthenticationResponse response = authController.login(request);
        assertEquals(mock.getAuthenticationToken(), response.getAuthenticationToken());
    }

    @Test
    public void refreshTokens() {
        RefreshTokenRequest refreshToken = RefreshTokenRequest.builder()
                .refreshToken("testRefreshToken123")
                .username("username")
                .build();
        AuthenticationResponse mock = AuthenticationResponse.builder()
                .authenticationToken("testToken123")
                .refreshToken("testRefreshToken123")
                .username("username")
                .build();
        when(authService.refreshToken(refreshToken)).thenReturn(mock);
        AuthenticationResponse response = authController.refreshTokens(refreshToken);
        assertNotEquals(mock.getAuthenticationToken(), response.getRefreshToken());
    }

    @Test
    public void logout() {
        RefreshTokenRequest refreshToken = RefreshTokenRequest.builder()
                .refreshToken("testRefreshToken123")
                .username("username")
                .build();
        doNothing().when(refreshTokenService).deleteRefreshToken(refreshToken.getRefreshToken());
        ResponseEntity<String> response = authController.logout(refreshToken);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
