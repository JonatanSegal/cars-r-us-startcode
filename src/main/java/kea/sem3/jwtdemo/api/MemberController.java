package kea.sem3.jwtdemo.api;

import kea.sem3.jwtdemo.dto.MemberRequest;
import kea.sem3.jwtdemo.dto.MemberResponse;
import kea.sem3.jwtdemo.service.MemberService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/member")
public class MemberController {
    MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/{id}")
    public MemberResponse getMember(@PathVariable String fullName) throws Exception{
        return memberService.getMember(fullName,false);
    }

    @PostMapping
    public MemberResponse addMember(@RequestBody MemberRequest body){
        return memberService.addMember(body);
    }

    @PostMapping("/{id}")
    public MemberResponse editMember(@RequestBody MemberRequest body, @PathVariable String fullName) throws Exception {
        return memberService.editMember(body,fullName);
    }

    @DeleteMapping("/{id}")
    public void deleteCar(@PathVariable String fullName){
        memberService.deleteMember(fullName);
    }

}
