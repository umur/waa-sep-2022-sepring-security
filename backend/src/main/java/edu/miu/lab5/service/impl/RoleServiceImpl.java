package edu.miu.lab5.service.impl;

import edu.miu.lab5.entity.Role;
import edu.miu.lab5.repo.RoleRepo;
import edu.miu.lab5.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepo roleRepo;

    @Override
    public Role getByName(String name) {
        return roleRepo.findByRole(name);
    }
}
