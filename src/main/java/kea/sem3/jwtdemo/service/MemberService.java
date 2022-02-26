package kea.sem3.jwtdemo.service;

import kea.sem3.jwtdemo.dto.CarRequest;
import kea.sem3.jwtdemo.dto.CarResponse;
import kea.sem3.jwtdemo.dto.MemberRequest;
import kea.sem3.jwtdemo.dto.MemberResponse;
import kea.sem3.jwtdemo.entity.Car;
import kea.sem3.jwtdemo.entity.Member;
import kea.sem3.jwtdemo.error.Client4xxException;
import kea.sem3.jwtdemo.repositories.MemberRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MemberService {
    MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public List<MemberResponse> getMembers(){
        List<Member> members = memberRepository.findAll();
        return MemberResponse.getMembersFromEntities(members);
    }

    public MemberResponse getMember(String fullName, boolean all) throws Exception{
        Member member = memberRepository.findById(fullName).orElseThrow(()->new Client4xxException("No member with this id exists"));
        return new MemberResponse(member,false);
    }

    public MemberResponse addMember(MemberRequest body){
        Member newMember = memberRepository.save(new Member(body));
        return new MemberResponse(newMember,true);
    }
    public MemberResponse editMember(MemberRequest body, String fullName) throws Exception{
        Member memberToEdit = memberRepository.findById(fullName).orElseThrow(()->new Client4xxException("No member with this id exists"));
        memberToEdit.setStreet(body.getStreet());
        memberToEdit.setCity(body.getCity());
        memberToEdit.setZip(body.getZip());
        memberToEdit.setEmail(body.getEmail());
        memberRepository.save(memberToEdit);
        return new MemberResponse(memberToEdit,false);
    }
    //Delete member? should you be able to delete your account yourself or should an admin do it?
    public void deleteMember(String fullName){
        memberRepository.deleteById(fullName);
    }

    //should there be an update for a specific variable?

}


