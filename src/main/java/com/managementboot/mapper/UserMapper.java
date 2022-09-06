package com.managementboot.mapper;

import com.managementboot.entity.AuthUser;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserMapper {

    @Select("select * from users where name = #{username}")
    AuthUser getPasswordByUsername(String username);

    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    @Insert("insert into users(name, role, password) values(#{name}, #{role}, #{password})")
    int addRegisterUser(AuthUser user);
    @Insert("insert into student(uid, name, grade, sex) values(#{uid}, #{name}, #{grade}, #{sex})")
    int addStudnetInfo(@Param("uid") int uid, @Param("name") String name, @Param("grade") String grade, @Param("sex") String sex);

    @Select("select sid from student where uid = #{uid}")
    Integer getSidByUserId(int uid);

    @Select("select count(*) from student")
    int getStudnetCount();

}
