package spring.deep.login.domain.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;

@Slf4j
@Repository
public class MemberRepository {

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    public Member save(Member member) {
        member.setId(++sequence);
        log.info("save: member={}", member);
        store.put(member.getId(), member);
        return member;
    }

    public Member findById(Long id) {
        return store.get(id);
    }

    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public Optional<Member> findByLoginId(String loginId) {
//        List<Member> all = findAll();
//        for(Member m : all) {
//            if(m.getLoginId().equals(loginId)) {
//                return Optional.of(m);
//            }
//        }
//        return Optional.empty();

        return findAll().stream() //스트림으로 변환. 즉, 반복한다고 생각하면 됨.
                .filter(m -> m.getLoginId().equals(loginId)) //조건에 만족하는 경우만 다음 단계
                .findFirst(); //가장 먼저 나오는 것 반환
    }

    public void clearStore() {
        store.clear();
    }
}
