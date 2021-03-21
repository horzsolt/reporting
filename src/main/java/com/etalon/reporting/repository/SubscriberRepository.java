package com.etalon.reporting.repository;

import com.etalon.reporting.entity.Subscriber;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SubscriberRepository extends CrudRepository<Subscriber, String> {

    List<Subscriber> findAll();// why default findAll() returns with an iterable?
}
