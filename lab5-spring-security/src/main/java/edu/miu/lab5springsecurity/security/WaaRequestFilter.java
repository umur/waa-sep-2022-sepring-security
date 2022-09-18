package edu.miu.lab5springsecurity.security;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.miu.lab6springsecurity.entity.redis.VulgarityUsage;
import edu.miu.lab6springsecurity.model.ErrorResponse;
import edu.miu.lab6springsecurity.repository.UserRepo;
import edu.miu.lab6springsecurity.repository.VulgarityUsageRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Comparator;
import java.util.Date;
import java.util.stream.Collectors;

@Component
@Slf4j
public class WaaRequestFilter extends OncePerRequestFilter {
    @Autowired
    private VulgarityUsageRepo vulgarityUsageRepo;

    @Autowired
    private UserRepo userRepo;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("request uri is: " + request.getRequestURI());

        if(request.getRequestURI().equals("/api/v1/products/1/reviews")) {
            var email = ((UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
            var user = userRepo.findByEmail(email);
            var vulgarities = vulgarityUsageRepo.findByUserId(user.getId());

            var now = System.currentTimeMillis();
            var thirtyMinEarlier = now - 30 * 60 * 1000;

            vulgarities = vulgarities
                    .stream().filter(v -> v.getUsedAt().compareTo(new Date(thirtyMinEarlier)) > 0)
                    .collect(Collectors.toList());

            if(vulgarities.size() > 4) {
                var earliestDate = vulgarities.stream()
                        .sorted(Comparator.comparing(VulgarityUsage::getId).reversed())
                        .findFirst().map(v -> v.getUsedAt()).orElseGet(null);

                var remaining = earliestDate.getTime() + (30 * 60 * 1000) - now;
                remaining /= 60 * 1000;
                var errorMsg = "Max Bad Words Requests Limit has been Reached. You need to wait for " + remaining + " minutes.";
                ErrorResponse errorResponse = new ErrorResponse(errorMsg);
                response.setStatus(HttpStatus.REQUEST_TIMEOUT.value());
                response.getWriter().write(convertObjectToJson(errorResponse));
                return;
            }
        }
        filterChain.doFilter(request, response);
    }

    public String convertObjectToJson(Object object) throws JsonProcessingException {
        if (object == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }
}
