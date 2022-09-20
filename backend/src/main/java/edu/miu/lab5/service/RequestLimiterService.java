package edu.miu.lab5.service;

import edu.miu.lab5.entity.RequestLimiter;

public interface RequestLimiterService {

	RequestLimiter findFirstByOrderByCreatedAtDesc();

}
