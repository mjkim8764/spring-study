package spring;

import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * @Component
 * 설정 파일에 빈으로 등록하지 않아도
 * 원하는 클래스를 빈으로 등록할 수 있게 함
 * 속성 값이 없으면 디폴트로 클래스 이름의 첫 글자를 소문자로 바꾼 이름(memberDao)의 빈을 사용
 */

@Component
public class MemberDao {

    private static long nextId = 0;

    private Map<String, Member> map = new HashMap<>();

    public Member selectByEmail(String email){
        return map.get(email);
    }

    public void insert(Member member){
        member.setId(++nextId);
        map.put(member.getEmail(), member);
    }

    public void update(Member member){
        map.put(member.getEmail(), member);
    }

    public Collection<Member> selectAll() {
        return map.values();
    }

}
