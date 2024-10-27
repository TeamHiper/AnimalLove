package com.animal.AnimalLove.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing
// @WebMvcTest 어노테이션을 지정한 테스트에서 애플리케이션 호출하다 에러가 발생활 확률이 있어 JpaAuditingConfiguration 따로 설정했음.
public class JpaAuditingConfiguration {
}
