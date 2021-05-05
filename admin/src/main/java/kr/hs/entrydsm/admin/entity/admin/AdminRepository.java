package kr.hs.entrydsm.admin.entity.admin;

import kr.hs.entrydsm.admin.entity.admin.Admin;

import java.util.Optional;

public interface AdminRepository {
    Optional<Admin> findById(String id);
}
