package com.example.demo.controller;

import org.springframework.stereotype.Controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequestMapping("/board")
@Log4j2
public class BoardController {
    @GetMapping("/create")
    public void getCreate() {
        // return "board/create"; // 템블릿 폴더 안 보드 폴더로 옮겨서 주소 수정.
    }

    @PostMapping("/create")
    // public String postCreate(@ModelAttribute("name") String name,
    // RedirectAttributes rttr) {
    public void postCreate(String name, HttpSession session) {
        log.info("name 값 가져오기 {}", name);

        session.setAttribute("name1", name);

        // 어느 페이지로(template) 이동을 하던지 간에 name 유지시키고 싶다면
        // 커맨드 객체, ModelAttribute( or @ModelAttribute)
        // return "board/list";

        // redirect 로 움직이는데 값 유지하고 싶다면?
        // rttr.addAttribute("name", name);
        // rttr.addFlashAttribute("name", name);
        // return "redirect:/board/list";
    }

    @GetMapping("/list")
    public void getList() {
        // return "board/list";
    }

    @GetMapping("/read")
    public void getRead() {
        // return "board/read";
    }

    @GetMapping("/update")
    public void getUpdate() {
        // return "board/update";
    }

}
