package com.picpay.desafio.domain.repositories;

import com.picpay.desafio.domain.entity.Transfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferRepository extends JpaRepository<Transfer, Long> {

}
