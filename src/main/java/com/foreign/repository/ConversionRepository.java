package com.foreign.repository;

import com.foreign.domain.Conversion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Temporal;
import org.springframework.stereotype.Repository;

import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;

/**
 * Created by bariscanakin on 7.3.2017.
 */
@Repository
public interface ConversionRepository extends JpaRepository<Conversion, Long> {

    List<Conversion> findByConversionDate(@Temporal(TemporalType.DATE) Date conversionDate);
}
