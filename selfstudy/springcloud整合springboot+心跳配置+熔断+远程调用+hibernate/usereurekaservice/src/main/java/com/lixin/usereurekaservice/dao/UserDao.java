package com.lixin.usereurekaservice.dao;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;
import com.lixin.usereurekaservice.model.UserInfo;

@Repository
public interface UserDao  extends CrudRepository<UserInfo,Long>
{
   
}



