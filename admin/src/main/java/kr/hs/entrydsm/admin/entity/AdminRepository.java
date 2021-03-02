package kr.hs.entrydsm.admin.entity;

import kr.hs.entrydsm.admin.entity.Admin;

import java.util.Optional;

public interface AdminRepository {
    Optional<Admin> findById(String id);
}
