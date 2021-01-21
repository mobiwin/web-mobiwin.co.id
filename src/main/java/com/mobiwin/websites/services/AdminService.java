package com.mobiwin.websites.services;

import java.util.List;

import com.mobiwin.websites.models.AdminModel;
import com.mobiwin.websites.repositories.AdminRepo;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    AdminRepo adminRepo;

    public void serviceInsert(String one, String two) {

        adminRepo.repoInsert(one, two);

    }

    public List<AdminModel> serviceUserPassword(String password_sessi,String userName) {
        var repoData = (List<AdminModel>) adminRepo.repoFindByPassword(password_sessi,userName);
        return repoData;
    }

    public List<AdminModel> serviceListUsersExcept(String password_sessi) {
        var repoData = (List<AdminModel>) adminRepo.repoFindUsersExcept(password_sessi);
        return repoData;
    }

    // public void updateFlaging(String flagUser, String idUser) {
    //     adminRepo.updateUserFlag(flagUser, idUser);
    // }
}
