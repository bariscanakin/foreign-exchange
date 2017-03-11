package com.foreign.domain.repository;

import com.foreign.domain.model.Conversion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * Created by bariscanakin on 7.3.2017.
 */
@Repository
public interface ConversionRepository extends JpaRepository<Conversion, Long> {

    List<Conversion> findByConversionDate(LocalDate conversionDate);
}
