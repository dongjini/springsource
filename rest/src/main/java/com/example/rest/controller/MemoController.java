package com.example.rest.controller;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.rest.dto.MemoDTO;
import com.example.rest.service.MemoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Log4j2
@RestController
@RequestMapping("/memo")
@RequiredArgsConstructor
public class MemoController {
    // 서비스 메소드 호출
    // 데이터가 전송된다면 전송된 데이터를 Model 에 담기
    private final MemoService memoService;

    // 주소 설계
    // 전체 memo 추출 : /memo/list
    @GetMapping("/list")
    public List<MemoDTO> getList(Model model) {
        List<MemoDTO> list = memoService.getList();
        return list;
    }

    // 특정 memo 조회 : /memo/read?mno-3
    @GetMapping(value = { "/read", "/update" })
    public MemoDTO getRow(Long mno, Model model) {
        log.info("조회요청 {}", mno);
        MemoDTO dto = memoService.getRow(mno);

        return dto;
    }

    // memo 수정 : /memo/update?mno=3 post
    @PutMapping("/update")
    public Long postUpdate(@RequestBody MemoDTO dto) {
        log.info("메모 수정 {}", dto);
        // 수정 요청
        Long mno = memoService.memoUpdate(dto);

        // 수정 완료 시 read 화면으로 이동
        return mno;
    }

    // 특정 memo 수정 : /memo/update?mno=3
    // memo 추가 : /memo/new
    @GetMapping("/new")
    public void getNew() {
        log.info("새 메모 작성 폼 요청");

    }

    @PostMapping("/new")
    public Long postNew(@RequestBody MemoDTO dto) {
        // 사용자 입력값 가져오기
        log.info("새 메모 작성 {}", dto);
        Long mno = memoService.memoCreate(dto);

        return mno;

    }

    // memo 삭제 : /memo/remove/3
    @DeleteMapping("/remove/{mno}")
    public Long getRemove(@PathVariable Long mno) {
        log.info("memo 삭제 요청 {}", mno);

        // 삭제 요청
        memoService.memoDelete(mno);

        return mno;
    }

}
