package com.example._5.controller;



import com.example._5.DTO.MemberDTO;
import com.example._5.service.MemberService;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MemberController {
    @Autowired
    MemberService memberService;

    @GetMapping("/")    // http://localhost:8080/
    public String index(){
        return "index";  //index.html
    }
    @GetMapping("/signIn")
    public String signUp(){
        return "signInForm";  //signInForm.html
    }
    @GetMapping("/signUp")
    public String signIn(){
        return "signUpForm";  //signInForm.html
    }

    @PostMapping("/register")
    public String regist(MemberDTO memberDTO){
        System.out.println("email :"+memberDTO.getEmail());
        System.out.println("password :"+memberDTO.getPwd());
        memberService.registNewMember(memberDTO);
        return  "index";

    }
    @PostMapping("/login")
    public String login(MemberDTO memberDTO, Model model, HttpSession session){
        System.out.println("Email:"+memberDTO.getEmail());
        System.out.println("pwd :"+memberDTO.getPwd());
        MemberDTO member = memberService.login(memberDTO);
        if (member != null){
            session.setAttribute("loginUser",member);
            System.out.println("Welcome !! "+member.getEmail());

        }else{
            System.out.println("try Again");
            System.out.println("or donot try");
        }

        return "index";
    }



    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate(); //session delete
        return "index";  //signInForm.html
    }
    @GetMapping("/memberList")
    public String memberList(Model model){
        List<MemberDTO> list =memberService.getAllmembers();
        model.addAttribute("members",list);
        System.out.println("Fetched members: " + list.size());
        return "member";  //signInForm.html
    }
    @GetMapping("/delete/{id}")
    public String deleteMember(@PathVariable("id") int id) {
        memberService.deleteMemberById(id);
        return "redirect:/memberList";


    }




}

