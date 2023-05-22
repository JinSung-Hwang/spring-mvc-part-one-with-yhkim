package hello.servlet.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class MemberRepositoryTest {

  MemberRepository memberRepository = MemberRepository.getInstance();

  @AfterEach
  void afterEach() {
    memberRepository.clearStore();
  }

  @Test
  void save() {
    // given
    Member member = new Member("hello", 20);

    // when
    Member savedMember = memberRepository.save(member);

    // then
    Member findMember = memberRepository.findById(savedMember.getId());
    assertThat(findMember).isEqualTo(savedMember);
  }

  // @Test는 순서가 보장이 안된다.
  // 따라서 afterEach를 통해서 데이터를 삭제해줘야하고 테스트끼리 데이터 독립적으로 수행되도록 테스트케이스 작성해야함
  @Test
  void findAll() {
    // given
    Member member1 = new Member("member1", 20);
    Member member2 = new Member("member2", 30);

    memberRepository.save(member1);
    memberRepository.save(member2);
    // when
    List<Member> result = memberRepository.findAll();

    // then
    assertThat(result.size()).isEqualTo(2);
    assertThat(result).contains(member1, member2);
  }

}