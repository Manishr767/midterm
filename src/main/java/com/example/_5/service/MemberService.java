package com.example._5.service;



import com.example._5.DTO.MemberDTO;
import com.example._5.Repository.MemberDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {

    @Autowired
    MemberDAO memberDAO;

    // Register new member
    public void registNewMember(MemberDTO dto){
        memberDAO.insertMember(dto);
    }

    // Login
    public MemberDTO login(MemberDTO memberDTO) {
        return memberDAO.finduser(memberDTO); // now returns actual member or null
    }

    // Get all members
    public List<MemberDTO> getAllmembers() {
        return memberDAO.getAllMembers();
    }



    public void deleteMemberById(int id) {
        memberDAO.deleteById(id);
    }

}
