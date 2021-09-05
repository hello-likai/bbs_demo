package cn.bx.bbsdemo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "tb_user") // implements UserDetails
public class User  {

    /**
     * 用户id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * 用户名
     */
    @Column(name = "user_name", nullable = false)
    private String username;

    /**
     * 密码
     */
    @Column(name = "pass_word", nullable = false)
    private String password;

    /**
     * 创建时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @Column(name = "create_time", nullable = true)
    private Date createTime;

    /**
     * 更新时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    @Column(name = "update_time", nullable = true)
    private Date updateTime;

    /**
     * 配置User和Post的关联关系
     * name是关联表中的字段名，
     * referencedColumnName：本实体类的主键
     * 放在Post中维护，这里用mappedBy指定,mappedBy = "user" 这是字段名
     * 一对多中，在一的一方，使用 cascade = CascadeType.ALL，就要可以级联操作了
     */
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    // @JoinColumn( name="user_id",  referencedColumnName = "id" )
    private List<Post> posts = new ArrayList<>();


    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @JsonIgnore
    public String getUsername() {
        return null;
    }

    @JsonIgnore
    public boolean isAccountNonExpired() {
        return false;
    }

    @JsonIgnore
    public boolean isAccountNonLocked() {
        return false;
    }

    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        return false;
    }


    @JsonIgnore
    public boolean isEnabled() {
        return false;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
