package com.foreign.repository;

import com.foreign.domain.Conversion;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by bariscanakin on 7.3.2017.
 */

public interface ConversionRepository extends JpaRepository<Conversion, Long> {
}
