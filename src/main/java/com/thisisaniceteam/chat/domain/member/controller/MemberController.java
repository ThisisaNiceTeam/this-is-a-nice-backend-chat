package com.thisisaniceteam.chat.domain.member.controller;

import com.thisisaniceteam.chat.domain.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
}
