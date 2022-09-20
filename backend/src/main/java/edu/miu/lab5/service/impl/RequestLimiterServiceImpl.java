package edu.miu.lab5.service.impl;

import edu.miu.lab5.entity.RequestLimiter;
import edu.miu.lab5.repo.RequestLimiterRepo;
import edu.miu.lab5.service.RequestLimiterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RequestLimiterServiceImpl implements RequestLimiterService {

	private final RequestLimiterRepo requestLimiterRepo;

	public RequestLimiter findFirstByOrderByCreatedAtDesc() {
		return requestLimiterRepo.findFirstByOrderByCreatedAtDesc();
	}

}
