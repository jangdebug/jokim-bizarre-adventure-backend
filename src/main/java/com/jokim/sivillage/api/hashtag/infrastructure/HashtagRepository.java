package com.jokim.sivillage.api.hashtag.infrastructure;

import com.jokim.sivillage.api.hashtag.domain.Hashtag;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;


public interface HashtagRepository extends JpaRepository<Hashtag, Long> {


}
