package net.newcapec.sca.user.das;

import java.util.List;

import net.newcapec.sca.param.FilterParam;
import net.newcapec.sca.user.UserPhoto;

public interface UserPhotoDAS 
{
    public UserPhoto getUserPhotoById(Integer id);
    public List<UserPhoto> findUserPhotoList(Integer domainId, Integer orgId, List<FilterParam> filter, Integer beginId, Integer limit);
    public Boolean insertUserPhoto(UserPhoto org);
    public Boolean updateUserPhoto(UserPhoto org);
    public Boolean delUserPhotoById(Integer id);
    //按照学工号删除照片记录
    public Boolean delUserPhotoByCust_code(String id);
    
    public UserPhoto getUserPhotoByUserCode(String code);
}