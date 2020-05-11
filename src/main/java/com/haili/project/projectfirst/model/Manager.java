package com.haili.project.projectfirst.model;

public class Manager {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MANAGER.ID
     *
     * @mbg.generated Sat May 09 10:45:01 CST 2020
     */
    private Long id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MANAGER.ACCOUNT_ID
     *
     * @mbg.generated Sat May 09 10:45:01 CST 2020
     */
    private String accountId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MANAGER.NAME
     *
     * @mbg.generated Sat May 09 10:45:01 CST 2020
     */
    private String name;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MANAGER.PASSWORD
     *
     * @mbg.generated Sat May 09 10:45:01 CST 2020
     */
    private String password;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MANAGER.EMAIL
     *
     * @mbg.generated Sat May 09 10:45:01 CST 2020
     */
    private String email;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MANAGER.AVATAR
     *
     * @mbg.generated Sat May 09 10:45:01 CST 2020
     */
    private String avatar;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MANAGER.TOKEN
     *
     * @mbg.generated Sat May 09 10:45:01 CST 2020
     */
    private String token;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MANAGER.GMT_CREATE
     *
     * @mbg.generated Sat May 09 10:45:01 CST 2020
     */
    private Long gmtCreate;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column MANAGER.GMT_MODIFIED
     *
     * @mbg.generated Sat May 09 10:45:01 CST 2020
     */
    private Long gmtModified;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MANAGER.ID
     *
     * @return the value of MANAGER.ID
     *
     * @mbg.generated Sat May 09 10:45:01 CST 2020
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MANAGER.ID
     *
     * @param id the value for MANAGER.ID
     *
     * @mbg.generated Sat May 09 10:45:01 CST 2020
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MANAGER.ACCOUNT_ID
     *
     * @return the value of MANAGER.ACCOUNT_ID
     *
     * @mbg.generated Sat May 09 10:45:01 CST 2020
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MANAGER.ACCOUNT_ID
     *
     * @param accountId the value for MANAGER.ACCOUNT_ID
     *
     * @mbg.generated Sat May 09 10:45:01 CST 2020
     */
    public void setAccountId(String accountId) {
        this.accountId = accountId == null ? null : accountId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MANAGER.NAME
     *
     * @return the value of MANAGER.NAME
     *
     * @mbg.generated Sat May 09 10:45:01 CST 2020
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MANAGER.NAME
     *
     * @param name the value for MANAGER.NAME
     *
     * @mbg.generated Sat May 09 10:45:01 CST 2020
     */
    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MANAGER.PASSWORD
     *
     * @return the value of MANAGER.PASSWORD
     *
     * @mbg.generated Sat May 09 10:45:01 CST 2020
     */
    public String getPassword() {
        return password;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MANAGER.PASSWORD
     *
     * @param password the value for MANAGER.PASSWORD
     *
     * @mbg.generated Sat May 09 10:45:01 CST 2020
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MANAGER.EMAIL
     *
     * @return the value of MANAGER.EMAIL
     *
     * @mbg.generated Sat May 09 10:45:01 CST 2020
     */
    public String getEmail() {
        return email;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MANAGER.EMAIL
     *
     * @param email the value for MANAGER.EMAIL
     *
     * @mbg.generated Sat May 09 10:45:01 CST 2020
     */
    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MANAGER.AVATAR
     *
     * @return the value of MANAGER.AVATAR
     *
     * @mbg.generated Sat May 09 10:45:01 CST 2020
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MANAGER.AVATAR
     *
     * @param avatar the value for MANAGER.AVATAR
     *
     * @mbg.generated Sat May 09 10:45:01 CST 2020
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MANAGER.TOKEN
     *
     * @return the value of MANAGER.TOKEN
     *
     * @mbg.generated Sat May 09 10:45:01 CST 2020
     */
    public String getToken() {
        return token;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MANAGER.TOKEN
     *
     * @param token the value for MANAGER.TOKEN
     *
     * @mbg.generated Sat May 09 10:45:01 CST 2020
     */
    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MANAGER.GMT_CREATE
     *
     * @return the value of MANAGER.GMT_CREATE
     *
     * @mbg.generated Sat May 09 10:45:01 CST 2020
     */
    public Long getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MANAGER.GMT_CREATE
     *
     * @param gmtCreate the value for MANAGER.GMT_CREATE
     *
     * @mbg.generated Sat May 09 10:45:01 CST 2020
     */
    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column MANAGER.GMT_MODIFIED
     *
     * @return the value of MANAGER.GMT_MODIFIED
     *
     * @mbg.generated Sat May 09 10:45:01 CST 2020
     */
    public Long getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column MANAGER.GMT_MODIFIED
     *
     * @param gmtModified the value for MANAGER.GMT_MODIFIED
     *
     * @mbg.generated Sat May 09 10:45:01 CST 2020
     */
    public void setGmtModified(Long gmtModified) {
        this.gmtModified = gmtModified;
    }
}