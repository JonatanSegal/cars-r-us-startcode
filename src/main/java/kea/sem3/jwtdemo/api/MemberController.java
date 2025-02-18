package kea.sem3.jwtdemo.api;

import kea.sem3.jwtdemo.dto.MemberRequest;
import kea.sem3.jwtdemo.dto.MemberResponse;
import kea.sem3.jwtdemo.service.MemberService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("api/members")
public class MemberController {
    MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping
    public ResponseEntity<List<MemberResponse>> getAllMembers() {
        return ResponseEntity.ok(memberService.getMembers());
    }
    @GetMapping("/fromtoken/user")
    @RolesAllowed({"USER","ADMIN"})
    public MemberResponse getAuthenticatedMember(Principal principal) {
        return (memberService.getMemberByUserName(principal.getName()));
    }

    @RolesAllowed("ADMIN")
    @GetMapping("/{username}")
    public MemberResponse getMembersFromUserName(@PathVariable String username) {
        return (memberService.getMemberByUserName(username));
    }

    @PostMapping()
    public MemberResponse AddMember(@RequestBody MemberRequest body) {
        System.out.println("Hello");
        return memberService.addMember(body);
    }

    @RolesAllowed("ADMIN")
    @PostMapping("/{username}")
    public MemberResponse editMember(@RequestBody MemberRequest body, @PathVariable String username) throws Exception {
        return memberService.editMember(body,username);
    }

    @RolesAllowed("ADMIN")
    @DeleteMapping("/{username}")
    public void deleteCar(@PathVariable String username){
        memberService.deleteMember(username);
    }

}
