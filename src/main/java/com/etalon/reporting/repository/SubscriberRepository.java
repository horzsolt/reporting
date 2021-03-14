package com.etalon.reporting.repository;

import com.etalon.reporting.entity.SubscriberEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SubscriberRepository extends CrudRepository<SubscriberEntity, String> {

    public List<SubscriberEntity> findAllSubscribers();// why default findAll() returns with an iterable?
}
