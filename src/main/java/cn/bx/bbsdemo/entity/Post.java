package cn.bx.bbsdemo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "tb_post")
public class Post {

    /**
     * id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * 创建人
     * 如果使用：insertable = false, updatable = false，则保存不到数据库
     */
    @Column(name = "user_id", nullable = false,insertable = false, updatable = false)
    private long userId;

    /**
     * 标题
     */
    @Column(name = "title", nullable = false)
    private String title;

    /**
     * 内容
     */
    @Column(name = "content", nullable = false)
    private String content;

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
     * 多对一关系
     * @JoinColumn( name = "user_id",referencedColumnName = "id" )
     * 可以由一方来维护，放在这里维护，User表就不要了
     * 这里的user属性，不要使用 User user = new User()否则会出现级联问题
     */
    @ManyToOne
    @JoinColumn( name = "user_id",referencedColumnName = "id" )
    private User user;

    /**
     * 与Comment实体类的关联映射
     */
    @OneToMany(mappedBy = "post")
    @JsonIgnore
    private List<Comment> comments = new ArrayList<>();

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
