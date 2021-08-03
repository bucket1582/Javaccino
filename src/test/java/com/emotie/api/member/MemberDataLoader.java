package com.emotie.api.member;

import com.emotie.api.auth.infra.PasswordHashProvider;
import com.emotie.api.member.domain.Member;
import com.emotie.api.member.domain.MemberRole;
import com.emotie.api.member.domain.MemberRoles;
import com.emotie.api.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
@Profile("memberDataLoader")
@RequiredArgsConstructor
public class MemberDataLoader implements CommandLineRunner {
    private final MemberRepository memberRepository;
    private final PasswordHashProvider passwordHashProvider;

    public static String authorizedEmail = "jasotn12@naver.com",
            unauthorizedEmail = "anfro2520@gmail.com",
            expelledEmail = "expelled@gmail.com",
            getAuthorizationTokenEmail = "authorizationToken@gmail.com",
            expiredAuthorizationTokenEmail = "expiredAuthorizationToken@gmail.com",
            getPasswordResetTokenEmail = "passwordResetToken@gmail.com",
            expiredPasswordResetTokenEmail = "expiredPasswordResetToken@gmail.com",
            notExistEmail = "notExist@gmail.com",
            password = "password123!", wrongPassword = "wrongPassword",
            resetPassword = "resetPassword123!";

    public static String authorizationToken = "authorization_token", passwordResetToken = "password_reset_token";

    @Override
    public void run(String... args) {
        memberRepository.save(
                Member.builder()
                        .UUID(UUID.randomUUID().toString())
                        .email(authorizedEmail)
                        .nickname(authorizedEmail)
                        .passwordHash(passwordHashProvider.encodePassword(password))
                        .passwordResetToken(null)
                        .passwordResetTokenValidUntil(null)
                        .authorizationToken(null)
                        .authorizationTokenValidUntil(null)
                        .reportCount(0)
                        .roles(MemberRoles.getDefaultFor(MemberRole.MEMBER))
                        .build());
        memberRepository.save(
                Member.builder()
                        .UUID(UUID.randomUUID().toString())
                        .email(unauthorizedEmail)
                        .nickname(unauthorizedEmail)
                        .passwordHash(passwordHashProvider.encodePassword(password))
                        .passwordResetToken(null)
                        .passwordResetTokenValidUntil(null)
                        .authorizationToken(null)
                        .authorizationTokenValidUntil(null)
                        .reportCount(0)
                        .roles(MemberRoles.getDefaultFor(MemberRole.UNACCEPTED))
                        .build());
        memberRepository.save(
                Member.builder()
                        .UUID(UUID.randomUUID().toString())
                        .email(expelledEmail)
                        .nickname(expelledEmail)
                        .passwordHash(passwordHashProvider.encodePassword(password))
                        .passwordResetToken(null)
                        .passwordResetTokenValidUntil(null)
                        .authorizationToken(null)
                        .authorizationTokenValidUntil(null)
                        .reportCount(0)
                        .roles(MemberRoles.getDefaultFor(MemberRole.EXPELLED))
                        .build());
        memberRepository.save(
                Member.builder()
                        .UUID(UUID.randomUUID().toString())
                        .email(getAuthorizationTokenEmail)
                        .nickname(getAuthorizationTokenEmail)
                        .passwordHash(passwordHashProvider.encodePassword(password))
                        .passwordResetToken(null)
                        .passwordResetTokenValidUntil(null)
                        .authorizationToken(authorizationToken)
                        .authorizationTokenValidUntil(LocalDateTime.now().plusDays(1))
                        .reportCount(0)
                        .roles(MemberRoles.getDefaultFor(MemberRole.UNACCEPTED))
                        .build());
        memberRepository.save(
                Member.builder()
                        .UUID(UUID.randomUUID().toString())
                        .email(expiredAuthorizationTokenEmail)
                        .nickname(expiredAuthorizationTokenEmail)
                        .passwordHash(passwordHashProvider.encodePassword(password))
                        .passwordResetToken(null)
                        .passwordResetTokenValidUntil(null)
                        .authorizationToken(authorizationToken)
                        .authorizationTokenValidUntil(LocalDateTime.now().minusDays(1))
                        .reportCount(0)
                        .roles(MemberRoles.getDefaultFor(MemberRole.UNACCEPTED))
                        .build());
        memberRepository.save(
                Member.builder()
                        .UUID(UUID.randomUUID().toString())
                        .email(getPasswordResetTokenEmail)
                        .nickname(getPasswordResetTokenEmail)
                        .passwordHash(passwordHashProvider.encodePassword(password))
                        .passwordResetToken(passwordResetToken)
                        .passwordResetTokenValidUntil(LocalDateTime.now().plusDays(1))
                        .authorizationToken(null)
                        .authorizationTokenValidUntil(null)
                        .reportCount(0)
                        .roles(MemberRoles.getDefaultFor(MemberRole.MEMBER))
                        .build());
        memberRepository.save(
                Member.builder()
                        .UUID(UUID.randomUUID().toString())
                        .email(expiredPasswordResetTokenEmail)
                        .nickname(expiredPasswordResetTokenEmail)
                        .passwordHash(passwordHashProvider.encodePassword(password))
                        .passwordResetToken(passwordResetToken)
                        .passwordResetTokenValidUntil(LocalDateTime.now().minusDays(1))
                        .authorizationToken(null)
                        .authorizationTokenValidUntil(null)
                        .reportCount(0)
                        .roles(MemberRoles.getDefaultFor(MemberRole.MEMBER))
                        .build());
    }
}

