package cn.xwtec.jumpserver.Pojo;

import java.util.ArrayList;
import java.util.List;

public class user {
    private String id;
    private String name;
    private String username;
    private String email;
    private List<String> groups;
    private String groups_display;
    private String role;
    private String role_display;
    private String wechat;
    private String phone;
    private int otp_level;
    private String comment;
    private String source;
    private String source_display;
    private boolean is_valid;
    private boolean is_expired;
    private boolean is_active;
    private String created_by;
    private boolean is_first_login;
    private String date_password_last_updated;
    private String date_expired;
    private String avatar_url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getGroups() {
        return groups;
    }

    public void setGroups(List<String> groups) {
        this.groups = groups;
    }

    public String getGroups_display() {
        return groups_display;
    }

    public void setGroups_display(String groups_display) {
        this.groups_display = groups_display;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRole_display() {
        return role_display;
    }

    public void setRole_display(String role_display) {
        this.role_display = role_display;
    }

    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getOtp_level() {
        return otp_level;
    }

    public void setOtp_level(int otp_level) {
        this.otp_level = otp_level;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getSource_display() {
        return source_display;
    }

    public void setSource_display(String source_display) {
        this.source_display = source_display;
    }

    public boolean isIs_valid() {
        return is_valid;
    }

    public void setIs_valid(boolean is_valid) {
        this.is_valid = is_valid;
    }

    public boolean isIs_expired() {
        return is_expired;
    }

    public void setIs_expired(boolean is_expired) {
        this.is_expired = is_expired;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }

    public boolean isIs_first_login() {
        return is_first_login;
    }

    public void setIs_first_login(boolean is_first_login) {
        this.is_first_login = is_first_login;
    }

    public String getDate_password_last_updated() {
        return date_password_last_updated;
    }

    public void setDate_password_last_updated(String date_password_last_updated) {
        this.date_password_last_updated = date_password_last_updated;
    }

    public String getDate_expired() {
        return date_expired;
    }

    public void setDate_expired(String date_expired) {
        this.date_expired = date_expired;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    @Override
    public String toString() {
        return "user{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", groups=" + groups +
                ", groups_display='" + groups_display + '\'' +
                ", role='" + role + '\'' +
                ", role_display='" + role_display + '\'' +
                ", wechat='" + wechat + '\'' +
                ", phone='" + phone + '\'' +
                ", otp_level=" + otp_level +
                ", comment='" + comment + '\'' +
                ", source='" + source + '\'' +
                ", source_display='" + source_display + '\'' +
                ", is_valid=" + is_valid +
                ", is_expired=" + is_expired +
                ", is_active=" + is_active +
                ", created_by='" + created_by + '\'' +
                ", is_first_login=" + is_first_login +
                ", date_password_last_updated='" + date_password_last_updated + '\'' +
                ", date_expired='" + date_expired + '\'' +
                ", avatar_url='" + avatar_url + '\'' +
                '}';
    }
}
