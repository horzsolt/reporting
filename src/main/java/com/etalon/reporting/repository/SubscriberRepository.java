package com.etalon.reporting.repository;

import com.etalon.reporting.entity.SubscriberEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SubscriberRepository extends CrudRepository<SubscriberEntity, String> {

    List<SubscriberEntity> findAll();// why default findAll() returns with an iterable?
}
